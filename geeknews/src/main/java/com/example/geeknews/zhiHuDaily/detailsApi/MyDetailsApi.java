package com.example.geeknews.zhiHuDaily.detailsApi;

import com.example.geeknews.zhiHuDaily.bean.SuperDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyDetailsApi {

    String url = "http://news-at.zhihu.com/api/4/";

    @GET("news/{news_id}")
    Observable<SuperDetails> getDateils(@Path("news_id") int id);
}
