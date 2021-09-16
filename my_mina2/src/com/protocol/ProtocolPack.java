package com.protocol;

/**
 * Package: mina.protocol
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-16 15:53
 */
public class ProtocolPack {
    private int length;
    private byte flag;
    private String content;

    public ProtocolPack(byte flag, String content) {
        this.flag = flag;
        this.content = content;
        int len1 = content == null ? 0 : content.getBytes().length;
        // 5 = int length 4个字节，加上，flag byte 长度为 1 ，所以包头长度为5；
        this.length = 5 + len1;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ProtocolPack{" +
                "length=" + length +
                ", flag=" + flag +
                ", content='" + content + '\'' +
                '}';
    }
}
