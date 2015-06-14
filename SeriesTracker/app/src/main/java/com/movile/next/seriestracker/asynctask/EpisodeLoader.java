package com.movile.next.seriestracker.asynctask;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.next.seriestracker.model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public class EpisodeLoader extends AsyncTaskLoader<Episode> {

    public EpisodeLoader(Context context, String seriesId) {
        super(context);
    }

    @Override
    public Episode loadInBackground() {
        return null;
    }

}

