package com.example.clqshopping;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    private Context context;
    private CalendarView calendarView;
    private Button btn_back2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        btn_back2 = (Button)findViewById(R.id.btn_back2);
        //点击返回上一个页面
        btn_back2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //日历显示
        context = this;
        calendarView = (CalendarView)findViewById(R.id.calendarViewId);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String content = year+"-"+(month+1)+"-"+dayOfMonth;
                Toast.makeText(context, "当前选择日期:\n"+content, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
