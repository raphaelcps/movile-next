package com.movile.next.seriestracker.asynctask;

import android.support.v4.app.*;
import android.support.v4.content.*;
import android.content.Context;
import android.os.Bundle;

import com.movile.next.seriestracker.model.Episode;

public class EpisodeLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {

    private Context context;
    private  String serieId;
    IEpisodeLoader handler;

    public EpisodeLoaderCallback(Context context, String serieId, IEpisodeLoader handler) {
        this.context = context;
        this.serieId = serieId;
        this.handler = handler;
    }

    @Override
    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        return new EpisodeLoader(context, serieId);
    }

    @Override
    public void onLoadFinished(Loader<Episode> loader, Episode data) {
        this.handler.onEpisodeLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Episode> loader) {

    }
}
