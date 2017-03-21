package com.bing.lan.inke.yingke.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.PlayActivity;
import com.bing.lan.inke.yingke.adapter.StoreAdapter;
import com.bing.lan.inke.yingke.bean.Gift;
import com.bing.lan.inke.yingke.bean.Gifts;
import com.bing.lan.inke.yingke.http.GiftAllClient;
import com.bing.lan.inke.yingke.http.ServiceGenerator;
import com.bing.lan.inke.yingke.interfaces.ProxyPlayActivity;
import com.bing.lan.inke.yingke.util.Contance;
import com.bing.lan.inke.yingke.util.JsonUtil;
import com.bing.lan.inke.yingke.util.SharePreferenceUtil;
import com.bing.lan.inke.yingke.widght.UnScrollGridView;
import com.bing.lan.inke.yingke.widght.WrapViewPager;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kay on 17/1/7.
 */

public class StoreFragment extends Fragment implements  View.OnClickListener{

    private static final String TAG = "StoreFragment";
    ArrayList<StoreAdapter> adapters;
    ArrayList<View> views;
    WrapViewPager viewPager;
    LinearLayout buttom;
    LayoutInflater inflater;
    LinearLayout dots;
    int pageSize = 0;

    static final int INT_SUCCESS = 10;

    MyHandler handler;
    ArrayList<ImageView> dots_list;

