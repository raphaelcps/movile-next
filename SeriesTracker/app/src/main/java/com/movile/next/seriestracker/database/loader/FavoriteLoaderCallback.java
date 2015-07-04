package com.movile.next.seriestracker.database.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Favorite;

public class FavoriteLoaderCallback implements LoaderManager.LoaderCallbacks<Favorite> {

    public interface FavoriteCallback {
        void onFavoriteLoaded(Favorite favorite);
    }

    public static final String SLUG_PARAM = "slug";
    public static final String TITLE_PARAM = "title";
    public static final String MODIFY_PARAM = "modify";

    private Context mContext;
    private FavoriteCallback mHandler;

    public FavoriteLoaderCallback(Context context, FavoriteCallback handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    public Loader<Favorite> onCreateLoader(int id, Bundle args) {
        return new FavoriteLoader(mContext, args.getString(SLUG_PARAM), args.getString(TITLE_PARAM), args.getBoolean(MODIFY_PARAM));
    }

    @Override
    public void onLoadFinished(Loader<Favorite> loader, Favorite data) {
        this.mHandler.onFavoriteLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Favorite> loader) {

    }

}
