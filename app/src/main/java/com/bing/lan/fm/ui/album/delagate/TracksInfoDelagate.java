package com.bing.lan.fm.ui.album.delagate;

import android.view.View;

import com.bing.lan.comm.utils.load.ImageLoader;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.album.bean.TracksInfoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * @author 蓝兵
 * @time 2017/3/2  16:57
 */
public class TracksInfoDelagate implements ItemViewDelegate<TracksInfoBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.album_item;
    }

    @Override
    public boolean isForViewType(TracksInfoBean item, int position) {
        return true;
    }

    @Override
    public void convert(ViewHolder holder, TracksInfoBean tracksInfoBean, int position) {

        SimpleDraweeView draweeView = holder.getView(R.id.iv_album_episode_image);
        ImageLoader.getInstance().loadImage(draweeView, tracksInfoBean.getCoverMiddle());

        holder.setText(R.id.iv_album_episode_Title, tracksInfoBean.getTitle());

        View view = holder.getView(R.id.ll_child_container);


        view.setTag(tracksInfoBean );



    }
}
