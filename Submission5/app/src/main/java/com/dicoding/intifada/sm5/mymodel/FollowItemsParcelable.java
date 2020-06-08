package com.dicoding.intifada.sm5.mymodel;

import android.os.Parcel;
import android.os.Parcelable;

public class FollowItemsParcelable implements Parcelable {

    private int id;
    private String login;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.login);
        dest.writeString(this.node_id);
        dest.writeString(this.avatar_url);
        dest.writeValue(this.gravatar_id);
    }

    private FollowItemsParcelable(Parcel in) {
        this.id = in.readInt();
        this.login = in.readString();
        this.node_id = in.readString();
        this.avatar_url = in.readString();
        this.gravatar_id = in.readString();
    }

    public static final Creator<FollowItemsParcelable> CREATOR = new Creator<FollowItemsParcelable>() {
        @Override
        public FollowItemsParcelable createFromParcel(Parcel source) {
            return new FollowItemsParcelable(source);
        }

        @Override
        public FollowItemsParcelable[] newArray(int size) {
            return new FollowItemsParcelable[size];
        }
    };
}
