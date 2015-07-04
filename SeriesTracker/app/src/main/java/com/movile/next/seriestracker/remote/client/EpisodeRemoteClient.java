package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.loader.IEpisodeLoader;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.service.IEpisodeRemoteService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 20/06/15.
 */
public class EpisodeRemoteClient {

    private static final String TAG = EpisodeRemoteClient.class.getSimpleName();
    private IEpisodeLoader mHandler;
    private RestAdapter mAdapter;

    public EpisodeRemoteClient(String endpoint, IEpisodeLoader handler) {
        this.mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        this.mHandler = handler;
    }

    public void getEpisodeDetails(String show, Long season, Long episode) {
        IEpisodeRemoteService service = mAdapter.create(IEpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mHandler.onEpisodeLoaded(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode: " + error, error.getCause());
            }
        });
    }

}
