package top.iofox.lib.otool.util;

/**
 * Created by [Oliver Chu] on 2018/11/19 3:38
 */
public class TimeUtil {

    public static final String NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TAG = "TimeUtil";



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
