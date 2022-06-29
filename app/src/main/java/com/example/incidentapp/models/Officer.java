package com.example.incidentapp.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.incidentapp.database.DbHelper;

import java.util.ArrayList;

public class Officer implements Person, Parcelable {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private DbHelper dbHelper = null;
    private int mData;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public  Officer( String email, String password){

        this.email = email;
        this.password = password;
    }

    public  Officer(int id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public  Officer(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    @Override
    public int getId() {
        return id;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public String getName() {
        return String.format("%s %s",firstName,lastName);
    }

    public Officer signIn() {

        return dbHelper.onGetOfficer(email, password);
    }


    public static Officer getOfficerById(int id, Context context) {

        DbHelper dbHelper = new DbHelper(context);
        return dbHelper.getOfficerById(id);
    }


    public ArrayList<Incident> getAllIncidents() {
        return dbHelper.onGetIncidentForOfficer();
    }

    public static ArrayList<Officer> getDefaultUsersForSeedingToDatabase(){

        ArrayList<Officer> users = new ArrayList<>();

        users.add( new Officer("amadi", "ejiofor", "amadi@gmail.com", "password"));
        users.add( new Officer("amara", "tinu", "tinu@gmail.com", "password"));

        return users;

    }


    public boolean updateIncident(int incidentId,String comment, Incident.STATUS status) {

        return dbHelper.onUpdateIncident(incidentId,comment,status);
    }


    public boolean closeIncident(int incidentId,String comment) {

        return updateIncident( incidentId ,comment, Incident.STATUS.CLOSED);

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mData);
    }

    public static final Parcelable.Creator<Officer> CREATOR = new Parcelable.Creator<Officer>() {
        public Officer createFromParcel(Parcel in) {
            return new Officer(in);
        }

        public Officer[] newArray(int size) {
            return new Officer[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Officer(Parcel in) {
        mData = in.readInt();
    }
}
