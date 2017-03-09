package com.bing.lan.fm.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.view.FlexibleScrollView;
import com.bing.lan.comm.view.FlowLayout;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.search.bean.SearchHotWordResult;
import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.bing.lan.fm.ui.search.SearchPresenter.LOAD_SEARCH_RESULT;

public class SearchActivity extends BaseActivity<ISearchContract.ISearchPresenter>
        implements ISearchContract.ISearchView, SearchView.OnQueryTextListener,
        SearchAdapter.OnItemClickListener,
        SearchView.OnVoiceClickListener,
        FlowLayout.OnItemClickListener {

    public static final String EXTRA_KEY_TEXT = "text";
    @BindView(R.id.searchView)
    SearchView mSearchView;
    // @BindView(R.id.search_recyclerView)
    // RecyclerView mSearchRecyclerView;
    @BindView(R.id.fs_container)
    FlexibleScrollView mFlexibleScrollView;
    @BindView(R.id.flow_container1)
    FlowLayout mFlowContainer1;
    @BindView(R.id.flow_container2)
    FlowLayout mFlowContainer2;

    private SearchHistoryTable mHistoryDatabase;
    private String mSearchWord;
    private List<SearchItem> mAllItems;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        mHistoryDatabase = new SearchHistoryTable(this);
        Integer databaseKey = null;
        mAllItems = mHistoryDatabase.getAllItems(databaseKey);

        initSearchView(intent);
        initFlexibleScrollView();
    }

    @Override
    protected void readyStartPresenter() {

        mPresenter.onStart();
    }

    private void initFlexibleScrollView() {
        mFlowContainer1.removeAllViews();
        for (SearchItem allItem : mAllItems) {
            mFlowContainer1.addChild(allItem.get_text());
        }
        mFlowContainer1.setOnItemClickListener(this);
        mFlowContainer2.setOnItemClickListener(this);
    }

    public void updateFlowContainer1(String string) {
        mFlowContainer1.addChild(string, 0);
    }

    public void updateFlowContainer2(List<SearchHotWordResult.HotWordListBean> hotWordListBeen) {
        mFlowContainer2.removeAllViews();
        for (SearchHotWordResult.HotWordListBean hotWordListBean : hotWordListBeen) {
            mFlowContainer2.addChild(hotWordListBean.getSearchWord());
        }
    }

    private void initSearchView(Intent intent) {
        mSearchView.open(true);

        Bundle extras = intent.getExtras();
        if (extras != null) {
            mSearchWord = extras.getString(EXTRA_KEY_TEXT);
            if (mSearchWord != null) {
                mSearchView.setQuery(mSearchWord, true);
                mSearchView.close(false);//关闭,并取消焦点
            }
        }

        mSearchView.setOnQueryTextListener(this);
        SearchAdapter searchAdapter = new SearchAdapter(this, new ArrayList<SearchItem>());
        searchAdapter.addOnItemClickListener(this);
        mSearchView.setAdapter(searchAdapter);

        mSearchView.setVoiceText("Set permission on Android 6.0+ !");
        mSearchView.setOnVoiceClickListener(this);

        mSearchView.setArrowOnly(false);
    }

    protected void requestQuery(String text) {
        mPresenter.requestData(LOAD_SEARCH_RESULT, text);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        requestQuery(query);
        updateFlowContainer1(query);
        mHistoryDatabase.addItem(new SearchItem(query));
        mSearchView.close(false);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    //search view 的监听
    @Override
    public void onItemClick(View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
        String query = textView.getText().toString();
        mHistoryDatabase.addItem(new SearchItem(query));
        requestQuery(query);
        mSearchView.close(false);
    }

    @Override
    public void onVoiceClick() {
        // permission
        showToast("请最准麦克风说话");
    }

    public void updateSearchWord(String searchWord) {
        mSearchView.setHint(searchWord);
    }

    //流式布局的监听器
    @Override
    public void onItemClick(View view) {

    }
}
