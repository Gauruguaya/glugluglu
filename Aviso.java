package com.example.wmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
public class Aviso extends Worker {
    public Aviso(@NonNull Context context, @NonNull WorkerParameters workerParams){
        super(context, workerParams);
    }
    @NonNull
    @Override
    public  Result doWork(){
        sendNotification("Hora de tomar agua", "Mensaje de WorkManager");
        return Result.success();
    }
    private void sendNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            channel = new NotificationChannel("default","Default",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }
        Calendar currentTime = Calendar.getInstance();
        NotificationCompat.Builder notification = new
                NotificationCompat.Builder(getApplicationContext(),"default");
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        if (hour >= 8 && hour <= 23){
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(getApplicationContext(),"default");

            PeriodicWorkRequest notificationWork =
                    new PeriodicWorkRequest.Builder(Aviso.class,1, TimeUnit.HOURS).build();

            WorkManager.getInstance(getApplicationContext()).enqueue(notificationWork);
        }
    }
}
