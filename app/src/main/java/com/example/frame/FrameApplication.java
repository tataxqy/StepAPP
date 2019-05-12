package com.example.frame;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;

public class FrameApplication extends Application {
    private static LinkedList<Activity> actList=new LinkedList<Activity>();
    public LinkedList<Activity> getActivityList()
    {
        return actList;
    }

    public static void addToAcivityList(final Activity act)
    {
         if(act!=null)
         {
             actList.add(act);
         }

    }
    public static void removeFromrActivityList(final Activity act)
    {
        if(actList!=null&&actList.size()>0&&actList.indexOf(act)!=-1)
        {
             actList.remove(act);
        }
    }

    //清理所有的activity
    public static void clearActivityList()
    {
        //不能从头开始删除，因为删除了第一个，第二个会跑到第一个的位置上
        for(int i=actList.size()-1;i>=0;i--)
        {
            final Activity act=actList.get(i);
            if(act!=null)
            {
                act.finish();
            }
        }
    }


    public static void exitApp()
    {
        try{
            clearActivityList();

        }catch(final Exception e){

        }finally{
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());//杀死进程的pid
        }
    }

    private PrefsManager prefManager;
    //单例构造
    private static FrameApplication instance;

    public static FrameApplication getInstance()
    {
        return instance;
    }

    public PrefsManager getPrefManager()
    {
        return prefManager;
    }

    private ErrorHandler errorHandler;


    public void onCreate()
    {
        super.onCreate();
        //初始化操作
        instance=this;
        prefManager=new PrefsManager(this);
        errorHandler=ErrorHandler.getInstance();


    }

}
