package com.denleyfu.demo.MinaDemo.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class StartServer {
	public static void main(String[] args) {
		// 创建一个非阻塞的serever端socket，用Nio
		SocketAcceptor acceptor = new NioSocketAcceptor();
		// 创建接收数据的过滤器
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		// 设定这个过滤器规则(将一行一行读取数据)
		chain.addLast("myChin", new ProtocolCodecFilter(
				new TextLineCodecFactory()));
		// 设定服务器端的消息处理器：一个MinaServerHandler对象
		acceptor.setHandler(new MinaServerHandler());
		// 服务器端绑定的端口
		int bindPort = 10000;
		// 绑定端口，启动服务器
		try {
			acceptor.bind(new InetSocketAddress(bindPort));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Mina server is listing on:=" + bindPort);
	}
}
