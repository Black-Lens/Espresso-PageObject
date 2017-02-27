package com.blacklenspub.espressorobots;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewStatus;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        setupOnClickListener();
        setupPresenter();
    }

    private void bindViews() {
        setContentView(R.layout.activity_main);
        editTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        editTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
    }

    private void setupOnClickListener() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();
                presenter.login(email, password);
            }
        });
    }

    private void setupPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override public void notifyEmptyEmail() {
        editTextEmail.setError(getString(R.string.enter_email));
    }

    @Override public void notifyEmptyPassword() {
        editTextPassword.setError(getString(R.string.enter_password));
    }

    @Override public void showLoginIndicator() {
        textViewStatus.setText(getString(R.string.logging_in));
    }

    @Override public void hideLoginIndicator() {
        // do nothing since we use the same TextView for login indicator and result.
    }

    @Override public void showLoginErrorInvalidEmail() {
        textViewStatus.setText(getString(R.string.invalid_email));
    }

    @Override public void showLoginError(String errorMessage) {
        textViewStatus.setText(getString(R.string.failure) + " : " + errorMessage);
    }

    @Override public void showLoginSuccess() {
        textViewStatus.setText(getString(R.string.success));
    }
}
