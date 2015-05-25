package com.vaughandroid.daggerdemo;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

public class MockLoggerTestRule<T extends Activity> extends ActivityTestRule<T> {

    Logger mMockLogger;

    public MockLoggerTestRule(Class<T> activityClass) {
        super(activityClass);
    }

    public MockLoggerTestRule(Class<T> activityClass, boolean initialTouchMode) {
        super(activityClass, initialTouchMode);
    }

    public MockLoggerTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();

        DemoApplication app = (DemoApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        MockComponent mockComponent = DaggerMockLoggerTestRule_MockComponent.create();
        app.setComponent(mockComponent);

        mMockLogger = mockComponent.getLogger();
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
    }

    public Logger getMockLogger() {
        if (mMockLogger == null) {
            Log.w("MockLoggerTestRule", "Activity wasn\'t created yet");
        }
        return mMockLogger;
    }

    @Singleton
    @Component(modules = MockModule.class)
    public interface MockComponent extends DemoApplication.Component {
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
