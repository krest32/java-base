package com.krest.netty01.hellonetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author: krest
 * @date: 2021/5/24 01:15
 * @description:  netty客户端代码
 */
@Slf4j
public class HelloClient {

    public static void main(String[] args) throws InterruptedException {
        // Bootstrap：客户端的一个启动器，将netty提供的各种组件组合在一起进行使用
        new Bootstrap()
                // 添加了一个NioEventLoopGroup组件：用来处理事件
                .group(new NioEventLoopGroup())
                //  channel 实现链接的处理方式，会有nio，也会有 bio
                .channel(NioSocketChannel.class)
                // boss 负责链接， worker 负责读写， child 负责具体的雨雾实现逻辑
                .handler(
                        // 和服务端进行数据读写的通道
                        new ChannelInitializer<NioSocketChannel>() {
                    // handler 中的具体实现代码
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 添加一个handler 增加一个编码器，将数据转换为字节码，用来将bytebuf转换位字符串
                        ch.pipeline().addLast(new StringEncoder());
                        // 添加一个handler，自定义的handler的处理器（读事件）
                    }
                })
                // 监听位置
                .connect(new InetSocketAddress("127.0.0.1",8001))
                .sync()
                .channel()
                // 向服务器发送数据
                .writeAndFlush("hello,world");
    }
}
