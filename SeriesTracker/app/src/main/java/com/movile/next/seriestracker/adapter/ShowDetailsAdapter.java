package com.movile.next.seriestracker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.next.seriestracker.fragment.ShowInfoFragment;
import com.movile.next.seriestracker.fragment.ShowSeasonFragment;
import com.movile.next.seriestracker.model.Show;

import java.text.MessageFormat;

/**
 * Created by movile on 21/06/15.
 */
public class ShowDetailsAdapter extends FragmentPagerAdapter {

    private static final int PAGE_INFO = 0;
    private static final int PAGE_SEASONS = 1;

    private Show show;

    public ShowDetailsAdapter(FragmentManager manager, Show show) {
        super(manager);
        this.show = show;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case PAGE_INFO:
                return "INFO";
            case PAGE_SEASONS:
                return "SEASONS";
        }
        return "No Title";
    }

    @Override
    public Fragment getItem(int position) {
        if (position == PAGE_INFO) {
            return ShowInfoFragment.newInstance(show);
        } else {
            return ShowSeasonFragment.newInstance(show);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
