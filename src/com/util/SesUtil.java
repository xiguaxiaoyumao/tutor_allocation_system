package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.会话类，将程序运行长期锁需要的数据保存在map中
 * 2.在需要使用的时候通过静态函数获取
 * @date 2020/10/25 11:51
 */
public class SesUtil {
    private static Map<String, Object> map = null;

    //静态块 在类加载的时候就会执行 （任何引用到该类的时候都会触发类加载，一次执行只会一次类加载）
    static {
        map = new HashMap<>();
    }
    //存本次会话长期要用的数据
    public static void setObject(String string, Object o) {
        map.put(string, o);
    }
    //取本次会话长期要用的数据
    public static Object getObject(String string) {
        Object o = map.get(string);
        return o;
    }
}
