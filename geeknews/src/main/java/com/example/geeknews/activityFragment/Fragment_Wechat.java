package com.example.geeknews.activityFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.adapter.MyAdapterWeChat;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.bean.SuperWeChat;
import com.example.geeknews.presenter.WeChatPT;
import com.example.geeknews.view.WeChatView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_Wechat extends BaseFramen<WeChatView, WeChatPT> implements WeChatView {

    @BindView(R.id.rv_weChat)
    RecyclerView mRvWeChat;
    private ArrayList<SuperWeChat.NewslistBean> list;
    private MyAdapterWeChat weChat;

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_wechats;
    }

    @Override
    protected WeChatPT initFragPre() {
        return new WeChatPT();
    }

    @Override
    protected void initView() {
        mRvWeChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        weChat = new MyAdapterWeChat(getActivity(), list);
        mRvWeChat.setAdapter(weChat);
    }

    public void setSearchData(String str){
        setStr(str);
    }

    public void setStr(String name){
        mPresentFrag.getDataSearch(name);
    }

    @Override
    protected void initData() {
        mPresentFrag.getData();
    }

    @Override
    public void setData(SuperWeChat superWeChat) {
        list.addAll(superWeChat.getNewslist());
        weChat.notifyDataSetChanged();
    }

    @Override
    public void getData(SuperWeChat superWeChat) {
        if (superWeChat!=null){
            list.clear();
            list.addAll(superWeChat.getNewslist());
            weChat.notifyDataSetChanged();
        }else {
            Toast.makeText(getActivity(), "没有该关键字对应的数据", Toast.LENGTH_SHORT).show();
        }
    }
}
