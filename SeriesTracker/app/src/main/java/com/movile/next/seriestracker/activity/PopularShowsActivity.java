package com.movile.next.seriestracker.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ShowsAdapter;
import com.movile.next.seriestracker.receiver.ShowUpdateReceiver;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowsPresenter;
import com.movile.next.seriestracker.service.ShowUpdateService;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.view.ShowsView;

import java.util.List;


public class PopularShowsActivity extends BaseNavigationToolbarActivity implements ShowsView, ShowsAdapter.ShowClickListener {

    private ShowsPresenter presenter;
    private ShowsAdapter mAdapter;

    // TODO: remove
    private void setReceiver(Context context) {

        LocalBroadcastManager.getInstance(context).registerReceiver(new ShowUpdateReceiver(), new IntentFilter(ApiConfiguration.BROADCAT_ACTION_SHOW_UPDATE));

        final long INTERVAL = 10 * 1000;
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, new Intent(context, ShowUpdateService.class), 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, INTERVAL, pendingIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: remove
        setReceiver(this);

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
