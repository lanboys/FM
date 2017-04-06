package com.bing.lan.inke.yingke.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.SearchActivity;
import com.bing.lan.inke.yingke.adapter.LiveAdapter;
import com.bing.lan.inke.yingke.bean.FragmentInfor;

import java.util.ArrayList;

/**
 * 首页（热门）
 */
public class LiveFragment extends Fragment implements ViewPager.OnPageChangeListener{
    ViewPager viewPager;
    FrameLayout tabs;
    LiveAdapter adapter;

    ArrayList<FragmentInfor> infors;
    ArrayList<View> titles;

    int lastP = 0;
    HorizontalScrollView scrollView;
    private ImageView leftSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        tabs = (FrameLayout) view.findViewById(R.id.tabs);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        infors = new ArrayList<>();
        titles = new ArrayList<>();

        String [] titles = getResources().getStringArray(R.array.title);
        Class [] classes =  {FocusFragment.class,HotFragment.class,NearFragment.class ,TalentFragment.class};
        for(int i =0;i<titles.length;i++){
            String title = titles[i];
            Fragment fragment = Fragment.instantiate(getContext(),classes[i].getName());
            FragmentInfor infor = new FragmentInfor(title,fragment);
            infors.add(infor);
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new LiveAdapter(getFragmentManager(),infors);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.include_title,tabs,false);
        scrollView = (HorizontalScrollView) view.findViewById(R.id.h_s);
        LinearLayout line = (LinearLayout) view.findViewById(R.id.line);
        leftSearch=(ImageView)view.findViewById(R.id.left);
        for(int i=0;i<infors.size();i++){
            FragmentInfor info = infors.get(i);
            View item_view =  inflater.inflate(R.layout.item_title,null);
            TextView title= (TextView) item_view.findViewById(R.id.item_title);
            title.setText(info.getName());
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            p.setMargins(40,0,40,0);
            line.addView(item_view,p);
            title.setTag(i);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = viewPager.getCurrentItem();
                    int position = (int) v.getTag();
                    if(current==position){
                        return;
                    }
                    scrollView.smoothScrollTo(position*188-(scrollView.getWidth()/2-94),0);
                    viewPager.setCurrentItem(position);
                }
            });
            titles.add(item_view);
        }
        tabs.addView(view);

        viewPager.setCurrentItem(1);


        /***
         * 点击跳到收索界面
         */
        leftSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.i("hked","position = "+position+" positionOffset = "+positionOffset+" positionOffsetPixels = "+positionOffsetPixels );
        if(positionOffset == 0){
            for(int i =0;i<titles.size();i++){
                View view = titles.get(i);
                if(i!=position){
                    view.setScaleX(1f);
                    view.setScaleY(1f);
                }else{
                    view.setScaleX(1.2f);
                    view.setScaleY(1.2f);
                }

            }
            return;
        }
        View view = null;
        View next = null;
        if(positionOffsetPixels>lastP){
            //往右
            //Log.i("hked","往右"+positionOffsetPixels+" last "+lastP);
            next = titles.get(position+1);
            next.setScaleX(1f+(0.2f*(positionOffset)));
            next.setScaleY(1f+(0.2f*(positionOffset)));

            view = titles.get(position);
            view.setScaleX(1.2f-(0.2f*(positionOffset)));
            view.setScaleY(1.2f-(0.2f*(positionOffset)));

        }else if(positionOffsetPixels<lastP){
            //往左
           // Log.i("hked","往左 "+positionOffsetPixels+" last "+lastP);

            next = titles.get(position);
            next.setScaleX(1f+(0.2f*(1f-positionOffset)));
            next.setScaleY(1f+(0.2f*(1f-positionOffset)));

            view = titles.get(position+1);
            view.setScaleX(1.2f-(0.2f*(1f-positionOffset)));
            view.setScaleY(1.2f-(0.2f*(1f-positionOffset)));

        }
        lastP = positionOffsetPixels;


    }

    @Override
    public void onPageSelected(int position) {

       if(position == 1){
           View view = titles.get(1);
           ImageView down = (ImageView) view.findViewById(R.id.down);
           down.setVisibility(View.VISIBLE);
       }else{
           View view = titles.get(1);
           ImageView down = (ImageView) view.findViewById(R.id.down);
           down.setVisibility(View.GONE);
       }

        scrollView.smoothScrollTo(position*188-(scrollView.getWidth()/2-94),0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
