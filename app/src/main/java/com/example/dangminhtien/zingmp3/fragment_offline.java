package com.example.dangminhtien.zingmp3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dangminhtien.zingmp3.fragment.fragment_music_library;

public class fragment_offline extends Fragment {
    private LinearLayout ln_library;
    private LinearLayout ln_favorite;
    private LinearLayout ln_playlist;
    private OnFragmentInteractionListener mListener;

    public fragment_offline() {
    }

    public static fragment_offline newInstance(String param1, String param2) {
        fragment_offline fragment = new fragment_offline();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offline, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        addEvents();
    }

    private void addControls(View view) {
        ln_library= (LinearLayout) view.findViewById(R.id.ln_library);
        ln_favorite= (LinearLayout) view.findViewById(R.id.ln_favorite);
        ln_playlist= (LinearLayout) view.findViewById(R.id.ln_playlist);
        ln_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.replace_fragment((AppCompatActivity) getContext(), new fragment_music_library());
            }
        });

        ln_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });

        ln_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addEvents() {
    }

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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
