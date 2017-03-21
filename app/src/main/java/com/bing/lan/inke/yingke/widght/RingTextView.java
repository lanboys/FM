package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bing.lan.fm.R;

/**
 * Created by kay on 16/11/10.
 */
public class RingTextView extends TextView {

    public RingTextView(Context context) {
        super(context);
    }

    public RingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.top_mes_back));
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);


        int  h = this.getHeight();
        int  w = this.getWidth();

        int diameter = ((h > w) ? h : w);
        int radius = diameter/2;

        this.setHeight(diameter);
        this.setWidth(diameter);



        canvas.drawCircle(diameter / 2, diameter / 2, radius, circlePaint);

        super.draw(canvas);
    }
}
