package com.example.geeknews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.activity.ZhiHuZhuanDemo;
import com.example.geeknews.bean.SuperExpert;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterExpert extends RecyclerView.Adapter<MyAdapterExpert.ViewHolder> {

    private ArrayList<SuperExpert.DataBean> list;
    private Context context;

    public MyAdapterExpert(ArrayList<SuperExpert.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expertlist, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final SuperExpert.DataBean dataBean = list.get(i);
        viewHolder.tv1.setText(dataBean.getDescription());
        viewHolder.tv2.setText(dataBean.getName());
        Glide.with(context).load(dataBean.getThumbnail()).into(viewHolder.iv);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhiHuZhuanDemo.class);
                intent.putExtra("id",dataBean.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_expert)
        ImageView iv;
        @BindView(R.id.tv_expertName)
        TextView tv1;
        @BindView(R.id.tv_expertTitle)
        TextView tv2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
