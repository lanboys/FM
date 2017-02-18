package com.bing.lan.comm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.bing.lan.fm.R;

import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * 修改图片大小的工具类,无论是在布局中还是代码中都能使用
 * 当其中一个为确定值时,如: match_parent,120dp
 */

public class ImageAdapterLayout extends FrameLayout {

    public static final int RELATIVE_WIDTH = 0;//相对宽度,也就是说宽度是精确值
    public static final int RELATIVE_HEIGHT = 1;//相对高度,也就是说高度是精确值
    public int mCurrentRelative = RELATIVE_WIDTH;//默认为宽
    private float mRelative = 0;//宽度/高度的比值

    public ImageAdapterLayout(Context context) {
        super(context);
    }

    public ImageAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageAdapterLayout);
        mRelative = a.getFloat(R.styleable.ImageAdapterLayout_relative, mRelative);
        mCurrentRelative = a.getInt(R.styleable.ImageAdapterLayout_mode_space, mCurrentRelative);
        a.recycle();
    }

    //方便在代码中设置相对值
    public void setCurrentRelative(int currentRelative) {
        if (currentRelative >= 0 && currentRelative <= 1) {//代码只能是0 到 1
            this.mCurrentRelative = currentRelative;
        }
    }

    // <declare-styleable name="ImageAdapterLayout">
    // <attr name="relative" format="float"/>
    // <attr name="mode_space">
    // <enum name="width" value="0"/>
    // <enum name="height" value="1"/>
    // </attr>
    // </declare-styleable>

    public void setRelative(float relative) {
        mRelative = relative;
    }

    //如果是修改大小复写onMeasure

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**widthMode
         * 1 不确定(使用不多)
         * 2 精确模式  matchPartent 140dp
         * 3 不精确模式 warpContent
         *
         */
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //以宽度为确定值
        if (widthMode == MeasureSpec.EXACTLY && mRelative != 0 && mCurrentRelative == RELATIVE_WIDTH) {
            //通过父亲确定孩子的宽度
            int childWidth = widthSize - getPaddingLeft() - getPaddingRight();
            //通过父亲确定孩子的高度
            int childHeight = (int) (childWidth / mRelative + 0.5f);

            int childWidthMeasureSpec = makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);

            // 通知孩子,告诉他高度
            measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
            //设置父亲的宽高(宽度已确定)
            heightSize = childHeight + getPaddingTop() + getPaddingBottom();

            setMeasuredDimension(widthSize, heightSize);

            //以高度为确定值
        } else if (heightMode == MeasureSpec.EXACTLY && mRelative != 0 && mCurrentRelative == RELATIVE_HEIGHT) {
            //求孩子的宽度和高度
            int childHeight = heightSize - getPaddingBottom() - getPaddingTop();
            int childWidth = (int) (childHeight * mRelative + 0.5f);
            int childWidthMeasureSpec = makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            int childHeightMeasureSpec = makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
            //设置父亲的宽高(高度已确定)
            widthSize = childWidth + getPaddingRight() + getPaddingLeft();

            setMeasuredDimension(widthSize, heightSize);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
