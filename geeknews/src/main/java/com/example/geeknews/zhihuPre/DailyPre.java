package com.example.geeknews.zhihuPre;

import com.example.geeknews.base.BasePres;
import com.example.geeknews.bean.SuperDaily;
import com.example.geeknews.callback.CallBacksDaily;
import com.example.geeknews.callback.CallBacksDailyPast;
import com.example.geeknews.zhihuModel.DailyModel;
import com.example.geeknews.zhihyView.BaseDaily;

public class DailyPre extends BasePres<BaseDaily> {

    private DailyModel dailyModel;

    @Override
    protected void initModel() {
        dailyModel = new DailyModel();

    }

    public void getData() {
        dailyModel.getSuper(new CallBacksDaily() {
            @Override
            public void Data(SuperDaily superDaily) {
                if (superDaily != null) {
                    if (mBaseView != null) {
                        mBaseView.getDaily(superDaily);
                    }
                }
            }
        });
    }

    public void getDatas(String data) {
        dailyModel.Past(data, new CallBacksDailyPast() {
            @Override
            public void Data(SuperDaily superDaily) {
                if (mBaseView != null) {
                    mBaseView.getPast(superDaily);
                }
            }
        });
    }
}
