package com.rakesh.nytimessearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by rparuthi on 3/19/2017.
 */

public class ArticleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article);

        try {
           // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
          //  setSupportActionBar(toolbar);

            final String url = getIntent().getStringExtra("url");

            WebView webView = (WebView) findViewById(R.id.wvArticle);

            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    try {
                        view.loadUrl(url);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    return true;
                }
            });

            webView.loadUrl(url);



        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
