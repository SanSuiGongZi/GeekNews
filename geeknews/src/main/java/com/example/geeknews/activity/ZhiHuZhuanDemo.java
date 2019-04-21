package com.example.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.MyAdapterDemo;
import com.example.geeknews.adapter.MyAdapterExpert;
import com.example.geeknews.adapter.MyAdapterHot;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.bean.ZhiHuZhuanLanDemo;
import com.example.geeknews.zhihuPre.ZhiHuDemoPre;
import com.example.geeknews.zhihyView.BaseZhiHuDemo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhiHuZhuanDemo extends BaseActivity<BaseZhiHuDemo, ZhiHuDemoPre> implements BaseZhiHuDemo {

    @BindView(R.id.rv_ZhiHuDemo)
    RecyclerView rvZhiHuDemo;
    private ArrayList<ZhiHuZhuanLanDemo.StoriesBean> list;
    private MyAdapterDemo adapterHot;

    @Override
    protected ZhiHuDemoPre initPresenter() {
        return new ZhiHuDemoPre();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_zhi_hu_zhuan_demo;
    }

    @Override
    protected void initView() {
        rvZhiHuDemo.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapterHot = new MyAdapterDemo(list,this);
        rvZhiHuDemo.setAdapter(adapterHot);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        mPresenter.setZhiHu(id);
    }

    @Override
    public void setDemo(ZhiHuZhuanLanDemo zhiHuZhuanLanDemo) {
        list.addAll(zhiHuZhuanLanDemo.getStories());
        adapterHot.notifyDataSetChanged();
    }
}
