package com.springbootdemo.demo1118.thread;

import java.util.concurrent.Callable;

/**
 * @author luzg
 * @date 2022-11-30 22:19
 */
public class CallbackThread implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("callable返回值");
        return "return content data";
    }
}
