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

import com.example.dangminhtien.zingmp3.adapter.adapter_bangxephang;
import com.example.dangminhtien.zingmp3.data.bangxephang;

import java.util.ArrayList;

public class fragment_bangxephang_online extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RecyclerView rcv_bangxephang;
    private ArrayList<bangxephang> source_rcv;
    private adapter_bangxephang adapter_bangxephang;
    public fragment_bangxephang_online() {

    }

    public static fragment_bangxephang_online newInstance(String param1, String param2) {
        fragment_bangxephang_online fragment = new fragment_bangxephang_online();
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
        return inflater.inflate(R.layout.fragment_bangxephang_online, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        addEvents();
        Toast.makeText(getContext(), "create view", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "resume", Toast.LENGTH_SHORT).show();
    }


    private void addControls(View view) {
        source_rcv=new ArrayList<bangxephang>();
        bangxephang bangxephang1 = new bangxephang();
        bangxephang1.setTen_baihat1("1234");
        bangxephang1.setTen_baihat2("Từ ngày em đến");
        bangxephang1.setTen_baihat3("Mình anh");
        source_rcv.add(bangxephang1);
        rcv_bangxephang= (RecyclerView) view.findViewById(R.id.rcv_bxh_online);
        adapter_bangxephang=new adapter_bangxephang(getContext(), source_rcv, rcv_bangxephang);
        rcv_bangxephang.setAdapter(adapter_bangxephang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcv_bangxephang.setLayoutManager(linearLayoutManager);
    }

    private void addEvents() {

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
        void onFragmentInteraction();
    }
}
