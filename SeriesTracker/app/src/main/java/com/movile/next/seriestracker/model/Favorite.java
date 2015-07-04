package com.movile.next.seriestracker.model;

/**
 * Created by movile on 04/07/15.
 */
public class Favorite {

    String mSlug;
    String mTitle;

    public Favorite(String slug, String title) {
        mSlug = slug;
        mTitle = title;
    }

    public String slug() {return mSlug;}
    public String title() {return mTitle;}

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public void setTitle(String title) {
        mSlug = title;
    }

}
