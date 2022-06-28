package com.example.incidentapp.models;

import com.example.incidentapp.database.DbHelper;

import java.util.ArrayList;

public class User implements Person{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private DbHelper dbHelper = null;

    public  User(int id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public  User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public  User( String email, String password){

        this.email = email;
        this.password = password;
    }

    public void setDbHelper(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return String.format("%s %s", firstName, lastName );
    }

    public String getFirstName(){

        return firstName;
    }

    public String getLastName(){
        return  lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return  this.password;
    }


    public User signIn() {

        return dbHelper.onGetUser(email, password);
    }

    public Incident createIncident(String topic, String description){


        return dbHelper.onCreateIncident(id, topic, description);
    }

    public ArrayList<Incident> getAllIncidents() {
        return dbHelper.onGetUserIncident(id);
    }

    public boolean signUp(){
        int userId = dbHelper.onCreateUser(email, firstName, lastName, password);
        return userId != -1 ;
    }

    public static ArrayList<User> getDefaultUsersForSeedingToDatabase(){

        ArrayList<User> users = new ArrayList<>();

        users.add( new User("amaobi", "obikobe", "amaobi@gmail.com", "password"));
        users.add( new User("amanda", "aduchie", "amanda@gmail.com", "password"));

        return users;

    }
}
