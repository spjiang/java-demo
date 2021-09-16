package com.protocol;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
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
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("MyClientHandle.sessionIdle");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("MyClientHandle.messageReceived");
//        String msg = (String) message;
//        System.out.println(msg);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        ProtocolPack msg = (ProtocolPack) message;
        System.out.println("MyClientHandle.messageSent:" + msg);
    }
}
