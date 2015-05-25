package com.vaughandroid.daggerdemo;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

public class MockLoggerTestRule implements TestRule {

    Logger mMockLogger;

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                DemoApplication app = (DemoApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
                MockComponent mockComponent = DaggerMockLoggerTestRule_MockComponent.create();
                app.setComponent(mockComponent);
                mMockLogger = mockComponent.getLogger();

                base.evaluate();
            }
        };
    }

    public Logger getMockLogger() {
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
