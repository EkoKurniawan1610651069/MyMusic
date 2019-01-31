package com.example.kurniawan.mymusic.top_artist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.kurniawan.mymusic.Music;
import com.example.kurniawan.mymusic.TopArtist;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TopArtistLoader extends AsyncTaskLoader<ArrayList<TopArtist>> {

    private String mArtistList;
    private ArrayList<TopArtist> mData;
    private boolean hasResult = false;


    public TopArtistLoader(final Context context, String artistList) {
        super(context);
        onContentChanged();
        this.mArtistList = artistList;
    }

    @Nullable
    @Override
    public ArrayList<TopArtist> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<TopArtist> artistArrayList = new ArrayList<>();
        String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=b87e9420de91506263690024c148b978&format=json";
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
                        TopArtist artist = new TopArtist(jsonMusic);
                        artistArrayList.add(artist);
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
    public void deliverResult(@Nullable ArrayList<TopArtist> data) {
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

    protected void onReleaseResources(ArrayList<TopArtist> mData) {

    }
}
