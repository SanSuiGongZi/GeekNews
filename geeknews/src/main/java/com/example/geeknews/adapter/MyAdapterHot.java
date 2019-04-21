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
import com.example.geeknews.activity.HotDemoActivity;
import com.example.geeknews.bean.SuperExpert;
import com.example.geeknews.bean.SuperHot;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterHot extends RecyclerView.Adapter<MyAdapterHot.ViewHolder> {

    private ArrayList<SuperHot.RecentBean> list;
    private Context context;

    public MyAdapterHot(ArrayList<SuperHot.RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotlist, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final SuperHot.RecentBean bean = list.get(i);
        viewHolder.tv1.setText(bean.getTitle());
        Glide.with(context).load(bean.getThumbnail()).into(viewHolder.iv);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HotDemoActivity.class);
                intent.putExtra("id" ,bean.getNews_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView iv;
        @BindView(R.id.tv_hot)
        TextView tv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
