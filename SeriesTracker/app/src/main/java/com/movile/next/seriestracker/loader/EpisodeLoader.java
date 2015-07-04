package com.movile.next.seriestracker.loader;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.converter.ModelConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.MessageFormat;
import android.support.v4.content.*;

/**
 * Created by movile on 14/06/15.
 */
public class EpisodeLoader extends AsyncTaskLoader<Episode> {

    private static final String TAG = EpisodeLoader.class.getSimpleName();

    private String mShowSlug;

    public EpisodeLoader(Context context, String slug) {
        super(context);
        this.mShowSlug = slug;
    }

    @Override
    public Episode loadInBackground() {
        Episode episode = null;
        InputStreamReader reader = null;
        Resources res = getContext().getResources();
        String url = res.getString(R.string.api_url_base) +
                MessageFormat.format(res.getString(R.string.api_url_episode), mShowSlug, 1, 1) +
                "?" +
                res.getString(R.string.api_url_query_image);

        Log.d(TAG, url);
        try {
            HttpURLConnection connection = configureConnection(getContext(), url);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading remote content", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {}
            }
        }
        return episode;
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

