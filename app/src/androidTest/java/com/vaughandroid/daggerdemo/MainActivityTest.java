package com.vaughandroid.daggerdemo;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    Logger mLogger;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUpDexmakerCache() throws Exception {
        // Workaround for a dexmaker issue, see: https://code.google.com/p/dexmaker/issues/detail?id=2
        System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
    }

    @Before
    public void setUpMockComponent() {
        MockComponent mockComponent = DaggerMainActivityTest_MockComponent.create();
        mLogger = mockComponent.getLogger();
        // TODO: Find a better way of inserting the mock component into the activity.
        mActivityRule.getActivity().setComponent(mockComponent);
    }

    @Test
    public void buttonClick_LogsClick() {
        onView(withId(R.id.button_1)).perform(click());

        Mockito.verify(mLogger).log("clicked");
    }

    @Singleton
    @Component(modules = MockModule.class)
    public interface MockComponent extends MainActivity.Component {
    }

    @Module
    public static class MockModule {
        @Provides
        @Singleton
        Logger provideInstance() {
            return Mockito.mock(Logger.class);
        }
    }
}