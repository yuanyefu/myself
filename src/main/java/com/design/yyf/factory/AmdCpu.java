package com.design.yyf.factory;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/1/12 16:42
 * @Modified By
 * \
 */
public class AmdCpu implements Cpu {
    /**
     * CPU的针脚数
     */
    private int pins = 0;
    public  AmdCpu(int pins){
        this.pins = pins;
    }
    @Override
    public void calculate() {
        System.out.println("AMD CPU的针脚数：" + pins);
    }
}
