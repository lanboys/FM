package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class NestedScrollLayout extends LinearLayout implements NestedScrollingParent {

    private static final String TAG = "NestedScrollLayout";
    private static boolean isShowingBottom = true;
    private int mBottomViewHeight;
    private boolean isFirstTime = true;
    private LinearLayout bottomLayout;
    private RelativeLayout.LayoutParams layout;

    public NestedScrollLayout(Context context) {
        super(context);
        initView();
    }

    public NestedScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NestedScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (isFirstTime) {
            RelativeLayout mRelativeLayout = (RelativeLayout) getChildAt(0);
            /**找到底部的Bottom 布局*/
            bottomLayout = (LinearLayout) mRelativeLayout.getChildAt(1);
            layout = (RelativeLayout.LayoutParams) bottomLayout.getLayoutParams();
            mBottomViewHeight = bottomLayout.getHeight();
            layout.height = mBottomViewHeight;
            isFirstTime = false;
            isShowingBottom = true;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /***
     * 初始化View
     */
    private void initView() {

    }

    /**
     * 该方法，一定要按照自己的需求返回true，该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
     * 假设你只涉及到纵向滑动，这里可以根据nestedScrollAxes这个参数，进行纵向判断
     *
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    /**
     * 该方法的会传入内部View移动的dx,dy，如果你需要消耗一定的dx,dy，
     * 就通过最后一个参数consumed进行指定，例如我要消耗一半的dy，
     * 就可以写consumed[1]=dy/2
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        /**
         * 向上滑动dy 为正
         * getScrollY（）是获取父控件滑动距离，向上滑动dy 为正
         * mTopViewHeight 是头部的高度
         * */
        Log.e(TAG, "isShowingBottom= " + isShowingBottom + "  layoutParams.height=" + layout.height + "  dy=" + dy);
        if (dy > 0) {
            /**显示*/
            if (!isShowingBottom) {
                layout.height = layout.height + dy;
                if (mBottomViewHeight - 10 <= layout.height) {
                    layout.height = mBottomViewHeight;
                    isShowingBottom = true;
                }
                bottomLayout.setLayoutParams(layout);
            }
        } else {
            /**隐藏*/
            if (isShowingBottom) {
                layout.height = layout.height + dy;
                if (10 >= layout.height) {
                    layout.height = 0;
                    isShowingBottom = false;
                }
                bottomLayout.setLayoutParams(layout);
            }
        }

        //        boolean hiddenTop = dy > 0 && getScrollY() < mBottomViewHeight;//如果是向上滑动，并且头部可见
        //        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);//如果是向下滑动，
        //        Log.e(TAG, "onNestedPreScroll "+"  dy="+dy+"  getScrollY()="+getScrollY()+" mTopViewHeight="+mBottomViewHeight+"  hiddenTop="+hiddenTop+" showTop=");
        //        if (hiddenTop)
        //        {
        //            /**让父控件滑动*/
        //            scrollBy(0, dy);
        //            /**让父控件消费此次滑动事件*/
        //            consumed[1] = dy;
        //        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {

        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return true;
    }

    /****
     * 下面的方法暂时没有用到
     */

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        //        Log.e(TAG, "onNestedScrollAccepted");
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.e(TAG, "onStopNestedScroll");
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //        Log.e(TAG, "onNestedScroll");
    }

    @Override
    public int getNestedScrollAxes() {
        Log.e(TAG, "getNestedScrollAxes");
        return 0;
    }
}
