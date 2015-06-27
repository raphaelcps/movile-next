package com.movile.next.seriestracker.remote.client;

import android.util.Log;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.service.ISeasonRemoteService;
import com.movile.next.seriestracker.remote.service.IShowRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by movile on 20/06/15.
 */
public class ShowRemoteClient {

    public interface IShowLoader {
        void onShowLoaded(Show show);

        void onShowsLoaded(List<Show> shows);
    }

    private static final String TAG = ShowRemoteClient.class.getSimpleName();

    IShowLoader handler;
    RestAdapter mAdapter;

    public ShowRemoteClient(String endpoint, IShowLoader handler) {
        this.mAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
        this.handler = handler;
    }

    public void getShowInfo(String show) {
        IShowRemoteService service = mAdapter.create(IShowRemoteService.class);
        service.getShowInfo(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {

                Log.e(TAG, "URL: " + response.getUrl());
                handler.onShowLoaded(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching show: " + error, error.getCause());
            }
        });
    }

    public void getShows() {
        IShowRemoteService service = mAdapter.create(IShowRemoteService.class);
        service.getShows(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                handler.onShowsLoaded(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching shows: " + error, error.getCause());
            }
        });
    }
}
