package com.example.geeknews.zhiHuFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapter.MyAdapterExpert;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.bean.SuperExpert;
import com.example.geeknews.zhihuPre.ExpertPre;
import com.example.geeknews.zhihyView.BaseExpert;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExpert extends BaseFramen<BaseExpert, ExpertPre> implements BaseExpert {

    @BindView(R.id.rv_Expert)
    RecyclerView rvExpert;
    private ArrayList<SuperExpert.DataBean> list;
    private MyAdapterExpert adapterExpert;

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_expert;
    }

    @Override
    protected ExpertPre initFragPre() {
        return new ExpertPre();
    }

    @Override
    protected void initView() {
        rvExpert.setLayoutManager(new GridLayoutManager(getActivity(),2));
        list = new ArrayList<>();
        adapterExpert = new MyAdapterExpert(list, getActivity());
        rvExpert.setAdapter(adapterExpert);
    }

    @Override
    protected void initData() {
        mPresentFrag.getData();
    }

    @Override
    public void setExpert(SuperExpert superExpert) {
        list.addAll(superExpert.getData());
        adapterExpert.notifyDataSetChanged();
    }
}
