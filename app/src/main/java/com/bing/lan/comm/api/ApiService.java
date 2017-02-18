package com.bing.lan.comm.api;

import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.hot.bean.HotColumnsResult;
import com.bing.lan.fm.ui.hot.bean.HotResult;
import com.bing.lan.fm.ui.home.bean.HomeTabsResult;
import com.bing.lan.fm.ui.home.bean.SearchWordResult;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {

    String BASE_URL = "http://mobile.ximalaya.com/mobile/";

    /*访问全路径API并返回原生数据*/
    @GET
    Observable<ResponseBody> getRaw(@Url String url);

    @GET
    Call<ResponseBody> getRawUrl(@Url String url);

    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> map
    );



    //搜索文字
    @GET("discovery/v1/search/hint?device=android&version=5.4.81")
    Observable<SearchWordResult> getHomeSearchWord();
    //http://mobile.ximalaya.com/mobile/discovery/v1/search/hint?device=android&version=5.4.81




    // 横向 listview
    @GET("discovery/v3/recommend/hotAndGuess?code=43_440000_4401&device=android&version=5.4.81")
    Observable<HotColumnsResult> getHotColumns();
    // http://mobile.ximalaya.com/mobile/discovery/v3/recommend/hotAndGuess?code=43_440000_4401&device=android&version=5.4.81

    // 小编推荐/轮播图(焦点图)/精品听当
    @GET("discovery/v4/recommends?channel=and-inapp&device=android&includeActivity=true&includeSpecial=true&scale=2&version=5.4.81")
    Observable<HotResult> getHotResult();
    // http://mobile.ximalaya.com/mobile/discovery/v4/recommends?channel=and-inapp&device=android&includeActivity=true&includeSpecial=true&scale=2&version=5.4.81

    // 首页 中 tabs
    @GET("discovery/v2/tabs?device=android&version=5.4.81")
    Observable<HomeTabsResult> getHomeTabs();
    // http://mobile.ximalaya.com/mobile/discovery/v2/tabs?device=android&version=5.4.81

    // Gank 妹子
    @GET
    Observable<GankBean> getGankGirl(@Url String url);
    // http://gank.io/api/data/%E7%A6%8F%E5%88%A9/4/2






    //    @POST("login")
    //    Observable<LoginResultBean> getLoginResult(@Body LoginParamsBean loginParamsBean);

    // @FormUrlEncoded
    // @POST("login")
    // Observable<LoginResultBean> login(@Field("username") String username,
    //         @Field("pwd") String pwd);
    //
    // @GET("banner")
    // Observable<BannerResultBean> loadBanner(@Query("adKind") int adKind);
    //
    // @GET("seckill")
    // Observable<SecKillResultBean> loadSecKill();
    //
    // @GET("getYourFav")
    // Observable<GetYourLikeResultBean> loadGetYourLike();
    //
    // @GET("category")
    // Observable<CategoryResultBean> loadCategory();
    //
    // @GET("category")
    // Observable<SubCategoryResultBean> loadCategory(@Query("parentId") int parentId);
    //
    // @GET("brand")
    // Observable<BrandResultBean> loadBrand(@Query("categoryId") int categoryId);

    // @POST("searchProduct")
    // Observable<ProductListResultBean> loadProductList(@QueryMap HashMap<String, String> params);    // @FormUrlEncoded
    //
    // @FormUrlEncoded
    // @POST("searchProduct")
    // Observable<ProductListResultBean> loadProductList(@FieldMap HashMap<String, String> params);
    //
    // @GET("productInfo")
    // Observable<ProductInfoResultBean> loadProductInfo(@Query("id") long id);
    // //http://mall.520it.com/productInfo?id=1
    //
    // @FormUrlEncoded
    // @POST("commentDetail")
    // Observable<CommentResultBean> loadProductCommentInfo(@FieldMap HashMap<String, String> params);

    // @GET("home")
    // Call<ResponseBody> getUrl(@Query("index") long index);
    // //http://192.168.196.2:8080/GooglePlayServer/home?index=0
    //
    // @GET("home")
    // Call<HomeInfoBean> getUrl1(@Query("index") long index);
    //
    // @GET("{home}")
    // Observable<HomeInfoBean> getHomeInfo(@Path("home") String home, @Query("index") int index);
    // //http://192.168.196.2:8080/GooglePlayServer/home?index=0

    // /*获得指定id的长评论*/
    // @GET("4/story/{id}/long-comments/{userId}")
    // Observable<CommentListBean> getLongCommentMore(@Path("id") long id, @Path("userId") long userId);
    // //http://news-at.zhihu.com/api/4/story/4232852/long-comments

    // /*获得指定栏目之前的新闻*/
    // @GET("4/section/{id}/before/{timestamp}")
    // Observable<StoriesBeforeBean> getSectionNewsBefore(@Path("id") long id, @Path("timestamp") long date);
    // //http://news-at.zhihu.com/api/4/section/#{section id}/before/#{timestamp}
}
