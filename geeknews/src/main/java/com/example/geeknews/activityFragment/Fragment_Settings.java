package com.example.geeknews.activityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.presenter.SettingsPT;
import com.example.geeknews.view.SettingsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_Settings extends BaseFramen<SettingsView, SettingsPT> implements SettingsView {
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.rl)
    RelativeLayout rl;

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_settings;
    }

    @Override
    protected SettingsPT initFragPre() {
        return new SettingsPT();
    }

    @Override
    protected void initView() {
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCache.setText("");
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        tvCache.setText("5.0KB");
    }
}
