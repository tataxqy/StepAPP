package com.example.frame;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 写入日志到文件
 */
public class LogWriter {

    private static final String DEBUG_TAG="xqy";
    private static boolean isDebug=true;
    private static boolean isWriteToLog=false;



    public static void d(final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.d(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void d(final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.d(LogWriter.DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void d(final String DEBUG_TAG, final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.d(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, msg);
        }
    }

    public static void d(final String DEBUG_TAG, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.d(DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, msg);
            // LogWriter.d(DEBUG_TAG, msg);
        }
    }

    public static void debugError(final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.e(LogWriter.DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }


    public static void logToFile(final String msg)
    {
//		final String fileName = Environment.getExternalStorageDirectory().getPath() + "/logFile.txt";
        final String fileName = "/mnt/sdcard/logFile.txt";
        final File file = new File(fileName);
        try
        {
            final FileWriter filerWriter = new FileWriter(file, true);
            final BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (final IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void debugError(final String tag, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.e(tag, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void debugInfo(final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.i(LogWriter.DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void e(final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.e(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void e(final String msg)
    {
        if (LogWriter.isDebug)
        {
            if (msg != null)
            {
                Log.e(LogWriter.DEBUG_TAG, msg);
            }
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void e(final String DEBUG_TAG, final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.e(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, msg);
        }
    }

    public static void e(final String DEBUG_TAG, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.e(DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, msg);
        }
    }

    public static void i(final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.i(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void i(final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.i(LogWriter.DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void i(final String DEBUG_TAG, final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.i(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
    }

    public static void i(final String DEBUG_TAG, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.i(DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, msg);
        }
    }

    public static void LogToFile(final String newLog)
    {
        LogWriter.LogToFile(LogWriter.DEBUG_TAG, newLog);
    }

    public static void LogToFile(final String tag, final String text)
    {
        if (!LogWriter.isWriteToLog)
        {
            return;
        }

        final String needWriteMessage = tag + ":" + text;
//		final String fileName = Environment.getExternalStorageDirectory().getPath() + "/logFile.txt";
        final String fileName = Environment.getDataDirectory().getPath() + "/logFile.txt";

        final File file = new File(fileName);
        try
        {
            final FileWriter filerWriter = new FileWriter(file, true);
            final BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (final IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void v(final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.v(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
    }

    public static void v(final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.v(LogWriter.DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void v(final String DEBUG_TAG, final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.v(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
    }

    public static void v(final String DEBUG_TAG, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.v(DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, msg);
        }
    }

    public static void w(final Object thiz)
    {
        if (LogWriter.isDebug)
        {
            Log.w(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName());
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName());
        }
    }

    public static void w(final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.w(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
    }

    public static void w(final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.w(LogWriter.DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }

    public static void w(final String DEBUG_TAG, final Object thiz, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.w(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, thiz.getClass().getSimpleName() + ": " + msg);
        }
    }

    public static void w(final String DEBUG_TAG, final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.w(DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(DEBUG_TAG, msg);
        }
    }

    public static void warnInfo(final String msg)
    {
        if (LogWriter.isDebug)
        {
            Log.w(LogWriter.DEBUG_TAG, msg);
        }
        if (LogWriter.isWriteToLog)
        {
            LogWriter.LogToFile(LogWriter.DEBUG_TAG, msg);
        }
    }





}
