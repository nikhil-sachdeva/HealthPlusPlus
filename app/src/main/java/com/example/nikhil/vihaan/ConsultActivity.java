package com.example.nikhil.vihaan;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.paytm.pgsdk.PaytmClientCertificate;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;

public class ConsultActivity extends AppCompatActivity {

    RecyclerView doctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        if (ContextCompat.checkSelfPermission(ConsultActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ConsultActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }



        doctorList = findViewById(R.id.doctor_list);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("userbase")
                .child("doctors")
                .child("details")
                .limitToFirst(50);

        DoctorListAdapter doctorListAdapter = new DoctorListAdapter(R.layout.doctor_info_layout, query, getApplicationContext());
        doctorList.setAdapter(doctorListAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        doctorList.setLayoutManager(layoutManager);


    }
}
