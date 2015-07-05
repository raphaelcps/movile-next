package com.movile.next.seriestracker.presenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;

import com.movile.next.seriestracker.loader.FavoriteLoaderCallback;
import com.movile.next.seriestracker.model.Favorite;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.remote.client.ShowRemoteClient;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.view.ShowDetailsView;

import java.util.List;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsPresenter implements ShowRemoteClient.IShowLoader, FavoriteLoaderCallback.FavoriteCallback {

    private final LoaderManager mLoadManager;
    private ShowDetailsView mView;
    private Context mContext;
    private ShowRemoteClient mClient;

    public ShowDetailsPresenter(Context context, ShowDetailsView mView, LoaderManager loadManager) {
        this.mView = mView;
        this.mContext = context;
        this.mClient = new ShowRemoteClient(ApiConfiguration.URL_BASE, this);
        this.mLoadManager = loadManager;
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

    public void getFavoriteStatus(String slug) {
        Bundle args = new Bundle();
        args.putString(FavoriteLoaderCallback.SLUG_PARAM, slug);
        mLoadManager.initLoader(
                0, args, new FavoriteLoaderCallback(mContext, this)
        ).forceLoad();
    }

    public void setFavorite(String slug, String title) {
        Bundle args = new Bundle();
        args.putString(FavoriteLoaderCallback.SLUG_PARAM, slug);
        args.putString(FavoriteLoaderCallback.TITLE_PARAM, title);
        args.putBoolean(FavoriteLoaderCallback.MODIFY_PARAM, true);

        mLoadManager.initLoader(
                1, args, new FavoriteLoaderCallback(mContext, this)
        ).forceLoad();
    }

    @Override
    public void onFavoriteLoaded(Favorite favorite) {
        mView.onFavoriteLoaded(favorite);
    }
}
