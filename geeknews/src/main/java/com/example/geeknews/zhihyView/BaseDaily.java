package com.example.geeknews.zhihyView;

import com.example.geeknews.base.BaseView;
import com.example.geeknews.bean.SuperDaily;

public interface BaseDaily extends BaseView {

    void getDaily(SuperDaily superDaily);
    void getPast(SuperDaily superDaily);

}
