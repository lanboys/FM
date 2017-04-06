package com.bing.lan.fm.ui.category.adapter;

import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.category.bean.CategoryListBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;


public class SecendCategoryInfoDelagate implements ItemViewDelegate<CategoryListBean.ListBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.divide_category_home;
    }

    @Override
    public boolean isForViewType(CategoryListBean.ListBean item, int position) {
        return !item.isNormal();
    }

    @Override
    public void convert(ViewHolder holder, CategoryListBean.ListBean listBean, int position) {

    }
}
