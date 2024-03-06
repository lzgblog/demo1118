package com.springbootdemo.demo1118.nettyconfig;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * netty处理器
 * @author luzg
 * @date 2022-11-29 20:15
 */
@Component
public class NettyHandler extends ChannelInboundHandlerAdapter {

    //存放netty连接
    private static final ConcurrentHashMap<ChannelId, ChannelHandlerContext> CHANNEL_MAP = new ConcurrentHashMap<>();

    public NettyHandler() {
        super();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接："+ctx.channel().id().toString()+"-------"+ctx.channel().remoteAddress());
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelId channelId = ctx.channel().id();
        CHANNEL_MAP.put(channelId, ctx);
        super.channelActive(ctx);
    }

    public void channelSend(String msg){
       if(CHANNEL_MAP.isEmpty()){
           return;
       }
       for(Map.Entry<ChannelId,ChannelHandlerContext> map:CHANNEL_MAP.entrySet()){
           ChannelId channelId = map.getKey();
           ChannelHandlerContext ctx = map.getValue();
           if (ctx == null) {
               System.out.println("通道不存在！");
           }
           //ctx.channel().writeAndFlush(msg);
           //ctx.writeAndFlush(Unpooled.wrappedBuffer(msg.toString()));
           ByteBuf buffer = Unpooled.buffer();
           buffer.writeBytes(msg.getBytes());
           ctx.channel().writeAndFlush(buffer);
       }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到的消息："+msg);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }
}
