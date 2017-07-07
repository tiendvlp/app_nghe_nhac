package com.example.dangminhtien.zingmp3.broadcast_reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.dangminhtien.zingmp3.model.xuly_music;

/**
 * Created by dangminhtien on 7/7/17.
 */

public class broadcast_btn_previous extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        xuly_music.get_instance().prev_music(context);
    }
}
