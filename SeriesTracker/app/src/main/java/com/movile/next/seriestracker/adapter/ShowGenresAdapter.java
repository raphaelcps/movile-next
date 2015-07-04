package com.movile.next.seriestracker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;

/**
 * Created by Raphael on 03/07/2015.
 */
public class ShowGenresAdapter extends RecyclerView.Adapter<ShowGenresAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        private View root;
        private TextView genre;

        public View getRoot() {
            return root;
        }

        public TextView getGenre() {
            return genre;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            genre = (TextView) root.findViewById(R.id.show_info_genre);
        }
    }

    private String[] mGenres;

    public ShowGenresAdapter() {
        super();
    }

    public ShowGenresAdapter(String[] genres) {
        super();
        mGenres = genres;
    }

    public void populate(String[] genres) {
        this.mGenres = genres;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_info_genre_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getGenre().setText(mGenres[position]);
    }

    @Override
    public int getItemCount() {
        return (mGenres != null)?mGenres.length:0;
    }
}
