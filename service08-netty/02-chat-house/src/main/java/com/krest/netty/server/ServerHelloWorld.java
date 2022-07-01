package com.krest.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.UnsupportedEncodingException;

public class ServerHelloWorld {
    private EventLoopGroup acceptGroup = null;
    private EventLoopGroup clientGroup = null;
    private ServerBootstrap bootstrap = null;

    private ServerHelloWorld() {
        init();
    }

    private void init() {
        acceptGroup = new NioEventLoopGroup();
        clientGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(acceptGroup, clientGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        bootstrap.option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);

    }


    public ChannelFuture doAccept(int port, final ChannelHandler... acceptorHandlers) throws InterruptedException {
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            /**
             * ChannelInitializer用于提供处理器的一个模拟对象
             * 其中定义了initChannel方法用于初始化逻辑责任链条的
             * 可以保证服务器的bootstrap只被初始化一次
             */
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(acceptorHandlers);
            }
        });
        ChannelFuture future = bootstrap.bind(port).sync();
        return future;
    }


    /**
     * 关闭服务，释放资源
     */
    public void release() {
        this.acceptGroup.shutdownGracefully();
        this.clientGroup.shutdownGracefully();
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        ChannelFuture future = null;
        ServerHelloWorld server = null;
        try {
            server = new ServerHelloWorld();
            future = server.doAccept(9999, new ServerHelloWorldHandler());
            System.out.println("server started:");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (null != future) {
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (null != server) {
                server.release();
            }
        }
    }


}
