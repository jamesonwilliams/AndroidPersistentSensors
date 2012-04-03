package org.nosemaj.service_lock_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ServiceLockTest extends Activity {
    public static final String TAG = ServiceLockTest.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void onResume() {
        super.onResume();

        startService(new Intent(this, TheService.class));
    }
}
