package com.protocol;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Package: com.protocol
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-15 23:15
 */
public class MyHandle extends IoHandlerAdapter {

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
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
        ProtocolPack pack = (ProtocolPack) message;
        System.out.println("服务端接收信息：" + pack);
    }
}
