package com.example.geeknews.presenter;

import com.example.geeknews.base.BasePres;
import com.example.geeknews.bean.SuperWeChat;
import com.example.geeknews.callback.WeChatCallbcak;
import com.example.geeknews.model.WeChatModel;
import com.example.geeknews.view.AboutView;
import com.example.geeknews.view.WeChatView;

public class WeChatPT extends BasePres<WeChatView> {

    private WeChatModel mWeChatModel;

    @Override
    protected void initModel() {
        mWeChatModel = new WeChatModel();
    }

    public void getData() {
        mWeChatModel.Data(new WeChatCallbcak() {
            @Override
            public void weChat(SuperWeChat superWeChat) {
                if (superWeChat != null) {
                    if (mBaseView != null) {
                        mBaseView.setData(superWeChat);
                    }
                }
            }

            @Override
            public void weChats(SuperWeChat superWeChat) {

            }
        });
    }

    public void getDataSearch(String word) {
        mWeChatModel.Datas(new WeChatCallbcak() {
            @Override
            public void weChat(SuperWeChat superWeChat) {

            }

            @Override
            public void weChats(SuperWeChat superWeChat) {
                if (superWeChat != null) {
                    if (mBaseView != null) {
                        mBaseView.getData(superWeChat);
                    }
                }
            }
        }, word);
    }

}
