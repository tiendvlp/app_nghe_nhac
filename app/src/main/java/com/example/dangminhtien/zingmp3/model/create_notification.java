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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.dangminhtien.zingmp3.R;

/**
 * Created by dangminhtien on 7/5/17.
 */

    public class create_notification {
        private Context context;
        private PendingIntent pendingIntent;
        private Notification noti1;
        private NotificationManager noti_manager;
        private Notification.Builder noti_builder;
        private Notification noti;
        private ImageButton btn_next_noti, btn_prev, btn_pause_noti, btn_play_noti;
        private TextView txt_ten_baihat_noti, txt_duration_noti;
        private LayoutInflater inflater;
        private View view_parent;
        private Intent intent_main;

        public static final int ID_NOTIFI=16253719;
        public static final int REQUEST_CODE=1237612;


        @TargetApi(Build.VERSION_CODES.M)
        public create_notification (Context context) {
            this.context = context;
            noti_builder = new Notification.Builder(context);
            noti_manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            intent_main=new Intent();
            pendingIntent = PendingIntent.getBroadcast(context,REQUEST_CODE, intent_main, PendingIntent.FLAG_CANCEL_CURRENT);
            noti_builder.setSmallIcon(Icon.createWithBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.backward)));
            noti_builder.setContentIntent(pendingIntent);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notifi_layout);
            noti_builder.setContent(remoteViews);
//            addControls();
//            addEvents();
        }

        private void addControls() {
            view_parent=inflater.from(context).inflate(R.layout.notifi_layout, null, false);
            btn_next_noti= (ImageButton) view_parent.findViewById(R.id.btn_next_noti);
            btn_pause_noti= (ImageButton) view_parent.findViewById(R.id.btn_pause_noti);
            btn_play_noti= (ImageButton) view_parent.findViewById(R.id.btn_play_noti);
            btn_prev= (ImageButton) view_parent.findViewById(R.id.btn_prev_noti);
        }


        private void addEvents() {
            btn_pause_noti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    xuly_music.get_instance().pause();
                }
            });
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void update_notifi (String ten_baihat, String duration) {
            txt_ten_baihat_noti.setText(ten_baihat);
            txt_duration_noti.setText(duration);
            noti_manager.cancel(ID_NOTIFI);
            noti_manager.notify(ID_NOTIFI, noti_builder.build());
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void noti () {
            noti_manager.notify(ID_NOTIFI, noti_builder.build());
        }

    }
