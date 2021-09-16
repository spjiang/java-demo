package com.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Package: com.mina
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-15 21:49
 */
public class MinaClient {

    static IoConnector connector = null;

    public static void main(String[] args) throws IOException {
        IoSession session = null;

        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(StandardCharsets.UTF_8, LineDelimiter.MAC.getValue(), LineDelimiter.MAC.getValue())
        ));
        connector.setHandler(new MyClientHandle());
        int port = 8001;
        String host = "127.0.0.1";
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress(host, port));
        // 等待我们的连接
        connectFuture.awaitUninterruptibly();
        session = connectFuture.getSession();
        session.write("发送信息：您好");
        // 等待关闭连接
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();


    }
}
