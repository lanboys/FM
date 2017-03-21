package com.bing.lan.inke.yingke.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class SystemUtil {
	
	/**
	 * 显示软键盘
	 * @param context
	 * @param edit
	 */
	public static void showSoftInput(Context context, View edit) {
	    try{
    		InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    		if (inputManager != null && inputManager.isActive(edit)) {
    			inputManager.showSoftInput(edit, 0);
    		}
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 隐藏软键盘
	 * @param context
	 * @param edit
	 */
	public static void hideSoftInput(Context context, View edit) {
	    try{
    		InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    		if (inputManager != null && inputManager.isActive(edit)) {
    			inputManager.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    		}
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public static void hideSoftInput(Activity activity) {
	    try{
    		InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    		View view = activity.getCurrentFocus();
    		if(inputManager != null && inputManager.isActive() && view!=null) {
    			inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    		}
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 获取当前系统每个app的内存等级，即最大使用内存
	 * @param context
	 * @return
	 */
	public static int getMemoryClass(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		return activityManager.getMemoryClass();
	}
	
	/**
	 * 获取进程ID
	 * @param
	 * @return
	 */
	public static int myPid() {
		return android.os.Process.myPid();
	}
	
	/**
	 * 获取进程名
	 * @param context
	 * @return
	 */
	public static String getCurProcessName(Context context) {
		if(context != null) {
			ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
				if (appProcess.pid == myPid()) {
					return appProcess.processName;
				}
			}
		}
		return "";
	}
	
	/**
	 * 是否在wifi网络中
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		if(context == null) {
			return false;
		}
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI);
	}
	
	/**
	 * 是否连网 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConected(Context context) {
		if(context != null) {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			return (netInfo != null && netInfo.isAvailable());
		}
        return false;
	}
	
	/**
     * 检测储存卡是否可用
     * 
     * @return
     */
    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    
    /**
     * dip转换px
     * 
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转换dip
     * 
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

	/**
	 * 获取屏幕宽
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getDisplayWidth(Context context) {
		if(context == null) {
			return 0;
		}
		int width = 0;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		try {
			Class<?> cls = Display.class;
			Class<?>[] parameterTypes = { Point.class };
			Point parameter = new Point();
			Method method = cls.getMethod("getSize", parameterTypes);
			method.invoke(display, parameter);
			width = parameter.x;
		} catch (Exception e) {
			width = display.getWidth();
		}
		return width;
	}

	/**
	 * 获取屏幕高
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getDisplayHeight(Context context) {
		if(context == null) {
			return 0;
		}
		int height = 0;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		try {
			Class<?> cls = Display.class;
			Class<?>[] parameterTypes = { Point.class };
			Point parameter = new Point();
			Method method = cls.getMethod("getSize", parameterTypes);
			method.invoke(display, parameter);
			height = parameter.y;
		} catch (Exception e) {
			height = display.getHeight();
		}
		return height;
	}

	/**
	 * 获取系统bar条高
	 * @param activity
	 * @return
	 */
	public static int getStatusBarHeight(Activity activity) {
		if(activity == null) {
			return 0;
		}
		int statusHeight = 0;
		try {
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            statusHeight = frame.top;
            if (0 == statusHeight) {
                Class<?> localClass;
                
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass
                        .getField("status_bar_height")
                        .get(localObject)
                        .toString());
                statusHeight = activity.getResources()
                        .getDimensionPixelSize(i5);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return statusHeight;
	}
	
	/**
	 * 获取IMEI码
	 * @param context
	 * @return
	 */
	public static String getImei(Context context) {
		if(context == null) {
			return "0";
		}
		String imei = "0";
		try{
    		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    		imei = tm.getDeviceId();
    		if(TextUtils.isEmpty(imei)) {
    			imei = "0";
    		}
		}catch(Exception e){
		    imei = "0";
		}
		return imei;
	}
	
	/**
	 * 获取系统型号
	 * @return
	 */
	public static String getSysModel() {
		return Build.MODEL;
	}

	/**
	 * 获取系统版本号
	 * @return
	 */
	public static String getSysVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * 判断当前程序是否前台进程
	 *
	 * @param context
	 * @return
	 */
    public static boolean isCurAppTop(Context context) {
    	if(context == null) {
    		return false;
    	}
		String curPackageName = context.getPackageName();
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(1);
		if (list != null && list.size() > 0) {
			RunningTaskInfo info = list.get(0);
			String topPackageName = info.topActivity.getPackageName();
			String basePackageName = info.baseActivity.getPackageName();
			if (topPackageName.equals(curPackageName) && basePackageName.equals(curPackageName)) {
				return true;
			}
		}
		return false;
	}

    /**
     * 获取手机IP地址
     * @return
     */
	public static String getLocalIpAddress() {
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface intf = en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return "";
	}

	/**
	 * 获取系统相册路径, 耗时操作
	 * @param context
	 * @return
	 */
	public static String getAlbumPath(Context context) {
		if(context == null) {
			return null;
		}
		ContentResolver cr = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Images.Media.TITLE, "title");
		values.put(Images.Media.DESCRIPTION, "description");
		values.put(Images.Media.MIME_TYPE, "image/jpeg");
		Uri url = cr.insert(Images.Media.EXTERNAL_CONTENT_URI, values);
		// 查询系统相册数据
		Cursor cursor = null;
		try {
			cursor = Images.Media.query(cr, url, new String[]{Images.Media.DATA});
		} catch (Exception e) {
			if (cursor != null) {
				cursor.close();
				cursor = null;
			}
		}
		String albumPath = null;
		if (cursor != null && cursor.moveToFirst()) {
			int column_index = cursor.getColumnIndexOrThrow(Images.Media.DATA);
			albumPath = cursor.getString(column_index);
			try {
				cursor.close();
			} catch (Exception e) {
			}
		}
		cr.delete(url, null, null);
		if (albumPath == null) {
			return null;
		}

		File albumDir = new File(albumPath);
		if (albumDir.isFile()) {
			albumDir = albumDir.getParentFile();
		}
		// 如果系统相册目录不存在,则创建此目录
		if (!albumDir.exists()) {
			albumDir.mkdirs();
		}
		albumPath = albumDir.getAbsolutePath();
		return albumPath;
	}

	/**
	 * 初始化一个空{@link Menu}
	 *
	 * @param context
	 * @return
	 */
	public static Menu newInstanceMenu(Context context) {
		try {
			Class menuBuilder = Class.forName("com.android.internal.view.menu.MenuBuilder");
			Constructor constructor = menuBuilder.getConstructor(Context.class);
			return (Menu) constructor.newInstance(context);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断Intent是否有效
	 *
	 * @param context
	 *
	 * @return true 有效
	 */
	public static boolean isIntentAvailable(Context context, Intent intent) {
		final PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}


    /**
     * 获取系统sdk版本号
     * @return
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }
    
	public static boolean hasSmartBar() { // 禁止smartbar
		if ("mx3".equals(Build.DEVICE) && Build.VERSION.SDK_INT >= 19) { // Mx3安装MX4的os视频
			return false;
		}
		try {
			// 新型号可用反射调用Build.hasSmartBar()
			Method method = Class.forName("android.os.Build").getMethod(
					"hasSmartBar");
			return ((Boolean) method.invoke(null)).booleanValue();
		} catch (Exception e) {
		}
		// 反射不到Build.hasSmartBar()，则用Build.DEVICE判断
		if (Build.DEVICE.equals("mx2")) {
			return true;
		} else if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
			return false;
		}
		return false;
	}
	
	
	/**
     * 获取MAC地址
     * @param context
     * @return
     */
    public static String getMac(Context context) {
        return getMac(context, true);
    }
    /**
     * 获取MAC地址
     * @param context
     * @param withHyphen 是否带"-"
     * @return
     */
    public static String getMac(Context context, boolean withHyphen) {
        String imei = "";
        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface ni = e.nextElement();
                byte[] mac = ni.getHardwareAddress();
                if (mac != null && mac.length > 0) {
                    imei = formatMac(mac, withHyphen);
                    break;
                }
            }
        } catch (Exception e) {
            imei = "";
        }
        return imei;
    }
    
    /**
     * 获取设备标识，使用串号加网卡地址
     * @param context
     * @return
     */
    public static String getDeviceLabel(Context context) {
        String imei = getImei(context);
        String mac = getMac(context, false);
        return imei + "$" + mac;
    }

    private static String formatMac(byte[] mac, boolean withHyphen) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            byte b = mac[i];
            int intValue = 0;
            if (b >= 0) {
                intValue = b;
            } else {
                intValue = 256 + b;
            }
            sb.append(Integer.toHexString(intValue));
            if (withHyphen && i != mac.length - 1) {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}
