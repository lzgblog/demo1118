package com.springbootdemo.demo1118.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面编程aop使用
 * @author luzg
 * @date 2022-12-03 16:57
 */
@Aspect
@Component
public class LogAspectJ {

    @Pointcut("execution(* com.springbootdemo.demo1118.controller.*.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"----方法之前执行-------"+joinPoint.getArgs());
    }
    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"----方法之后执行-------"+joinPoint.getArgs());
    }
    @Around("execution(* com.springbootdemo.demo1118.controller.*.hasParam(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint.getSignature().getName()+"-----前后通知-------"+ Arrays.toString(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        return proceed;
    }
}
