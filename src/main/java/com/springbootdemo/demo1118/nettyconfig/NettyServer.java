package com.springbootdemo.demo1118.nettyconfig;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * netty服务
 * @author luzg
 * @date 2022-11-29 20:32
 */
@Component
public class NettyServer {

    @Resource
    private SocketInitializer socketInitializer;
    @Getter
    private ServerBootstrap serverBootstrap;
    @Value("${netty.port}")
    private int port;//端口

    public void start(){
        this.inti();
        serverBootstrap.bind(port);
        System.out.println("启动TCP连接+++++++"+"启动端口："+this.port);
    }
    public void inti(){
        NioEventLoopGroup eventExecutors1 = new NioEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap();
        this.serverBootstrap.group(eventExecutors1)
                .channel(NioServerSocketChannel.class)//使用nio类型
                .childHandler(socketInitializer);//加入自定义的初始化器
    }
}
