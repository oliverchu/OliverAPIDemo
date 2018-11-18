package top.iofox.lib.otool.ui.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import top.iofox.lib.otool.util.ResourceUtil;


/**
 * Created by [Oliver Chu] on 2018/11/18 22:25
 */
public class OToast {
    private static final String TAG = "OToast";

    private OToast() {
    }

    private static int backgroundColor = 0xdeffffff;
    private static int textColor = 0xff000000;

    private static void init(@LayoutRes int layoutRes) {

    }

    public static void show(@NonNull Context context, @NonNull CharSequence text, int duration, int gravity, int xOffset, int yOffset) {
        getToast(context, text, duration, gravity, xOffset, yOffset).show();
    }

    public static void showBottom(@NonNull Context context, @NonNull CharSequence text, int duration) {
        show(context, text, duration, -1, 0, 0);
    }

    public static void shortBottom(@NonNull Context context, @NonNull CharSequence text) {
        showBottom(context, text, Toast.LENGTH_SHORT);
    }

    public static void longBottom(@NonNull Context context, @NonNull CharSequence text) {
        showBottom(context, text, Toast.LENGTH_LONG);
    }

    public static void shortCenter(Context context, @NonNull CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT, Gravity.CENTER, 0, 0);
    }

    public static void longCenter(Context context, @NonNull CharSequence text) {
        show(context, text, Toast.LENGTH_LONG, Gravity.CENTER, 0, 0);
    }

    public static Toast getToast(@NonNull Context context, @NonNull CharSequence text, int duration, int gravity, int xOffset, int yOffset) {
        Toast toast = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(ResourceUtil.getId(context, "otoast_layout", "layout"), null);
        TextView tvMessage = v.findViewById(ResourceUtil.getId(context, "tvMessage", "id"));
        tvMessage.setText(text);
        toast.setView(v);
        toast.setDuration(duration);
        if (gravity != -1) {
            toast.setGravity(gravity, xOffset, yOffset);
        }
        return toast;
    }


}
