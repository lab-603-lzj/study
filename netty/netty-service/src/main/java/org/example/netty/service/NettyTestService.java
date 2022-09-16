package org.example.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年09月16日 9:17
 */
public class NettyTestService {
	public void bind(int port){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap strap = new ServerBootstrap();
			strap.group(bossGroup,workGroup)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel sh) throws Exception {
							sh.pipeline().addLast();
							sh.pipeline().addLast(new ServiceHandler());
						}
					});
			ChannelFuture sync = strap.bind(port).sync();
			sync.channel().closeFuture().sync();
		}catch (Exception e){
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port = 9001;
		if (args!= null && args.length>0){
			try {
				port = Integer.valueOf(args[0]);
			}catch (NumberFormatException e){

			}
		}
		new NettyTestService().bind(port);
	}
}
