package com.movile.next.seriestracker.database.dao;

import com.movile.next.seriestracker.model.Favorite;

/**
 * Created by movile on 04/07/15.
 */
public interface IFavoriteDAO {

    public void save(Favorite favorite);
    public void delete(String slug);
    public Favorite query(String slug);
}
