package com.example.dangminhtien.zingmp3.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by tiend on 7/1/2017.
 */

public class service_music extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Zingmp3/"+"song/"+"ghen.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

   public class binder extends Binder {
        public service_music get_service () {
            return service_music.this;
        }
    }

}
