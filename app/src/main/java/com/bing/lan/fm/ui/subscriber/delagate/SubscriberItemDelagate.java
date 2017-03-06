package com.bing.lan.fm.ui.subscriber.delagate;

import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.subscriber.bean.SubscriberItemBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * @author 蓝兵
 * @time 2017/3/5  16:50
 */
public class SubscriberItemDelagate implements ItemViewDelegate<SubscriberItemBean> {

    // 精品听单
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_subscriber;
    }

    @Override
    public boolean isForViewType(SubscriberItemBean item, int position) {
        return true;
        // return item.getList().get(0) instanceof ListItemSpecialBean;
    }

    @Override
    public void convert(ViewHolder holder, SubscriberItemBean itemBean, int position) {







    }
}
