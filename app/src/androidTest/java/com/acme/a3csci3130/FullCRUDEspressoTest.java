package com.acme.a3csci3130;

/**
 * Created by Joel Fong on 2018-06-06
 */
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class FullCRUDEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    //Clear out everything so tests can run
    private DatabaseReference Firebase;
    @Before
    public void setUp() throws Exception {
        Firebase = FirebaseDatabase.getInstance().getReference("businesses");
        Firebase.removeValue();
    }

    @Test
    public void testCreation() {
        onView(withId(R.id.submitButton)).perform(click());

        //Go through creation steps
        onView(withId(R.id.name)).perform(typeText("Test Business"));
        onView(withId(R.id.businessNumber)).perform(typeText("928394764"));
        onView(withId(R.id.address)).perform(typeText("123 Peggy's Cove Road"), closeSoftKeyboard());

        onView(withId(R.id.businessSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fisher"))).perform(click());
        onView(withId(R.id.businessSpinner)).check(matches(withSpinnerText(containsString("Fisher"))));
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("NS"))));

        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
    }

    @Test
    public void testRead() {
        onView(withId(R.id.submitButton)).perform(click());

        //Go through creation steps
        onView(withId(R.id.name)).perform(typeText("Test Business"));
        onView(withId(R.id.businessNumber)).perform(typeText("928394764"));
        onView(withId(R.id.address)).perform(typeText("123 Peggy's Cove Road"), closeSoftKeyboard());

        onView(withId(R.id.businessSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fisher"))).perform(click());
        onView(withId(R.id.businessSpinner)).check(matches(withSpinnerText(containsString("Fisher"))));
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("NS"))));

        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.name)).check(matches(withText("Test Business")));
        onView(withId(R.id.businessNumber)).check(matches(withText("928394764")));
        onView(withId(R.id.address)).check(matches(withText("123 Peggy's Cove Road")));

        onView(withId(R.id.businessSpinner)).check(matches(withSpinnerText(containsString("Fisher"))));
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("NS"))));
    }

    @Test
    public void testUpdate() throws Exception{
        onView(withId(R.id.submitButton)).perform(click());

        //Go through creation steps
        onView(withId(R.id.name)).perform(typeText("Test Business"));
        onView(withId(R.id.businessNumber)).perform(typeText("928394764"));
        onView(withId(R.id.address)).perform(typeText("123 Peggy's Cove Road"), closeSoftKeyboard());

        onView(withId(R.id.businessSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fisher"))).perform(click());
        onView(withId(R.id.businessSpinner)).check(matches(withSpinnerText(containsString("Fisher"))));
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("NS"))));

        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        Thread.sleep(1000);

        onView(withId(R.id.name)).perform(replaceText("Test Business2"));
        onView(withId(R.id.businessNumber)).perform(replaceText("111111111"));
        onView(withId(R.id.address)).perform(replaceText("1233 LeMarchant Street"), closeSoftKeyboard());

        onView(withId(R.id.businessSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Distributor"))).perform(click());
        onView(withId(R.id.businessSpinner)).check(matches(withSpinnerText(containsString("Distributor"))));
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("AB"))).perform(click());
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("AB"))));

        onView(withId(R.id.updateButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.name)).check(matches(withText("Test Business2")));
        onView(withId(R.id.businessNumber)).check(matches(withText("111111111")));
        onView(withId(R.id.address)).check(matches(withText("1233 LeMarchant Street")));

        onView(withId(R.id.businessSpinner)).check(matches(withSpinnerText(containsString("Distributor"))));
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("AB"))));
    }

    @Test
    public void testDelete() throws Exception{
        onView(withId(R.id.submitButton)).perform(click());

        //Go through creation steps
        onView(withId(R.id.name)).perform(typeText("Test Business"));
        onView(withId(R.id.businessNumber)).perform(typeText("928394764"));
        onView(withId(R.id.address)).perform(typeText("123 Peggy's Cove Road"), closeSoftKeyboard());

        onView(withId(R.id.businessSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fisher"))).perform(click());
        onView(withId(R.id.businessSpinner)).check(matches(withSpinnerText(containsString("Fisher"))));
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("NS"))));

        onView(withId(R.id.submitButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.deleteButton)).perform(click());
        onView(withId(R.id.listView)).check(matches(not(isDisplayed())));    }
}