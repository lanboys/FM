package com.bing.lan.inke.yingke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.fragment.StoreFragment;

/**
 *
 * Created by kay on 16/12/22.
 */

public class Demo2Activity extends AppCompatActivity {

    StoreFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        FragmentManager fm = getSupportFragmentManager();
        fragment = (StoreFragment) fm.findFragmentByTag("store");
    }

    public void click(View view) {
        if (fragment.isShowStore()) {
            fragment.hideContent();
        } else {
            fragment.showContent();
        }


    }


}
