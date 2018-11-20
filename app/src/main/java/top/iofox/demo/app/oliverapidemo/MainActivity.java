package top.iofox.demo.app.oliverapidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import top.iofox.demo.app.oliverapidemo.base.BaseActivity;
import top.iofox.demo.app.oliverapidemo.util.AnnotationTest;
import top.iofox.lib.otool.ui.view.ONotification;
import top.iofox.lib.otool.ui.view.listener.ClickOnceListener;
import top.iofox.lib.otool.util.DeviceUtil;

/**
 * Created by [Oliver Chu] on 2018/11/10 15:37
 */
public class MainActivity extends BaseActivity {

    private ImageView ivLogo;
    private int count = 0;
    private static final String TAG = "MainActivity";

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
//        DeviceUtil.setStatusBar(this, getResources().getColor(R.color.colorPrimary));
        DeviceUtil.setImmerseLayout(this, ivLogo);
        findViewById(R.id.btnClick).setOnClickListener(new ClickOnceListener() {
            @Override
            public void clickOnce(View v) {
                count++;
//                byte[] bytes = CryptoUtil.encryptAes("oliver".getBytes(Charset.defaultCharset()), "test".getBytes(Charset.defaultCharset()));
//                byte[] bytes1 = CryptoUtil.decryptAes("oliver".getBytes(Charset.defaultCharset()), bytes);
//
//                Log.d(TAG, "clickOnce: " + new String(bytes1, Charset.defaultCharset()));

            }
        });

    }


    public void setIvLogo(){
        ivLogo.setScaleX(0.5f);
        ivLogo.setScaleY(0.5f);
    }

}
