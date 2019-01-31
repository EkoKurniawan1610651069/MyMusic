package com.example.kurniawan.mymusic.top_album;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Album implements Parcelable {
    private String judulAlbum;
    private String artistAlbum;
    private String imageAlbum;
    private String urlAlbum;
    private int playCount;

    public String getJudulAlbum() {
        return judulAlbum;
    }

    public void setJudulAlbum(String judulAlbum) {
        this.judulAlbum = judulAlbum;
    }

    public String getArtistAlbum() {
        return artistAlbum;
    }

    public void setArtistAlbum(String artistAlbum) {
        this.artistAlbum = artistAlbum;
    }

    public String getImageAlbum() {
        return imageAlbum;
    }

    public void setImageAlbum(String imageAlbum) {
        this.imageAlbum = imageAlbum;
    }

    public String getUrlAlbum() {
        return urlAlbum;
    }

    public void setUrlAlbum(String urlAlbum) {
        this.urlAlbum = urlAlbum;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public Album(JSONObject jsonObject) {
        try {
            String judulAlbum = jsonObject.getString("name");
            this.judulAlbum = judulAlbum;

            String artistAlbum = jsonObject.getJSONObject("artist").getString("name");
            this.artistAlbum = artistAlbum;

            String imageAlbum = jsonObject.getJSONArray("image").getJSONObject(2).getString("#text");
            this.imageAlbum = imageAlbum;

            int playCount = jsonObject.getJSONObject("@attr").getInt("rank");
            this.playCount = playCount;

            String urlAlbum = jsonObject.getString("url");
            this.urlAlbum = urlAlbum;


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
        dest.writeString(this.judulAlbum);
        dest.writeString(this.artistAlbum);
        dest.writeString(this.imageAlbum);
        dest.writeString(this.urlAlbum);
        dest.writeInt(this.playCount);
    }

    protected Album(Parcel in) {
        this.judulAlbum = in.readString();
        this.artistAlbum = in.readString();
        this.imageAlbum = in.readString();
        this.urlAlbum = in.readString();
        this.playCount = in.readInt();
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}