package com.bing.lan.comm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bing.lan.fm.R;

//注意开通网络权限
public class RequestUtil {

    public static void loadImageByRequest(final Context context, String url, final CallBackListner<Bitmap> listner) {
        loadImageByRequest(context, null, null, url, listner);
    }

    public static void loadImageByRequest(final Context context, final ImageView img_pic, String url) {
        loadImageByRequest(context, null, img_pic, url, null);
    }

    public static void loadImageByRequest(final Context context, final Handler handler, String url) {
        loadImageByRequest(context, handler, null, url, null);
    }

    private static void loadImageByRequest(final Context context, final Handler handler, final ImageView img_pic, String url, final CallBackListner<Bitmap> listner) {
        //		1. 创建一个RequestQueue对象。
        RequestQueue mQueue = Volley.newRequestQueue(context);
        //		2. 创建一个Request对象。
        @SuppressWarnings("deprecation")
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {//一直是主线程执行

                if (img_pic != null) {//TODO
                    img_pic.setImageBitmap(response);
                    return;
                } else if (handler != null) {
                    Message message = Message.obtain();
                    message.obj = response;
                    handler.sendMessage(message);
                } else if (listner != null) {
                    listner.OnFinish(response);
                }

            }
        }, 0, 0, Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listner != null) {
                    listner.OnError(error);
                    return;
                }
                //TODO 打印日志
                Log.v("-->520it", "RequestUtil.onErrorResponse():68:: " + error.getLocalizedMessage());
                img_pic.setImageResource(R.mipmap.ic_launcher);
            }
        });
        //		3. 将Request对象添加到RequestQueue里面。
        mQueue.add(imageRequest);
    }

    /**
     * ImageLoader
     *
     * @param context
     * @param img_pic
     * @param url
     */

    public static void loadImageByImageLoader(Context context, final ImageView img_pic, String url) {

        //		1. 创建一个RequestQueue对象。
        RequestQueue mQueue = Volley.newRequestQueue(context);
        //		2. 创建一个ImageLoader对象。
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        //		3. 获取一个ImageListener对象。
        ImageListener listener = ImageLoader.getImageListener(img_pic, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        //		4. 调用ImageLoader的get()方法加载网络上的图片
        imageLoader.get(url, listener, 0, 0);
    }

    public static void loadStringByRequest(final Context context, String url, final CallBackListner<String> listner) {
        loadStringByRequest(context, null, null, url, listner);
    }

    public static void loadStringByRequest(final Context context, final View view, String url) {
        loadStringByRequest(context, null, view, url, null);
    }

    public static void loadStringByRequest(final Context context, final Handler handler, String url) {
        loadStringByRequest(context, handler, null, url, null);
    }

    private static void loadStringByRequest(Context context, final Handler handler, final View view, String url, final CallBackListner<String> listner) {

        //		1. 创建一个RequestQueue对象。
        RequestQueue mQueue = Volley.newRequestQueue(context);
        //		2. 创建一个StringRequest对象。
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//一直是主线程执行
                if (view instanceof TextView) {//TODO
                    ((TextView) view).setText(response);
                } else if (handler != null) {
                    Message message = Message.obtain();
                    message.obj = response;
                    handler.sendMessage(message);
                } else if (listner != null) {
                    listner.OnFinish(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (listner != null) {
                    listner.OnError(error);
                    return;
                }
                Log.e("loadStringByRequest: ", error.getMessage(), error);
            }
        });
        //		3. 将StringRequest对象添加到RequestQueue里面。
        mQueue.add(stringRequest);

    }

    public interface CallBackListner<T> {

        void OnFinish(T response);

        void OnError(Exception e);

    }
}
