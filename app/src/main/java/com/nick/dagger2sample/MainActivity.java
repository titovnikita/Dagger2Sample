package com.nick.dagger2sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.utils.SharedHelper;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btnSaveToPreferences)
    Button btnSave;
    @Bind(R.id.btnShow)
    Button btnShow;
    @Inject
    SharedHelper sharedHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerApplication.getStorageComponent(this).inject(this);
    }

    @OnClick(R.id.btnSaveToPreferences)
    void onSaveButtonClick() {
        sharedHelper.generateNewValue();
    }

    @OnClick(R.id.btnShow)
    void onShowValueClick() {
        Toast.makeText(this, sharedHelper.getValue(), Toast.LENGTH_SHORT).show();
    }
}
