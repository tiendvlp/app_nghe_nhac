package com.example.dangminhtien.zingmp3.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.File;
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
    private final String app_folder_name="Zingmp3";
    private final String path_external_storage;

    public write_data_to_external_storage(Context context, Activity activity) {
        this.environment = environment;
        this.context = context;
        path_external_storage=Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+app_folder_name;
        this.activity=activity;
    }

    public void write_music (String file_name) throws IOException {
        checkAndRequestPermissions();
        File file = new File(path_external_storage);
            if (!file.exists()){
                file.mkdir();
            }
        InputStream inputStream=context.getAssets().open(file_name);
        FileOutputStream fileOutputStream=new FileOutputStream(path_external_storage+"/"+file_name);
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
