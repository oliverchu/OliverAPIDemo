package top.iofox.lib.otool.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import top.iofox.lib.otool.BuildConfig;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogUtil {

    private static boolean DEBUG = BuildConfig.DEBUG;
    private final static int LOG_MAX_LENGTH = 2000;

    private static String gTag = "LogUtil";


    public LogUtil t(String tag) {
        gTag = tag;
        return this;
    }

    public static void eLong(String tag, String msg) {
        printLong(Log.ERROR, tag, msg);
    }

    /**
     * 显示超长的log
     *
     * @param tag 该log的TAG
     * @param msg 打印的内容
     */
    public static void dLong(String tag, String msg) {
        printLong(Log.DEBUG, tag, msg);
    }

    private static void printLong(int type, String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        //规定每段显示的长度
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAX_LENGTH;
        for (int i = 0; i < 100; i++) {
            if (strLength > end) {
                Log.println(type, tag + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAX_LENGTH;
            } else {
                Log.println(type, tag, msg.substring(start, strLength));
                break;
            }
        }
    }

    /**
     * 打印超长的格式化json
     *
     * @param type    Log的类型  Log.DEBUG Log.Error etc.
     * @param tag     该log的TAG
     * @param jsonStr 未格式化json字符串
     */
    public static void printJson(int type, String tag, String jsonStr) {
        if (!DEBUG) {
            return;
        }
        try {
            String str = format(jsonStr);
            printLong(type, tag, str);
        } catch (Exception ex) {
            printLong(Log.ERROR, tag, "Error occurred while parsing json: " + jsonStr);
        }

    }

    public static void eJson(String tag, String json) {
        printJson(Log.ERROR, tag, json);
    }

    public static void dJson(String tag, String json) {
        printJson(Log.DEBUG, tag, json);
    }

    private static String format(String jsonStr) {
        int level = 0;
        StringBuilder jsonForMatStr = new StringBuilder();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void eAutoTag(Object parentClz, String msg) {
        e(parentClz.getClass().getSimpleName(), msg);
    }

    public static void dPretty(String msg) {
        Log.d(gTag, "╒════════════════════════════════════════════════════");
        String[] split = msg.split("\n");
        for (String k : split) {
            Log.d(gTag, "│" + k);
        }
        Log.d(gTag, "╘════════════════════════════════════════════════════");
    }


    public static void dPretty(Iterable list) {

        try {
            throw new Exception("Null");
        } catch (Exception ex) {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            String s = stackTrace[1].toString();

            Log.d(gTag, "│." + stackTrace[1].getMethodName() + "(" + stackTrace[1].getFileName() + ":" + stackTrace[1].getLineNumber() + ")");
            Log.d(gTag, "│\t." + stackTrace[2].getMethodName() + "(" + stackTrace[1].getFileName() + ":" + stackTrace[2].getLineNumber() + ")");
        }


        Log.d(gTag, "│" + list.getClass().getName());
        Log.d(gTag, "╞════════════════════════════════════════════════════");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Log.d(gTag, "│" + iterator.next());
        }
        Log.d(gTag, "╘════════════════════════════════════════════════════");
    }

    public static void dPretty(Intent intent) {
        int flags = intent.getFlags();
        Log.d(gTag, "│Data=" + intent.getDataString());
        Log.d(gTag, "│Data=" + intent.getAction());
        Log.d(gTag, "│Type=" + intent.getType());
        if (intent.getExtras() != null) {
            dPretty(intent.getExtras());
        }
        Log.d(gTag, intent.toString());
    }


    private void print() {

    }

    public static void dPretty(Bundle bundle) {
        for (String key : bundle.keySet()) {
            Log.d(gTag, "│" + key + ": " + bundle.get(key));
        }
    }


    public static void dPretty(Set sets) {
        for (Object k : sets) {
            Log.d(gTag, "│" + k.toString());
        }
    }


    public static void dPretty(Map map) {
        Log.d(gTag, "╒════════════════════════════════════════════════════");

        try {
            throw new Exception("Null");
        } catch (Exception ex) {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            String s = stackTrace[1].toString();

            Log.d(gTag, "│." + stackTrace[1].getMethodName() + "(" + stackTrace[1].getFileName() + ":" + stackTrace[1].getLineNumber() + ")");
            Log.d(gTag, "│\t." + stackTrace[2].getMethodName() + "(" + stackTrace[1].getFileName() + ":" + stackTrace[2].getLineNumber() + ")");
        }

        Log.d(gTag, "╞════════════════════════════════════════════════════");
        for (Object k : map.keySet()) {
            Log.d(gTag, "│" + k.toString() + ":" + map.get(k));
        }
        Log.d(gTag, "╘════════════════════════════════════════════════════");


    }

    public static void setDebug(boolean debug) {
        LogUtil.DEBUG = debug;
    }

    public static void dPretty(List list) {
        Log.d(gTag, "╒════════════════════════════════════════════════════");

        try {
            throw new Exception("Null");
        } catch (Exception ex) {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            String s = stackTrace[1].toString();

            Log.d(gTag, "│." + stackTrace[1].getMethodName() + "(" + stackTrace[1].getFileName() + ":" + stackTrace[1].getLineNumber() + ")");
            Log.d(gTag, "│\t." + stackTrace[2].getMethodName() + "(" + stackTrace[1].getFileName() + ":" + stackTrace[2].getLineNumber() + ")");
        }


        Log.d(gTag, "│" + list.getClass().getName());
        Log.d(gTag, "╞════════════════════════════════════════════════════");
        for (Object k : list) {
            Log.d(gTag, "│" + k.toString());
        }
        Log.d(gTag, "╘════════════════════════════════════════════════════");
    }


}
