package com.bing.lan.comm.utils;

import android.app.Activity;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class ToastUtil {

    private static SuperToast sSuperToast;
    private static SuperToast sSuperActivityToast;

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

    private static void thisToast(final Activity act, String msg) {
        if (sSuperActivityToast == null) {
            sSuperActivityToast = SuperActivityToast.create(act, new Style(), Style.TYPE_STANDARD)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_ORANGE))
                    .setAnimations(Style.ANIMATIONS_POP);
        }
        if (msg.length() < 10) {
            sSuperActivityToast.setDuration(Style.DURATION_SHORT);
        } else {
            sSuperActivityToast.setDuration(Style.DURATION_LONG);
        }

        // TODO: 2017/2/25 不同的activity调用 是否应该清除
        sSuperActivityToast.setText(msg).show();
    }

    public static void showToast(String msg) {

        // if (sSuperToast == null) {
        //     sSuperToast = SuperToast.create(AppUtil.getAppContext(), msg, Style.DURATION_SHORT)
        //             .setFrame(Style.FRAME_KITKAT)
        //             .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_ORANGE))
        //             .setAnimations(Style.ANIMATIONS_POP);
        // }
        // if (msg.length() < 10) {
        //     sSuperToast.setDuration(Style.DURATION_SHORT);
        // } else {
        //     sSuperToast.setDuration(Style.DURATION_LONG);
        // }
        //
        // sSuperToast.setText(msg).show();
    }

    // SuperActivityToast.create(getActivity(), new Style(), Style.TYPE_BUTTON)
    //         .setButtonText("UNDO")
    // .setButtonIconResource(R.drawable.ic_undo)
    // .setOnButtonClickListener("good_tag_name", null, onButtonClickListener)
    // .setProgressBarColor(Color.WHITE)
    // .setText("Email deleted")
    // .setDuration(Style.DURATION_LONG)
    // .setFrame(Style.FRAME_LOLLIPOP)
    // .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_PURPLE))
    //         .setAnimations(Style.ANIMATIONS_POP).show();
}
