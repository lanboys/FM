package com.bing.lan.fm.ui.hot.delagate;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.album.AlbumActivity;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.ListItemGuessBean;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.bing.lan.fm.ui.hot.delagate.EditorRecomItemDelagate.ALBUM_DETAIL;

/**
 * @author 蓝兵
 * @time 2017/3/5  16:50
 */
public class GuessItemDelagate implements
        ItemViewDelegate<HotInfoBean>, MultiItemTypeAdapter.OnItemClickListener {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.hot_item;
    }

    @Override
    public boolean isForViewType(HotInfoBean item, int position) {
        return item.getList().get(0) instanceof ListItemGuessBean;
    }

    @Override
    public void convert(ViewHolder holder, HotInfoBean hotInfoBean, int position) {
        holder.setText(R.id.tv_hot_item_title, hotInfoBean.getTitle());

        List<ListItemGuessBean> list = hotInfoBean.getList();
        initChildRecyclerView(holder, list);
    }

    private void initChildRecyclerView(ViewHolder holder, List<ListItemGuessBean> list) {
        // LinearLayoutManager layoutManager = new LinearLayoutManager(AppUtil.getAppContext()) {
        //
        //     @Override
        //     public boolean canScrollVertically() {
        //         return false;
        //     }
        // };

        GridLayoutManager layoutManager = new GridLayoutManager(AppUtil.getAppContext(), 3) {

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };





        RecyclerView itemRecyclerView = holder.getView(R.id.rv_hot_item_child);
        itemRecyclerView.setLayoutManager(layoutManager);

        ChildRecyclerViewAdapter adapter = new ChildRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.hot_item_child_guess, list);

        itemRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        ListItemGuessBean tag = (ListItemGuessBean) view.getTag();

        Intent intent = new Intent(view.getContext(), AlbumActivity.class);
        intent.putExtra(ALBUM_DETAIL, tag);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
    private static class ChildRecyclerViewAdapter extends CommonAdapter<ListItemGuessBean> {

        protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

        ChildRecyclerViewAdapter(Context context, int layoutId, List<ListItemGuessBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, ListItemGuessBean listItemEditorBean, int position) {

            View view = holder.getView(R.id.ll_child_container);

            view.setTag(listItemEditorBean);

            holder.setText(R.id.tv_track_Title, listItemEditorBean.getTitle());
            // holder.setText(R.id.tv_track_subtitle, listItemEditorBean.getSubtitle());
            // holder.setText(R.id.tv_footNote, listItemEditorBean.getFootnote());



            final SimpleDraweeView draweeView = holder.getView(R.id.iv_cover_image);
            // ImagePicassoUtil.loadImage(imageView, listItemEditorBean.getCoverMiddle());
            com.bing.lan.comm.utils.load.ImageLoader
                    .getInstance()
                    .loadImage(draweeView,
                            listItemEditorBean.getCoverMiddle(),
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
