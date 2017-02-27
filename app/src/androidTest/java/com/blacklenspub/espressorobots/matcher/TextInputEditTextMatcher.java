package com.blacklenspub.espressorobots.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import android.support.design.widget.TextInputEditText;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

public class TextInputEditTextMatcher {

    // NOTE : I tested with TextInputLayout but can't retrieve error message.

    public static Matcher<View> hasErrorText(final Matcher<String> stringMatcher) {
        return new BoundedMatcher<View, TextInputEditText>(TextInputEditText.class) {

            @Override public void describeTo(Description description) {
                description.appendText("with error text: ");
                stringMatcher.describeTo(description);
            }

            @Override protected boolean matchesSafely(TextInputEditText item) {
                return stringMatcher.matches(item.getError());
            }
        };
    }

}
