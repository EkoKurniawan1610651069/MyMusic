package com.example.kurniawan.mymusic.top_track;

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

import java.util.ArrayList;

public class TopTrackActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Music>> {

    ListView listView;
    MusicAdapter adapter;
    ArrayList<Music> music;
    static final String EXTRA_MUSIC = "EXTRA_MUSIC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_track);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Top Track Music");

        adapter = new MusicAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        music = new ArrayList<>();

        Bundle bundle = new Bundle();
        getSupportLoaderManager().initLoader(0, bundle, this);
    }


    @NonNull
    @Override
    public Loader<ArrayList<Music>> onCreateLoader(int id, @Nullable Bundle args) {
        String musicList = "";
        if (args != null) {
            musicList = args.getString(EXTRA_MUSIC);
        }
        return new MusicAsyncTaskLoader(this, musicList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Music>> loader, ArrayList<Music> data) {
        music.addAll(data);
        adapter.setData(data  );
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Music>> loader) {
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home){
                onBackPressed();
                return true;
            }
            return false;
        }


    }

