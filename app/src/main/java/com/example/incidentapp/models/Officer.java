package com.example.incidentapp.models;

import com.example.incidentapp.database.DbHelper;

import java.util.ArrayList;

public class Officer implements Person{

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private DbHelper dbHelper = null;

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
        return null;
    }

    public Officer signIn(String email, String password) {

        return dbHelper.onGetOfficer(email, password);
    }

    @Override
    public ArrayList<Incident> getAllIncidents() {
        return null;
    }

    public static ArrayList<Officer> getDefaultUsersForSeedingToDatabase(){

        ArrayList<Officer> users = new ArrayList<>();

        users.add( new Officer("amadi", "ejiofor", "amadi@gmail.com", "password"));
        users.add( new Officer("amara", "tinu", "tinu@gmail.com", "password"));

        return users;

    }


}
