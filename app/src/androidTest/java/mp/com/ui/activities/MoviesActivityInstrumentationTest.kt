package mp.com.ui.activities

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeRight
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import mp.com.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesActivityInstrumentationTest   {

    @Rule
    @JvmField
    val rule  = ActivityTestRule(MoviesActivity::class.java)


    @Test
    fun itshouldbesetwhenresumed()
    {
        onView(withId(R.id.activity_movies_view_pager)).perform(swipeRight());
        onView(withId(R.id.activity_movies_tab_layout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    fun itshouldbeattachedwithfragment()
    {
        onView(allOf(withId(R.id.root), isCompletelyDisplayed()))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }
}