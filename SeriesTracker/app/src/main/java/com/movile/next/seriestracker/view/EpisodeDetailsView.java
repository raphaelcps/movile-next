package com.movile.next.seriestracker.view;

import android.graphics.Bitmap;

import com.movile.next.seriestracker.model.Episode;

/**
 * Created by movile on 20/06/15.
 */
public interface EpisodeDetailsView {

    void loadEpisode(Episode episode);

    void loadImage(Bitmap image);
}
