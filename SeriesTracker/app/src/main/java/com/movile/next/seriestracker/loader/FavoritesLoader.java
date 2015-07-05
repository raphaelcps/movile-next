package com.movile.next.seriestracker.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.movile.next.seriestracker.database.dao.FavoriteDBFlowDAO;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesLoader extends CursorLoader {

    public FavoritesLoader(Context context) {
        super(context);
    }

    public Cursor loadInBackground() {
        return new FavoriteDBFlowDAO().all();
    }

}
