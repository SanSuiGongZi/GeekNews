package com.example.geeknews.zhiHuDaily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.api.MyApiClick;
import com.example.geeknews.zhiHuDaily.bean.SuperDetails;
import com.example.geeknews.zhiHuDaily.detailsApi.MyDetailsApi;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyDetails extends AppCompatActivity {

    private static final String TAG = "DailyDetails";
    private ImageView iv_details;
    private TextView tv_detailsTitle;
    private TextView tv_detailsStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_details);
        initView();
        initData();
    }

    private void initView() {
        iv_details = (ImageView) findViewById(R.id.iv_details);
        tv_detailsTitle = (TextView) findViewById(R.id.tv_detailsTitle);
        tv_detailsStr = (TextView) findViewById(R.id.tv_detailsStr);
    }

    private void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyDetailsApi.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyDetailsApi detailsApi = retrofit.create(MyDetailsApi.class);
        Observable<SuperDetails> dateils = detailsApi.getDateils(id);
        dateils.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SuperDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(SuperDetails superDetails) {
                        String body = superDetails.getBody();
                        String title = superDetails.getTitle();
                        String s = superDetails.getImages().get(0);
                        tv_detailsTitle.setText(title);
                        tv_detailsStr.setText(body);
                        if (s != null) {
                            Glide.with(DailyDetails.this).load(s).into(iv_details);
                        }
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
