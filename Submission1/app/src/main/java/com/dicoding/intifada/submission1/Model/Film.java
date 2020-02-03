package com.dicoding.intifada.submission1.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable {
    private int photo;
    private String name;
    private String description;
    private String vote;
    private String lang;
    private String runtime;
    private String release;
    private String budget;
    private String revenue;
    private String genre;

    private Film(Parcel in) {
        photo = in.readInt();
        name = in.readString();
        description = in.readString();
        vote = in.readString();
        lang = in.readString();
        runtime = in.readString();
        release = in.readString();
        budget = in.readString();
        revenue = in.readString();
        genre = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public Film() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(vote);
        dest.writeString(lang);
        dest.writeString(runtime);
        dest.writeString(release);
        dest.writeString(budget);
        dest.writeString(revenue);
        dest.writeString(genre);
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}