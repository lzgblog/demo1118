package com.springbootdemo.demo1118.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author luzg
 * @date 2022-11-25 12:29
 */
public class Test implements Runnable{

    private boolean flag;
    @Override
    public void run() {
        synchronized (this){
            for(int i=0;i<5;i++){
                System.out.println("-----"+i);
            }
        }

    }
}
