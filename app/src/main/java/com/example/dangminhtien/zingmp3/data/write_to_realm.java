package com.example.dangminhtien.zingmp3.data;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by tiend on 7/2/2017.
 */

public class write_to_realm {
    private Context context;
    private Realm realm;

    public write_to_realm(Context context) {
        this.context = context;
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("music").build();
        realm = Realm.getInstance(configuration);
    }

    public void write (final music music) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                com.example.dangminhtien.zingmp3.data.music music1 = realm.copyToRealm(music);
            }
        });
    }
}
