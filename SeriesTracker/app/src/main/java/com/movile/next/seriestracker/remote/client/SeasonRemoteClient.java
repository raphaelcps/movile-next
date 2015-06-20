package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.service.ISeasonRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 20/06/15.
 */
public class SeasonRemoteClient {

    private static final String TAG = SeasonRemoteClient.class.getSimpleName();

    ISeasonLoader handler;
    RestAdapter mAdapter;

    public SeasonRemoteClient(String endpoint, ISeasonLoader handler) {
        this.mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        this.handler = handler;
    }

    public void getSeasonDetails(String show, Long season) {
        ISeasonRemoteService service = mAdapter.create(ISeasonRemoteService.class);
        service.getSeasonDetails(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {
                handler.onSeasonLoaded(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season: " + error, error.getCause());
            }
        });
    }
}
