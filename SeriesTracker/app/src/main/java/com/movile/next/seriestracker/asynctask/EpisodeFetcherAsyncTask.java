package com.movile.next.seriestracker.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.next.seriestracker.business.FetchLocalEpisodeDetails;
import com.movile.next.seriestracker.model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public class EpisodeFetcherAsyncTask extends AsyncTask<Void, Void, Episode> {

    private Context mContext;
    private IEpisodeLoader mLoader;
    public EpisodeFetcherAsyncTask(Context context, IEpisodeLoader loader) {
        this.mContext = context;
        this.mLoader = loader;
    }

    @Override
    protected Episode doInBackground(Void... params) {
        FetchLocalEpisodeDetails fetcher = new FetchLocalEpisodeDetails();

        return fetcher.get(mContext);
    }

    @Override
    protected void onPostExecute(Episode episode) {
        mLoader.onEpisodeLoaded(episode);
    }
}
