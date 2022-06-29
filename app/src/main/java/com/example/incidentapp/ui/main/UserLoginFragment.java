package com.example.incidentapp.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.incidentapp.R;
import com.example.incidentapp.UserActivity;
import com.example.incidentapp.database.DbHelper;
import com.example.incidentapp.models.User;
import com.example.incidentapp.validator.TextFieldValidator;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;


/**
 * A placeholder fragment containing a simple view.
 */
public class UserLoginFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private EditText email;
    private EditText password;
    private Button button;
    private User user;


    public static UserLoginFragment newInstance() {
        UserLoginFragment fragment = new UserLoginFragment();
        Bundle bundle = new Bundle();
//        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_user_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(v -> {
            login(v);
        });
        return view;
    }

    private void login(View view) {

        try {
            String emailText = email.getText().toString();

            String passwordText = password.getText().toString();
            TextFieldValidator.validateLoginInformation(emailText, passwordText);

            user = new User(emailText, passwordText);

            user.setDbHelper(new DbHelper(getContext()));

            User validUser = user.signIn();
            if (validUser == null)
                throw new Exception("Invalid Login.");

            user = validUser;

            Snackbar.make(getView(), "Login", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            goToCreateIncidentActivity();

        } catch (Exception ex) {
            Snackbar.make(getView(), ex.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void goToCreateIncidentActivity(){
        Intent intent = new Intent( getActivity(), UserActivity.class);
        Gson gson = new Gson();

        String objAsString = gson.toJson(user);
        System.out.println("this is the user name we are sending : "+user.getName());
        intent.putExtra("user", objAsString);
         // send user to that activity
        startActivity(intent);

        ( (Activity)getActivity()).overridePendingTransition(0,0);
    }


}