package com.example.dangminhtien.zingmp3;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.HandlerThread;
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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.dangminhtien.zingmp3.data.helper_tools;
import com.example.dangminhtien.zingmp3.data.music;
import com.example.dangminhtien.zingmp3.data.write_data_to_external_storage;
import com.example.dangminhtien.zingmp3.data.write_to_realm;
import com.example.dangminhtien.zingmp3.model.xuly_music;
import com.example.dangminhtien.zingmp3.service.service_music;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements ServiceConnection, xuly_music.on_play_listener {
    private BottomNavigationView btm_nav_menu;
    private Fragment fragment_offline, fragment_search, fragment_online;
    private helper_tools helper_tools;
    private BottomSheetBehavior behavior;
    private View btms;
    private Service service_music;
    private ImageButton btn_pause, btn_backward, btn_forward, btn_play;
    public static final int CONNECTED=0;
    public static final int DISCONNECTED=1;
    private SeekBar sb_music;
    public static int STATE=DISCONNECTED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        addControls();
        addEvents();
        xuly_bottom_sheet();
        music music = new music();
        music.setId_music("12312312");
        music.setSinger_name("Noo");
        music.setSong_name("Mình Anh");
        music.setFavorite_music(true);
        music.setSong_name_path("minhanh");

        music music1 = new music();
        music1.setId_music("12312312123");
        music1.setSinger_name("Erik");
        music1.setSong_name("Ghen");
        music1.setFavorite_music(true);
        music1.setSong_name_path("ghen");

        write_to_realm write_to_realm = new write_to_realm(getApplicationContext());
//        write_to_realm.write(music);
//        write_to_realm.write(music1);
    }
    private void addControls() {
        btms=findViewById(R.id.btms_play_music);
        behavior = BottomSheetBehavior.from(btms);
        btm_nav_menu= (BottomNavigationView) findViewById(R.id.btm_nav_menu);
        fragment_offline=new fragment_offline();
        fragment_search=new fragment_search();
        fragment_online=new fragment_online();
        helper_tools=new helper_tools(this,this);
        btn_backward= (ImageButton) findViewById(R.id.btn_backward);
        btn_forward= (ImageButton) findViewById(R.id.btn_forward);
        btn_play= (ImageButton) findViewById(R.id.btn_play);
        btn_pause= (ImageButton) findViewById(R.id.btn_pause);
        sb_music= (SeekBar) findViewById(R.id.sb_music);
        xuly_music.get_instance().set_on_play_listener(this);
    }

    private void addEvents() {
        btm_nav_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_item_search:
                        xuly_hienthi_fragment(fragment_search);
                            break;
                        case R.id.nav_item_music_offline:
                        xuly_hienthi_fragment(fragment_offline);
                            break;
                        case R.id.nav_item_music_online:
                            xuly_hienthi_fragment(fragment_online);
                            break;
                        default:
                            break;
                    }
                return true;
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xuly_music.STATE==xuly_music.PLAYING) {
                btn_pause.setVisibility(View.GONE);
                btn_play.setVisibility(View.VISIBLE);
                xuly_music xuly_music = com.example.dangminhtien.zingmp3.model.xuly_music.get_instance();
                xuly_music.pause();
            }}
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(xuly_music.STATE==xuly_music.PLAYING)) {
                btn_pause.setVisibility(View.VISIBLE);
                btn_play.setVisibility(View.GONE);
                xuly_music xuly_music = com.example.dangminhtien.zingmp3.model.xuly_music.get_instance();
                xuly_music.play();

            }}
        });

        btn_forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        default:
                            break;
                    }
                return true;
            }
        });
        sb_music.setProgress(xuly_music.get_instance().get_duration());
        sb_music.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    xuly_music xuly_music = com.example.dangminhtien.zingmp3.model.xuly_music.get_instance();
                    xuly_music.seek_to(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void xuly_hienthi_fragment(Fragment fragment_replaced) {
        FragmentManager fragment_manager=getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        fragment_transaction.replace(R.id.frame_main, fragment_replaced);
        fragment_transaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void replace_fragment(AppCompatActivity activity, Fragment fragment_replaced) {
        FragmentManager fragment_manager=activity.getSupportFragmentManager();
        FragmentTransaction fragment_transaction=fragment_manager.beginTransaction();
        // cũng giống với activity thôi
        fragment_replaced.setEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
        fragment_replaced.setReenterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
        fragment_replaced.setSharedElementEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.transition_slide));
//      Dùng phương thức này để set transaction cho từng element, giống với activity  fragment_transaction.addSharedElement();
//      Cần lưu ý là fragment không hỗ trợ việc sử dụng nhiều view (pair.create)
        fragment_transaction.replace(R.id.frame_main, fragment_replaced);
        // sử dung thằng này để commit
        fragment_transaction.commitAllowingStateLoss();
    }

    private void xuly_bottom_sheet () {

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
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        com.example.dangminhtien.zingmp3.service.service_music.binder binder = (service_music.binder) service;
        service_music=binder.get_service();
        STATE=CONNECTED;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        STATE=DISCONNECTED;
    }

    @Override
    public void on_play() {
        sb_music.setMax(xuly_music.get_instance().get_duration());
        sb_music.setProgress(0);
        update_seekBar update_seekBar = new update_seekBar();
        update_seekBar.execute();
    }

    class update_seekBar extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            int progress = 0;
            while (xuly_music.STATE==xuly_music.PLAYING) {
                    try {
                        Thread.sleep(500);
                        publishProgress(xuly_music.get_instance().get_current_position());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            sb_music.setProgress(values[0]);
        }
    }
}
