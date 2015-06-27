package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.fragment.ShowSeasonFragment;
import com.movile.next.seriestracker.model.Images;
import com.movile.next.seriestracker.model.Season;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public class SeasonsRecyclerAdapter extends RecyclerView.Adapter<SeasonsRecyclerAdapter.ViewHolder> {

    public interface OnSeasonClickListener {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View root;
        private ImageView image;
        private TextView tile;
        private TextView episodes;

        public View getRoot() {
            return root;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getTile() {
            return tile;
        }

        public TextView getEpisodes() {
            return episodes;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            image = (ImageView) root.findViewById(R.id.show_season_item_image);
            tile = (TextView) root.findViewById(R.id.show_season_item_title);
            episodes = (TextView) root.findViewById(R.id.show_season_item_episodes);
        }
    }

    OnSeasonClickListener mListener;
    List<Season> mSeasons;
    Context mContext;

    public SeasonsRecyclerAdapter(Context context, OnSeasonClickListener listener) {
        super();
        mListener = listener;
        mContext = context;
    }

    public void populateSeasons(List<Season> seasons) {
        this.mSeasons = seasons;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_season_item, viewGroup, false);
        return new ViewHolder(view);
    }

    private void loadImage(ImageView imageView, String url) {
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Season season = mSeasons.get(position);

        holder.getTile().setText(String.format("Season %d", season.number()));
        holder.getEpisodes().setText(String.format("%d Episodes", season.episodeCount()));

        loadImage(holder.getImage(), season.images().poster().get(Images.ImageSize.THUMB));

    }

    @Override
    public int getItemCount() {
        if (mSeasons == null) {
            return 0;
        }
        return mSeasons.size();
    }
}
