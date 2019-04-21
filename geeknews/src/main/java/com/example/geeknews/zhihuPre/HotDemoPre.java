package com.example.geeknews.zhihuPre;

import com.example.geeknews.base.BasePres;
import com.example.geeknews.bean.HotDemo;
import com.example.geeknews.callback.CallBacksHotDemo;
import com.example.geeknews.zhihuModel.BaseHouDemoModel;
import com.example.geeknews.zhihyView.BaseHotDemo;

public class HotDemoPre extends BasePres<BaseHotDemo> {

    private BaseHouDemoModel model;

    @Override
    protected void initModel() {
        model = new BaseHouDemoModel();
    }

    public void getDemo(int id) {
        model.hotDemo(new CallBacksHotDemo() {
            @Override
            public void getHotDemo(HotDemo hotDemo) {
                if (hotDemo!=null){
                    if (mBaseView!=null){
                        mBaseView.setDemo(hotDemo);
                    }
                }
            }
        }, id);
    }

}
