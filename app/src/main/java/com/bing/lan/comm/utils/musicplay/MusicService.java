package com.bing.lan.comm.utils.musicplay;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/3/1  16:48
 */
public class MusicService extends Service {

    public MultiPlayer mPlayer;
    private boolean mBePlaying = false;
    private int mCurrentPlayPos = -1;
    private int mNextPlayPos = -1;
    private MusicPlayerHandler mPlayerHandler;
    private HandlerThread mHandlerThread;
    private List<Music> mPlaylist = new ArrayList<>();
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            handleCommandIntent(intent);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceStub(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(MusicServiceCons.TAG, "Create service");

        new Thread() {
            @Override
            public void run() {
                initPlayerHandler();
                registerCmdReceiver();
                initPlayer();
            }
        }.start();
    }

    private void registerCmdReceiver() {
        // Initialize the intent filter and each action
        final IntentFilter filter = new IntentFilter();
        filter.addAction(MusicServiceCons.SERVICECMD);
        filter.addAction(MusicServiceCons.TOGGLEPAUSE_ACTION);
        filter.addAction(MusicServiceCons.PAUSE_ACTION);
        filter.addAction(MusicServiceCons.STOP_ACTION);
        filter.addAction(MusicServiceCons.NEXT_ACTION);
        filter.addAction(MusicServiceCons.PREVIOUS_ACTION);
        filter.addAction(MusicServiceCons.PREVIOUS_FORCE_ACTION);
        filter.addAction(MusicServiceCons.REPEAT_ACTION);
        filter.addAction(MusicServiceCons.SHUFFLE_ACTION);

        filter.addAction(MusicServiceCons.PLAY_ACTION);
        filter.addAction(MusicServiceCons.SET_PLAYLIST_ACTION);
        // Attach the broadcast listener
        registerReceiver(mIntentReceiver, filter);
    }

    private void initPlayer() {
        mPlayer = new MultiPlayer(this);
        mPlayer.setHandler(mPlayerHandler);
    }

