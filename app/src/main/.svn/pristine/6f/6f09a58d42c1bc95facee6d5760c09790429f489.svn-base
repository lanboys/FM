package com.bing.lan.comm.app;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 捕捉报错信息
 */
public class ErrorReport implements UncaughtExceptionHandler {

    private static ErrorReport mErrorReport;
    private Context mContext;
    private UncaughtExceptionHandler mDefaultHandler;

    private ErrorReport() {
    }

    public static synchronized ErrorReport getInstance() {
        if (mErrorReport == null) {
            mErrorReport = new ErrorReport();
        }
        return mErrorReport;
    }

    public void init(Context context) {
        this.mContext = context;
        //1 获取系统默认的错误处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //2.设置该类为系统的处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //处理异常的方法
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果我们的处理器不行了 那么就使用系统的处理器
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                //				AllActivityManager.getInstance().exitPrograms();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉  程序挂掉了!", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        saveCrashInfo(ex);
        return true;
    }

    /**
     * 保存错误的日志
     */
    private void saveCrashInfo(Throwable ex) {
        File path = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            path = mContext.getFilesDir();
            String fileName = "/" + getTime(System.currentTimeMillis())
                    + ".txt";
            try {
                if (!path.exists()) {
                    path.mkdirs();
                }
                Writer writer = new StringWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                ex.printStackTrace(printWriter);
                Throwable cause = ex.getCause();
                while (cause != null) {
                    cause.printStackTrace(printWriter);
                    cause = cause.getCause();
                }
                printWriter.close();
                FileOutputStream mFileOutputStream = new FileOutputStream(path
                        + fileName);
                mFileOutputStream.write(writer.toString().getBytes());
                mFileOutputStream.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private String getTime(long time) {
        String str = "";
        Date date = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        str = sf.format(date);
        return str;
    }
}
