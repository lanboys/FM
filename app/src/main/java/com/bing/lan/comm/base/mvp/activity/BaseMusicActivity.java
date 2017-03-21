package com.bing.lan.comm.base.mvp.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import com.bing.lan.comm.utils.DialogUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.musicplay.Music;
import com.bing.lan.comm.utils.musicplay.MusicPlayer;
import com.bing.lan.comm.utils.musicplay.MusicServiceCons;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseMusicActivity<T extends IBaseActivityContract.IBaseActivityPresenter>
        extends BaseActivity<T> {

    public static final int MSG_SAVE_DB = 11;
    private static final int MSG_PLAY_PAUSE = 0;
    private static final int MSG_PREVIOUS = 1;
    private static final int MSG_NEXT = 2;
    private static final int MSG_PLAY_POSITION = 3;
    private static final int MSG_INIT = 4;
    private static final int MSG_UPDATE_UI = 10;
    private static final int MSG_RECEIVER_MUSIC_BROADCAST = 111;

    protected MusicBroadcastReceiver mReceiver;
    protected MusicMainHandler mMainHandler;
    protected TimerTask mUpdateUITask = new UpdateUITask(this);
    protected HandlerThread mHandlerThread;
    private MusicPlayerHandler mPlayerHandler;
    private MusicPlayer.ServiceToken mServiceToken;
    private Timer mUpdateUITimer;
    private boolean isActivity = false;//界面处于可见状态标志
    private SweetAlertDialog mDialog;

    protected void initPlayHandler() {
        mHandlerThread = new HandlerThread("MusicPlayerHandler12",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        mHandlerThread.start();
        //子线程
        mPlayerHandler = new MusicPlayerHandler(this, mHandlerThread.getLooper());
        //主线程
        mMainHandler = new MusicMainHandler(this);
        //不断循环
        mUpdateUITask = new UpdateUITask(this);
    }

    private void onServiceConnected(ComponentName name, IBinder service) {

    }

    private void onServiceDisconnected(ComponentName name) {

    }

    private void initMusicPlay() {
        mServiceToken = MusicPlayer.bindToService(this, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                BaseMusicActivity.this.onServiceConnected(name, service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                BaseMusicActivity.this.onServiceDisconnected(name);
            }
        });
    }

    /**
     * 权限请求成功时调用
     */
    protected void requestPermissionSucceed() {
        registerMusicReceiver();
        initMusicPlay();
        initPlayHandler();
        readyStartPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }

        destroyHandler();
        MusicPlayer.unbindFromService(mServiceToken);
    }

    private void destroyHandler() {

        if (mMainHandler != null) {
            mMainHandler.removeCallbacksAndMessages(null);
            mMainHandler = null;
        }
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

        if (mUpdateUITask != null) {
            mUpdateUITask = null;
        }
    }

    @Override
    public void showDialog(String msg) {
        if (mDialog == null) {
            mDialog = DialogUtil.showProgressAlertDialog(this, msg);
        }
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    //注册广播接收者,接收音乐广播
    private void registerMusicReceiver() {
        mReceiver = new MusicBroadcastReceiver(this);
        final IntentFilter filter = new IntentFilter();
        filter.addAction(MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES_BROADCAST);
        // filter.addAction(MusicServiceCons.MUSIC_SAVE_MUSIC_DB_BROADCAST);
        registerReceiver(mReceiver, filter);
    }

    //子线程的操作(操作播放器)
    private void handlerMusicPlayerMessage(Message msg) {
        switch (msg.what) {
            case MSG_PLAY_PAUSE:
                MusicPlayer.playOrPause();
                break;
            case MSG_PREVIOUS:
                MusicPlayer.gotoPrev();
                break;
            case MSG_NEXT:
                MusicPlayer.gotoNext();
                break;
            case MSG_INIT:
                //初始化播放器列表
                MusicPlayer.setPlaylist((List<Music>) msg.obj);
                break;
            case MSG_PLAY_POSITION:
                MusicPlayer.goToPosition(msg.arg1);
                break;
        }
    }

    public void playOrPause() {
        mPlayerHandler.obtainMessage(MSG_PLAY_PAUSE).sendToTarget();
    }

    public void gotoNext() {
        mPlayerHandler.obtainMessage(MSG_NEXT).sendToTarget();
    }

    public void gotoPrev() {
        mPlayerHandler.obtainMessage(MSG_PREVIOUS).sendToTarget();
    }

    public void setMusicPlaylist(List<Music> playlist) {
        if (playlist != null) {
            mPlayerHandler.obtainMessage(MSG_INIT, playlist).sendToTarget();
        }
    }

    public void gotoPosition(int pos) {
        if (pos >= 0) {
            mPlayerHandler.obtainMessage(MSG_PLAY_POSITION, pos, 0).sendToTarget();
        }
    }

    //主线程 收到广播后的操作(发消息)
    protected abstract void handlerOnReceive(Intent intent);

    //主线程的操作(更新ui)
    protected void handlerMusicMainMessage(Message msg) {

        switch (msg.what) {

            case MSG_RECEIVER_MUSIC_BROADCAST:
                //启动ui更新
                startUIUpdate();
                //处理其他广播事件
                handlerOnReceive((Intent) msg.obj);
                break;

            //不断更新
            case MSG_UPDATE_UI:
                updateUIDisplay();
                break;
        }
    }

    private void startUIUpdate() {
        if (mUpdateUITimer == null) {
            mUpdateUITimer = new Timer();
            //500ms 执行一次
            mUpdateUITimer.schedule(new UpdateUITask(this), 0, 100);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActivity = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActivity = true;
        startUIUpdate();
    }

    protected abstract void updateUIDisplay();

    public static class UpdateUITask extends TimerTask {

        protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
        private final WeakReference<BaseMusicActivity> mBaseMusicActivity;

        public UpdateUITask(final BaseMusicActivity activity) {
            mBaseMusicActivity = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            BaseMusicActivity musicActivity = mBaseMusicActivity.get();
            if (musicActivity != null) {
                // musicActivity.updateUIDisplay();

                if (musicActivity.isActivity && MusicPlayer.isPlaying()) {
                    //10ms 更新一次
                    log.d("run(): 定时器发消息");
                    musicActivity.mMainHandler.sendEmptyMessage(MSG_UPDATE_UI);
                    return;
                }

                if (musicActivity.mUpdateUITimer != null) {
                    musicActivity.mUpdateUITimer.cancel();
                    musicActivity.mUpdateUITimer = null;
                }
            }
        }
    }

    public static class MusicPlayerHandler extends Handler {

        private final WeakReference<BaseMusicActivity> mBaseMusicActivity;

        public MusicPlayerHandler(final BaseMusicActivity activity, final Looper looper) {
            super(looper);
            mBaseMusicActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mBaseMusicActivity.get() != null) {
                mBaseMusicActivity.get().handlerMusicPlayerMessage(msg);
            }
        }
    }

    public static class MusicBroadcastReceiver extends BroadcastReceiver {

        private final WeakReference<BaseMusicActivity> mBaseMusicActivity;

        MusicBroadcastReceiver(BaseMusicActivity activity) {
            mBaseMusicActivity = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            BaseMusicActivity activity = mBaseMusicActivity.get();
            if (activity != null && activity.mMainHandler != null) {
                activity.mMainHandler.obtainMessage(MSG_RECEIVER_MUSIC_BROADCAST, intent).sendToTarget();
            }
        }
    }

    public static class MusicMainHandler extends Handler {

        private final WeakReference<BaseMusicActivity> mBaseMusicActivity;

        public MusicMainHandler(final BaseMusicActivity activity) {
            mBaseMusicActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mBaseMusicActivity.get() != null) {
                mBaseMusicActivity.get().handlerMusicMainMessage(msg);
            }
        }
    }
}
