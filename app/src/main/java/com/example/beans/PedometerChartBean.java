package com.example.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 显示图表
 */
public class PedometerChartBean implements Parcelable {
    private int[] arrayData;//1440是怎么定义出来的
    private int index=0;//数组存储在哪个位置

    public PedometerChartBean()
    {
        index=0;
        arrayData=new int[1440];
    }

    protected PedometerChartBean(Parcel in) {
        arrayData = in.createIntArray();
        index = in.readInt();
    }

    public static final Creator<PedometerChartBean> CREATOR = new Creator<PedometerChartBean>() {
        @Override
        public PedometerChartBean createFromParcel(Parcel in) {
            return new PedometerChartBean(in);
        }

        @Override
        public PedometerChartBean[] newArray(int size) {
            return new PedometerChartBean[size];
        }
    };

    public void setArrayData(int[] arrayData)
    {
        this.arrayData=arrayData;

    }

    public int[] getArrayData()
    {
        return arrayData;
    }

    public int getIndex()
    {
        return index;

    }

    public void setIndex(int index)
    {
        this.index=index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//写入
        dest.writeIntArray(arrayData);
        dest.writeInt(index);
    }
}
