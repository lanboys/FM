package com.bing.lan.inke.yingke.fragment;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.PlayActivity;
import com.bing.lan.inke.yingke.adapter.NearAdapter;
import com.bing.lan.inke.yingke.bean.LivesBean;
import com.bing.lan.inke.yingke.bean.NearBean;
import com.bing.lan.inke.yingke.bean.NearDates;
import com.bing.lan.inke.yingke.http.NearAllClient;
import com.bing.lan.inke.yingke.http.ServiceGenerator;
import com.jcodecraeer.xrecyclerview.YRecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NearFragment extends Fragment {
    YRecyclerView list;
    View loading;
    static final int INIT = 0;
    InnerHandler handler;
    NearAdapter adapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hots, container, false);
        list = (YRecyclerView) view.findViewById(R.id.list);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        list.setLayoutManager(staggeredGridLayoutManager);
        //list.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        loading = view.findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        if(adapter==null){
            adapter = new NearAdapter(getContext());
        }
        list.setAdapter(adapter);
        list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int poisition =  parent.getChildAdapterPosition(view);
                if(poisition==0){
                    outRect.set(0,0,0,0);
                }else{
                    if(poisition%3==0){
                        outRect.set(10,0,10,0);
                    }else{
                        outRect.set(10,0,0,0);
                    }

                }
            }
        });

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
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new InnerHandler(this);
        getDate();
    }

    private void getDate() {
        //获取附近的直播人
        // Create a very simple REST adapter which points the GitHub API endpoint.
        NearAllClient client = ServiceGenerator.createService(NearAllClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<NearDates> call = client.getAllDate();


        call.enqueue(new Callback<NearDates>() {

            @Override
            public void onResponse(Call<NearDates> call, Response<NearDates> response) {
                if (response.isSuccessful()) {
                    Message message = handler.obtainMessage(INIT);
                    message.obj = response.body();
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<NearDates> call, Throwable throwable) {

            }
        });
    }

    private void setDate(NearDates dates){

        List<NearBean> lives = dates.getLives();

        if(adapter==null){
            adapter = new NearAdapter(getContext());
            list.setAdapter(adapter);
        }
        if(lives!=null){
            adapter.setDate(lives);
        }

        adapter.setOnNearClickListener(new NearAdapter.OnNearClickListener() {
            @Override
            public void onClickNearItem(int index) {
                Toast.makeText(getActivity(), "index="+index, Toast.LENGTH_SHORT).show();
                NearBean nearBean = (NearBean) adapter.getDataByIndex(index);
                LivesBean mLivesBean=new LivesBean();
                mLivesBean.setCity(nearBean.getCity());
                mLivesBean.setCreator(nearBean.getCreator());
                mLivesBean.setGroup(nearBean.getGroup());
                mLivesBean.setId(nearBean.getId());
                mLivesBean.setLink(nearBean.getLink());
                mLivesBean.setMulti(nearBean.getMulti());
                mLivesBean.setName(nearBean.getName());
                mLivesBean.setOptimal(nearBean.getOptimal());
                mLivesBean.setRotate(nearBean.getRotate());
                mLivesBean.setShare_addr(nearBean.getShare_addr());
                mLivesBean.setVersion(nearBean.getVersion());
                mLivesBean.setStream_addr(nearBean.getStream_addr());
                mLivesBean.setSlot(nearBean.getSlot());
                Intent intent = new Intent();
                intent.setClass(getActivity(), PlayActivity.class);
                intent.putExtra(PlayActivity.DATA,mLivesBean);
                startActivity(intent);
            }
        });

    }

    static class InnerHandler extends Handler {
        WeakReference<NearFragment> hot;

        public InnerHandler(NearFragment hotFragment) {
            this.hot = new WeakReference<NearFragment>(hotFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            NearFragment nearFragment = hot.get();
            if (nearFragment == null) {
                return;
            }
            switch (msg.what) {

                case INIT:

                    NearDates dates = (NearDates) msg.obj;
                    nearFragment.setDate(dates);

                    break;
            }
        }
    }
}
