package com.example.kurniawan.mymusic.top_album;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.kurniawan.mymusic.Music;
import com.example.kurniawan.mymusic.R;
import com.example.kurniawan.mymusic.TopArtist;
import com.example.kurniawan.mymusic.top_artist.TopArtisAdapter;
import com.example.kurniawan.mymusic.top_artist.TopArtistLoader;
import com.example.kurniawan.mymusic.top_track.MusicAdapter;

import java.util.ArrayList;

public class TopAlbumActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Album>>  {

    ListView listView4;
    AlbumAdapter adapter;
    ArrayList<Album> album;
    static final String EXTRA_ALBUM = "EXTRA_ALBUM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_album);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Top Album");

        adapter = new AlbumAdapter(this);
        adapter.notifyDataSetChanged();
        listView4 = (ListView) findViewById(R.id.listview4);
        listView4.setAdapter(adapter);
        album = new ArrayList<>();

        Bundle bundle = new Bundle();
        getSupportLoaderManager().initLoader(0, bundle, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Album>> onCreateLoader(int id, @Nullable Bundle args) {
        String albumList = "";
        if (args != null) {
            albumList = args.getString(EXTRA_ALBUM);
        }
        return new AlbumLoader(this, albumList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Album>> loader, ArrayList<Album> data) {
        album.addAll(data);
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Album>> loader) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}