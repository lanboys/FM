package com.bing.lan.inke.yingke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.bean.SocketDate;
import com.bing.lan.inke.yingke.util.Contance;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Author : liujun
 * Email  : liujin2son@163.com
 * Date   : 2017/3/6 0006
 */

public class InputCharAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final LayoutInflater inflater;
    List<SocketDate> dates =null;

    public InputCharAdapter(Context context) {
        this.dates = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }
    public void setDates(List<SocketDate> dates) {
        if(dates!=null) {
            Log.d("TAG","setDates==========="+dates.toString());
            this.dates.addAll(dates);
            notifyDataSetChanged();
        }
    }

    public void addDates(SocketDate date) {
        if(date!=null) {
            Log.d("TAG","addDates==========="+date.toString());
            this.dates.add(date);
            notifyDataSetChanged();
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new InputCharViewHolder(inflater.inflate(R.layout.item_chat,null),null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        InputCharViewHolder charHolder=(InputCharViewHolder)viewHolder;
        SocketDate socketDate = dates.get(i);
        if(socketDate!=null) {
            /**首次登录入群返回的消息*/
            if (socketDate.getType() == Contance.LOGIN_TYPE) {
                charHolder.tvChar.setText(socketDate.getUserName() + " : 上线");
            }else if(socketDate.getType()==Contance.SEND_TYPE){
                charHolder.tvChar.setText(socketDate.getUserName()+" : "+socketDate.getMsg());
            }else if(socketDate.getType()==Contance.SEND_GIFT_TYPE){
                Log.d("TAG",socketDate.toString());
                charHolder.tvChar.setText(socketDate.getUserName()+" : "+socketDate.getGifts().get(0).getName());
            }
        }
    }

    public Object getDataByIndex(int index){
        if(dates!=null) {
            SocketDate bean = dates.get(index);
            return bean;
        }else{
            return null;
        }
    }
    @Override
    public int getItemCount() {
        return dates.size();
    }



    static class InputCharViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        private final OnInputCharClickListener listener;
        private final TextView tvChar;

        public InputCharViewHolder(View itemView, OnInputCharClickListener listener) {
            super(itemView);
            this.listener=listener;
            tvChar=(TextView)itemView.findViewById(R.id.tv_char);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                listener.onClickInputChatItem(getAdapterPosition());
            }
        }
    }

    public  interface   OnInputCharClickListener{
        public void onClickInputChatItem(int index);
    }
}
