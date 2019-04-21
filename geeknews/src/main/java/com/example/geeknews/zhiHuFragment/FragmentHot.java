package com.example.geeknews.zhiHuFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapter.MyAdapterHot;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.bean.SuperHot;
import com.example.geeknews.zhihuPre.HotPre;
import com.example.geeknews.zhihyView.BaseHot;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHot extends BaseFramen<BaseHot, HotPre> implements BaseHot {

    @BindView(R.id.rv_Hot)
    RecyclerView rvHot;
    private ArrayList<SuperHot.RecentBean> list;
    private MyAdapterHot myAdapterHot;

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_hot;
    }

    @Override
    protected HotPre initFragPre() {
        return new HotPre();
    }

    @Override
    protected void initView() {
        rvHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        myAdapterHot = new MyAdapterHot(list, getActivity());
        rvHot.setAdapter(myAdapterHot);
    }

    @Override
    protected void initData() {
        mPresentFrag.getData();
    }

    @Override
    public void setHot(SuperHot superHot) {
        list.addAll(superHot.getRecent());
        myAdapterHot.notifyDataSetChanged();
    }
}
