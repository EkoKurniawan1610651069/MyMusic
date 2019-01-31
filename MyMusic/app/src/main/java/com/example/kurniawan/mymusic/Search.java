package com.example.kurniawan.mymusic;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Search implements Parcelable {
    private String strJudulLagu;
    private String strArtist;
    private String strTrackThumb;
    private String url;
    private int listeners;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public static Creator<Search> getCREATOR() {
        return CREATOR;
    }

    public Search(JSONObject jsonObject) {
        try {

            String strJudulLagu = jsonObject.getString("name");
            this.strJudulLagu = strJudulLagu;

            String strArtist = jsonObject.getString("artist");
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

    protected Search(Parcel in) {
        this.strJudulLagu = in.readString();
        this.strArtist = in.readString();
        this.strTrackThumb = in.readString();
        this.url = in.readString();
        this.listeners = in.readInt();
    }

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel in) {
            return new Search(in);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };

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
}
