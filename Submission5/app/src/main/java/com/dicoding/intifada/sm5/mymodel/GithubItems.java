package com.dicoding.intifada.sm5.mymodel;

import android.os.Parcel;
import android.os.Parcelable;

public class GithubItems {
    private int id;
    private String title;
    private String original_title;
    private String release_date;
    private Double vote_average;
    private String vote_count;
    private String overview;
    private String poster_path;
    private String backdrop_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }


    public String getVote_count() {
        return vote_count;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    //@Override
    public int describeContents() {
        return 0;
    }

    //@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.original_title);
        dest.writeString(this.release_date);
        dest.writeValue(this.vote_average);
        dest.writeString(this.vote_count);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
    }

    private GithubItems(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.original_title = in.readString();
        this.release_date = in.readString();
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
        this.vote_count = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
    }

    public static final Parcelable.Creator<GithubItems> CREATOR = new Parcelable.Creator<GithubItems>() {
        @Override
        public GithubItems createFromParcel(Parcel source) {
            return new GithubItems(source);
        }

        @Override
        public GithubItems[] newArray(int size) {
            return new GithubItems[size];
        }
    };
}