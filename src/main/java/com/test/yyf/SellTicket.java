package com.test.yyf;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/3/9 15:17
 * @Modified By
 * \
 */
public class SellTicket {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Ticket t = new Ticket();
        new Thread(t).start();
        new Thread(t).start();
    }
}

class Ticket implements Runnable {

    private volatile int ticket = 10;
    @Override
    public void run() {
        synchronized (Ticket.class){
            while (ticket > 0) {
                ticket--;
                System.out.println("当前票数为：" + ticket);
            }
        }


    }
}