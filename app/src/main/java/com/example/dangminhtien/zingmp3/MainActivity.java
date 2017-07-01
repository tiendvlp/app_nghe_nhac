package com.example.dangminhtien.zingmp3;

import android.annotation.TargetApi;
import android.os.Build;
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

import com.example.dangminhtien.zingmp3.data.helper_tools;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView btm_nav_menu;
    private FrameLayout frame_main;
    private Fragment fragment_offline, fragment_search, fragment_online;
    private helper_tools helper_tools;
    private BottomSheetBehavior behavior;
    private View btms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        xuly_bottom_sheet();
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
    public void replace_fragment(AppCompatActivity activity) {
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

    private void xuly_phatnhac () {

    }
}
