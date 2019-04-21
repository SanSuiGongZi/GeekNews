package com.example.geeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.presenter.EmptyP;
import com.example.geeknews.view.EmptyV;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDateGald extends BaseActivity<EmptyV, EmptyP> implements EmptyV {

    private static final String TAG = "MyDateGald";

    @BindView(R.id.calenderView)
    MaterialCalendarView mCalenderView;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_date;
    }

    @Override
    protected void initView() {
        //日历
        initDATE();
    }
    private void initDATE() {
        mCalenderView.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(1,4,3))
                .setMaximumDate(CalendarDay.from(2021,5,21))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        mCalenderView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Toast.makeText(MyDateGald.this, date.toString(), Toast.LENGTH_SHORT).show();

                Date oldDate = date.getDate();
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
                String format = sf.format(oldDate);
                Log.e(TAG, "onDateSelected: "+format );

                Intent intent = new Intent();
                intent.putExtra("date",format);
                setResult(2,intent);
                finish();
            }
        });
    }
}
