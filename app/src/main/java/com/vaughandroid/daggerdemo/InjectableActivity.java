package com.vaughandroid.daggerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Chris
 */
public class InjectableActivity extends Activity {

    private Logger mLogger;

    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerInjectableActivity_Component.builder()
                .component(((DemoApplication) getApplication()).getComponent())
                .build()
                .inject(this);
    }

    @Inject public void inject(Logger logger) {
        mLogger = logger;

        mButton1 = (Button) findViewById(R.id.button_1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogger.log("clicked");
            }
        });
    }

    @ActivityScope
    @dagger.Component(dependencies = { DemoApplication.Component.class })
    public interface Component extends DemoApplication.Component {

        InjectableActivity inject(InjectableActivity activity);
    }
}
