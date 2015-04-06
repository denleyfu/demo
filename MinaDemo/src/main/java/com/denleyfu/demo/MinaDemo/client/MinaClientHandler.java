package com.denleyfu.demo.MinaDemo.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

//Mina客户端消息处理器类
public class MinaClientHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		String s = (String) message;
		System.out.println("来自服务器的消息:" + s);
		session.write(s);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务器走了~");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out
				.println("one Client Connection" + session.getRemoteAddress());
		session.write("我来了······");
	}

}
