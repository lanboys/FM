package com.bing.lan.fm.ui.gank.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by gaohailong on 2016/5/17.
 */
public class MeiZhiAdapter extends RecyclerArrayAdapter<GankBean.ResultsBean> {

    public MeiZhiAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiZhiViewHolder(parent);
    }
}
