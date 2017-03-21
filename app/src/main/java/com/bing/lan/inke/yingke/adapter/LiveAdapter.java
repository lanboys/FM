package com.bing.lan.inke.yingke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bing.lan.inke.yingke.bean.FragmentInfor;

import java.util.ArrayList;

/**
 * Created by kay on 16/11/15.
 */
public class LiveAdapter extends FragmentPagerAdapter {

    ArrayList<FragmentInfor> infors;

    public LiveAdapter(FragmentManager fm, ArrayList<FragmentInfor> all) {
        super(fm);
        this.infors = new ArrayList<>();
        this.infors.addAll(all);
    }

    @Override
    public Fragment getItem(int position) {
        return infors.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return infors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return infors.get(position).getName();
    }
}
