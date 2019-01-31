package com.example.kurniawan.mymusic.top_album;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kurniawan.mymusic.R;
import com.example.kurniawan.mymusic.TopArtist;

import java.util.ArrayList;

public class DetailAlbum extends AppCompatActivity {
    private WebView detailAlbum;
    private ArrayList<Album> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_album);

        String url = getIntent().getStringExtra("url");
        detailAlbum = findViewById(R.id.detail_album);
        detailAlbum.setWebViewClient(new WebViewClient());
        WebSettings websetting = detailAlbum.getSettings();
        websetting.setJavaScriptEnabled(true);
        detailAlbum.loadUrl(url);
    }
}
