package top.iofox.lib.otool.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import top.iofox.lib.otool.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class ContentLayout extends LinearLayout {

    public static final int EMPTY_DATA = 1;
    public static final int NETWORK_ERROR = -1;
    public static final int NORMAL = 0;

    private LayoutInflater inflater;
    private View vEmptyData, vNetworkError, vContent, vLoading, vHeader;

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
        inflateLayout(array, R.styleable.ContentLayout_header_layout, vHeader, VISIBLE);
        inflateLayout(array, R.styleable.ContentLayout_empty_layout, vEmptyData, GONE);
        inflateLayout(array, R.styleable.ContentLayout_network_error_layout, vNetworkError, GONE);
        inflateLayout(array, R.styleable.ContentLayout_loading_layout, vLoading, GONE);
        boolean headerShowLeftButton = array.getBoolean(R.styleable.ContentLayout_header_show_left_button, true);
        toggleVisibility(findViewById(R.id.ibHome), headerShowLeftButton ? VISIBLE : INVISIBLE);
        String headerTitle = array.getString(R.styleable.ContentLayout_header_title);
        if (headerTitle != null) {
            headerTitle(headerTitle);
        }
        array.recycle();
    }

    private void inflateLayout(TypedArray array, int id, View viewType, int visibility) {
        if (array != null) {
            int headerId = array.getResourceId(id, -1);
            if (headerId != -1) {
                getReplacedView(viewType, headerId, visibility);
            }
        }
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
        View newView = inflater.inflate(res, this, false);
        addView(newView);
        newView.setVisibility(initStatus);
        return newView;
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
