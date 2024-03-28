package com.example.goodwillapp.common;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailValidator {

    private static final String GMAIL_REGEX = "^[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*@gmail.com$";
    private static final Pattern PATTERN = Pattern.compile(GMAIL_REGEX);

    public static boolean isValidGmail(String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }


    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    private static final Pattern PATTERNS = Pattern.compile(PASSWORD_REGEX);

    public static boolean isValidPassword(String password) {
        Matcher matcher = PATTERNS.matcher(password);
        return matcher.matches();
    }



}


