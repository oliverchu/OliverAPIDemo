package top.iofox.lib.otool.ui.view.listener;

import android.view.View;

/**
 * Created by [Oliver Chu] on 2018/11/18 22:31
 */
public abstract class ClickOnceListener implements View.OnClickListener {

    private boolean isClicked = false;
    private int delayMillis;


    public ClickOnceListener(int delayMillis) {
        this.delayMillis = delayMillis;
    }

    public ClickOnceListener() {
        this.delayMillis = 1000;
    }


    public abstract void clickOnce(View v);

    @Override
    public void onClick(View v) {
        if (!isClicked) {
            isClicked = true;
            clickOnce(v);
            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isClicked = false;
                }
            }, delayMillis);
        }
    }


}
