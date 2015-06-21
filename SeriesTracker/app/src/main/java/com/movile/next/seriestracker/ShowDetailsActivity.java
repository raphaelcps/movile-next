package com.movile.next.seriestracker;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ShowDetailsAdapter;
import com.movile.next.seriestracker.fragment.ShowInfoFragment;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.next.seriestracker.view.ShowDetailsView;


public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowInfoFragment.OnFragmentInteractionListener, ShowDetailsView {

    ShowDetailsPresenter presenter;
    String showKey = "under-the-dome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        presenter = new ShowDetailsPresenter(this, this);
        presenter.getShowInfo(showKey);
        configureToolbar();
        loadToolbarTitle(showKey);
        showLoading();
    }

    private void loadToolbarTitle(String show) {
        getSupportActionBar().setTitle(show);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_details, menu);
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
    public void loadShow(Show show) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.show_details_content);
        viewPager.setAdapter(new ShowDetailsAdapter(getSupportFragmentManager(), show));
        hideLoading();
    }
}
