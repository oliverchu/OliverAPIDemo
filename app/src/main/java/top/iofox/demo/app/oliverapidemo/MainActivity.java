package top.iofox.demo.app.oliverapidemo;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import top.iofox.demo.app.oliverapidemo.base.BaseActivity;
import top.iofox.demo.app.oliverapidemo.util.AnnotationTest;

/***************************************
 * Description: []
 * Created by [Oliver Chu] on 2018/11/10 15:37
 * Version: [1]
 ***************************************
 * Update:
 * 1.[Oliver Chu] 创建文件;编写初始代码
 * 2.[NAME] LOG_HERE
 *
 ***************************************/
public class MainActivity extends BaseActivity {

    private  View ivLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivLogo = findViewById(R.id.ivLogo);
        AnnotationTest test = new AnnotationTest();
        test.WorkerThread();
        test.UiThread();
        test.MainThread();

    }


    public void setIvLogo(){
        ivLogo.setScaleX(0.5f);
        ivLogo.setScaleY(0.5f);
    }

}
