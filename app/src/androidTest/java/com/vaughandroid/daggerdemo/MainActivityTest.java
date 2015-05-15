package com.vaughandroid.daggerdemo;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;

import org.mockito.Mockito;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    Logger mLogger;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Workaround for a dexmaker issue, see: https://code.google.com/p/dexmaker/issues/detail?id=2
        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());

        MockComponent mockComponent = DaggerMainActivityTest_MockComponent.create();
        mLogger = mockComponent.getLogger();
        // TODO: Find a better way of inserting the mock component into the activity.
        getActivity().setComponent(mockComponent);
    }

    public void test_ButtonClick_LogsClick() {
        onView(withId(R.id.button_1))
                .perform(click());

        Mockito.verify(mLogger).log("clicked");
    }

    @Singleton @Component(modules = MockModule.class)
    public interface MockComponent extends MainActivity.Component {
    }

    @Module
    public static class MockModule {
        @Provides @Singleton Logger provideInstance() {
            return Mockito.mock(Logger.class);
        }
    }
}