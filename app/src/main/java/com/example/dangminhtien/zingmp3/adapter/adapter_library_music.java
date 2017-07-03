package com.example.dangminhtien.zingmp3.adapter;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.telecom.ConnectionService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dangminhtien.zingmp3.R;
import com.example.dangminhtien.zingmp3.data.music;

import java.util.ArrayList;

public class adapter_library_music extends RecyclerView.Adapter<adapter_library_music.viewholder> implements View.OnClickListener {
    private ArrayList<music> src_music;
    private Context context;
    private RecyclerView rcv_lbr;
    private on_child_click_listener on_click_listener;

    public adapter_library_music(Context context, ArrayList<music> src_music, RecyclerView rcv_lbr) {
        this.src_music = src_music;
        this.context = context;
        this.rcv_lbr = rcv_lbr;
    }

    @Override
    public adapter_library_music.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_library_music, parent, false);
        view.setOnClickListener(this);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(adapter_library_music.viewholder holder, int position) {
        holder.txt_ten_casi_lbr.setText(src_music.get(position).getSinger_name());
        holder.txt_ten_baihat_lbr.setText(src_music.get(position).getSong_name());
    }

    @Override
    public int getItemCount() {
        return src_music.size();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        int position = rcv_lbr.getChildLayoutPosition(v);
        on_click_listener.on_child_click(src_music.get(position));
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView txt_ten_baihat_lbr, txt_ten_casi_lbr;
        ImageView img_baihat_lbr;

        public viewholder(View itemView) {
            super(itemView);
            txt_ten_baihat_lbr = (TextView) itemView.findViewById(R.id.txt_ten_baihat_lbr);
            txt_ten_casi_lbr = (TextView) itemView.findViewById(R.id.txt_ten_casi_lbr);
            img_baihat_lbr = (ImageView) itemView.findViewById(R.id.img_baihat_lbr);
        }
    }

    public void set_on_click_listener (on_child_click_listener on_click_listener) {
        this.on_click_listener=on_click_listener;
    }

    public interface on_child_click_listener {
        void on_child_click(music music);
    }
}