package com.movile.next.seriestracker.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.next.seriestracker.adapter.ShowDetailsAdapter;
import com.movile.next.seriestracker.fragment.ShowInfoFragment;
import com.movile.next.seriestracker.fragment.ShowSeasonFragment;
import com.movile.next.seriestracker.model.Favorite;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Season;
import com.movile.next.seriestracker.model.Show;
import com.movile.next.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.next.seriestracker.view.ShowDetailsView;

import java.util.ArrayList;
import java.util.List;


public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowInfoFragment.OnFragmentInteractionListener, ShowSeasonFragment.OnFragmentInteractionListener, ShowDetailsView {

    public final static String EXTRA_SHOW_SLUG = "show_slug";
    private ShowDetailsPresenter mPresenter;
    private String mShowSlug = "breaking-bad";
    private Show mShow;
    private FloatingActionButton mFavoriteButton;

    private void loadParams() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mShowSlug = extras.getString(EXTRA_SHOW_SLUG);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        FloatingActionButton a = null;

        loadParams();

        mPresenter = new ShowDetailsPresenter(this, this, getSupportLoaderManager());
        mPresenter.getShowInfo(mShowSlug);
        mPresenter.getFavoriteStatus(mShowSlug);

        configure();
    }

    List<Animator> heartBeat() {
        AnimatorSet togetherSet = new AnimatorSet();

        List<Animator> list = new ArrayList<Animator>();

        togetherSet.playTogether(ObjectAnimator.ofFloat(mFavoriteButton, "scaleX", 1.0f, 1.5f), ObjectAnimator.ofFloat(mFavoriteButton, "scaleY", 1.0f, 1.5f));
        togetherSet.setDuration(50);
        list.add(togetherSet);

        togetherSet = new AnimatorSet();
        togetherSet.playTogether(ObjectAnimator.ofFloat(mFavoriteButton, "scaleX", 1.5f, 0.5f), ObjectAnimator.ofFloat(mFavoriteButton, "scaleY", 1.5f, 0.5f));
        togetherSet.setDuration(100);
        list.add(togetherSet);

        togetherSet = new AnimatorSet();
        togetherSet.playTogether(ObjectAnimator.ofFloat(mFavoriteButton, "scaleX", 0.5f, 1f), ObjectAnimator.ofFloat(mFavoriteButton, "scaleY", 0.5f, 1f));
        togetherSet.setDuration(50);
        list.add(togetherSet);

        return list;
    }

    private void animateHeart() {
        AnimatorSet sequentialSet = new AnimatorSet();
        AnimatorSet togetherSet = new AnimatorSet();

        List<Animator> list = new ArrayList<Animator>();
        list.addAll(heartBeat());
        list.addAll(heartBeat());


        sequentialSet.playSequentially(heartBeat());
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFavoriteButton, "rotation", 360f);
        animator.setDuration(600);
        //animator.setRepeatCount(ValueAnimator.INFINITE);

        togetherSet.playTogether(sequentialSet, animator);

        togetherSet.start();

    }


    private void configure() {
        //PagerTabStrip tabs = (PagerTabStrip)findViewById(R.id.show_details_pager_tab);
        //tabs.setTextColor(getResources().getColor(R.color.default_textColor_first));

        configureToolbar();
        //loadToolbarTitle(mShowSlug);
        showLoading();

        mFavoriteButton = (FloatingActionButton) findViewById(R.id.show_details_favorite_button);

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShow != null) {
                    mPresenter.setFavorite(mShow.ids().slug(), mShow.title());
                    animateHeart();
                }
            }
        });
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
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadShow(Show show) {
        mShow = show;

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

        loadToolbarTitle(show.title());

        hideLoading();
    }

    @Override
    public void onSeasonClick(Season season) {
        Intent intent = new Intent(this, SeasonDetailsActivity.class);
        intent.putExtra(SeasonDetailsActivity.EXTRA_SEASON, season);
        intent.putExtra(SeasonDetailsActivity.EXTRA_SHOW, mShow);
        startActivity(intent);
    }

    @Override
    public void onFavoriteLoaded(Favorite favorite) {
        //Log.d("aaaaaaa", favorite.title());
        if (favorite != null) {
            Log.d("aaaaaaa", favorite.title());
            mFavoriteButton.setImageResource(R.drawable.show_details_favorite_on);
            mFavoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.show_details_favorite_on));
        } else {
            Log.d("aaaaaaa", "nothign");
            mFavoriteButton.setImageResource(R.drawable.show_details_favorite_off);
            mFavoriteButton.setBackgroundTintList(getResources().getColorStateList(R.color.show_details_favorite_off));
        }
    }
}
