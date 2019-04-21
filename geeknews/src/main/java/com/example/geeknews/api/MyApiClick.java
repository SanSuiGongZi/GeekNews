package com.example.geeknews.api;

import com.example.geeknews.bean.HotDemo;
import com.example.geeknews.bean.SuperDaily;
import com.example.geeknews.bean.SuperExpert;
import com.example.geeknews.bean.SuperHot;
import com.example.geeknews.bean.SuperWeChat;
import com.example.geeknews.bean.ZhiHuZhuanLanDemo;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MyApiClick {

    String url = "http://news-at.zhihu.com/api/4/";

    //日报
    @GET("news/latest")
    Observable<SuperDaily> getDaily();

    //往期日报
    @GET("news/before/{date}")
    Observable<SuperDaily> getPast(@Path("date") String date);

    //专栏
    @GET("sections")
    Observable<SuperExpert> getExpert();
    //专栏详情
    @GET("section/{id}")
    Observable<ZhiHuZhuanLanDemo> getDemo(@Path("id") int id);

    //热门
    @GET("news/hot")
    Observable<SuperHot> getHot();
    //热门详情
    @GET("news/{news_id}")
    Observable<HotDemo> hot(@Path("news_id") int id);


    //微信

    String HOST = "http://api.tianapi.com/";

    //初始数据
    @GET("wxnew")
    Observable<SuperWeChat> weChatDa(@QueryMap() HashMap<String , String> map);
}
