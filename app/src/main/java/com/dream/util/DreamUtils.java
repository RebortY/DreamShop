package com.dream.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/26 20:07
 */
public class DreamUtils {

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 格式化事件
     *
     * @param time   long 型时间  注意单位是 秒
     * @param format 如  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatSecTime(long time, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time * 1000L));
    }

    /**
     * 格式化事件
     *
     * @param time   long 型时间  注意单位是 毫秒
     * @param format 如  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatTime(long time, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }
}
