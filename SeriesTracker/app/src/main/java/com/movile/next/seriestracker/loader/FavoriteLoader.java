package com.movile.next.seriestracker.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.movile.next.seriestracker.database.dao.FavoriteDBFlowDAO;
import com.movile.next.seriestracker.database.dao.IFavoriteDAO;
import com.movile.next.seriestracker.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */
public class FavoriteLoader extends AsyncTaskLoader<Favorite> {

    String mSlug;
    String mTitle;
    boolean mModify;
    IFavoriteDAO dao;

    public FavoriteLoader(Context context, String slug, String title, boolean modify) {
        super(context);

        dao = new FavoriteDBFlowDAO();
        mSlug = slug;
        mTitle = title;
        mModify = modify;
    }

    @Override
    public Favorite loadInBackground() {
        Favorite favorite = dao.query(mSlug);
        if (mModify) {
            if (favorite != null) {
                dao.delete(mSlug);
                return null;
            } else {
                favorite = new Favorite(mSlug, mTitle);
                dao.save(favorite);
                return favorite;
            }
        } else {
            return favorite;
        }
    }

}
