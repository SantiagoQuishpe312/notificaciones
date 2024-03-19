package com.example.notificaciones;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "NOTIFICAR_ACCION";
    private static final int NOTIFICATION_ID = 1;
    Button notificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificar = findViewById(R.id.btnNotificar);
        crearCanal();
        notificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //crearNotificacion();
                calendario();
            }
        });
    }
    @SuppressLint("MissingPermission")
    private void crearNotificacion() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle("Recordar tomar tu medicación");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setColor(Color.MAGENTA);
        builder.setVibrate(new long[]{1000, 200, 1000, 2000});

        builder.setTicker("No olvides tomar tu medicamento");
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }

    private void crearCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notificaciones";
            String description = "Canal para notificaciones";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
    @SuppressLint("ScheduleExactAlarm")
    private void calendario() {
        Intent intent = new Intent(getApplicationContext(), Notificaciones.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), NOTIFICATION_ID, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 8000, pendingIntent);
        }
    }
}
