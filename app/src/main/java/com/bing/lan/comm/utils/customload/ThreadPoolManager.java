package com.bing.lan.comm.utils.customload;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理
 *
 * @author Lean
 */
public class ThreadPoolManager {

    private final static int DEFAULT_COREPOOL_SIZE = 1;
    private final static int DEFAULT_MAXIMUMPOOL_SIZE = 4;
    private final static long DEFAULT_KEEPALIVE_TIME = 3;
    private final static TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;
    private static ThreadPoolManager sInstance = null;
    private ThreadPoolExecutor mWorkThreadPool = null; // 线程池
    private ScheduledExecutorService mScheduledExecutorService = null; // 调度线程池
    private Thread mScheduledThread = null; // 调度Runnable
    private Queue<Runnable> mWaitTasksQueue = null; // 等待任务队列
    private RejectedExecutionHandler mRejectedExecutionHandler = null; // 任务被拒绝执行的处理器
    private Object mLock = new Object();

    private ThreadPoolManager() {
        this(DEFAULT_COREPOOL_SIZE, DEFAULT_MAXIMUMPOOL_SIZE,
                DEFAULT_KEEPALIVE_TIME, DEFAULT_TIMEUNIT, false);
    }

    private ThreadPoolManager(int corePoolSize, int maximumPoolSize,
            long keepAliveTime, TimeUnit unit, boolean isPriority) {
        //并发队列 交替执行任务
        mWaitTasksQueue = new ConcurrentLinkedQueue<>();
        mScheduledThread = new ScheduledThread();
        //创建一个单线程执行器 每1秒执行一个
        //http://blog.csdn.net/lmj623565791/article/details/27109467
        mScheduledExecutorService = Executors
                .newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(mScheduledThread, 0,
                1000, TimeUnit.MILLISECONDS);

        initRejectedExecutionHandler();
        //阻塞队列 队列最大长度16
        BlockingQueue<Runnable> queue = isPriority ? new PriorityBlockingQueue<Runnable>(
                16) : new LinkedBlockingQueue<Runnable>(16);
        mWorkThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit, queue, mRejectedExecutionHandler);
    }

    public synchronized static ThreadPoolManager getInstance() {
        if (sInstance == null) {
            sInstance = new ThreadPoolManager();
        }
        return sInstance;
    }

    /**
     * 初始化任务被拒绝执行的处理器的方法
     */
    private void initRejectedExecutionHandler() {
        mRejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r,
                    ThreadPoolExecutor executor) {
                // 把被拒绝的任务重新放入到等待队列中
                synchronized (mLock) {
                    mWaitTasksQueue.offer(r);
                }
            }
        };
    }

    /**
     * 是否还有等待任务的方法
     *
     * @return
     */
    public boolean hasMoreWaitTask() {
        boolean result = false;
        if (mWaitTasksQueue != null && !mWaitTasksQueue.isEmpty()) {
            result = true;
        }
        return result;
    }

    /**
     * 执行任务的方法
     *
     * @param task
     */
    public void execute(Runnable task) {
        if (mWorkThreadPool != null && task != null) {
            mWorkThreadPool.execute(task);
        }
    }

    /**
     * 取消任务
     *
     * @param task
     */
    public void cancel(Runnable task) {
        if (task != null) {
            synchronized (mLock) {
                if (mWaitTasksQueue != null && mWaitTasksQueue.contains(task)) {
                    mWaitTasksQueue.remove(task);
                }
            }
            if (mWorkThreadPool != null) {
                mWorkThreadPool.remove(task);
            }
        }
    }

    public boolean isShutdown() {
        boolean result = true;
        if (null != mWorkThreadPool) {
            result = mWorkThreadPool.isShutdown();
        }
        return result;
    }

    /**
     * 清理方法
     */
    public void cleanUp() {
        if (mWorkThreadPool != null) {
            if (!mWorkThreadPool.isShutdown()) {
                try {
                    mWorkThreadPool.shutdownNow();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            mWorkThreadPool = null;
        }
        mRejectedExecutionHandler = null;
        if (mScheduledExecutorService != null) {
            if (!mScheduledExecutorService.isShutdown()) {
                try {
                    mScheduledExecutorService.shutdownNow();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            mScheduledExecutorService = null;
        }
        mScheduledThread = null;
        synchronized (mLock) {
            if (mWaitTasksQueue != null) {
                mWaitTasksQueue.clear();
                mWaitTasksQueue = null;
            }
        }
    }

    /**
     * 初始化调度Runnable
     * 应该改为 implement Runnable
     */
    private class ScheduledThread extends Thread {

        @Override
        public void run() {
            synchronized (mLock) {
                if (hasMoreWaitTask()) {
                    Runnable runnable = mWaitTasksQueue.poll();
                    if (runnable != null) {
                        //把队列的头 runnable 再次扔回给线程池
                        execute(runnable);
                    }
                }
            }
        }
    }
}
