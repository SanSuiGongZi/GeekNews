package com.example.geeknews.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.presenter.EmptyP;
import com.example.geeknews.view.EmptyV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class V2EXActivity extends BaseActivity<EmptyV, EmptyP> implements EmptyV {

    private static final String TAG = "V2EXActivity";
    private String mUrl = "https://www.v2ex.com/";
    private ArrayList<String> listtab;
    private ArrayList<ArrayList<String>> arrayLists;
    private ArrayList<String> listData;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.e(TAG, "handleMessage: "+listtab.toString() );
            for (int i = 0; i < arrayLists.size(); i++) {
                ArrayList<String> strings = arrayLists.get(i);
                Log.e(TAG, "handleMessage: "+i+""+strings.toString() );
            }
        }
    };


    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_v2_ex;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                listtab = new ArrayList<>();
                listData = new ArrayList<>();
                arrayLists = new ArrayList<>();
                Pattern compile = Pattern.compile("[0-9]*");

                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements select2 = doc.select("div.box");
                    Elements select = select2.select("div.cell");
                    for (Element element : select) {
                        Elements select1 = element.select("table tbody tr td span.fade");
                        String text = select1.text();
                        if (!text.equals("")) {
                            listtab.add(text);
                            listtab.add(text);
                        }

                        Elements select3 = element.select("table tbody tr td > a");
                        for (Element element1 : select3) {
                            String text1 = element1.text();

                            if (!text1.equals("")) {
                                if (!compile.matcher(text1).matches()) {
                                    listData.add(text1);
                                }
                            }
                        }
                        arrayLists.add(listData);
                    }

                    Elements selects = select2.select("div.inner");
                    for (Element element : selects) {
                        Elements select1 = element.select("table tbody tr td right.fade");
                        String text = select1.text();
                        if (!text.equals("")) {
                            listtab.add(text);
                            listtab.add(text);
                        }

                        Elements select3 = element.select("table tbody tr td > a");
                        for (Element element1 : select3) {
                            String text1 = element1.text();

                            if (!text1.equals("")) {
                                if (!compile.matcher(text1).matches()) {
                                    listData.add(text1);
                                }
                            }
                        }
                        arrayLists.add(listData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
    }
}
