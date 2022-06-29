package com.example.incidentapp;

//import android.support.v7.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.incidentapp.models.Incident;
import com.example.incidentapp.models.Officer;
import com.google.gson.Gson;

import java.util.ArrayList;


// The adapter class which
// extends RecyclerView Adapter
public class OfficerIncidentAdapter
        extends RecyclerView.Adapter<OfficerIncidentAdapter.MyView> {

    // List with String type
    private ArrayList<Incident> list;
    private Officer user;
    private Context context;
    private final String TAG = "ScheduleAdapter";
    private final ArrayList<String> daysAlreadyIncludedInTheView = new ArrayList<>();

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView
            extends RecyclerView.ViewHolder {

        // Text View
        TextView title, description, comment, createdBy;

        CardView cardView;


        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public MyView(View view) {
            super(view);


            title = (TextView) view
                    .findViewById(R.id.title);

            description = (TextView) view
                    .findViewById(R.id.description);

            comment = (TextView) view
                    .findViewById(R.id.comment);

            createdBy = (TextView) view
                    .findViewById(R.id.createdBy);

            cardView = (CardView)  view.findViewById(R.id.cardView);

        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public OfficerIncidentAdapter(Officer user, Context context) {

        this.user = user;
        this.list = user.getAllIncidents();
        this.context = context;


    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType) {

        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.officer_incident_item,
                        parent,
                        false);

        return new MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(final MyView holder,
                                 final int position) {

        // Set the text of each item of
        // Recycler view with the list items

        Incident entry = list.get(position);

        holder.title.setText(entry.getTopic());


        holder.description.setText(entry.getDescription());
        holder.comment.setText(entry.getComments());
        holder.createdBy.setText(entry.getCreatedBy().getName());

        holder.cardView.setOnClickListener( v->{
            viewNewDialog(v, entry);
        });

    }

    public void viewNewDialog(View v, Incident entry){

        Intent intent = new Intent( context, UpdateIncidentActivity.class);


        Gson gson = new Gson();




        intent.putExtra("userName", user.getName());
        intent.putExtra("userId", user.getId());
        intent.putExtra("commentId", entry.getId());
        intent.putExtra("comment", entry.getComments());
        intent.putExtra("title", entry.getTopic());
        intent.putExtra("description", entry.getDescription());
        intent.putExtra("createdBy", entry.getCreatedBy().getName());


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // send user to that activity
        context.startActivity(intent);

    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount() {

        return list.size();
    }
}
