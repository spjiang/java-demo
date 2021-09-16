package com.protocol;

import lombok.SneakyThrows;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
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
public class ProtocolClient {

    static IoConnector connector = null;
    static int port = 8888;
    static String host = "127.0.0.1";
    static int fil = 1;
    static long start = 0;

    public static void main(String[] args) throws IOException {
        start = System.currentTimeMillis();

        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new ProtocolFactory(StandardCharsets.UTF_8)
        ));
        // 设置读缓冲区的大小,设置buffer的长度
        connector.getSessionConfig().setReadBufferSize(1024);
        // 设置缓冲区读写过期时间,设置连接超时时间,每一次客户端连接服务端的描述（会话），多少时间进入空闲状态，无操作就进入休眠状态
        connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        connector.setHandler(new MyClientHandle());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress(host, port));
        connectFuture.addListener(new IoFutureListener<IoFuture>() {
            @SneakyThrows
            @Override
            public void operationComplete(IoFuture ioFuture) {
                if (ioFuture.isDone()) {
                    IoSession session = ioFuture.getSession();
                    sendData(session);
                }
            }
        });
    }

    public static void sendData(IoSession session) {
        for (int i = 0; i < fil; i++) {
            String content = "spjiang:" + i;
            ProtocolPack pack = new ProtocolPack((byte) i, content);
            session.write(pack);
            System.out.println("发送信息："+pack);
        }
    }
}
