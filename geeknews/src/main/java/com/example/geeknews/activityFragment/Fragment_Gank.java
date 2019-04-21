package com.example.geeknews.activityFragment;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.presenter.GankPT;
import com.example.geeknews.view.GankView;

public class Fragment_Gank extends BaseFramen<GankView,GankPT> implements GankView {
    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_gank;
    }

    @Override
    protected GankPT initFragPre() {
        return new GankPT();
    }

    public static void setSearchs(String str){

    }

}
