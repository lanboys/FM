package com.bing.lan.inke.yingke.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.PlayActivity;
import com.bing.lan.inke.yingke.adapter.HotAdapter;
import com.bing.lan.inke.yingke.bean.IndexImage;
import com.bing.lan.inke.yingke.bean.LiveDates;
import com.bing.lan.inke.yingke.bean.LivesBean;
import com.bing.lan.inke.yingke.http.IndexAllClient;
import com.bing.lan.inke.yingke.http.IndexClient;
import com.bing.lan.inke.yingke.http.ServiceGenerator;
import com.bing.lan.inke.yingke.interfaces.ProxyIndexActivity;
import com.jcodecraeer.xrecyclerview.YRecyclerView;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 热门
 * Created by kay on 16/11/15.
 */
public class HotFragment extends Fragment {

    private static final String TAG = "HotFragment";
    View loading;
    HotAdapter adapter;
    LiveDates dates;
    IndexImage image;
    YRecyclerView list;
    static final int BANNEER = 0;
    static final int ITEM = 1;
    static final int ERROR = 2;
    InnerHandler handler ;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new InnerHandler(this);
        getBannerDate();
        getIndexDate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hots, container, false);
        list = (YRecyclerView) view.findViewById(R.id.list);
         linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(linearLayoutManager);
        loading = view.findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        if (adapter == null) {
            adapter = new HotAdapter(getContext());
        }
        list.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list.setLoadingListener(new YRecyclerView.LoadingListener(){

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {


                        list.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable(){
                        public void run() {
                            list.loadMoreComplete();
                        }
                    }, 1000);

            }
        });
    }

    /**
     * 显示Home Bottom
     */
    private void showHomeBottom() {
        ProxyIndexActivity mProxyIndexActivity= (ProxyIndexActivity)getActivity();
        mProxyIndexActivity.showBottom();
    }

    /**
     * 隐藏Home Bottom
     */
    private void hideHomeBottom() {
        ProxyIndexActivity mProxyIndexActivity= (ProxyIndexActivity)getActivity();
        mProxyIndexActivity.hideBottom();
    }

    private void onScrollDown() {

    }


    private void onScrollUp() {

    }
    //获取首页的banner数据
    private void getBannerDate() {
        // Create a very simple REST adapter which points the GitHub API endpoint.
        IndexClient client = ServiceGenerator.createService(IndexClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<IndexImage> call = client.contributors();


        call.enqueue(new Callback<IndexImage>() {

            @Override
            public void onResponse(Call<IndexImage> call, Response<IndexImage> response) {
                if (response.isSuccessful()) {
                    Message message  = handler.obtainMessage(BANNEER);
                    message.obj = response.body();
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<IndexImage> call, Throwable throwable) {

            }
        });
    }

    //获取首页的直播的数据
    //获取首页的直播的数据
    private void getIndexDate() {

        // Create a very simple REST adapter which points the GitHub API endpoint.
        IndexAllClient client = ServiceGenerator.createService(IndexAllClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<LiveDates> call = client.getAllDate();


        call.enqueue(new Callback<LiveDates>() {

            @Override
            public void onResponse(Call<LiveDates> call, Response<LiveDates> response) {
                if (response.isSuccessful()) {
                    Message message  = handler.obtainMessage(ITEM);
                    message.obj = response.body();
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<LiveDates> call, Throwable throwable) {

            }
        });
    }

    public void setDate(LiveDates dates) {
        this.dates = dates;
        if (adapter == null) {
            adapter = new HotAdapter(getContext());
            list.setAdapter(adapter);
        }
        adapter.setDates(dates.getLives());
        /**处理RecyclerView item点击事件*/
        adapter.setClickListener(new HotAdapter.OnHotClickListener() {
            @Override
            public void onClickHotItem(int index) {
                Object click_item = adapter.getDataByIndex(index);
                if(index > 0){
                    Log.d(TAG,"click_item="+click_item.toString());
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), PlayActivity.class);
                    intent.putExtra(PlayActivity.DATA,(LivesBean)click_item);
                    startActivity(intent);
                }
            }
        });
    }

    public void setImage(IndexImage image) {
        this.image = image;
        if (adapter == null) {
            adapter = new HotAdapter(getContext());
            list.setAdapter(adapter);
        }
        adapter.setBANNER(image.getTicker());

    }


    static class InnerHandler extends Handler {
        WeakReference<HotFragment> hot;

        public InnerHandler(HotFragment hotFragment) {
            this.hot = new WeakReference<HotFragment>(hotFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            HotFragment hotFragment = hot.get();
            if (hotFragment == null) {
                return;
            }
            switch (msg.what) {
                case BANNEER:

                    IndexImage images = (IndexImage) msg.obj;
                    hotFragment.setImage(images);

                    break;
                case ITEM:

                    LiveDates dates = (LiveDates) msg.obj;
                    hotFragment.setDate(dates);

                    break;
            }
        }
    }
}
