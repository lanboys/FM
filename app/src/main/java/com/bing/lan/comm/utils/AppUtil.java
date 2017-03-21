package com.bing.lan.comm.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.RawRes;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.bing.lan.comm.utils.load.ImageLoader;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AppUtil {

    /* global application parameter */
    private static Context sContext;
    private static Handler sHandler;
    private static int sThreadId;
    private static Gson sGson;

    private static Application sApplication;
    private static Resources sResources;
    private static RefWatcher sWatcher;

    /* global cache */
    private static Map<String, Object> sCacheMap;
    private static File sPicassoCacheFile;
    /* log */
    private static LogUtil log = LogUtil.getLogUtil(AppUtil.class, LogUtil.LOG_VERBOSE);

    public static void initGlobal(Application application, Context appCtx) {
        sApplication = application;
        sContext = appCtx;
        sGson = new Gson();
        sHandler = new Handler();
        sThreadId = Process.myTid();
        sCacheMap = new HashMap<>();
        sResources = sContext.getResources();
        // add leak canary
        // initLeakCanary(sApplication);
        // realm init
        RealmManager.initRealm(sContext);
        //ImageLoader init
        ImageLoader.init(application);
    }

    private static void initLeakCanary(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return;
        }
        sWatcher = LeakCanary.install(application);
    }

    public static void putGlobal(String key, Object value) {
        sCacheMap.put(key, value);
    }

    //    public static Object getGlobal(String key, Object defaultValue) {
    //        Object va = sCacheMap.get(key);
    //        if (va == null)
    //            return defaultValue;
    //        return va;
    //    }

    public static <T> T getGlobal(String key) {
        return getGlobal(key, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getGlobal(String key, T defaultValue) {
        T va = (T) sCacheMap.get(key);
        if (va == null)
            return defaultValue;
        return va;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return getAppRes().getDisplayMetrics();
    }

    public static String formatByteSize(long size) {
        return Formatter.formatFileSize(getAppContext(), size);
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static Application getApplication() {
        return sApplication;
    }

    public static RefWatcher getLeakCanaryRefWatcher() {
        return sWatcher;
    }

    public static void MemoryLeakCheck(Object watchedReference) {
        sWatcher.watch(watchedReference);
    }

    public static int getMainThreadId() {
        return sThreadId;
    }

    public static Handler getMainHandler() {
        return sHandler;
    }

    public static Gson getGson() {
        return sGson;
    }

    public static Resources getAppRes() {
        return sResources;
    }

    public static AssetManager getAssets() {
        return sResources.getAssets();
    }

    public static String getPackageName() {
        return getAppContext().getPackageName();
    }

    public static String getCacheDir() {
        return FileUtils.getCacheDir();
    }

    public static String getString(int strId) {
        return sResources.getString(strId);
    }

    public static InputStream openRawResource(@RawRes int id) {
        return sResources.openRawResource(id);
    }

    public static String getString(int strId, String format) {
        return sResources.getString(strId, format);
    }

    public static String[] getStrArr(int arrId) {
        return sResources.getStringArray(arrId);
    }

    public static int[] getIntArr(int arrId) {
        return sResources.getIntArray(arrId);
    }

    public static int getInteger(int intId) {
        return sResources.getInteger(intId);
    }

    public static int getColor(int colorId) {
        return sResources.getColor(colorId);
    }

    public static String loadAssetsJson(String fileName) {

        AssetManager assets = getAssets();
        BufferedReader bfr = null;
        try {

            InputStreamReader isr = new InputStreamReader(assets.open(fileName), "utf-8");
            //从assets获取json文件
            bfr = new BufferedReader(isr);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bfr.readLine()) != null) {
                stringBuilder.append(line);
            }//将JSON数据转化为字符串
            Log.d("qh", stringBuilder.toString());
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // assets.close();不能close,不然后面的资源就解析不了了
            IOUtils.close(bfr);
        }
        return "";
    }

    public static void startActivity(Context c, Class<? extends Activity> clazz, boolean ifFinish, boolean addFlags) {
        Intent intent = new Intent(c, clazz);
        if (addFlags) {
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        }
        c.startActivity(intent);
        if (ifFinish) {
            ((Activity) c).finish();
        }
    }

    public static void showToast(Context c, String msg) {
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 安全执行任务
     *
     * @param runnable
     */
    public static void postTaskSafe(Runnable runnable) {
        postTaskSafeDelay(runnable, 0);
    }

    public static void postTaskSafeDelay(Runnable runnable, long delayMillis) {
        int currId = Process.myTid();
        if (currId == getMainThreadId() && delayMillis == 0)
            runnable.run();
        else
            getMainHandler().postDelayed(runnable, delayMillis);
    }

    public static int dip2px(int dip) {
        // denstity*dip=px;
        float density = getAppRes().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    public static int px2dip(int px) {
        // denstity*dip=px;
        float density = getAppRes().getDisplayMetrics().density;
        int dip = (int) (px / density + .5f);
        return dip;
    }

    //获得状态栏的高度
    public static int getStatusHeight() {
        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = getAppContext().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            log.e("getStatusHeight():  " + e.getLocalizedMessage());
        }
        return statusHeight;
    }

    public static void RunApp(String packageName) {
        PackageInfo pi;
        try {
            pi = getAppContext().getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            // resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);
            PackageManager pManager = getAppContext().getPackageManager();
            List<ResolveInfo> apps = pManager.queryIntentActivities(resolveIntent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;

                Intent intent = new Intent(Intent.ACTION_MAIN);
                // intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName(packageName, className);

                intent.setComponent(cn);
                getAppContext().startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int getAppVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static File getCacheFile(String name) {
        return new File(getCacheDir(), name);
    }

    public static Intent createShareIntent(String shareTitle,
            String detailTitle,
            String msgText,
            File img) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (img == null || TextUtils.isEmpty(img.getAbsolutePath())) {
            intent.setType("text/plain");
        } else {
            Uri u = Uri.fromFile(img);
            intent.putExtra(Intent.EXTRA_STREAM, u);
            intent.setType("image/*");
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, detailTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        return Intent.createChooser(intent, shareTitle);
    }

    /**
     * 获取屏幕的高度
     */
    public static int getScreenH() {

        // DisplayMetrics metrics = new DisplayMetrics();
        //获取屏幕的显示信息
        // Display display = activity.getWindowManager().getDefaultDisplay();

        // 第一种方法
        // display.getMetrics(metrics);//数据在metrics中

        // 第二种方法
        // int screenWidth = display.getWidth();
        // int screenHeight = display.getHeight();

        // 第三种方法
        DisplayMetrics metrics = getAppContext().getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    /**
     * 获取屏幕的宽度
     */
    public static int getScreenW() {
        DisplayMetrics metrics = getAppContext().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 在application中调用,初始化所有工具类
     *
     * @param context
     * @param parms
     */
    public static void initOtherUtil(Context context, String... parms) {

        // SpUtil.prepare(context, parms[0]);
        // BlackNumberInfoDao.prepare(context);
        // AppLockInfoDao.prepare(context);
        // AssetsUtil.prepare(context);
    }

    /**
     * 获取系统所有app信息
     *
     * @param context
     * @return
     */
    public static List<AppInfoBean> getAllAppInfo(Context context) {

        List<AppInfoBean> appInfoBeens = new ArrayList<>();
        //获取包管理器
        PackageManager packageManager = context.getPackageManager();
        //获取安装包的信息
        List<PackageInfo> infos = packageManager.getInstalledPackages(0);

        //遍历
        for (PackageInfo info : infos) {
            AppInfoBean appInfoBean = new AppInfoBean();

            //获取包名
            String packageName = info.packageName;
            appInfoBean.setAppPackageName(packageName);

            //获取app名称
            String label = info.applicationInfo.loadLabel(packageManager).toString();
            appInfoBean.setAppName(label);

            //获取app图标
            Drawable icon = info.applicationInfo.loadIcon(packageManager);
            appInfoBean.setAppIcon(icon);

            //获取app大小
            String path = info.applicationInfo.sourceDir;
            long size = new File(path).length();
            appInfoBean.setAppSize(size);

            //用户标志
            int flags = info.applicationInfo.flags;
            //系统标志
            int flaSystem = ApplicationInfo.FLAG_SYSTEM;

            if ((flags & flaSystem) == 1) {
                //系统软件
                appInfoBean.setUserApp(false);
            } else {
                //用户软件
                appInfoBean.setUserApp(true);
            }
            //安装位置标志
            int flagExternalStorage = ApplicationInfo.FLAG_EXTERNAL_STORAGE;

            if ((flags & flagExternalStorage) == 1) {
                //说明安装在sd卡
                appInfoBean.setSDApp(true);
            } else {
                //说明安装在内存
                appInfoBean.setSDApp(false);
            }
            appInfoBeens.add(appInfoBean);
        }

        return appInfoBeens;
    }

    /**
     * 启动app详情界面
     *
     * @param activity
     * @param apkPackagename
     */
    public static void detailApp(Activity activity, String apkPackagename) {
       /* <action android:name="android.settings.APPLICATION_DETAILS_SETTINGS" />
        <category android:name="android.intent.category.DEFAULT" />
        <ta android:scheme="package" />*/
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("package:" + apkPackagename));
        activity.startActivity(intent);
    }

    /**
     * 打开分享界面
     *
     * @param activity
     * @param shareText 分享时显示的文本内容
     */
    public static void shareApp(Activity activity, String shareText) {
       /* <action android:name="android.intent.action.SEND" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:mimeType="text/plain" />*/
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        activity.startActivity(intent);
    }

    /**
     * 开启其他app
     *
     * @param activity
     * @param apkPackagename
     */
    public static void startApp(Activity activity, String apkPackagename) {
        PackageManager pm = activity.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(apkPackagename);
        if (intent != null) {
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "该软件无法启动", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 卸载软件
     *
     * @param activity
     * @param apkPackagename
     */
    public static void uninstalllApp(Activity activity, String apkPackagename) {
       /* <action android:name="android.intent.action.VIEW" />
        <action android:name="android.intent.action.DELETE" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="package" />*/

        List<AppInfoBean> allAppInfo = getAllAppInfo(activity);
        boolean isExit = false;
        AppInfoBean apkInfoBean = null;
        for (AppInfoBean appInfoBean : allAppInfo) {
            String apkPackagename1 = appInfoBean.getAppPackageName();
            if (apkPackagename1.equals(apkPackagename)) {
                apkInfoBean = appInfoBean;
                isExit = true;
            }
        }
        if (!isExit) {
            Toast.makeText(activity, "不存在该软件,无法卸载", Toast.LENGTH_SHORT).show();
            return;
        }

        if (apkInfoBean.isUserApp()) {

            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setAction("android.intent.action.DELETE");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:" + apkPackagename));
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "系统软件root后才能卸载", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 回到launcher界面,即桌面
     *
     * @param activity
     */

    public static void startLauncher(Activity activity) {

        // <action android:name="android.intent.action.MAIN" />
        // <category android:name="android.intent.category.HOME" />
        // <category android:name="android.intent.category.DEFAULT" />
        // <category android:name="android.intent.category.MONKEY"/>

        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.MONKEY");
        activity.startActivity(intent);
    }

    /**
     * 隐藏app图标
     *
     * @param activity
     */
    public static void hideAppIcon(Activity activity) {
        activity.getPackageManager().setComponentEnabledSetting(activity.getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 在桌面生成快捷方式
     * <p>
     * -- 获取在桌面创建快捷方式的权限--
     * <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
     *
     * @param activity
     */

    public static void createShortCut(Activity activity, Bitmap iconBitmap, String appName, String action) {

        //在桌面创建快捷方式的意图
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 传递的图片
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, iconBitmap);
        // 软件的名称
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, appName);
        // 点击这个快捷方式的意图(隐式意图)
        Intent shortCutIntent = new Intent();
        shortCutIntent.setAction(action);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortCutIntent);
        //发送广播给桌面,创建快捷方式
        activity.sendBroadcast(intent);
    }

    /**
     * 获取应用程序名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AppUtil", "getAppName: " + e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            log.e("getVersionName: ", e);
        }
        return null;
    }

    public static boolean isInstalled(String packageName) {
        return isInstalled(getAppContext(), packageName);
    }

    /**
     * 判断包是否安装
     *
     * @param context
     * @param packageName
     * @return
     */

    public static boolean isInstalled(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            log.e("isInstalled: 是否已安装app ", e);
            return false;
        }
    }

    /**
     * 安装应用程序
     *
     * @param context
     * @param apkFile
     */
    public static void installApp(Context context, File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 打开应用程序
     *
     * @param context
     * @param packageName
     */
    public static void openApp(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    /**
     * 安装apk
     *
     * @param activity
     * @param requestCode
     * @param apkFile     安装包路径,用来生成uri
     */
    private void installApk(Activity activity, int requestCode, File apkFile) {
        //android系统里面要求系统做什么事
        //都是通过意图来表达(Intent)
               /* <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:scheme="content" />
                <data android:scheme="file" />
                <data android:mimeType="application/vnd.android.package-archive" />*/
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");

        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        // startActivity(intent);//这种方法启动安装界面,安装完成后,将不会自动进入下一个界面,造成假死界面
        activity.startActivityForResult(intent, requestCode);
    }

    public static class AppInfoBean {

        private Drawable appIcon;
        private String appName;
        private String appPackageName;

        private long appSize;
        private boolean isUserApp;
        private boolean isSDApp;

        public AppInfoBean() {
        }

        public Drawable getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(Drawable appIcon) {
            this.appIcon = appIcon;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppPackageName() {
            return appPackageName;
        }

        public void setAppPackageName(String appPackageName) {
            this.appPackageName = appPackageName;
        }

        public long getAppSize() {
            return appSize;
        }

        public void setAppSize(long appSize) {
            this.appSize = appSize;
        }

        public boolean isSDApp() {
            return isSDApp;
        }

        public void setSDApp(boolean isSDApp) {
            this.isSDApp = isSDApp;
        }

        public boolean isUserApp() {
            return isUserApp;
        }

        public void setUserApp(boolean isUserApp) {
            this.isUserApp = isUserApp;
        }
    }
}
