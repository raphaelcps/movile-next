package com.movile.next.seriestracker.database.dao;

import android.database.Cursor;
import android.util.Log;

import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity;

import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity$Table;
import com.movile.next.seriestracker.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteDBFlowDAO implements IFavoriteDAO {

    @Override
    public void save(Favorite favorite) {
        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        entity.save();
    }

    @Override
    public void delete(String slug) {
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .queryClose();
    }

    @Override
    public Favorite query(String slug) {
        FavoriteEntity entity = new Select()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .querySingle();

        if (entity != null) {
            return new Favorite(entity.slug(), entity.title());
        }
        return null;
    }

    @Override
    public Cursor all() {
        return new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
    }
}
