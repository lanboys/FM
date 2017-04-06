package com.bing.lan.inke.yingke.widght;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;



public class RoundImage extends View {

    int half = 200;
    int small_half = 50;
    int all;
    Paint p;
    Paint other;
    int value ;

    ValueAnimator animator;
    public RoundImage(Context context) {
        super(context);
    }

    public RoundImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        all = half * 2 + 10;
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        other = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLUE);
        other.setColor(Color.GREEN);

        animator = ValueAnimator.ofInt(0,360);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
           public void onAnimationUpdate(ValueAnimator animation){
               value = (int) animation.getAnimatedValue();
                invalidate();
               Log.i("hked","value = "+value);
            }
        });
        animator.setDuration(2000);
        animator.start();
    }


    public void onStop(){
        animator.end();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(all, all);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);

        canvas.drawCircle(all / 2, all / 2, half, p);

        canvas.save();
        canvas.rotate(value,all/2,all/2);
        canvas.drawCircle(all / 2, 10 + 50, 50, other);
        canvas.restore();
    }
}
