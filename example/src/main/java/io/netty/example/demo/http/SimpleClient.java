package io.netty.example.demo.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author HALOXIAO
 * @since 2021/6/30
 */
public class SimpleClient {


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .handler(new ClientHandler())
                .group(new NioEventLoopGroup(1))
                .channel(NioSocketChannel.class);

        ChannelFuture future = bootstrap.connect("localhost", 8081);

    }

}
