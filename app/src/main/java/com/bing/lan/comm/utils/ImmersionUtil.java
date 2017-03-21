package com.bing.lan.comm.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * @author 蓝兵
 * @time 2017/2/22  21:49
 */
public class ImmersionUtil {

    public static void initTranslucentStatus(Activity activity) {

        //借用沉浸式来实现透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            //1.设置界面布局全屏
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE  //具体不知道什么用
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  //将界面伸到状态栏下面,不隐藏状态栏
                    // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  //将界面伸到导航栏下面,不隐藏导航栏
                    // | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY        //实现触摸显示状态栏.导航栏
                    // | View.SYSTEM_UI_FLAG_FULLSCREEN              //隐藏状态栏
                    // | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION         //隐藏导航栏
            );

            // 2.给最外层布局根据版本调价padding值

            //3.将StatusBar颜色改为透明(注意不是系统StatusBar,
            // 系统的在21时,默认为透明,可以在values-21 NoActionBar主题中设置其他颜色
            // <item name="android:statusBarColor">@android:color/transparent</item>
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            // getWindow().setStatusBarColor(Color.BLACK);

            // getWindow().setNavigationBarColor(Color.TRANSPARENT);
            return;
        }
        //透明状态栏
        if (Build.VERSION.SDK_INT >= 19) {

            // api 19  1.系统StatusBar渐变半透明
            //         2.界面伸到系统StatusBar下面

            // api 21  1.系统StatusBar灰色透明(类似沉浸式效果颜色)
            //         2.隐藏非系统StatusBar(不会更改颜色)
            //         3.故,界面伸到系统StatusBar下面
            //         4.非全透明,故用上面的方法实现
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // TODO: 2017/2/9 添加一个view 放在 状态栏下面
            // 2.给最外层布局根据版本调价padding值

        }
    }

    /**
     * api < 19 的沉浸式设置方法
     */
    public static void initImmersionSmallApi19(Activity activity) {
        //在onCreate中调用
        if (Build.VERSION.SDK_INT < 19) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static void initImmersion(Activity activity) {
        //必须在onWindowFocusChanged这个方法中请求,否则从其他app跳转过来后,沉浸式效果就消失了
        // 系统StatusBar 灰色透明 ,自动隐藏,轻触就显示出来
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE  //具体不知道什么用
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY     //实现触摸显示状态栏.导航栏
                            | View.SYSTEM_UI_FLAG_FULLSCREEN //隐藏状态栏
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  //隐藏导航栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN     //将界面伸到状态栏下面,不隐藏状态栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //将界面伸到导航栏下面,不隐藏导航栏
            );
        }
    }
}
