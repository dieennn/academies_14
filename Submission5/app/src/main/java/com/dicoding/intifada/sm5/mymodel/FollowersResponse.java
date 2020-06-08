package com.dicoding.intifada.sm5.mymodel;

import java.util.List;


public class FollowersResponse {
    private List<FollowersItems> items;

    public FollowersResponse(List<FollowersItems> items) {
        this.items = items;
    }

    public List<FollowersItems> getResults() {
        return items;
    }

}
