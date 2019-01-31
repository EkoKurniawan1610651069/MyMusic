package com.example.kurniawan.mymusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class SearchDetailActivity extends AppCompatActivity {
    private WebView detailSearch;
    private ArrayList<Music> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        String url = getIntent().getStringExtra("url");
        detailSearch = findViewById(R.id.detail_search);
        detailSearch.setWebViewClient(new WebViewClient());
        WebSettings websetting = detailSearch.getSettings();
        websetting.setJavaScriptEnabled(true);
        detailSearch.loadUrl(url);
    }
}
