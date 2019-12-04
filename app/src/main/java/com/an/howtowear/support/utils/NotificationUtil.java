package com.an.howtowear.support.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.an.howtowear.HTWApp;
import com.an.howtowear.R;
import com.an.howtowear.ui.activity.MainActivity;

public class NotificationUtil {

    private static final String NOTIFICATION_CHANNEL_ID = "Notification_001";
    private static final String CHANNEL_MANE = "Notification_Defalut";
    private static final int NOTIFICATION_UNIQUE_ID = 1234;

    private static NotificationUtil notificationUtil;
    private static NotificationManager notificationManager;

    private NotificationUtil() {

    }

    public static synchronized NotificationUtil getInstance() {
        if (notificationUtil == null) {
            notificationUtil = new NotificationUtil();
        }

        return notificationUtil;
    }

    private synchronized NotificationManager getNotificationManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) HTWApp.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }

    public void showNotificationMessage() {
        Intent notificationIntent = new Intent(HTWApp.getContext(), MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(HTWApp.getContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(HTWApp.getContext(), NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(HTWApp.getContext().getResources(), R.drawable.ic_launcher_foreground)) //BitMap 이미지 요구
                .setContentTitle("상태바 드래그시 보이는 타이틀")
                .setContentText("상태바 드래그시 보이는 서브타이틀")
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_MANE, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("");

            // 노티피케이션 채널을 시스템에 등록
            getNotificationManager().createNotificationChannel(channel);

        } else
            builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        getNotificationManager().notify(NOTIFICATION_UNIQUE_ID, builder.build()); // 고유숫자로 노티피케이션 동작시킴
    }
}
