package com.dicoding.intifada.sm5.mymodel;

import android.os.Parcel;
import android.os.Parcelable;

public class TopDevelopersParcelable implements Parcelable {
    private String username;
    private String name;
    private String url;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.username);
        dest.writeValue(this.name);
        dest.writeValue(this.url);
        dest.writeValue(this.avatar);
    }

    private TopDevelopersParcelable(Parcel in) {
        this.username = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.avatar = in.readString();
    }

    public static final Creator<TopDevelopersParcelable> CREATOR = new Creator<TopDevelopersParcelable>() {
        @Override
        public TopDevelopersParcelable createFromParcel(Parcel source) {
            return new TopDevelopersParcelable(source);
        }

        @Override
        public TopDevelopersParcelable[] newArray(int size) {
            return new TopDevelopersParcelable[size];
        }
    };
}
