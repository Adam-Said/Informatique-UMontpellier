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
import java.util.List;

public class SensorListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        TextView sensor_list_title = findViewById(R.id.title_sensor_list);
        ListView sensors_list_view = findViewById(R.id.sensors_list);
        sensor_list_title.setText(R.string.sensor_list);

        SensorManager sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensor_list = sensor_manager.getSensorList(Sensor.TYPE_ALL);

        ArrayList<String> sensors_list_str = new ArrayList<>();
        int sensor_list_size =sensor_list.size();
        for(int i = 0 ; i < sensor_list_size; i++){
            sensors_list_str.add(sensor_list.get(i).getName());
        }
        ArrayAdapter<String> adapter_sensors_list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , sensors_list_str);


        sensors_list_view.setAdapter(adapter_sensors_list);
    }
}
