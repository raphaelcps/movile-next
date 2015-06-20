package com.movile.next.seriestracker.asynctask;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Episode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by movile on 14/06/15.
 */
public class EpisodeLoader extends AsyncTaskLoader<Episode> {

    private static final String TAG = EpisodeLoader.class.getSimpleName();

    String seriesId;
    Context context;

    public EpisodeLoader(Context context, String seriesId) {
        super(context);
    }

    @Override
    public Episode loadInBackground() {
        return null;
    }

    private HttpURLConnection configureConnection(Context context, String url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            Log.e(TAG, "Error loading connection with url: " + url, e);
            return null;
        }

        Resources res = context.getResources();

        connection.setReadTimeout(res.getInteger(R.integer.api_timeout_read));
        connection.setConnectTimeout(res.getInteger(R.integer.api_timeout_connect));
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            Log.e(TAG, "Bad protocol", e);
            return null;
        }

        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("trakt-api-version", res.getString(R.string.api_version));
        connection.setRequestProperty("trakt-api-key", res.getString(R.string.api_key));
        return connection;
    }

}

