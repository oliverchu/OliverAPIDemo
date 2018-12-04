package top.iofox.lib.otool.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by [Oliver Chu] on 2018/11/29 10:27
 */
public class SlideLayout extends FrameLayout {
    private static final String TAG = "SlideLayout";

    private View slidedView, actionView;

    public SlideLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "SlideLayout: ");
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: " + changed);
        init();
    }

    private void init() {
        if (getChildCount() >= 2) {
            actionView = getChildAt(0);
            slidedView = getChildAt(getChildCount() - 1);
            slidedView.setOnTouchListener(new View.OnTouchListener() {
                float startX = 0;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.d(TAG, "onTouch: ");
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getRawX();
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            float offset = event.getRawX() - startX;
                            if (offset < 0) {
                                //左滑
                                if (v.getTranslationX() > -actionView.getWidth()) {
                                    if (v.getTranslationX() - offset < -actionView.getWidth()) {
                                        offset = actionView.getWidth() + v.getTranslationX();
                                    }
                                    v.setTranslationX(v.getTranslationX() - Math.abs(offset));
                                    startX = event.getRawX();
                                } else {
                                    v.setTranslationX(-actionView.getWidth());
                                }
                            } else {
                                //右滑
                                if (v.getTranslationX() < 0) {
                                    v.setTranslationX(v.getTranslationX() + Math.abs(offset));
                                    startX = event.getRawX();
                                } else {

                                    v.setTranslationX(0);
                                }
                            }
                            return false;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
//                            reset();

                            if (Math.abs(v.getTranslationX()) >= actionView.getWidth() / 2) {
                                expand();
                            } else {
                                close();
                            }
                            return false;
                        default:
                            break;
                    }
                    return false;
                }
            });

        }
    }


    public void close() {
        if (slidedView != null) {
            slidedView.animate().translationX(0).setDuration(500).setInterpolator(new DecelerateInterpolator()).start();
        }
    }

    public void expand() {
        if (slidedView != null) {
            if (actionView != null) {
                slidedView.animate().translationX(-actionView.getWidth()).setDuration(500).setInterpolator(new DecelerateInterpolator()).start();
            } else {
                slidedView.animate().translationX(-slidedView.getWidth() + 100).setDuration(500).setInterpolator(new DecelerateInterpolator()).start();
            }
        }
    }

}
