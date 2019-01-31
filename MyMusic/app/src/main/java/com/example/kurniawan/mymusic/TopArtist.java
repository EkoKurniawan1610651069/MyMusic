package com.example.kurniawan.mymusic;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TopArtist implements Parcelable {
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

    public TopArtist(JSONObject jsonObject) {
        try {

            String strArtistTop = jsonObject.getString("name");
            this.strArtistTop = strArtistTop;

            String strArtistThumb = jsonObject.getJSONArray("image").getJSONObject(2).getString("#text");
            this.strArtistThumb = strArtistThumb;

            int playCount = jsonObject.getInt("playcount");
            this.playCount = playCount;

            int listenersArtistTop = jsonObject.getInt("listeners");
            this.listenersArtistTop = listenersArtistTop;

            String urlProfil = jsonObject.getString("url");
            this.urlProfil = urlProfil;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected TopArtist(Parcel in) {
        this.strArtistTop = in.readString();
        this.strArtistThumb = in.readString();
        this.urlProfil = in.readString();
        this.playCount = in.readInt();
        this.listenersArtistTop = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.strArtistTop);
        dest.writeString(this.strArtistThumb);
        dest.writeString(this.urlProfil);
        dest.writeInt(this.playCount);
        dest.writeInt(this.listenersArtistTop);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TopArtist> CREATOR = new Creator<TopArtist>() {
        @Override
        public TopArtist createFromParcel(Parcel in) {
            return new TopArtist(in);
        }

        @Override
        public TopArtist[] newArray(int size) {
            return new TopArtist[size];
        }
    };
}
