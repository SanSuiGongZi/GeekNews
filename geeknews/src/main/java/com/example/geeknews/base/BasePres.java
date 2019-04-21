package com.example.geeknews.base;

public abstract class BasePres<V extends BaseView> {

    protected V mBaseView;

    public BasePres() {
        initModel();
    }

    protected abstract void initModel();



    public void bind(V v){
        this.mBaseView=v;
    }
}
