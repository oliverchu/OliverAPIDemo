package top.iofox.lib.otool.util;

import android.content.Context;

/**
 * Created by [Oliver Chu] on 2018/11/19 3:27
 */
public class ResourceUtil {

    public static final String STRING = "string";

    public static String getString(Context context, String name) {
        return context.getResources().getString(getId(context, name, STRING));
    }

    public static int getId(Context context, String name, String type) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

}
