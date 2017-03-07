package com.bing.lan.comm.utils.musicplay;

import android.os.Binder;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/3/2  12:52
 */
// public class ServiceStub extends IMusicService.Stub {
public class ServiceStub extends Binder implements IMusicService {

    private final WeakReference<MusicService> mService;

    public ServiceStub(final MusicService service) {
        mService = new WeakReference<>(service);
    }

    @Override
    public void setPlaylist(List<Music> playlist) {
        mService.get().setPlaylist(playlist);
    }

    @Override
    public void playOrPause() {
        mService.get().play();
    }

    @Override
    public void play() {
        mService.get().play();
    }

    @Override
    public void stop() {
        mService.get().pause();
    }

    @Override
    public void pause() {
        mService.get().pause();
    }

    @Override
    public boolean isPlaying() {
        return mService.get().isPlaying();
    }

    @Override
    public void gotoNext() {
        mService.get().gotoNext();
    }

    @Override
    public void gotoPrev() {
        mService.get().gotoPrev();
    }

    @Override
    public void goToPosition(int pos) {
        mService.get().goToPosition(pos);
    }

    @Override
    public long seek(long position) {
        return mService.get().seek(position);
    }

    @Override
    public void seekRelative(long deltaInMs) {
        mService.get().seekRelative(deltaInMs);
    }

    @Override
    public long position() {
        return mService.get().position();
    }

    @Override
    public long duration() {
        return mService.get().duration();
    }

    @Override
    public int getCurrentPlaylistPos() {
        return mService.get().getCurrentPlaylistPos();
    }
}
