package pbru.yaemsak.chonlakan.showpro;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FacebookPage extends AppCompatActivity {
    private WebView mWebView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_page);

        mWebView = (WebView)this.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyBrowser());
        mWebView.loadUrl("https://www.facebook.com/Yonghouse");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public  boolean shouldOverrideUrlLoading(WebView view, String url ){
            view.loadUrl(url);
            return true;
        }
    }
    public void clickBackMenu(View view) {
        startActivity(new Intent(FacebookPage.this, MenuActivity.class));
    }
}