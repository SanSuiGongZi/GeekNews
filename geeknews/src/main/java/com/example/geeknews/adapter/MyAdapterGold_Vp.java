package com.example.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.bean.GoldTitleBean;

import java.util.ArrayList;


/**
 * @author xts
 *         Created by asus on 2019/4/17.
 *         生命周期不一样
 *         FragmentStatePagerAdapter:用不着的碎片生命周期,onDetach();取消关联
 *         FragmentPagerAdapter:用不着的碎片生命周期,只会走到onDestoryView();
 *
 */

public class MyAdapterGold_Vp extends FragmentStatePagerAdapter {

    private ArrayList<BaseFramen> listFragment;
    private ArrayList<GoldTitleBean> listTitle;
    private ArrayList<String> newTitle = new ArrayList<>();

    public MyAdapterGold_Vp(FragmentManager fm, ArrayList<BaseFramen> listFragment,
                            ArrayList<GoldTitleBean> listTitles) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitles;
        for (int i = 0; i < listTitle.size(); i++) {
            GoldTitleBean bean = listTitle.get(i);
            if (bean.isChecked){
                newTitle.add(bean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return newTitle.get(position);
    }
}
