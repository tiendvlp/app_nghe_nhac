package com.example.dangminhtien.zingmp3.model;

import android.content.Context;
import android.media.MediaPlayer;

public class xuly_music {
    private MediaPlayer mediaPlayer;
    private Context context;
    private int song;
    private boolean is_play=false;

    private static final int IDLE=0;
    private static final int PLAYING=1;
    private static final int STOPPED=2;
    private static final int PAUSE=3;

    private static int STATE=IDLE;

    public xuly_music (Context context, int song) {
        this.context=context;
        this.song=song;
        if (mediaPlayer != null) {
            stop_music();
        }
        mediaPlayer=MediaPlayer.create(context,song);

    }

    private void stop_music() {
        if (STATE==PLAYING || STATE==PAUSE) {
            mediaPlayer.stop();
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
            STATE=PAUSE;
        }
    }

    public void play() {
        if(STATE == IDLE || STATE == PAUSE || STATE == STOPPED) {
            mediaPlayer.start();
            STATE=PLAYING;
        }}

    public int get_duration () {
        return mediaPlayer.getDuration();
    }
}
