package com.example.kurniawan.mymusic.top_artist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kurniawan.mymusic.Music;
import com.example.kurniawan.mymusic.R;
import com.example.kurniawan.mymusic.TopArtist;

import java.util.ArrayList;

public class ProfilActivity extends AppCompatActivity {
    private WebView detailProfil;
    private ArrayList<TopArtist> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        String url = getIntent().getStringExtra("url");
        detailProfil = findViewById(R.id.detail_profil);
        detailProfil.setWebViewClient(new WebViewClient());
        WebSettings websetting = detailProfil.getSettings();
        websetting.setJavaScriptEnabled(true);
        detailProfil.loadUrl(url);

    }
}
