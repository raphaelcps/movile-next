package com.movile.next.seriestracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.movile.next.seriestracker.adapters.SeasonDetailsAdapter;
import com.movile.next.seriestracker.adapters.SeasonDetailsClickListener;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.next.seriestracker.view.SeasonDetailsView;

import java.util.List;


public class SeasonDetailsActivity extends ActionBarActivity implements SeasonDetailsView, SeasonDetailsClickListener {

    SeasonDetailsPresenter presenter;
    SeasonDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        presenter = new SeasonDetailsPresenter(this, this);
        setupContent();
    }

    private void setupContent() {
        ListView view = (ListView)findViewById(R.id.season_details_list_view);
        adapter = new SeasonDetailsAdapter(this, this);
        view.setAdapter(adapter);
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

    @Override
    public void loadSeason(List<Episode> episodes) {

    }
}
