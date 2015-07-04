package com.movile.next.seriestracker.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.presenter.EpisodeDetailsPresenter;
import com.movile.next.seriestracker.util.FormatUtil;
import com.movile.next.seriestracker.view.EpisodeDetailsView;

import java.text.MessageFormat;


public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView {

    public final static String EXTRA_SHOW = "show";
    public final static String EXTRA_SHOW_NAME = "show_name";
    public final static String EXTRA_SEASON = "season";
    public final static String EXTRA_EPISODE = "episode";

    private String mShow;
    private String mShowName;
    private Long mSeason;
    private Long mEpisode;

    public final static String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private EpisodeDetailsPresenter mPresenter;

    private void loadParameters() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mShow = extras.getString( EXTRA_SHOW);
            mShowName = extras.getString(EXTRA_SHOW_NAME);
            mSeason = extras.getLong(EXTRA_SEASON);
            mEpisode = extras.getLong(EXTRA_EPISODE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);
        showLoading();

        /*
        //new EpisodeFetcherAsyncTask(this, this).execute();
        getSupportLoaderManager().initLoader(
                0, null, new EpisodeLoaderCallback(this, "under-the-dome", this)
        ).forceLoad(); */

        // new EpisodeRemoteClient(ApiConfiguration.URL_BASE, this).getEpisodeDetails("under-the-dome", 1l, 1l);

        loadParameters();

        configureToolbar();
        loadToolbarTitle();

        mPresenter = new EpisodeDetailsPresenter(this);

        mPresenter.getEpisodeDetails(mShow, mSeason, mEpisode);

        Log.d(TAG, "onCreate()");
    }

    private void loadToolbarTitle() {
        getSupportActionBar().setTitle(MessageFormat.format("S{0}E{1} - {2}", mSeason, mEpisode, mShowName));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("key", "valueOriginal");
        Log.d(TAG, "onSaveInstanceState()" + outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState()" + savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_episode_details, menu);
        Log.d(TAG, "onCreateOptionsMenu()");
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
        ImageView imageView = (ImageView)findViewById(R.id.episode_details_screenshot);
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.highlight_placeholder)
            .centerCrop()
            .into(imageView);
    }

    @Override
    public void loadEpisode(Episode episode) {
        TextView title = (TextView)findViewById(R.id.episode_details_title);

        title.setText(episode.title());

        TextView date = (TextView)findViewById(R.id.episode_details_exhibition_time);
        date.setText(FormatUtil.formatDate(FormatUtil.formatDate(episode.firstAired())));

        TextView summary = (TextView)findViewById(R.id.episode_details_summary);
        summary.setText(episode.overview());

        // Image
        String url = episode.images().screenshot().get(Images.ImageSize.THUMB);
        if (url != null) {
            //new RemoteImageAsyncTask(this).execute(url);
            loadImage(url);
        }

        hideLoading();
    }

    @Override
    public void loadImage(Bitmap image) {
        ImageView imageView = (ImageView)findViewById(R.id.episode_details_screenshot);
        imageView.setImageBitmap(image);
    }

}
