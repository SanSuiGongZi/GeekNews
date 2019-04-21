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
import com.example.geeknews.bean.SuperDaily;
import com.example.geeknews.zhiHuDaily.DailyDetails;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAdapterDaily extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ZERO = 0;
    private int ONE = 1;
    private int TWO = 2;
    private ArrayList<SuperDaily.StoriesBean> listList;
    private ArrayList<SuperDaily.TopStoriesBean> listBann;
    private Context context;
    private int newPostion;
    private String text = "..";

    public MyAdapterDaily(ArrayList<SuperDaily.StoriesBean> listList,
                          ArrayList<SuperDaily.TopStoriesBean> listBann,
                          Context context) {
        this.listList = listList;
        this.listBann = listBann;
        this.context = context;
    }

    public void  setText(String str){
        if (str!= null){
            text=str;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (i == ZERO) {
            View view = inflater.inflate(R.layout.item_dailybann, null);
            ViewHolderBann bann = new ViewHolderBann(view);
            return bann;
        } else if (i == ONE) {
            View view = inflater.inflate(R.layout.item_dailytext, null);
            ViewHolderText text = new ViewHolderText(view);
            return text;
        } else {

            View view = inflater.inflate(R.layout.item_dailycardlist, null);
            ViewHolderCard card = new ViewHolderCard(view);
            return card;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        if (type == ZERO) {
            ViewHolderBann holder = (ViewHolderBann) viewHolder;
            holder.banns.setImages(listBann);
            holder.banns.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    SuperDaily.TopStoriesBean bean = (SuperDaily.TopStoriesBean) path;
                    Glide.with(context).load(bean.getImage()).into(imageView);
                }
            }).start();
        } else if (type == ONE) {
            ViewHolderText holder = (ViewHolderText) viewHolder;


                if (text.equals("..")){
                    holder.tv.setText("今日热闻");
                }else {
                    holder.tv.setText(text);
                }


        } else {

            newPostion = i - 1;
            if (listBann.size() > 0) {
                newPostion -= 1;
            }

            ViewHolderCard holder = (ViewHolderCard) viewHolder;
            final SuperDaily.StoriesBean storiesBean = listList.get(newPostion);
            holder.tv_card.setText(storiesBean.getTitle());
            Glide.with(context).load(storiesBean.getImages().get(0)).into(holder.iv);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DailyDetails.class);
                    intent.putExtra("id", storiesBean.getId());
                    context.startActivity(intent);
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        if (listBann.size() > 0) {
            return 1 + 1 + listList.size();
        } else {
            return 1 + listList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (listBann.size() > 0) {
            if (position == 0) {
                return ZERO;
            } else if (position == 1) {
                return ONE;
            } else {
                return TWO;
            }
        } else {
            if (position == 0) {
                return ONE;
            } else {
                return TWO;
            }
        }
    }

    class ViewHolderBann extends RecyclerView.ViewHolder {

        @BindView(R.id.banns)
        Banner banns;

        public ViewHolderBann(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolderText extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_daily)
        TextView tv;

        public ViewHolderText(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolderCard extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_daily)
        ImageView iv;
        @BindView(R.id.tv_dailyCard)
        TextView tv_card;

        public ViewHolderCard(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick {
        void onClick(SuperDaily.StoriesBean bean, int position);
    }

}
