package com.bing.lan.fm.ui.subscriber.delagate;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.comm.utils.AppUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/17  18:06
 */
public class GirlViewPagerAdapter extends PagerAdapter {

    private List<View> mData;
    private WeakReference<Context> mContextRef;

    public GirlViewPagerAdapter() {
        this(null);
    }

    public GirlViewPagerAdapter(Context context) {
        if (context != null) {
            this.mContextRef = new WeakReference<>(context);
        }
    }

    public void setData(List<View> data) {
        mData = data;
        notifyDataSetChanged();
    }

    /**
     * Just use to inflate layout.
     *
     * @return Context of a activity
     */
    Context getContext() {
        Context context = mContextRef.get();
        return context != null ? context : AppUtil.getAppContext();
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mData.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mData.get(position));
        return mData.get(position);
    }
}