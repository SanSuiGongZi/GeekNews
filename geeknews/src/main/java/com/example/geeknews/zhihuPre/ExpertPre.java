package com.example.geeknews.zhihuPre;

import com.example.geeknews.base.BasePres;
import com.example.geeknews.bean.SuperDaily;
import com.example.geeknews.bean.SuperExpert;
import com.example.geeknews.callback.CallBacksDaily;
import com.example.geeknews.callback.CallBacksExpert;
import com.example.geeknews.zhihuModel.ExpertModel;
import com.example.geeknews.zhihyView.BaseDaily;
import com.example.geeknews.zhihyView.BaseExpert;

public class ExpertPre extends BasePres<BaseExpert> {

    private ExpertModel expertModel;

    @Override
    protected void initModel() {
        expertModel = new ExpertModel();
    }

    public void getData(){
        expertModel.expert(new CallBacksExpert() {
            @Override
            public void set(SuperExpert superExpert) {
                if (superExpert!=null){
                    if (mBaseView!=null){
                        mBaseView.setExpert(superExpert);
                    }
                }
            }
        });
    }

}
