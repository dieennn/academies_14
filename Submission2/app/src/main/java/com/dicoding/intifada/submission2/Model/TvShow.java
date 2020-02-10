package com.dicoding.intifada.submission2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int photoTv;
    private int photoPosterTv;
    private String nameTv;
    private String dateTv;
    private String yearTv;
    private String overviewTv;

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

    private String score;
    private String language;
    private String runtime;
    private String description;

    public int getPhotoTv() {
        return photoTv;
    }

    public void setPhotoTv(int photoTv) {
        this.photoTv = photoTv;
    }

    public int getPhotoPosterTv() {
        return photoPosterTv;
    }

    public void setPhotoPosterTv(int photoPosterTv) {
        this.photoPosterTv = photoPosterTv;
    }

    public String getNameTv() {
        return nameTv;
    }

    public void setNameTv(String nameTv) {
        this.nameTv = nameTv;
    }

    public String getDateTv() {
        return dateTv;
    }

    public void setDateTv(String dateTv) {
        this.dateTv = dateTv;
    }

    public String getYearTv() {
        return yearTv;
    }

    public void setYearTv(String yearTv) {
        this.yearTv = yearTv;
    }

    public String getOverviewTv() {
        return overviewTv;
    }

    public void setOverviewTv(String overviewTv) {
        this.overviewTv = overviewTv;
    }

    protected TvShow(Parcel in) {
        photoTv = in.readInt();
        photoPosterTv = in.readInt();
        nameTv = in.readString();
        dateTv = in.readString();
        yearTv = in.readString();
        overviewTv = in.readString();

        score = in.readString();
        language = in.readString();
        runtime = in.readString();
        description = in.readString();
    }

    public TvShow () {

    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(photoTv);
        dest.writeInt(photoPosterTv);
        dest.writeString(nameTv);
        dest.writeString(dateTv);
        dest.writeString(yearTv);
        dest.writeString(overviewTv);

        dest.writeString(score);
        dest.writeString(language);
        dest.writeString(runtime);
        dest.writeString(description);
    }
}
