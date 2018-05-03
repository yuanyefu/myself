package com.facade.yyf.config;

/**
 * @Author: yyf
 * @Date: 2018/5/3 10:11
 * @Description:
 */
public class ConfigModel {

    private boolean needGenPresentation = true;

    private boolean needGenBusiness = true;

    private boolean needGenDao = true;

    public boolean isNeedGenPresentation() {
        return needGenPresentation;
    }

    public void setNeedGenPresentation(boolean needGenPresentation) {
        this.needGenPresentation = needGenPresentation;
    }

    public boolean isNeedGenBusiness() {
        return needGenBusiness;
    }

    public void setNeedGenBusiness(boolean needGenBusiness) {
        this.needGenBusiness = needGenBusiness;
    }

    public boolean isNeedGenDao() {
        return needGenDao;
    }

    public void setNeedGenDao(boolean needGenDao) {
        this.needGenDao = needGenDao;
    }
}
