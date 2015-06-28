package com.movile.next.seriestracker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.movile.next.seriestracker.model.ShowUpdate;

public class ShowUpdateReceiver extends BroadcastReceiver {

    public final static String EXTRA_UPDATE = "show_update";

    public ShowUpdateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getExtras() != null) {
            ShowUpdate update = (ShowUpdate)intent.getExtras().getSerializable(EXTRA_UPDATE);

            //Log.d("Receiver", update.title());
            Toast.makeText(context, update.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
