package com.mybatis.yyf.entity;

import java.util.StringTokenizer;
import java.util.TreeSet;

public class User {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int i = 0;
        for(i=0;i<5;i++)
        {
            System.out.println(i);
        }
        //假设程序不小心多了一句--i;
        --i;
        System.out.println(i);
        assert i==5;
    }

    private static int test(){
        int x = 1;
        try
        {
            return 1;
        }
        finally
        {
            return 2;
        }
    }
}
class Parent implements Comparable {
    private int age = 0;
    public Parent(int age){
        this.age = age;
    }
    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        System.out.println("method of parent");
        Parent o1 = (Parent)o;
        return age>o1.age?1:age<o1.age?-1:0;
    }

}
 class Child extends Parent {

    public Child(){
        super(3);
    }
    @Override
    public int compareTo(Object o) {

        // TODO Auto-generated method stub
        System.out.println("method of child");
//			Child o1 = (Child)o;
        return 1;

    }
}