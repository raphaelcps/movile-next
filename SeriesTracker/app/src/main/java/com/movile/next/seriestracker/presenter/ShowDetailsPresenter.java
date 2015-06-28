package com.movile.next.seriestracker.presenter;

import android.content.Context;

import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.client.ShowRemoteClient;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.view.ShowDetailsView;

import java.util.List;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsPresenter implements ShowRemoteClient.IShowLoader {

    private ShowDetailsView mView;
    private Context mContext;
    private ShowRemoteClient mClient;

    public ShowDetailsPresenter(Context context, ShowDetailsView mView) {
        this.mView = mView;
        this.mContext = context;
        this.mClient = new ShowRemoteClient(ApiConfiguration.URL_BASE, this);
    }

    public void getShowInfo(String show) {
        mClient.getShowInfo(show);
    }

    @Override
    public void onShowLoaded(Show show) {
        mView.loadShow(show);
    }

    @Override
    public void onShowsLoaded(List<Show> shows) {

    }

}
