package org.example.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年09月16日 10:49
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf buf) throws Exception {
		System.out.println("client receive" + buf.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("connect success,please input msg...");
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			String s = in.nextLine();
			if ("break".equals(s)){
				break;
			}
			ByteBuf buffer = ctx.alloc().buffer(10);
			buffer.writeBytes(s.getBytes());
			final ChannelFuture f = ctx.writeAndFlush(buffer);
		}
		ByteBuf buffer = ctx.alloc().buffer(10);
		buffer.writeBytes("break".getBytes());
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("connect lose");
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
