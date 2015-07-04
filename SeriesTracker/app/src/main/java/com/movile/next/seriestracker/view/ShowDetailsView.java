package com.movile.next.seriestracker.view;

import com.movile.next.seriestracker.model.Favorite;
import com.movile.next.seriestracker.model.Show;

/**
 * Created by movile on 21/06/15.
 */
public interface ShowDetailsView {
    void loadShow(Show show);

    void onFavoriteLoaded(Favorite favorite);
}
