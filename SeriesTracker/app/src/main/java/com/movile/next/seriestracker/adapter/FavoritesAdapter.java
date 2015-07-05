package com.movile.next.seriestracker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity;
import com.movile.next.seriestracker.database.dbflow.entity.FavoriteEntity$Adapter;

/**
 * Created by movile on 05/07/15.
 */
public class FavoritesAdapter extends CursorAdapter {

    FavoritesListener mListener;

    static class ViewHolder {

        View mRoot;
        TextView mTitle;

        TextView title() {
            return mTitle;
        }

        public ViewHolder(View view) {
            mRoot = view;
            mTitle = (TextView) view.findViewById(R.id.favorite_show_title);
        }
    }

    public FavoritesAdapter(Context context, FavoritesListener listener) {
        super(context, null, 0);
        mListener = listener;
    }


    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_show_item, parent, false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    public void bindView(View view, Context context, Cursor cursor) {

        final FavoriteEntity entity = new FavoriteEntity();
        new FavoriteEntity$Adapter().loadFromCursor(cursor, entity);

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title().setText(entity.title());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFavoriteClicked(entity.slug());
            }
        });
    }

    public interface FavoritesListener {
        void onFavoriteClicked(String slug);
    }
}
