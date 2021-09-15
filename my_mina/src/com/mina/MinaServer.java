package com.mina;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

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
public class MinaServer {
    static int PORT = 8001;
    static NioSocketAcceptor accept = null;

    public static void main(String[] args) throws IOException {
        accept = new NioSocketAcceptor();
        accept.setReuseAddress(true);
        // 过滤器主要实现协议的编码和解码
        accept.getFilterChain().addLast("codec",new ProtocolCodecFilter(
                new TextLineCodecFactory(StandardCharsets.UTF_8, LineDelimiter.MAC.getValue(),LineDelimiter.MAC.getValue())
        ));
        // 设置读缓冲区的大小,设置buffer的长度
        accept.getSessionConfig().setReadBufferSize(1024);
        // 设置缓冲区读写过期时间,设置连接超时时间,每一次客户端连接服务端的描述（会话），多少时间进入空闲状态，无操作就进入休眠状态
        accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        accept.setHandler(new MyHeadle());
        accept.bind(new InetSocketAddress(PORT));
        System.out.println("Server -> "+PORT);

    }
}
