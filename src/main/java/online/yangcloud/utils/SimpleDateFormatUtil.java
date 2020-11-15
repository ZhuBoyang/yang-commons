package online.yangcloud.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程安全下的时间日期格式化工具类
 *
 * @author zhuby
 * @since 2020/11/4 3:11 下午
 */

public class SimpleDateFormatUtil {

    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 线程安全转换 String to Date
     */
    public static Date safeParseDate(String dateStr) throws ParseException {
        return getFormat().parse(dateStr);
    }

    /**
     * 线程安全格式化 Date to String
     */
    public static String safeFormatDate(Date date) {
        return getFormat().format(date);
    }

    /**
     * 借助 ThreadLocal 完成对每个线程第一次调用时初始化 SimpleDateFormat 对象
     */
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    /**
     * 获取当前线程中的安全 SimpleDateFormat 对象
     */
    private static DateFormat getFormat() {
        return threadLocal.get();
    }

}
