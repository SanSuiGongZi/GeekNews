package com.example.geeknews.activityFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geeknews.R;
import com.example.geeknews.activity.V2EXActivity;
import com.example.geeknews.adapter.MyAdapters_vp;
import com.example.geeknews.base.BaseFramen;
import com.example.geeknews.bean.SuperV2EX;
import com.example.geeknews.presenter.V2exPT;
import com.example.geeknews.view.V2EXView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_V2ex extends BaseFramen<V2EXView, V2exPT> implements V2EXView {

    private static final String TAG = "Fragment_V2ex";
    @BindView(R.id.iv_gold)
    ImageView ivGold;
    private String mUrl = "https://www.v2ex.com/";

    @BindView(R.id.tab_v2ex)
    TabLayout tabV2ex;
    @BindView(R.id.vp_v2ex)
    ViewPager vpV2ex;
    private ArrayList<String> listTab;
    private ArrayList<SuperV2EX> list;
    private ArrayList<BaseFramen> fragments;
    private FragmentV2EX v2EX;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            fragments = new ArrayList<>();
            for (int i = 0; i < listTab.size(); i++) {
                v2EX = new FragmentV2EX();
                fragments.add(v2EX);
                Log.e(TAG, "handleMessage: " + i + "....." + list.get(i).getImg());
                Bundle bundle = new Bundle();
                bundle.putSerializable("1", list);
                v2EX.setArguments(bundle);
            }

            FragmentManager fm = getChildFragmentManager();
            MyAdapters_vp adapterVp = new MyAdapters_vp(fm, fragments, listTab);
            vpV2ex.setAdapter(adapterVp);
            tabV2ex.setupWithViewPager(vpV2ex);

        }
    };


    @Override
    protected int getLayoutFragID() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected V2exPT initFragPre() {
        return new V2exPT();
    }

    @Override
    protected void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    listTab = new ArrayList<>();
                    list = new ArrayList<>();

                    //------------------------------------------------------------------//

                    Document doc = Jsoup.connect(mUrl).get();
                    //tabs
                    ///  //class等于masthead的div标签
                    Element tabs = doc.select("div#Tabs").first();
                    Elements allTabs = tabs.select("a[href]");

                    for (Element element : allTabs) {
                        //获取href属性
                        String linkHref = element.attr("href");
                        //获取标签里面文本的
                        String linkText = element.text();
                        listTab.add(linkText);

                        Log.d(TAG, "linkHref: " + linkHref + ",tab:" + linkText);
                    }

                    Log.d(TAG, "run: " + listTab.size() + "listtabData:" + listTab.toString());
                    //新闻item数据
                    Elements items = doc.select("div.cell.item");

                    for (Element item : items) {
                        //图片
                        Element image = item.select("table tr td a > img.avatar").first();
                        String src = image.attr("src");
                        //Log.d(TAG, "src: "+src);

                        //评论数量和评论链接地址
                        String num = "";
                        Element comment = item.select("table tbody tr td a.count_livid").first();
                        if (comment != null) {
                            String href = comment.attr("href");
                            String text = comment.text();
                            num = text;
                            Log.d(TAG, "评论: " + ",链接:" + href + ",数量:" + text);
                        }
                        //标题
                        Element title = item.select("table tbody tr td span.item_title > a").first();
                        String text = title.text();
                        Log.d(TAG, "标题: " + text);


                        //topic_info
                        Element topic = item.select("table tbody tr td span.topic_info").first();
                        Element secondaryTab = topic.select("a.node").first();
                        String secTab = secondaryTab.text();
                        Log.d(TAG, "secTab: " + secTab);

                        String topicText = topic.text();
                        Log.d(TAG, "topicText: " + topicText);

                        list.add(new SuperV2EX(src, text, topicText, num));
                        Log.d(TAG, "run: ....." + list.size() + list.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(12);
            }
        }).start();

    }

    @Override
    protected void initData() {

        ivGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),V2EXActivity.class));
            }
        });

    }
}
