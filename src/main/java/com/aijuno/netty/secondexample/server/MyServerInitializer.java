package com.aijuno.netty.secondexample.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("LengthFieldBasedFrameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));//解码器
        pipeline.addLast("LengthFieldPrepender",new LengthFieldPrepender(4));//编码器
        pipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new MyServerHandler());//自己的

    }
}
