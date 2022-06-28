package com.example.incidentapp.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.incidentapp.R;
import com.example.incidentapp.database.DbHelper;
import com.example.incidentapp.models.User;
import com.example.incidentapp.validator.TextFieldValidator;
import com.google.android.material.snackbar.Snackbar;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserSignUpFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private EditText email, password, firstName, lastName;
    private Button button;

    private User user;

    public static UserSignUpFragment newInstance() {
        UserSignUpFragment fragment = new UserSignUpFragment();
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

        View view = inflater.inflate(R.layout.fragment_user_sign_up, container, false);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        button = view.findViewById(R.id.button);

        button.setOnClickListener( v->{
            Log.d("Button", "Clicked");
            createUser(v);
        });
        return  view;
    }


    private void createUser(View view){

        try {
            String emailText = email.getText().toString();
            String firstNameText = firstName.getText().toString();
            String lastNameText = lastName.getText().toString();
            String passwordText = password.getText().toString();
            TextFieldValidator.validateSignUpInformation(emailText, firstNameText, lastNameText, passwordText);

            user = new User(firstNameText, lastNameText,emailText,passwordText);

            user.setDbHelper(new DbHelper(getContext()));

            if(!user.signUp()) throw  new Exception("Unable to create user with the given information.");

            Snackbar.make(getView(), "user was created", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }catch(Exception ex){
            Snackbar.make(getView(), ex.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void onMoveToLoginActivity(View view){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment newFragment = UserLoginFragment.newInstance();
        ft.replace(R.id.container, newFragment);

        ft.addToBackStack(UserLoginFragment.class.getSimpleName());
        ft.commitAllowingStateLoss();
    }
    private void goToCreateIncidentActivity(){
//        Intent intent = new Intent( getActivity(), ActivityName.class);

        // send user to that activity
//        startActivity(intent);

//        ( (Activity)getActivity()).overridePendingTransition(0,0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}