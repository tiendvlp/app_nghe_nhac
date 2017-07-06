package com.example.dangminhtien.zingmp3.model;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;

import com.example.dangminhtien.zingmp3.data.music;
import com.example.dangminhtien.zingmp3.data.read_from_realm;

import java.io.IOException;
import java.util.ArrayList;

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
    private static int position;

    public static final int SRC_FROM_lIBRARY=12920301;
    public static final int SRC_FROM_FAVORITE=122309912;
    private static int SRC_FROM;
    private on_play_pause_listener on_play_pause_listener;
    // dùng để truyền thằng music này cho những thằng bắt sự kiện play
    private static music music;


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

    public void pause (music music) {
        if (STATE == PLAYING) {
            mediaPlayer.pause();
            STATE=PAUSE;
            on_play_pause_listener.on_pause(music);
        }
    }

    public void play(music music) {
        if(STATE == IDLE || STATE == PAUSE || STATE == STOPPED) {
            mediaPlayer.start();
            STATE=PLAYING;
            on_play_pause_listener.on_play(music);
        }}

    public void set_src_position (int SRC_FROM, int position) {
        com.example.dangminhtien.zingmp3.model.xuly_music.SRC_FROM=SRC_FROM;
        com.example.dangminhtien.zingmp3.model.xuly_music.position=position;
    }

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
            if (second<10) {
                second_string="0" +second;
            }
        return minutes_string+":"+second_string;
    }

    public void reset() {
        // reset media (reset cả resource, phải set lại resource sau khi reset)
        mediaPlayer.reset();
    }
    // sự kiện khi play nhạc
    public void set_on_play_listener (on_play_pause_listener on_play_listener) {
        this.on_play_pause_listener=on_play_listener;
    }

    public void next_music () {

    }

    public ArrayList<music> get_source (Context context) {
        read_from_realm read_from_realm = new read_from_realm(context);
        switch (SRC_FROM) {
            case SRC_FROM_lIBRARY:
                return read_from_realm.get_all_music();
            case SRC_FROM_FAVORITE:
                return read_from_realm.get_all_favorite_music();
            default:
                return null;
        }
    }

    public static com.example.dangminhtien.zingmp3.data.music getMusic() {
        return music;
    }

    public static void setMusic(com.example.dangminhtien.zingmp3.data.music music) {
        com.example.dangminhtien.zingmp3.model.xuly_music.music = music;
    }

    public interface on_play_pause_listener {
        void on_play(music music);
        void on_pause(music music);
    }
}
