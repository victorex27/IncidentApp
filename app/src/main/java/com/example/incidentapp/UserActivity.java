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
import com.example.incidentapp.models.User;
import com.example.incidentapp.validator.TextFieldValidator;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

public class UserActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private TextView textView;
    private User user;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    LinearLayoutManager verticalLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Gson gson=new Gson();
        String value=getIntent().getStringExtra("user");
        user = gson.fromJson(value,User.class);
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
                UserActivity.this,
                LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setLayoutManager(verticalLayout);

        System.out.println("Len: "+ user.getAllIncidents().size());
        recyclerView.setAdapter( new UserIncidentAdapter(user.getAllIncidents(), getApplicationContext()));
    }

    public void createComplaint(View view) {

        try {
            String titleText = title.getText().toString();
            String descriptionText = description.getText().toString();
            TextFieldValidator.validateComplaintsInformation(titleText, descriptionText);
            Incident incident = user.createIncident(titleText, descriptionText).setCreatedBy(user);
            Snackbar.make(view, "Complaint was created successfully", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }catch (Exception ex){
            Snackbar.make(view, ex.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


    }
}