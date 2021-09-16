package com.protocol;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import java.nio.charset.Charset;

/**
 * Package: com.mina
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-15 23:15
 */
public class ProtocolFactory implements ProtocolCodecFactory {

    private final ProtocolDecoder decoder;
    private final ProtocolEncoder encoder;

    public ProtocolFactory(Charset charset) {
        encoder = new ProtocolEncoder(charset);
        decoder = new ProtocolDecoder(charset);
    }


    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        System.out.println("ProtocolFactory.getEncoder");
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        System.out.println("ProtocolFactory.getDecoder");
        return decoder;
    }
}
