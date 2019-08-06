package com.example.stepapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.Service.IPedometerService;
import com.example.Service.PedometerService;
import com.example.Utils.Utils;
import com.example.beans.PedometerChartBean;
import com.example.frame.BaseActivity;
import com.example.frame.LogWriter;
import com.example.widgets.CircleProgressBar;

public class HomeActivity extends BaseActivity {

    private CircleProgressBar progressBar;
    private TextView textCalorie;
    private TextView time;
    private TextView distance;
    private TextView stepCount;
    private Button reset;
    private Button btnStart;
    private Button dataChart;
    private IPedometerService remoteService;
    private int status=-1;
    private static final int STATUS_NOT_RUNNING=0;
    private static final int STATUS_RUNNING=1;
   //是否更新数据
    private boolean isRunning=false;
    private boolean isChartUpdate=false;

    private static final int MESSAGE_UPDATE_STEP_COUNT=1000;
    private static final int MESSAGE_UPDATE_CHART_DATA=2000;

    private static final int GET_DATA_TIME=200;
    private PedometerChartBean chartBean;
    private boolean bindService=false;




    @Override
    protected void onInitVariable() {

    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        progressBar=(CircleProgressBar)findViewById(R.id.progressbar);
        progressBar.setProgress(5000);
        progressBar.setMaxprogress(10000);
        textCalorie=findViewById(R.id.calorie);
        time=findViewById(R.id.time);
        distance=findViewById(R.id.distance);
        stepCount=findViewById(R.id.stepCount);
        reset=findViewById(R.id.reset);
        btnStart=findViewById(R.id.start);
        dataChart=findViewById(R.id.chart1);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    status=remoteService.getServiceStatus();

                } catch (RemoteException e) {
                    LogWriter.d(e.toString());
                }

                if(status==STATUS_RUNNING&&remoteService!=null)
                {
                    try {
                        remoteService.stopCount();
                        btnStart.setText("启动");
                        isRunning=false;
                        isChartUpdate=false;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }else if(status==STATUS_NOT_RUNNING&&remoteService!=null){
                    try {
                        remoteService.startCount();
                        btnStart.setText("停止");
                        isRunning=true;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }


                }

            }
        });
    }


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService=IPedometerService.Stub.asInterface(service);
            try {
                status=remoteService.getServiceStatus();
                if(status==STATUS_RUNNING)
                {
                    btnStart.setText("停止");
                    isChartUpdate=true;
                    isRunning=true;
                    //启动两个线程，定时获取数据，刷新UI
                    new Thread(new StepRunnable()).start();

                }
                else{
                    btnStart.setText("启动");
                }
            } catch (RemoteException e) {

                LogWriter.d(e.toString());
            }
        }

        protected  void onRequestData(){
            //检查服务是否运行
            //服务没有运行，启动服务，如果服务运行，直接绑定函数
            Intent serviceIntent=new Intent(this,PedometerService.class);

            if(!Utils.isServiceRunning(this, PedometerService.class.getName())){

                startService(serviceIntent);


            }else{//服务运行
                serviceIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            }
            bindService=bindService(serviceIntent,serviceConnection,BIND_AUTO_CREATE);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onRequestData() {

    }

    private class StepRunnable implements Runnable{
        @Override
        public void run() {
            while(isRunning)
            {
                try {
                    status=remoteService.getServiceStatus();
                    if(status==STATUS_RUNNING)
                    {
                        handler.removeMessages(MESSAGE_UPDATE_STEP_COUNT);
                        //发送消息，让Handler去更新数据
                        handler.sendEmptyMessage(MESSAGE_UPDATE_STEP_COUNT);
                        Thread.sleep(GET_DATA_TIME);//间隔多少时间去更新

                    }
                } catch (RemoteException e) {
                    LogWriter.d(e.toString());
                } catch (InterruptedException e) {
                    LogWriter.d(e.toString());
                }

            }
        }
    }

    private class ChartRunable implements Runnable{

        @Override
        public void run() {
            while(isChartUpdate)
            {


            }
        }
    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case MESSAGE_UPDATE_STEP_COUNT:
                {
                    //更新计步数据
                    updateStepCount();

                }
                break;
                default:
                    LogWriter.d("Default="+msg.what);
            }

            super.handleMessage(msg);

        }
    };


    public  void updateStepCount()
    {
        if(remoteService!=null){
            int stepCountVal=0;
            double calorieVal=0;
            double distanceVal=0;
            try{
                stepCountVal=remoteService.getStepsCount();
                calorieVal=remoteService.getCalorie();
                distanceVal=remoteService.getDistance();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            stepCount.setText(String.valueOf(stepCountVal)+"步");
            textCalorie.setText(Utils.getFormatVal(calorieVal)+"卡");
            distance.setText(Utils.getFormatVal(distanceVal));
            progressBar.setProgress(stepCountVal);

        }
    }


}
