package com.example.clqshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒后进入主页面
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //启动MainActivity主页面，这段代码是在主线程执行
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}