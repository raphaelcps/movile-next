package com.movile.next.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.adapter.SeasonDetailsAdapter;
import com.movile.next.seriestracker.adapter.SeasonDetailsClickListener;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.text.MessageFormat;
import java.util.List;

public class SeasonDetailsActivity extends com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity implements SeasonDetailsView, SeasonDetailsClickListener {

    public static final String EXTRA_SEASON = "season";
    public static final String EXTRA_SHOW = "show";
    private SeasonDetailsPresenter mPresenter;
    private  SeasonDetailsAdapter mAdapter;
    private Season mSeason;
    private Show mShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        mPresenter = new SeasonDetailsPresenter(this, this);
        setupContent();
    }

    private void loadToolbarTitle(String show, Long season) {
        getSupportActionBar().setTitle(MessageFormat.format("Season {0} - {1}", season, show));
    }

    private void setupContent() {
        showLoading();

        loadParams();

        configureToolbar();
        loadToolbarTitle(mShow.title(), mSeason.number());

        ListView view = (ListView)findViewById(R.id.season_details_list_view);
        view.addHeaderView(LayoutInflater.from(this).inflate(R.layout.season_details_list_header, view, false));
        mAdapter = new SeasonDetailsAdapter(this, this);
        view.setAdapter(mAdapter);
        mPresenter.getSeasonDetails(mShow.ids().slug(), mSeason.number());
    }

    private void loadParams() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mSeason = (Season)extras.getSerializable(EXTRA_SEASON);
            mShow = (Show)extras.getSerializable(EXTRA_SHOW);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_season_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadImages(String header, String thumb) {
        ImageView imageView = (ImageView)findViewById(R.id.season_details_list_header_image);
        Glide.with(this)
                .load(header)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(imageView);

        imageView = (ImageView)findViewById(R.id.season_details_thumb);
        Glide.with(this)
                .load(thumb)
                .placeholder(R.drawable.season_details_show_placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadSeason(List<Episode> episodes) {
        mAdapter.addAll(episodes);

        // set score
        ((TextView) findViewById(R.id.season_details_score)).setText(String.format("%.1f", mSeason.rating()));

        // set year
        ((TextView) findViewById(R.id.season_details_year)).setText(Long.toString(mShow.year()));

        // set image
        String header = mSeason.images().thumb().get(Images.ImageSize.FULL);
        String thumb = mSeason.images().poster().get(Images.ImageSize.THUMB);
        if (header != null || thumb != null) {
            loadImages(header, thumb);
        }

        hideLoading();
    }

    @Override
    public void loadEpisode(Episode item) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW, mShow.ids().slug());
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW_NAME, mShow.title());
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SEASON, mSeason.number());
        intent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODE, item.number());

        startActivity(intent);
    }
}
