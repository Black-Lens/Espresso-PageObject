package com.blacklenspub.espressorobots.page;

import com.blacklenspub.espressorobots.LoginActivity;
import com.blacklenspub.espressorobots.R;
import com.blacklenspub.espressorobots.matcher.TextInputEditTextMatcher;

import org.hamcrest.Matchers;

import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LoginPage {

    public ActivityTestRule<LoginActivity> rule;

    public LoginPage(ActivityTestRule<LoginActivity> rule) {
        this.rule = rule;
    }

    public LoginPage login(String email, String password) {
        return enterEmail(email)
                .enterPassword(password)
                .tapLoginButton()
                .waitForLoginStatus();
    }

    private LoginPage enterEmail(String email) {
        onView(withId(R.id.textInputEditTextEmail))
                .perform(typeText(email));
        return this;
    }

    private LoginPage enterPassword(String password) {
        onView(withId(R.id.textInputEditTextPassword))
                .perform(typeText(password));
        return this;
    }

    private LoginPage tapLoginButton() {
        onView(withId(R.id.buttonLogin))
                .perform(click());
        return this;
    }

    private LoginPage waitForLoginStatus() {
        while (isLoginInProgress()) ;
        return this;
    }

    private boolean isLoginInProgress() {
        // A simple way to wait for background work instead of using IdlingResource in actual code.
        TextView tv = (TextView) rule.getActivity().findViewById(R.id.textViewStatus);
        return tv.getText().toString().trim().equals("Loging In …");
    }

    public LoginPage expectStatusWithMessage(String message) {
        onView(withId(R.id.textViewStatus))
                .check(matches(withText(message)));
        return this;
    }

    public LoginPage expectEmptyEmailErrorMessage(String message) {
        onView(withId(R.id.textInputEditTextEmail))
                .check(matches(TextInputEditTextMatcher.hasErrorText(Matchers.containsString(message))));
        return this;
    }

    public LoginPage expectEmptyPasswordErrorMessage(String message) {
        onView(withId(R.id.textInputEditTextPassword))
                .check(matches(TextInputEditTextMatcher.hasErrorText(Matchers.containsString(message))));
        return this;
    }

}
