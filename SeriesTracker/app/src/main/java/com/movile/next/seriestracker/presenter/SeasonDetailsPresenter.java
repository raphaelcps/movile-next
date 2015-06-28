package com.movile.next.seriestracker.presenter;

import android.content.Context;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.remote.client.ISeasonLoader;
import com.movile.next.seriestracker.remote.client.SeasonRemoteClient;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.util.List;

/**
 * Created by Raphael on 20/06/2015.
 */
public class SeasonDetailsPresenter implements ISeasonLoader{

    private SeasonDetailsView mView;
    private Context mContext;
    private SeasonRemoteClient mClient;

    public SeasonDetailsPresenter(Context context, SeasonDetailsView mView) {
        this.mView = mView;
        this.mContext = context;
        this.mClient = new SeasonRemoteClient(ApiConfiguration.URL_BASE, this);
    }

    @Override
    public void onSeasonLoaded(List<Episode> episodes) {
        mView.loadSeason(episodes);
    }

    @Override
    public void onSeasonsLoaded(List<Season> seasons) {
    }

    public void getSeasonDetails(String show, Long season) {
        mClient.getSeasonDetails(show, season);
    }
}
