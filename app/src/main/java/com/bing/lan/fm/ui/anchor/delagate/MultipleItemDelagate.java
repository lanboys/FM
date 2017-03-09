package com.bing.lan.fm.ui.anchor.delagate;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.load.ImageLoader;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.anchor.bean.ListBeanX;
import com.bing.lan.fm.ui.anchor.bean.NormalBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * @author lihoujing2ken
 * @time 2017/3/7  12:45
 * @desc ${TODD}
 */
public class MultipleItemDelagate implements ItemViewDelegate<NormalBean>, MultiItemTypeAdapter.OnItemClickListener {


    @Override
    public int getItemViewLayoutId() {
        return R.layout.anchor_item_star;
    }

    @Override
    public boolean isForViewType(NormalBean item, int position) {
        return true;
    }

    @Override
    public void convert(ViewHolder holder, NormalBean normalBean, int position) {

        //设置头
        holder.setText(R.id.tv_anchor_item_title,normalBean.getTitle());
        //设置Item
        List<ListBeanX> list=normalBean.getList();

               initChildRecyclerView(holder, list);
    }

    private void initChildRecyclerView(ViewHolder holder, List<ListBeanX> list) {
        //网格布局,每行显示3个
        GridLayoutManager layoutManager = new GridLayoutManager(AppUtil.getAppContext(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        //当前的ID,绑定rv_anchor_item_child
        RecyclerView itemRecyclerView = holder.getView(R.id.rv_anchor_item_child);
        itemRecyclerView.setLayoutManager(layoutManager);
        //固定个数
        itemRecyclerView.setHasFixedSize(true);

        //关联数据
        MultipleItemDelagate.ChildRecyclerViewAdapter adapter = new MultipleItemDelagate.ChildRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.anchor_item_child_guess, list);
        itemRecyclerView.setAdapter(adapter);
        //点击事件
        adapter.setOnItemClickListener(this);
    }



    /**
     * 设置数据
     */
    private class ChildRecyclerViewAdapter extends CommonAdapter<ListBeanX>{
        public ChildRecyclerViewAdapter(Context context, int layoutId, List<ListBeanX> datas) {
            super(context, layoutId, datas);
        }

       @Override
        public int getItemCount() {
            return mDatas.size()-1;
        }

        /**
         * 绑定数据
         * @param holder 控件
         * @param listBeanX 数据
         * @param position 当前的Item
         */
        @Override
        protected void convert(ViewHolder holder, ListBeanX listBeanX, int position) {
                holder.setText(R.id.tv_track_Title,listBeanX.getNickname());
                holder.setText(R.id.tv_verifyTitle_Title,listBeanX.getVerifyTitle());
                SimpleDraweeView image = holder.getView(R.id.iv_cover_image);
                ImageLoader.getInstance().loadImage(image,listBeanX.getSmallLogo());

        }
    }

    /**
     * 点击事件
     */
    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        //TODO 实现界面的跳转
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
