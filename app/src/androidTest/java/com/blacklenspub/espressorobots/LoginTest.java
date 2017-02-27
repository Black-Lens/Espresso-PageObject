package com.blacklenspub.espressorobots;

import com.blacklenspub.espressorobots.page.LoginPage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;

public class LoginTest {

    private static final String EMAIL = "johnnydew@blacklenspub.com";
    private static final String PASSWORD = "nopassword";
    private static final String INVALID_EMAIL = "blacklens";

    private static final String ENTER_EMAIL = "Enter Email";
    private static final String ENTER_PASSWORD = "Enter Password";
    private static final String STATUS_SUCCESS = "Success";
    private static final String STATUS_INVALID_EMAIL = "Invalid Email";

    private LoginPage loginPage;

    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule(LoginActivity.class);

    @Before
    public void setupTest() {
        loginPage = new LoginPage(rule);
    }

    @Test
    public void loginSuccess() {
        loginPage.login(EMAIL, PASSWORD)
                 .expectStatusWithMessage(STATUS_SUCCESS);
    }

    @Test
    public void emptyEmailAndPassword() {
        loginPage.login("", "")
                 .expectEmptyEmailErrorMessage(ENTER_EMAIL)
                 .expectEmptyPasswordErrorMessage(ENTER_PASSWORD);
    }

    @Test
    public void invalidEmail() {
        loginPage.login(INVALID_EMAIL, PASSWORD)
                 .expectStatusWithMessage(STATUS_INVALID_EMAIL);
    }
}
