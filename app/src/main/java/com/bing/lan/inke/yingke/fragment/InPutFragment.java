package com.bing.lan.inke.yingke.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.PlayActivity;
import com.bing.lan.inke.yingke.adapter.InputCharAdapter;
import com.bing.lan.inke.yingke.adapter.InputCreatorAdapter;
import com.bing.lan.inke.yingke.adapter.ShowGiftsAdapter;
import com.bing.lan.inke.yingke.bean.Gift;
import com.bing.lan.inke.yingke.bean.GiftReceive;
import com.bing.lan.inke.yingke.bean.Inout;
import com.bing.lan.inke.yingke.bean.SocketDate;
import com.bing.lan.inke.yingke.bean.Viewer;
import com.bing.lan.inke.yingke.event.ShowCloseEvent;
import com.bing.lan.inke.yingke.interfaces.ProxyPlayActivity;
import com.bing.lan.inke.yingke.util.Contance;
import com.bing.lan.inke.yingke.util.JsonUtil;
import com.bing.lan.inke.yingke.util.TimerUtil;
import com.bing.lan.inke.yingke.util.WebSocketManger;
import com.bing.lan.inke.yingke.widght.DivergeView;
import com.bing.lan.inke.yingke.widght.MyRecyclerView;
import com.bing.lan.inke.yingke.widght.ResizeLayout;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;



