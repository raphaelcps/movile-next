package com.movile.next.seriestracker.presenter;

import android.content.Context;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.remote.client.ISeasonLoader;
import com.movile.next.seriestracker.remote.client.SeasonRemoteClient;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.view.SeasonDetailsView;
import com.movile.next.seriestracker.view.SeasonsDetailsView;

import java.util.List;

/**
 * Created by Raphael on 20/06/2015.
 */
public class SeasonsDetailsPresenter implements ISeasonLoader{

    SeasonsDetailsView mView;
    SeasonRemoteClient mClient;

    public SeasonsDetailsPresenter(SeasonsDetailsView mView) {
        this.mView = mView;
        this.mClient = new SeasonRemoteClient(ApiConfiguration.URL_BASE, this);
    }

    @Override
    public void onSeasonLoaded(List<Episode> episodes) {

    }

    @Override
    public void onSeasonsLoaded(List<Season> seasons) {
        mView.loadSeasons(seasons);
    }

    public void getSeasonsDetails(String show) {
        mClient.getSeasons(show);
    }
}
