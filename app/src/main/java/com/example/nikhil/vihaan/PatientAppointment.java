package com.example.nikhil.vihaan;

public class PatientAppointment {

    private String patientName, doctorName, patientID, doctorID;
    private Time appointmentTime;
    private String gender;
    private int age, confirmed;
    private String description;

    public PatientAppointment(){}


    public PatientAppointment(String patientName, String doctorName, String patientID, String doctorID,
                              Time appointmentTime, String gender, int age, String description) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentTime = appointmentTime;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.confirmed = 0;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getTime(){
        return appointmentTime.getHour()+":"+appointmentTime.getMinutes();
    }

    public String getDate(){
        return appointmentTime.getDay()+"."+appointmentTime.getMonth()+"."+appointmentTime.getYear();
    }
}
