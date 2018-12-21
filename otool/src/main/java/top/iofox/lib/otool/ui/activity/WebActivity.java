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
import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;
import top.iofox.lib.otool.R;

public class WebActivity extends AppCompatActivity {

    private ImageView ivFavicon;
    private TextView tvTitle;
    private WebView wvWeb;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ivFavicon = findViewById(R.id.ivFavicon);
        tvTitle = findViewById(R.id.tvTitle);
        wvWeb = findViewById(R.id.wvWeb);
        pbLoading = findViewById(R.id.pbLoading);
        wvWeb.setWebViewClient(new WebViewClient());
        wvWeb.getSettings().setJavaScriptEnabled(true);
        wvWeb.setWebChromeClient(new MyWebClient());
        wvWeb.loadUrl("https://iplaysoft.com");
    }

    class MyWebClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            pbLoading.setProgress(newProgress);
            if(newProgress == 100){
                pbLoading.setVisibility(View.GONE);
            }else{
                pbLoading.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            ivFavicon.setImageBitmap(icon);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            tvTitle.setText(title);
        }
    }

    @Override
    public void onBackPressed() {
        if(wvWeb.canGoBack()){
            wvWeb.goBack();
        }else {
            super.onBackPressed();
        }

    }
}
