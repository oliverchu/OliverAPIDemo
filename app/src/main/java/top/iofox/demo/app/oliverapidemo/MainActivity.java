package top.iofox.demo.app.oliverapidemo;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import top.iofox.demo.app.oliverapidemo.base.BaseActivity;
import top.iofox.demo.app.oliverapidemo.util.AnnotationTest;
import top.iofox.lib.otool.ui.activity.WebActivity;
import top.iofox.lib.otool.ui.view.SlideLayout;
import top.iofox.lib.otool.util.IntentUtil;

/**
 * Created by [Oliver Chu] on 2018/11/10 15:37
 */
public class MainActivity extends BaseActivity {

    private ImageView ivLogo;
    private int count = 0;
    private static final String TAG = "MainActivity";
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etInput = findViewById(R.id.etInput);
        etInput.setCursorVisible(false);
        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                findViewById(R.id.slLayout, SlideLayout.class).close();
                IntentUtil.getBitmapFromSystem(MainActivity.this,"");
            }
        });
        AnnotationTest test = new AnnotationTest();
        test.WorkerThread();
        test.UiThread();
        test.MainThread();
        ivLogo.setImageResource(R.drawable.ic_webp);
//        startActivity(new Intent(this,WebActivity.class));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = IntentUtil.resolveBitmapFromSystem(this, requestCode, resultCode, data);
        ivLogo.setImageBitmap(bitmap);
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
