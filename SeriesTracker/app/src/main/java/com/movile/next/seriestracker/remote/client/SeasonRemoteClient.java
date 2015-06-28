package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Season;
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

    private ISeasonLoader mHandler;
    private RestAdapter mAdapter;

    public SeasonRemoteClient(String endpoint, ISeasonLoader handler) {
        this.mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        this.mHandler = handler;
    }

    public void getSeasonDetails(String show, Long season) {
        ISeasonRemoteService service = mAdapter.create(ISeasonRemoteService.class);
        service.getSeasonDetails(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {

                //try {
                    Log.d("rest", response.getUrl());
                //} catch (IOException e) {
                  //  e.printStackTrace();
                //}
                mHandler.onSeasonLoaded(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season: " + error, error.getCause());
            }
        });
    }

    public void getSeasons(String show) {
        ISeasonRemoteService service = mAdapter.create(ISeasonRemoteService.class);
        service.getSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mHandler.onSeasonsLoaded(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching seasons: " + error, error.getCause());
            }
        });
    }
}
