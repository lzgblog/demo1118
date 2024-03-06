package com.springbootdemo.demo1118.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @author luzg
 * @date 2022-12-07 19:42
 */
public class RunTest {
    public static void main(String args[]){
        TargetService targetService = new TargetServiceImpl();
        ProxyHandler proxyHandler = new ProxyHandler(targetService);
        TargetService proxyInstance = (TargetService)Proxy.newProxyInstance(targetService.getClass().getClassLoader(), targetService.getClass().getInterfaces(), proxyHandler);
        proxyInstance.sendMessage("9999999");
    }
}
