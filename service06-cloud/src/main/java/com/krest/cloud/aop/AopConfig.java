package com.krest.cloud.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 定义程序的切面类
 */
@Aspect
@Component
public class AopConfig {

    @Pointcut(value = "execution(* com.krest.cloud.controller.SpringCloudController.*(..))")
    public void pointCut(){};

    @Before("pointCut()")
    public void logStart(){
        System.out.println("Before方法执行.......");
    }

    @After("pointCut()")
    public void logEnd(){
        System.out.println("After方法执行......");
    }

    @AfterReturning("pointCut()")
    public void logReturn(){
        System.out.println("AfterRunning方法执行......");
    }

    @AfterThrowing("pointCut()")
    public void logException(){
        System.out.println("AfterThrowing 程序跑出异常后执行.......");
    }

    @Around("pointCut()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("@Arount:执行目标方法之前...");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("@Arount:执行目标方法之后...");
        return obj;
    }
}
