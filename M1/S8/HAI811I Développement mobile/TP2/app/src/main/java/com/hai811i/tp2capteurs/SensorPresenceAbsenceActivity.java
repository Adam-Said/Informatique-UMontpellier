package com.hai811i.tp2capteurs;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SensorPresenceAbsenceActivity extends AppCompatActivity {


    public final static int SENSOR_PRESENCE = 0;
    public final static int SENSOR_ABSENCE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        TextView sensor_list_title = findViewById(R.id.title_sensor_list);
        ListView sensors_list_view = findViewById(R.id.sensors_list);

        HashMap<Integer,String> type_sensors = new HashMap<>();
        type_sensors.put(Sensor.TYPE_ACCELEROMETER,Sensor.STRING_TYPE_ACCELEROMETER);
        type_sensors.put(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED,Sensor.STRING_TYPE_ACCELEROMETER_UNCALIBRATED);
        type_sensors.put(Sensor.TYPE_AMBIENT_TEMPERATURE,Sensor.STRING_TYPE_AMBIENT_TEMPERATURE);
        type_sensors.put(Sensor.TYPE_GAME_ROTATION_VECTOR,Sensor.STRING_TYPE_GAME_ROTATION_VECTOR);
        type_sensors.put(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,Sensor.STRING_TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        type_sensors.put(Sensor.TYPE_GRAVITY,Sensor.STRING_TYPE_GRAVITY);
        type_sensors.put(Sensor.TYPE_GYROSCOPE,Sensor.STRING_TYPE_GYROSCOPE);
        type_sensors.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED,Sensor.STRING_TYPE_GYROSCOPE_UNCALIBRATED);
        type_sensors.put(Sensor.TYPE_HEART_BEAT,Sensor.STRING_TYPE_HEART_BEAT);
        type_sensors.put(Sensor.TYPE_HEART_RATE,Sensor.STRING_TYPE_HEART_RATE);
        type_sensors.put(Sensor.TYPE_HINGE_ANGLE,Sensor.STRING_TYPE_HINGE_ANGLE);
        type_sensors.put(Sensor.TYPE_LIGHT,Sensor.STRING_TYPE_LIGHT);
        type_sensors.put(Sensor.TYPE_LINEAR_ACCELERATION,Sensor.STRING_TYPE_LINEAR_ACCELERATION);
        type_sensors.put(Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT,Sensor.STRING_TYPE_LOW_LATENCY_OFFBODY_DETECT);
        type_sensors.put(Sensor.TYPE_MAGNETIC_FIELD,Sensor.STRING_TYPE_MAGNETIC_FIELD);
        type_sensors.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,Sensor.STRING_TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        type_sensors.put(Sensor.TYPE_MOTION_DETECT,Sensor.STRING_TYPE_MOTION_DETECT);


        int param_intent = Integer.parseInt(getIntent().getExtras().getString("param_intent"));

        ArrayList<String> sensors_list_str = new ArrayList<>();

        SensorManager sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        for (Map.Entry<Integer, String> entry : type_sensors.entrySet()) {
            Integer k = entry.getKey();
            String v = entry.getValue();
            Sensor curr_sensor=sensor_manager.getDefaultSensor(k);
            if (curr_sensor != null && param_intent == SENSOR_PRESENCE){
                sensors_list_str.add(v);
            } else if(curr_sensor == null && param_intent == SENSOR_ABSENCE){
                sensors_list_str.add(v);
            }
        }
        switch (param_intent){
            case SENSOR_PRESENCE:
                sensor_list_title.setText(R.string.sensor_presence);
                break;
            case SENSOR_ABSENCE:
                sensor_list_title.setText(R.string.sensor_absence);
                break;
        }
        ArrayAdapter<String> adapter_sensors_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , sensors_list_str);
        sensors_list_view.setAdapter(adapter_sensors_list);
    }
}
