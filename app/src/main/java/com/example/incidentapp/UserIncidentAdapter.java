package com.example.incidentapp;

//import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.incidentapp.models.Incident;

import java.util.ArrayList;


// The adapter class which
// extends RecyclerView Adapter
public class UserIncidentAdapter
        extends RecyclerView.Adapter<UserIncidentAdapter.MyView> {

    // List with String type
    private ArrayList<Incident> list;
    private Context context;
    private final String TAG = "ScheduleAdapter";
    private final ArrayList<String> daysAlreadyIncludedInTheView = new ArrayList<>();

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView
            extends RecyclerView.ViewHolder {

        // Text View
        TextView title, description, comment, createdBy;


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

        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public UserIncidentAdapter(ArrayList<Incident> list, Context context) {

        this.list = list;
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
                .inflate(R.layout.incident_item,
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


//        holder.viewAttendanceButton.setOnClickListener(v -> {
//
//            Intent intent = new Intent(context, AttendanceViewActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putInt("scheduleEntryId", entry.getId());
//            bundle.putString("courseCode", entry.getCourseCode());
//            intent.putExtras(bundle);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//        });
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount() {

        return list.size();
    }
}
