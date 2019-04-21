package com.example.geeknews.zhiHuFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.zhihuPre.ThemePre;
import com.example.geeknews.zhihyView.BaseTheme;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTheme extends BaseFramen<BaseTheme,ThemePre> implements BaseTheme {

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_theme;
    }

    @Override
    protected ThemePre initFragPre() {
        return new ThemePre();
    }

}
