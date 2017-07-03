package com.example.dangminhtien.zingmp3.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;

import java.io.IOException;

public class xuly_music {
    private static MediaPlayer mediaPlayer;
    private static final xuly_music xuly_music=new xuly_music();
    private static final int IDLE=0;
    private static final int PLAYING=1;
    private static final int STOPPED=2;
    private static final int PAUSE=3;
    private static int STATE=IDLE;

    public static boolean is_playing=false;

    private xuly_music () {
        mediaPlayer=new MediaPlayer();
    }

    public static xuly_music get_instance () {
            return xuly_music;
    }


    public void set_data_source(String song_name) throws IOException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Zingmp3/song/"+song_name+".mp3";
        mediaPlayer.setDataSource(path);
        mediaPlayer.prepare();
    }

    public void stop_music() {
        if (STATE==PLAYING || STATE==PAUSE) {
            mediaPlayer.stop();
            is_playing=false;
        }
    }

    public void fast_forward (int milisec) {
        if (STATE==PLAYING && STATE == PAUSE) {
        mediaPlayer.seekTo(mediaPlayer.getDuration()+milisec);
        }
    }

    public void backward (int milesec) {
        mediaPlayer.seekTo(mediaPlayer.getDuration()-milesec);
    }

    public void pause () {
        if (STATE == PLAYING) {
            mediaPlayer.pause();
            is_playing=false;
            STATE=PAUSE;
        }
    }

    public void play() {
        if(STATE == IDLE || STATE == PAUSE || STATE == STOPPED) {
            mediaPlayer.start();
            is_playing=true;
            STATE=PLAYING;
        }}

    public void seek_to (int mili) {
        mediaPlayer.seekTo(mili);
    }



    public int get_duration () {
        return mediaPlayer.getDuration();
    }
    public int get_current_position () {
        return mediaPlayer.getCurrentPosition();
    }

}
