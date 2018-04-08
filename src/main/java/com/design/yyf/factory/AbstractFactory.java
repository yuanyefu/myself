package com.design.yyf.factory;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/1/15 16:54
 * @Modified By
 * \
 */
public interface AbstractFactory {
    /**
     * 创建CPU对象
     * @return CPU对象
     */
     Cpu createCpu();
    /**
     * 创建主板对象
     * @return 主板对象
     */
     Mainboard createMainboard();
}
