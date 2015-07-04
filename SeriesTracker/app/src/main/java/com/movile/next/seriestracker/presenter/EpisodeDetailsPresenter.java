package com.movile.next.seriestracker.presenter;

import android.graphics.Bitmap;

import com.movile.next.seriestracker.loader.IEpisodeLoader;
import com.movile.next.seriestracker.asynctask.IRemoteImageLoader;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.client.EpisodeRemoteClient;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.view.EpisodeDetailsView;

/**
 * Created by movile on 20/06/15.
 */
public class EpisodeDetailsPresenter implements IEpisodeLoader, IRemoteImageLoader {

    private EpisodeDetailsView mView;
    private EpisodeRemoteClient mClient;

    public EpisodeDetailsPresenter(EpisodeDetailsView mView) {
        this.mView = mView;
        mClient = new EpisodeRemoteClient(ApiConfiguration.URL_BASE, this);
    }

    @Override
    public void onEpisodeLoaded(Episode episode) {
        mView.loadEpisode(episode);
    }

    @Override
    public void onImageLoaded(Bitmap image) {
        mView.loadImage(image);
    }

    public void getEpisodeDetails(String show, long season, long episode) {
        mClient.getEpisodeDetails(show, season, episode);
    }
}
