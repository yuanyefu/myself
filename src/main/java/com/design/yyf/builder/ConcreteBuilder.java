package com.design.yyf.builder;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/2/26 16:19
 * @Modified By
 * \
 */
public class ConcreteBuilder implements Builder {
    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("编号：9527");
    }

    @Override
    public void buildPart2() {
        product.setPart1("名称：XXX");
    }

    @Override
    public Product retrieveResult() {
        return product;
    }
}
