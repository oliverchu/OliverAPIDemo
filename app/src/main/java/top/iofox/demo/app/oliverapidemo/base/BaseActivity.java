package top.iofox.demo.app.oliverapidemo.base;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/***************************************
 * Description: []
 * Created by [Oliver Chu] on 2018/11/10 15:36
 * Version: [1]
 ***************************************
 * Update:
 * 1.[Oliver Chu] 创建文件;编写初始代码
 * 2.[NAME] LOG_HERE
 *
 ***************************************/
public class BaseActivity extends AppCompatActivity {

    public <T extends View> T findViewById(int id, Class<T> type) {
        View view = findViewById(id);
        if (view != null) {
            return (T) view;
        }
        return null;
    }
}
