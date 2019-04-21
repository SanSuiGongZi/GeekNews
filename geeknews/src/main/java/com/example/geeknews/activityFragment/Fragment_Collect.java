package com.example.geeknews.activityFragment;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.presenter.CollectPT;
import com.example.geeknews.view.CollectView;

public class Fragment_Collect extends BaseFramen<CollectView,CollectPT> implements CollectView {
    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_collect;
    }

    @Override
    protected CollectPT initFragPre() {
        return new CollectPT();
    }
}
