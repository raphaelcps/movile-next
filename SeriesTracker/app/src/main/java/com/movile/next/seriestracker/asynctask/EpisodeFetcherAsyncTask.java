package com.movile.next.seriestracker.asynctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.movile.next.seriestracker.business.FetchLocalEpisodeDetails;
import com.movile.next.seriestracker.model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public class EpisodeFetcherAsyncTask extends AsyncTask<Void, Void, Episode> {

    private Context context;
    private IEpisodeLoader loader;
    public EpisodeFetcherAsyncTask(Context context, IEpisodeLoader loader) {
        this.context = context;
        this.loader = loader;
    }

    @Override
    protected Episode doInBackground(Void... params) {
        FetchLocalEpisodeDetails fetcher = new FetchLocalEpisodeDetails();

        return fetcher.get(context);
    }

    @Override
    protected void onPostExecute(Episode episode) {
        loader.onEpisodeLoaded(episode);
    }
}
