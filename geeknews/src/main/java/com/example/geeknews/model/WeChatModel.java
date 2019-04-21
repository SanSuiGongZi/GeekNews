package com.example.geeknews.model;

import android.util.Log;

import com.example.geeknews.api.MyApiClick;
import com.example.geeknews.base.BaseModel;
import com.example.geeknews.bean.SuperWeChat;
import com.example.geeknews.callback.WeChatCallbcak;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatModel extends BaseModel {

    private String key = "52b7ec3471ac3bec6846577e79f20e4c";
    private int num = 10;
    private int page = 1;
    private static final String TAG = "WeChatModel";

    public void Data(final WeChatCallbcak chatCallbcak) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApiClick.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key",key);
        hashMap.put("num",num+"");
        hashMap.put("page",page+"");

        MyApiClick apiClick = retrofit.create(MyApiClick.class);
        Observable<SuperWeChat> chatDa = apiClick.weChatDa(hashMap);
        chatDa.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SuperWeChat>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: "+d.getClass() );
                    }

                    @Override
                    public void onNext(SuperWeChat superWeChat) {
                        chatCallbcak.weChat(superWeChat);
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


    public void Datas(final WeChatCallbcak chatCallbcak,String word) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApiClick.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key",key);
        hashMap.put("num",num+"");
        hashMap.put("page",page+"");
        hashMap.put("word",word);

        MyApiClick apiClick = retrofit.create(MyApiClick.class);
        Observable<SuperWeChat> weChatDa = apiClick.weChatDa(hashMap);
        weChatDa.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SuperWeChat>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: "+d.toString() );
                    }

                    @Override
                    public void onNext(SuperWeChat superWeChat) {
                        chatCallbcak.weChats(superWeChat);
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
