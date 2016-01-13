package com.nick.dagger2sample.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    public void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void makeToast(int msg) {
        Toast.makeText(this, String.valueOf(msg), Toast.LENGTH_SHORT).show();
    }
}
