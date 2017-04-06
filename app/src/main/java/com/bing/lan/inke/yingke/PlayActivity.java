package com.bing.lan.inke.yingke;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bing.lan.fm.R;
import com.bing.lan.inke.widght.IjkVideoView;
import com.bing.lan.inke.yingke.bean.Inout;
import com.bing.lan.inke.yingke.bean.LivesBean;
import com.bing.lan.inke.yingke.bean.MMInfo;
import com.bing.lan.inke.yingke.bean.Viewer;
import com.bing.lan.inke.yingke.event.ShowCloseEvent;
import com.bing.lan.inke.yingke.fragment.InPutFragment;
import com.bing.lan.inke.yingke.fragment.StoreFragment;
import com.bing.lan.inke.yingke.http.IGetMMInfor;
import com.bing.lan.inke.yingke.http.ServiceGenerator;
import com.bing.lan.inke.yingke.interfaces.ProxyPlayActivity;
import com.bing.lan.inke.yingke.util.JsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.bing.lan.inke.yingke.util.JsonUtil.parse;

/**
 * 进入直播间
 */

public class PlayActivity extends AppCompatActivity implements ProxyPlayActivity {

    public static final String INPUT_FRAGMENT = "InPutFragment";
    public static final String STORE_FRAGMENT = "StoreFragment";
    public static final String TAG = "PlayActivity";
    public static final String DATA = "LIVE_DATA";
    public static final int GET_VIEWS_SUCCESS = 1;
    public static final int GET_GOLD_SUCCESS = 2;
    private static final int GET_MMINFO_SUCCESS = 3;
    ImageView close;
    ArrayList<Viewer> views;
    private LivesBean livesBean;
    private InPutFragment inPutFragment;
    private MyHandler handler;
    private IjkVideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**获取直播间信息*/
        livesBean = (LivesBean) getIntent().getSerializableExtra(PlayActivity.DATA);
        Log.d("TAG", "livesBean=" + livesBean.toString());
        views = new ArrayList<>();
        enterRoom(livesBean);
        setContentView(R.layout.activity_play);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        handler = new PlayActivity.MyHandler(this);
        EventBus.getDefault().register(this);

        initView();
        initData();
        //        InPutFragment  inPutFragment = (InPutFragment)getSupportFragmentManager().findFragmentByTag(INPUT_FRAGMENT);
        InPutFragment inPutFragment = (InPutFragment) getFragment(INPUT_FRAGMENT);
        inPutFragment.showOnlineNumbers(livesBean.getOnline_users() + "");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 获取Fragment
     *
     * @param tag
     * @return
     */
    @Override
    public Fragment getFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    private void initView() {
        close = (ImageView) findViewById(R.id.close);
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
    }

    private void initData() {
        if (livesBean != null) {
            startPlay();
        }
    }

    private void startPlay() {
        /**拉流地址*/
        mVideoView.setVideoPath(livesBean.getStream_addr());
        mVideoView.setHudView(null);
        mVideoView.showMediaInfo();
        mVideoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //        if(!mVideoView.isPlaying()){
        //            mVideoView.setVideoPath(livesBean.getStream_addr());
        //            mVideoView.start();
        //        }
        Log.i(TAG, "onStart isPlaying=" + mVideoView.isPlaying());//false
        Log.i(TAG, "onStart isBackgroundPlayEnabled=" + !mVideoView.isBackgroundPlayEnabled());//true
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop isBackgroundPlayEnabled=" + !mVideoView.isBackgroundPlayEnabled());//true
        //        if (!mVideoView.isBackgroundPlayEnabled()) {
        //            mVideoView.stopPlayback();
        //            mVideoView.release(true);
        //            mVideoView.stopBackgroundPlay();
        //        } else {
        //            mVideoView.enterBackground();
        //        }
        //        IjkMediaPlayer.native_profileEnd();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showOrUnshowClose(ShowCloseEvent event) {
        int status = event.isShow() ? View.VISIBLE : View.INVISIBLE;
        close.setVisibility(status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
            IjkMediaPlayer.native_profileEnd();
        }
        EventBus.getDefault().unregister(this);
    }

    //进房接口,获取艺人的信息
    private void enterRoom(LivesBean bean) {
        //String liveId = bean.getId();
        // int id = bean.getCreator().getId();
        if (bean != null) {
            String liveId = bean.getId();
            Log.i(TAG, "bean=" + bean.toString());
            getInfo(liveId);
            getViewsByurl(liveId);
            getGold(bean.getCreator().getId());
        } else {
            /**测试使用*/
            //            String liveId = "1484743319510602";
            //            getInfo(liveId);
            //            getViewsByurl(liveId);
            //            getGold(4223067);
        }
    }

