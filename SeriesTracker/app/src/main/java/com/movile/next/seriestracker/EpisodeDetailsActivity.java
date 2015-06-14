package com.movile.next.seriestracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.TextView;

import com.movile.next.seriestracker.Util.FormatUtil;
import com.movile.next.seriestracker.asynctask.EpisodeFetcherAsyncTask;
import com.movile.next.seriestracker.asynctask.IEpisodeLoader;
import com.movile.next.seriestracker.model.Episode;


public class EpisodeDetailsActivity extends ActionBarActivity implements IEpisodeLoader {

    public final static String TAG = "EpisodeDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        new EpisodeFetcherAsyncTask(this, this).execute();

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

    @Override
    public void onEpisodeLoaded(Episode episode) {
        TextView title = (TextView)findViewById(R.id.episode_details_title);
        title.setText(episode.title());

        TextView date = (TextView)findViewById(R.id.episode_details_exhibition_time);
        date.setText(FormatUtil.formatDate(FormatUtil.formatDate(episode.firstAired())));

        TextView summary = (TextView)findViewById(R.id.episode_details_summary);
        summary.setText(episode.overview());
    }
}
