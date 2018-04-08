package com.design.yyf.builder;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/2/26 16:21
 * @Modified By
 * \
 */
public class Client {
    public static void main(String[]args){
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.retrieveResult();
        System.out.println(product.getPart1());
        System.out.println(product.getPart2());
    }
}
