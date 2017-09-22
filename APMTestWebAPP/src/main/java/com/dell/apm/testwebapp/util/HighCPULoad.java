package com.dell.apm.testwebapp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxiao on 5/24/2017.
 */
public class HighCPULoad {

    public static long highCPULoad(String prefix, int minV, int maxV, int loopTimes) {
        long spentTime = 0;
        long t0 = System.currentTimeMillis();
        int i = minV;
        int loopCount = 0;
        while(true) {
            i++;
            System.out.println(prefix + " HighCPULoad: loopCount:" + loopCount + ", minV:" + minV + ", maxV:" + maxV + ", i=" + i);
            if (i>=maxV) {
                loopCount++;
                if (loopCount >= loopTimes) {
                    spentTime = System.currentTimeMillis() - t0;
                    System.out.println(prefix + ", HighCPULoad: [" + loopCount +"] count loop completed. Spend [" + spentTime + "] ms.");
                    return spentTime;
                }
            }
        }
    }

    public static long highCPULoadWithMultiThreads(final int loopTimes, final int minV, final int maxV, final int threadCount) throws IOException, InterruptedException {
        long spentTime = 0;
        long t0 = System.currentTimeMillis();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println("HighCPULoad: Entered thread: [" + threadName + "]");
                long spendTime = HighCPULoad.highCPULoad("Thread [" + threadName + "]", minV, maxV, loopTimes);
                System.out.println("HighCPULoad: Thread: [" + threadName + "] completed. loopTimes:" + loopTimes + ", minV:" + minV + ", maxV:" + maxV + ", spendTime:" + spendTime);
            }
        };
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i<threadCount; i++) {
            String threadName = "Test-HighCPULoad-Thread-" + i;
            Thread thread = new Thread(run, threadName);
            threadList.add(thread);
            thread.start();
            System.out.println("HighCPULoad: Thread [" + threadName + "] start.");
        }

        Thread.sleep(3 * 1000L);

        for (Thread t : threadList) {
            t.join();
        }

        spentTime = System.currentTimeMillis() - t0;
        System.out.println("HighCPULoad: Totally " + threadCount + " threads completed. spentTime:[" + spentTime + "] ms.");

        return spentTime;
    }


    public static int toInteger(String s, int defaultV) {
        try {
            return Integer.valueOf(s);
        } catch (Throwable e) {
            return defaultV;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int loopTimes = 1;
        int minV = 0;
        int maxV = 100 * 1000;
        //int maxV = 1 * 1000 * 1000;
        //int maxV = 100 * 1000 * 1000;
        //highCPULoad("", minV, maxV, loopTimes);
        int threadCount = 4;

        HighCPULoad.highCPULoadWithMultiThreads(loopTimes, minV, maxV, threadCount);
    }
}
