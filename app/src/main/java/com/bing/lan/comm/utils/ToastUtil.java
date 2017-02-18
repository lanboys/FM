package com.bing.lan.comm.utils;

import android.app.Activity;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast;

    public static void showToast(final Activity act, final String msg) {
        //获取当前线程
        String nowThreadName = Thread.currentThread().getName();
        //如果为主线程
        if ("main".equals(nowThreadName)) {
            thisToast(act, msg);

            //如果为子线程
        } else {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    thisToast(act, msg);
                }
            });
        }
    }

    public static void thisToast(Activity act, final String msg) {
        if (toast == null) {
            toast = Toast.makeText(act, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