    /***
     * 获取房间信息
     *
     * @param liveId
     */
    private void getInfo(String liveId) {

        IGetMMInfor client = ServiceGenerator.createService(IGetMMInfor.class);

        String url = Constance.getRoomInfor(liveId);

        Log.i("hked", "url = " + url);
        Call<ResponseBody> call = client.doGet(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String json = null;
                    try {
                        json = response.body().string();
                        try {
                            JSONObject js = new JSONObject(json);
                            JSONObject live_obj = js.optJSONObject("live");
                            MMInfo info = parse(live_obj.toString(), MMInfo.class);
                            Log.i("hked", info.toString());
                            Message msg = handler.obtainMessage();
                            msg.obj = info;
                            msg.what = GET_MMINFO_SUCCESS;
                            handler.sendMessage(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(json)) {
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    /***
     * 获取Creators
     *
     * @param liveId
     */
    public void getViewsByurl(String liveId) {
        IGetMMInfor client = ServiceGenerator.createService(IGetMMInfor.class);

        String url = Constance.getViewersUrl(liveId);

        Call<ResponseBody> call = client.doGet(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String json = null;
                    try {
                        json = response.body().string();

                        try {
                            JSONObject js = new JSONObject(json);

                            JSONArray users = js.optJSONArray("users");

                            for (int i = 0; i < users.length(); i++) {

                                JSONObject object = users.getJSONObject(i);
                                Viewer viewer = JsonUtil.parse(object.toString(), Viewer.class);
                                views.add(viewer);
                            }
                            Log.i("hked", views.toString());
                            Message msg = handler.obtainMessage();
                            msg.obj = views;
                            msg.what = GET_VIEWS_SUCCESS;
                            handler.sendMessage(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(json)) {
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    /***
     * 获取映客号和映票
     *
     * @param creator_id
     */
    public void getGold(int creator_id) {
        IGetMMInfor client = ServiceGenerator.createService(IGetMMInfor.class);

        String url = Constance.getGoldsUrl(String.valueOf(creator_id));

        Call<ResponseBody> call = client.doGet(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String json = null;
                    try {
                        json = response.body().string();

                        try {
                            JSONObject js = new JSONObject(json);

                            JSONObject jsonObject = js.optJSONObject("inout");

                            Inout inout = JsonUtil.parse(jsonObject.toString(), Inout.class);

                            Log.i("hked", "inout = " + inout.toString());
                            Message msg = handler.obtainMessage();
                            msg.obj = inout;
                            msg.what = GET_GOLD_SUCCESS;
                            handler.sendMessage(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(json)) {
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    public void showViewers(List<Viewer> viewers) {
        InPutFragment inPutFragment = (InPutFragment) getFragment(INPUT_FRAGMENT);
        //        InPutFragment  inPutFragment = (InPutFragment)getSupportFragmentManager().findFragmentByTag(INPUT_FRAGMENT);
        inPutFragment.initAllData(viewers);
    }

    public void showNumber(Inout inout) {
        InPutFragment inPutFragment = (InPutFragment) getFragment(INPUT_FRAGMENT);
        //        InPutFragment  inPutFragment = (InPutFragment)getSupportFragmentManager().findFragmentByTag(INPUT_FRAGMENT);
        inPutFragment.showNumbers(inout);
    }

    /***
     * 设计映客号id
     *
     * @param RoomId
     */
    public void setRoomID(int RoomId) {
        InPutFragment inPutFragment = (InPutFragment) getFragment(INPUT_FRAGMENT);
        //        InPutFragment  inPutFragment = (InPutFragment)getSupportFragmentManager().findFragmentByTag(INPUT_FRAGMENT);
        inPutFragment.setRoomID(RoomId);
    }

    @Override
    public void onBackPressed() {
        StoreFragment fragment = (StoreFragment) getFragment(STORE_FRAGMENT);
        if (fragment != null) {
            if (fragment.isShowStore()) {
                fragment.hideContent();
                return;
            }
        }
        super.onBackPressed();
    }

    static class MyHandler extends Handler {

        WeakReference<PlayActivity> baseActivity;

        public MyHandler(PlayActivity activity) {
            this.baseActivity = new WeakReference<PlayActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            PlayActivity activity = baseActivity.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case GET_VIEWS_SUCCESS:
                    List<Viewer> viewers = (List<Viewer>) msg.obj;
                    Log.d(TAG, viewers.toString());
                    activity.showViewers(viewers);
                    break;
                case GET_GOLD_SUCCESS:
                    Inout inout = (Inout) msg.obj;
                    activity.showNumber(inout);
                    break;
                case GET_MMINFO_SUCCESS:
                    MMInfo info = (MMInfo) msg.obj;
                    activity.setRoomID(info.getRoom_id());
                    break;
            }
        }
    }
}
