package com.example.dangminhtien.zingmp3;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.dangminhtien.zingmp3.data.helper_tools;
import com.example.dangminhtien.zingmp3.data.music;
import com.example.dangminhtien.zingmp3.data.write_data_to_external_storage;
import com.example.dangminhtien.zingmp3.data.write_to_realm;
import com.example.dangminhtien.zingmp3.fragment.fragment_music_library;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView btm_nav_menu;
    private FrameLayout frame_main;
    private Fragment fragment_offline, fragment_search, fragment_online;
    private helper_tools helper_tools;
    private BottomSheetBehavior behavior;
    private View btms;
    private Service service_music;
    private ServiceConnection connection_service;
    private boolean is_connected_to_service=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        addControls();
        addEvents();
        xuly_bottom_sheet();
        music music1 = new music();
        music1.setFavorite_music(true);
        music1.setId_music("abcxyz");
        music1.setImage_song("abcjas");
        music1.setSong_name("1234");
        music1.setSinger_name("Chi Dân");
        music1.setId_music("1213jasndj12");
        music music2 = new music();
        music2.setFavorite_music(false);
        music2.setId_music("abcxyz");
        music2.setImage_song("abcjas");
        music2.setSinger_name("Noo Phước Thịnh");
        music2.setSong_name("Mình Anh");
        music2.setId_music("1211113jasndj12");
        music music3 = new music();
        music3.setFavorite_music(true);
        music3.setId_music("abcxyz");
        music3.setImage_song("abcjas");
        music3.setSinger_name("Dlab");
        music3.setSong_name("Từ ngày em đến");
        music3.setId_music("1213jasndj1332");
        write_to_realm write_to_realm = new write_to_realm(getApplicationContext());
        write_data_to_external_storage write_data_to_external_storage = new write_data_to_external_storage(this, this);
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"Zingmp3/"+"song/"+"ghen.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }
    }

    private void addControls() {
        btm_nav_menu= (BottomNavigationView) findViewById(R.id.btm_nav_menu);
        frame_main= (FrameLayout) findViewById(R.id.frame_main);
        fragment_offline=new fragment_offline();
        fragment_search=new fragment_search();
        fragment_online=new fragment_online();
        helper_tools=new helper_tools(this,this);
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void xuly_hienthi_fragment_online() {
        com.example.dangminhtien.zingmp3.fragment_online fragment_online1= com.example.dangminhtien.zingmp3.fragment_online.newInstance(null, null );
        FragmentManager fragment_manager=getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        fragment_transaction.replace(R.id.frame_main, fragment_online1);
        fragment_transaction.commit();
    }

    private void xuly_hienthi_fragment_offline() {
        FragmentManager fragment_manager=getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        fragment_transaction.replace(R.id.frame_main, fragment_offline);
        fragment_transaction.commit();
    }

    private void xuly_hienthi_fragment_search() {
        FragmentManager fragment_manager=getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        fragment_transaction.replace(R.id.frame_main, fragment_search);
        fragment_transaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void replace_fragment_bangxephang(AppCompatActivity activity) {
        FragmentManager fragment_manager=activity.getSupportFragmentManager();
        fragment_bangxephang fragment_bangxephang= new fragment_bangxephang();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        // cũng giống với activity thôi
        fragment_bangxephang.setEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
        fragment_bangxephang.setReenterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
        fragment_bangxephang.setSharedElementEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
//      Dùng phương thức này để set transaction cho từng element, giống với activity  fragment_transaction.addSharedElement();
//      Cần lưu ý là fragment không hỗ trợ việc sử dụng nhiều view (pair.create)
        fragment_transaction.replace(R.id.frame_main, fragment_bangxephang);
        // sử dung thằng này để commit
        fragment_transaction.commitAllowingStateLoss();

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void replace_fragment_library(AppCompatActivity activity) {
        FragmentManager fragment_manager=activity.getSupportFragmentManager();
        fragment_music_library fragment_music_library= new fragment_music_library();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        // cũng giống với activity thôi
        fragment_music_library.setEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
        fragment_music_library.setReenterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
        fragment_music_library.setSharedElementEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
//      Dùng phương thức này để set transaction cho từng element, giống với activity  fragment_transaction.addSharedElement();
//      Cần lưu ý là fragment không hỗ trợ việc sử dụng nhiều view (pair.create)
        fragment_transaction.replace(R.id.frame_main, fragment_music_library);
        // sử dung thằng này để commit
        fragment_transaction.commitAllowingStateLoss();
    }

    private void xuly_bottom_sheet () {
        View bottomsheet=findViewById(R.id.btms_play_music);
        final DisplayMetrics displayMetrics= new DisplayMetrics();
        final BottomSheetBehavior behavior=BottomSheetBehavior.from(bottomsheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState==BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setPeekHeight(helper_tools.convert_dp_to_px(32));
                } else if (newState==BottomSheetBehavior.STATE_EXPANDED){
                    behavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setPeekHeight(helper_tools.convert_dp_to_px(32));
    }

    private void start_service_music () {
           connection_service=new ServiceConnection() {
               @Override
               public void onServiceConnected(ComponentName name, IBinder service) {
                   com.example.dangminhtien.zingmp3.service.service_music.binder binder = (com.example.dangminhtien.zingmp3.service.service_music.binder) service;
                   service_music=binder.get_service();
                   is_connected_to_service=true;
               }

               @Override
               public void onServiceDisconnected(ComponentName name) {
                    is_connected_to_service=false;
               }
           };
        Intent intent = new Intent(getApplicationContext(), com.example.dangminhtien.zingmp3.service.service_music.class);
            if (is_connected_to_service) {
        bindService(intent, connection_service, BIND_AUTO_CREATE);
            }
    }

    private void stop_service_music () {
        if (!is_connected_to_service) {
            unbindService(connection_service);
        }
    }

}
