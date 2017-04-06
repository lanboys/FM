package com.bing.lan.inke.yingke.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;



public class UIUtils {

    private static boolean ishide=false;
    /**
     * 自低往上显示某个布局
     */
    public static void showLayout(View view, Context context){
        if(ishide) {
            /**如果view不可见*/
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
                int self = Animation.RELATIVE_TO_SELF;/**相对于自身*/
                Animation animation = new TranslateAnimation(self, 0f, self, 0f,
                        self, 1.0f, self, 0f);
                animation.setDuration(300);
//                        animation.setFillAfter(true);
                view.startAnimation(animation);
            }
            ishide = false;
        }
    }

    /**
     * 往底部隐藏某个布局
     */
    public static void hideLayout(View view, Context context){
        if(!ishide) {
            if (view.getVisibility() != View.GONE) {
                view.setVisibility(View.GONE);
                int self = Animation.RELATIVE_TO_SELF;
                Animation animation = new TranslateAnimation(self, 0f, self, 0f,
                        self, 0f, self, 1.0f);
                animation.setDuration(300);
//                        animation.setFillAfter(true);
                view.startAnimation(animation);
            }
            ishide = true;
        }
    }

}
