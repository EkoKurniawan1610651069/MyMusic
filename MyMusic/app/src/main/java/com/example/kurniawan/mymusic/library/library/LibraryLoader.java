package com.example.kurniawan.mymusic.library.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.kurniawan.mymusic.LibraryArtist;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class LibraryLoader extends AsyncTaskLoader<ArrayList<LibraryArtist>> {
    private String mLibraryList;
    private ArrayList<LibraryArtist> mData;
    private boolean hasResult = false;


    public LibraryLoader(final Context context, String libraryList) {
        super(context);
        onContentChanged();
        this.mLibraryList = libraryList;
    }
    @Nullable
    @Override
    public ArrayList<LibraryArtist> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<LibraryArtist> artistArrayList = new ArrayList<>();
        String url = "http://ws.audioscrobbler.com/2.0/?method=library.getartists&api_key=b87e9420de91506263690024c148b978&user=joanofarctan&format=json";
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject object = jsonObject.getJSONObject("artists");
                    JSONArray jsonArray = object.getJSONArray("artist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonMusic = jsonArray.getJSONObject(i);
                        LibraryArtist library = new LibraryArtist(jsonMusic);
                        artistArrayList.add(library);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
        return artistArrayList;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(@Nullable ArrayList<LibraryArtist> data) {
        mData = data;
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasResult) {
            onReleaseResources(mData);
            mData = null;
            hasResult = false;
        }
    }

    protected void onReleaseResources(ArrayList<LibraryArtist> mData) {

    }
}
