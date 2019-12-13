package com.dale.tasklib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadPoolManger {

    /**
     * 默认核心线程数，依据设备硬件设置
     */
    public static final int DEFAULT_THREAD_COUNT = Runtime.getRuntime().availableProcessors();


    private static class ThreadHolder {
        private static ThreadPoolManger threadPoolManger = new ThreadPoolManger();
    }

    public static ThreadPoolManger getInstance() {
        return ThreadHolder.threadPoolManger;
    }

    private ExecutorService newScheduled;
    private ExecutorService newSingle;
    private ExecutorService newFixed;
    private ExecutorService newCached;

    private ThreadPoolManger() {
        newCached = Executors.newCachedThreadPool(threadFactory);  //它是一个数量无限多的线程池，都是非核心线程，适合执行大量耗时小的任务
        newSingle = Executors.newSingleThreadExecutor(threadFactory);  //内部只有一个核心线程，所有任务进来都要排队按顺序执行
        newFixed = Executors.newFixedThreadPool(5, threadFactory); //线程数量固定的线程池，全部为核心线程，响应较快，不用担心线程会被回收。
        newScheduled = Executors.newScheduledThreadPool(2, threadFactory); //有数量固定的核心线程，且有数量无限多的非核心线程，适合用于执行定时任务和固定周期的重复任务
    }

    private static final ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "Task #" + mCount.getAndIncrement());
        }
    };


    public void execute(Task task) {
        newScheduled.execute(task);
    }

    /**
     * 关闭线程池操作
     */
    public void stop() {
        try {
            newScheduled.shutdown();
            if (!newScheduled.awaitTermination(0, TimeUnit.MILLISECONDS)) {
                newScheduled.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            newScheduled.shutdownNow();
        }
    }
}