    private void initPlayerHandler() {
        mHandlerThread = new HandlerThread("MusicPlayerHandler",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        mHandlerThread.start();
        mPlayerHandler = new MusicPlayerHandler(this, mHandlerThread.getLooper());
    }

    @Override
    public void onDestroy() {
        Log.d(MusicServiceCons.TAG, "Destroying service");
        super.onDestroy();
        if (mPlayerHandler != null) {
            mPlayerHandler.removeCallbacksAndMessages(null);
            mPlayerHandler = null;
        }

        if (mHandlerThread != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                mHandlerThread.quitSafely();
            else
                mHandlerThread.quit();
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
        if (mIntentReceiver != null) {
            unregisterReceiver(mIntentReceiver);
            mIntentReceiver = null;
        }
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        if (intent != null) {
            handleCommandIntent(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void handleCommandIntent(Intent intent) {
        final String action = intent.getAction();
        final String command = intent.getStringExtra(MusicServiceCons.CMD_NAME);

        Log.d(MusicServiceCons.TAG, "handleCommandIntent: action = " + action + ", command = " + command);

        if (MusicServiceCons.CMD_SET_PLAYLIST.equals(command) || MusicServiceCons.SET_PLAYLIST_ACTION.equals(action)) {
            ArrayList<Music> serializableExtra = (ArrayList<Music>) intent.getSerializableExtra(MusicServiceCons.PLAYLIST_URL);
            setPlaylist(serializableExtra);
        } else if (MusicServiceCons.CMD_NEXT.equals(command) || MusicServiceCons.NEXT_ACTION.equals(action)) {
            //播放下一首
            gotoNext();
        } else if (MusicServiceCons.CMD_PREVIOUS.equals(command) || MusicServiceCons.PREVIOUS_ACTION.equals(action)) {
            //播放上一首
            gotoPrev();
        } else if (MusicServiceCons.CMD_TOGGLEPAUSE.equals(command) || MusicServiceCons.TOGGLEPAUSE_ACTION.equals(action)) {
            //暂停/播放
            if (isPlaying()) {
                pause();
            } else {
                play();
            }
        } else if (MusicServiceCons.CMD_PAUSE.equals(command) || MusicServiceCons.PAUSE_ACTION.equals(action)) {
            //暂停
            pause();
        } else if (MusicServiceCons.CMD_PLAY.equals(command) || MusicServiceCons.PLAY_ACTION.equals(action)) {
            //播放
            play();
        } else if (MusicServiceCons.CMD_STOP.equals(command) || MusicServiceCons.STOP_ACTION.equals(action)) {
            //停止
            pause();
            seek(0);
        }
    }

    public void setPlaylist(List<Music> playlist) {
        if (playlist != null && playlist.size() > 0) {
            mPlaylist.clear();
            mPlaylist.addAll(playlist);

            mCurrentPlayPos = 0;
            if (!mPlayer.isInitialized()) {
                mPlayer.setDataSource(mPlaylist.get(mCurrentPlayPos).Url);
            }
        }
    }

    public void play() {
        if (!mPlayer.isInitialized()) {
            mPlayer.setDataSource(mPlaylist.get(mCurrentPlayPos).Url);
        }

        if (mPlayer.isInitialized()) {
            mPlayer.start();
            mPlayerHandler.removeMessages(MusicServiceCons.FADEDOWN);
            mPlayerHandler.sendEmptyMessage(MusicServiceCons.FADEUP);
            setBePlaying(true);
        }
    }

    public void setNextTrack() {
        //默认情况就是列表的下一首
        setNextTrack(getNextPosition());
    }

    private void setNextTrack(int position) {
        mNextPlayPos = position;
        Log.d(MusicServiceCons.TAG, "setNextTrack: next play position = " + mNextPlayPos);
        if (mNextPlayPos >= 0 && mPlaylist != null && mNextPlayPos < mPlaylist.size()) {
            mPlayer.setNextDataSource(mPlaylist.get(mNextPlayPos).Url);
        }
    }

    private int getNextPosition() {
        if (mPlaylist == null || mPlaylist.isEmpty()) {
            return -1;
        }
        return mCurrentPlayPos + 1;
    }

    private void stop() {

        if (mPlayer.isInitialized()) {
            mPlayer.stop();
        }
    }

    private void setBePlaying(boolean value) {
        if (mBePlaying != value) {
            mBePlaying = value;
        }
    }

    public void pause() {
        Log.d(MusicServiceCons.TAG, "Pausing playback");
        synchronized (this) {
            mPlayerHandler.removeMessages(MusicServiceCons.FADEUP);
            mPlayer.pause();
            setBePlaying(false);
        }
    }

    public boolean isPlaying() {
        return mBePlaying;
    }

    public void gotoNext() {
        goToPosition(mCurrentPlayPos + 1);
    }

    public void gotoPrev() {
        goToPosition(mCurrentPlayPos - 1);
    }

    public void goToPosition(int pos) {
        synchronized (this) {
            int size = mPlaylist.size();
            if (size <= 0) {
                Log.i(MusicServiceCons.TAG, "No play queue");
                return;
            }
            if (pos < 0) {
                Log.i(MusicServiceCons.TAG, "currentPlayPos: " + mCurrentPlayPos);
                return;
            }
            if (pos > size) {
                Log.i(MusicServiceCons.TAG, "Playlist.Size: " + size + ", currentPlayPos: " + mCurrentPlayPos);
                return;
            }

            if (pos == mCurrentPlayPos) {
                if (!isPlaying()) {
                    play();
                }
                return;
            }

            setNextTrack(pos);
            mCurrentPlayPos = pos;
            mNextPlayPos = mCurrentPlayPos + 1;
            mPlayer.gotoNext();
        }
    }

    public long seek(long position) {
        if (mPlayer.isInitialized()) {
            if (position < 0) {
                position = 0;
            } else if (position > mPlayer.duration()) {
                position = mPlayer.duration();
            }
            return mPlayer.seek(position);
        }
        return -1;
    }

    public void seekRelative(long deltaInMs) {
        synchronized (this) {
            if (mPlayer.isInitialized()) {
                final long newPos = position() + deltaInMs;
                final long duration = duration();
                if (newPos < 0) {
                    gotoPrev();
                    // seek to the new duration + the leftover position
                    seek(duration() + newPos);
                } else if (newPos >= duration) {
                    gotoNext();
                    // seek to the leftover duration
                    seek(newPos - duration);
                } else {
                    seek(newPos);
                }
            }
        }
    }

    public long position() {
        if (mPlayer.isInitialized()) {
            return mPlayer.position();
        }
        return -1;
    }

    public long duration() {
        if (mPlayer.isInitialized()) {
            return mPlayer.duration();
        }
        return -1;
    }

    void changCurrentPlayPosition() {
        mCurrentPlayPos++;
    }
}
