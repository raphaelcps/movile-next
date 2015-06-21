package com.movile.next.seriestracker.presenter;

import android.content.Context;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.remote.client.ISeasonLoader;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.util.List;

/**
 * Created by Raphael on 20/06/2015.
 */
public class SeasonDetailsPresenter implements ISeasonLoader{

    SeasonDetailsView mView;
    Context context;

    public SeasonDetailsPresenter(Context context, SeasonDetailsView mView) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void onSeasonLoaded(List<Episode> episodes) {
        mView.loadSeason(episodes);
    }
}
