package com.example.geeknews.zhihuModel;

import android.util.Log;

import com.example.geeknews.api.MyApiClick;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.SuperExpert;
import com.example.geeknews.callback.CallBacksExpert;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpertModel extends BaseModel {

    private static final String TAG = "ExpertModel";

    public void expert(final CallBacksExpert callBacksExpert){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApiClick.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyApiClick apiClick = retrofit.create(MyApiClick.class);
        Observable<SuperExpert> expert = apiClick.getExpert();
        expert.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SuperExpert>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: "+d.toString() );
                    }

                    @Override
                    public void onNext(SuperExpert superExpert) {
                        callBacksExpert.set(superExpert);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " );
                    }
                });
    }

}
