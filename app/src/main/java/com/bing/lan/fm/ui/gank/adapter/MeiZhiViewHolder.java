package com.bing.lan.fm.ui.gank.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by gaohailong on 2016/5/17.
 */
public class MeiZhiViewHolder extends BaseViewHolder<GankBean.ResultsBean> {
    private ImageView image;

    public MeiZhiViewHolder(ViewGroup parent) {
        super(parent, R.layout.meizhi_item);
        image = $(R.id.image);

    }

    @Override
    public void setData(GankBean.ResultsBean data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);
        log.d("setData(): " + data.toString());
    }

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
}
