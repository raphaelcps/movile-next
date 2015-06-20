package com.movile.next.seriestracker;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.presenter.EpisodeDetailsPresenter;
import com.movile.next.seriestracker.util.FormatUtil;
import com.movile.next.seriestracker.view.EpisodeDetailsView;


public class EpisodeDetailsActivity extends ActionBarActivity implements EpisodeDetailsView {

    public final static String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private EpisodeDetailsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        /*
        //new EpisodeFetcherAsyncTask(this, this).execute();
        getSupportLoaderManager().initLoader(
                0, null, new EpisodeLoaderCallback(this, "under-the-dome", this)
        ).forceLoad(); */

        // new EpisodeRemoteClient(ApiConfiguration.URL_BASE, this).getEpisodeDetails("under-the-dome", 1l, 1l);
        mPresenter = new EpisodeDetailsPresenter(this);

        mPresenter.getEpisodeDetails("under-the-dome", 1l, 2l);

        Log.d(TAG, "onCreate()");
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
    }

    @Override
    public void loadImage(Bitmap image) {
        ImageView imageView = (ImageView)findViewById(R.id.episode_details_screenshot);
        imageView.setImageBitmap(image);
    }

}
