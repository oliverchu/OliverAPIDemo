package top.iofox.lib.otool.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import top.iofox.lib.otool.R;

/**
 * Created by [Oliver Chu] on 2018/12/11 17:33
 */
public class WebChromeActivity extends Activity {

    private ProgressBar pbLoading;
    private TextView tvTilte;
    private ImageView ivIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_chrome);
        ivIcon = findViewById(R.id.ivIcon);
        tvTilte = findViewById(R.id.tvTitle);
        WebView wvWeb = findViewById(R.id.wvWeb);
        wvWeb.setWebChromeClient(new MyWebChromeClient());
        wvWeb.setWebViewClient(new WebViewClient());
        pbLoading = findViewById(R.id.pbLoading);
        wvWeb.loadUrl("https://baidu.com");
    }


    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            pbLoading.setProgress(newProgress);
            if (newProgress == 100) {
                pbLoading.setVisibility(View.GONE);
            } else {
                pbLoading.setVisibility(View.VISIBLE);
            }
        }


        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            ivIcon.setImageBitmap(icon);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            tvTilte.setText(title);
        }
    }

}
