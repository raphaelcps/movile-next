package com.movile.next.seriestracker.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.movile.next.seriestracker.service.ShowUpdateService;
import com.movile.next.seriestracker.util.ApiConfiguration;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    private void setReceiver(Context context) {

        LocalBroadcastManager.getInstance(context).registerReceiver(new ShowUpdateReceiver(), new IntentFilter(ApiConfiguration.BROADCAT_ACTION_SHOW_UPDATE));

        final long INTERVAL = 10 * 1000;
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, new Intent(context, ShowUpdateService.class), 0);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, 0, INTERVAL, pendingIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BootReceiver", "BootReceiver called");
        setReceiver(context);
    }
}
