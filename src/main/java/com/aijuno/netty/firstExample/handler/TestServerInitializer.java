package com.aijuno.netty.firstExample.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 自定义初始化管道
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();//管道 存放着所有的处理器
        pipeline.addLast("httpServerCodec",new HttpServerCodec());//再最后加入HTTP处理器
        pipeline.addLast("HttpServerHandler",new TestHttpServerHandler());
//        System.out.println("管道已经初始化完毕。");
    }
}
