package com.bing.lan.fm.ui.music;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.hot.bean.ListItemEditorBean;

import static com.bing.lan.fm.ui.hot.delagate.EditorRecomItemDelagate.ALBUM_DETAIL;

public class MusicActivity extends AppCompatActivity {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListItemEditorBean serializableExtra = (ListItemEditorBean) getIntent().getSerializableExtra(ALBUM_DETAIL);

        log.d("onCreate(): " + serializableExtra);

    }
}
