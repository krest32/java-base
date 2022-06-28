package com.krest.netty01.hellonetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;


/**
 * @author: krest
 * @date: 2021/5/24 01:15
 * @description:  netty服务端代码
 */
public class HelloServer {
    public static void main(String[] args) {
        // ServerBootstrap：服务器端的一个启动器，将netty提供的各种组件组合在一起进行使用
        new ServerBootstrap()
                // BossEventGroup 和 WorkerGroup ：包含selector用来监听事件，同时配合线程来处理事件
                // 添加了一个NioEventLoopGroup组件：用来处理事件
                .group(new NioEventLoopGroup())
                //  channel 实现链接的处理方式，会有nio，也会有 bio
                .channel(NioServerSocketChannel.class)
                // boss 负责链接， worker 负责读写， child 负责具体的雨雾实现逻辑
                .childHandler(
                        // 和客户端进行数据读写的通道
                        new ChannelInitializer<NioSocketChannel>() {
                    // handler 中的具体实现代码
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 添加一个handler 解码器，将客户端传来的字节码转换位字符串
                        ch.pipeline().addLast(new StringDecoder());
                        // 添加一个handler，自定义的handler的处理器（读事件）
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            // 输出打印方法
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                // 监听位置
                .bind(8001);
    }
}
