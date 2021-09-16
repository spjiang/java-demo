package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Package: com.mina
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-16 09:33
 */
public class MyClientHandle extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        // super.exceptionCaught(session, cause);
        System.out.println("MyClientHandle.exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // super.messageReceived(session, message);
        System.out.println("MyClientHandle.messageReceived");
        String msg = (String) message;
        System.out.println(msg);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // super.messageSent(session, message);
        System.out.println("MyClientHandle.messageSent");
        String msg = (String) message;
        System.out.println(msg);
    }

}
