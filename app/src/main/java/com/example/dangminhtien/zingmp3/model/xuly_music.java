package com.example.dangminhtien.zingmp3.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.widget.Toast;

import java.io.IOException;

public class xuly_music {
    private static MediaPlayer mediaPlayer;
    private static final xuly_music xuly_music=new xuly_music();
    // Tự tạo vòng đời
    public static final int IDLE=0;
    public static final int PLAYING=1;
    public static final int STOPPED=2;
    public static final int PAUSE=3;
    // ban đầu sẽ là idle
    public static int STATE=IDLE;
    private on_play_listener on_play_listener;

    private xuly_music () {
        mediaPlayer=new MediaPlayer();
    }

    public static xuly_music get_instance () {
            return xuly_music;
    }


    public void set_data_source(String song_name) throws IOException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Zingmp3/song/"+song_name+".mp3";
        // cách set resource siêu khủng
        stop_music();
        mediaPlayer.reset();
        mediaPlayer.setDataSource(path);
        // phải gọi lệnh prepare sau khi set resource
        mediaPlayer.prepare();
    }
    public void stop_music() {
        if (STATE==PLAYING || STATE==PAUSE) {
            mediaPlayer.stop();
            STATE=STOPPED;
        }
    }

    public void pause () {
        if (STATE == PLAYING) {
            mediaPlayer.pause();
            STATE=PAUSE;
        }
    }

    public void play() {
        if(STATE == IDLE || STATE == PAUSE || STATE == STOPPED) {
            mediaPlayer.start();
            STATE=PLAYING;
            on_play_listener.on_play();
        }}

    public void seek_to (int mili) {
        mediaPlayer.seekTo(mili);
    }

    public int get_duration () {
        // Lấy ra độ dài của bài hát
        return mediaPlayer.getDuration();
    }

    public int get_current_position () {
        // Lấy ra vị trí hiện tại của bài hát
        return mediaPlayer.getCurrentPosition();
    }

    public String duration_toString () {
        int duration = get_duration();
        int minutes = duration/60000;
        int second = (duration%60000)/1000;
        String minutes_string=minutes+"";
        String second_string=second+"";
        String second_string1=second+"";
            if (second+"".length()==1) {
                second_string1="0" +second_string;
            }
        return minutes_string+":"+second_string1;
    }

    public void reset() {
        // reset media (reset cả resource, phải set lại resource sau khi reset)
        mediaPlayer.reset();
    }
    // sự kiện khi play nhạc
    public void set_on_play_listener (on_play_listener on_play_listener) {
        this.on_play_listener=on_play_listener;
    }

    public interface on_play_listener {
        void on_play();
    }

}
