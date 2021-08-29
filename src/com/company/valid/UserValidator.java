package com.company.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private Pattern pattern;
    private Matcher matcher;

    public boolean checkFirstName(String firstName) {
        return firstName.length() > 1;
    }

    public boolean checkLastName(String lastName) {
        return lastName.length() > 2;
    }

    public boolean checkPassword(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean checkEmail(String email){
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern= Pattern.compile(EMAIL_PATTERN);
        matcher=pattern.matcher(email);
        return matcher.matches();
    }
}
