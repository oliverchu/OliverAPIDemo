package top.iofox.lib.otool.util;

import android.util.Log;

import java.util.List;
import java.util.Locale;

/**
 * Created by [Oliver Chu] on 2018/12/7 10:58
 */
public class CLog {

    private static String mTag = "CLog";

    private static String[] HEARDER_LINE = {
            "┌─────────────────────────────────────────",

            "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━",
    };

    private static String[] HORIZONTAL_RULE_LINE = {
            "├─────────────────────────────────────────",

            "┠━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━",
    };

    private static String[] FOOTER_LINE = {
            "└─────────────────────────────────────────",

            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━",
    };
    private static String[] CONTENT_LINE = {
            "│",

            "┃"
    };

    public enum LineStyle {
        LIGHT, BOLD
    }

    private static boolean debug = true;


    private static int lineStyle = LineStyle.LIGHT.ordinal();


    public static void d(String message) {
        d(mTag, message);
    }

    public static void e(String message) {
        e(mTag, message);
    }

    public static void i(String message) {
        i(mTag, message);
    }

    public static void v(String message) {
        v(mTag, message);
    }

    public static void w(String message) {
        w(mTag, message);
    }

    public static void wtf(String message) {
        wtf(mTag, message);
    }

    public static void d(String tag, String message) {
        print(Log.DEBUG, tag, message);
    }

    public static void e(String tag, String message) {
        print(Log.ERROR, tag, message);
    }

    public static void i(String tag, String message) {
        print(Log.INFO, tag, message);
    }

    public static void v(String tag, String message) {
        print(Log.VERBOSE, tag, message);
    }

    public static void w(String tag, String message) {
        print(Log.WARN, tag, message);
    }

    public static void wtf(String tag, String message) {
        print(Log.ASSERT, tag, message);
    }


    public static final int DEBUG = Log.DEBUG;
    public static final int WARN = Log.WARN;
    public static final int INFO = Log.INFO;
    public static final int VERBOSE = Log.VERBOSE;
    public static final int ERROR = Log.ERROR;
    public static final int ASSERT = Log.ASSERT;

    public static void format(int priority, String pattern, Object... args) {
        format(mTag, priority, pattern, args);
    }

    public static void format(String tag, int priority, String pattern, Object... args) {
        if (debug) {
            print(priority, tag, String.format(Locale.US, pattern, args));
        }
    }

    public static void print(int priority, String tag, List list) {
        if (debug) {
            StringBuilder sb = new StringBuilder("[");
            if (list != null && list.size() > 0) {
                sb.append("\n");
                for (Object v : list) {
                    sb.append("\t").append(v).append("\n");
                }
            }
            sb.append("]");
            print(priority, tag, sb.toString());
        }
    }

    public static void print(int priority, String tag, String msg) {
        if (debug) {
            header(priority, tag);
            String[] split = msg.split("\n");
            for (String s : split) {
                content(priority, tag, s);
            }
            footer(priority, tag);
        }

    }

    private static void header(int priority, String tag) {
        Log.println(priority, tag, HEARDER_LINE[lineStyle]);
    }

    private static void horizontalRule(int priority, String tag) {
        Log.println(priority, tag, HORIZONTAL_RULE_LINE[lineStyle]);
    }

    public static void setLineStyle(Enum<LineStyle> lineStyle) {
        CLog.lineStyle = lineStyle.ordinal();
    }

    private static void footer(int priority, String tag) {
        Log.println(priority, tag, FOOTER_LINE[lineStyle]);
    }

    private static void content(int priority, String tag, String msg) {
        Log.println(priority, tag, CONTENT_LINE[lineStyle] + msg);
    }

    public static void setDebug(boolean debug) {
        CLog.debug = debug;
    }
}
