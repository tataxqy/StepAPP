// IPedometerService.aidl
package com.example.Service;
//import  com.example.beans.PedometerChartBean;

// Declare any non-default types here with import statements

interface IPedometerService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void startCount();
       void stopCount();
       void resetCount();
       int getStepsCount();
       double getCalorie();
       double getDistance();
       void saveData();
       //设置传感器灵敏度
       void setSensitivity(double sensitivity);
       double getSensitivity();
       //设置采样时间间隔
       void setInterval(int interval);
       //获取采样时间
       int getInterval();
       //获取开始时间戳
       long getStartTimeStamp();
       //获取服务运行状态
       int getServiceStatus();
       //获取运动图表数据
      //PedometerChartBean getChartData();

}
