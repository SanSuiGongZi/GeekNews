package com.example.geeknews.activityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.activity.SetTabDataActivity;
import com.example.geeknews.adapter.MyAdapterGold_Vp;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.base.Constants;
import com.example.geeknews.bean.GoldTitleBean;
import com.example.geeknews.presenter.GoldPT;
import com.example.geeknews.view.GoldView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment_Gold extends BaseFramen<GoldView, GoldPT> implements GoldView {

    @BindView(R.id.tab_gold)
    TabLayout tabGold;
    @BindView(R.id.iv_gold)
    ImageView ivGold;
    @BindView(R.id.vp_gold)
    ViewPager vpGold;
    private ArrayList<GoldTitleBean> mTitles;
    private ArrayList<BaseFramen> list;

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_gold;
    }

    @Override
    protected GoldPT initFragPre() {
        return new GoldPT();
    }

    @Override
    protected void initView() {
        initTitles();
        setFragment();
    }


    @OnClick({R.id.iv_gold})
    public void click(View v){
        switch (v.getId()) {
            case R.id.iv_gold:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), SetTabDataActivity.class);
        intent.putExtra(Constants.DATA,mTitles);
        startActivityForResult(intent,100);
    }

    @Override
    protected void initFragment() {
        list = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            GoldTitleBean goldTitleBean = mTitles.get(i);
            if (goldTitleBean.isChecked){
                list.add(FragmentSetData.newInstance(goldTitleBean.title));
            }
        }
    }

    private void setFragment() {
        initFragment();
        FragmentManager fm = getChildFragmentManager();
        MyAdapterGold_Vp gold_vp = new MyAdapterGold_Vp(fm, list, mTitles);
        vpGold.setAdapter(gold_vp);
        tabGold.setupWithViewPager(vpGold);
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(new GoldTitleBean("Android",true));
        mTitles.add(new GoldTitleBean("iOS",true));
        mTitles.add(new GoldTitleBean("设计",true));
        mTitles.add(new GoldTitleBean("工具资源",false));
        mTitles.add(new GoldTitleBean("产品",true));
        mTitles.add(new GoldTitleBean("阅读",true));
        mTitles.add(new GoldTitleBean("前端",true));
        mTitles.add(new GoldTitleBean("后端",true));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                mTitles = (ArrayList<GoldTitleBean>) data.getSerializableExtra(Constants.DATA);
                setFragment();
            }
        }
    }

}
