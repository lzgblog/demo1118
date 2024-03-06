package com.springbootdemo.demo1118.nettyconfig;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;

/**
 * 初始化器
 * @author luzg
 * @date 2022-11-29 20:27
 */
@Component
public class SocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //加入编码器和解码器
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());
        //加入自己得NettyHandler
        pipeline.addLast(new NettyHandler());
    }
}
