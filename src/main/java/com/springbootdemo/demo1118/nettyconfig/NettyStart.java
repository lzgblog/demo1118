package com.springbootdemo.demo1118.nettyconfig;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监听spring容器启动后，执行
 * @author luzg
 * @date 2022-11-29 20:46
 */
@Component
public class NettyStart implements ApplicationRunner {
    @Resource
    private NettyServer nettyServer;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        nettyServer.start();
    }
}
