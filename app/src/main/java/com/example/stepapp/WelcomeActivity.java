package com.example.stepapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.example.frame.BaseActivity;

public class WelcomeActivity extends BaseActivity {


    public static final int DELAY_MILLIS = 3000;
    private Handler handler;
    private Runnable jumpRunnable;

    @Override
    protected void onInitVariable() {
        handler=new Handler();
        jumpRunnable=new Runnable() {
            @Override
            public void run() {
                //跳转到home
                Intent intent=new Intent();
                intent.setClass(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        };
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onRequestData() {
        handler.postDelayed(jumpRunnable,DELAY_MILLIS);

    }
}
