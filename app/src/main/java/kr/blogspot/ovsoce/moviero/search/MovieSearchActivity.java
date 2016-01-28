package kr.blogspot.ovsoce.moviero.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kr.blogspot.ovsoce.moviero.R;

/**
 * Created by ovso on 2016. 1. 28..
 */
public class MovieSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //((WebView)findViewById(R.id.webview)).loadUrl(getIntent().getStringExtra("name"));
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://m.search.daum.net/search?w=tot&nil_mtopsearch=btn&DA=YZR&q="+getIntent().getStringExtra("name"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        //return super.onOptionsItemSelected(item);
        return true;
    }
}
