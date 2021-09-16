package com.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Package: mina.protocol
 * 编码
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-16 16:04
 */
public class ProtocolDecoder extends ProtocolDecoderAdapter {
    /**
     * session 上下文
     */
    private final AttributeKey CONTEXT = new AttributeKey(this.getClass(), "context");
    private final Charset charset;

    private int maxPackLength = 100;

    public ProtocolDecoder() {
        this(Charset.defaultCharset());
    }

    public ProtocolDecoder(Charset charset) {
        this.charset = charset;
    }


    public Context getConText(IoSession session) {
        Context ctx = (Context) session.getAttribute(CONTEXT);
        if (ctx == null) {
            ctx = new Context();
            session.setAttribute(CONTEXT, ctx);
        }
        return ctx;
    }

    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        final int packHeadLength = 5;
        Context ctx = this.getConText(session);
        ctx.append(in);
        IoBuffer buf = ctx.getBuf();
        buf.flip();
        while (buf.remaining() >= packHeadLength) {
            buf.mark();
            int length = buf.getInt();
            byte flag = buf.get();
            if (length < 0 || length > maxPackLength) {
                buf.reset();
                break;
            } else if (length >= packHeadLength && length - packHeadLength <= buf.remaining()) {
                int oldLimit = buf.limit();
                buf.limit(buf.position() + length - packHeadLength);
                String content = buf.getString(ctx.getDecoder());
                buf.limit(oldLimit);
                ProtocolPack packAge = new ProtocolPack(flag, content);
                out.write(packAge);
            } else {
                // 半包
                buf.clear();
                break;
            }
        }
        if (buf.hasRemaining()) {
            IoBuffer temp = IoBuffer.allocate(maxPackLength).setAutoExpand(true);
            temp.put(buf);
            temp.flip();
            buf.reset();
            buf.put(temp);
        } else {
            buf.reset();
        }
    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
        super.finishDecode(session, out);
    }

    @Override
    public void dispose(IoSession session) throws Exception {
        Context ctx = (Context) session.getAttribute(CONTEXT);
        if (ctx != null) {
            session.removeAttribute(CONTEXT);
        }
    }

    public int getMaxPackLength() {
        return maxPackLength;
    }

    public void setMaxPackLength(int maxPackLength) {
        if (maxPackLength < 0) {
            throw new IllegalArgumentException("maxParkLength参数：" + maxPackLength);
        }
        this.maxPackLength = maxPackLength;
    }

    private class Context {
        private final CharsetDecoder decoder;
        private IoBuffer buf;

        private Context() {
            decoder = charset.newDecoder();
            buf = IoBuffer.allocate(80).setAutoExpand(true);
        }

        /**
         * 追加数据
         *
         * @param in 追加
         */
        public void append(IoBuffer in) {
            this.getBuf().put(in);
        }

        public void rest() {
            decoder.reset();
        }

        public IoBuffer getBuf() {
            return buf;
        }

        public void setBuf(IoBuffer buf) {
            this.buf = buf;
        }

        public CharsetDecoder getDecoder() {
            return decoder;
        }
    }
}
