package com.example.geeknews;
//张东 1808A 206

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geeknews.activityFragment.Fragment_About;
import com.example.geeknews.activityFragment.Fragment_Collect;
import com.example.geeknews.activityFragment.Fragment_Gank;
import com.example.geeknews.activityFragment.Fragment_Gold;
import com.example.geeknews.activityFragment.Fragment_Settings;
import com.example.geeknews.activityFragment.Fragment_V2ex;
import com.example.geeknews.activityFragment.Fragment_Wechat;
import com.example.geeknews.activityFragment.Fragment_zhihu;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.presenter.IPresenter;
import com.example.geeknews.view.IView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<IView, IPresenter> implements IView {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tv_toolBar)
    TextView tvToolBar;
    @BindView(R.id.tool)
    Toolbar tool;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    private FragmentManager manager;
    private ArrayList<Fragment> list;
    private final int ZHIHUPAGE = 0;
    private final int WECHATPAGE = 1;
    private final int GANKPAGE = 2;
    private final int GOLDPAGE = 3;
    private final int V2EXPAGE = 4;
    private final int COLLECTPAGE = 5;
    private final int SETTINGSPAGE = 6;
    private final int ABOUTPAGE = 7;
    private int previouslyPage = 0;
    private MenuItem mSearchItem;
    private Fragment_Wechat fragment_wechat;

    @Override
    protected IPresenter initPresenter() {
        return new IPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tool.setTitle("");
        tool.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(tool);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tool, R.string.app_name, R.string.app_name);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        dl.addDrawerListener(toggle);
        toggle.syncState();

        manager = getSupportFragmentManager();

        initFrag();
        initAddHomeFrag();
        initSeachView();

    }

    //设置搜索
    private void initSeachView() {
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展开
                Toast.makeText(MainActivity.this, "展开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框关闭
                Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
            }
        });
        //显示提示信息
        //mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
    }

    private void initAddHomeFrag() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, list.get(0));
        transaction.commit();
    }

    private void initFrag() {
        fragment_wechat = new Fragment_Wechat();
        list = new ArrayList<>();
        list.add(new Fragment_zhihu());
        list.add(fragment_wechat);
        list.add(new Fragment_Gank());
        list.add(new Fragment_Gold());
        list.add(new Fragment_V2ex());
        list.add(new Fragment_Collect());
        list.add(new Fragment_Settings());
        list.add(new Fragment_About());
    }

    @Override
    protected void initLister() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                dl.closeDrawer(Gravity.LEFT);
                int itemId = menuItem.getItemId();
                if (itemId != R.id.info && itemId != R.id.options) {
                    menuItem.setChecked(true);
                    tvToolBar.setText(menuItem.getTitle());
                    switch (itemId) {
                        case R.id.zhihu:
                            switchFragments(ZHIHUPAGE);
                            break;
                        case R.id.wechat:
                            switchFragments(WECHATPAGE);
                            break;
                        case R.id.gank:
                            switchFragments(GANKPAGE);
                            break;
                        case R.id.gold:
                            switchFragments(GOLDPAGE);
                            break;
                        case R.id.v2ex:
                            switchFragments(V2EXPAGE);
                            break;
                        case R.id.collect:
                            switchFragments(COLLECTPAGE);
                            break;
                        case R.id.settings:
                            switchFragments(SETTINGSPAGE);
                            break;
                        case R.id.about:
                            switchFragments(ABOUTPAGE);
                            break;
                    }

                } else {
                    menuItem.setChecked(false);
                }
                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交搜索内容时的监听

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //文本发生改变的监听
                return false;
            }
        });
    }

    private void switchFragments(int page) {
        //获取需要显示的碎片
        Fragment fragment = list.get(page);
        //获取需要隐藏的碎片
        Fragment fragment1 = list.get(previouslyPage);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.frame, fragment);
        }

        if (page == previouslyPage) {
            transaction.show(fragment);
        } else {
            transaction.show(fragment);
            transaction.hide(fragment1);
        }
        transaction.commit();
        previouslyPage = page;

        //显示隐藏搜索框
        if (page == WECHATPAGE || page == GANKPAGE) {
            mSearchItem.setVisible(true);
        } else {
            mSearchItem.setVisible(false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);

        mSearchItem = menu.findItem(R.id.action_seach);
        mSearchItem.setVisible(false);
        mSearchView.setMenuItem(mSearchItem);

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (fragment_wechat.isVisible()){
                    fragment_wechat.setSearchData(query);
                    Log.e(TAG, "onQueryTextSubmit: "+query );
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


        return true;
    }

    /**
     * 那回退键会调用这个方法
     */
    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
