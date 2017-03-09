package com.bing.lan.comm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * @author 蓝兵
 * @time 2017/1/15  16:46
 */
public class ProgressBarButton extends Button {

    private long mProgress = 0;
    private long mMaxProgress = 0;
    private boolean isProgressEnable = false;

    public ProgressBarButton(Context context) {
        super(context);
    }

    public ProgressBarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isProgressEnable() {
        return isProgressEnable;
    }

    public void setProgressEnable(boolean progressEnable) {
        isProgressEnable = progressEnable;
    }

    public void setMaxProgress(long max) {
        mMaxProgress = max;
        invalidate();
    }

    public void setProgress(long progress) {
        mProgress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isProgressEnable) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.BLUE);
            int left = 0;
            int top = 0;
            int right = (int) (mProgress * 1.0f / mMaxProgress * getRight() + .5f);
            int bottom = getBottom();
            Rect bounds = new Rect(left, top, right, bottom);
            colorDrawable.setBounds(bounds);
            colorDrawable.draw(canvas);
        }

        super.onDraw(canvas);
    }
}
