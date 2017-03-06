package com.bing.lan.fm.ui.subscriber.delagate;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.ListItemDiscoverBean;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/3/5  16:50
 */
public class DiscoverItemDelagate implements ItemViewDelegate<HotInfoBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.hot_item_discover;
    }

    @Override
    public boolean isForViewType(HotInfoBean item, int position) {
        return item.getList().get(0) instanceof ListItemDiscoverBean;
    }

    @Override
    public void convert(ViewHolder holder, HotInfoBean hotInfoBean, int position) {
        List<ListItemDiscoverBean> list = hotInfoBean.getList();
        initChildRecyclerView(holder, list);
    }

    private void initChildRecyclerView(ViewHolder holder, List<ListItemDiscoverBean> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(AppUtil.getAppContext(),
                LinearLayoutManager.HORIZONTAL, false);

        RecyclerView itemRecyclerView = holder.getView(R.id.rv_hot_item_child);
        itemRecyclerView.setLayoutManager(layoutManager);
        itemRecyclerView.setHasFixedSize(true);

        DiscoverChildAdapter adapter = new DiscoverChildAdapter(AppUtil.getAppContext(),
                R.layout.hot_item_child_discover, list);

        itemRecyclerView.setAdapter(adapter);
        // adapter.setOnItemClickListener(this);
    }

    private static class DiscoverChildAdapter extends CommonAdapter<ListItemDiscoverBean> {

        protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

        DiscoverChildAdapter(Context context, int layoutId, List<ListItemDiscoverBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, ListItemDiscoverBean listItemEditorBean, int position) {

            View view = holder.getView(R.id.ll_child_container);

            view.setTag(listItemEditorBean);

            holder.setText(R.id.tv_discover_Title, listItemEditorBean.getTitle());

            final SimpleDraweeView draweeView = holder.getView(R.id.iv_cover_image);
            com.bing.lan.comm.utils.load.ImageLoader
                    .getInstance()
                    .loadImage(draweeView,
                            listItemEditorBean.getCoverPath(),
                            new BaseControllerListener<ImageInfo>() {
                                @Override
                                public void onFinalImageSet(String id,
                                        @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {

                                    // if (imageInfo == null) {
                                    //     return;
                                    // }
                                    // ViewGroup.LayoutParams vp = draweeView.getLayoutParams();
                                    // //计算控件高宽比
                                    // vp.width = imageInfo.getWidth();
                                    // vp.height = imageInfo.getHeight();
                                    //
                                    // draweeView.requestLayout();
                                }
                            });
        }
    }
}
