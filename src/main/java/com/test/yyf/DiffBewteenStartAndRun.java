package com.test.yyf;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/3/9 14:12
 * @Modified By
 * \
 */
public class DiffBewteenStartAndRun {


    public static void main(String args[]) {


        System.out.println(Thread.currentThread().getName());
        // creating two threads for start and run method call
        Thread startThread = new Thread(new Task("start"));
        Thread runThread = new Thread(new Task("run"));


        startThread.start(); // calling start method of Thread - will execute in
        // new Thread
        runThread.run(); // calling run method of Thread - will execute in
        // current Thread
    }

    /*
   * Simple Runnable implementation
   */
    private static class Task implements Runnable {
        private String caller;


        public Task(String caller) {
            this.caller = caller;
        }


        @Override
        public void run() {
            System.out.println("Caller: " + caller
                    + " and code on this Thread is executed by : "
                    + Thread.currentThread().getName());


        }
    }
}
