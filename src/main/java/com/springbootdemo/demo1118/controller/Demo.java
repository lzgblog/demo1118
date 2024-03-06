package com.springbootdemo.demo1118.controller;

import java.util.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author luzg
 * @date 2022-11-25 11:58
 */
public class Demo {

    public static void main(String args[]){
        Demo demo = new Demo();
        demo.threadPropertyByVolatile();
    }
    private volatile boolean flag = false;
    public void threadPropertyByVolatile(){
        Thread thread1 = new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1---"+flag);

        });
        Thread thread2 = new Thread(()->{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
                System.out.println("thread2---"+flag);
        });
        thread1.start();
        thread2.start();
    }
    private ThreadLocal<Integer> th = new ThreadLocal();
    public void threadPropertyByThreadLocal(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                th.set(1111111111);
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111---"+th.get());
                th.set(2222);
                System.out.println("2222--"+th.get());
                th.remove();
                System.out.println("3333--"+th.get());
            }
        });
        thread2.start();
    }
    public void test(){
        Thread t1 = new Thread(new Test());
        try {
            t1.start();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义线程执行顺序
     */
    public void test2(){
        Test3 test3 = new Test3();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test3.printA();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test3.printB();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                test3.printC();
            }
        });
        t1.setName("A");
        t2.setName("B");
        t3.setName("C");
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 死锁
     */
    public void deadLock(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj1){
                    System.out.println("获得锁1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj2){
                        System.out.println("想要获得锁2");
                    }
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj2){
                    System.out.println("获得锁2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj1){
                        System.out.println("想要获得锁1");
                    }
                }
            }
        });
        thread2.start();
    }

    /**
     * wait、notify线程通信
     */
    Object baozidian;
    public void waitNotifyTest(){
        Thread thread1 = new Thread(()->{
            while (baozidian == null){
                synchronized (this){
                    try {
                        System.out.println("1.线程1阻塞，等待唤醒");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("3.线程1执行完成");
            }
        });
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        baozidian = new Object();
        Thread thread2 = new Thread(()->{
            synchronized (this){
                this.notifyAll();
                System.out.println("2.执行线程2，唤醒线程1");
            }
        });
        thread2.start();
    }

    /**
     * wait造成死锁
     */
    public void deadWaitNotify(){
        Thread thread1 = new Thread(()->{
            while (baozidian == null){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this){
                    try {
                        System.out.println("1111.线程1阻塞，等待唤醒");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("3333.线程1执行完成");
            }
        });
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            this.notifyAll();
            System.out.println("2222.执行线程2，唤醒线程1");
        }
    }

    /**
     * 使用park/unpark通信
     */
    public void parkUnpark(){
        Thread thread = new Thread(() -> {
            while (baozidian == null) {
                System.out.println("1.线程1阻塞，等待唤醒");
                LockSupport.park();
            }
            System.out.println("3.线程1执行完成");
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        baozidian = new Object();
        LockSupport.unpark(thread);
        System.out.println("2.执行线程2，唤醒线程1");
    }
}
