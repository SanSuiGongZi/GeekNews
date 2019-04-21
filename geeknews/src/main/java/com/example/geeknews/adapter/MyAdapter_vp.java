package com.example.geeknews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.geeknews.base.BaseFramen;

import java.util.ArrayList;

public class MyAdapter_vp extends FragmentPagerAdapter {

    private ArrayList<BaseFramen> list;

    public MyAdapter_vp(FragmentManager fm, ArrayList<BaseFramen> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
