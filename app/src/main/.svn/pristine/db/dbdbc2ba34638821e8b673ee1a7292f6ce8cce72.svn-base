package com.bing.lan.comm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

public class FlexibleScrollView extends ScrollView{
    //初始可拉动Y轴方向距离
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;
    //上下文环境
    private Context mContext;
    //实际可上下拉动Y轴上的距离
    private int mMaxYOverscrollDistance;  
      
    public FlexibleScrollView(Context context){
        super(context);  
        mContext = context;  
        initBounceListView();  
    }  
      
    public FlexibleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);  
        mContext = context;  
        initBounceListView();  
    }  
      
    public FlexibleScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
        mContext = context;  
        initBounceListView();  
    }  
      
    private void initBounceListView(){  
        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();  
            final float density = metrics.density;  
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);  
    }  
      
    @Override  
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,   
            int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //实现的本质就是在这里动态改变了maxOverScrollY的值
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverscrollDistance, isTouchEvent);    
    }  
      
}  
