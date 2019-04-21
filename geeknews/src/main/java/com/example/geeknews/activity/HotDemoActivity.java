package com.example.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.bean.HotDemo;
import com.example.geeknews.zhihuPre.HotDemoPre;
import com.example.geeknews.zhihyView.BaseHotDemo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotDemoActivity extends BaseActivity<BaseHotDemo, HotDemoPre> implements BaseHotDemo {

    @BindView(R.id.web)
    WebView web;

    @Override
    protected HotDemoPre initPresenter() {
        return new HotDemoPre();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_hot_demo;
    }

    @Override
    protected void initView() {
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        mPresenter.getDemo(id);
    }

    @Override
    public void setDemo(HotDemo hotDemo) {
        String url = hotDemo.getShare_url();
        web.loadUrl(url);
    }
}
