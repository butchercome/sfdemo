package com.cjw.demo.thread.Barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 828471 on 2017/9/14.
 */
public class Rommate implements Runnable {
    private CyclicBarrier barrier;
    private CountDownLatch downLatch;
    private static int count = 1;
    private int id;

    public Rommate(CyclicBarrier barrier, CountDownLatch downLatch) {
        this.barrier = barrier;
        this.id = count++;
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        try {
            barrier.await();
            CXOrderNoUtil.getOrderNo();
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (BrokenBarrierException e) {
            System.out.println(e);
        } finally {
            downLatch.countDown();
        }
    }
}
