package com.bing.lan.fm.ui.pic;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.gank.bean.GankBean;

import java.util.List;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

public class PictureActivity extends BaseActivity<IPictureContract.IPicturePresenter>
        implements IPictureContract.IPictureView {

    public static final String PIC_INDEX = "pic_index";
    public static final String PIC_LIST = "pic_list";
    @BindView(R.id.tv_pic_index)
    TextView mTvIndex;
    @BindView(R.id.tv_pic_total)
    TextView mTvTotal;
    @BindView(R.id.viewPager_show_pic)
    ViewPager mViewPagerShowPic;
    private List<GankBean.ResultsBean> mImgList;
    private int mIndex;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_show_pic;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

        initView();
        initData();
        initListener();
    }

    @Override
    protected void readyStartPresenter() {

    }

    private void initListener() {
        mViewPagerShowPic.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvIndex.setText(String.valueOf(position + 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {

        Intent intent = getIntent();
        mImgList = (List<GankBean.ResultsBean>) intent.getSerializableExtra(PIC_LIST);
        mIndex = intent.getIntExtra(PIC_INDEX, 0);

        BigPicAdapter bigPicAdapter = new BigPicAdapter(mImgList);
        mViewPagerShowPic.setAdapter(bigPicAdapter);
        //初始化文本内容
        mTvIndex.setText(String.valueOf(mIndex + 1));
        mTvTotal.setText("/" + mImgList.size());

        mViewPagerShowPic.setCurrentItem(mIndex);
    }

    private void initView() {
    }

    class BigPicAdapter extends PagerAdapter {

        private List<GankBean.ResultsBean> mImgList;

        public BigPicAdapter(List<GankBean.ResultsBean> imgList) {
            this.mImgList = imgList;
        }

        @Override
        public int getCount() {
            return mImgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            //加载图片
            loadImage(mImgList.get(position).getUrl(), photoView);

            container.addView(photoView);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
