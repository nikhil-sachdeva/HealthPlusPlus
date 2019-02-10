package com.example.nikhil.vihaan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Objects;

import mehdi.sakout.fancybuttons.FancyButton;

public class DoctorForm extends AppCompatActivity {
    EditText messCharge, appointCharge, address, qualification, specialities;
    FancyButton proceed;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_form);
        messCharge=findViewById(R.id.mess_charge);
        appointCharge=findViewById(R.id.app_charge);
        address=findViewById(R.id.address);
        qualification=findViewById(R.id.qual);
        proceed=findViewById(R.id.proceed_btn);
        specialities = findViewById(R.id.speciality);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorDetails details = new DoctorDetails(user.getDisplayName(), FirebaseAuth.getInstance().getUid(),
                        qualification.getText().toString(),
                        address.getText().toString(), specialities.getText().toString(),
                        Integer.parseInt(messCharge.getText().toString()), Integer.parseInt(appointCharge.getText().toString()),
                        90, 92);


                FirebaseDatabase.getInstance().getReference()
                        .child("userbase")
                        .child("doctors")
                        .child("details")
                        .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                        .setValue(details);
                Toast.makeText(DoctorForm.this, "Details Updated Successfully", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("doctorDetails",true);
                editor.apply();

                finish();
                startActivity(new Intent(DoctorForm.this, DoctorActivity.class));

            }
        });
    }
}
