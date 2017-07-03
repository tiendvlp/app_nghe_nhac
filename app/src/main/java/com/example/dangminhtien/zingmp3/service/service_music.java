package com.example.dangminhtien.zingmp3.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.dangminhtien.zingmp3.model.xuly_music;

import java.io.IOException;

public class service_music extends Service {
    public static boolean is_bind=false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        is_bind=true;
        xuly_music xuly_music= com.example.dangminhtien.zingmp3.model.xuly_music.get_instance();

        try {
            xuly_music.set_data_source(intent.getStringExtra("song_name"));
            xuly_music.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "run", Toast.LENGTH_SHORT).show();
        return new binder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        xuly_music.get_instance().stop_music();
        is_bind=false;
        return super.onUnbind(intent);

    }

   public class binder extends Binder {
        public service_music get_service () {
            return service_music.this;
        }
    }

}
