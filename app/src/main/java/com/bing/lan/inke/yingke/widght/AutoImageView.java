package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.lang.reflect.Method;



public class AutoImageView extends ImageView {

    String TAG = "hked";
    int fristHeight = 762;
    public AutoImageView(Context context) {
        super(context);
    }

    public AutoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //fristHeight = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int screen = getDisplayHeight(getContext());
        int status = getStatusHeight(getContext());
        setMeasuredDimension(width,screen-status
        );
        Log.i(TAG, "onMeasure,height -->" + MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //super.onLayout(changed, l, t, r, b);
        Log.i(TAG, "changed ->>"+changed+"onLayout,left -->" + l + ",top -->>" + t + ", right -->>" + r + ",bottom -->>" + b);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged,width -->"
                + w
                + ",height -->>"
                + h
                + ", oldWidth -->>"
                + oldw
                + ",oldHeight -->>"
                + oldh);
    }


    public int getStatusHeight(Context context){

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

    public  int getDisplayHeight(Context context) {
        if(context == null) {
            return 0;
        }
        int height = 0;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        try {
            Class<?> cls = Display.class;
            Class<?>[] parameterTypes = { Point.class };
            Point parameter = new Point();
            Method method = cls.getMethod("getSize", parameterTypes);
            method.invoke(display, parameter);
            height = parameter.y;
        } catch (Exception e) {
            height = display.getHeight();
        }
        return height;
    }
}

