package com.movile.next.seriestracker.asynctask;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.next.seriestracker.model.Episode;

public class EpisodeLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {

    private Context context;
    private  String seriesId;
    IEpisodeLoader handler;

    public EpisodeLoaderCallback(Context context, String seriesId, IEpisodeLoader handler) {
        this.context = context;
        this.seriesId = seriesId;
        this.handler = handler;
    }

    @Override
    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        return new EpisodeLoader(context, seriesId);
    }

    @Override
    public void onLoadFinished(Loader<Episode> loader, Episode data) {
        //if (data != null) {
        this.handler.onEpisodeLoaded(data);
        //}
    }

    @Override
    public void onLoaderReset(Loader<Episode> loader) {

    }
}
