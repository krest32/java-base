package com.krest.xml.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
    }

    private static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void init() {
        threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static void set(String regex) {
        threadLocal.set(new SimpleDateFormat(regex));
    }

    public static DateFormat get() {
        return threadLocal.get();
    }

    public static String ToString(Date date, String regex) {
        set(regex);
        return get().format(date);
    }

    public static String ToString(String regex) {
        set(regex);
        return get().format(new Date());
    }
}
