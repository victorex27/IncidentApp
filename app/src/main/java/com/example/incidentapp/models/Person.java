package com.example.incidentapp.models;

import java.util.ArrayList;

public interface Person {


    public int getId();
    public String getName();
    public boolean signIn();
    public ArrayList<Incident> getAllIncidents();
}
