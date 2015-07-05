package com.movile.next.seriestracker.presenter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.*;

import com.movile.next.seriestracker.loader.FavoriteLoaderCallback;
import com.movile.next.seriestracker.loader.FavoritesLoaderCallback;
import com.movile.next.seriestracker.model.Favorite;
import com.movile.next.seriestracker.view.FavoritesView;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesPresenter implements FavoritesLoaderCallback.FavoritesHandler {

    FavoritesView mView;
    Context mContext;
    LoaderManager mLoadManager;

    public FavoritesPresenter(Context context, FavoritesView view, LoaderManager loadManager) {
        mView = view;
        mContext = context;
        mLoadManager = loadManager;
    }

    public void LoadFavorites() {
        mLoadManager.initLoader(
                0, null, new FavoritesLoaderCallback(mContext, this)
        ).forceLoad();
    }

    @Override
    public void onFavoriteClicked(Cursor cursor) {
        mView.onFavoritesLoaded(cursor);
    }
}
