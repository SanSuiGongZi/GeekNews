package com.example.geeknews.presenter;

import com.example.geeknews.base.BasePres;
import com.example.geeknews.model.IModel;
import com.example.geeknews.view.IView;

public class IPresenter extends BasePres<IView> {


    protected IModel iModel;

    @Override
    protected void initModel() {
        iModel = new IModel();
    }
}
