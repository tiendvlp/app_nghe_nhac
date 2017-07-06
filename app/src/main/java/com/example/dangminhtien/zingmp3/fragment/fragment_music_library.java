package com.example.dangminhtien.zingmp3.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dangminhtien.zingmp3.MainActivity;
import com.example.dangminhtien.zingmp3.R;
import com.example.dangminhtien.zingmp3.adapter.adapter_library_music;
import com.example.dangminhtien.zingmp3.data.music;
import com.example.dangminhtien.zingmp3.data.read_from_realm;
import com.example.dangminhtien.zingmp3.model.xuly_music;
import com.example.dangminhtien.zingmp3.service.service_music;

import java.io.IOException;
import java.util.ArrayList;

public class fragment_music_library extends Fragment {

    private ArrayList<music> src_music;
    private RecyclerView rcv_lbr;
    private adapter_library_music adapter_library_music;
    private service_music service_music;
    private OnFragmentInteractionListener mListener;

    public fragment_music_library() {
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_music_library newInstance(Boolean is_library) {
        fragment_music_library fragment = new fragment_music_library();
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_library", is_library);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        addEvents();
    }

    private void addControls(View view) {
        rcv_lbr= (RecyclerView) view.findViewById(R.id.rcb_lbr);
        read_from_realm read_from_realm = new read_from_realm(getContext());
        src_music=new ArrayList<>();
            if (getArguments().getBoolean("is_library")) {
        src_music.addAll(read_from_realm.get_all_music());
            } else {
        src_music.addAll(read_from_realm.get_all_favorite_music());
            }
        adapter_library_music=new adapter_library_music(getContext(), src_music, rcv_lbr);
        rcv_lbr.setAdapter(adapter_library_music);
        rcv_lbr.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private static boolean bind=false;
    private void addEvents() {
        adapter_library_music.set_on_click_listener(new adapter_library_music.on_child_click_listener() {
            @Override
            public void on_child_click(music music, int position) {

                    if (MainActivity.STATE== MainActivity.CONNECTED) {
                        try {
                            xuly_music.get_instance().reset();
                            xuly_music.get_instance().set_data_source(music.getSong_name_path());
                            xuly_music.get_instance().play(music);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Intent intent = new Intent(getContext(), service_music.class);
                            intent.putExtra("song_name", music.getSong_name_path());
                bind=getContext().bindService(intent, new MainActivity(), Context.BIND_AUTO_CREATE);
        }           if (getArguments().getBoolean("is_library")) {
                xuly_music.SRC_FROM=xuly_music.SRC_FROM_lIBRARY;}
                else {
                    xuly_music.SRC_FROM=xuly_music.SRC_FROM_FAVORITE;
            }
            }});
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_library, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(music music);
    }
}
