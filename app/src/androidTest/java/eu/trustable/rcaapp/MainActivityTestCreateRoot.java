package eu.trustable.rcaapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestCreateRoot {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTestCreateRoot() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerValidity),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                3),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.txtCsrPEM), withText("CN=trustable Root CA, C=DE"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.txtCsrPEM), withText("CN=trustable Root CA, C=DE"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                5),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("CN=trustable Root CA test, C=DE"));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.txtCsrPEM), withText("CN=trustable Root CA test, C=DE"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                5),
                        isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinnerQuorum),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                7),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatTextView2.perform(click());

        pressBack();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSubmit), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnSubmit), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        3),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageQRCertificate), withContentDescription("QR code of the selected certificate"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonQRCancel), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
