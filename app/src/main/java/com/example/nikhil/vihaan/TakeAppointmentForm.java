package com.example.nikhil.vihaan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class TakeAppointmentForm extends AppCompatActivity {

    SharedPreferences sref;
    TextView name, age, gender, date,time;
    EditText problem;
    DatePicker picker;
    TimePicker picker_time;
    TextView doc,fee;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_appointment_form);

        sref = getSharedPreferences("Info",MODE_PRIVATE);

        submit  = findViewById(R.id.submit_appointment);

        name = findViewById(R.id.name);
        age = findViewById(R.id.Age);
        gender = findViewById(R.id.Gender);

        final String Name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        final String Age = String.valueOf(sref.getInt("Age",20));
        final String Gender = sref.getString("Gender","M");

        name.setText(Name);

        age.setText("Age: "+Age);

        gender.setText("Gender: " +Gender);

        picker = findViewById(R.id.date_picker);
        picker_time = findViewById(R.id.time_picker);

        problem = findViewById(R.id.et_problem);

        time = findViewById(R.id.time);
        date = findViewById(R.id.date);

        doc =  findViewById(R.id.doctor);
        fee = findViewById(R.id.fees);

        final String doctor = getIntent().getStringExtra("DoctorName");
        final String doctorID = getIntent().getStringExtra("DoctorID");
        String fees = String.valueOf(getIntent().getIntExtra("Fees",100));

        doc.setText("Dr."+doctor);
        fee.setText("Fee: "+fees);








        submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String Problem = problem.getText().toString();
               final Time appointmentTime = new Time(picker_time.getHour(), picker_time.getMinute(), picker.getDayOfMonth(),
                       picker.getMonth(), picker.getYear());
               // update databse of appointments

               //Add Paytm Okay

               // Nikhil will do it.
               //Add to OnActivity result


               Log.i("MyLogsProb", Problem);
               PatientAppointment patientAppointment = new PatientAppointment(Name, doctor, FirebaseAuth.getInstance().getUid(),

                       doctorID, appointmentTime, Gender, Integer.parseInt(Age), Problem);

                   FirebaseDatabase.getInstance().getReference().child("userbase")
                           .child("doctors")
                           .child(doctorID)
                           .child("appointments")
                           .push()
                           .setValue(patientAppointment);

                   FirebaseDatabase.getInstance().getReference().child("userbase")
                           .child("patients")
                           .child(FirebaseAuth.getInstance().getUid())
                           .child("appointments")
                           .push()
                           .setValue(patientAppointment);

                   finish();
                   startActivity(new Intent(TakeAppointmentForm.this,UserAppointments.class));

           }

       });







    }
}
