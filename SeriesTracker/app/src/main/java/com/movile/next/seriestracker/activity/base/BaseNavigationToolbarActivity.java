package com.movile.next.seriestracker.activity.base;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.movile.next.seriestracker.R;

public class BaseNavigationToolbarActivity extends BaseLoadingActivity {

    private ViewGroup mRoot;
    protected Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater layoutInflater = getLayoutInflater();

        mRoot = (ViewGroup) layoutInflater.inflate(R.layout.base_navigation_toolbar_activity, null);
        ViewGroup content = (ViewGroup) mRoot.findViewById(R.id.base_navigation_toolbar_content);

        layoutInflater.inflate(layoutResID, content, true);
        super.setContentView(mRoot);
    }

    public void configureToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.base_navigation_drawer_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
