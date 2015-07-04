package com.movile.next.seriestracker.loader;

import android.support.v4.app.*;
import android.support.v4.content.*;
import android.content.Context;
import android.os.Bundle;

import com.movile.next.seriestracker.model.Episode;

public class EpisodeLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {

    private Context mContext;
    private  String mShowSlug;
    private IEpisodeLoader mHandler;

    public EpisodeLoaderCallback(Context context, String showSlug, IEpisodeLoader handler) {
        this.mContext = context;
        this.mShowSlug = showSlug;
        this.mHandler = handler;
    }

    @Override
    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        return new EpisodeLoader(mContext, mShowSlug);
    }

    @Override
    public void onLoadFinished(Loader<Episode> loader, Episode data) {
        this.mHandler.onEpisodeLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Episode> loader) {

    }
}
