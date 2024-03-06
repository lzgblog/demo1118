package com.springbootdemo.demo1118.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author luzg
 * @date 2022-12-07 19:37
 */
public class ProxyHandler implements InvocationHandler {
    private Object object;

    public ProxyHandler(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法前处理-----");
        Object invoke = method.invoke(object, args);
        System.out.println("方法后处理-----");
        return invoke;
    }
}
