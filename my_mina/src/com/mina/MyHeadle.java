package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * Package: com.mina
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-15 23:15
 */
public class MyHeadle extends IoHandlerAdapter {

    public MyHeadle() {
        super();
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // super.sessionCreated(session);
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // super.sessionOpened(session);
        System.out.println("sessionOpened,打开可以读写数据。。。");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // super.sessionClosed(session);
        System.out.println("sessionClosed,sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // super.sessionIdle(session, status);
        System.out.println("sessionIdle,没有操作空闲状态。。。");
    }

    /**
     * 连接过程出现异常处理过程
     *
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        // super.exceptionCaught(session, cause);
        System.out.println("exceptionCaught,连接出现异常");
    }

    /**
     * 接收数据处理的过程
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // super.messageReceived(session, message);
        System.out.println("messageReceived,接收数据处理的过程");
        String msg = (String) message;
        System.out.println("服务端接收到数据：" + msg);
        if ("exit".equals(msg)){
            session.close(true);
        }
        Date date = new Date();
        session.write("发送数据到客户端...." + date);
    }

    /**
     * 发送数据处理过程
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // super.messageSent(session, message);
        System.out.println("messageSent,发送数据处理过程");
    }
}
