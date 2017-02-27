package com.blacklenspub.espressorobots;

public interface LoginContract {

    interface View {
        void notifyEmptyEmail();
        void notifyEmptyPassword();
        void showLoginIndicator();
        void hideLoginIndicator();
        void showLoginErrorInvalidEmail();
        void showLoginError(String errorMessage);
        void showLoginSuccess();
    }

    interface Presenter {
        void login(String email, String password);
    }

}
