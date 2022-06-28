package com.krest.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CliemtHelloWprldHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        try {
            ByteBuf readBuffer = (ByteBuf) msg;
            byte[] tempDatas = new byte[readBuffer.readableBytes()];
            readBuffer.readBytes(tempDatas);
            System.out.println("from server:" + new String(tempDatas, "UTF-8"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            ctx.writeAndFlush(Unpooled.copiedBuffer(line.getBytes("UTF-8")));

        } finally {
            ReferenceCountUtil.release(msg);
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("client exceptionCanght method run...");
        ctx.close();
    }
}