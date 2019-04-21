package com.example.geeknews.zhihuModel;

import android.util.Log;

import com.example.geeknews.api.MyApiClick;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.SuperDaily;
import com.example.geeknews.callback.CallBacksDaily;
import com.example.geeknews.callback.CallBacksDailyPast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyModel extends BaseModel {

    private static final String TAG = "DailyModel";

    public void getSuper(final CallBacksDaily callBacksDaily){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApiClick.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyApiClick apiClick = retrofit.create(MyApiClick.class);
        Observable<SuperDaily> daily = apiClick.getDaily();
        daily.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SuperDaily>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: "+d.toString() );
                    }

                    @Override
                    public void onNext(SuperDaily superDaily) {
                        callBacksDaily.Data(superDaily);
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


    public void Past(String data , final CallBacksDailyPast callBacksDailyPast){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApiClick.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyApiClick apiClick = retrofit.create(MyApiClick.class);
        Observable<SuperDaily> past = apiClick.getPast(data);
        past.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SuperDaily>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: "+d.toString() );
                    }

                    @Override
                    public void onNext(SuperDaily superDaily) {
                        callBacksDailyPast.Data(superDaily);
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
