package com.bing.lan.fm.ui.search.delagate;

import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.ListItemEditorBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * @author 蓝兵
 * @time 2017/2/17  17:44
 */
public class EditorRecomItemDelagate
        implements com.zhy.adapter.recyclerview.base.ItemViewDelegate<HotInfoBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.hot_item;
    }

    @Override
    public boolean isForViewType(HotInfoBean item, int position) {
        return true;
        // return item.getList().get(0) instanceof ListItemEditorBean;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void convert(ViewHolder holder, HotInfoBean hotInfoBean, int position) {
        holder.setText(R.id.tv_hot_item_title, hotInfoBean.getTitle());
        if (!(hotInfoBean.getList().get(0) instanceof ListItemEditorBean)) {
            return;
        }
    }
}