public class InPutFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "InPutFragment";
    private static final int GET_SOCKET_INFO_SUCCESS = 1;
    protected InputCharAdapter adapterChar;
    private ImageView icon;
    private LinearLayout left;
    private MyRecyclerView recyle;
    private RelativeLayout top;
    private ImageView send;
    private ImageView close;
    private RelativeLayout space;
    private ImageView share;
    private ImageView shop;
    private ImageView p_chat;
    private RelativeLayout buttom;
    private TextView info_numer;//映客号
    private TextView tv_gold_number;//映票
    private TextView tv_online_number;//在线人数
    private TextView date;
    private LinearLayout infor;
    private EditText edt;
    private TextView send_msg;
    private MyRecyclerView chat;
    private RecyclerView show_gifts;
    private ResizeLayout content;
    private LinearLayout card;
    private RelativeLayout edit_outer;
    private boolean isShowKeyBoard = false;
    private InputCreatorAdapter adapterCreator;
    /**
     * 房间号的id
     */
    private int roomId;
    private MyHandler hander;
    private String userName;
    private DivergeView divergeView;
    private ArrayList<Object> mList;
    private WebSocketConnection mConnect;
    private ShowGiftsAdapter adapterGift;
    private Timer timer;
    private TimerTask timerTask;

    public static void hideSoftInput(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (inputManager != null && inputManager.isActive() && view != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InputCharAdapter getAdapterChar() {
        return adapterChar;
    }

    public ShowGiftsAdapter getAdapterGift() {
        return adapterGift;
    }

    public MyRecyclerView getChat() {
        return chat;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        icon = (ImageView) view.findViewById(R.id.icon);
        left = (LinearLayout) view.findViewById(R.id.left);
        recyle = (MyRecyclerView) view.findViewById(R.id.recyle);
        top = (RelativeLayout) view.findViewById(R.id.top);
        send = (ImageView) view.findViewById(R.id.send);
        send.setOnClickListener(this);
        space = (RelativeLayout) view.findViewById(R.id.space);
        share = (ImageView) view.findViewById(R.id.share);
        share.setOnClickListener(this);
        shop = (ImageView) view.findViewById(R.id.shop);

        shop.setOnClickListener(this);
        p_chat = (ImageView) view.findViewById(R.id.p_chat);
        p_chat.setOnClickListener(this);

        buttom = (RelativeLayout) view.findViewById(R.id.buttom);
        info_numer = (TextView) view.findViewById(R.id.info_numer);
        tv_online_number = (TextView) view.findViewById(R.id.tv_online_number);
        tv_gold_number = (TextView) view.findViewById(R.id.tv_gold_number);
        date = (TextView) view.findViewById(R.id.date);
        date.setText(TimerUtil.getDatefromSystem());
        infor = (LinearLayout) view.findViewById(R.id.infor);

        divergeView = (DivergeView) view.findViewById(R.id.divergeView);
        edt = (EditText) view.findViewById(R.id.edt);
        send_msg = (TextView) view.findViewById(R.id.send_msg);
        send_msg.setOnClickListener(this);
        /**聊天的RecyclerView*/
        chat = (MyRecyclerView) view.findViewById(R.id.chat);
        /**展示送礼的 RecyclerView */
        show_gifts = (RecyclerView) view.findViewById(R.id.show_gifts);
        content = (ResizeLayout) view.findViewById(R.id.rz_content);
        card = (LinearLayout) view.findViewById(R.id.card);
        edit_outer = (RelativeLayout) view.findViewById(R.id.edid_outer);
        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(getActivity());
            }
        });
        content.setOnResizeListener(new ResizeLayout.OnResizeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                if (state == ResizeLayout.KEYBOARD_STATE_SHOW) {
                    top.setVisibility(View.GONE);
                    card.setVisibility(View.GONE);
                    infor.setVisibility(View.GONE);
                } else {
                    // hideKeyBoard(getActivity());
                    isShowKeyBoard = false;
                    edit_outer.setVisibility(View.INVISIBLE);
                    buttom.setVisibility(View.VISIBLE);

                    top.setVisibility(View.VISIBLE);
                    card.setVisibility(View.VISIBLE);
                    infor.setVisibility(View.VISIBLE);
                    EventBus.getDefault().post(new ShowCloseEvent(true));
                }
            }
        });

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "w53", Toast.LENGTH_SHORT).show();
                PlayActivity mPlayActivity = (PlayActivity) getActivity();
                StoreFragment mStoreFragment = (StoreFragment) mPlayActivity.getFragment(PlayActivity.STORE_FRAGMENT);
                if (mStoreFragment.isShowStore()) {
                    mStoreFragment.hideContent();
                }
            }
        });

        //指定布局的方向
        recyle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //        recyle.addItemDecoration(new RecyclerViewDecoration(getActivity(), RecyclerViewDecoration.HORIZONTAL_LIST));
        recyle.setResizeLayout(content);
        chat.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //        chat.addItemDecoration(new RecyclerViewDecoration(getActivity(), RecyclerViewDecoration.HORIZONTAL_LIST));
        chat.setResizeLayout(content);

        show_gifts.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        SlideInLeftAnimator slideInLeftAnimator = new SlideInLeftAnimator();
        slideInLeftAnimator.setAddDuration(500);
        slideInLeftAnimator.setRemoveDuration(500);
        show_gifts.setItemAnimator(slideInLeftAnimator);

        hander = new MyHandler(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * websocket连接，接收服务器消息
     */
    private void connect() {
        mConnect = WebSocketManger.getInstance();
        Log.i(TAG, "ws connect....");
        try {
            mConnect.connect(Constance.WSURL, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    Log.i(TAG, "Status:Connect to " + Constance.WSURL);
                    Random random = new Random();
                    userName = random.nextInt(100) + "" + random.nextInt(100) + "" + random.nextInt(100);
                    Log.i(TAG, "userName: " + userName);
                    autoLogin(userName, roomId);
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.i(TAG, payload);
                    if (payload != null) {
                        SocketDate socketDate = JsonUtil.parse(payload, SocketDate.class);
                        Log.i(TAG, "socketDate: " + socketDate.toString());
                        Message message = hander.obtainMessage();
                        message.obj = socketDate;
                        message.what = GET_SOCKET_INFO_SUCCESS;
                        hander.sendMessage(message);
                    }
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.i(TAG, "Connection lost..");
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送礼物
     *
     * @param selectGift
     */
    public void sendGiftsToWebSocket(List<Gift> selectGift) {
        Log.d(TAG, selectGift.toString());
        if (selectGift != null) {
            sendGiftsToALL(userName, selectGift, roomId);
        }
    }

    /**
     * 自动登录
     *
     * @param userName 用户名
     * @param roomId   加入哪个主播的聊天群
     */
    private void autoLogin(String userName, int roomId) {
        SocketDate mSocketDate = new SocketDate();
        //将要登录到哪个群
        mSocketDate.setGroud(roomId + "");
        //登录用户名
        mSocketDate.setUserName(userName);
        String json = new Gson().toJson(mSocketDate);
        mConnect.sendTextMessage(json);
    }

    /**
     * 这个方法在加载完 Creator 的时候调用
     *
     * @param viewers
     */
    public void initAllData(List<Viewer> viewers) {
        //init creators
        adapterCreator = new InputCreatorAdapter(getActivity());
        recyle.setAdapter(adapterCreator);
        adapterCreator.setDates(viewers);

        //init chat
        adapterChar = new InputCharAdapter(getActivity());
        chat.setAdapter(adapterChar);

        // int show gifts
        adapterGift = new ShowGiftsAdapter(getActivity());
        show_gifts.setAdapter(adapterGift);
    }

    @Override
    public void onStart() {
        super.onStart();
        //init heart
        mList = new ArrayList<>();
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.mipmap.short_video_heart_praise, null)).getBitmap());
        divergeView.setEndPoint(new PointF(divergeView.getMeasuredWidth() / 2, 0));
        divergeView.setDivergeViewProvider(new Provider());
        /*** 新建一个定时器*/
        startTimer();
    }

    /**
     * 开始一个定时器
     */
    private void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                //冒红心
                Random randow = new Random();
                int i = randow.nextInt(5);
                for (int j = 0; j < i; j++) {
                    divergeView.startDiverges(0);
                }
                //刷新展示礼物的recyeView
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (adapterGift != null) {
                            adapterGift.updata();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    @Override
    public void onDestroyView() {
        stopTimer();
        super.onDestroyView();
    }

    /**
     * 停止计时器
     */
    private void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 显示映客号,映票
     *
     * @param inout
     */
    public void showNumbers(Inout inout) {
        if (inout != null) {
            info_numer.setText(inout.getPoint() + "");
            tv_gold_number.setText("映客号：" + inout.getGold() + "");
        }
    }

    /**
     * 显示在线人数
     *
     * @param onLineNumber
     */
    public void showOnlineNumbers(String onLineNumber) {
        if (onLineNumber != null) {
            tv_online_number.setText(onLineNumber);
        }
    }

    /**
     * roomId
     *
     * @param roomId 房间号
     */
    public void setRoomID(int roomId) {
        this.roomId = roomId;
        /**连接WebSocket*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                connect();
            }
        }).start();
    }

    /**
     * 发送消息
     */
    private void submit() {
        // validate
        String send_message = edt.getText().toString().trim();
        if (TextUtils.isEmpty(send_message)) {
            Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        } else {
            /**发送消息*/
            sendMessageToALL(userName, send_message, roomId);
            edt.setText("");
            hideKeyBoard(getActivity());
        }
    }

    //隐藏键盘
    private void hideKeyBoard(Activity activity) {
        isShowKeyBoard = false;
        edit_outer.setVisibility(View.INVISIBLE);
        // edt.setVisibility(View.INVISIBLE);

        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (inputManager != null && inputManager.isActive() && view != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //显示键盘
    private void showKeyBoard() {
        isShowKeyBoard = true;
        edit_outer.setVisibility(View.VISIBLE);

        //edt.setVisibility(View.VISIBLE);
        edt.requestFocus();

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**打开软件盘*/
            case R.id.send:
                if (!isShowKeyBoard) {
                    buttom.setVisibility(View.INVISIBLE);

                    EventBus.getDefault().post(new ShowCloseEvent(false));
                    showKeyBoard();
                }
                break;
            /**发送消息*/
            case R.id.send_msg:
                submit();
                break;
            /**发送礼品*/
            case R.id.shop:
                ProxyPlayActivity mProxyActivity = (ProxyPlayActivity) getActivity();
                StoreFragment mStoreFragment = (StoreFragment) mProxyActivity.getFragment(PlayActivity.STORE_FRAGMENT);
                if (!mStoreFragment.isShowStore()) {
                    mStoreFragment.showContent();
                }
                break;
        }
    }

    /**
     * 发送消息
     *
     * @param userName
     * @param msg      msg
     * @param roomId
     */
    private void sendMessageToALL(String userName, String msg, int roomId) {
        if (mConnect.isConnected()) {
            SocketDate mSocketDate = new SocketDate();
            mSocketDate.setGroud(roomId + "");
            mSocketDate.setMsg(msg);
            mSocketDate.setUserName(userName);
            /**发送给所有人*/
            mSocketDate.setSendToUserName("all");
            String string = new Gson().toJson(mSocketDate);
            mConnect.sendTextMessage(string);
        } else {
            Log.i(TAG, "no connection!!");
        }
    }

    /**
     * 发送礼物
     *
     * @param userName
     * @param gifts    礼物
     * @param roomId
     */
    private void sendGiftsToALL(String userName, List<Gift> gifts, int roomId) {
        if (mConnect.isConnected()) {
            SocketDate mSocketDate = new SocketDate();
            mSocketDate.setGroud(roomId + "");
            mSocketDate.setGifts(gifts);
            mSocketDate.setUserName(userName);
            /**发送给所有人*/
            mSocketDate.setSendToUserName("all");
            String string = new Gson().toJson(mSocketDate);
            mConnect.sendTextMessage(string);
        } else {
            Log.i(TAG, "no connection!!");
        }
    }

    /***
     * 回收资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mConnect != null && mConnect.isConnected()) {
            mConnect.disconnect();
            mConnect = null;
        }
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }

    static class MyHandler extends Handler {

        WeakReference<InPutFragment> input;

        public MyHandler(InPutFragment fragment) {
            this.input = new WeakReference<InPutFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            InPutFragment inPutFragment = input.get();
            if (inPutFragment == null) {
                return;
            }
            switch (msg.what) {
                case GET_SOCKET_INFO_SUCCESS:
                    SocketDate mSocketDate = (SocketDate) msg.obj;
                    if (mSocketDate != null) {
                        ShowGiftsAdapter adapterGift = inPutFragment.getAdapterGift();
                        if (mSocketDate.getType() == Contance.SEND_GIFT_TYPE) {
                            List<Gift> gifts = mSocketDate.getGifts();
                            List<GiftReceive> gitfReceive = new ArrayList<>();
                            for (Gift gift : gifts) {
                                GiftReceive mGiftReceive = new GiftReceive();
                                mGiftReceive.setIcon(gift.getIcon());
                                mGiftReceive.setUser(mSocketDate.getUserName());
                                mGiftReceive.setName(gift.getName());
                                mGiftReceive.setImage(gift.getImage());
                                mGiftReceive.setCounts(1);
                                mGiftReceive.setTimeTemp(System.currentTimeMillis());
                                gitfReceive.add(mGiftReceive);
                            }
                            //                            int itemCount = adapterGift.getItemCount();
                            //                            if(itemCount>=2){
                            //                                adapterGift.removeData(0);
                            //                            }
                            adapterGift.addData(gitfReceive);
                        }
                        InputCharAdapter adapterChar = inPutFragment.getAdapterChar();
                        if (adapterChar != null) {
                            adapterChar.addDates(mSocketDate);
                            MyRecyclerView chat = inPutFragment.getChat();
                            chat.smoothScrollToPosition(adapterChar.getItemCount() - 1);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * 图片提供者
     */
    class Provider implements DivergeView.DivergeViewProvider {

        @Override
        public Bitmap getBitmap(Object obj) {
            Object o = mList == null ? null : mList.get((int) obj);
            return (Bitmap) o;
        }
    }
}
