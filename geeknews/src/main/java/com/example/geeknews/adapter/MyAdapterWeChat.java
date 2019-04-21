package com.example.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.bean.SuperWeChat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterWeChat extends RecyclerView.Adapter<MyAdapterWeChat.ViewHolder> {

    private Context context;
    private ArrayList<SuperWeChat.NewslistBean> list;

    public MyAdapterWeChat(Context context,
                           ArrayList<SuperWeChat.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wechat, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SuperWeChat.NewslistBean bean = list.get(i);
        viewHolder.tvWeChat1.setText(bean.getTitle());
        viewHolder.tvWeChat2.setText(bean.getDescription());
        viewHolder.tvWeChatData.setText(bean.getCtime());
        Glide.with(context).load(bean.getPicUrl()).into(viewHolder.ivWeChat);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_weChat)
        ImageView ivWeChat;
        @BindView(R.id.tv_weChat1)
        TextView tvWeChat1;
        @BindView(R.id.tv_weChat2)
        TextView tvWeChat2;
        @BindView(R.id.tv_weChatData)
        TextView tvWeChatData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
