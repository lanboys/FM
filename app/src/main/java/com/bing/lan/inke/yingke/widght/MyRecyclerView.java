package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;



public class MyRecyclerView extends RecyclerView {
    private ResizeLayout resizeLayout;
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setResizeLayout(ResizeLayout resizeLayout) {
        this.resizeLayout = resizeLayout;
    }
        @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(resizeLayout!=null)
                     resizeLayout.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if(resizeLayout!=null)
                    resizeLayout.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int action=ev.getAction();
//        if(action==MotionEvent.ACTION_DOWN){
//            return false;
//        }else {
//
//        }
        return true;
    }
}
