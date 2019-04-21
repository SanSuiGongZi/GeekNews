package com.example.geeknews.view;

import com.example.geeknews.base.BaseView;
import com.example.geeknews.bean.SuperWeChat;

public interface WeChatView extends BaseView {

    void setData(SuperWeChat superWeChat);

    void getData(SuperWeChat superWeChat);

}
