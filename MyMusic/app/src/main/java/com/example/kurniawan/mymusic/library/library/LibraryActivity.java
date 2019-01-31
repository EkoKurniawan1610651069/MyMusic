package com.example.kurniawan.mymusic.library.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;

import com.example.kurniawan.mymusic.LibraryArtist;
import com.example.kurniawan.mymusic.R;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<LibraryArtist>> {

    GridView rv_images;
    LibraryAdapter libraryadapter;
    ArrayList<LibraryArtist> library;
    static final String EXTRA_LIBRARY = "EXTRA_LIBRARY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Library Artist");
        libraryadapter = new LibraryAdapter(this);
        libraryadapter.notifyDataSetChanged();
        rv_images = (GridView) findViewById(R.id.rv_images);
        rv_images.setAdapter(libraryadapter);
        library = new ArrayList<>();

        Bundle bundle = new Bundle();
        getSupportLoaderManager().initLoader(0, bundle, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<LibraryArtist>> onCreateLoader(int id, @Nullable Bundle args) {
        String libraryList = "";
        if (args != null) {
            libraryList = args.getString(EXTRA_LIBRARY);
        }
        return new LibraryLoader(this, libraryList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<LibraryArtist>> loader, ArrayList<LibraryArtist> data) {
        library.addAll(data);
        libraryadapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<LibraryArtist>> loader) {

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