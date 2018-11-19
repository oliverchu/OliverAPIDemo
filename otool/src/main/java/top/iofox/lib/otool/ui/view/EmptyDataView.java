package top.iofox.lib.otool.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import top.iofox.lib.otool.util.LogUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class EmptyDataView extends FrameLayout {

    public static final int EMPTY_DATA = 1;
    public static final int NETWORK_ERROR = -1;
    public static final int NORMAL = 0;

    private LayoutInflater inflater;
    private View vEmptyDataView, vNetworkErrorView, vContent;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public EmptyDataView(Context context) {
        this(context, null);
    }

    public EmptyDataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflater = LayoutInflater.from(getContext());
    }

    public void setEmptyDataView(int res) {
        vEmptyDataView = getReplacedView(vEmptyDataView, res, View.GONE);
    }

    public void setNetworkErrorView(int res) {
        vNetworkErrorView = getReplacedView(vNetworkErrorView, res, View.GONE);
    }

    public void setContentView(int res) {
        vContent = getReplacedView(vContent, res, View.VISIBLE);
    }

    private View getReplacedView(View view, int res, int initStatus) {
        if (view != null) {
            removeView(view);
            view = null;
        }
        View newView = inflater.inflate(res, this, false);
        addView(newView);
        newView.setVisibility(initStatus);
        return newView;
    }

    /**
     * @param status One of {@link #EMPTY_DATA},{@link #NORMAL}, or {@link #NETWORK_ERROR}.
     */
    public void setStatus(@Status int status) {
        toggleVisibility(vEmptyDataView, status == EMPTY_DATA ? View.VISIBLE : View.GONE);
        toggleVisibility(vNetworkErrorView, status == NETWORK_ERROR ? View.VISIBLE : View.GONE);
        toggleVisibility(vContent, status == NORMAL ? View.VISIBLE : GONE);
    }

    private void toggleVisibility(View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        } else {
            LogUtil.eAutoTag(this, "Null");
        }
    }

}
