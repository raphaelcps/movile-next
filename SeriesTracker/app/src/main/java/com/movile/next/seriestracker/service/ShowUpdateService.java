package com.movile.next.seriestracker.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;

import com.movile.next.seriestracker.model.ShowUpdate;
import com.movile.next.seriestracker.receiver.ShowUpdateReceiver;
import com.movile.next.seriestracker.remote.client.UpdatesClient;
import com.movile.next.seriestracker.util.ApiConfiguration;
import com.movile.next.seriestracker.util.Constants;
import com.movile.next.seriestracker.util.FormatUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class ShowUpdateService extends IntentService {

    public ShowUpdateService() {
        super(ShowUpdateService.class.getSimpleName());
    }

    private void setPreferenceUpdate(String date) {
        SharedPreferences.Editor editor = getSharedPreferences(Constants.COMMON_SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putString(Constants.KEY_LAST_UPDATE, date);
        editor.commit();
    }

    private void setInternalUpdate(String date) {
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(this.openFileInput(Constants.LAST_UPDATE_FILE)));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setLastUpdate(String date) {
        setPreferenceUpdate(date);
    }

    private String getLastUpdate() {
        return getPreferenceUpdate();
    }

    private String getPreferenceUpdate() {
        return getSharedPreferences(Constants.COMMON_SHARED_PREFS, MODE_PRIVATE).getString(Constants.KEY_LAST_UPDATE, null);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ShowUpdate update = new UpdatesClient(ApiConfiguration.API_URL_UPDATES).getLatest();

        String lastUpdate = getLastUpdate();
        if (lastUpdate == null || !lastUpdate.equals(update.date())) {

            if (lastUpdate == null || FormatUtil.formatDate(lastUpdate).before(FormatUtil.formatDate(update.date()))) {

                setLastUpdate(update.date());

                Intent bIntent = new Intent(ApiConfiguration.BROADCAT_ACTION_SHOW_UPDATE);
                bIntent.putExtra(ShowUpdateReceiver.EXTRA_UPDATE, update);
                //LocalBroadcastManager.getInstance(this).sendBroadcast(bIntent);
                sendBroadcast(bIntent);
            }
        }

        //Log.d("Service", update.title());
        //Toast.makeText(this, "Oi", Toast.LENGTH_SHORT).show();

    }
}
