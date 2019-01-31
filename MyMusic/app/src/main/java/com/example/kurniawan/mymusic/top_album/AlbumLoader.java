package com.example.kurniawan.mymusic.top_album;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.kurniawan.mymusic.TopArtist;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AlbumLoader extends AsyncTaskLoader<ArrayList<Album>> {

    private String mAlbumList;
    private ArrayList<Album> mData;
    private boolean hasResult = false;

    public AlbumLoader(final Context context, String albumList) {
        super(context);
        onContentChanged();
        this.mAlbumList = albumList;
    }

    @Nullable
    @Override
    public ArrayList<Album> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<Album> albumArrayList = new ArrayList<>();
        String url = "http://ws.audioscrobbler.com/2.0/?method=tag.gettopalbums&tag=disco&api_key=b87e9420de91506263690024c148b978&format=json";
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
                    JSONObject object = jsonObject.getJSONObject("albums");
                    JSONArray jsonArray = object.getJSONArray("album");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonMusic = jsonArray.getJSONObject(i);
                        Album album = new Album(jsonMusic);
                        albumArrayList.add(album);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
        return albumArrayList;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(@Nullable ArrayList<Album> data) {
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

    protected void onReleaseResources(ArrayList<Album> mData) {

    }
}
