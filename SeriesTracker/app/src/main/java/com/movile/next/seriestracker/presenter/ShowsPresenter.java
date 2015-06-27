package com.movile.next.seriestracker.presenter;

import android.content.Context;

import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.client.ShowRemoteClient;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.view.ShowDetailsView;
import com.movile.next.seriestracker.view.ShowsView;

import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public class ShowsPresenter implements ShowRemoteClient.IShowLoader {

    ShowsView mView;
    Context context;
    ShowRemoteClient mClient;

    public ShowsPresenter(Context context, ShowsView mView) {
        this.mView = mView;
        this.context = context;
        this.mClient = new ShowRemoteClient(ApiConfiguration.URL_BASE, this);
    }

    public void getShows() {
        mClient.getShows();
    }

    @Override
    public void onShowLoaded(Show show) {
    }

    @Override
    public void onShowsLoaded(List<Show> shows) {
        mView.loadShows(shows);
    }

}
