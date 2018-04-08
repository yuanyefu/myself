package com.docker.yyf.entity;

public enum  NameType {
    BUY_PROP(1),
    //购买商品
    BUY_GOODS(2),
    //开箱
    OPEN_BOX(3);

    private int code;
    NameType(int code) {
        this.code = code;
    }

    public static NameType getType(int code){
        for (NameType status :
                NameType.values()) {
            if (status.code == code){
                return status;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getCode() {
        return code;
    }

}
