package com.design.yyf.factory;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/1/12 16:46
 * @Modified By
 * \
 */
public class MainboardFactory {
    public static Mainboard createMainboard(int type){
        Mainboard mainboard = null;
        if(type == 1){
            mainboard = new IntelMainboard(755);
        }else if(type == 2){
            mainboard = new AmdMainboard(938);
        }
        return mainboard;
    }
}
