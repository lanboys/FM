package com.bing.lan.fm.ui.gank.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bing.lan.comm.utils.ImagePicassoUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
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

    private static class MeiZhiViewHolder extends BaseViewHolder<GankBean.ResultsBean> {

        protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
        private ImageView image;

          MeiZhiViewHolder(ViewGroup parent) {
            super(parent, R.layout.meizhi_item);
            image = $(R.id.image);
        }

        @Override
        public void setData(GankBean.ResultsBean data) {
            super.setData(data);

            // Glide.with(getContext())
            //         .load(data.getUrl())
            //         .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            //         .into(image);

            ImagePicassoUtil.loadImage(image,data.getUrl());
            log.d("setData(): " + data.toString());
        }
    }
}
