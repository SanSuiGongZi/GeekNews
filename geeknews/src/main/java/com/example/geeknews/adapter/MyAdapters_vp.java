package com.example.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.geeknews.base.BaseFramen;

import java.util.ArrayList;

public class MyAdapters_vp extends FragmentPagerAdapter {

    private ArrayList<BaseFramen> list;
    private ArrayList<String> listTab;

    public MyAdapters_vp(FragmentManager fm, ArrayList<BaseFramen> list, ArrayList<String> listTab) {
        super(fm);
        this.list = list;
        this.listTab=listTab;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab.get(position);
    }
}
