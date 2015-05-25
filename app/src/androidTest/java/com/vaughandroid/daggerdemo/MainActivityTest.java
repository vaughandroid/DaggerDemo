package com.vaughandroid.daggerdemo;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public MockLoggerTestRule<MainActivity> mMockLoggerRule = new MockLoggerTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUpDexmakerCache() throws Exception {
        // Workaround for a dexmaker issue, see: https://code.google.com/p/dexmaker/issues/detail?id=2
        System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
    }

    @Test
    public void buttonClick_LogsClick() {
        onView(withId(R.id.button_1)).perform(click());

        verify(mMockLoggerRule.getMockLogger()).log("clicked");
    }
}