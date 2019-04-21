package com.example.geeknews.activityFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapter.MyAdapterV2EX;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.bean.SuperV2EX;
import com.example.geeknews.presenter.EmptyP;
import com.example.geeknews.view.EmptyV;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentV2EX extends BaseFramen<EmptyV, EmptyP> implements EmptyV {

    @BindView(R.id.rv_v2ex)
    RecyclerView rvV2ex;
    private ArrayList<SuperV2EX> arrayList;
    private MyAdapterV2EX adapterV2EX;

    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_v2_ex;
    }

    @Override
    protected EmptyP initFragPre() {
        return new EmptyP();
    }

    @Override
    protected void initView() {
        arrayList = new ArrayList<>();
        rvV2ex.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle arguments = getArguments();
        ArrayList<SuperV2EX> serializable = (ArrayList<SuperV2EX>) arguments.getSerializable("1");
        arrayList.addAll(serializable);
        adapterV2EX = new MyAdapterV2EX(getActivity(), arrayList);
        rvV2ex.setAdapter(adapterV2EX);
    }
}
