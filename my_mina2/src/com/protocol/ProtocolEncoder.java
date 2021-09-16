package com.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * Package: mina.protocol
 * 编码
 *
 * @description:
 * @author: jsp <spjiang@aliyun.com>
 * @create: 2021-09-16 16:04
 */
public class ProtocolEncoder extends ProtocolEncoderAdapter {

    final Charset charset;
    public ProtocolEncoder(Charset charset){
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        ProtocolPack pack = (ProtocolPack) message;
        System.out.println("ProtocolEncoder.encode："+pack);
        IoBuffer buf = IoBuffer.allocate(pack.getLength());
        buf.setAutoExpand(true);
        buf.putInt(pack.getLength());
        buf.put(pack.getFlag());
        if (pack.getContent() != null) {
            buf.put(pack.getContent().getBytes());
        }
        // 就是让我们的limit=position,position=0;为我们读取缓冲区的数据做好准备，因为有时候，limit！=position，一般在发送数据之前调用
        buf.flip();
        out.write(buf);
    }
}
