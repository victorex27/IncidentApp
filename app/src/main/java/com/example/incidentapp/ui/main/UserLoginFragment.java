package com.example.incidentapp.ui.main;

import android.os.Bundle;
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

            Snackbar.make(getView(), "Login", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } catch (Exception ex) {
            Snackbar.make(getView(), ex.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onMoveToSignUpActivity(View view) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment newFragment = UserSignUpFragment.newInstance();

        ft.remove(UserLoginFragment.newInstance());
//        ft.replace(R.id.container, newFragment);
//
        ft.addToBackStack(UserSignUpFragment.class.getSimpleName());
        ft.commitAllowingStateLoss();
    }


}