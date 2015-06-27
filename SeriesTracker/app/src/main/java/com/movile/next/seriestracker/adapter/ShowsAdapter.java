package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Episode;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Show;

import java.util.List;

/**
 * Created by Raphael on 27/06/2015.
 */
public class ShowsAdapter extends ArrayAdapter<Show> {

    private ShowClickListener mListener;
    private Context mContext;
    private List<Show> mShows;

    public ShowsAdapter(Context context, ShowClickListener listener) {
        super(context, R.layout.popular_shows_item);

        this.mListener = listener;
        this.mContext = context;
    }

    public int getCount() {
        if (mShows != null) {
            return mShows.size();
        }
        return 0;
    }

    public Show getItem(int position) {
        if (mShows == null || mShows.size() <= position) {
            return null;
        }
        return mShows.get(position);
    }

    public long getItemId(int position) {
        Show show = getItem(position);
        if (show == null) {
            return -1;
        }
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resource = R.layout.popular_shows_item;

            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position, type);

        return view;
    }

    private void loadImage(ImageView imageView, String url) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into(imageView);
    }

    private void populateViewFromHolder(ViewHolder holder, int position, int type) {
        final Show item = getItem(position);
        if (item != null) {

            loadImage(holder.getCover(), item.images().poster().get(Images.ImageSize.THUMB));
            holder.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onShowClicked(item);
                }
            });
        }
    }

    public int getViewTypeCount() { return 1; }
    public int getItemViewType(int position) { return 0; }

    public static class ViewHolder {
        View root;
        ImageView coverView;

        public ImageView getCover() {
            return coverView;
        }

        public View getRoot() {
            return root;
        }

        public ViewHolder(View view) {
            this.root = view;
            this.coverView = (ImageView)view.findViewById(R.id.popular_shows_cover);
        }
    }

    public void populate(List<Show> shows) {
        this.mShows= shows;
        this.notifyDataSetChanged();
    }

    public interface ShowClickListener {
        void onShowClicked(Show show);
    }

}
