package com.test.yyf;

/**
 * @Author: yyf
 * Description:饿汉式单例
 * Date: Created in 2018/3/12 10:58
 * @Modified By
 * \
 */
public class MySingleton {
    private static MySingleton mySingleton = new MySingleton();

    public MySingleton() {
    }
    public static MySingleton getInstance(){
        return mySingleton;
    }
}
