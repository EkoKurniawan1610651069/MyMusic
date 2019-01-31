package com.example.kurniawan.mymusic;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class LibraryArtist implements Parcelable {

    private String textViewNamaArtist;
    private String imageViewArtist, urlLibrary;


    public String getUrlLibrary() {
        return urlLibrary;
    }

    public void setUrlLibrary(String urlLibrary) {
        this.urlLibrary = urlLibrary;
    }

    public LibraryArtist(JSONObject jsonObject) {
        try {

            String textViewNamaArtist = jsonObject.getString("name");
            this.textViewNamaArtist = textViewNamaArtist;

            String imageViewArtist = jsonObject.getJSONArray("image").getJSONObject(3).getString("#text");
            this.imageViewArtist = imageViewArtist;

            String urlLibrary = jsonObject.getString("url");
            this.urlLibrary = urlLibrary;

        } catch (Exception e) {
            e.printStackTrace();
        }


}

        public String getTextViewNamaArtist() {
        return textViewNamaArtist;
    }

    public void setTextViewNamaArtist(String textViewNamaArtist) {
        this.textViewNamaArtist = textViewNamaArtist;
    }

    public String getImageViewArtist() {
        return imageViewArtist;
    }

    public void setImageViewArtist(String imageViewArtist) {
        this.imageViewArtist = imageViewArtist;
    }

    public static Creator<LibraryArtist> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.textViewNamaArtist);
        dest.writeString(this.imageViewArtist);
        dest.writeString(this.urlLibrary);
    }

    protected LibraryArtist(Parcel in) {
        this.textViewNamaArtist = in.readString();
        this.imageViewArtist = in.readString();
        this.urlLibrary = in.readString();
    }

    public static final Creator<LibraryArtist> CREATOR = new Creator<LibraryArtist>() {
        @Override
        public LibraryArtist createFromParcel(Parcel source) {
            return new LibraryArtist(source);
        }

        @Override
        public LibraryArtist[] newArray(int size) {
            return new LibraryArtist[size];
        }
    };
}
