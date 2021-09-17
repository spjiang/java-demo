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

//    public ProtocolDecoder() {
//        this(Charset.defaultCharset());
//        System.out.println("ProtocolDecoder.ProtocolDecoder");
//    }

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
        System.out.println("++++ProtocolDecoder.decode++++");
        final int packHeadLength = 5;
        Context ctx = this.getConText(session);
        ctx.append(in);
        IoBuffer buf = ctx.getBuf();
        buf.flip();
        System.out.println("++++ProtocolDecoder.decode.while.buf.position.0++++" + buf.position()); // 0
        while (buf.remaining() >= packHeadLength) {
            System.out.println("++++ProtocolDecoder.decode.while++++");
            buf.mark();
            int length = buf.getInt();
            System.out.println("++++ProtocolDecoder.decode.while.buf.length.position++++" + buf.position()); // 4,18
            System.out.println("++++ProtocolDecoder.decode.while.buf.length.limit++++" + buf.limit()); // 42,42
            System.out.println("++++ProtocolDecoder.decode.while.length++++" + length); // 14,14
            byte flag = buf.get();
            System.out.println("++++ProtocolDecoder.decode.while.buf.flag.position++++" + buf.position()); // 5,19
            System.out.println("++++ProtocolDecoder.decode.while.buf.flag.limit++++" + buf.limit()); // 42,42
            System.out.println("++++ProtocolDecoder.decode.while.flag++++" + flag); // 0,1
            System.out.println("++++ProtocolDecoder.decode.while.buf.remaining++++" + buf.remaining()); // 37,23(14+5=19)
            if (length < 0 || length > maxPackLength) {
                System.out.println("++++ProtocolDecoder.decode.while.buf.reset++++");
                buf.reset();
                break;
            } else if (length >= packHeadLength && length - packHeadLength <= buf.remaining()) {
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常++++");
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.(length - packHeadLength)++++" + (length - packHeadLength)); // 9,9
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.buf.remaining++++" + buf.remaining()); // 37,23
                int oldLimit = buf.limit();
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.oldLimit++++" + oldLimit); // 42,42,
                buf.limit(buf.position() + length - packHeadLength);
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.position++++" + buf.position()); // 5,19
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.buf.position() + length - packHeadLength++++" + (buf.position() + (length - packHeadLength))); // 14,28
                String content = buf.getString(ctx.getDecoder());
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.content++++" + content);
                buf.limit(oldLimit);
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.position.oldLimit++++" + oldLimit); // 42,42
                ProtocolPack pack = new ProtocolPack(flag, content);
                System.out.println("++++ProtocolDecoder.decode.while.buf.正常.position.2++++" + buf.position()); // 14,28
                out.write(pack);
            } else {
                System.out.println("++++ProtocolDecoder.decode.while.buf.半包++++");
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
