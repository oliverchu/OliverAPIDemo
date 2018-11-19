package top.iofox.lib.otool.ui.view;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import top.iofox.lib.otool.util.ResourceUtil;

/**
 * Created by [Oliver Chu] on 2018/11/19 11:58
 */
public class ODialog extends Dialog {

    public ODialog(Context context) {
        super(context);
        init();
    }

    public ODialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public ODialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    private void init() {
        View inflate = getLayoutInflater().inflate(ResourceUtil.getId(getContext(), "o_loading_dialog", "layout"), null);
        setContentView(inflate);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(null);
        }
    }
}
