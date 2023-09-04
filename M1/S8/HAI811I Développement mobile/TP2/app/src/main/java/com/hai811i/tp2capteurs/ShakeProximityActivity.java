package com.hai811i.tp2capteurs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ShakeProximityActivity extends AppCompatActivity {

    private boolean reliable = true;
    private final static double SHAKE_THRESHOLD = 1500.;
    private final static double PROXIMITY_THRESHOLD = 3.;
    private long lastUpdate;
    private long lastTiming;
    private float last_x, last_y, last_z;
    private boolean torch_mode;
    private ImageView iv_proximity;
    SensorManager sensor_manager;
    SensorEventListener sel;
    Sensor accelerometer,proximity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_proximity);

        iv_proximity = findViewById(R.id.state_proximity);
        lastUpdate = System.currentTimeMillis();
        last_x = 0;
        last_y = 0;
        last_z = 0;
        torch_mode = false;

        sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensor_manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        proximity = sensor_manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensor_event) {

                if (reliable) {
                    if (sensor_event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                        Bitmap bm;
                        if (sensor_event.values[0] <= PROXIMITY_THRESHOLD) {
                            bm = BitmapFactory.decodeResource(getResources(), R.drawable.full);
                        } else {
                            bm = BitmapFactory.decodeResource(getResources(), R.drawable.none);
                        }
                        iv_proximity.setImageBitmap(bm);
                    } else if (sensor_event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                        long curTime = System.currentTimeMillis();
                        // only allow one update every 500ms.
                        if ((curTime - lastUpdate) > 500) {
                            long diffTime = (curTime - lastTiming);
                            lastTiming = curTime;
                            if (diffTime != 0) {

                                float x = sensor_event.values[0];
                                float y = sensor_event.values[1];
                                float z = sensor_event.values[2];

                                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                                if (speed > SHAKE_THRESHOLD) {
                                    lastUpdate = curTime;
                                    CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                                    String cameraId;
                                    try {
                                        cameraId = camManager.getCameraIdList()[0];
                                        camManager.setTorchMode(cameraId, !torch_mode);
                                        torch_mode = !torch_mode;
                                    } catch (CameraAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                                last_x = x;
                                last_y = y;
                                last_z = z;
                            }
                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                reliable = accuracy == SensorManager.SENSOR_STATUS_ACCURACY_HIGH || accuracy == SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM;
            }
        };

        boolean supported = sensor_manager.registerListener(sel, accelerometer, SensorManager.SENSOR_DELAY_UI);
        if (!supported) {
            sensor_manager.unregisterListener(sel, accelerometer);
        }
        supported = sensor_manager.registerListener(sel, proximity, SensorManager.SENSOR_DELAY_UI);
        if (!supported) {
            sensor_manager.unregisterListener(sel, proximity);
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        sensor_manager.unregisterListener(sel, proximity);
        sensor_manager.unregisterListener(sel, accelerometer);

    }
}
