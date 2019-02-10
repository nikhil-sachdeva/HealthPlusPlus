package com.example.nikhil.vihaan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class VitalSignsResults extends AppCompatActivity {

    private String user,Date;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    java.util.Date today = Calendar.getInstance().getTime();
    int VBP1,VBP2,VRR,VHR,VO2;

    ArrayList<CautiousVitalSigns> caution = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs_results);

        Date = df.format(today);
        TextView VSRR = this.findViewById(R.id.RRV);
        TextView VSBPS = this.findViewById(R.id.BP2V);
        TextView VSHR = this.findViewById(R.id.HRV);
        TextView VSO2 = this.findViewById(R.id.O2V);

        recyclerView = this.findViewById(R.id.caution_signs);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VRR = bundle.getInt("breath");
            VHR = bundle.getInt("bpm");
            VBP1 = bundle.getInt("SP");
            VBP2 = bundle.getInt("DP");
            VO2 = bundle.getInt("O2R");

            Log.i("MyLogs", Objects.requireNonNull(FirebaseAuth.getInstance().getUid()) );

            FirebaseDatabase.getInstance().getReference()
                    .child("userbase")
                    .child("patients")
                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                    .child("vitals")
                    .push()
                    .setValue(new PatientSigns(VBP1, VBP2, VHR, VO2, VRR));

//            VRR = 11;
//            VHR = 100;
//            VBP1 = 150;
//            VBP2 = 60;
//            VO2 = 101;

            user = bundle.getString("Usr");
            VSRR.setText(String.valueOf(VRR));
            VSHR.setText(String.valueOf(VHR));
            VSBPS.setText(String.valueOf(VBP1+" / "+VBP2));
            VSO2.setText(String.valueOf(VO2));
        }

        if(VHR<60){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.LOW, CautiousVitalSigns.HR));
        } else if (VHR>90){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.HIGH, CautiousVitalSigns.HR));
        }

        if(VBP1>=135 || VBP2>=90){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.HIGH, CautiousVitalSigns.BP));
        } else if(VBP1<=90 || VBP2<=60){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.LOW, CautiousVitalSigns.BP));
        }

        if(VRR<12){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.LOW, CautiousVitalSigns.RR));
        } else if (VRR>90){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.HIGH, CautiousVitalSigns.RR));
        }

        if(VO2<95){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.LOW, CautiousVitalSigns.O2));
        } else if (VO2>100){
            caution.add(new CautiousVitalSigns(CautiousVitalSigns.HIGH, CautiousVitalSigns.O2));
        }

        CautionAdapter adapter = new CautionAdapter(caution);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(VitalSignsResults.this, VitalSignsActivity.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
    }


}