package com.design.yyf.factory;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/1/15 16:55
 * @Modified By
 * \
 */
public class IntelFactory implements AbstractFactory  {
    @Override
    public Cpu createCpu() {
        return new IntelCpu(755);
    }

    @Override
    public Mainboard createMainboard() {
        return new IntelMainboard(755);
    }
}
