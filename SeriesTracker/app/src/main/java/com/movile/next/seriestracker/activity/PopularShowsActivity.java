package com.movile.next.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ShowsAdapter;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowsPresenter;
import com.movile.next.seriestracker.view.ShowsView;

import java.util.List;


public class PopularShowsActivity extends BaseNavigationToolbarActivity implements ShowsView, ShowsAdapter.ShowClickListener {

    private ShowsPresenter presenter;
    private ShowsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_shows_activity);

        presenter = new ShowsPresenter(this, this);
        presenter.getShows();

        configure();
    }

    private void configure() {
        configureToolbar();

        showLoading();

        // TODO use locale
        getSupportActionBar().setTitle("Popular Shows");

        // Grid Adapter
        GridView grid = (GridView)findViewById(R.id.popular_shows_grid);
        mAdapter = new ShowsAdapter(this, this);
        grid.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popular_shows, menu);
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

    @Override
    public void loadShows(List<Show> shows) {
        mAdapter.populate(shows);
        hideLoading();
    }

    @Override
    public void onShowClicked(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW_SLUG, show.ids().slug());
        startActivity(intent);
    }
}
