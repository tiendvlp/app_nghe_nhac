package com.example.dangminhtien.zingmp3.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class write_data_to_external_storage {
    private Environment environment;
    private Context context;
    private Activity activity;
    private final String app_folder="Zingmp3";
    private final String app_folder_image_song="image_song";
    private final String app_folder_song="song";
    private final String path_external_storage=Environment.getExternalStorageDirectory().getAbsolutePath();
    private String file_name;
    public write_data_to_external_storage(Context context, Activity activity) {
        this.context = context;
        this.activity=activity;
        File file = new File(path_external_storage+"/"+app_folder);
        if (!file.exists()) {
            file.mkdir();
        }
        checkAndRequestPermissions();
    }

    public void write_music (String file_name) throws IOException {
        this.file_name=file_name;
//        checkAndRequestPermissions();
        File file = new File(path_external_storage+"/"+app_folder+"/"+app_folder_song);
            if (!file.exists()){
                file.mkdir();
            }
        InputStream inputStream=context.getAssets().open(file_name);
        FileOutputStream fileOutputStream=new FileOutputStream(file.getAbsolutePath()+"/"+file_name);
        byte[] b1= new byte[1024];
        int i = -1;
        while ((i = inputStream.read(b1))!= -1) {
            fileOutputStream.write(b1);
        }
        fileOutputStream.close();
        inputStream.close();
        Toast.makeText(context, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
    }

    public void write_image (Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            FileOutputStream fileOutputStream = new FileOutputStream(path_external_storage+"/"+app_folder_song+"/"+file_name+"_bitmap.png");
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write_image (String file_name) throws IOException {
        this.file_name=file_name;
        File file = new File(path_external_storage+"/"+app_folder+"/"+app_folder_image_song+"_bitmap.png");
        if (!file.exists()){
            file.mkdir();
        }
        InputStream inputStream=context.getAssets().open(file_name);
        FileOutputStream fileOutputStream=new FileOutputStream(path_external_storage+"/"+app_folder_song+"/"+file_name+"_bitmap.png");
        byte[] b1= new byte[1024];
        int i = -1;
        while ((i = inputStream.read(b1))!= -1) {
            fileOutputStream.write(b1);
        }
        fileOutputStream.close();
        inputStream.close();
    }

    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

}
