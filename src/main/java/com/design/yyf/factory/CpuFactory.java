package com.design.yyf.factory;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/1/12 16:45
 * @Modified By
 * \
 */
public class CpuFactory {
    public static Cpu createCpu(int type){
        Cpu cpu = null;
        if(type == 1){
            cpu = new IntelCpu(755);
        }else if(type == 2){
            cpu = new AmdCpu(938);
        }
        return cpu;
    }
}
