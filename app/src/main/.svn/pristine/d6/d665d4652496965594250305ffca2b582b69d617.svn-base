package com.bing.lan.fm.ui.album.delagate;

import android.view.View;
import android.widget.TextView;

import com.bing.lan.comm.utils.load.ImageLoader;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.album.bean.TracksInfoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Date;

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

        ImageLoader.getInstance().loadImage(draweeView, tracksInfoBean.getSmallLogo());

        View view = holder.getView(R.id.ll_child_container);
        view.setTag(tracksInfoBean);

        bindViewData(holder, tracksInfoBean);
    }

    private void bindViewData(ViewHolder holder, TracksInfoBean tracksInfoBean) {

        holder.setText(R.id.iv_album_episode_Title, tracksInfoBean.getTitle());

        long createdAt = tracksInfoBean.getCreatedAt();
        if (createdAt > 0) {
            TextView textView = holder.getView(R.id.tv_create_time);

            Date date = new Date(createdAt);
            String s = date.toLocaleString();

            // TODO: 2017/3/6 时间转换
            textView.setText(s);
            textView.setVisibility(View.VISIBLE);
        }
        int comments = tracksInfoBean.getComments();
        if (comments > 0) {
            TextView textView = holder.getView(R.id.tv_comments);
            textView.setText(comments + "");
            textView.setVisibility(View.VISIBLE);
        }
        int playtimes = tracksInfoBean.getPlaytimes();
        if (playtimes > 0) {
            TextView textView = holder.getView(R.id.tv_playtimes);
            textView.setText(playtimes + "");
            textView.setVisibility(View.VISIBLE);
        }
        int duration = tracksInfoBean.getDuration();
        if (duration > 0) {
            TextView textView = holder.getView(R.id.tv_duration);

            int min = duration / 60;
            int sec = duration % 60;

            textView.setText(min + ":" + sec);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
