package com.movile.next.seriestracker.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Raphael on 19/06/2015.
 */
public class RemoteImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = RemoteImageAsyncTask.class.getSimpleName();

    private IRemoteImageLoader mHandler;

    public RemoteImageAsyncTask(IRemoteImageLoader handler) {
        super();
        this.mHandler = handler;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            Log.e(TAG, "Error fetching image from " + url, e);
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.mHandler.onImageLoaded(bitmap);
    }

}
