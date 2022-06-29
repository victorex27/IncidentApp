package com.example.incidentapp.validator;

public class TextFieldValidator {

    public static void validateSignUpInformation(String emailText, String firstNameText, String lastNameText, String passwordText) throws Exception {
        if (emailText.isEmpty()) {
            throw new Exception("Email field cannot be empty ");
        }

        if (passwordText.isEmpty()) {
            throw new Exception("Password field cannot be empty ");
        }

        if (firstNameText.isEmpty()) {
            throw new Exception("First name field cannot be empty ");
        }

        if (lastNameText.isEmpty()) {
            throw new Exception("Last name field cannot be empty ");
        }
    }

    public static void validateLoginInformation(String emailText, String passwordText) throws Exception {
        if (emailText.isEmpty()) {
            throw new Exception("Email field cannot be empty ");
        }

        if (passwordText.isEmpty()) {
            throw new Exception("Password field cannot be empty ");
        }
    }

    public static void validateComplaintsInformation(String topic, String description) throws Exception {
        if (topic.isEmpty()) {
            throw new Exception("Topic field cannot be empty ");
        }

        if (description.isEmpty()) {
            throw new Exception("Description field cannot be empty ");
        }
    }
}
