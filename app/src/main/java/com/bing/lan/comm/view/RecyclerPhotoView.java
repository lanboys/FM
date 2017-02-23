package com.bing.lan.comm.view;

import android.content.Context;
import android.util.AttributeSet;

import uk.co.senab.photoview.PhotoView;

/**
 * @author 蓝兵
 * @time 2017/2/20  17:33
 */
public class RecyclerPhotoView extends PhotoView {

    public RecyclerPhotoView(Context context) {
        super(context);
    }

    public RecyclerPhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public RecyclerPhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }
}
