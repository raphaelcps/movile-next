package com.movile.next.seriestracker.remote.client;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Season;

import java.util.List;

/**
 * Created by movile on 20/06/15.
 */
public interface ISeasonLoader {
    void onSeasonLoaded(List<Episode> episodes);

    void onSeasonsLoaded(List<Season> seasons);
}

