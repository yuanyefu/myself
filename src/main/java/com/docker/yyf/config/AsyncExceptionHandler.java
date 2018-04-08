package com.docker.yyf.config;

import com.docker.yyf.exception.AlreadyLockedException;
import com.mongodb.util.JSON;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;


public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        System.out.println("Async method: {} has uncaught exception,params:{}"+method.getName()+ JsonMapper.nonEmptyMapper().toJson(objects));
        System.out.println("异常处理");
        if (throwable instanceof AlreadyLockedException) {
            AlreadyLockedException asyncException = (AlreadyLockedException) throwable;
            System.out.println("asyncException:{}"+ asyncException.getMessage());
        }else {

        }
        System.out.println("Exception :");
        //throwable.printStackTrace();
    }
}
