package com.bing.lan.inke.yingke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.bing.lan.fm.R;
import com.bing.lan.inke.widght.IjkVideoView;
import com.bing.lan.inke.yingke.bean.LivesBean;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 测试
 */
public class MainActivity extends AppCompatActivity {

    private IjkVideoView mVideoView;
    private TableLayout mHudView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        /**获取直播间信息*/
        LivesBean livesBean = (LivesBean) getIntent().getSerializableExtra(PlayActivity.DATA);
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);

        mHudView = (TableLayout) findViewById(R.id.hud_view);

        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mVideoView.setVideoPath(livesBean.getStream_addr());
        mVideoView.setHudView(mHudView);
        mVideoView.showMediaInfo();
        mVideoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}
