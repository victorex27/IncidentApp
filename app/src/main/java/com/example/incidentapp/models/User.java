package com.example.incidentapp.models;

import java.util.ArrayList;

public class User implements Person{
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean signIn() {

        return false;
    }

    @Override
    public ArrayList<Incident> getAllIncidents() {
        return null;
    }

    public boolean signUp(String username, String password){

        return false;
    }
}
