package com.example.geeknews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFramen<V extends BaseView , P extends BasePres> extends Fragment implements BaseView {

    private Unbinder bind;
    protected P mPresentFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutFragID(), null);
        bind = ButterKnife.bind(this, view);
        mPresentFrag=initFragPre();
        if (mPresentFrag!=null){
            mPresentFrag.bind((V)this);
        }
        initView();
        initFragment();
        initData();
        initLister();
        return view;
    }

    protected void initFragment() {

    }

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initLister() {

    }

    protected abstract int getLayoutFragID();

    protected abstract P initFragPre();
}
