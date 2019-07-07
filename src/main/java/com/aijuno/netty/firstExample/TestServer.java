package com.aijuno.netty.firstExample;

import com.aijuno.netty.firstExample.handler.TestServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务器类
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {

        //定义2个线程组 每个线程组就是一个死循环
        EventLoopGroup bossGroup = new NioEventLoopGroup(); //接受请求
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //具体处理
        try {

            //轻松启动  ServerChannel
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)//接收2个线程组的方法
                .channel(NioServerSocketChannel.class)//通过反射创建管道
                .childHandler(new TestServerInitializer())//自己的请求处理类
            ;

            ChannelFuture channelFuture = bootstrap.bind(8320).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
