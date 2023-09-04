package com.hai811i.tp2capteurs;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AccelerometerActivity extends AppCompatActivity {
    private boolean reliable = true;
    private final static double TRESHOLD_VAL = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        ConstraintLayout color_playground = findViewById(R.id.accelerometer_color_playground);

        SensorManager sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensor_manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensor_event) {
                if (reliable) {
                    for (int i = 0; i < sensor_event.values.length; i++) {
                        double val = sensor_event.values[0] + sensor_event.values[1] + sensor_event.values[2];
                        if (val > TRESHOLD_VAL) {
                            color_playground.setBackgroundColor(Color.RED);
                        } else if (val < -TRESHOLD_VAL) {
                            color_playground.setBackgroundColor(Color.GREEN);
                        } else {
                            color_playground.setBackgroundColor(Color.BLACK);
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
    }
}
