package com.movile.next.seriestracker.database.dbflow.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.movile.next.seriestracker.database.dbflow.SeriesTrackerDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by movile on 04/07/15.
 */
@Table(databaseName = SeriesTrackerDatabase.NAME)
public class FavoriteEntity extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String slug;

    @Column
    String title;

    public String slug() {
        return slug;
    }

    public String title() {
        return title;
    }

    public FavoriteEntity() {
        super();
    }

    public FavoriteEntity(String slug, String title) {
        super();
        this.slug = slug;
        this.title = title;
    }

    public FavoriteEntity fromCursor(Cursor cursor) {
        title = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteEntity$Table.TITLE));
        slug = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteEntity$Table.SLUG));
        return this;
    }

}
