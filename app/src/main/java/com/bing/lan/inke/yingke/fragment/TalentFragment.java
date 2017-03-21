package com.bing.lan.inke.yingke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.fm.R;

/**
 * Created by kay on 16/11/15.
 */
public class TalentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot,container,false);
        TextView title= (TextView) view.findViewById(R.id.textView);
        title.setText("才艺");
        return view;
    }
}
