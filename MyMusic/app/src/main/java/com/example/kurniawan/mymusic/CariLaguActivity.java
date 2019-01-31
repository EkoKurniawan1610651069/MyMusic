package com.example.kurniawan.mymusic;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class CariLaguActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Search>> {

    ListView listView ;
    SearchAdapter adapter;
    ArrayList<Search> music;
    static final String EXTRAS_SEARCH = "EXTRAS_SEARCH";
    String queryCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_lagu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new SearchAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        music = new ArrayList<>();
        queryCari = getIntent().getStringExtra("EXTRA_CARI");
        Bundle bundle = new Bundle();


        getSupportLoaderManager().initLoader(0, bundle, CariLaguActivity.this);
    }

    @Override
    public Loader<ArrayList<Search>> onCreateLoader(int id, Bundle args) {
        String searchList = "";
        if (args != null){
            searchList = args.getString(EXTRAS_SEARCH);
        }

        return new SearchLoader(this,queryCari);
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<Search>> loader, ArrayList<Search> data) {
        music.addAll(data);
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Search>> loader) {
        adapter.setData(null);
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