package com.movile.next.seriestracker.asynctask;

import com.movile.next.seriestracker.model.Episode;

/**
 * Created by movile on 14/06/15.
 */
public interface IEpisodeLoader {
    void onEpisodeLoaded (Episode episode);
}
