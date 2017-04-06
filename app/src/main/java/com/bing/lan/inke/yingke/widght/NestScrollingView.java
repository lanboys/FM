package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;



public class NestScrollingView extends FrameLayout implements NestedScrollingChild {
    private static final String TAG ="NestScrollingView" ;

    public NestScrollingView(Context context) {
        super(context);
        initView();
    }



    public NestScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {


    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        super.setNestedScrollingEnabled(enabled);
    }
    @Override
    public boolean isNestedScrollingEnabled() {
        return super.isNestedScrollingEnabled();
    }
    @Override
    public boolean startNestedScroll(int axes) {
        return super.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
    }
    @Override
    public boolean hasNestedScrollingParent() {
        return super.hasNestedScrollingParent();
    }
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        Log.d(TAG,"dispatchNestedScroll"+"  dyConsumed="+dyConsumed+" dyUnconsumed="+dyUnconsumed);
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.d(TAG,"dispatchNestedPreScroll"+"  dy="+dy);
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }
    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return super.dispatchNestedFling(velocityX, velocityY, consumed);
    }
    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return super.dispatchNestedPreFling(velocityX, velocityY);
    }

}
