package com.example.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvShowAdapter;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.base.Constants;
import com.example.geeknews.bean.GoldTitleBean;
import com.example.geeknews.presenter.EmptyP;
import com.example.geeknews.view.EmptyV;
import com.example.geeknews.widget.SimpleItemTouchCallBack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetTabDataActivity extends BaseActivity<EmptyV, EmptyP> implements EmptyV {

    @BindView(R.id.tv_tool_goldData)
    Toolbar toolGoldData;
    @BindView(R.id.rv_gold)
    RecyclerView rvGold;
    private ArrayList<GoldTitleBean> mTitles;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_set_tabdata;
    }

    @Override
    protected void initView() {
        toolGoldData.setTitle(R.string.special_show);
        toolGoldData.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(toolGoldData);
        toolGoldData.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });

        mTitles = (ArrayList<GoldTitleBean>) getIntent().getSerializableExtra(Constants.DATA);
        rvGold.setLayoutManager(new LinearLayoutManager(this));
        RlvShowAdapter adapter = new RlvShowAdapter(this,mTitles);
        rvGold.setAdapter(adapter);
        rvGold.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //拖拽移动和左滑删除
        SimpleItemTouchCallBack simpleItemTouchCallBack = new SimpleItemTouchCallBack(adapter);
        //取消左滑删除
        simpleItemTouchCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchCallBack);
        helper.attachToRecyclerView(rvGold);


    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA, mTitles);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }
}
