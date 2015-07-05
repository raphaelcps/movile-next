package com.movile.next.seriestracker.view;

import android.database.Cursor;

/**
 * Created by movile on 05/07/15.
 */
public interface FavoritesView {
    void onFavoritesLoaded(Cursor cursor);
}
