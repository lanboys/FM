package com.bing.lan.fm.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.fm.R;
import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<ISearchContract.ISearchPresenter>
        implements ISearchContract.ISearchView, SearchView.OnQueryTextListener, SearchAdapter.OnItemClickListener, SearchView.OnVoiceClickListener {

    public static final String EXTRA_KEY_TEXT = "text";
    @BindView(R.id.searchView)
    SearchView mSearchView;
    @BindView(R.id.search_recyclerView)
    RecyclerView mSearchRecyclerView; 

    private SearchHistoryTable mHistoryDatabase;
    private String mSearchWord;

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

        initSearchView(intent);
        initRecyclerView();
    }

    @Override
    protected void readyStartPresenter() {
        // CharSequence hint = mSearchView.getHint();
        mPresenter.onStart();
    }

    private void initRecyclerView() {
        List<String> list = new ArrayList<>(30);
        while (list.size() < 30) {
            list.add("test数据");
        }

        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSearchRecyclerView.setAdapter(new RecyclerViewAdapter(list));
        mSearchRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // mSearchView.close(true);
            }
        });
    }

    private void initSearchView(Intent intent) {

        mHistoryDatabase = new SearchHistoryTable(this);
        mSearchView.open(true);

        Bundle extras = intent.getExtras();
        if (extras != null) {
            mSearchWord = extras.getString(EXTRA_KEY_TEXT);
            mSearchView.setHint(mSearchWord);
        }

        mSearchView.setOnQueryTextListener(this);

        SearchAdapter searchAdapter = new SearchAdapter(this, new ArrayList<SearchItem>());
        searchAdapter.addOnItemClickListener(this);
        mSearchView.setAdapter(searchAdapter);

        mSearchView.setVoiceText("Set permission on Android 6.0+ !");
        mSearchView.setOnVoiceClickListener(this);

        mSearchView.setArrowOnly(false);
    }

    protected void goSearchActivity(String text) {
        showToast(text);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mHistoryDatabase.addItem(new SearchItem(query));
        goSearchActivity(query);
        mSearchView.close(false);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemClick(View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
        String query = textView.getText().toString();
        mHistoryDatabase.addItem(new SearchItem(query));
        goSearchActivity(query);
        mSearchView.close(false);
    }

    @Override
    public void onVoiceClick() {
        // permission
        showToast("请最准麦克风说话");
    }

    public void updateSearchWord(String searchWord) {
        mSearchView.setHint(searchWord);
        mSearchView.removeFocus();
    }
}
