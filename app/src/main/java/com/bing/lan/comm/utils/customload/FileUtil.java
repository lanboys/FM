package com.bing.lan.comm.utils.customload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

/**
 * 文件读写工具类
 * 
 * @author Lean
 * */

public class FileUtil {

	/**
	 * 保存位图到sd卡目录下
	 * 
	 * @param bitmap
	 *            ：位图资源
	 * @param filePathName
	 *            ：待保存的文件完整路径名
	 * @param iconFormat
	 *            ：图片格式
	 * @return true for 保存成功，false for 保存失败。
	 */
	public static boolean saveBitmapToSDFile(final Bitmap bitmap,
			final String filePathName, CompressFormat iconFormat) {
		boolean result = false;
		try {
			createNewFile(filePathName, false);
			OutputStream outputStream = new FileOutputStream(filePathName);
			result = bitmap.compress(iconFormat, 100, outputStream);
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param path
	 *            ：文件路径
	 * @param append
	 *            ：若存在是否插入原文件
	 * @return
	 */
	public static File createNewFile(String path, boolean append) {
		File newFile = new File(path);
		if (!append) {
			if (newFile.exists()) {
				newFile.delete();
			} else {
				// 不存在，则删除带png后缀名的文件
				File prePngFile = new File(path + ".png");
				if (prePngFile != null && prePngFile.exists()) {
					prePngFile.delete();
				}
			}
		}
		if (!newFile.exists()) {
			try {
				File parent = newFile.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}
				newFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newFile;
	}

	/**
	 * 保存数据到指定文件
	 * 
	 * @param byteData
	 * @param filePathName
	 */
	public static boolean saveByteToSDFile(final byte[] byteData,
			final String filePathName) {
		boolean result = false;
		try {
			File newFile = createNewFile(filePathName, false);
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(byteData);
			fileOutputStream.flush();
			fileOutputStream.close();
			result = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 指定路径文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		boolean result = false;
		try {
			File file = new File(filePath);
			result = file.exists();
			file = null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 把字符串写到SD卡的方法
	 * 
	 * @param data
	 * @param fileName
	 */
	public static void writeToSDCard(String data, String fileName) {
		if (data != null) {
			try {
				if (BaseUtils.isSDCardAvaiable()) {
					saveByteToSDFile(data.getBytes(), fileName);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从SD卡读取字符串
	 * 
	 * @param data
	 * @param fileName
	 */
	public static String readFromSDCard(String fileName) {
		String res = null;
		try {
			if (BaseUtils.isSDCardAvaiable()) {
				File infile = new File(fileName);
				Scanner input = new Scanner(infile);
				while (input.hasNext()) {
					res = input.next();
				}
				input.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 根据给定路径参数删除单个文件
	 * 
	 * @param filePath
	 *            要删除的文件路径
	 * @return 成功返回true,失败返回false
	 */
	public static boolean deleteFile(String filePath) {
		// 定义返回结果
		boolean result = false;
		try {
			if (filePath != null && !"".equals(filePath.trim())) {
				File file = new File(filePath);
				if (file.exists()) {
					result = file.delete();
				}
			}
		} catch (Exception e) {

		}
		return result;
	}

}
