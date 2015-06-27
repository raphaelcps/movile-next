package com.movile.next.seriestracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.adapter.SeasonDetailsAdapter;
import com.movile.next.seriestracker.adapter.SeasonDetailsClickListener;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.text.MessageFormat;
import java.util.List;

public class SeasonDetailsActivity extends com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity implements SeasonDetailsView, SeasonDetailsClickListener {

    SeasonDetailsPresenter presenter;
    SeasonDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        presenter = new SeasonDetailsPresenter(this, this);
        setupContent();
    }

    private void loadToolbarTitle(String show, Long season) {
        getSupportActionBar().setTitle(MessageFormat.format("S{0} - {1}", season, show));
    }

    private void setupContent() {
        showLoading();

        configureToolbar();
        loadToolbarTitle("Under The Dome", 1l);

        ListView view = (ListView)findViewById(R.id.season_details_list_view);
        view.addHeaderView(LayoutInflater.from(this).inflate(R.layout.season_details_list_header, view, false));
        adapter = new SeasonDetailsAdapter(this, this);
        view.setAdapter(adapter);
        presenter.getSeasonDetails("under-the-dome", 1l);
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
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadSeason(List<Episode> episodes) {
        adapter.addAll(episodes);

        // TODO: unmock
        // set score
        ((TextView) findViewById(R.id.season_details_score)).setText(String.format("%.1f", 9.5));

        // set year
        ((TextView) findViewById(R.id.season_details_year)).setText(Long.toString(2008));

        // set image
        String header = "https://walter.trakt.us/images/shows/000/001/388/thumbs/original/2fd220ab54.jpg";
        String thumb = "https://walter.trakt.us/images/shows/000/001/388/posters/thumb/fa39b59954.jpg";
        if (header != null || thumb != null) {
            loadImages(header, thumb);
        }

        hideLoading();
    }

    @Override
    public void loadEpisode(Episode item) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW, "under-the-dome");
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SEASON, 1l);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODE, item.number());

        startActivity(intent);
    }
}
