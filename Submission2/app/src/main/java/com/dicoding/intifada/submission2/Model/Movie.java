package com.dicoding.intifada.submission2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int photo;
    private int photoPoster;
    private String nameFilm;
    private String dateFilm;
    private String yearFilm;
    private String overview;

    private String score;
    private String language;
    private String runtime;
    private String description;

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPhotoPoster() {
        return photoPoster;
    }

    public void setPhotoPoster(int photoPoster) {
        this.photoPoster = photoPoster;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getDateFilm() {
        return dateFilm;
    }

    public void setDateFilm(String dateFilm) {
        this.dateFilm = dateFilm;
    }

    public String getYearFilm() {
        return yearFilm;
    }

    public void setYearFilm(String yearFilm) {
        this.yearFilm = yearFilm;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Movie(Parcel in) {
        photo = in.readInt();
        photoPoster = in.readInt();
        nameFilm = in.readString();
        dateFilm = in.readString();
        yearFilm = in.readString();
        overview = in.readString();

        score = in.readString();
        language = in.readString();
        runtime = in.readString();
        description = in.readString();
    }

    public Movie(){

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(photo);
        dest.writeInt(photoPoster);
        dest.writeString(nameFilm);
        dest.writeString(dateFilm);
        dest.writeString(yearFilm);
        dest.writeString(overview);

        dest.writeString(score);
        dest.writeString(language);
        dest.writeString(runtime);
        dest.writeString(description);
    }
}
