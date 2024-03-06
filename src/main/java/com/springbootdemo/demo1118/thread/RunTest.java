package com.springbootdemo.demo1118.thread;

import java.util.concurrent.*;

/**
 * @author luzg
 * @date 2022-11-30 22:23
 */
public class RunTest {

    public static void main(String args[]){
       /* FutureTask<Object> task = new FutureTask<Object>(new CallbackThread());
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println(task.get());//获取call的返回值
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        RunTest test = new RunTest();
        test.threadPool();
    }

    public void threadPool(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
        pool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行内容111");
            }
        },2,1,TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行内容222");
            }
        },2,1,TimeUnit.SECONDS);
    }

    public void createThreadPool(){
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for(int i = 0;i<5;i++){
            int count = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("执行线程："+count);
                }
            });
            System.out.println("线程已提交："+i);
        }
    }
}
