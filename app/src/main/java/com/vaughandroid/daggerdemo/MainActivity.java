package com.vaughandroid.daggerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Singleton;

public class MainActivity extends Activity {

    private Logger mLogger;

    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogger = ((DemoApplication) getApplication()).getComponent().getLogger();

        mButton1 = (Button) findViewById(R.id.button_1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogger.log("clicked");
            }
        });
    }
}
