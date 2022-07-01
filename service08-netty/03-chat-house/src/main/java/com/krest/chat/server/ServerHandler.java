package com.krest.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.atomic.AtomicReference;


/**
 * 处理客户端事件，共享信息
 */
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup clientChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        clientChannels.add(channel);

        String sendMsg = "客户[" + channel.remoteAddress() + "]上线\n";
        System.out.print(sendMsg);
        clientChannels.forEach(clientChannel -> {
            if (clientChannel != channel) {
                clientChannel.writeAndFlush(sendMsg);
            } else {
                clientChannel.writeAndFlush("欢迎您上线\n");
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (clientChannels.contains(channel)) {
            clientChannels.remove(channel);
            String sendMsg = "客户[" + channel.remoteAddress() + "]异常下线\n";
            System.out.print(sendMsg);
            clientChannels.forEach(clientChannel -> clientChannel.writeAndFlush(sendMsg));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        AtomicReference<String> sendMsg = new AtomicReference<>("客户[" + channel.remoteAddress() + "]消息: " + msg + "\n");
        if (msg instanceof String && msg.equals("quit")) {
            clientChannels.remove(channel);
            channel.close();
            sendMsg.set("客户[" + channel.remoteAddress() + "]下线\n");
            System.out.print(sendMsg.get());
        }
        clientChannels.forEach(clientChannel -> {
            if (clientChannel != channel) {
                clientChannel.writeAndFlush(sendMsg.get());
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        clientChannels.remove(channel);

        String msg = cause.getMessage();
        String sendMsg = "客户[" + channel.remoteAddress() + "]异常: " + msg + "\n";
        System.out.print(sendMsg);
        clientChannels.forEach(clientChannel -> clientChannel.writeAndFlush(sendMsg));
    }
}