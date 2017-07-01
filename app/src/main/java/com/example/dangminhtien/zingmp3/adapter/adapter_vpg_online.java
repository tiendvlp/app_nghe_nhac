package com.example.dangminhtien.zingmp3.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.dangminhtien.zingmp3.fragment_bangxephang_online;
import com.example.dangminhtien.zingmp3.fragment_nhacmoi;
import com.example.dangminhtien.zingmp3.fragment_nhacnoibat;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tiend on 6/30/2017.
 */

public class adapter_vpg_online extends FragmentPagerAdapter{
    Context context;
    ArrayList<String> title = new ArrayList<String>();
    public adapter_vpg_online(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
        title.add("Nổi bật");
        title.add("Bảng xếp hạng");
        title.add("Mới");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return fragment_nhacnoibat.newInstance(null,null);
            case 1:
                return fragment_bangxephang_online.newInstance(null,null);
            case 2:
                return fragment_nhacmoi.newInstance(null,null);
        }
        return fragment_nhacnoibat.newInstance(null,null);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
