package com.example.dangminhtien.zingmp3.model;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;

import com.example.dangminhtien.zingmp3.R;

/**
 * Created by dangminhtien on 7/5/17.
 */

    public class create_notification {
        private static Context context;
        private static PendingIntent pendingIntent;
        private static Notification notification;
        private static RemoteViews remoteViews;
        private static NotificationManager noti_manager;
        private static Notification.Builder noti_builder;
        private static LayoutInflater inflater;
        private static View view_parent;
        private static Intent intent_main;

        public static final int ID_NOTIFI=16253719;
        public static final int REQUEST_CODE=1237612;

        @TargetApi(Build.VERSION_CODES.N)
        public create_notification (Context context) {
            this.context = context;
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.notifi_layout);
        }

        public static RemoteViews get_remoteviews  () {
            return remoteViews;
        }

        @TargetApi(Build.VERSION_CODES.N)
        public void create_noti (String ten_baihat, String duration) {
            noti_builder = new Notification.Builder(context);
            noti_manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            intent_main=new Intent();

            pendingIntent = PendingIntent.getBroadcast(context,REQUEST_CODE, intent_main, PendingIntent.FLAG_CANCEL_CURRENT);
            noti_builder.setSmallIcon(Icon.createWithBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.backward)));
            noti_builder.setContentIntent(pendingIntent);

            PendingIntent pending_pause_and_play = PendingIntent.getBroadcast(context, REQUEST_CODE, intent_main.setAction("play_and_pause"), PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setImageViewResource(R.id.btn_pause_play_noti, R.drawable.pause);
            remoteViews.setOnClickPendingIntent(R.id.btn_pause_play_noti, pending_pause_and_play);

            PendingIntent pending_prev = PendingIntent.getBroadcast(context, REQUEST_CODE, intent_main.setAction("previous"), PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btn_prev_noti, pending_prev);

            PendingIntent pending_next = PendingIntent.getBroadcast(context, REQUEST_CODE, intent_main.setAction("next"), PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btn_next_noti, pending_next);
                if(ten_baihat!= null) {
                    remoteViews.setTextViewText(R.id.txt_ten_baihat_noti, ten_baihat);
                }
                if (duration!=null) {
                    remoteViews.setTextViewText(R.id.txt_duration_noti, duration);
                }
            noti_builder.setCustomContentView(remoteViews);

            notification = noti_builder.build();
        }

        public Notification getNotification () {
            return notification;
        }

        public  void update_notifi (boolean is_play) {
            if (is_play) {
                get_remoteviews().setImageViewResource(R.id.btn_pause_play_noti, R.drawable.pause);
            } else {
                get_remoteviews().setImageViewResource(R.id.btn_pause_play_noti, R.drawable.play);
            }
            noti();
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void noti () {
            noti_manager.notify(ID_NOTIFI,getNotification());
        }

    }
