package pbru.yaemsak.chonlakan.showpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by chonlakan on 4/6/2559.
 */
public class FacebookPage extends AppCompatActivity{
    private WebView mWebView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_page);

        WebView view = (WebView) this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());
        view.loadUrl("https://www.facebook.com/Yonghouse");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public  boolean shouldOverrideUrlLoading(WebView view, String url ){
            view.loadUrl(url);
            return true;
        }
    }
    /*public void clickBackMenu(View view) {
        startActivity(new Intent(FacebookPage.this, MenuActivity.class));
    }*/
}

