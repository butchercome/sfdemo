package com.cjw.demo.thread.Barrier;

import java.util.concurrent.*;

/**
 * Created by 828471 on 2017/9/14.
 */
public class CyclicBarrierDemo {
    public static void prepareDo(int threadNo) {
        CountDownLatch downLatch = new CountDownLatch(threadNo);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNo, new Runnable() {
            @Override
            public void run() {
                System.out.println("we are ready");
            }
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNo; i++) {
            executorService.execute(new Rommate(cyclicBarrier, downLatch));
        }
        try {
            downLatch.await();
            CXOrderNoUtil.checkIsExist();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        prepareDo(1000);
    }
}
