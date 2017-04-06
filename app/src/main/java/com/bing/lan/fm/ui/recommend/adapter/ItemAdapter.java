package com.bing.lan.fm.ui.recommend.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.recommend.bean.AlbumBean;
import com.bing.lan.fm.ui.recommend.bean.DataBean;
import com.bing.lan.fm.ui.recommend.bean.TrackBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;


public class ItemAdapter implements ItemViewDelegate<DataBean> {

    protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_recomd;
    }

    @Override
    public boolean isForViewType(DataBean item, int position) {
        return true;
    }

    public void convert(ViewHolder holder, DataBean dataBean, int position) {

        //加载图片
        TrackBean track = dataBean.getTrack();
        AlbumBean album = dataBean.getAlbum();
        SimpleDraweeView view = holder.getView(R.id.iv_cover_image);
        //        ImageLoader.getInstance().loadImage(view,track.getCover_small_path());
        view.setImageURI(track.getCover_small_path());
        view.setBackgroundResource(R.drawable.recom_image_selector);

        holder.setText(R.id.tv_track_subtitle, track.getTitle());
        holder.setText(R.id.tv_footNote, album.getTitle());

        // View cradview = holder.getView(R.id.cv);

        //设置每个item中的tags
        // holder.setText(R.id.cv, track.getTags());

        // log.d("convert(): tag" + track.getTags());

        String tags = track.getTags();

        String[] split = tags.split(",");
        LinearLayout tag_container = holder.getView(R.id.tag_container);
        tag_container.removeAllViews();
        for (int i = 0; i < split.length; i++) {

            if (i > 3) {
                break;
            }

            View inflate = View.inflate(tag_container.getContext(), R.layout.item_recomd_child, null);
            TextView textView = (TextView) inflate.findViewById(R.id.cv);
            textView.setText(split[i]);
            tag_container.addView(inflate);
        }

        View view1 = holder.getView(R.id.ll_child_container);
        view1.setTag(album);

        //        //设置item
        //        List<ListItemGuessBean> list = hotInfoBean.getList();
        //        initChildRecyclerView(holder, list);
    }

    //    @Override
    //    public void convert(ViewHolder holder, RecBean recBean, int position) {
    //        List<DataBean> data = recBean.getData();
    //
    //
    //    }
}
