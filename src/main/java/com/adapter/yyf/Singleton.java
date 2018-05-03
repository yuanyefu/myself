package com.adapter.yyf;

/**
 * @Author: yyf
 * @Date: 2018/5/3 17:32
 * @Description:
 */
public class Singleton {

    private static class SingletonHolder{
        private static Singleton instance  = new Singleton();
    }
    private Singleton(){}

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}
