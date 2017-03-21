package com.bing.lan.comm.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 维护了两个线程池
 */
public class ThreadPoolProxyUtil {

    private static ThreadPoolProxyUtil mNormalThreadPoolProxy;
    private static ThreadPoolProxyUtil mDownLoadThreadPoolProxy;
    private ThreadPoolExecutor mExecutor;
    private int mCorePoolSize;
    private int mMaximumPoolSize;
    private long mKeepAliveTime;

    /**
     * 1.提交任务和执行任务
     * 是否有返回值
     * 提交任务有返回值
     * 执行任务没有返回值
     * 2.Future是啥?
     * 1.得到任务执行之后的结果
     * 2.包含了一个get方法和cancle
     * 3.其中get方法,是一个阻塞的方法,会阻塞等待任务执行完成之后的结果,还可以try catch到任务执行过程中抛出的异常
     */

    private ThreadPoolProxyUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime = keepAliveTime;
    }

    public static void executeDownLoadTask(Runnable task) {
        createDownLoadThreadPoolProxy().execute(task);
    }

    public static void executeNormalTask(Runnable task) {
        createNormalThreadPoolProxy().execute(task);
    }

    public static void removeDownLoadTask(Runnable task) {
        createDownLoadThreadPoolProxy().remove(task);
    }

    public static void removeNormalTask(Runnable task) {
        createNormalThreadPoolProxy().remove(task);
    }

    public static Future<?> submitDownLoadTask(Runnable task) {
        return createDownLoadThreadPoolProxy().submit(task);
    }

    public static Future<?> submitNormalTask(Runnable task) {
        return createNormalThreadPoolProxy().submit(task);
    }

    /**
     * 返回普通线程池代理
     */
    private static ThreadPoolProxyUtil createNormalThreadPoolProxy() {
        if (mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyUtil.class) {
                if (mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new ThreadPoolProxyUtil(5, 5, 3000);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    /**
     * 返回下载线程池代理
     */
    private static ThreadPoolProxyUtil createDownLoadThreadPoolProxy() {
        if (mDownLoadThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyUtil.class) {
                if (mDownLoadThreadPoolProxy == null) {
                    mDownLoadThreadPoolProxy = new ThreadPoolProxyUtil(3, 3, 3000);
                }
            }
        }
        return mDownLoadThreadPoolProxy;
    }

    private void getThreadPoolExecutor() {

        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {

            synchronized (ThreadPoolProxyUtil.class) {

                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {

                    int corePoolSize = mCorePoolSize;
                    int maximumPoolSize = mMaximumPoolSize;
                    long keepAliveTime = mKeepAliveTime;

                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                    mExecutor = new ThreadPoolExecutor(
                            corePoolSize,//核心线程数
                            maximumPoolSize,//最大线程池书
                            keepAliveTime,//保持时间
                            unit, //保持时间单位
                            workQueue,//任务队列
                            threadFactory,//线程工厂
                            handler//异常捕获器
                    );

                    /**
                     * 线程池的饱和策略，当阻塞队列满了，且没有空闲的工作线程，
                     * 如果继续提交任务，必须采取一种策略处理该任务，线程池提供了4种策略：
                     1、AbortPolicy：直接抛出异常，默认策略；
                     2、CallerRunsPolicy：用调用者所在的线程来执行任务；
                     3、DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
                     4、DiscardPolicy：直接丢弃任务；
                     当然也可以根据应用场景实现RejectedExecutionHandler接口，自定义饱和策略，
                     如记录日志或持久化存储不能处理的任务。
                     */

                }
            }
        }
    }

    /**
     * 提交任务
     */
    private Future<?> submit(Runnable task) {
        getThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 执行任务
     */
    private void execute(Runnable task) {
        getThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 移除任务
     */
    private void remove(Runnable task) {
        getThreadPoolExecutor();
        mExecutor.remove(task);
    }
}
