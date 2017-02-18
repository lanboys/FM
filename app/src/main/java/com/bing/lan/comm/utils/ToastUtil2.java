package com.bing.lan.comm.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:17
 * 描述:
 */
public class ToastUtil2 {

    private ToastUtil2() {
    }

    public static void show(CharSequence text) {
        if (text.length() < 10) {
            Toast.makeText(AppUtil.getAppContext(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AppUtil.getAppContext(), text, Toast.LENGTH_LONG).show();
        }
    }

    public static void show(@StringRes int resId) {
        show(AppUtil.getAppContext().getString(resId));
    }

}