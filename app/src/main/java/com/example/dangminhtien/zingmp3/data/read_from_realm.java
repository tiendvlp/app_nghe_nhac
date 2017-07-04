package com.example.dangminhtien.zingmp3.data;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by tiend on 7/2/2017.
 */

public class read_from_realm {
    private Context context;
    private Realm realm;

    public read_from_realm(Context context) {
        this.context = context;
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name("music").build();
        realm = Realm.getInstance(configuration);
    }

    public ArrayList<music> get_all_music () {
        RealmResults<music> musics = realm.where(music.class).findAll();
        ArrayList<music> result = new ArrayList<music>();
        result.addAll(musics);
        return result;
    }

    public music get_music (String id_music) {
        music music = realm.where(music.class).equalTo("id_music", id_music).findFirst();
        return music;
    }

    public music get_music (String singer_name, String song_name) {
        music music = realm.where(com.example.dangminhtien.zingmp3.data.music.class).equalTo("song_name", song_name).equalTo("singer_name", singer_name).findFirst();
        return music;
    }

    public ArrayList<music> get_all_favorite_music () {
        RealmResults<music> musics = realm.where(music.class).equalTo("favorite_song", true).findAll();
        ArrayList<music> result = new ArrayList<music>();
        result.addAll(musics);
        return result;
    }


}
