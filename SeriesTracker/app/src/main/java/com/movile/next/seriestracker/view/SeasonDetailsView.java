package com.movile.next.seriestracker.view;

import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Season;

import java.util.List;

/**
 * Created by Raphael on 20/06/2015.
 */
public interface SeasonDetailsView
{
    void loadSeason(List<Episode> episodes);
}
