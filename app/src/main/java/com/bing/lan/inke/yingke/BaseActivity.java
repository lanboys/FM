package com.bing.lan.inke.yingke;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * Created by kay on 16/12/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    MyHandler handler;

    public static  final int INIT_SUCCESS = 0;
    public static  final int DOWN_SUCCESS = 1;
    public static  final int LOAD_MORE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(useHandle()){
            handler = getHandler();
        }

    }

    public abstract boolean useHandle();

    public abstract  MyHandler getHandler();

    static class  MyHandler extends Handler{

        WeakReference<BaseActivity> baseActivity;

        public MyHandler(BaseActivity activity) {
            this.baseActivity = new WeakReference<BaseActivity>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            BaseActivity activity =  baseActivity.get();
            if(activity == null){
                return;
            }
        }
    }
}
