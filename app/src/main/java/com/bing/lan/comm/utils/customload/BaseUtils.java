package com.bing.lan.comm.utils.customload;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * 程序工具类
 * 
 * @author Lean
 * 
 * */
public class BaseUtils {
	/**
	 * 检测手机网络是否可用的方法
	 * 
	 * @return 可用返回TRUE,否则返回FALSE
	 */
	public static boolean isNetWorkAvailable(Context context) {
		boolean result = false;
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null) {
				NetworkInfo networkInfo = connectivityManager
						.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 检测手机WIFI有没有打开的方法
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiEnable(Context context) {
		boolean result = false;
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null) {
				NetworkInfo networkInfo = connectivityManager
						.getActiveNetworkInfo();
				if (networkInfo != null
						&& networkInfo.isConnected()
						&& networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * sd卡是否可读写
	 * 
	 * @return
	 */
	public static boolean isSDCardAvaiable() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}	
	/**
	 * 安装APK
	 * 
	 * @param context
	 * @param filePath
	 * */
	public static void installApk(Context context, String filePath) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse("file://" + filePath),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	/**
	 * 获取手机内存的可用空间大小
	 * */
	public static long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * 获取手机内存的总空间大小
	 * */
	public static long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	/**
	 * 获取手机内存空间已使用百分比
	 * */
	public static int getInternalMemoryUsePercent() {
		double usedInternalMemorySize = getTotalInternalMemorySize()
				- getAvailableInternalMemorySize();
		return (int) ((usedInternalMemorySize / getTotalInternalMemorySize()) * 100);
	}

	/**
	 * 获取手机sdcard的可用空间大小
	 * */
	static public long getAvailableExternalMemorySize() {
		if (isSDCardAvaiable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return 0;
		}
	}

	/**
	 * 获取手机sdcard的总空间大小
	 * */
	public static long getTotalExternalMemorySize() {
		if (isSDCardAvaiable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return 0;
		}
	}

	/**
	 * 获取sdcard空间已使用百分比
	 * */
	public static int getExternalMemoryUsePercent() {
		double usedExternalMemorySize = getTotalExternalMemorySize()
				- getAvailableExternalMemorySize();
		return (int) ((usedExternalMemorySize / getTotalExternalMemorySize()) * 100);
	}

}
