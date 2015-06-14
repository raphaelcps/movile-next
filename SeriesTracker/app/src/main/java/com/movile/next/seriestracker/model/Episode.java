package com.movile.next.seriestracker.model;

import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

/**
 * Created by movile on 14/06/15.
 */
public class Episode {
    private Long season;
    private Long number;
    private String title;
    private MediaIds ids;
    private String overview;
    private Double rating;
    private Long votes;
    @SerializedName("first_aired")
    private String firstAired;
    @SerializedName("available_translations")
    private String[] translations;
    //private MediaStore.Images images;

    public Long season() {
        return season;
    }

    public Long number() {
        return number;
    }

    public String title() {
        return title;
    }

    public MediaIds ids() {
        return ids;
    }

    public String overview() {
        return overview;
    }

    public Double rating() {
        return rating;
    }

    public Long votes() {
        return votes;
    }

    public String firstAired() {
        return firstAired;
    }

    public String[] translations() {
        return translations;
    }

    /*TODO: return images
    public Images images() {
        return images;
    }*/
}
