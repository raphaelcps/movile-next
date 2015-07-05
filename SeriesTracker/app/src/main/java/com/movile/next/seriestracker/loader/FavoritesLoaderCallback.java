package com.movile.next.seriestracker.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;



/**
 * Created by movile on 05/07/15.
 */

public class FavoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    public interface FavoritesHandler {
        void onFavoriteClicked(Cursor cursor);
    }

    Context mContext;
    FavoritesHandler mHandler;

    public FavoritesLoaderCallback(Context context, FavoritesHandler handler) {
        super();
        mContext = context;
        mHandler = handler;
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        return new FavoritesLoader(mContext);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mHandler.onFavoriteClicked(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

}
