package com.krest.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Sharable表示此对象在channel间共享
 * handler类是我们的具体业务类
 */
@ChannelHandler.Sharable//注解@Sharable可以让它在channels间共
public class ServerHelloWorldHandler extends ChannelHandlerAdapter {

    private String shutDownString = "exit";
    /**
     * 处理 Channel 传递过来的事件，并且进行处理
     *
     * @param ctx
     * @param msg 具体传递的消息内容
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf readBuffer = (ByteBuf) msg;
        byte[] tmpeDatas = new byte[readBuffer.readableBytes()];
        readBuffer.readBytes(tmpeDatas);
        String message = new String(tmpeDatas, "UTF-8");
        System.out.println("from client:" + message);
        if (shutDownString.equals(message)) {
            ctx.close();
            System.out.println("连接关闭");
            return;
        } else {
            System.out.println("to client");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));
        }
    }

    /**
     * 异常处理方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("server Exception Caught Method Run...");
        ctx.close();
    }
}
