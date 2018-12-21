package top.iofox.demo.app.oliverapidemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import top.iofox.demo.app.oliverapidemo.base.BaseActivity;
import top.iofox.lib.otool.ui.activity.WebChromeActivity;

/**
 * Created by [Oliver Chu] on 2018/11/10 15:37
 */
public class MainActivity extends BaseActivity {

    private ImageView ivLogo;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, WebChromeActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setIvLogo() {
        ivLogo.setScaleX(0.5f);
        ivLogo.setScaleY(0.5f);
    }

}
