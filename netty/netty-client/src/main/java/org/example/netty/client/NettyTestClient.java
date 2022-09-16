package org.example.netty.client;

import com.sun.security.ntlm.Client;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Optional;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年09月16日 10:00
 */
public class NettyTestClient {
	private String host  = "127.0.0.1";
	private int port = 9001;
	public NettyTestClient(String host,int port){
		this.host = host;
		this.port = port;
	}

	public void start(){
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap strap = new Bootstrap();
			strap.group(group)
					.channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress(host,port))
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel sh) throws Exception {
							sh.pipeline().addLast(new ClientHandler());
						}
					});

			ChannelFuture sync = strap.connect().sync();
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
	}


	public static void main(String[] args) {
		new NettyTestClient("127.0.0.1",9001).start();
	}
}
