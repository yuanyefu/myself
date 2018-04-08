package com.test.yyf;

/**
 * @Author: yyf
 * Description:懒汉式
 * Date: Created in 2018/3/12 11:04
 * @Modified By
 * \
 */
public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    public LazySingleton() {
    }
    public LazySingleton getInstance(){
        if (lazySingleton == null){
            try {
                Thread.sleep(500);
                synchronized (LazySingleton.class){
                    if (lazySingleton == null){
                        lazySingleton = new LazySingleton();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return lazySingleton;
    }
}
