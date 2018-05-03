package com.facade.yyf.manager;

import com.facade.yyf.config.ConfigModel;

/**
 * @Author: yyf
 * @Date: 2018/5/3 10:20
 * @Description:
 */
public class ConfigManager {

    private static ConfigManager manager = null;

    private static ConfigModel model= null;

    private ConfigManager() {
    }
    public static ConfigManager getInstance(){
        if (manager == null){
            manager = new ConfigManager();
            model = new ConfigModel();
        }
        return manager;
    }
    public ConfigModel getConfigData(){
        return model;
    }

}
