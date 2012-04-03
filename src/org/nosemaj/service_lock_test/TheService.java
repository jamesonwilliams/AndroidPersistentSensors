package org.nosemaj.service_lock_test;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager.WakeLock;
import android.os.PowerManager;
import android.os.Process;
import android.util.Log;

public class TheService extends Service implements SensorEventListener {
    public static final String TAG = TheService.class.getName();
    private WakeLock mWakeLock = null;

    /*
     * Acquire a partial wake lock.
     */
    private void acquireWakeLock() {
        PowerManager manager =
            (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
        mWakeLock.acquire();
    }

    /*
     * Register this as a sensor event listener.
     */
    private void registerListener() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
     * Make this service a foreground service.
     */
    private void putInForeground() {
        startForeground(Process.myPid(), new Notification());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        
        Log.i(TAG, "onCreate()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.i(TAG, "onStartCommand()");

        putInForeground();
        registerListener();
        acquireWakeLock();

        return START_STICKY;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i(TAG, "onAccuracyChanged().");
    }

    public void onSensorChanged(SensorEvent event) {
        Log.i(TAG, "onSensorChanged().");
    }
}

