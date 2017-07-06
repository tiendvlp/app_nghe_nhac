package com.example.dangminhtien.zingmp3.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.dangminhtien.zingmp3.model.xuly_music;

import java.io.IOException;

public class service_music extends Service {
    public static boolean is_bind=false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try {
            xuly_music.get_instance().reset();
            xuly_music.get_instance().set_data_source(intent.getStringExtra("song_name"));
            xuly_music.get_instance().play(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new binder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        xuly_music.get_instance().stop_music();
        return super.onUnbind(intent);

    }

   public class binder extends Binder {
        public service_music get_service () {
            return service_music.this;
        }
    }

}
