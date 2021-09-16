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

    private final Charset charset;
    public ProtocolEncoder(Charset charset){
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        ProtocolPack value = (ProtocolPack) message;
        IoBuffer buf = IoBuffer.allocate(value.getLength());
        buf.setAutoExpand(true);
        buf.putInt(value.getLength());
        buf.put(value.getFlag());
        if (value.getContent() != null) {
            buf.put(value.getContent().getBytes());
        }
        // 就是让我们的limit=position,position=0;为我们读取缓冲区的数据做好准备，因为有时候，limit！=position，一般在发送数据之前调用
        buf.flip();
        out.write(buf);
    }
}
