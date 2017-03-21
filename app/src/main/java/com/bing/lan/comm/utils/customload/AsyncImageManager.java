package com.bing.lan.comm.utils.customload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异步加载图片类
 * 
 * @author Lean
 * 
 */
public class AsyncImageManager {

	private static AsyncImageManager sInstance = null;
	private Handler mHandler = new Handler();
	private ConcurrentHashMap<String, SoftReference<Bitmap>> mImageCache;
	private ThreadPoolManager mThreadPoolManager = null;

	private AsyncImageManager(Context context) {
		//ConcurrentHashMap “分段锁”的概念 将ConcurrentHashMap变化成N个子HastTable进行存储 具体可看put/get
		//SoftReference 软引用 足够内存则存储  不足则销毁
		mImageCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>();
		mThreadPoolManager = ThreadPoolManager.getInstance();
	}

	public static synchronized AsyncImageManager getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new AsyncImageManager(context);
		}
		return sInstance;
	}

	/**
	 * 功能简述:通过图片的URL从内存获取图片的方法
	 * 
	 * @param imgUrl
	 *            图片URL
	 * @return
	 */
	public Bitmap loadImgFromMemery(String imgUrl) {
		Bitmap bm = null;
		if (!TextUtils.isEmpty(imgUrl)) {
			try {
				bm = mImageCache.get(imgUrl).get();
			} catch (NullPointerException e) {

			}
		}
		return bm;
	}

	/**
	 * 从SD卡加载图片的方法
	 */
	public Bitmap loadImgFromSD(String imgPath) {
		if (TextUtils.isEmpty(imgPath)) {
			return null;
		}
		try {
			return BitmapFactory.decodeFile(imgPath);
		} catch (OutOfMemoryError e) {
			Log.e("AsyncImageManager", "loadImgFromSD OutOfMemoryError!!");
			System.gc();
			return null;
		}

	}

	/**
	 * 从网络加载图片的方法
	 * 
	 * @param imgUrl
	 * @return
	 */
	public Bitmap loadImgFromNetwork(String imgUrl) {

		Bitmap result = null;
		InputStream inputStream = null;
		HttpURLConnection urlCon = null;
		try {
			urlCon = HttpTools.createURLConnection(imgUrl);
			inputStream = (InputStream) urlCon.getContent();
			if (inputStream != null) {
				result = BitmapFactory.decodeStream(inputStream);
			}
		} catch (OutOfMemoryError e) {
			Log.e("AsyncImageManager", "loadImgFromNetwork():OutOfMemoryError");
			e.printStackTrace();
			System.gc();
		} catch (SocketTimeoutException e) {
			Log.e("AsyncImageManager",
					"loadImgFromNetwork():SocketTimeoutException");
			e.printStackTrace();
		} catch (Exception e) {
			Log.e("AsyncImageManager", "loadImgFromNetwork()：" + e.toString());
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (urlCon != null) {
				urlCon.disconnect();
			}
		}
		return result;
	}

	/**
	 * 加载图片，如果图片在内存里，则直接返回图片，否则异步从SD卡或者网络加载图片
	 * 
	 * @param imgPath
	 * @param imgUrl
	 * @param callBack
	 * @return
	 */
	public Bitmap loadImage(final String imgUrl,
							final String imgPath,final IAsyncImageLoadedCallBack callBack) {
		if (TextUtils.isEmpty(imgUrl)) {
			return null;
		}
		Bitmap result = null;
		//从内存中取出位图
		result = loadImgFromMemery(imgUrl);
		if (result == null) {
			if (mThreadPoolManager == null || mThreadPoolManager.isShutdown()) {
				Log.e("AsyncImageManager",
						"threadPoll == null || threadPoll.isShutdown()");
				return null;
			}
			LoadImageRunable loadimg = new LoadImageRunable() {
				@Override
				public void run() {
					Bitmap b = null;
					//如果SD卡有图片 则从SD卡中取
					b = loadImgFromSD(imgPath);
					if (b == null) {
						//如果没有 则从网络中取
						b = loadImgFromNetwork(imgUrl);
						if (b != null) {
							if (imgPath != null && !"".equals(imgPath)) {
								//第一次从网络取 则直接保存到SD卡
								FileUtil.saveBitmapToSDFile(b, imgPath,
										Bitmap.CompressFormat.PNG);
							}
						}
					}
					//上面2种情况获取的数据 都要先放到缓存中去
					if (b != null) {
						mImageCache.put(imgUrl, new SoftReference<Bitmap>(b));
						// 主线程显示图片
						ImgCallBackRunnable callBackRunnable = new ImgCallBackRunnable(
								b, callBack, imgUrl);
						mHandler.post(callBackRunnable);
					}
				}
			};
			if (FileUtil.isFileExist(imgPath)) {
				// SD卡存在该图片，让它优先显示
				loadimg.setPriority(LoadImageRunable.MAX_PRIORITY);
			}
			if (mThreadPoolManager != null) {
				mThreadPoolManager.execute(loadimg);
			}
		}
		return result;
	}

	/**
	 * 
	 * 类描述:加载图片的Runnable,可比较优先级，在线程池中通过优先级控制先后执行
	 * 
	 */
	public abstract static class LoadImageRunable implements Runnable,
			Comparable<LoadImageRunable> {

		public static final int MAX_PRIORITY = Integer.MAX_VALUE;
		public static final int NORMAL_PRIORITY = 0;
		public static final int MIN_PRIORITY = Integer.MIN_VALUE;

		private int mPriority = NORMAL_PRIORITY;

		@Override
		public int compareTo(LoadImageRunable another) {
			int priority = another.getPriority();
			if (this.mPriority > priority) {
				return -1;
			}
			return 1;
		}

		public int getPriority() {
			return mPriority;
		}

		public void setPriority(int priority) {
			this.mPriority = priority;
		}
	}

	public static class ImgCallBackRunnable implements Runnable {
		private Bitmap bm = null;
		private IAsyncImageLoadedCallBack callBack = null;
		private String imgUrl = null;

		ImgCallBackRunnable(Bitmap img, IAsyncImageLoadedCallBack callBack,
				String imgUrl) {
			this.bm = img;
			this.callBack = callBack;
			this.imgUrl = imgUrl;
		}

		@Override
		public void run() {
			if (callBack != null) {
				callBack.imageLoaded(bm, imgUrl);
			}
		}
	}

	/**
	 * 销毁
	 */
	public synchronized static void destroy() {
		if (sInstance != null) {
			sInstance.clear();
			sInstance = null;
		}
	}

	public void clear() {
		if (mImageCache != null) {
			mImageCache.clear();
		}
		if (mThreadPoolManager != null) {
			mThreadPoolManager.cleanUp();
			mThreadPoolManager = null;
		}
	}
}
