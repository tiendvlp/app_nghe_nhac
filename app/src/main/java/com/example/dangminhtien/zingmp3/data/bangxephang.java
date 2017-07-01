package com.example.dangminhtien.zingmp3.data;

import android.graphics.Bitmap;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by tiend on 6/30/2017.
 */

public class bangxephang extends RealmObject {
    private String title;
    private String ten_baihat1;
    private String ten_baihat2;
    private String ten_baihat3;
    private String path_bitmap;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTen_baihat1() {
        return ten_baihat1;
    }

    public void setTen_baihat1(String ten_baihat1) {
        this.ten_baihat1 = ten_baihat1;
    }

    public String getTen_baihat2() {
        return ten_baihat2;
    }

    public void setTen_baihat2(String ten_baihat2) {
        this.ten_baihat2 = ten_baihat2;
    }

    public String getTen_baihat3() {
        return ten_baihat3;
    }

    public void setTen_baihat3(String ten_baihat3) {
        this.ten_baihat3 = ten_baihat3;
    }

    public String getPath_bitmap() {
        return path_bitmap;
    }

    public void setPath_bitmap(String path_bitmap) {
        this.path_bitmap = path_bitmap;
    }
}