    boolean isSuccess = false;
    RelativeLayout content;
    LinearLayout golds;
    View back;
    private RelativeLayout re_send_store;
    /**所有的礼品*/
    private Gifts gifts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = new ArrayList<>();
        adapters = new ArrayList<>();
        handler = new MyHandler(this);
        dots_list = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        viewPager = (WrapViewPager) view.findViewById(R.id.viewPager);
        buttom = (LinearLayout) view.findViewById(R.id.buttom);
        dots = (LinearLayout) view.findViewById(R.id.dots);
        content = (RelativeLayout) view.findViewById(R.id.content);
        golds=(LinearLayout)view.findViewById(R.id.golds);
        re_send_store=(RelativeLayout)view.findViewById(R.id.re_send_store);
        back=(View) view.findViewById(R.id.back);
        golds.setOnClickListener(this);
        re_send_store.setOnClickListener(this);
        back.setOnClickListener(this);
        this.inflater = inflater;
        return view;
    }

    public boolean isShowStore(){
      boolean isShow =  content.getVisibility()==View.VISIBLE?true:false;
      return isShow;
    }


    /**
     * 显示该fragment
     */
    public void showContent(){
        content.setVisibility(View.VISIBLE);
        Animation in = AnimationUtils.loadAnimation(getContext(),R.anim.store_in);
        buttom.startAnimation(in);
    }

    /**
     * 隐藏该fragment
     */
    public void hideContent(){
        Animation out = AnimationUtils.loadAnimation(getContext(),R.anim.store_out);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                content.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        buttom.startAnimation(out);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDate();
    }


    public void loadDate() {
        String json = SharePreferenceUtil.getString(getActivity().getApplicationContext(), Contance.STORE_KEY);
        if (TextUtils.isEmpty(json)) {
            //先去获取数据

            GiftAllClient client = ServiceGenerator.createService(GiftAllClient.class);

            // Fetch and print a list of the contributors to this library.
            Call<ResponseBody> call = client.getAllDate();

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        String json = null;
                        try {
                            json = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (TextUtils.isEmpty(json)) {
                            return;
                        }

                        SharePreferenceUtil.putString(getContext().getApplicationContext(), Contance.STORE_KEY, json);

                        gifts = JsonUtil.parse(json, Gifts.class);
                        initDate(gifts);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                }
            });

        } else {
            gifts = JsonUtil.parse(json, Gifts.class);

            initDate(gifts);
        }
    }

    //初始化各个数据
    private void initDate(Gifts gifts) {
        if (gifts != null) {
            List<Gift> list = gifts.getGifts();
            int size = list.size();
            pageSize = size / 8 + size % 8;

            for (int i = 0; i < pageSize; i++) {
                ArrayList<Gift> one_page = new ArrayList<>();

                for (int j = 0; j < 8; j++) {
                    int will_get_size = 8 * i + j;
                    if (will_get_size < size) {

                        one_page.add(list.get(will_get_size));
                    } else {
                        break;
                    }
                }

                View view = inflater.inflate(R.layout.item_store_outer, viewPager, false);
                UnScrollGridView grid = (UnScrollGridView) view.findViewById(R.id.gridView);
                final StoreAdapter adapter = new StoreAdapter(one_page, getActivity());
                grid.setAdapter(adapter);
                adapters.add(adapter);

                grid.setTag(i);
                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int index = (int) parent.getTag();
                        StoreAdapter tmp = adapters.get(index);
                        tmp.setIsSelect(position);
                    }
                });
                views.add(view);

                addADot(dots);
            }
            setDot(0);
            handler.sendEmptyMessage(INT_SUCCESS);
        }
    }


    public void initSuccess() {
        MyViewPageAdapter adapter = new MyViewPageAdapter(views, getActivity());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addADot(LinearLayout line) {
        ImageView dot = new ImageView(getActivity());
        dot.setBackgroundResource(R.drawable.dot_selector);

        dots_list.add(dot);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(15, 0, 0, 0);
        line.addView(dot, p);

    }

    private void setDot(int index) {

        for (int i = 0; i < dots_list.size(); i++) {
            ImageView image = dots_list.get(i);
            if (i == index) {
                image.setSelected(true);
            } else {
                image.setSelected(false);
            }

        }

        isSuccess = true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /**隐藏fragment*/
            case R.id.golds:
                if(isShowStore()){
                    hideContent();
                }
                break;
            /**点击送花*/
            case R.id.re_send_store:
                List<Gift> selectGift=new ArrayList<>();
                if(gifts!=null){
                    List<Gift> gifts = this.gifts.getGifts();
                    for (Gift gift:gifts){
                        if(gift.isSelect()){
                            selectGift.add(gift);
                        }
                    }
                    if(selectGift.size()>0){
                        ProxyPlayActivity mProxyActivity=(ProxyPlayActivity)getActivity();
                        InPutFragment mInPutFragment = (InPutFragment) mProxyActivity.getFragment(PlayActivity.INPUT_FRAGMENT);
                        mInPutFragment.sendGiftsToWebSocket(selectGift);
                        if(isShowStore()){
                            hideContent();
                            /**回复没选中状态*/
                            restoreSelectState();
                        }
                    }else{
                        Toast.makeText(getActivity(),"请选择礼品",Toast.LENGTH_SHORT).show();
                    }


                }
                break;
            case R.id.back:
                if(isShowStore()){
                    hideContent();
                }
                break;
        }
    }

    /**
     * 恢复选中的状态
     */
    private void restoreSelectState() {
        List<Gift> gifts = this.gifts.getGifts();
        for (Gift gift:gifts){
            if(gift.isSelect()){
                gift.setSelect(false);
            }
        }
        for ( StoreAdapter adapter: adapters ){
            adapter.notifyDataSetChanged();
        }
    }


    static class MyViewPageAdapter extends PagerAdapter {

        ArrayList<View> views;


        public MyViewPageAdapter(ArrayList<View> views, Context context) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    static class MyHandler extends Handler {
        WeakReference<StoreFragment> reference;

        public MyHandler(StoreFragment storeFragment) {
            this.reference = new WeakReference<StoreFragment>(storeFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            StoreFragment fragment = reference.get();
            if (fragment == null) {
                return;
            }
            switch (msg.what) {
                case INT_SUCCESS:
                    fragment.initSuccess();
                    break;
            }
        }
    }
}
