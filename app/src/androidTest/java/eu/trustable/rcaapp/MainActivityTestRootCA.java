package eu.trustable.rcaapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestRootCA {

    Random rnd = new Random();

    String caName = "CN=TEST " +  rnd.nextInt(1000) + " trustable Root CA, C=DE";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.CAMERA");

    public void mainActivityTestRootCA() {

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.txtNewCASubject),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText(caName),closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.txtNewCASubject), withText(caName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        1),
                                5),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnSubmit), withText("Next"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.txtNewCA_QPW1),
//                        withText("Quorum member 1"),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("Quorum member !"), closeSoftKeyboard());


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnSubmit), withText("Submit"),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageQRCertificate), withContentDescription("QR code of the selected certificate"),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonQRCancel), withText("Cancel"),
                        isDisplayed()));
        appCompatButton3.perform(click());

    }

    @Test
    public void mainActivityCSRTest() {

        mainActivityTestRootCA();


        // select the first Root in the list
        ViewInteraction treeNodeWrapperView2 = onView(
                childAtPosition(
                        allOf(withId(R.id.tree_items),
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0)),
                        0));
        treeNodeWrapperView2.perform(scrollTo(), longClick());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btnScanCSR), withText("Scan CSR"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.GridLayout")),
                                        2),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        pressBack();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.txtCSRAsPEM),
                        // withText("Place a barcode inside the viewfinder rectangle to scan it."),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("-----BEGIN NEW CERTIFICATE REQUEST-----\nMIIC/DCCAeQCAQAwHDEaMBgGA1UEAxMRV1MtMjAxOS1Jc3N1aW5nQ0EwggEiMA0G\nCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC6/GWkDeV1KrHz4ZrSGG5e/vqFDndl\n9K9Q9cqdnF3+gZJo9oRYVASvMZ5zAJFpvZZ87KT8E6WlRUjZ4T2egap9GSsjXr5R\n2Q/N6i5FK7pTnSSMrYBSqVWdti8yQ7+bt+mdJExbP5IVjuXWPTE1PpYzJUrfJUpV\nJpBfGmYhzGDMTiXLrZOen0bKbytx4j4wevFWgsBdfeuZs2zchF4VYKt/SAZwTxW0\n3BeSlB/sxN6POvHDofD/CPD1vOsz8oVCCyVTKC4RS9S8YEkELSbxI/0quMFLkXXn\nrgVl7v+Za+7PZJ1q4ra0B2Doa2+X64LDq4YxyYdnHQzyMmm15fWnTe/dAgMBAAGg\ngZowHQYKKwYBBAGCNw0CAzEPFg0xMC4wLjE3NzYzLjIuMHkGCSqGSIb3DQEJDjFs\nMGowEAYJKwYBBAGCNxUBBAMCAQAwHQYDVR0OBBYEFPZBinSxew5Q6MlHG3Mz0oT+\nsHsYMBkGCSsGAQQBgjcUAgQMHgoAUwB1AGIAQwBBMAsGA1UdDwQEAwIBhjAPBgNV\nHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4IBAQAXgvm54Mzszp4UActJ5u0i\nJIawzSpbK/tjIElugatRqPbZCoZXsGW3HWr/LWNRc8GxooG+fxWYnNLvqq4U8TLx\nHBiISOTDGsK7C0X1LRG/Mh6uYGSlA7RW/smUyR8FHwPnwxlPDdafw3+QN0ZpkKA4\n4rvQ4WrObPzmA/ybdK1RBXNjD3BQZjHpYV2SF9O1vDfs1mmkKezQejvnh2tPgTkJ\nsT7E1GdqCPjpWQ1C8Kiz/PRdz/jt1D2ZeeAfJyS8G8lIwqq/w0nPlIGcKidMXKo1\nSSD3AwieROIlyDb5ueVaxnydIAjDlO/a1VsJz/UKibH3+fjHa/grnU6MGancxKLO\n-----END NEW CERTIFICATE REQUEST-----\n"),
                closeSoftKeyboard());


        ViewInteraction textView = onView(
                allOf(withId(R.id.txtCsrSubject), withText("CN=WS-2019-IssuingCA"),
                        isDisplayed()));
        textView.check(matches(withText("CN=WS-2019-IssuingCA")));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btnSubmit), withText("Next"),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.txtReview_QPW1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("Quorum member 1"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btnSubmit), withText("Next"),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.txtReview_QPW1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("Quorum member 2"), closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btnSubmit), withText("Next"),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageQRCertificate), withContentDescription("QR code of the selected certificate"),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
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
