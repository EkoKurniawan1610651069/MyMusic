package com.example.kurniawan.mymusic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchLoader extends AsyncTaskLoader<ArrayList<Search>> {

    private String mMusicList;
    private ArrayList<Search> mData;
    private boolean hasResult = false;

    public SearchLoader(final Context context, String musicList) {
        super(context);
        onContentChanged();
        this.mMusicList = musicList;
    }

    @Nullable
    @Override
    public ArrayList<Search> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<Search> musicArrayList = new ArrayList<>();
        String url = "http://ws.audioscrobbler.com/2.0/?method=track.search&track= "+mMusicList+" &api_key=b87e9420de91506263690024c148b978&format=json";
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

                    JSONObject object = jsonObject.getJSONObject("results");
                    JSONObject objectin = object.getJSONObject("trackmatches");
                    JSONArray jsonArray = objectin.getJSONArray("track");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonMusic = jsonArray.getJSONObject(i);
                        Search music = new Search(jsonMusic);
                        musicArrayList.add(music);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
        return musicArrayList;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(@Nullable ArrayList<Search> data) {
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

    protected void onReleaseResources(ArrayList<Search> mData) {

    }
}
