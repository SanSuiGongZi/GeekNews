package com.example.geeknews.zhihuPre;

import com.example.geeknews.base.BasePres;
import com.example.geeknews.bean.SuperHot;
import com.example.geeknews.callback.CallBacksHot;
import com.example.geeknews.zhihuModel.HotModel;
import com.example.geeknews.zhihyView.BaseDaily;
import com.example.geeknews.zhihyView.BaseHot;

public class HotPre extends BasePres<BaseHot> {

    private HotModel hotModel;

    @Override
    protected void initModel() {
        hotModel = new HotModel();
    }

    public void getData(){
        hotModel.hot(new CallBacksHot() {
            @Override
            public void set(SuperHot superHot) {
                if (superHot!=null){
                    if (mBaseView!=null){
                        mBaseView.setHot(superHot);
                    }
                }
            }
        });
    }
}
