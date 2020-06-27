
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 非阻塞
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-24 17:13
 */
public class BlockingNIO2Tests {


    // 客户端
    @Test
    public void client() throws IOException {
        // 1、获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        //2、分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3、读取本地文件，并发送到服务端
        while (inChannel.read(buf) != 1) {
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.shutdownOutput();

        // 接受服务端反馈
        int len = 0;
        while ((len = sChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len));
            buf.clear();
        }

        // 4、关闭通道
        inChannel.close();
        sChannel.close();
    }

    // 服务端
    @Test
    public void server() throws IOException {
        // 1、获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 2、绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        // 3、获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        // 4、分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5、接收客户端的数据，并保存在本地

        while (sChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        // 发送反馈给客户端
        buf.get("服务端接受数据成功".getBytes());
        buf.flip();
        sChannel.write(buf);

        sChannel.shutdownOutput();
        // 6、关闭通道
        sChannel.close();
        ssChannel.close();
        outChannel.close();


    }
}
