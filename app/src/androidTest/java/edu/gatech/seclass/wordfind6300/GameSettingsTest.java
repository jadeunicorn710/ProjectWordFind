package edu.gatech.seclass.wordfind6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GameSettingsTest {

  @Rule
  public ActivityTestRule<GameSettingActivity> activityRule = new ActivityTestRule<>(
      GameSettingActivity.class);

  // Test Purpose: This test checks whether game settings properly detects invalid game time
  @Test
  public void screenshotErrors1() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("0"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gametimelimit))
        .check(matches(hasErrorText("Input integers between 1 and 5 minutes")));
  }

  // Test Purpose: This test checks whether game settings properly detects invalid game time
  @Test
  public void screenshotErrors2() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("7"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gametimelimit))
        .check(matches(hasErrorText("Input integers between 1 and 5 minutes")));
  }

  // Test Purpose: This test checks whether game settings properly detects invalid board size
  @Test
  public void screenshotErrors3() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameboardsize))
        .check(matches(hasErrorText("Input integers between 4 and 8")));
  }

  // Test Purpose: This test checks whether game settings properly detects invalid board size
  @Test
  public void screenshotErrors4() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("9"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameboardsize))
        .check(matches(hasErrorText("Input integers between 4 and 8")));
  }

  // Test Purpose: This test checks whether game settings properly detects invalid letter
  @Test
  public void screenshotErrors5() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("a"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameletter))
        .check(matches(hasErrorText("Input letters between A and Z for letter")));
  }

  // Test Purpose: This test checks whether game settings properly detects invalid letter weight
  @Test
  public void screenshotErrors6() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("0"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameweight))
        .check(matches(hasErrorText("Input integers between 1 and 5 for weight")));
  }

  // Test Purpose: This test checks whether game settings properly detects invalid letter weight
  @Test
  public void screenshotErrors7() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("6"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameweight))
        .check(matches(hasErrorText("Input integers between 1 and 5 for weight")));
  }


  // Test Purpose: This test checks whether game settings properly detects valid game time
  @Test
  public void timeLimitTest1() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gametimelimit)).check(matches(withText("3")));
  }

  // Test Purpose: This test checks whether game settings properly detects valid board size
  @Test
  public void boardSizeTest1() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameboardsize)).check(matches(withText("4")));
  }

  // Test Purpose: This test checks whether game settings properly detects valid letter
  @Test
  public void letterTest1() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameletter)).check(matches(withText("A")));
  }

  // Test Purpose: This test checks whether game settings properly detects valid letter weight
  @Test
  public void letterWeightTest1() {
    onView(withId(R.id.gametimelimit)).perform(clearText(), replaceText("3"));
    onView(withId(R.id.gameboardsize)).perform(clearText(), replaceText("4"));
    onView(withId(R.id.gameletter)).perform(clearText(), replaceText("A"));
    onView(withId(R.id.gameweight)).perform(clearText(), replaceText("1"));
    Espresso.closeSoftKeyboard();
    onView(withId(R.id.save)).perform(click());
    onView(withId(R.id.gameweight)).check(matches(withText("1")));
  }
}
