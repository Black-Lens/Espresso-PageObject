package com.blacklenspub.espressorobots;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override public void login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            handleEmptyInput(email, password);
        } else {
            if (isValidEmail(email)) {
                performFakeLogin();
            } else {
                view.showLoginErrorInvalidEmail();
            }
        }
    }

    private void handleEmptyInput(String email, String password) {
        if (email.isEmpty()) {
            view.notifyEmptyEmail();
        }
        if (password.isEmpty()) {
            view.notifyEmptyPassword();
        }
    }

    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) &&
               Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void performFakeLogin() {
        view.showLoginIndicator();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                view.hideLoginIndicator();
                view.showLoginSuccess();
            }
        }, 2000);
    }
}
