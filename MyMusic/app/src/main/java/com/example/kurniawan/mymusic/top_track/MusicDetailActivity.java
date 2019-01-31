package com.example.kurniawan.mymusic.top_track;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kurniawan.mymusic.Music;
import com.example.kurniawan.mymusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicDetailActivity extends AppCompatActivity {
    private WebView detailTrack;
    private ArrayList<Music> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);

        String url = getIntent().getStringExtra("url");
        detailTrack = findViewById(R.id.detail_track);
        detailTrack.setWebViewClient(new WebViewClient());
        WebSettings websetting = detailTrack.getSettings();
        websetting.setJavaScriptEnabled(true);
        detailTrack.loadUrl(url);

    }
}
