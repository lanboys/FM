package com.bing.lan.comm.utils.musicplay;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;

import com.bing.lan.comm.utils.LogUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import static com.bing.lan.comm.utils.musicplay.MusicServiceCons.STATUS_NEXT;
import static com.bing.lan.comm.utils.musicplay.MusicServiceCons.STATUS_NEXT_CUSTOM;
import static com.bing.lan.comm.utils.musicplay.MusicServiceCons.STATUS_NO_NEXT;
import static com.bing.lan.comm.utils.musicplay.MusicServiceCons.STATUS_PAUSE;
import static com.bing.lan.comm.utils.musicplay.MusicServiceCons.STATUS_START;
import static com.bing.lan.comm.utils.musicplay.MusicServiceCons.STATUS_STOP;

/**
 * @author 蓝兵
 * @time 2017/3/1  22:12
 */
public final class MultiPlayer implements MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {

    private final WeakReference<MusicService> mService;

    private MediaPlayer mCurrentMediaPlayer = new MediaPlayer();

    private MediaPlayer mNextMediaPlayer;

    private Handler mPlayerHandler;

    private boolean mIsInitialized = false;

    private String mNextMediaPath;

    public MultiPlayer(final MusicService service) {
        mService = new WeakReference<>(service);
        mCurrentMediaPlayer.setWakeMode(mService.get(), PowerManager.PARTIAL_WAKE_LOCK);
    }

