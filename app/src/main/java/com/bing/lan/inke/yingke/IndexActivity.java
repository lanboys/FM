package com.bing.lan.inke.yingke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bing.lan.fm.R;
import com.bing.lan.inke.ui.live.LiveActivity;
import com.bing.lan.inke.yingke.fragment.LiveFragment;
import com.bing.lan.inke.yingke.fragment.MineFragment;
import com.bing.lan.inke.yingke.http.GiftAllClient;
import com.bing.lan.inke.yingke.http.ServiceGenerator;
import com.bing.lan.inke.yingke.interfaces.ProxyIndexActivity;
import com.bing.lan.inke.yingke.util.Contance;
import com.bing.lan.inke.yingke.util.SharePreferenceUtil;
import com.bing.lan.inke.yingke.widght.MyToast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 映客的首页
 */
public class IndexActivity extends FragmentActivity implements ProxyIndexActivity {

    final String MINE = "mine";
    final String LIVE = "live";
    @BindView(R.id.top)
    FrameLayout top;
    @BindView(R.id.mine)
    LinearLayout mine;
    @BindView(R.id.live)
    LinearLayout live;
    /**
     * 进入百度云LSS直播（创建直播间）
     */
    @BindView(R.id.show)
    ImageView show;
    private String show_Type = LIVE;
    private Toast toast;
    private MyToast myToast;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);

        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_Type.equals(MINE)) {
                    return;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                MineFragment mineFragment = (MineFragment) fragmentManager.findFragmentByTag(MINE);
                LiveFragment liveFragment = (LiveFragment) fragmentManager.findFragmentByTag(LIVE);
                if (null != liveFragment) {
                    hideLive(fragmentManager, liveFragment);
                }
                if (null == mineFragment) {
                    mineFragment = new MineFragment();
                    addMine(fragmentManager, mineFragment);
                } else {
                    showMine(fragmentManager, mineFragment);
                }
                show_Type = MINE;
            }
        });

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_Type.equals(LIVE)) {
                    return;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                MineFragment mineFragment = (MineFragment) fragmentManager.findFragmentByTag(MINE);
                LiveFragment liveFragment = (LiveFragment) fragmentManager.findFragmentByTag(LIVE);
                if (null == liveFragment) {
                    addLive(fragmentManager, liveFragment);
                } else {
                    showLive(fragmentManager, liveFragment);
                }

                if (null != mineFragment) {
                    hideMine(fragmentManager, mineFragment);
                }

                show_Type = LIVE;
            }
        });

        /***
         * 点击创建直播间
         */
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, LiveActivity.class);
                // Intent intent = new Intent(IndexActivity.this, StreamingActivity.class);
                startActivity(intent);

                // Toast.makeText(AppUtil.getAppContext(), "直播间功能还在开发中哦", Toast.LENGTH_SHORT).show();
                
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        addLive(fm, new LiveFragment());

        //获取商城的数据
        getStoreDate();
    }

    private void getStoreDate() {
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

                    SharePreferenceUtil.putString(getApplicationContext(), Contance.STORE_KEY, json);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void addLive(FragmentManager fragmentManager, LiveFragment liveFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.top, liveFragment, LIVE);
        transaction.commit();
    }

    private void addMine(FragmentManager fragmentManager, MineFragment mineFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.top, mineFragment, MINE);
        transaction.commit();
    }

    private void showLive(FragmentManager fragmentManager, LiveFragment liveFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(liveFragment);
        transaction.commit();
    }

    private void hideLive(FragmentManager fragmentManager, LiveFragment liveFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(liveFragment);
        transaction.commit();
    }

    private void showMine(FragmentManager fragmentManager, MineFragment mineFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(mineFragment);
        transaction.commit();
    }

    private void hideMine(FragmentManager fragmentManager, MineFragment mineFragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(mineFragment);
        transaction.commit();
    }

    // @Override
    // public void onBackPressed() {
    //
    //     if (myToast == null)
    //         myToast = new MyToast(this);
    //     if (!myToast.isShow()) {
    //         myToast.showCustomToast();
    //     }
    //     /**两次按下间隔操过两秒*/
    //     if (System.currentTimeMillis() - exitTime > 2000) {
    //         myToast.cancleCustomToast();
    //         myToast.showCustomToast();
    //         exitTime = System.currentTimeMillis();
    //     } else {
    //         myToast.cancleCustomToast();
    //         finish();
    //         System.exit(0);
    //         android.os.Process.killProcess(android.os.Process.myPid());
    //     }
    // }

    @Override
    public void showBottom() {

    }

    @Override
    public void hideBottom() {

    }
}
