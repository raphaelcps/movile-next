package com.movile.next.seriestracker.remote.client;

import android.content.Context;
import android.util.Log;
import android.widget.Adapter;

import com.movile.next.seriestracker.asynctask.IEpisodeLoader;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.service.IEpisodeRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 20/06/15.
 */
public class EpisodeRemoteClient {

    private static final String TAG = EpisodeRemoteClient.class.getSimpleName();
    IEpisodeLoader handler;
    RestAdapter mAdapter;

    public EpisodeRemoteClient(String endpoint, IEpisodeLoader handler) {
        this.mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        this.handler = handler;
    }

    public void getEpisodeDetails(String show, Long season, Long episode) {
        IEpisodeRemoteService service = mAdapter.create(IEpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                handler.onEpisodeLoaded(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode: " + error, error.getCause());
            }
        });
    }

}
