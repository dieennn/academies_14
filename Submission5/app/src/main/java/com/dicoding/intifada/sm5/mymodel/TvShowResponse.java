package com.dicoding.intifada.sm5.mymodel;

import java.util.List;



public class TvShowResponse {
    private List<TvShowItems> results;

    public TvShowResponse(List<TvShowItems> results) {
        this.results = results;
    }

    List<TvShowItems> getResults() {
        return results;
    }
}
