package com.bing.lan.comm.utils.musicplay;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.bing.lan.comm.utils.LogUtil;

import java.lang.ref.WeakReference;

public final class MusicPlayerHandler extends Handler {

    private final WeakReference<MusicService> mService;
    private float mCurrentVolume = 1.0f;

    public MusicPlayerHandler(final MusicService service, final Looper looper) {
        super(looper);
        mService = new WeakReference<>(service);
    }

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    @Override
    public void handleMessage(final Message msg) {
        final MusicService service = mService.get();
        if (service == null) {
            return;
        }

        synchronized (service) {
            switch (msg.what) {
                //降低音量
                case MusicServiceCons.FADEDOWN:
                    mCurrentVolume -= .05f;
                    if (mCurrentVolume > .2f) {
                        sendEmptyMessageDelayed(MusicServiceCons.FADEDOWN, 10);
                    } else {
                        mCurrentVolume = .2f;
                    }
                    service.mPlayer.setVolume(mCurrentVolume);
                    break;
                //提高音量
                case MusicServiceCons.FADEUP:
                    mCurrentVolume += .01f;
                    if (mCurrentVolume < 1.0f) {
                        sendEmptyMessageDelayed(MusicServiceCons.FADEUP, 10);
                    } else {
                        mCurrentVolume = 1.0f;
                    }
                    service.mPlayer.setVolume(mCurrentVolume);
                    break;

                //播放结束,重置当前播放位置,继续播放
                case MusicServiceCons.TRACK_ENDED:
                    // TODO: 2017/3/8
                    // service.setPlaying(false);
                    service.resetCurrentPlayPosition();
                    service.play();
                    break;
                //播放下一首时处理(发送广播)
                case MusicServiceCons.TRACK_WENT_TO_NEXT:
                    service.setNextTrack();
                    break;
                //改变当前播放位置(第几首)
                case MusicServiceCons.HANDLER_CHANGE_CURR_POS:
                    service.changCurrentPlayPosition();
                    break;
                //状态改变,发送广播,让有需要的接受消息
                case MusicServiceCons.MUSIC_SERVICE_STATUS_CHANGES:
                    service.sendStatusBroadcast(msg.arg1);
                    break;

                default:
                    break;
            }
        }
    }
}
