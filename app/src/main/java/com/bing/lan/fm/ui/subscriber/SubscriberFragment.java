package com.bing.lan.fm.ui.subscriber;

import android.app.Fragment;
import android.os.Bundle;

import com.bing.lan.fm.cons.Constants;

/**
 * @author 蓝兵
 * @time 2017/2/24  15:30
 */
public class SubscriberFragment extends Fragment {

    public static SubscriberFragment newInstance(String title) {
        SubscriberFragment fragment = new SubscriberFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }
}
