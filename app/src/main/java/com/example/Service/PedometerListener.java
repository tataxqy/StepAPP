package com.example.Service;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.example.beans.PedometerBean;

public class PedometerListener implements SensorEventListener {

    //当前步数
    private int currentStep=0;

    public void setCurrentStep(int step)
    {
        currentStep=step;
    }
    //灵敏度
    private float sensitivity=30;
    //采样时间
    private long mLimit=300;
    //最后保存的数值
    private float mLastValue;
    private float mScale=-4f;//缩放，放大值

    private float offset=240f;//采样数据的偏移

    //采样时间
    private long start=0;
    private long end=0;

    //方向
    private float mLastDirection;
    //记录数值
    private float mLastExtremes[][]=new float[2][1];
    //最后一次变化量
    private float mLastDiff;
    //是否匹配
    private int mLastMatch=-1;


    private PedometerBean data;

    public PedometerListener( PedometerBean data)
    {
        this.data=data;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor=event.sensor;
        synchronized (this)
        {
            if(sensor.getType()==Sensor.TYPE_ACCELEROMETER)//如果是加速度传感器
            {
                float sum=0;
                for(int i=0;i<3;i++)
                {
                    float vector=offset+event.values[i]*mScale;
                    sum+=vector;

                }

                //取得传感器平均值
                float average=sum/3;

                float dir;
                //判断了方向
                if(average>mLastValue)
                {
                    dir=1;
                }
                else if(average<mLastValue)
                {
                    dir=-1;
                }else{
                    dir=0;
                }

                if(dir==-mLastDirection)
                {
                    int extType=(dir>0?0:1);
                    mLastExtremes[extType][0]=mLastValue;

                    //变化的绝对值
                    float diff=Math.abs(mLastExtremes[extType][0]-mLastExtremes[1-extType][0]);
                   if(diff>sensitivity)
                   {
                       boolean isLargeAsPrevious=diff>(mLastDiff*2/3);
                      //数值是否小于上次数值的1/3
                       boolean isPreviousLargeEnough=mLastDiff>(diff/3);
                       //方向判断
                       boolean isNotContra=(mLastMatch!=1-extType);
                       if(isLargeAsPrevious&&isPreviousLargeEnough&&isNotContra)
                       {
                           //这是一次有效的记录

                           end=System.currentTimeMillis();
                           if(end-start>mLimit)
                           {
                               currentStep++;
                               mLastMatch=extType;
                               start=end;
                               mLastDiff=diff;

                               if(data!=null)
                               {
                                   data.setStepCount(currentStep);
                                   data.setLastStepTime(System.currentTimeMillis());


                               }

                           }else{
                               mLastDiff=sensitivity;

                           }

                       }
                       else
                       {
                           mLastMatch=-1;
                           mLastDiff=sensitivity;
                       }

                   }

                }
              mLastDiff=dir;
                mLastValue=average;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
