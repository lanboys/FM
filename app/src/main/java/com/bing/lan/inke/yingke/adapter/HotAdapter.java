package com.bing.lan.inke.yingke.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.CreatorBean;
import com.bing.lan.inke.yingke.bean.LivesBean;
import com.bing.lan.inke.yingke.bean.TickerBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 热门
 */
public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HotAdapter";
    private final int BANNER = 0;
    private final int LIVE_ITEM = 1;
    ArrayList<LivesBean> dates;
    ArrayList<TickerBean> image;

    LayoutInflater inflater;

    OnHotClickListener clickListener;

    public HotAdapter(Context context) {
        dates = new ArrayList<>();
        image = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        if (type == BANNER) {
            BannerItemHodler hodler = new BannerItemHodler(inflater.inflate(R.layout.item_banner, null));
            return hodler;
        } else {
            HotItemHodler hodler = new HotItemHodler(inflater.inflate(R.layout.item_live, null), clickListener);
            return hodler;
        }
    }

    public Object getDataByIndex(int index) {
        if (index == 0) {
            return image;
        } else {
            /**
             * 一定要减去2个item：
             * 一个下拉刷新
             * 一个广告栏
             * */
            LivesBean bean = dates.get(index - 2);
            return bean;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HotItemHodler) {
            LivesBean bean = dates.get(i - 1);
            try {
                setHolder(bean, (HotItemHodler) viewHolder);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (viewHolder instanceof BannerItemHodler) {
            setBaner(image, (BannerItemHodler) viewHolder);
        }
    }

    private void setHolder(LivesBean bean, HotItemHodler hodler) throws UnsupportedEncodingException {
        CreatorBean create = bean.getCreator();
        //String image_name = URLEncoder.encode(create.getPortrait(),"utf-8");
        String image_name = create.getPortrait();
        //        Log.e(TAG,"image_name="+image_name);
        //        image_name=http://img2.inke.cn/MTQ4NjM0Nzk1OTAzNCM5ODUjanBn.jpg
        //        image_name=MTQ3NzczMDUyNTk1NiM1MSNqcGc=.jpg
        String[] name_server = Constance.getImageNameAndServer(image_name);

        String small_url = Constance.getHotSmallUrl(name_server[0], name_server[1]);

        Uri uri = Uri.parse(small_url);
        Uri uri_big = Uri.parse(Constance.getHotBigUrl(name_server[0], name_server[1]));

        hodler.icon.setImageURI(uri);
        hodler.src.setImageURI(uri_big);
        hodler.viewCount.setText(String.valueOf(bean.getOnline_users()));
        hodler.from.setText(String.valueOf(bean.getCity()));
        hodler.name.setText(create.getNick());
        if (!TextUtils.isEmpty(bean.getName())) {
            hodler.title.setVisibility(View.VISIBLE);
            hodler.title.setText(bean.getName());
        } else {
            hodler.title.setVisibility(View.GONE);
        }
    }

    private void setBaner(ArrayList<TickerBean> image, BannerItemHodler viewHolder) {
        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            View view = inflater.inflate(R.layout.item_image, null);
            views.add(view);
        }

        ImageAdapter page = new ImageAdapter(views, image);
        viewHolder.viewPager.setAdapter(page);
    }

    @Override
    public int getItemCount() {
        return dates.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? BANNER : LIVE_ITEM;
    }

    public void setClickListener(OnHotClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setDates(List<LivesBean> tmp) {
        dates.addAll(tmp);
        notifyDataSetChanged();
    }

    public void setBANNER(List<TickerBean> tmp) {
        image.addAll(tmp);
        notifyDataSetChanged();
    }

    public interface OnHotClickListener {

        public void onClickHotItem(int index);
    }

    public static class HotItemHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        SimpleDraweeView icon;
        SimpleDraweeView src;
        TextView viewCount;
        TextView from;
        TextView name;
        TextView title;
        OnHotClickListener listener;

        public HotItemHodler(View itemView, OnHotClickListener listener) {
            super(itemView);
            this.listener = listener;
            icon = (SimpleDraweeView) itemView.findViewById(R.id.icon);
            src = (SimpleDraweeView) itemView.findViewById(R.id.src);
            viewCount = (TextView) itemView.findViewById(R.id.viewCount);
            from = (TextView) itemView.findViewById(R.id.from);
            name = (TextView) itemView.findViewById(R.id.name);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClickHotItem(getAdapterPosition());
                Log.d("TAG", "index");
            }
        }
    }

    public static class BannerItemHodler extends RecyclerView.ViewHolder {

        ViewPager viewPager;

        public BannerItemHodler(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager);
        }
    }
}
