package com.example.frame;

import android.content.Context;

public class ErrorHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 处理异常,把异常写入到文件中
        LogWriter.logToFile("崩溃简短信息:" + e.getMessage());
        LogWriter.logToFile("崩溃简短信息:" + e.toString());
        LogWriter.logToFile("崩溃线程名称:" + t.getName() + "崩溃线程ID:" + t.getId());

        final StackTraceElement[] trace = e.getStackTrace();
        for (final StackTraceElement element : trace)
        {
            LogWriter.debugError("Line " + element.getLineNumber() + " : " + element.toString());
        }
        e.printStackTrace();
        FrameApplication.exitApp();


    }

    private static ErrorHandler instance;
    public static ErrorHandler getInstance()
    {
        if(ErrorHandler.instance ==null)
        {
            ErrorHandler.instance=new ErrorHandler();
        }
        return ErrorHandler.instance;
    }

    private ErrorHandler(){

    }

    public void setErrorHandler(final Context ctx)
    {
        Thread.setDefaultUncaughtExceptionHandler(this);//设置默认的未捕获异常处理器
    }

}
