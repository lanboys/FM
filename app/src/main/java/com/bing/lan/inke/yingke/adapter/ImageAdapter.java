package com.bing.lan.inke.yingke.adapter;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.TickerBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


public class ImageAdapter extends PagerAdapter {
    ArrayList<View> images;
    ArrayList<TickerBean> urls;

    public ImageAdapter(ArrayList<View> image, ArrayList<TickerBean> urls) {
        this.images = image;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = images.get(position);
        TickerBean bean = urls.get(position);
        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.image);
        try {
            //String image_url = URLEncoder.encode(bean.getImage(), "utf-8");
            String image_url = bean.getImage();

            String[] name_server =  Constance.getImageNameAndServer(image_url);

            String banner_url = Constance.getBannerUrl(name_server[0],name_server[1]);

            Uri uri = Uri.parse(banner_url);
            draweeView.setImageURI(uri);

        } catch (Exception e) {
            e.printStackTrace();
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
