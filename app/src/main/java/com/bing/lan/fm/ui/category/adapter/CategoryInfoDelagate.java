package com.bing.lan.fm.ui.category.adapter;

import com.bing.lan.comm.utils.load.ImageLoader;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.category.bean.CategoryListBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * @author Holly
 * @time 2017/3/7  14:25
 * @desc ${TODD}
 */
public class CategoryInfoDelagate implements ItemViewDelegate<CategoryListBean.ListBean> {


    @Override
    public int getItemViewLayoutId() {
        return R.layout.category_item_child;
    }

    @Override
    public boolean isForViewType(CategoryListBean.ListBean item, int position) {
        return item.isNormal();
    }

    @Override
    public void convert(ViewHolder holder, CategoryListBean.ListBean listBean, int position) {
        if(position>0) {
            holder.setText(R.id.tv_category_home,listBean.getTitle());
            SimpleDraweeView view = holder.getView(R.id.iv_category_home_image);
            ImageLoader.getInstance().loadImage(view,listBean.getCoverPath());
        }

    }

}



