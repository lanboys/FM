// IMusicService.aidl
package com.bing.lan.comm.utils.musicplay;

// Declare any non-default types here with import statements

import java.util.List;

interface IMusicService {

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    boolean setPlaylist(List<Music> playlist);

    Music getCurrentPlayMusic();

    void playOrPause();

    void play();

    void stop();

    void pause();

    boolean isPlaying();

    void gotoNext();

    void gotoPrev();

    void goToPosition(int pos);

    long seek(long position);

    void seekRelative(long deltaInMs);

    long position();

    long duration();

    int getCurrentPlaylistPos();

    boolean addPlaylist(List<Music> playlist);

    boolean addPlaylist(Music play);

    List<Music> getPlaylist();

}











