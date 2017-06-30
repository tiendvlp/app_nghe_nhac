package com.example.dangminhtien.zingmp3;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dangminhtien.zingmp3.data.music;
import com.example.dangminhtien.zingmp3.data.playlist;
import com.example.dangminhtien.zingmp3.data.write_data_to_external_storage;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView btm_nav_menu;
    private FrameLayout frame_main;
    private Fragment fragment_offline, fragment_search, fragment_online;
    private FragmentManager fragment_manager;
    private FragmentTransaction fragment_transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        btm_nav_menu= (BottomNavigationView) findViewById(R.id.btm_nav_menu);
        frame_main= (FrameLayout) findViewById(R.id.frame_main);
        fragment_manager=getSupportFragmentManager();
        fragment_offline=new fragment_offline();
        fragment_search=new fragment_search();
        fragment_online=new fragment_online();
        check_csdl();
    }

    private void check_csdl () {
        Realm.init(getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("test_case1.realm").build();
        Realm realm = Realm.getInstance(configuration);
    }

    private void addEvents() {
        btm_nav_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_item_search:
                        xuly_hienthi_fragment_search();
                            break;
                        case R.id.nav_item_music_offline:
                        xuly_hienthi_fragment_offline();
                            break;
                        case R.id.nav_item_music_online:
                            xuly_hienthi_fragment_online();
                            break;
                        default:
                            break;
                    }
                return true;
            }
        });
    }

    private void xuly_hienthi_fragment_online() {
        FragmentManager fragment_manager=getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        fragment_transaction.add(R.id.frame_main, fragment_online);
        fragment_transaction.replace(R.id.frame_main, fragment_online);
        fragment_transaction.commit();
    }

    private void xuly_hienthi_fragment_offline() {
        FragmentManager fragment_manager=getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        fragment_transaction.add(R.id.frame_main, fragment_offline);
        fragment_transaction.replace(R.id.frame_main, fragment_offline);
        fragment_transaction.commit();
    }

    private void xuly_hienthi_fragment_search() {
        FragmentManager fragment_manager=getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        fragment_transaction.add(R.id.frame_main, fragment_search);
        fragment_transaction.replace(R.id.frame_main, fragment_search);
        fragment_transaction.commit();
    }
}
