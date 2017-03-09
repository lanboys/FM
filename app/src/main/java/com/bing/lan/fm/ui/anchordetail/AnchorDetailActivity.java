package com.bing.lan.fm.ui.anchordetail;

import android.content.Intent;
import android.util.Log;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.fm.R;
import com.lzy.widget.PullZoomView;

public class AnchorDetailActivity extends BaseActivity<IAnchorDetailContract.IAnchorDetailPresenter>
        implements IAnchorDetailContract.IAnchorDetailView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_anchor_detail;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

        PullZoomView pzv = (PullZoomView) findViewById(R.id.pzv);
        pzv.setIsParallax(true);
        pzv.setIsZoomEnable(true);
        pzv.setSensitive(1.5f);
        pzv.setZoomTime(500);

        pzv.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onScroll   t:" + t + "  oldt:" + oldt);
                Log.d("fmapp", "onScroll   t:" + t + "  oldt:" + oldt);
            }

            @Override
            public void onHeaderScroll(int currentY, int maxY) {
                Log.d("fmapp", "onHeaderScroll   currentY:" + currentY + "  maxY:" + maxY);
            }

            @Override
            public void onContentScroll(int l, int t, int oldl, int oldt) {
                Log.d("fmapp", "onContentScroll   t:" + t + "  oldt:" + oldt);
            }
        });
        pzv.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onPullZoom(int originHeight, int currentHeight) {
                Log.d("fmapp", "onPullZoom  originHeight:" + originHeight + "  currentHeight:" + currentHeight);
            }

            @Override
            public void onZoomFinish() {
                Log.d("fmapp", "onZoomFinish");
            }
        });

    }

    @Override
    protected void readyStartPresenter() {

    }


}
