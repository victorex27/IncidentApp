package com.example.incidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.incidentapp.database.DbHelper;
import com.example.incidentapp.models.Incident;
import com.example.incidentapp.models.Officer;
import com.example.incidentapp.models.User;
import com.example.incidentapp.validator.TextFieldValidator;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

public class OfficerActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Officer user;
    private TextView textView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer);

        Gson gson=new Gson();
        String value=getIntent().getStringExtra("user");
        user = gson.fromJson(value,Officer.class);
        user.setDbHelper( new DbHelper(getApplicationContext()));

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        textView = findViewById(R.id.username);

        textView.setText("User is logged in as "+user.getName()+" and id: "+user.getId());

        recyclerView
                = (RecyclerView) findViewById(
                R.id.recyclerView);




        verticalLayout
                = new LinearLayoutManager(
                OfficerActivity.this,
                LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setLayoutManager(verticalLayout);
        user.getAllIncidents();

        System.out.println("Len: "+ user.getAllIncidents().size());
        System.out.println("username: "+user.getName());
        recyclerView.setAdapter( new OfficerIncidentAdapter(user, getApplicationContext()));
    }


}