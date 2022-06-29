package com.example.incidentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.incidentapp.database.DbHelper;
import com.example.incidentapp.models.Incident;
import com.example.incidentapp.models.Officer;
import com.google.gson.Gson;

public class UpdateIncidentActivity extends AppCompatActivity {

    private Officer user;
    private EditText commentEditText;
    private TextView currentComment, currentTitle, currentDescription, currentCreatedBy;
    private int commentId;
    private String username, title,description,comment,createdBy;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_incident);


        commentId = getIntent().getIntExtra("commentId", 0);
        userId = getIntent().getIntExtra("userId", 0);
        username = getIntent().getStringExtra("userName");
        title = getIntent().getStringExtra("title");
        comment = getIntent().getStringExtra("comment");
        description = getIntent().getStringExtra("description");
        createdBy = getIntent().getStringExtra("createdBy");

        System.out.println("comment id: "+commentId);
        System.out.println("user id: "+userId);

        user = Officer.getOfficerById(userId,getApplicationContext());

        user.setDbHelper(new DbHelper(getApplicationContext()));

        System.out.println("name of the officer: "+user.getName());


        commentEditText = findViewById(R.id.commentEditText);
        currentComment = findViewById(R.id.comment);
        currentCreatedBy = findViewById(R.id.createdBy);
        currentDescription = findViewById(R.id.description);
        currentTitle = findViewById(R.id.title);

        currentTitle.setText(title);
        currentComment.setText(comment);
        currentCreatedBy.setText(createdBy);
        currentDescription.setText(description);
    }

    public void updateComment(View view) {

        boolean a = user.updateIncident(commentId, String.format("%s \n %s by (%s) \n",currentComment.getText().toString(),commentEditText.getText().toString(), user.getName()), Incident.STATUS.ACTIVE);
        if(a){
            finish();
        }else{

            Toast.makeText(getApplicationContext(), "not able to update", Toast.LENGTH_LONG).show();
        }
    }

    public void closeComment(View view) {


        user.closeIncident(commentId, String.format("%s \n %s by (%s) \n",currentComment.getText().toString(),commentEditText.getText().toString(), user.getName()));
    }
}