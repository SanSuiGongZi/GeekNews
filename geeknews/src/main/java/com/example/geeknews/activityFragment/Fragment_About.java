package com.example.geeknews.activityFragment;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.presenter.AboutPT;
import com.example.geeknews.view.AboutView;

public class Fragment_About extends BaseFramen<AboutView,AboutPT> implements AboutView {
    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_about;
    }

    @Override
    protected AboutPT initFragPre() {
        return new AboutPT();
    }
}
