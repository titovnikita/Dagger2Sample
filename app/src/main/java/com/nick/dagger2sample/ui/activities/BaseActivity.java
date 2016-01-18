package com.nick.dagger2sample.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nick.dagger2sample.utils.RxUtils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AppCompatActivity {

    private CompositeSubscription subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RxUtils.restoreSubscriptions(subscriptions);
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxUtils.unsubscribe(subscriptions);
    }

    protected void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    protected void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void makeToast(int msg) {
        Toast.makeText(this, String.valueOf(msg), Toast.LENGTH_SHORT).show();
    }

}
