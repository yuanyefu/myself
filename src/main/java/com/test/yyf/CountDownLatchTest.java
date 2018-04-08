package com.test.yyf;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/3/9 14:29
 * @Modified By
 * \
 */
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for(int i=0;i<5;i++){
            new Thread(new ReadNum(i,countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("线程执行结束。。。。");
    }

    static class ReadNum  implements Runnable{
        private int id;
        private CountDownLatch latch;
        public ReadNum(int id,CountDownLatch latch){
            this.id = id;
            this.latch = latch;
        }
        @Override
        public void run() {
            synchronized (CountDownLatchTest.class){
                System.out.println("id:"+id);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println("线程组任务"+id+"结束，其他任务继续");
            }
        }
    }
}
