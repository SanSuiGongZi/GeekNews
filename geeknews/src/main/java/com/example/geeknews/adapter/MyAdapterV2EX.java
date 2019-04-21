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
import com.example.geeknews.bean.SuperV2EX;
import com.example.geeknews.bean.SuperWeChat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterV2EX extends RecyclerView.Adapter<MyAdapterV2EX.ViewHolder> {

    private Context context;
    private ArrayList<SuperV2EX> list;

    public MyAdapterV2EX(Context context, ArrayList<SuperV2EX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_v2ex, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SuperV2EX superV2EX = list.get(i);
        viewHolder.tvV2ex1.setText(superV2EX.getTitle());
        viewHolder.tvV2ex2.setText(superV2EX.getStr());
        viewHolder.tvV2exNum.setText(superV2EX.getNum());
        Glide.with(context).load("https:"+superV2EX.getImg()).into(viewHolder.ivV2ex);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_v2ex)
        ImageView ivV2ex;
        @BindView(R.id.tv_v2ex1)
        TextView tvV2ex1;
        @BindView(R.id.tv_v2ex2)
        TextView tvV2ex2;
        @BindView(R.id.tv_v2exNum)
        TextView tvV2exNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
