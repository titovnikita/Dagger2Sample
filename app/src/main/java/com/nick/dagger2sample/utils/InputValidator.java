package com.nick.dagger2sample.utils;

import android.util.Patterns;

public class InputValidator {

    public static boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email.toString().trim()).matches();
    }

    public static boolean isUsernameValid(CharSequence username) {
        String strUsername = username.toString().trim();
        return strUsername.length() > 3 && strUsername.length() < 20;
    }

    public static boolean isPasswordValid(CharSequence password) {
        String strPassword = password.toString().trim();
        return strPassword.length() > 3 && strPassword.length() < 20;
    }
}
