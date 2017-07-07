package com.example.dangminhtien.zingmp3.broadcast_reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.dangminhtien.zingmp3.model.create_notification;
import com.example.dangminhtien.zingmp3.model.xuly_music;

public class broadcast_btn_pause extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        create_notification create_notification = new create_notification(context);
        if (xuly_music.STATE==xuly_music.PLAYING) {
            xuly_music.get_instance().pause(null);
        } else {
            xuly_music.get_instance().play(null);
        }
            create_notification.create_noti(null,null);
            create_notification.update_notifi(xuly_music.STATE==xuly_music.PLAYING);
    }
}
