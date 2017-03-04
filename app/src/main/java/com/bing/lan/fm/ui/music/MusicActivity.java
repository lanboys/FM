package com.bing.lan.fm.ui.music;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.musicplay.Music;
import com.bing.lan.comm.utils.musicplay.MusicPlayer;
import com.bing.lan.comm.view.CircularSeekBar;
import com.bing.lan.comm.view.DivergeView;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.album.bean.TracksInfoBean;
import com.bing.lan.fm.ui.music.bean.TrackInfoBean;
import com.dl7.player.danmaku.BiliDanmukuParser;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;

public class MusicActivity extends BaseActivity<IMusicContract.IMusicPresenter>
        implements IMusicContract.IMusicView {

    public static final String TRACK_POSITION = "track_position";
    public static final String TRACK_PLAYLIST = "track_playlist";
    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    public Runnable mUpdateCircularProgress;
    @BindView(R.id.song_progress_circular)
    CircularSeekBar mCircularProgress;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.sv_danmaku)
    IDanmakuView mDanmakuView;
    @BindView(R.id.previous)
    MaterialIconView mPrevious;
    @BindView(R.id.cir_play1)
    CircleImageView mCirPlay1;
    @BindView(R.id.cir_play)
    CircleImageView mCirPlay;
    @BindView(R.id.next)
    MaterialIconView mNext;
    @BindView(R.id.divergeView)
    DivergeView mDivergeView;
    private ArrayList<Music> mArrayList;
    private ArrayList<TracksInfoBean> mTrackInfos;
    private int mCurrentPlayPos;
    private long mCurrentTrackId;
    private BaseDanmakuParser mDanmakuParser;//解析器对象
    private DanmakuContext mDanmakuContext;
    private boolean mIsEnableDanmaku = true;
    // 弹幕加载器
    private ILoader mDanmakuLoader;
    private ArrayList<Bitmap> mList;
    private int mIndex = 0;

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

        setToolBar(mToolbar, "", true);
        if (Build.VERSION.SDK_INT >= 19) {
            mToolbar.post(new Runnable() {
                @Override
                public void run() {
                    int statusHeight = AppUtil.getStatusHeight();
                    mToolbar.setPadding(0, statusHeight, 0, 0);
                    ViewGroup.LayoutParams layoutParams = mToolbar.getLayoutParams();
                    layoutParams.height = mToolbar.getHeight() + statusHeight;
                }
            });
        }

        mTrackInfos = (ArrayList<TracksInfoBean>) intent.getSerializableExtra(TRACK_PLAYLIST);
        mCurrentPlayPos = intent.getIntExtra(TRACK_POSITION, 0);
        updateCurrentTrackId();

        mUpdateCircularProgress = new UpdateCircularProgress();
        initMusicPlay();
        initData();

        setDanmakuSource(getResources().openRawResource(R.raw.bili));
        initDanmakuView();

        initDivergeView();
    }

    private void initDivergeView() {
        mList = new ArrayList<>();
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_praise_sm1, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_praise_sm2, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_praise_sm3, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_praise_sm4, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_praise_sm5, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_praise_sm6, null)).getBitmap());

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIndex == 5) {
                    mIndex = 0;
                }
                mDivergeView.startDiverges(mIndex);
                mIndex++;
            }
        });
        mDivergeView.post(new Runnable() {
            @Override
            public void run() {
                mDivergeView.setEndPoint(new PointF(mDivergeView.getMeasuredWidth() / 2, 0));
                mDivergeView.setDivergeViewProvider(new DivergeView.DivergeViewProvider() {

                    @Override
                    public Bitmap getBitmap(Object obj) {
                        return mList == null ? null : mList.get((int) obj);
                    }
                });
            }
        });
    }

    /**
     * 设置弹幕资源，默认资源格式需满足 bilibili 的弹幕文件格式，
     * 配合{ setDanmakuCustomParser}来进行自定义弹幕解析方式，{ setDanmakuCustomParser}必须先调用
     *
     * @param stream 弹幕资源
     * @return
     */
    public void setDanmakuSource(InputStream stream) {
        if (stream == null) {
            return;
        }
        if (!mIsEnableDanmaku) {
            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
        }
        if (mDanmakuLoader == null) {
            mDanmakuLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        }
        try {
            mDanmakuLoader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        IDataSource<?> dataSource = mDanmakuLoader.getDataSource();
        if (mDanmakuParser == null) {
            mDanmakuParser = new BiliDanmukuParser();
        }
        mDanmakuParser.load(dataSource);
    }

    /**
     * 装载弹幕，在视频按了播放键才装载
     */
    private void initDanmakuView() {

        if (mIsEnableDanmaku) {
            // 设置弹幕
            mDanmakuContext = DanmakuContext.create();
            //同步弹幕和video，貌似没法保持同步，可能我用的有问题，先注释掉- -
            //            mDanmakuContext.setDanmakuSync(new VideoDanmakuSync(this));
            if (mDanmakuParser == null) {
                mDanmakuParser = new BaseDanmakuParser() {
                    @Override
                    protected Danmakus parse() {
                        return new Danmakus();
                    }
                };
            }
            mDanmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    // 这里处理下有时调用 _resumeDanmaku() 时弹幕还没 prepared 的情况
                    // if (mVideoView.isPlaying() && !mIsBufferingStart) {
                    mDanmakuView.start();
                    // }
                }

                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
                }

                @Override
                public void drawingFinished() {
                }
            });
            mDanmakuView.enableDanmakuDrawingCache(true);
            mDanmakuView.prepare(mDanmakuParser, mDanmakuContext);
        }
    }

    private void updateCurrentTrackId() {
        mCurrentTrackId = mTrackInfos.get(mCurrentPlayPos).getTrackId();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }

        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart(mCurrentTrackId);
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

    private void initData() {

        mArrayList = new ArrayList<>();
        for (TracksInfoBean s : mTrackInfos) {
            mArrayList.add(new Music(s.getPlayUrl32()));
        }
    }

    private void playMusic() {
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

    public void updateTrackInfo(TrackInfoBean trackInfoBean) {

    }

    @OnClick({R.id.previous, R.id.cir_play, R.id.cir_play1, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previous:
                break;
            case R.id.cir_play:
            case R.id.cir_play1:
                playMusic();
                break;
            case R.id.next:
                break;
        }
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
