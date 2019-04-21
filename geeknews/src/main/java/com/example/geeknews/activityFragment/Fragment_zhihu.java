package com.example.geeknews.activityFragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.geeknews.R;
import com.example.geeknews.adapter.MyAdapter_vp;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.presenter.ZhiHuPT;
import com.example.geeknews.view.ZhiHuView;
import com.example.geeknews.zhiHuFragment.FragmentDaily;
import com.example.geeknews.zhiHuFragment.FragmentExpert;
import com.example.geeknews.zhiHuFragment.FragmentHot;
import com.example.geeknews.zhiHuFragment.FragmentTheme;

import java.util.ArrayList;

import butterknife.BindView;

public class Fragment_zhihu extends BaseFramen<ZhiHuView, ZhiHuPT> implements ZhiHuView {

    @BindView(R.id.tab_zhiHu)
    TabLayout tabZhiHu;
    @BindView(R.id.vp_ZhiHu)
    ViewPager vpZhiHu;
    private ArrayList<BaseFramen> list;
    private MyAdapter_vp adapter_vp;

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected ZhiHuPT initFragPre() {
        return new ZhiHuPT();
    }

    @Override
    protected void initView() {
        tabZhiHu.addTab(tabZhiHu.newTab().setText("日报"));
        tabZhiHu.addTab(tabZhiHu.newTab().setText("主题"));
        tabZhiHu.addTab(tabZhiHu.newTab().setText("专栏"));
        tabZhiHu.addTab(tabZhiHu.newTab().setText("热门"));

        list = new ArrayList<>();
        list.add(new FragmentDaily());
        list.add(new FragmentTheme());
        list.add(new FragmentExpert());
        list.add(new FragmentHot());
        FragmentManager cfm = getChildFragmentManager();
        adapter_vp = new MyAdapter_vp(cfm, list);
        vpZhiHu.setAdapter(adapter_vp);
        vpZhiHu.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabZhiHu));
        tabZhiHu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpZhiHu.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
