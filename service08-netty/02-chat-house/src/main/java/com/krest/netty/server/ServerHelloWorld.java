package com.krest.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.UnsupportedEncodingException;

public class ServerHelloWorld {
    /**
     * 监听事件的线程组，负责监听客户端请求
     */
    private EventLoopGroup acceptGroup = null;

    /**
     * 处理客户段相关操作的线程组，负责处理与客户端的数据通讯
     */
    private EventLoopGroup clientGroup = null;

    /**
     * 服务启动相关配置
     */
    private ServerBootstrap bootstrap = null;

    private ServerHelloWorld() {
        init();
    }

    /**
     * netty初始化服务
     */
    private void init() {
        acceptGroup = new NioEventLoopGroup();
        clientGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();

        // 绑定线程组： 两个参数 接收请求、处理事件
        bootstrap.group(acceptGroup, clientGroup);
        // 设置通讯模式为 Nio
        bootstrap.channel(NioServerSocketChannel.class);
        // 设置缓存区的大小
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);

        //SO_SNDBUF 为发送缓冲区，SO_RCVBUF 为接受缓冲区，SO_KEEPALIVE 表示心跳检测，保证连接有效
        bootstrap.option(ChannelOption.SO_SNDBUF, 16 * 1024)
                .option(ChannelOption.SO_RCVBUF, 16 * 1024)
                .option(ChannelOption.SO_KEEPALIVE, true);

    }


    public ChannelFuture doAccept(int port, final ChannelHandler... acceptorHandlers) throws InterruptedException {
        // childHandler 是 bootstrap 独有的方法，是用于提供处理对象的可以一次性处理若干个请求
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
