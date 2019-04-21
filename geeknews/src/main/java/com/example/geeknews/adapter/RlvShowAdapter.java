package com.example.geeknews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.bean.GoldTitleBean;
import com.example.geeknews.widget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvShowAdapter extends RecyclerView.Adapter<RlvShowAdapter.ViewHolder>
implements TouchCallBack {

    private Context context;
    private ArrayList<GoldTitleBean> mTitles;

    public RlvShowAdapter(Context context, ArrayList<GoldTitleBean> mTitles) {
        this.context = context;
        this.mTitles = mTitles;
    }

    @NonNull
    @Override
    public RlvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_show, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RlvShowAdapter.ViewHolder viewHolder, int i) {
        ViewHolder holder = viewHolder;
        final GoldTitleBean bean = mTitles.get(i);
        holder.mTv.setText(bean.title);
        holder.mSc.setChecked(bean.isChecked);
        holder.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换集合中连个数据的位置
        Collections.swap(mTitles,fromPosition,toPosition);
        //刷新界面,局部刷新,索引会混乱
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        mTitles.remove(position);
        //局部刷新,索引会混乱+集合越界
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        TextView mTv;
        @BindView(R.id.sc)
        SwitchCompat mSc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
