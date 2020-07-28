import org.junit.Test;

import java.io.*;

/**
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-24 17:13
 */
public class IOTests {

    /**
     * InputStream 使用示例
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        InputStream inputStream = new FileInputStream("1.txt");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String str = new String(bytes, "utf-8");
        System.out.println(str);
        inputStream.close();
    }

    /**
     * OutputStream 使用示例
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        OutputStream outputStream = new FileOutputStream("log.txt", true); // 参数二，表示是否追加，true=追加
        outputStream.write("你好，老王".getBytes("utf-8"));
        outputStream.close();
    }

    /**
     * Writer 使用示例
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        Writer writer = new FileWriter("log1.txt", true); // 参数二，是否追加文件，true=追加
        writer.append("老王，你好");
        writer.close();
    }

    /**
     * Reader
     *
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        Reader reader = new FileReader("1.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer bf = new StringBuffer();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            bf.append(str + "\n");
        }
        bufferedReader.close();
        reader.close();
        System.out.println(bf.toString());
    }
}
