package com.bing.lan.comm.utils;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author 蓝兵
 * @time 2017/2/25  22:54
 */
public class DialogUtil {

    public static SweetAlertDialog showProgressAlertDialog(Context context, String msg) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(msg);
        pDialog.setCancelable(false);
        pDialog.show();

        return pDialog;
    }
    public static void showAlertDialog(Context context, String msg) {
        new SweetAlertDialog(context)
                .setTitleText(msg)
                .show();
    }





}
