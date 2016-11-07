package com.example.matias.entregableparsers.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by matias on 06/11/16.
 */

public class AlbumContainer {

    @SerializedName("result")
    private List<Album> albumList;

    public List<Album> getResults() {

        return albumList;
    }

    public void setPostList(List<Album> postList) {
        this.albumList = albumList;
    }
}