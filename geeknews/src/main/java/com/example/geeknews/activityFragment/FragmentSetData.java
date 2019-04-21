package com.example.geeknews.activityFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.base.Constants;
import com.example.geeknews.presenter.EmptyP;
import com.example.geeknews.view.EmptyV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetData extends BaseFramen<EmptyV, EmptyP> implements EmptyV {


    @BindView(R.id.tv_setData)
    TextView tvSetData;

    public static FragmentSetData newInstance(String text) {
        FragmentSetData goldDetailFragment = new FragmentSetData();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, text);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_set_data;
    }

    @Override
    protected EmptyP initFragPre() {
        return new EmptyP();
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        tvSetData.setText(data);
    }
}
