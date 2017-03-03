// package com.bing.lan.ijkplayer;
//
// import android.content.res.Configuration;
// import android.os.Bundle;
// import android.support.design.widget.FloatingActionButton;
// import android.support.design.widget.Snackbar;
// import android.support.v7.app.AppCompatActivity;
// import android.support.v7.widget.Toolbar;
// import android.view.KeyEvent;
// import android.view.Menu;
// import android.view.MenuItem;
// import android.view.View;
//
// import com.bumptech.glide.Glide;
// import com.dl7.player.media.IjkPlayerView;
//
// public class MusicActivity extends AppCompatActivity {
//
//     private static final String VIDEO_URL1 = "\"http://download.xmcdn.com/group25/M01/B9/DE/wKgJMViyV8rgTyfwAASnKx3q-DI155.m4a\"";
//     private static final String VIDEO_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/SD/movie_index.m3u8";
//     private static final String VIDEO_HD_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/HD/movie_index.m3u8";
//     private static final String IMAGE_URL = "http://vimg2.ws.126.net/image/snapshot/2016/11/I/M/VC62HMUIM.jpg";
//     private IjkPlayerView mPlayerView;
//
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_music);
//         initView();
//     }
//
//     private void initView() {
//         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//         setSupportActionBar(toolbar);
//
//         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//         fab.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                         .setAction("Action", null).show();
//             }
//         });
//
//         initPlayer();
//     }
//
//     private void initPlayer() {
//         mPlayerView = (IjkPlayerView) findViewById(R.id.player_view);
//
//         Glide.with(this).load(IMAGE_URL).fitCenter().into(mPlayerView.mPlayerThumb);
//         mPlayerView.init()
//                 .setTitle("这是个跑马灯TextView，标题要足够长才会跑。-(゜ -゜)つロ 乾杯~")
//                 .setSkipTip(1000 * 60 * 1)
//                 .enableDanmaku()
//                 .setDanmakuSource(getResources().openRawResource(R.raw.bili))
//                 .setVideoSource(null, VIDEO_URL1, VIDEO_URL1, null, null)
//                 .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH);
//     }
//
//     @Override
//     protected void onResume() {
//         super.onResume();
//         mPlayerView.onResume();
//     }
//
//     @Override
//     protected void onPause() {
//         super.onPause();
//         mPlayerView.onPause();
//     }
//
//     @Override
//     protected void onDestroy() {
//         super.onDestroy();
//         mPlayerView.onDestroy();
//     }
//
//     @Override
//     public void onConfigurationChanged(Configuration newConfig) {
//         super.onConfigurationChanged(newConfig);
//         mPlayerView.configurationChanged(newConfig);
//     }
//
//     @Override
//     public boolean onKeyDown(int keyCode, KeyEvent event) {
//         if (mPlayerView.handleVolumeKey(keyCode)) {
//             return true;
//         }
//         return super.onKeyDown(keyCode, event);
//     }
//
//     @Override
//     public void onBackPressed() {
//         if (mPlayerView.onBackPressed()) {
//             return;
//         }
//         super.onBackPressed();
//     }
//
//     @Override
//     public boolean onCreateOptionsMenu(Menu menu) {
//         getMenuInflater().inflate(R.menu.menu_music, menu);
//         return true;
//     }
//
//     @Override
//     public boolean onOptionsItemSelected(MenuItem item) {
//         int id = item.getItemId();
//         if (id == R.id.action_settings) {
//             return true;
//         }
//         return super.onOptionsItemSelected(item);
//     }
// }
