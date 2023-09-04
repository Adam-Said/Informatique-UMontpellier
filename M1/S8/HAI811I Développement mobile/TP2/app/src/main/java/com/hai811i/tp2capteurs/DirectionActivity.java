package com.hai811i.tp2capteurs;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DirectionActivity extends AppCompatActivity {

    private boolean reliable = true;
    private final static double TRESHOLD_VAL = 2.f;
    private long next_timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        next_timing = System.currentTimeMillis();
        TextView direction_value_tv = findViewById(R.id.direction_value);

        SensorManager sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensor_manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensor_event) {

                if (reliable && System.currentTimeMillis() > next_timing) {
                    next_timing = System.currentTimeMillis();
                    double val = Math.sqrt(sensor_event.values[0] * sensor_event.values[0] + sensor_event.values[1] * sensor_event.values[1] + sensor_event.values[2] * sensor_event.values[2]);
                    if (val > TRESHOLD_VAL) {
                        next_timing+=400;
                        int id_max_abs;
                        double val_x_abs, val_y_abs, val_z_abs;
                        val_x_abs = Math.abs(sensor_event.values[0]);
                        val_y_abs = Math.abs(sensor_event.values[1]);
                        val_z_abs = Math.abs(sensor_event.values[2]);
                        if (val_x_abs > val_y_abs && val_x_abs > val_z_abs) {
                            id_max_abs = 0;
                        } else if (val_y_abs > val_x_abs && val_y_abs > val_z_abs) {
                            id_max_abs = 1;
                        } else {
                            id_max_abs = 2;
                        }

                        switch (id_max_abs) {
                            case 0:
                                if (sensor_event.values[id_max_abs] > 0) {
                                    direction_value_tv.setText("➡");
                                } else {
                                    direction_value_tv.setText("⬅");
                                }
                                break;
                            case 1:
                                if (sensor_event.values[id_max_abs] > 0) {
                                    direction_value_tv.setText("⬆");
                                } else {
                                    direction_value_tv.setText("⬇");
                                }
                                break;
                            case 2:
                                if (sensor_event.values[id_max_abs] > 0) {
                                    direction_value_tv.setText("⤵");
                                } else {
                                    direction_value_tv.setText("⤴");
                                }
                                break;
                        }
                    } else {
                        direction_value_tv.setText("❌");
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
