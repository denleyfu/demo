package com.denleyfu.demo.MinaDemo.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class StartClient {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// create tcp/ip connector
		NioSocketConnector connector = new NioSocketConnector();
		// 创建接收数据的过滤器
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		// 设定这个过滤器将一行一行读数据
		chain.addLast("myChin", new ProtocolCodecFilter(
				new TextLineCodecFactory()));
		connector.setHandler(new MinaClientHandler());
		connector.setConnectTimeout(30);
		// 连接到服务器
		ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",
				10000));
		// wait for the connection attempt to be finished
		cf.awaitUninterruptibly();
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
}
