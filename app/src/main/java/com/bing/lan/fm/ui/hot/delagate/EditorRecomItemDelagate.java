package com.bing.lan.fm.ui.hot.delagate;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.ImageLoaderManager;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.ListItemEditorBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/17  17:44
 */
public class EditorRecomItemDelagate
        implements com.zhy.adapter.recyclerview.base.ItemViewDelegate<HotInfoBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.hot_item_editor;
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
        List<ListItemEditorBean> list = hotInfoBean.getList();
        initChildRecyclerView(holder, list);
    }

    private void initChildRecyclerView(ViewHolder holder, final List<ListItemEditorBean> list) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(AppUtil.getAppContext(), 3) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        //可实现带头部的gridview
        // gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        //     @Override
        //     public int getSpanSize(int position) {
        //         return (3 - position % 3);
        //     }
        // });

        RecyclerView itemRecyclerView = holder.getView(R.id.rv_hot_item_child);
        itemRecyclerView.setLayoutManager(gridLayoutManager);
        itemRecyclerView.setAdapter(new ChildRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.hot_item_child_editor, list));
    }

    private static class ChildRecyclerViewAdapter extends CommonAdapter<ListItemEditorBean> {

        ChildRecyclerViewAdapter(Context context, int layoutId, List<ListItemEditorBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, ListItemEditorBean listItemEditorBean, int position) {

            RecyclerView.LayoutParams layoutParams =
                    new RecyclerView.LayoutParams(AppUtil.getScreenW() / 3,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

            holder.getView(R.id.ll_child_container).setLayoutParams(layoutParams);

            holder.setText(R.id.tv_track_Title, listItemEditorBean.getTrackTitle());
            holder.setText(R.id.tv_title, listItemEditorBean.getTitle());

            ImageView imageView = holder.getView(R.id.iv_cover_image);
            ImageLoaderManager.loadImage(imageView, listItemEditorBean.getCoverMiddle());
        }
    }
}
