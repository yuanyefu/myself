package com.mybatis.yyf.entity;

import java.util.*;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/3/2 13:26
 * @Modified By
 * \
 */
public class OuterClass {

    private String name ="张三" ;
    private int age =21;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public  class InnerClass{
        public InnerClass(){
            name = "chenssy";
            age = 23;
        }

        public void display(){
            System.out.println("name：" + getName() +"   ;age：" + getAge());
        }
    }

    public static void main(String args[]) {
        HashMap<String, String> map = new HashMap();
        map.put("0", "1");
        map.put("1", "x");
        map.put("2", "x");
        Iterator<String> i = map.keySet().iterator();
        while (i.hasNext()) {
            if (map.get(i.next()).equals("x")) {
                map.put("ok", "true");
            }
        }

    }
    static int test()
    {
        int x = 1;
        try
        {
            return x;
        }
        finally
        {
            x++;
        }
    }
}
