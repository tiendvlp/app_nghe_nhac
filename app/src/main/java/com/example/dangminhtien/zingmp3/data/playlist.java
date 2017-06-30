package com.example.dangminhtien.zingmp3.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by dangminhtien on 6/29/17.
 */

public class playlist extends RealmObject {
    private RealmList<music> musics;
    private String name_playlist;
    private String id_playlist;

    public playlist() {

    }

    public RealmList<music> getList_music() {
        return musics;
    }

    public void setList_music(RealmList<music> list_music) {
        this.musics = list_music;
    }

    public String getName_playlist() {
        return name_playlist;
    }

    public void setName_playlist(String name_playlist) {
        this.name_playlist = name_playlist;
    }

    public String getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(String id_playlist) {
        this.id_playlist = id_playlist;
    }
}
