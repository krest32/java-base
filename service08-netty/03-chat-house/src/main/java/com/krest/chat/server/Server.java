package com.krest.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class Server {

    private static final int PORT = 8008;

    /**
     * 启动 Netty Server
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        final ServerHandler serverHandler = new ServerHandler();
        // 主线程组，用于接受客户端的连接，但是不做任何处理，跟老板一样
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程组，主线程组会把任务丢给它，让从线程组去做相应的处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(PORT)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        /**
                         * 服务端会使用ChannelInitializer初始化与客户端通信的Channel，
                         * 并且在Channel的ChannelPipeline 中添加ChannelHandler，
                         * 这里主要是添加StringDecoder（将接收到的ByteBuf解码为 String, 是一种ChannelInboundHandlerAdapter）、
                         * StringEncoder（将请求的String编码为ByteBuf，是一种ChannelOutboundHandlerAdapter）以及自实现的ChannelInboundHandlerAdapter（处理与客户端之间的通信）。
                         * @param ch
                         * @throws Exception
                         */
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(serverHandler);
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
