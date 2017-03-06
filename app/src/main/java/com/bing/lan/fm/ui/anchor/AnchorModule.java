// package com.bing.lan.fm.ui.anchor;
//
// import com.bing.lan.comm.base.mvp.IBaseContract;
// import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
// import com.bing.lan.fm.ui.anchor.bean.AnchorResult;
//
// import rx.Observable;
// import rx.functions.Func1;
//
// /**
//  * @author 蓝兵
//  * @time 2017/2/8  10:26
//  */
// public class AnchorModule extends BaseFragmentModule
//         implements IAnchorContract.IAnchorModule {
//
//     @Override
//     public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
//         switch (action) {
//             case  AnchorPresenter.ANCHOR_NULL:
//                 loadAnchorMain(action,listener);
//                 break;
//         }
//     }
//
//     private void loadAnchorMain(int action, IBaseContract.OnDataChangerListener listener) {
//         //获取到对象的数据
//         Observable<AnchorResult> observable= mApiService.getAnchorResult().filter(new Func1<AnchorResult, Boolean>() {
//             @Override
//             public Boolean call(AnchorResult anchorResult) {
//                 if (!(anchorResult.getRet() == 0)) {
//                     throw new RuntimeException("请求数据失败,错误信息:" + anchorResult.getMsg());
//                 }
//                 return true;
//             }
//         });
//         //回调的方法
//         subscribe(observable,action,listener,"主播界面");
//     }
// }
