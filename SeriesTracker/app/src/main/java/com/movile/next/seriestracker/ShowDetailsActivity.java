package com.movile.next.seriestracker;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ShowDetailsAdapter;
import com.movile.next.seriestracker.fragment.ShowInfoFragment;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.next.seriestracker.view.ShowDetailsView;


public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowInfoFragment.OnFragmentInteractionListener, ShowDetailsView {

    ShowDetailsPresenter presenter;
    String showKey = "breaking-bad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        presenter = new ShowDetailsPresenter(this, this);
        presenter.getShowInfo(showKey);

        configure();
    }

    private void configure() {
        //PagerTabStrip tabs = (PagerTabStrip)findViewById(R.id.show_details_pager_tab);
        //tabs.setTextColor(getResources().getColor(R.color.default_textColor_first));

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

    private void loadImage(String url) {
        ImageView imageView = (ImageView)findViewById(R.id.show_details_screenshot);
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadShow(Show show) {
        // set pager
        ViewPager viewPager = (ViewPager) findViewById(R.id.show_details_content);
        viewPager.setAdapter(new ShowDetailsAdapter(getSupportFragmentManager(), show));

        // set score
        TextView score = (TextView) findViewById(R.id.show_details_score);
        score.setText(String.format("%.1f", show.rating()));

        // set launched year
        TextView year = (TextView) findViewById(R.id.show_details_launched_year);
        year.setText(Long.toString(show.year()));

        // set image
        String url = show.images().thumb().get(Images.ImageSize.FULL);
        if (url != null) {
            loadImage(url);
        }

        // status
        ((TextView) findViewById(R.id.show_details_ended)).setText(show.status());

        hideLoading();
    }
}
