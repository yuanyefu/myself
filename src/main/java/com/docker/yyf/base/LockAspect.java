package com.docker.yyf.base;

import com.docker.yyf.exception.AlreadyLockedException;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LockAspect {
    private static final ExpressionParser PARSER = new SpelExpressionParser();
    private static final LocalVariableTableParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

    @Around("@annotation(synLock)")
    public Object lock(ProceedingJoinPoint pjp, Lock synLock) throws Throwable {
        Class lock = pjp.getTarget().getClass();
        System.out.println("Lock进入");
        throw new AlreadyLockedException();
    }
}