    //首次设置音乐资源
    public void setDataSource(final String path) {
        try {

            if (mCurrentMediaPlayer == null) {
                mCurrentMediaPlayer = new MediaPlayer();
            }
            mIsInitialized = setDataSourceImpl(mCurrentMediaPlayer, path);
            if (!mIsInitialized) {
                //设置失败,释放资源
                if (mCurrentMediaPlayer != null) {
                    mCurrentMediaPlayer.release();
                    mCurrentMediaPlayer = null;
                }
                Log.w("fm", "MusicPlay,setDataSource失败");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    //设置下一首歌资源
    public void setNextDataSource(final String path) {
        mNextMediaPath = null;
        try {
            mCurrentMediaPlayer.setNextMediaPlayer(null);
        } catch (IllegalArgumentException e) {
            Log.i(MusicServiceCons.TAG, "Next media player is current one, continuing");
        } catch (IllegalStateException e) {
            Log.e(MusicServiceCons.TAG, "Media player not initialized!");
            return;
        }
        if (mNextMediaPlayer != null) {
            mNextMediaPlayer.release();
            mNextMediaPlayer = null;
        }
        if (path == null) {
            return;
        }
        mNextMediaPlayer = new MediaPlayer();
        mNextMediaPlayer.setWakeMode(mService.get(), PowerManager.PARTIAL_WAKE_LOCK);
        try {
            //判断是否设置成功
            if (setDataSourceImpl(mNextMediaPlayer, path)) {
                mNextMediaPath = path;
                log.d("setNextDataSource():mNextMediaPath " + mNextMediaPath);
                mCurrentMediaPlayer.setNextMediaPlayer(mNextMediaPlayer);
            } else {
                if (mNextMediaPlayer != null) {
                    mNextMediaPlayer.release();
                    mNextMediaPlayer = null;
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    //设置歌曲资源具体实现
    private boolean setDataSourceImpl(final MediaPlayer player, final String path) {
        try {
            player.reset();
            player.setOnPreparedListener(null);
            if (path.startsWith("content://")) {
                player.setDataSource(mService.get(), Uri.parse(path));
            } else {
                player.setDataSource(path);
            }
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.prepare();//没网络 会anr
        } catch (final IOException todo) {
            Log.e("fm", todo + "");
            return false;
        } catch (final IllegalArgumentException todo) {
            Log.e("fm", todo + "");
            return false;
        }
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
        return true;
    }

    public void setHandler(final Handler handler) {
        mPlayerHandler = handler;
    }

    public boolean isInitialized() {
        return mIsInitialized;
    }

    public void start() {
        // if (mNextMediaPlayer == null) {
        //     mPlayerHandler.sendEmptyMessage(MusicServiceCons.TRACK_WENT_TO_NEXT);
        // }
        mCurrentMediaPlayer.start();
        mPlayerHandler.obtainMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES, STATUS_START).sendToTarget();
    }

    public void stop() {
        mCurrentMediaPlayer.reset();
        mIsInitialized = false;
        mPlayerHandler.obtainMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES, STATUS_STOP).sendToTarget();
    }

    public void release() {
        mCurrentMediaPlayer.release();
    }

    public void pause() {
        mCurrentMediaPlayer.pause();
        mPlayerHandler.obtainMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES, STATUS_PAUSE).sendToTarget();
    }

    public long duration() {
        return mCurrentMediaPlayer.getDuration();
    }

    public long position() {
        return mCurrentMediaPlayer.getCurrentPosition();
    }

    public long seek(final long whereto) {
        mCurrentMediaPlayer.seekTo((int) whereto);
        return whereto;
    }

    public void setVolume(final float vol) {
        try {
            mCurrentMediaPlayer.setVolume(vol, vol);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onError(final MediaPlayer mp, final int what, final int extra) {
        // Log.w(TAG, "Music Server Error what: " + what + " extra: " + extra);
        // switch (what) {
        //     case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
        //         final MusicService service = mService.get();
        //         final TrackErrorInfo errorInfo = new TrackErrorInfo(service.getAudioId(),
        //                 service.getTrackName());
        //
        //         mIsInitialized = false;
        //         mCurrentMediaPlayer.release();
        //         mCurrentMediaPlayer = new MediaPlayer();
        //         mCurrentMediaPlayer.setWakeMode(service, PowerManager.PARTIAL_WAKE_LOCK);
        //         Message msg = mPlayerHandler.obtainMessage(SERVER_DIED, errorInfo);
        //         mPlayerHandler.sendMessageDelayed(msg, 2000);
        //         return true;
        //     default:
        //         break;
        // }
        return false;
    }

    @Override
    public void onCompletion(final MediaPlayer mp) {
        if (mp == mCurrentMediaPlayer && mNextMediaPlayer != null) {
            mCurrentMediaPlayer.release();
            mCurrentMediaPlayer = mNextMediaPlayer;
            mNextMediaPath = null;
            mNextMediaPlayer = null;
            mPlayerHandler.sendEmptyMessage(MusicServiceCons.HANDLER_CHANGE_CURR_POS);//注意顺序
            mPlayerHandler.sendEmptyMessage(MusicServiceCons.TRACK_WENT_TO_NEXT);
            // mPlayerHandler.sendEmptyMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES);
            mPlayerHandler.obtainMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES, STATUS_NEXT).sendToTarget();
        } else {
            mPlayerHandler.sendEmptyMessage(MusicServiceCons.TRACK_ENDED);
            // mPlayerHandler.sendEmptyMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES);
            mPlayerHandler.obtainMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES, STATUS_NO_NEXT).sendToTarget();

            // mPlayerHandler.sendEmptyMessage(RELEASE_WAKELOCK);
        }
    }

    //强制设置下一首(不一定是列表的)
    public void gotoPosition() {
        if (mCurrentMediaPlayer != null && mNextMediaPlayer != null) {
            if (mCurrentMediaPlayer.isPlaying()) {
                mCurrentMediaPlayer.stop();
            }
            mCurrentMediaPlayer.release();
            mCurrentMediaPlayer = mNextMediaPlayer;
            mNextMediaPath = null;
            mNextMediaPlayer = null;
            //直接启动播放下一首(在服务中已经设置)
            // start();
            mPlayerHandler.sendEmptyMessage(MusicServiceCons.TRACK_WENT_TO_NEXT);
            // mPlayerHandler.sendEmptyMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES);
            // mPlayerHandler.sendEmptyMessage(TRACK_WENT_TO_NEXT);

            mPlayerHandler.obtainMessage(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES, STATUS_NEXT_CUSTOM).sendToTarget();
        }
    }
}


