// package com.bing.lan.fm.ui.anchor;
//
// import android.content.Intent;
// import android.os.Bundle;
// import android.support.v4.widget.SwipeRefreshLayout;
// import android.support.v7.widget.RecyclerView;
//
// import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
// import com.bing.lan.comm.di.FragmentComponent;
// import com.bing.lan.fm.R;
// import com.bing.lan.fm.ui.anchor.bean.AnchorResult;
// import com.bing.lan.fm.ui.anchor.bean.FamousBean;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import butterknife.BindView;
//
// /**
//  *
//  */
// public class AnchorFragment extends BaseFragment<IAnchorContract.IAnchorPresenter>
//         implements IAnchorContract.IAnchorView {
//     @BindView(R.id.anchor_recyclerView)
//     RecyclerView mAnchorRecyclerView;
//     @BindView(R.id.anchor_refresh_container)
//     SwipeRefreshLayout mAnchorRefreshContainer;
//     //1级数据
//     List<FamousBean> mRecyclerViewData;
//
//     /**
//      * 绑定的布局
//      *
//      * @return 返回当前的布局
//      */
//     @Override
//     protected int getLayoutResId() {
//         return R.layout.fragment_anchor;
//     }
//
//     /**
//      * @param fragmentComponent 注入界面
//      */
//     @Override
//     protected void startInject(FragmentComponent fragmentComponent) {
//         fragmentComponent.inject(this);
//     }
//
//     /**
//      * @param intent    Intent 的实例
//      * @param arguments Fragment 传递的参数
//      */
//     @Override
//     protected void initViewAndData(Intent intent, Bundle arguments) {
//
//     }
//
//     /**
//      *发起一个请求
//      */
//     @Override
//     protected void readyStartPresenter() {
//         mPresenter.onStart();
//     }
//
//     /**
//      * 得到数据后,开始操作数据
//      * @param data 所有的数据
//      */
//     @Override
//     public void upDateRecyclerView(AnchorResult data) {
//         mRecyclerViewData = new ArrayList<>();
// //            new MultiItemTypeAdapter<>()
//      }
// }
