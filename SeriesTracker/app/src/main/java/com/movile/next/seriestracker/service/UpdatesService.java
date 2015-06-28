package com.movile.next.seriestracker.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.movile.next.seriestracker.broadcast.UpdateReceiver;
import com.movile.next.seriestracker.model.ShowUpdate;
import com.movile.next.seriestracker.remote.client.UpdatesClient;
import com.movile.next.seriestracker.util.ApiConfiguration;

public class UpdatesService extends IntentService {

    public UpdatesService() {
        super(UpdatesService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ShowUpdate update = new UpdatesClient(ApiConfiguration.API_URL_UPDATES).getLatest();

        //Log.d("Service", update.title());
        //Toast.makeText(this, "Oi", Toast.LENGTH_SHORT).show();

        Intent bIntent = new Intent(ApiConfiguration.BROADCAT_ACTION_SHOW_UPDATE);
        bIntent.putExtra(UpdateReceiver.EXTRA_UPDATE, update);
        LocalBroadcastManager.getInstance(this).sendBroadcast(bIntent);
        //sendBroadcast(bIntent);

    }
}
