package com.design.yyf.example;

/**
 * @Author: yyf
 * Description:
 * Date: Created in 2018/2/26 16:32
 * @Modified By
 * \
 */
public class GoodbyeBuilder extends Builder {
    public GoodbyeBuilder(){
        msg = new GoodbyeMessage();
    }
    @Override
    public void buildBody() {
        // TODO Auto-generated method stub
        msg.setBody("欢送内容");
    }

    @Override
    public void buildSubject() {
        // TODO Auto-generated method stub
        msg.setSubject("欢送标题");
    }
}
