package com.example.notificaciones;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notificaciones extends BroadcastReceiver {
    private static final String CHANNEL_ID = "NOTIFICAR_ACCION";
    private static final int NOTIFICATION_ID = 1;
    @Override
    public void onReceive(Context context, Intent intent) {
        crearNotificacion(context);
    }

    @SuppressLint("MissingPermission")
    private void crearNotificacion(Context c) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(c.getApplicationContext(), CHANNEL_ID);
        builder.setContentTitle("Recordar tomar tu medicación");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setColor(Color.MAGENTA);
        builder.setVibrate(new long[]{1000, 200, 1000, 2000});

        builder.setTicker("No olvides tomar tu medicamento");
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(c.getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }

}
