package com.example.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseActivity extends FragmentActivity {

    //是否显示程序标题
    protected boolean isHideAppTitle=true;
    //是否显示系统标题
    protected boolean isHideSysTitle=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.onInitVariable();
        if(this.isHideAppTitle){
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        }//APP的隐藏标题方法要在onCreate（）之前
        super.onCreate(savedInstanceState);

        if(this.isHideSysTitle)
        {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        //构造view，绑定数据
        this.onInitView(savedInstanceState);
       //请求数据
        this.onRequestData();
        //加入activity
        FrameApplication.addToAcivityList(this);


    }

    protected void onDestory()
    {
        FrameApplication.removeFromrActivityList(this);
        super.onDestroy();
    }

    /**
     * 1.初始化变量 最先被调用 用于初始化一些变量，创建一些变量
      */
    protected abstract void onInitVariable();

    /**
     * 2.初始化UI 布局载入操作
     */

    protected  abstract void onInitView(final Bundle savedInstanceState);

    /**
     * 3.请求数据
     */

    protected abstract void onRequestData();




}
