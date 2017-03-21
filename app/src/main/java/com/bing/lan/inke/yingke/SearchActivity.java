package com.bing.lan.inke.yingke;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bing.lan.fm.R;
import com.bing.lan.inke.yingke.adapter.ResultAdapter;
import com.bing.lan.inke.yingke.adapter.SearchAdapter;
import com.bing.lan.inke.yingke.bean.LivesBean;
import com.bing.lan.inke.yingke.bean.SearchRecomd;
import com.bing.lan.inke.yingke.bean.SearchUserBean;
import com.bing.lan.inke.yingke.bean.SearchUserWarper;
import com.bing.lan.inke.yingke.http.ISearchKeyWord;
import com.bing.lan.inke.yingke.http.SearchAllClient;
import com.bing.lan.inke.yingke.http.ServiceGenerator;
import com.bing.lan.inke.yingke.util.JsonUtil;
import com.bing.lan.inke.yingke.util.LoggerUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * 收索界面
 * Created by kay on 16/12/8.
 */

public class SearchActivity extends AppCompatActivity {

    private ImageView clean;
    private TextView cancle;
    private EditText search;
    private RecyclerView recyle, result;

    private ArrayList<SearchRecomd> recomd_titles;
    private ArrayList<SearchUserWarper> recomd_user;

    public static final int INIT_SUCCESS = 0;
    public static final int SEARCH_SUCCESS = 1;

    MyHandler handler;

    SearchAdapter adapter;
    ResultAdapter search_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search1);
        initView();
        setListener();
        getAll();
    }




    private void initView() {

        handler = new MyHandler(this);
        //推荐的栏目的数据集合
        recomd_titles = new ArrayList<>();
        //推荐的用户的数据集合
        recomd_user = new ArrayList<>();

        cancle = (TextView) findViewById(R.id.cancle);
        search = (EditText) findViewById(R.id.search);
        recyle = (RecyclerView) findViewById(R.id.recyle);
        result = (RecyclerView) findViewById(R.id.result);
        clean = (ImageView) findViewById(R.id.clean);

        recyle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        result.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果搜索的关键字不为空，就去请求查询接口
                if (!TextUtils.isEmpty(s.toString())) {
                    clean.setVisibility(View.VISIBLE);
                    doSearch(s.toString());
                } else {
                    clean.setVisibility(View.GONE);
                    result.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setListener() {

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void doSearch(String keyWord) {
        try {
            String par = URLEncoder.encode(keyWord, "utf-8");
            String url = Constance.getSearchKeyword(par);
            LoggerUtil.t("url = " + url);
            // Create a very simple REST adapter which points the GitHub API endpoint.
            ISearchKeyWord client = ServiceGenerator.createService(ISearchKeyWord.class);

            Call<ResponseBody> call = client.doSearch(url);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            String json = response.body().string();
                            if (TextUtils.isEmpty(json)) {
                                return;
                            }
                            ArrayList<SearchUserBean> result = new ArrayList<SearchUserBean>();
                            try {

                                JSONObject js = new JSONObject(json);

                                JSONArray users = js.getJSONArray("users");

                                for (int i = 0; i < users.length(); i++) {
                                    JSONObject tmp = users.getJSONObject(i);
                                    SearchUserBean bean = JsonUtil.parse(tmp.toString(), SearchUserBean.class);
                                    result.add(bean);
                                }

                                Message message = handler.obtainMessage(SEARCH_SUCCESS, result);
                                handler.sendMessage(message);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                }
            });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void submit() {
        // validate
        String searchString = search.getText().toString().trim();
        if (TextUtils.isEmpty(searchString)) {
            Toast.makeText(this, "请输入内容！！！", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    //获取所有的推荐的数据
    private void getAll() {

        // Create a very simple REST adapter which points the GitHub API endpoint.
        SearchAllClient client = ServiceGenerator.createService(SearchAllClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<ResponseBody> call = client.getRecomed();


        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();


                        JSONObject js = new JSONObject(json);
                        JSONArray live = js.getJSONArray("live_nodes");
                        JSONArray users = js.getJSONArray("user_nodes");

                        for (int i = 0; i < live.length(); i++) {
                            JSONObject tmp = live.optJSONObject(i);
                            SearchRecomd re = JsonUtil.parse(tmp.toString(), SearchRecomd.class);
                            recomd_titles.add(re);

                        }


                        for (int i = 0; i < users.length(); i++) {
                            JSONObject tmp = users.optJSONObject(i);
                            SearchUserWarper userWarper = JsonUtil.parse(tmp.toString(), SearchUserWarper.class);
                            recomd_user.add(userWarper);
                        }


                        handler.sendEmptyMessage(INIT_SUCCESS);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    public void initAllDate() {

        adapter = new SearchAdapter(recomd_titles, recomd_user, this);
        recyle.setAdapter(adapter);
        adapter.setOnClickListener(new SearchAdapter.OnSearchClickListener() {
            @Override
            public void onClickSearchItem(int index) {
                Toast.makeText(getApplication(),"index="+index,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickSearchOnePicture(View v, int position, LivesBean lives) {
//                Toast.makeText(getApplication(),"position="+position,Toast.LENGTH_SHORT).show();
                if(lives!=null){
                    Intent intent = new Intent();
                    intent.setClass(SearchActivity.this, PlayActivity.class);
                    intent.putExtra(PlayActivity.DATA,lives);
                    startActivity(intent);
                }

            }
        });

    }

    public void showSearchResult(ArrayList<SearchUserBean> tmp) {
        if (search_adapter == null) {
            search_adapter = new ResultAdapter(tmp, this);
            result.setAdapter(search_adapter);
        } else {
            search_adapter.addNewDate(tmp);
            search_adapter.notifyDataSetChanged();
        }
        result.setVisibility(View.VISIBLE);

//        search_adapter.setOnClickListener(new ResultAdapter.OnResultClickListener() {
//            @Override
//            public void onClickResultItem(int index) {
//            }
//        });
    }

    static class MyHandler extends Handler {

        WeakReference<SearchActivity> baseActivity;

        public MyHandler(SearchActivity activity) {
            this.baseActivity = new WeakReference<SearchActivity>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            SearchActivity activity = baseActivity.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case INIT_SUCCESS:
                    activity.initAllDate();
                    break;
                case SEARCH_SUCCESS:
                    ArrayList<SearchUserBean> tmp = (ArrayList<SearchUserBean>) msg.obj;
                    activity.showSearchResult(tmp);
                    break;
            }
        }
    }

}
