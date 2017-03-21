package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by kay on 17/1/2.
 */

public class UnScrollGridView extends GridView {
    public UnScrollGridView(Context context) {
        super(context);
    }

    public UnScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }


}
