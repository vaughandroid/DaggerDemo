package com.vaughandroid.daggerdemo;

import android.app.Application;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Chris
 */
public class DemoApplication extends Application {

    private Component mComponent;

    public void setComponent(Component component) {
        mComponent = component;
    }

    public Component getComponent() {
        if (mComponent == null) {
            mComponent = DaggerDemoApplication_Component.create();
        }
        return mComponent;
    }

    @Singleton
    @dagger.Component(modules = Logger.Module.class)
    public interface Component {
        Logger getLogger();
    }
}
