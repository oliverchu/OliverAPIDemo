package top.iofox.lib.otool.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ImageDecoder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import top.iofox.lib.otool.R;
import top.iofox.lib.otool.util.DeviceUtil;


public class ContentLayout extends LinearLayout {

    private static final String TAG = "ContentLayout";

    public static final int EMPTY_DATA = 1;
    public static final int NETWORK_ERROR = -1;
    public static final int NORMAL = 0;

    private LayoutInflater inflater;
    private View vEmptyData, vNetworkError, vContent, vLoading, vHeader;
    private int statusBarHeight = 0, headerHeight = 0;
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public ContentLayout(Context context) {
        this(context, null);
    }

    public ContentLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        setOrientation(VERTICAL);
        inflater = LayoutInflater.from(getContext());
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ContentLayout);
        vHeader = inflateLayout(array, R.styleable.ContentLayout_header_layout, vHeader, VISIBLE);
        vEmptyData = inflateLayout(array, R.styleable.ContentLayout_empty_layout, vEmptyData, GONE);
        vNetworkError = inflateLayout(array, R.styleable.ContentLayout_network_error_layout, vNetworkError, GONE);
        vLoading = inflateLayout(array, R.styleable.ContentLayout_loading_layout, vLoading, GONE);
        headerHeight = array.getDimensionPixelSize(R.styleable.ContentLayout_header_height, 0);
        setHeaderHeight();
        boolean headerShowLeftButton = array.getBoolean(R.styleable.ContentLayout_header_show_left_button, true);
        toggleVisibility(findViewById(R.id.ibHome), headerShowLeftButton ? VISIBLE : INVISIBLE);
        String headerTitle = array.getString(R.styleable.ContentLayout_header_title);
        if (headerTitle != null) {
            headerTitle(headerTitle);
        }
        ImageDecoder.Source source = ImageDecoder.createSource(getResources(), R.drawable.ic_keyboard_arrow_left_black_24dp);

        array.recycle();
    }

    public void setupImmersiveBar(Window window) {
        statusBarHeight = DeviceUtil.setImmerseLayout(window);
        Log.d(TAG, "setupImmersiveBar: " + statusBarHeight + "  " + (vHeader == null));
        setHeaderHeight();
    }

    private void setHeaderHeight() {
        if (vHeader != null) {
            FrameLayout layout = (FrameLayout) vHeader;
            ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) layout.getLayoutParams();
            layoutParams.height = statusBarHeight + headerHeight;
            layout.setLayoutParams(layoutParams);
        }
    }

    private View inflateLayout(TypedArray array, int id, View viewType, int visibility) {
        if (array != null) {
            int headerId = array.getResourceId(id, -1);
            if (headerId != -1) {
                return getReplacedView(viewType, headerId, visibility);
            }
        }
        return null;
    }


    public void addHeaderLayout(int layoutId) {

    }

    public void setEmptyDataView(int res) {
        vEmptyData = getReplacedView(vEmptyData, res, View.GONE);
    }

    public void setNetworkErrorView(int res) {
        vNetworkError = getReplacedView(vNetworkError, res, View.GONE);
    }

    public void setContentView(int res) {
        vContent = getReplacedView(vContent, res, View.VISIBLE);
    }

    private View getReplacedView(View view, int res, int initStatus) {
        if (view != null) {
            removeView(view);
            view = null;
        }
        view = inflater.inflate(res, this, false);
        addView(view);
        view.setVisibility(initStatus);
        return view;
    }

    /**
     * @param status One of {@link #EMPTY_DATA},{@link #NORMAL}, or {@link #NETWORK_ERROR}.
     */
    public void setStatus(@Status int status) {
        toggleVisibility(vEmptyData, status == EMPTY_DATA ? View.VISIBLE : View.GONE);
        toggleVisibility(vNetworkError, status == NETWORK_ERROR ? View.VISIBLE : View.GONE);
        toggleVisibility(vContent, status == NORMAL ? View.VISIBLE : GONE);
    }

    private void toggleVisibility(View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    public <T extends View> T findViewById(int id, Class<T> type) {
        View view = findViewById(id);
        if (view != null) {
            return (T) view;
        }
        return null;
    }

    public ContentLayout headerTitle(String title) {
        TextView tvTitle = findViewById(R.id.tvTitle);
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
        return this;
    }


}
