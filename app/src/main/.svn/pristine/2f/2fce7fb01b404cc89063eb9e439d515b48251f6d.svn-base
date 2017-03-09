package com.bing.lan.comm.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @author 蓝兵
 * @time 2017/2/6  11:35
 */
public abstract class BaseRecyclerViewHolder<D> extends RecyclerView.ViewHolder {

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public abstract void fillData(D data);

}
