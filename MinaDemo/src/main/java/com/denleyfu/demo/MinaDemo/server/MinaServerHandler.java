package com.denleyfu.demo.MinaDemo.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

//负责处理连接上来的客户机，即消息处理器
public class MinaServerHandler extends IoHandlerAdapter {

	// 客户端发送的消息到达时
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		String s = (String) message;
		System.out.println("来自客户端的消息:" + s);
		session.write(s);
	}

	// 一个客户端关闭时
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("one Client Disconnect");
	}

	// 一个客户端接入时
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("one Client Connection");
	}

}
