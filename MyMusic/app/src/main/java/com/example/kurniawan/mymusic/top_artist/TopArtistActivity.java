package com.example.kurniawan.mymusic.top_artist;

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
import com.example.kurniawan.mymusic.top_track.MusicAdapter;
import com.example.kurniawan.mymusic.top_track.MusicAsyncTaskLoader;

import java.util.ArrayList;

public class TopArtistActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<TopArtist>> {

    ListView listView1;
    TopArtisAdapter artistadapter;
    ArrayList<TopArtist> artist;
    static final String EXTRA_ARTIST = "EXTRA_ARTIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_artist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Top Artist");

        artistadapter = new TopArtisAdapter(this);
        artistadapter.notifyDataSetChanged();
        listView1 = (ListView) findViewById(R.id.listview1);
        listView1.setAdapter(artistadapter);
        artist = new ArrayList<>();

        Bundle bundle = new Bundle();
        getSupportLoaderManager().initLoader(0, bundle, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<TopArtist>> onCreateLoader(int id, @Nullable Bundle args) {
        String artistList = "";
        if (args != null) {
            artistList = args.getString(EXTRA_ARTIST);
        }
        return new TopArtistLoader(this, artistList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<TopArtist>> loader, ArrayList<TopArtist> data) {
        artist.addAll(data);
        artistadapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<TopArtist>> loader) {

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