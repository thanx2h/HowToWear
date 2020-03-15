package com.an.howtowear.support.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.an.howtowear.HTWApp;
import com.an.howtowear.R;
import com.an.howtowear.ui.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

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
        Intent notiIntent = new Intent(HTWApp.getContext(), MainActivity.class);
        notiIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(HTWApp.getContext(), 0, notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);
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
//        NotificationManagerCompat.from(HTWApp.getContext()).notify(NOTIFICATION_UNIQUE_ID, builder.build());

    }

    public void showNotificationMessage2(){
        NotificationManager notificationManager = (NotificationManager) HTWApp.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(HTWApp.getContext(), MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingI = PendingIntent.getActivity(HTWApp.getContext(), 0,
                notificationIntent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(HTWApp.getContext(), "default");


        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남


            String channelName ="매일 알람 채널";
            String description = "매일 정해진 시간에 알람합니다.";
            int importance = NotificationManager.IMPORTANCE_HIGH; //소리와 알림메시지를 같이 보여줌

            NotificationChannel channel = new NotificationChannel("default", channelName, importance);
            channel.setDescription(description);

            if (notificationManager != null) {
                // 노티피케이션 채널을 시스템에 등록
                notificationManager.createNotificationChannel(channel);
            }
        }else builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남


        builder.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())

                .setTicker("{Time to watch some cool stuff!}")
                .setContentTitle("상태바 드래그시 보이는 타이틀")
                .setContentText("상태바 드래그시 보이는 서브타이틀")
                .setContentInfo("INFO")
                .setContentIntent(pendingI);

        if (notificationManager != null) {

            // 노티피케이션 동작시킴
            notificationManager.notify(1234, builder.build());

            Calendar nextNotifyTime = Calendar.getInstance();

            // 내일 같은 시간으로 알람시간 결정
            nextNotifyTime.add(Calendar.DATE, 1);

//            //  Preference에 설정한 값 저장
//            SharedPreferences.Editor editor = context.getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
//            editor.putLong("nextNotifyTime", nextNotifyTime.getTimeInMillis());
//            editor.apply();
//
//            Date currentDateTime = nextNotifyTime.getTime();
//            String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
//            Toast.makeText(context.getApplicationContext(),"다음 알람은 " + date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();
        }
    }
}
