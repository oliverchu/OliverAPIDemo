package top.iofox.lib.otool.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by [Oliver Chu] on 2018/11/19 3:27
 */
public class ResourceUtil {

    public static final String STRING = "string";
    public static final String COLOR = "color";
    public static final String DRAWABLE = "drawable";

    public static String getString(Context context, String name) {
        return context.getResources().getString(getId(context, name, STRING));
    }

    public static int getColor(Context context, String name) {
        return context.getResources().getColor(getId(context, name, COLOR));
    }

    public static Drawable getDrawable(Context context, String name) {
        return context.getResources().getDrawable(getId(context, name, DRAWABLE));
    }

    public static int getId(Context context, String name, String type) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

}
