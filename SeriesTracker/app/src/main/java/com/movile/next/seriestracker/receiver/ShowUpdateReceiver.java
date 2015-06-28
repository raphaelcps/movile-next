package com.movile.next.seriestracker.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.movile.next.seriestracker.R;
import com.movile.next.seriestracker.activity.ShowDetailsActivity;
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
            //Toast.makeText(context, update.message(), Toast.LENGTH_SHORT).show();

            Intent pIntent = new Intent(context, ShowDetailsActivity.class);
            pIntent.putExtra(ShowDetailsActivity.EXTRA_SHOW_SLUG, update.show());
            pIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            /*
            PendingIntent action = PendingIntent.getActivity(
                    context, 0, pIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    */
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(ShowDetailsActivity.class);
            stackBuilder.addNextIntent(pIntent);
            PendingIntent action = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(update.title())
                    .setContentText(update.message())
                    .setContentIntent(action)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(update.message()));

            Notification notification = builder.build();

            NotificationManager manager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            manager.notify(0, notification);

        }
    }
}
