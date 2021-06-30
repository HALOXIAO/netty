package io.netty.example.demo.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author HALOXIAO
 * @since 2021/6/30
 */
public class SimpleServer {

    public static void main(String[] args) {
        EventLoopGroup master = new NioEventLoopGroup(1);
        EventLoopGroup slave = new NioEventLoopGroup();
        try {
            ServerHandler serverHandler = new ServerHandler();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(serverHandler)
                    .group(master, slave);
            ChannelFuture future = serverBootstrap.bind(8081);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            master.shutdownGracefully();
            slave.shutdownGracefully();
        }
    }

}
