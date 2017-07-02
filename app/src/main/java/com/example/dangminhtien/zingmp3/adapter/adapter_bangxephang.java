package com.example.dangminhtien.zingmp3.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dangminhtien.zingmp3.MainActivity;
import com.example.dangminhtien.zingmp3.R;
import com.example.dangminhtien.zingmp3.data.bangxephang;
import java.util.ArrayList;

public class adapter_bangxephang extends RecyclerView.Adapter<adapter_bangxephang.viewholder> implements View.OnClickListener {
    private Context context;
    private ArrayList<bangxephang> source_rcv;
    private RecyclerView recyclerView;
    public adapter_bangxephang(Context context, ArrayList<bangxephang> source_rcv, RecyclerView recyclerView) {
        this.context=context;
        this.source_rcv=source_rcv;
        this.recyclerView=recyclerView;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_lv_bang_xep_hang, parent, false);
        view.setOnClickListener(this);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        holder.txt_row_bxh_ten_baihat1.setText(source_rcv.get(position).getTen_baihat1());
        holder.txt_row_bxh_ten_baihat2.setText(source_rcv.get(position).getTen_baihat2());
        holder.txt_row_bxh_ten_baihat3.setText(source_rcv.get(position).getTen_baihat3());
    }

    @Override
    public int getItemCount() {
        return source_rcv.size();
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildLayoutPosition(v);
        MainActivity mainActivity=new MainActivity();
        mainActivity.replace_fragment_bangxephang((AppCompatActivity) context);
    }

    class viewholder extends RecyclerView.ViewHolder {
        private TextView txt_row_bxh_ten_baihat1,txt_row_bxh_ten_baihat2,txt_row_bxh_ten_baihat3;
        private ImageView img_row_bang_xep_hang;
        public viewholder(View itemView) {
            super(itemView);
            txt_row_bxh_ten_baihat1= (TextView) itemView.findViewById(R.id.txt_row_bxh_ten_baihat1);
            txt_row_bxh_ten_baihat2= (TextView) itemView.findViewById(R.id.txt_row_bxh_ten_baihat2);
            txt_row_bxh_ten_baihat3= (TextView) itemView.findViewById(R.id.txt_row_bxh_ten_baihat3);
            img_row_bang_xep_hang= (ImageView) itemView.findViewById(R.id.img_row_bang_xep_hang);
        }
    }
}
