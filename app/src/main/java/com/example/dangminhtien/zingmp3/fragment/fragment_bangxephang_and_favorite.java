package com.example.dangminhtien.zingmp3.fragment;

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

import com.example.dangminhtien.zingmp3.R;
import com.example.dangminhtien.zingmp3.adapter.adapter_bangxephang2;
import com.example.dangminhtien.zingmp3.data.music;
import com.example.dangminhtien.zingmp3.data.read_from_realm;

import java.util.ArrayList;

public class fragment_bangxephang_and_favorite extends Fragment {
    private RecyclerView rcv_bxh;
    private ArrayList<music> src_music;
    private OnFragmentInteractionListener mListener;

    public fragment_bangxephang_and_favorite() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static fragment_bangxephang_and_favorite newInstance() {
        fragment_bangxephang_and_favorite fragment = new fragment_bangxephang_and_favorite();
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
        return inflater.inflate(R.layout.fragment_bangxephang_and_favorite, container, false);
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
        read_from_realm music1 = new read_from_realm(getContext());
        src_music.addAll(music1.get_all_music());
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
