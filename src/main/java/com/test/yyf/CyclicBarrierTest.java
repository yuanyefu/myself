package com.test.yyf;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/3/9 14:47
 * @Modified By
 * \
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("线程组执行结束");
            }
        });
        for (int i = 0; i < 5; i++) {
            new Thread(new ReadNum(i,cyclicBarrier)).start();
        }
        //CyclicBarrier 可以重复利用
        // 这个是CountDownLatch做不到的
        for (int i = 11; i < 16; i++) {
            new Thread(new ReadNum(i,cyclicBarrier)).start();
        }
    }


    static class ReadNum  implements Runnable{
        private int id;
        private CyclicBarrier cyc;
        public ReadNum(int id,CyclicBarrier cyc){
            this.id = id;
            this.cyc = cyc;
        }
        @Override
        public void run() {
            synchronized (this){
                System.out.println("id:"+id);
                try {
                    cyc.await();
                    System.out.println("线程组任务" + id + "结束，其他任务继续");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
