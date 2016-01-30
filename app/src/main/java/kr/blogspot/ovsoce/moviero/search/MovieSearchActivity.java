package kr.blogspot.ovsoce.moviero.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kr.blogspot.ovsoce.moviero.R;

public class MovieSearchActivity extends AppCompatActivity implements MovieSearchPresenter.View {

    MovieSearchPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        mPresenter = new MovieSearchPresenterImpl(this);
        mPresenter.onCreate(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        //return super.onOptionsItemSelected(item);
        return true;
    }

    private WebView mWebView;
    @Override
    public void onInit() {
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void search(String url) {
        String query = getIntent().getStringExtra("name");
        mWebView.loadUrl(url + query);
    }

    @Override
    public void onBackPressed() {
        if(!mWebView.canGoBack()) {
            super.onBackPressed();
        } else {
            mWebView.goBack();
        }
    }
}
