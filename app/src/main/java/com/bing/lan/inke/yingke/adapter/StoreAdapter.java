package com.bing.lan.inke.yingke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.Gift;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by kay on 17/1/2.
 */

public class StoreAdapter extends BaseAdapter {

    ArrayList<Gift> titles;
    LayoutInflater inflater;

    public StoreAdapter(ArrayList<Gift> titles, Context context) {
        this.titles = titles;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_store, null);
        ImageView tag = (ImageView) view.findViewById(R.id.tag);
        Gift tmp = titles.get(position);
        SimpleDraweeView gift = (SimpleDraweeView) view.findViewById(R.id.gift);
        String url = Constance.getNearIconUrl(tmp.getIcon());
        TextView priace = (TextView) view.findViewById(R.id.priace);
        TextView exp = (TextView) view.findViewById(R.id.exp);
        priace.setText(String.valueOf(tmp.getGold()));
        gift.setImageURI(url);
        exp.setText("+" + tmp.getExp() + "点经验值");
        boolean isSelect = tmp.isSelect();
        if (isSelect || tmp.getType() == 1) {
            tag.setVisibility(View.VISIBLE);
            if (isSelect) {
                tag.setImageResource(R.drawable.pop_gift_choose);
            } else {
                tag.setImageResource(R.drawable.pop_gift_lian);
            }
        } else {
            tag.setVisibility(View.INVISIBLE);
        }


        return view;
    }

    public void setIsSelect(int position) {
        boolean isSelect = titles.get(position).isSelect();

        titles.get(position).setSelect(!isSelect);
        notifyDataSetChanged();

    }


}
