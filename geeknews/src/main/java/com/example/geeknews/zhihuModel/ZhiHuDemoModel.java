package com.example.geeknews.zhihuModel;

import android.util.Log;

import com.example.geeknews.api.MyApiClick;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.ZhiHuZhuanLanDemo;
import com.example.geeknews.callback.CallBacksDemo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhiHuDemoModel extends BaseModel {

    private static final String TAG = "ZhiHuDemoModel";

    public void getDemo(final CallBacksDemo callBacksDemo, int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApiClick.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyApiClick apiClick = retrofit.create(MyApiClick.class);
        Observable<ZhiHuZhuanLanDemo> clickDemo = apiClick.getDemo(id);
        clickDemo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuZhuanLanDemo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(ZhiHuZhuanLanDemo zhiHuZhuanLanDemo) {
                        callBacksDemo.Demo(zhiHuZhuanLanDemo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }

}
