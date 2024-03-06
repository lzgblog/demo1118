package com.springbootdemo.demo1118.jdkproxy;

/**
 * @author luzg
 * @date 2022-12-07 19:41
 */
public class TargetServiceImpl implements TargetService {
    @Override
    public void sendMessage(String str) {
        System.out.println("输出内容："+str);
    }
}
