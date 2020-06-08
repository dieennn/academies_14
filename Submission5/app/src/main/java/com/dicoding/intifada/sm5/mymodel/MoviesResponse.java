package com.dicoding.intifada.sm5.mymodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class MoviesResponse {
    /*private List<MovieItems> results;

    public MoviesResponse(List<MovieItems> results) {
        this.results = results;
    }

    List<MovieItems> getResults() {
        return results;
    }*/
    private List<MovieItems> items;

    public MoviesResponse(List<MovieItems> items) {
        this.items = items;
    }

    public List<MovieItems> getResults() {
        return items;
    }

}
