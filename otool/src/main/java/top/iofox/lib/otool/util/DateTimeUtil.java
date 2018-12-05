package top.iofox.lib.otool.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by [Oliver Chu] on 2018/11/19 3:38
 */
public class DateTimeUtil {
    private static final String TAG = "DateTimeUtil";

    /**
     * 将日期格式化成 yyyy-MM-dd HH:mm:ss 的形式
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(Date date, String pattern) {
        if (date != null) {
            try {
                return new SimpleDateFormat(pattern, Locale.US).format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }


    /**
     * 打印run()方法执行的运行时间
     *
     * @param task 任务
     */
    public static void testForTime(Runnable task) {
        if (task != null) {
            long start = System.currentTimeMillis();
            LogUtil.d(TAG, "testForTime: start at " + start + "ms");
            task.run();
            long end = System.currentTimeMillis();
            LogUtil.d(TAG, "testForTime: finished at " + start + "ms. cost for " + (end - start) + "ms");
        }
    }

    private static long startTime = -1;

    public static void taskStart() {
        startTime = System.currentTimeMillis();
        LogUtil.d(TAG, "testForTime: start at " + startTime + "ms");
    }


    public static long taskFinished() {
        if (startTime == -1) {
            return -1;
        }
        long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        LogUtil.d(TAG, "testForTime: finished at " + endTime + "ms. cost for " + cost + "ms");
        return cost;
    }
}
