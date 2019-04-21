package com.example.geeknews.zhiHuFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.activity.MyDateGald;
import com.example.geeknews.activityFragment.Fragment_zhihu;
import com.example.geeknews.adapter.MyAdapterDaily;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.bean.SuperDaily;
import com.example.geeknews.zhihuPre.DailyPre;
import com.example.geeknews.zhihyView.BaseDaily;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDaily extends BaseFramen<BaseDaily, DailyPre> implements BaseDaily {


    private static final String TAG = "FragmentDaily";
    @BindView(R.id.rv_Daily)
    RecyclerView rvDaily;
    @BindView(R.id.fab_ZhiHu)
    FloatingActionButton mFabZhiHu;
    private ArrayList<SuperDaily.StoriesBean> dailyList;
    private ArrayList<SuperDaily.TopStoriesBean> dailyBann;
    private MyAdapterDaily daily;
    private String newData = "..";

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_daily;
    }

    @Override
    protected DailyPre initFragPre() {
        return new DailyPre();
    }

    @Override
    protected void initView() {
        rvDaily.setLayoutManager(new LinearLayoutManager(getActivity()));
        dailyList = new ArrayList<>();
        dailyBann = new ArrayList<>();
        daily = new MyAdapterDaily(dailyList, dailyBann, getActivity());
        rvDaily.setAdapter(daily);

    }

    @Override
    protected void initData() {
        mPresentFrag.getData();

    }

    @Override
    public void getDaily(SuperDaily superDaily) {
        dailyBann.addAll(superDaily.getTop_stories());
        dailyList.addAll(superDaily.getStories());
        daily.notifyDataSetChanged();

    }

    @Override
    public void getPast(SuperDaily superDaily) {
        dailyBann.clear();
        dailyList.clear();
        dailyList.addAll(superDaily.getStories());
        daily.notifyDataSetChanged();
    }


    @Override
    protected void initLister() {
        mFabZhiHu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "哈哈哈", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MyDateGald.class);
                startActivityForResult(intent, 1);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            newData = data.getStringExtra("date");
            Log.e(TAG, "onActivityResult: "+newData );
            mPresentFrag.getDatas(newData);
            daily.setText(newData);
        }
    }
}
