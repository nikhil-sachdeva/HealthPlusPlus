package com.example.nikhil.vihaan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class AppointmentListAdapter extends FirebaseRecyclerAdapter<PatientAppointment, AppointmentListAdapter.AppointmentHolder> {

    Context context;
    /**
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public AppointmentListAdapter(int modelLayout, Query ref, Context context) {
        super(PatientAppointment.class, modelLayout, AppointmentHolder.class, ref);
        this.context = context;
    }

    @NonNull
    @Override
    public AppointmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_layout, parent, false);
        return new AppointmentHolder(view);
    }


    @Override
    protected void populateViewHolder(AppointmentHolder viewHolder, final PatientAppointment model, int position) {
        if(context.getSharedPreferences("Info",MODE_PRIVATE).getBoolean("isDoctor", false)) {
            viewHolder.appName.setText("Patient Name: "+ model.getPatientName());
        } else {
            viewHolder.appName.setText("Doctor Name: "+ model.getDoctorName());
        }
        viewHolder.appDate.setText(model.getDate());
        viewHolder.appTime.setText(model.getTime());
        viewHolder.appDescription.setText("Description: " + model.getDescription());

    }



    public class AppointmentHolder extends RecyclerView.ViewHolder {
        TextView appName, appDescription, appTime, appDate;

        public AppointmentHolder(@NonNull View itemView) {
            super(itemView);
            this.appName = itemView.findViewById(R.id.appointment_name);
            this.appDescription = itemView.findViewById(R.id.appointment_desc);
            this.appTime = itemView.findViewById(R.id.appointment_time);
            this.appDate = itemView.findViewById(R.id.appointment_date);
        }

    }
}
