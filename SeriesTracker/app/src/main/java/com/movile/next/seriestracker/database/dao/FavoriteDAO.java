package com.movile.next.seriestracker.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.movile.next.seriestracker.database.manual.entity.FavoriteEntity;
import com.movile.next.seriestracker.database.manual.helper.DatabaseHelper;
import com.movile.next.seriestracker.database.manual.helper.ProviderUriHelper;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Favorite;
import com.movile.next.seriestracker.database.manual.entity.FavoriteEntity.*;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteDAO {

    Context mContext;
    ProviderUriHelper helper;
    public FavoriteDAO(Context context) {
        super();
        mContext = context;
        helper = new ProviderUriHelper(context);
    }

    public void save(Favorite favorite) {
        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        mContext.getContentResolver().insert(helper.mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME), entity.toContentValues());
    }

    public Cursor all() {
        return mContext.getContentResolver().query(helper.mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME),
                null, null, null, FavoriteEntity.FavoriteEntityFields.COLUMN_TITLE);
    }

    public void delete(String slug) {
        mContext.getContentResolver().delete(helper.mountManyUri(FavoriteEntity.FavoriteEntityFields.TABLE_NAME),
                FavoriteEntity.FavoriteEntityFields.COLUMN_SLUG + " = ?", new String[]{slug});
    }

    public Favorite query(String slug) {
        Favorite favorite = null;
        String[] param = {slug};
        Cursor cursor = null;

        try {
            cursor = mContext.getContentResolver().query(helper.mountManyUri(FavoriteEntityFields.TABLE_NAME), null, FavoriteEntityFields.COLUMN_SLUG + " = ?", param, null);

            if (cursor.moveToFirst()) {
                FavoriteEntity entity = new FavoriteEntity().fromCursor(cursor);
                favorite = new Favorite(entity.slug(), entity.title());
            }
        } catch (Exception ex) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return favorite;
    }

}
