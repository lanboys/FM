package com.bing.lan.fm.ui.recommend.adapter;

import android.view.View;

import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.recommend.bean.AlbumBean;
import com.bing.lan.fm.ui.recommend.bean.DataBean;
import com.bing.lan.fm.ui.recommend.bean.TrackBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * @author jk
 * @time 2017/3/6  20:42
 * @desc ${TODD}
 */
public class ItemAdapter implements ItemViewDelegate<DataBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_recomd;
    }

    protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);

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
        holder.setText(R.id.tv_track_subtitle, track.getTitle());
        holder.setText(R.id.tv_footNote, album.getTitle());
        holder.setText(R.id.cv,track.getTags());
       // holder.setText(R.id.cv,album.getTags());

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
