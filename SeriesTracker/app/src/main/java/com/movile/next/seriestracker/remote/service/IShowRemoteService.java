package com.movile.next.seriestracker.remote.service;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.util.ApiConfiguration;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by movile on 20/06/15.
 */
public interface IShowRemoteService {

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })

    @GET("/shows/{show}?extended=full,images")
    void getShowInfo(
            @Path("show") String show,
            Callback<Show> callback);
}
