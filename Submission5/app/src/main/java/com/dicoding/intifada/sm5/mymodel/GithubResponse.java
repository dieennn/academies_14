package com.dicoding.intifada.sm5.mymodel;

import com.google.gson.annotations.SerializedName;

public class GithubResponse {
    @SerializedName("login")

    private String login;
    public final String get_Username() {

        return this.login;

    }
}
