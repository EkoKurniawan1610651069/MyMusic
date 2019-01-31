package com.example.kurniawan.mymusic.top_track;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.kurniawan.mymusic.Music;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MusicAsyncTaskLoader extends AsyncTaskLoader<ArrayList<Music>> {

    private String mMusicList;
    private ArrayList<Music> mData;
    private boolean hasResult = false;


    public MusicAsyncTaskLoader(final Context context, String musicList) {
        super(context);
        onContentChanged();
        this.mMusicList = musicList;
    }

    @Nullable
    @Override
    public ArrayList<Music> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<Music> musicArrayList = new ArrayList<>();
        String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=b87e9420de91506263690024c148b978&format=json";
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
                    JSONObject object = jsonObject.getJSONObject("tracks");
                    JSONArray jsonArray = object.getJSONArray("track");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonMusic = jsonArray.getJSONObject(i);
                        Music music = new Music(jsonMusic);
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
    public void deliverResult(@Nullable ArrayList<Music> data) {
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

    protected void onReleaseResources(ArrayList<Music> mData) {

    }
}
