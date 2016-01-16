package com.nick.dagger2sample.utils;

import android.content.SharedPreferences;

import java.util.Random;

public class SharedHelper {
    private final String KEY_VALUE = "value";

    private final SharedPreferences sharedPreferences;

    public SharedHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void generateNewValue() {
        sharedPreferences.edit().putInt(KEY_VALUE, new Random().nextInt(1000)).apply();
    }

    public String getValue() {
        return String.valueOf(sharedPreferences.getInt(KEY_VALUE, 0));
    }
}