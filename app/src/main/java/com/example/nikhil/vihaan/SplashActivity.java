package com.example.nikhil.vihaan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private String permission_String[] = new String[]{
            "android.permission.CAMERA",
            "android.permission.FLASHLIGHT",
            "android.permission.WAKE_LOCK",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.READ_SMS",
            "android.permission.RECEIVE_SMS"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if(!(checkCallingOrSelfPermission(permission_String[0])== PackageManager.PERMISSION_GRANTED
                && checkCallingOrSelfPermission(permission_String[1])==PackageManager.PERMISSION_GRANTED
                && checkCallingOrSelfPermission(permission_String[2])==PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, permission_String, 131);
        }
        else
            DisplayActivity();
    }

    void DisplayActivity() {
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        };

        handler.postDelayed(r,1000);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case 131:
                if (grantResults.length!=0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                ) {

                    DisplayActivity();

                } else {
                    Toast.makeText(this, "Please Grant All the Permissions To Continue", Toast.LENGTH_SHORT).show();
                    finish();
                }

                return;
        }

    }


}
