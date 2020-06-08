package com.dicoding.intifada.submission5.setting.reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

import com.dicoding.intifada.submission5.R;
import com.dicoding.intifada.submission5.activity.MainActivity;

public class DailyReminder extends BroadcastReceiver {

    private static int id = 222;

    public DailyReminder() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        notification(context, context.getString(R.string.app_name),
                context.getResources().getString(R.string.message_daily_reminder), id);
    }

    private void notification(Context context, String title, String message, int id) {
        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "Daily Reminder";
        /*Bitmap bmp = null;
        try {
            InputStream in = new URL("https://dien16.github.io/images/dien.png").openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Release : ", String.valueOf(bmp));*/
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_new_releases_white_24dp)
                .setContentTitle(title)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setContentText(message)
                //.setLargeIcon(bmp)
                .setContentIntent(pendingIntent)
                //.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(sound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            //channel.enableVibration(true);
            //channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = builder.build();
        notificationManager.notify(id, notification);
    }

    public void setAlarmDaily(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(context, DailyReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, 0);

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Log.d("Daily : ", "acive");
        Toast.makeText(context, R.string.on_daily_reminder, Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarmDaily(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, 0);

        alarmManager.cancel(pendingIntent);
        Log.d("Daily : ", "tidak acive");
        Toast.makeText(context, R.string.off_daily_reminder, Toast.LENGTH_SHORT).show();
    }
}
