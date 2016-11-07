package com.example.matias.entregableparsers.model;

/**
 * Created by dh-mob-tt on 03/11/16.
 */
public class Album {

    private Integer id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Album() {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}