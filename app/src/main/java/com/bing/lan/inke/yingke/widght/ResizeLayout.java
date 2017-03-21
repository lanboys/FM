package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.bing.lan.inke.yingke.util.SystemUtil;

public class ResizeLayout extends RelativeLayout {

    public static final byte KEYBOARD_STATE_SHOW = -3;
    public static final byte KEYBOARD_STATE_HIDE = -2;
    private static final String TAG = "ResizeLayout";
    //滚动的边际
    final int SCROLL_EDAGE = -250;
    Rect buttom;
    Scroller scroller;
    int width;
    private boolean mHasInit;
    private boolean mHasKeybord;
    private int mHeight;
    private int mDisplayHeigt;
    private int mKeyboardHeight;
    private int lastX;
    private OnResizeListener mListener;

    public ResizeLayout(Context context) {
        super(context);
    }

    public ResizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        int space = dip2px(context, 50);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        int status = getStatusHeight(context);
        buttom = new Rect(0, height - space - status, width, height);

        scroller = new Scroller(context);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setOnResizeListener(OnResizeListener l) {
        mListener = l;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        //        int action=ev.getAction();
        //        if(action==MotionEvent.ACTION_DOWN|mHasKeybord){
        //            Log.e(TAG,"onInterceptTouchEvent1");
        //            return false;
        //        }else {
        //            boolean isC = buttom.contains((int) ev.getX(), (int) ev.getY());
        //            if (isC) {
        //                Log.e(TAG,"onInterceptTouchEvent2");
        //                return false;
        //            } else {
        //                Log.e(TAG,"onInterceptTouchEvent3");
        //                return true;
        //            }
        //        }

        if (mHasKeybord) {
            return false;
        } else {

            boolean isC = buttom.contains((int) ev.getX(), (int) ev.getY());
            Log.i("TAG", "---->" + isC);
            if (isC) {
                return false;
            } else {
                int action = ev.getAction();
                if (action == MotionEvent.ACTION_DOWN | mHasKeybord) {
                    Log.e(TAG, "onInterceptTouchEvent1");
                    //这个代替了OnTouchEvent中的down事件
                    lastX = (int) ev.getX();
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mHasKeybord) {
            return false;
        } else {
            boolean isC = buttom.contains((int) event.getX(), (int) event.getY());
            //Log.i("hked", "isc = " + isC + " X = " + (int) event.getX() + " Y = " + (int) event.getY() + "buttom = " + buttom.toString());
            if (isC) {
                return false;
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = (int) event.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getX();
                    int space = nowX - lastX;
                    int will = getScrollX() - space;
                    Log.i("hked", "nowX=" + nowX + "  lastX=" + lastX + " will" + will);
                    if (will > 0) {
                        scrollTo(0, 0);
                    } else {
                        scrollBy(-space, 0);
                    }
                    lastX = nowX;
                    break;
                case MotionEvent.ACTION_UP:
                    int scrollX = getScrollX();
                    Log.i("hked", "scrollX = " + scrollX);
                    if (scrollX > SCROLL_EDAGE) {
                        scrollToLeft();
                    } else {
                        scrollToRight();
                    }
                    break;
            }

            return true;
        }
    }

    public void scrollToLeft() {
        scroller.startScroll(getScrollX(), 0, 0 - getScrollX(), 0);
        invalidate();
    }

    public void scrollToRight() {
        scroller.startScroll(getScrollX(), 0, -width - getScrollX(), 0);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //如果是0不处理
        if (h == 0 || w == 0) {
            return;
        }
        //第一次onSizeChanged的时候记录下全屏的高度。
        if (!mHasInit) {
            mHasInit = true;
            //当前控件的高度
            mHeight = h;
            Log.i("show", "mHeight = " + mHeight);
            mDisplayHeigt = SystemUtil.getDisplayHeight(getContext());
            return;
        }

        if (w != oldw) {//橫竖屏切换时不回调
            return;
        }

        //如果这次的控件高度小于初始化时候的高度的话，就是弹出了键盘
        if (!mHasKeybord && h < mHeight) {
            mHasKeybord = true;
            mKeyboardHeight = oldh - h;
            if (mListener != null) {
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_SHOW);
            }
        }
        //如果这次的控件高度等于初始化时候的高度的话，就是收起了键盘
        else if (mHasKeybord && mHeight == h) {
            mHasKeybord = false;
            mKeyboardHeight = h - oldh;
            if (mListener != null) {
                mListener.onKeyBoardStateChange(KEYBOARD_STATE_HIDE);
            }
        }
    }

    public int getStatusHeight(Context context) {

        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    public interface OnResizeListener {

        void onKeyBoardStateChange(int state);
    }
}
