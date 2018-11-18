package top.iofox.demo.app.oliverapidemo;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import top.iofox.demo.app.oliverapidemo.base.BaseActivity;
import top.iofox.demo.app.oliverapidemo.util.AnnotationTest;
import top.iofox.lib.otool.ui.view.ONotification;
import top.iofox.lib.otool.ui.view.OToast;
import top.iofox.lib.otool.ui.view.listener.ClickOnceListener;

/**
 * Created by [Oliver Chu] on 2018/11/10 15:37
 */
public class MainActivity extends BaseActivity {

    private  View ivLogo;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivLogo = findViewById(R.id.ivLogo);
        AnnotationTest test = new AnnotationTest();
        test.WorkerThread();
        test.UiThread();
        test.MainThread();
        final ONotification notification = new ONotification(this);
        findViewById(R.id.btnClick).setOnClickListener(new ClickOnceListener() {
            @Override
            public void clickOnce(View v) {
                count++;
                OToast.longBottom(MainActivity.this, "XX-X--XX-" + count);
                notification.notice("1", "" + count, MainActivity.class);
            }
        });
    }


    public void setIvLogo(){
        ivLogo.setScaleX(0.5f);
        ivLogo.setScaleY(0.5f);
    }

}
