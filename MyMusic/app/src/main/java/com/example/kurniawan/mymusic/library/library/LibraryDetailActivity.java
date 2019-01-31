package com.example.kurniawan.mymusic.library.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kurniawan.mymusic.LibraryArtist;
import com.example.kurniawan.mymusic.R;
import com.example.kurniawan.mymusic.TopArtist;

import java.util.ArrayList;

public class LibraryDetailActivity extends AppCompatActivity {
    private WebView detailLibrary;
    private ArrayList<LibraryArtist> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_library_detail);
        String url = getIntent().getStringExtra("url");
        detailLibrary = findViewById(R.id.detail_library);
        detailLibrary.setWebViewClient(new WebViewClient());
        WebSettings websetting = detailLibrary.getSettings();
        websetting.setJavaScriptEnabled(true);
        detailLibrary.loadUrl(url);

    }
    }
