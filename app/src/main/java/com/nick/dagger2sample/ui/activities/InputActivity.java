package com.nick.dagger2sample.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.nick.dagger2sample.R;
import com.nick.dagger2sample.utils.InputValidator;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func3;

public class InputActivity extends BaseActivity {

    @Bind(R.id.etUsername)
    EditText etUsername;
    @Bind(R.id.etEmail)
    EditText etEmail;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ButterKnife.bind(this);

        addSubscription(Observable.combineLatest(
                RxTextView.textChanges(etUsername).skip(1),
                RxTextView.textChanges(etEmail).skip(1),
                RxTextView.textChanges(etPassword).skip(1),

                new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {

                    @Override
                    public Boolean call(CharSequence username, CharSequence email, CharSequence password) {
                        boolean isValid = true;

                        if (!InputValidator.isUsernameValid(username)) {
                            etUsername.setError("Invalid username.");
                            isValid = false;
                        }

                        if (!InputValidator.isEmailValid(email)) {
                            etEmail.setError("Invalid email.");
                            isValid = false;
                        }

                        if (!InputValidator.isPasswordValid(password)) {
                            etPassword.setError("Invalid password.");
                            isValid = false;
                        }

                        return isValid;
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean isValid) {
                        btnSubmit.setEnabled(isValid);
                    }
                }));
    }


}
