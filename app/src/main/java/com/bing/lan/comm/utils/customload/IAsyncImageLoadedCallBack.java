package com.bing.lan.comm.utils.customload;

import android.graphics.Bitmap;

/**
 * 异步加载图片完成时的回调接口
 * 
 * @author Lean
 * */
public interface IAsyncImageLoadedCallBack {
	public void imageLoaded(Bitmap imageBitmap, String imgUrl);
}
