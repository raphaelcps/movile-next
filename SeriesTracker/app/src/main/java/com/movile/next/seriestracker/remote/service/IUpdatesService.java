package com.movile.next.seriestracker.remote.service;

import com.movile.next.seriestracker.model.ShowUpdate;

import retrofit.http.GET;

/**
 * Created by movile on 28/06/15.
 */
public interface IUpdatesService {

    @GET("/latestUpdate.json")
    ShowUpdate getLatest();

}
