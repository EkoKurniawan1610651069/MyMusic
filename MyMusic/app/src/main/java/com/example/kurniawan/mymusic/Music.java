package com.example.kurniawan.mymusic;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Music implements Parcelable {
    private String strJudulLagu;
    private String strArtist;
    private String strTrackThumb;
    private String url;
    private int listeners;

    private String strArtistTop;
    private String strArtistThumb;
    private String urlProfil;
    private int playCount, listenersArtistTop;

    public String getStrArtistTop() {
        return strArtistTop;
    }

    public void setStrArtistTop(String strArtistTop) {
        this.strArtistTop = strArtistTop;
    }

    public String getStrArtistThumb() {
        return strArtistThumb;
    }

    public void setStrArtistThumb(String strArtistThumb) {
        this.strArtistThumb = strArtistThumb;
    }

    public String getUrlProfil() {
        return urlProfil;
    }

    public void setUrlProfil(String urlProfil) {
        this.urlProfil = urlProfil;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getListenersArtistTop() {
        return listenersArtistTop;
    }

    public void setListenersArtistTop(int listenersArtistTop) {
        this.listenersArtistTop = listenersArtistTop;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStrJudulLagu() {
        return strJudulLagu;
    }

    public void setStrJudulLagu(String strJudulLagu) {
        this.strJudulLagu = strJudulLagu;
    }

    public String getStrArtist() {
        return strArtist;
    }

    public void setStrArtist(String strArtist) {
        this.strArtist = strArtist;
    }

    public String getStrTrackThumb() {
        return strTrackThumb;
    }

    public void setStrTrackThumb(String strTrackThumb) {
        this.strTrackThumb = strTrackThumb;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }


    public Music(JSONObject jsonObject) {
        try {
            String strJudulLagu = jsonObject.getString("name");
            this.strJudulLagu = strJudulLagu;

            String strArtist = jsonObject.getJSONObject("artist").getString("name");
            this.strArtist = strArtist;

            String strTrackThumb = jsonObject.getJSONArray("image").getJSONObject(0).getString("#text");
            this.strTrackThumb = strTrackThumb;

            String strTrackThumbLrg = jsonObject.getJSONArray("image").getJSONObject(2).getString("#text");
            this.strTrackThumb = strTrackThumbLrg;

            int listeners = jsonObject.getInt("listeners");
            this.listeners = listeners;

            String url = jsonObject.getString("url");
            this.url = url;




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.strJudulLagu);
        dest.writeString(this.strArtist);
        dest.writeString(this.strTrackThumb);
        dest.writeString(this.url);
        dest.writeInt(this.listeners);

    }

    protected Music(Parcel in) {
        this.strJudulLagu = in.readString();
        this.strArtist = in.readString();
        this.strTrackThumb = in.readString();
        this.url = in.readString();
        this.listeners = in.readInt();

    }

    public static final Parcelable.Creator<Music> CREATOR = new Parcelable.Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel source) {
            return new Music(source);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
}

