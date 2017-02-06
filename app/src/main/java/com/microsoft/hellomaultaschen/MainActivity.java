package com.microsoft.hellomaultaschen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.microsoft.azure.mobile.MobileCenter;
import com.microsoft.azure.mobile.analytics.Analytics;
import com.microsoft.azure.mobile.crashes.Crashes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final long reference = System.currentTimeMillis();

        MobileCenter.start(getApplication(),
                "66b68e32-2bbf-4a69-8f21-44dd69a20848",
                Analytics.class, Crashes.class
        );

        Button crashButton = (Button) findViewById(R.id.button_crash);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<String>();
                list.get(10);
            }
        });

        Button eventButton = (Button) findViewById(R.id.button_event);
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> properties = new HashMap<String, String>();
                properties.put("time", Long.toString(System.currentTimeMillis() - reference));
                Analytics.trackEvent("button_clicked", properties);
            }
        });
    }
}
