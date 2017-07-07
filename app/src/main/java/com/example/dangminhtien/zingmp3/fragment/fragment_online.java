package com.example.dangminhtien.zingmp3.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dangminhtien.zingmp3.R;
import com.example.dangminhtien.zingmp3.adapter.adapter_vpg_online;


public class fragment_online extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewPager vpg_online;
    private TabLayout tbl_online;

    private OnFragmentInteractionListener mListener;

    public fragment_online() {

    }

    public static fragment_online newInstance(String param1, String param2) {
        fragment_online fragment = new fragment_online();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_online, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        addEvents();
    }

    private void addControls(View view) {
        tbl_online= (TabLayout) view.findViewById(R.id.tbl_online);
        vpg_online= (ViewPager) view.findViewById(R.id.vpg_online);
        adapter_vpg_online adapter_vpg_online = new adapter_vpg_online(getChildFragmentManager(), getContext());
        vpg_online.setAdapter(adapter_vpg_online);
        tbl_online.setupWithViewPager(vpg_online);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
