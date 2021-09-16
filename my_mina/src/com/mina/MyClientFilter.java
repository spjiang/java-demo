package com.mina;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

/**
 * Package: com.mina
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-16 11:53
 */
public class MyClientFilter extends IoFilterAdapter {
    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        // super.messageReceived(nextFilter, session, message);
        System.out.println("MyClientFilter.messageReceived");
        nextFilter.messageReceived(session, message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        // super.messageSent(nextFilter, session, writeRequest);
        System.out.println("MyClientFilter.messageSent");
        nextFilter.messageSent(session, writeRequest);
    }
}
