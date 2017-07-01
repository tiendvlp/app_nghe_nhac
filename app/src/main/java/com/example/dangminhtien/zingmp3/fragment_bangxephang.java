package com.example.dangminhtien.zingmp3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dangminhtien.zingmp3.adapter.adapter_bangxephang2;
import com.example.dangminhtien.zingmp3.data.music;

import java.util.ArrayList;

public class fragment_bangxephang extends Fragment {
    private RecyclerView rcv_bxh;
    private ArrayList<music> src_music;
    private OnFragmentInteractionListener mListener;

    public fragment_bangxephang() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static fragment_bangxephang newInstance() {
        fragment_bangxephang fragment = new fragment_bangxephang();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bangxephang, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        addEvents();
    }

    private void addControls(View view) {
        rcv_bxh= (RecyclerView) view.findViewById(R.id.rcv_bxh);
        src_music=new ArrayList<music>();
        music music1 = new music();
        music1.setFavorite_music(true);
        music1.setId_music("abcxyz");
        music1.setImage_song("abcjas");
        music1.setSong_name("1234");
        music1.setSinger_name("Chi Dân");
        music music2 = new music();
        music2.setFavorite_music(false);
        music2.setId_music("abcxyz");
        music2.setImage_song("abcjas");
        music2.setSinger_name("Noo Phước Thịnh");
        music2.setSong_name("Mình Anh");
        music music3 = new music();
        music3.setFavorite_music(true);
        music3.setId_music("abcxyz");
        music3.setImage_song("abcjas");
        music3.setSinger_name("Dlab");
        music3.setSong_name("Từ ngày em đến");
        src_music.add(music1);
        src_music.add(music2);
        src_music.add(music3);
        adapter_bangxephang2 adapter_bangxephang=new adapter_bangxephang2(src_music, getContext());
        rcv_bxh.setAdapter(adapter_bangxephang);
        rcv_bxh.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void addEvents() {
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
