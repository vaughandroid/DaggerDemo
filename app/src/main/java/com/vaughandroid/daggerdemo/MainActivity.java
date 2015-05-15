package com.vaughandroid.daggerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Singleton;

public class MainActivity extends Activity {

    private Component mComponent;

    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton1 = (Button) findViewById(R.id.button_1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mComponent.getLogger().log("clicked");
            }
        });
    }

    public void setComponent(Component component) {
        mComponent = component;
    }

    public Component getComponent() {
        if (mComponent == null) {
            mComponent = DaggerMainActivity_Component.create();
        }
        return mComponent;
    }

    @Singleton @dagger.Component(modules = Logger.Module.class)
    public interface Component {
        Logger getLogger();
    }
}
