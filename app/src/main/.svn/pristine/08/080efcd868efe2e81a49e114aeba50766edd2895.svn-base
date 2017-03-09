package com.bing.lan.comm.utils.musicplay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.List;
import java.util.WeakHashMap;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * @author 蓝兵
 * @time 2017/3/1  16:39
 */
public class MusicPlayer {

    private static final WeakHashMap<Context, ServiceBinder> mConnectionMap;
    private static IMusicService mService;

    static {
        mConnectionMap = new WeakHashMap<>();
    }

    // public static void setPlaylist(Context context, ArrayList<Music> arrayList) {
    //     Intent intent1 = new Intent(MusicServiceCons.SET_PLAYLIST_ACTION);
    //     intent1.putExtra(MusicServiceCons.CMD_NAME, MusicServiceCons.CMD_SET_PLAYLIST);
    //     intent1.putExtra(MusicServiceCons.PLAYLIST_URL, arrayList);
    //     context.sendBroadcast(intent1);
    // }
    //
    // public static void Play(Context context) {
    //     sendMusicCmd(context, MusicServiceCons.PLAY_ACTION, MusicServiceCons.CMD_PLAY);
    // }
    //
    // public static void startMusicService(Context context) {
    //     Intent intent = new Intent(context, MusicService.class);
    //     context.startService(intent);
    // }
    //
    // public static void sendMusicCmd(Context context, String action, String cmd) {
    //     Intent intent = new Intent(action);
    //     intent.putExtra(MusicServiceCons.CMD_NAME, cmd);
    //     context.sendBroadcast(intent);
    // }

    // public static ServiceToken startService(final Context context) {
    //     Activity realActivity = ((Activity) context).getParent();
    //     if (realActivity == null) {
    //         realActivity = (Activity) context;
    //     }
    //     final ContextWrapper contextWrapper = new ContextWrapper(realActivity);
    //
    //     Intent service = new Intent(contextWrapper, MusicService.class);
    //     contextWrapper.startService(service);
    //
    //     return new ServiceToken(contextWrapper);
    // }

    // public static void closeService(final ServiceToken token) {
    //     if (token == null) {
    //         return;
    //     }
    //     final ContextWrapper mContextWrapper = token.mWrappedContext;
    //     Intent service = new Intent(mContextWrapper, MusicService.class);
    //     mContextWrapper.stopService(service);
    // }

    public static ServiceToken bindToService(final Context context,
            final ServiceConnection callback) {

        Activity realActivity = ((Activity) context).getParent();
        if (realActivity == null) {
            realActivity = (Activity) context;
        }

        final ContextWrapper contextWrapper = new ContextWrapper(realActivity);

        Intent service = new Intent(contextWrapper, MusicService.class);
        //bind服务,都启动一次服务
        contextWrapper.startService(service);

        final ServiceBinder binder = new ServiceBinder(callback, contextWrapper.getApplicationContext());

        if (contextWrapper.bindService(service, binder, BIND_AUTO_CREATE)) {

            mConnectionMap.put(contextWrapper, binder);
            return new ServiceToken(contextWrapper);
        }
        return null;
    }

    public static void unbindFromService(final ServiceToken token) {
        if (token == null) {
            return;
        }
        final ContextWrapper mContextWrapper = token.mWrappedContext;
        final ServiceBinder mBinder = mConnectionMap.remove(mContextWrapper);
        if (mBinder == null) {
            return;
        }

        mContextWrapper.unbindService(mBinder);
        if (mConnectionMap.isEmpty()) {
            //没有连接停止服务
            Intent service = new Intent(mContextWrapper, MusicService.class);
            mContextWrapper.stopService(service);
            mService = null;
        }
    }

    public static void setPlaylist(List<Music> playlist) {
        if (mService != null && playlist != null && playlist.size() > 0) {
            mService.setPlaylist(playlist);
        }
    }

    public static void playOrPause() {
        try {
            if (mService != null) {
                if (mService.isPlaying()) {
                    mService.pause();
                } else {
                    mService.play();
                }
            }
        } catch (final Exception ignored) {
        }
    }

    public static final boolean isPlaying() {
        if (mService != null) {
            return mService.isPlaying();
        }
        return false;
    }

    public static void seek(final long position) {
        if (mService != null) {
            mService.seek(position);
        }
    }

    public static void seekRelative(final long deltaInMs) {
        if (mService != null) {
            try {
                mService.seekRelative(deltaInMs);
            } catch (final IllegalStateException ignored) {

            }
        }
    }

    public static final long getCurrentSeekPosition() {
        if (mService != null) {
            try {
                return mService.position();
            } catch (final IllegalStateException ex) {

            }
        }
        return 0;
    }

    public static final long getCurrentPlaylistPos() {
        if (mService != null) {
            try {
                return mService.getCurrentPlaylistPos();
            } catch (final IllegalStateException ex) {

            }
        }
        return 0;
    }

    public static final long getCurrentSeekPositionPercent() {
        if (mService != null) {
            try {
                return getCurrentSeekPosition()*100 / getDuration();
            } catch (final IllegalStateException ex) {

            }
        }
        return 0;
    }

    public static final long getDuration() {
        if (mService != null) {
            try {
                return mService.duration();
            } catch (final IllegalStateException ignored) {

            }
        }
        return 0;
    }

    public static void gotoNext() {
        if (mService != null) {
            mService.gotoNext();
        }
    }

    public static void gotoPrev() {
        if (mService != null) {
            mService.gotoPrev();
        }
    }

    public static void goToPosition(int pos) {
        if (mService != null) {
            mService.goToPosition(pos);
        }
    }

    public static final class ServiceBinder implements ServiceConnection {

        private final ServiceConnection mCallback;
        private final Context mContext;

        public ServiceBinder(final ServiceConnection callback, final Context context) {
            mCallback = callback;
            mContext = context;
        }

        @Override
        public void onServiceConnected(final ComponentName className, final IBinder service) {
            // mService = IMusicService.Stub.asInterface(service);
            mService = (IMusicService) service;

            if (mCallback != null) {
                mCallback.onServiceConnected(className, service);
            }
            // initPlaybackServiceWithSettings(mContext);
        }

        @Override
        public void onServiceDisconnected(final ComponentName className) {
            if (mCallback != null) {
                mCallback.onServiceDisconnected(className);
            }
            mService = null;
        }
    }

    public static final class ServiceToken {

        public ContextWrapper mWrappedContext;

        public ServiceToken(final ContextWrapper context) {
            mWrappedContext = context;
        }
    }
}
