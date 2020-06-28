package com.spjiang;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Package: com.spjiang.map
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-28 09:19
 */
public class MapTest {
    @Test
    public void test01() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("01", "qwe");
        map.put("02", "asd");
        map.put("03", "zxc");
        // 先获取map集合的所有键的set集合，即为map中所有key值得集合
        Set<String> keySet = map.keySet();
        // 有了set集合，就可以获取其迭代器
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            // 有了键可以通过map集合的get方法获取其对应位置
            String value = map.get(key);
            // 获得key和value值
            System.out.println("key:" + key + "-->value:" + value);
        }
    }

    @Test
    public void test02() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("01", "qwe");
        map.put("02", "asd");
        map.put("03", "zxc");
        // 通过entrySet()方法将map集合中的映射关系取出（这个关系就是Map.Entry类型）
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        // 将关系集合entryset进行迭代，存放到迭代器中
        Iterator<Map.Entry<String, String>> it2 = entrySet.iterator();
        while (it2.hasNext()) {
            // 获取Map.Entry关系对象me
            Map.Entry<String, String> me = it2.next();
            // 通过关系对像获取key
            String key2 = me.getKey();
            // 通过关系对像获取value
            String value2 = me.getValue();
            System.out.println("key:" + key2 + "-->value:" + value2);
        }
    }
}
