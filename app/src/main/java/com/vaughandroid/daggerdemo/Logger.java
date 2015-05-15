package com.vaughandroid.daggerdemo;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Provides;

public class Logger {
    public void log(String message) {
        Log.d("tag", message);
    }

    @dagger.Module
    public static class Module {
        @Provides @Singleton Logger getInstance() {
            return new Logger();
        }
    }
}
