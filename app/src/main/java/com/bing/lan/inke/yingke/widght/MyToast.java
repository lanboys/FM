package com.bing.lan.inke.yingke.widght;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.fm.R;

/**
 * Description :自定义Toast
 * Author : liujun
 * Email  : liujin2son@163.com
 * Date   : 2017/3/9 0009
 */

public class MyToast {

    private Toast toast;
    private Context context;
    private boolean isShow ;

    public MyToast(Context context) {
        this.context=context;
    }
    public  void showCustomToast() {
        View layout = View.inflate(context, R.layout.custom_toast,null);
        TextView tv_toast = (TextView) layout.findViewById(R.id.tv_toast);

        //避免toast长时间显示
        if (toast != null)
        {
            toast.cancel();
        }

        toast = new Toast(context);
        /**设计位置*/
        toast.setGravity(Gravity.LEFT|Gravity.TOP|Gravity.RIGHT, 0, 0);
        //短时间显示1秒，长时间显示5秒
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        isShow=true;
        /*** 新建一个定时器,两秒后cancle*/
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancleCustomToast();
            }
        },2000);

    }

    public boolean isShow() {
        return isShow;
    }

    /**
     * 使toast不在显示
     */
    public  void cancleCustomToast()
    {
        if (toast != null)
        {
            toast.cancel();
            isShow=false;
        }
    }





}
