package com.bing.lan.fm.ui.music;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.musicplay.Music;
import com.bing.lan.comm.utils.musicplay.MusicPlayer;
import com.bing.lan.comm.view.CircularSeekBar;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.album.bean.TracksInfoBean;

import java.util.ArrayList;

import butterknife.BindView;

public class MusicActivity extends BaseActivity<IMusicContract.IMusicPresenter>
        implements IMusicContract.IMusicView {

    public static final String TRACK_ID = "track_id";
    public static final String TRACK_POSITION = "track_position";
    public static final String ALBUM_ID = "album_id";
    public static final String TRACK_UID = "track_uid";
    public static final String TRACK_PLAYLIST = "track_playlist";
    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    public Runnable mUpdateCircularProgress;
    @BindView(R.id.song_progress_circular)
    CircularSeekBar mCircularProgress;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private ArrayList<Music> mArrayList;
    private ArrayList<TracksInfoBean> mTrackInfos;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_music;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_music;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        mUpdateCircularProgress = new UpdateCircularProgress();
        initMusicPlay();
        initData(intent);

        initView();
    }

    @Override
    protected void readyStartPresenter() {

    }

    private void initMusicPlay() {
        MusicPlayer.bindToService(this, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        });
    }

    private void initData(Intent intent) {
        mTrackInfos = (ArrayList<TracksInfoBean>) intent.getSerializableExtra(TRACK_PLAYLIST);

        mArrayList = new ArrayList<>();
        for (TracksInfoBean s : mTrackInfos) {
            mArrayList.add(new Music(s.getPlayUrl32()));
        }
    }

    private void initView() {

        setToolBar(mToolbar, "", true);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        MusicPlayer.setPlaylist(mArrayList);
                        // MusicPlayer.Play(MusicActivity.this);
                        MusicPlayer.playOrPause();
                        updateCircleProgress();
                    }
                }.start();
            }
        });
    }

    public void updateCircleProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mCircularProgress != null) {
                    mCircularProgress.setMax((int) MusicPlayer.duration());
                    if (mUpdateCircularProgress != null) {
                        mCircularProgress.removeCallbacks(mUpdateCircularProgress);
                    }
                    mCircularProgress.postDelayed(mUpdateCircularProgress, 10);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class UpdateCircularProgress implements Runnable {

        @Override
        public void run() {
            if (mCircularProgress != null) {
                long position = MusicPlayer.position();
                mCircularProgress.setProgress((int) position);

                if (MusicPlayer.isPlaying()) {
                    mCircularProgress.postDelayed(mUpdateCircularProgress, 50);
                }
            }
        }
    }
}
