package com.bing.lan.inke.yingke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.Viewer;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : 直播间 InPutFragment 顶部的RecyclerView
 * Author : liujun
 * Email  : liujin2son@163.com
 * Date   : 2017/3/3 0003
 */

public class InputCreatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Viewer>  dates;
    private OnInPutCreatorClickListener listener;


    public void setListener(OnInPutCreatorClickListener listener) {
        this.listener = listener;
    }

    public void setDates(List<Viewer> dates) {
        if(dates!=null) {
            this.dates.addAll(dates);
            notifyDataSetChanged();
        }
    }

    public Object getDataByIndex(int index){
        if(dates!=null) {
            Viewer bean = dates.get(index);
            return bean;
        }else{
            return null;
        }
    }


    public InputCreatorAdapter(Context context) {
        dates = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        Log.d("TAG","onCreateViewHolder===========");
        return new InPutCreatorViewHolder(inflater.inflate(R.layout.item_creator,null,false),listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if(dates!=null){
//            Log.d("TAG","onBindViewHolder===========");
            Viewer viewer = dates.get(i);
            InPutCreatorViewHolder inputViewHolder=(InPutCreatorViewHolder)viewHolder;
            Log.d("TAG","url============"+viewer.getPortrait());
            String[] imageNameAndServer = Constance.getImageNameAndServer(viewer.getPortrait());
            if(imageNameAndServer!=null&&imageNameAndServer.length==2){
                String imgUrl = Constance.getHotSmallUrl(imageNameAndServer[0], imageNameAndServer[1]);
                inputViewHolder.imgCreator.setImageURI(imgUrl);
            }else{
                /**设计默认图片*/
//                inputViewHolder.imgCreator.setImageURI(imgUrl);
            }
        }
    }

    @Override
    public int getItemCount() {
//        Log.d("TAG","getItemCount==========="+dates.size());
        return dates==null?0:dates.size();
    }

    public  static class InPutCreatorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnInPutCreatorClickListener listener;
        SimpleDraweeView imgCreator;
        public InPutCreatorViewHolder(View itemView,OnInPutCreatorClickListener listener) {
            super(itemView);
            this.listener=listener;
            imgCreator= (SimpleDraweeView)itemView.findViewById(R.id.img_creator);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClickInPutCreatorItem(getAdapterPosition());
            }
        }
    }

    public  interface   OnInPutCreatorClickListener{
        public void onClickInPutCreatorItem(int index);
    }
}
