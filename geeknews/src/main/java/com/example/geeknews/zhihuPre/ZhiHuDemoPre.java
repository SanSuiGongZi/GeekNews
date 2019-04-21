package com.example.geeknews.zhihuPre;

import com.example.geeknews.activity.ZhiHuZhuanDemo;
import com.example.geeknews.base.BasePres;
import com.example.geeknews.bean.ZhiHuZhuanLanDemo;
import com.example.geeknews.callback.CallBacksDemo;
import com.example.geeknews.zhihuModel.ZhiHuDemoModel;
import com.example.geeknews.zhihyView.BaseZhiHuDemo;

public class ZhiHuDemoPre extends BasePres<BaseZhiHuDemo> {

    private ZhiHuDemoModel zhiHuDemoModel;

    @Override
    protected void initModel() {
        zhiHuDemoModel = new ZhiHuDemoModel();
    }

    public void setZhiHu(int id){
        zhiHuDemoModel.getDemo(new CallBacksDemo() {
            @Override
            public void Demo(ZhiHuZhuanLanDemo zhiHuZhuanLanDemo) {
                if (zhiHuZhuanLanDemo!=null){
                    if (mBaseView!=null){
                        mBaseView.setDemo(zhiHuZhuanLanDemo);
                    }
                }
            }
        }, id);
    }

}
