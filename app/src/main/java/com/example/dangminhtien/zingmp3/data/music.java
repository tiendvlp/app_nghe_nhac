package com.example.dangminhtien.zingmp3.data;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class music extends RealmObject {
    private String song_name;
    private String song_name_path;
    private boolean favorite_music;
    private String singer_name;
    @PrimaryKey
    private String id_music;
    private String image_song;
    @LinkingObjects("musics")
    private final RealmResults<playlist> playlists=null;

    public music() {

    }

    public String getId_music() {
        return id_music;
    }

    public String getSong_name_path() {
        return song_name_path;
    }

    public void setSong_name_path(String song_name_path) {
        this.song_name_path = song_name_path;
    }

    public void setId_music(String id_music) {
        this.id_music = id_music;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public boolean isFavorite_music() {
        return favorite_music;
    }

    public void setFavorite_music(boolean favorite_music) {
        this.favorite_music = favorite_music;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public String getImage_song() {
        return image_song;
    }

    public void setImage_song(String image_song) {
        this.image_song = image_song;
    }
}
