package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.model.Episode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Raphael on 20/06/2015.
 */
public class SeasonDetailsAdapter extends ArrayAdapter<Episode> {

    SeasonDetailsClickListener listener;
    Context context;
    List<Episode> episodes;

    public SeasonDetailsAdapter(Context context, SeasonDetailsClickListener listener) {
        super(context, R.layout.season_details_list_item);

        this.listener = listener;
        this.context = context;
    }

    public int getCount() {
        if (episodes != null) {
            return episodes.size();
        }
        return 0;
    }

    public Episode getItem(int position) {
        if (episodes == null || episodes.size() <= position) {
            return null;
        }
        return episodes.get(position);
    }

    public long getItemId(int position) {
        Episode episode = getItem(position);
        if (episode == null) {
            return -1;
        }
        return episode.number();
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resource = R.layout.season_details_list_item;
            /*
            if (type == TYPE_TBA) {
                resource = R.layout.episode_item_tba;
            }*/

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

    private void populateViewFromHolder(ViewHolder holder, int position, int type) {
        final Episode item = getItem(position);
        if (item != null) {
            Long num = item.number();
            String prefix = "E0";
            if (num >= 10) {
                prefix = "E";
            }
            holder.getEpisodeNumber().setText(prefix + Long.toString(item.number()));
            holder.getTitle().setText(item.title());

            holder.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.loadEpisode(item);
                }
            });
        }
    }

    public int getViewTypeCount() { return 1; }
    public int getItemViewType(int position) { return 0; }

    public static class ViewHolder {
        View view;
        TextView episodeNumber;
        TextView title;

        public TextView getEpisodeNumber() {
            return episodeNumber;
        }

        public TextView getTitle() {
            return title;
        }

        public View getView() {
            return view;
        }

        public ViewHolder(View view) {
            this.view = view;
            episodeNumber = (TextView)view.findViewById(R.id.season_details_list_item_episode_number);
            title = (TextView)view.findViewById(R.id.season_details_list_item_episode_title);
        }


    }


    @Override
    public void addAll(Collection<? extends Episode> collection) {
        super.addAll(collection);

        if (this.episodes == null) {
            this.episodes = new ArrayList<Episode>(collection);
        } else {
            this.episodes.addAll(collection);
        }

        this.notifyDataSetChanged();
    }
}
