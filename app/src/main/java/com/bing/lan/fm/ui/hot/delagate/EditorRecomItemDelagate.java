package com.bing.lan.fm.ui.hot.delagate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.album.AlbumActivity;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.ListItemEditorBean;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import javax.annotation.Nullable;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * @author 蓝兵
 * @time 2017/2/17  17:44
 */
public class EditorRecomItemDelagate
        implements ItemViewDelegate<HotInfoBean>, MultiItemTypeAdapter.OnItemClickListener {

    public static final String ALBUM_DETAIL = "album_detail";
    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

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
        ChildRecyclerViewAdapter adapter = new ChildRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.hot_item_child_editor, list);
        itemRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        log.d("onItemClick(): item点击事件");

        // ListItemEditorBean tag = (ListItemEditorBean) view.getTag();
        // log.d("onItemClick(): " + tag);
        // AppUtil.startActivity(view.getContext(), MusicActivity.class, false, true);
        // Intent intent = new Intent(view.getContext(), MusicActivity.class);
        // intent.putExtra(ALBUM_DETAIL, tag);
        // intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        // view.getContext().startActivity(intent);

        ListItemEditorBean tag = (ListItemEditorBean) view.getTag();
        log.d("onItemClick(): " + tag);
        Intent intent = new Intent(view.getContext(), AlbumActivity.class);
        intent.putExtra(ALBUM_DETAIL, tag);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    private static class ChildRecyclerViewAdapter extends CommonAdapter<ListItemEditorBean> {

        protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

        ChildRecyclerViewAdapter(Context context, int layoutId, List<ListItemEditorBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, ListItemEditorBean listItemEditorBean, int position) {

            RecyclerView.LayoutParams layoutParams =
                    new RecyclerView.LayoutParams(AppUtil.getScreenW() / 3,
                            ViewGroup.LayoutParams.WRAP_CONTENT);

            View view = holder.getView(R.id.ll_child_container);
            view.setLayoutParams(layoutParams);

            view.setTag(listItemEditorBean);
            log.d("convert(): " + listItemEditorBean);

            holder.setText(R.id.tv_track_Title, listItemEditorBean.getTrackTitle());
            holder.setText(R.id.tv_title, listItemEditorBean.getTitle());

            final int imageViewWidth = AppUtil.getScreenW() / 3;

            final SimpleDraweeView draweeView = holder.getView(R.id.iv_cover_image);
            // ImagePicassoUtil.loadImage(imageView, listItemEditorBean.getCoverMiddle());
            com.bing.lan.comm.utils.load.ImageLoader
                    .getInstance()
                    .loadImage(draweeView,
                            listItemEditorBean.getCoverMiddle(), new BaseControllerListener<ImageInfo>() {
                                @Override
                                public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                                    if (imageInfo == null) {
                                        return;
                                    }
                                    ViewGroup.LayoutParams vp = draweeView.getLayoutParams();
                                    //计算控件高宽比
                                    vp.width = imageInfo.getWidth();
                                    vp.height = imageInfo.getHeight();

                                    draweeView.requestLayout();
                                }
                            });
        }
    }
}
