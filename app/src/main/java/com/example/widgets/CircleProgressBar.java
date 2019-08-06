package com.example.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgressBar extends View {
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    private int progress=0;
    private int maxprogress=100;

    public int getMaxprogress() {
        return maxprogress;
    }

    public void setMaxprogress(int maxprogress) {
        this.maxprogress = maxprogress;
        invalidate();
    }

    public int getPathColor() {
        return pathColor;
    }

    public void setPathColor(int pathColor) {
        this.pathColor = pathColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getPathWidth() {
        return pathWidth;
    }

    public void setPathWidth(int pathWidth) {
        this.pathWidth = pathWidth;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
        if(reset)
        {
            progress=0;
            invalidate();

        }
    }


    //paint
    private Paint pathPaint;
    private Paint fillPaint;
    //绘制的矩形区域

    private RectF oval;

    private int[] arcColors = new int[]{0xFF02C016, 0xFF3DF346, 0xFF40F1D5, 0xFF02C016};
    //背景黑色
    private int pathColor=0xFFF0EEDF;
    //边框黑色
    private int borderColor=0xFFD2D1C4;

    private int pathWidth=35;
    private int width;
    private int height;
    //圆半径
    private int radius=120;
   //梯度渲染
    private SweepGradient sweepGradient;
    private boolean reset=false;



    public CircleProgressBar(Context context,  AttributeSet attrs) {
        super(context, attrs);
        pathPaint=new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setStyle(Paint.Style.STROKE);//实心
        pathPaint.setDither(true);
        pathPaint.setStrokeJoin(Paint.Join.ROUND);

        fillPaint=new Paint();
        fillPaint.setAntiAlias(true);
        fillPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.STROKE);//实心
        fillPaint.setDither(true);
        fillPaint.setStrokeJoin(Paint.Join.ROUND);

        oval=new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int height=MeasureSpec.getSize(heightMeasureSpec);
       int width=MeasureSpec.getSize(widthMeasureSpec);
       setMeasuredDimension(width,height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(reset)
        {
            canvas.drawColor(0XFFFFFFFF);
            reset=false;
        }
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        radius=getMeasuredWidth()/2-pathWidth;
        //设置背景颜色
        pathPaint.setColor(pathColor);
        //设置画笔宽度
        pathPaint.setStrokeWidth(pathWidth);
        //绘制背景
        canvas.drawCircle(width/2,height/2,radius,pathPaint);
        pathPaint.setStrokeWidth(0.5f);
        pathPaint.setColor(borderColor);
        canvas.drawCircle(width/2,height/2,(float)(radius+pathWidth/2)+0.5f,pathPaint);
        canvas.drawCircle(width/2,height/2,(float)(radius+pathWidth/2)-0.5f,pathPaint);

        sweepGradient=new SweepGradient((float)(width/2),(float)(height/2),arcColors,null);
        fillPaint.setShader(sweepGradient);
        fillPaint.setStrokeCap(Paint.Cap.ROUND);
        fillPaint.setStrokeWidth(pathWidth);
        oval.set(width/2-radius,height/2-radius,width/2+radius,height/2+radius);
       // canvas.drawArc(oval,-90.0F,progress/maxprogress*360.0F,false,fillPaint);
        canvas.drawArc(oval,-90.0F,(float)progress/(float)maxprogress*360.0F,false,fillPaint);


    }
}
