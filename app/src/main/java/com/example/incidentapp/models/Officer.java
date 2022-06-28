package com.example.incidentapp.models;

import java.util.ArrayList;

public class Officer implements Person{
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    public boolean signIn() {

        return false;
    }

    @Override
    public ArrayList<Incident> getAllIncidents() {
        return null;
    }


}
