package com.bing.lan.fm.ui.gank;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.pic.PictureActivity;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 *
 */
public class GankFragment extends BaseFragment<IGankContract.IGankPresenter>
        implements IGankContract.IGankView,
        MultiItemTypeAdapter.OnItemClickListener {

    private static final int GRIL_COLUMN = 3;
    @BindView(R.id.recyclerView_gank)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_container)
    BGARefreshLayout mRefreshLayout;
    private List<GankBean.ResultsBean> mRecyclerViewData;
    private GankRecyclerViewAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @Override
    protected void initViewAndData(Intent intent) {
        initRefreshLayout(mRefreshLayout);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setVisibility(View.GONE);
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(GRIL_COLUMN, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerViewData = new ArrayList<>();
        mAdapter = new GankRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.item_gank_meizi, mRecyclerViewData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void updateGank(List<GankBean.ResultsBean> data) {
        mRecyclerViewData.clear();
        updateRecylerViewData(data);
    }

    @Override
    public void loadMoreGank(List<GankBean.ResultsBean> data) {
        updateRecylerViewData(data);
    }

    private void updateRecylerViewData(List<GankBean.ResultsBean> data) {
        mRecyclerViewData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.updateGankData();
        log.d("onBGARefreshLayoutBeginRefreshing(): gank界面下拉刷新");
    }

    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.loadMoreGankData();
        log.d("onBGARefreshLayoutBeginLoadingMore(): gank界面加载更多");
        return true;
    }

    public void closeRefeshing() {
        if (mRefreshLayout != null) {
            mRefreshLayout.endRefreshing();
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        Intent intent = new Intent(AppUtil.getAppContext(), PictureActivity.class);

        intent.putExtra(PictureActivity.PIC_INDEX, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PictureActivity.PIC_LIST, (Serializable) mRecyclerViewData);//注意序列化

        AppUtil.getAppContext().startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    public void loadImageByFresco(final ViewHolder holder, String imageUrl) {
        // SimpleDraweeView imageView2 = holder.getView(R.id.main_simple_drawee_view);
        //   int imageViewSize = AppUtil.getScreenW() / GRIL_COLUMN;
        //
        // int base = (int) (imageViewSize * (1 - 0.3));
        //
        // Random random = new Random();
        // int i = random.nextInt(base);
        //
        // //计算控件高宽比
        // final ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
        // layoutParams.height = imageViewSize - 50 + i;
        //
        // DraweeController controller = Fresco.newDraweeControllerBuilder()
        //         .setUri(imageUrl)
        //         .setAutoPlayAnimations(true)
        //         .build();
        // imageView2.setController(controller);
    }

    private void loadImageByLoad(ViewHolder holder, String imageUrl, int position) {

        // String imagePath = new File(AppUtil.getAppContext().getFilesDir(), position + "haha.jpeg").getAbsolutePath();
        //
        // final int imageViewSize = AppUtil.getScreenW() / GRIL_COLUMN;
        // final ImageView imageView = holder.getView(R.id.iv_girl);
        //
        // manager.loadImage(imageUrl, imagePath, new IAsyncImageLoadedCallBack() {
        //     @Override
        //     public void imageLoaded(Bitmap imageBitmap, String imgUrl) {
        //         int width = imageBitmap.getWidth();
        //         int height = imageBitmap.getHeight();
        //
        //         // imageView1.setRelative(width / height);
        //         // imageView.setImageBitmap(imageBitmap);
        //
        //         //计算高宽比
        //         int finalHeight = (imageViewSize) * height / width;
        //
        //         ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        //         layoutParams.height = finalHeight;
        //         imageView.setImageBitmap(imageBitmap);
        //     }
        // });
    }

    private void loadImageByPicasso(final ViewHolder holder, String imageUrl) {
        // final ImageView imageView = holder.getView(R.id.iv_girl);
        // ImagePicassoUtil.loadBigImage(imageView, imageUrl);
    }

    private void loadImageByGlide(final ViewHolder holder, final String imageUrl) {
        // final int imageViewWidth = AppUtil.getScreenW() / GRIL_COLUMN;
        // final ImageView imageView = holder.getView(R.id.iv_girl);
        // final ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        // //
        // Glide.with(getContext())
        //         .load(imageUrl)
        //         .asBitmap()
        //         // .Transformation()
        //         // .thumbnail(0.1f)
        //         // .fitCenter()
        //         // .centerCrop() Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL
        //         // .placeholder(R.drawable.image_default_202)
        //         .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        //         .into(new SimpleTarget<Bitmap>() {
        //                   @Override
        //                   public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
        //
        //                       int width = bitmap.getWidth();
        //                       int height = bitmap.getHeight();
        //                       log.d("onResourceReady(): imageUrl:  " + imageUrl);
        //                       log.d("onResourceReady(): width:  " + width + ", height: " + height + "   " + imageUrl);
        //
        //                       // imageView1.setRelative(width / height);
        //                       // imageView.setImageBitmap(bitmap);
        //
        //                       //计算控件高宽比
        //                       int finalHeight = (int) (imageViewWidth * height / width + 0.5f);
        //                       layoutParams.height = finalHeight;
        //
        //                       log.d("onResourceReady(): imageViewWidth: " + imageViewWidth
        //                               + " imageViewHeight: " + finalHeight);
        //
        //                       // //计算inSampleSize
        //                       // float scaleW = (width * 1.0f) / imageViewWidth;
        //                       // float scaleH = (height * 1.0f) / layoutParams.height;
        //                       // //取大的
        //                       // float inSampleSize = Math.max(scaleW, scaleH);
        //
        //                       // Bitmap scaleBitmap = BitmapUtils.createScaleBitmap(bitmap, Math.abs(imageViewWidth), Math.abs(finalHeight), (int) inSampleSize);
        //
        //                       // imageView.setImageBitmap(bitmap);
        //                       bitmap.recycle();
        //
        //                       Glide.with(getContext())
        //                               .load(imageUrl)
        //                               .asBitmap()
        //                               // .Transformation()
        //                               .thumbnail(0.1f)
        //                               .override(imageViewWidth, finalHeight)
        //                               // .placeholder(R.drawable.image_default_202)
        //                               .error(R.drawable.image_default_202)
        //                               .diskCacheStrategy(DiskCacheStrategy.RESULT)
        //                               .into(imageView);
        //                   }
        //               }
        //         );
    }

    class GankRecyclerViewAdapter extends CommonAdapter<GankBean.ResultsBean> {

        public GankRecyclerViewAdapter(Context context, int layoutId, List<GankBean.ResultsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(final ViewHolder holder, GankBean.ResultsBean resultsBean, int position) {
            String imageUrl = resultsBean.getUrl();

            // loadImageByFresco(holder, imageUrl);
            // loadImageByPicasso(holder,imageUrl);
            // loadImageByGlide(holder, imageUrl);
            // loadImageByLoad(holder, imageUrl, position);

            final SimpleDraweeView draweeView = holder.getView(R.id.main_simple_drawee_view);
            final int imageViewWidth = AppUtil.getScreenW() / GRIL_COLUMN;

            com.bing.lan.comm.utils.load.ImageLoader
                    .getInstance()
                    .loadImage(draweeView, imageUrl, new BaseControllerListener<ImageInfo>() {
                        @Override
                        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                            if (imageInfo == null) {
                                return;
                            }
                            ViewGroup.LayoutParams vp = draweeView.getLayoutParams();
                            //计算控件高宽比
                            vp.height = (int) (imageViewWidth * imageInfo.getHeight() / imageInfo.getWidth() + 0.5f);
                            draweeView.requestLayout();
                        }
                    });
        }
    }
}
