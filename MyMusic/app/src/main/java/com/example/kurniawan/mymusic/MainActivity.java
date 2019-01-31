package com.example.kurniawan.mymusic;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kurniawan.mymusic.library.library.LibraryActivity;
import com.example.kurniawan.mymusic.top_album.AlbumAdapter;
import com.example.kurniawan.mymusic.top_album.TopAlbumActivity;
import com.example.kurniawan.mymusic.top_artist.TopArtisAdapter;
import com.example.kurniawan.mymusic.top_artist.TopArtistActivity;
import com.example.kurniawan.mymusic.top_track.MusicAdapter;
import com.example.kurniawan.mymusic.top_track.TopTrackActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<ArrayList<TopArtist>> {

    private Intent move;
    MusicAdapter musicAdapter;
    TopArtisAdapter artistAdapter;
    SearchAdapter searchAdapter;
    AlbumAdapter albumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.library) {
            move = new Intent(this, LibraryActivity.class);
            startActivity(move);
        } else if (id == R.id.top_track) {
            move = new Intent(this, TopTrackActivity.class);
            startActivity(move);

        } else if (id == R.id.top_artist) {
            move = new Intent(this, TopArtistActivity.class);
            startActivity(move);

        } else if (id == R.id.top_albums) {
            move = new Intent(this, TopAlbumActivity.class);
            startActivity(move);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @NonNull
    @Override
    public Loader<ArrayList<TopArtist>> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<TopArtist>> loader, ArrayList<TopArtist> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<TopArtist>> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView)(menu.findItem(R.id.search))
                    .getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_lagu));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    String cari = query.toLowerCase();
                    Intent intent = new Intent(MainActivity.this, CariLaguActivity.class);
                    intent.putExtra("EXTRA_CARI", cari);
                    startActivity(intent);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }
}
