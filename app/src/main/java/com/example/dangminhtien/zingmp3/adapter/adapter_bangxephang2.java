package com.example.dangminhtien.zingmp3.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dangminhtien.zingmp3.R;
import com.example.dangminhtien.zingmp3.data.music;

import java.util.ArrayList;

public class adapter_bangxephang2 extends RecyclerView.Adapter<adapter_bangxephang2.viewholder> {
    private ArrayList<music> src_music;
    private Context context;

    public adapter_bangxephang2(ArrayList<music> src_music, Context context) {
        this.src_music = src_music;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_lv_bangxephang, parent, false);
        return new viewholder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        holder.txt_ten_casi_bxh.setText(src_music.get(position).getSinger_name());
        holder.txt_ten_baihat_bxh.setText(src_music.get(position).getSong_name());
    }

    @Override
    public int getItemCount() {
        return src_music.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        private TextView txt_ten_baihat_bxh, txt_ten_casi_bxh;
        private ImageView img_baihat_bxh;
        private ImageButton btn_more_bxh;

        public viewholder(View itemView) {
            super(itemView);
            txt_ten_baihat_bxh= (TextView) itemView.findViewById(R.id.txt_ten_baihat_lbr);
            txt_ten_casi_bxh= (TextView) itemView.findViewById(R.id.txt_ten_casi_lbr);
            img_baihat_bxh= (ImageView) itemView.findViewById(R.id.img_baihat_lbr);
            btn_more_bxh= (ImageButton) itemView.findViewById(R.id.btn_more_bxh);
        }
    }
}
