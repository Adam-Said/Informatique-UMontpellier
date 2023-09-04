package com.hai811i.tp2capteurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sensor_list_btn, sensor_presence_btn,sensor_absence_btn, accelerometer_btn,direction_btn,shake_proximity_btn,geolocalization_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensor_list_btn = findViewById(R.id.sensor_list_launcher);
        sensor_presence_btn = findViewById(R.id.sensor_presence_launcher);
        sensor_absence_btn = findViewById(R.id.sensor_absence_launcher);
        accelerometer_btn = findViewById(R.id.accelerometer_launcher);
        direction_btn = findViewById(R.id.direction_launcher);
        shake_proximity_btn = findViewById(R.id.shake_proximity_launcher);
        geolocalization_btn = findViewById(R.id.geolocalization_launcher);


        sensor_list_btn.setOnClickListener(view -> launch_Activity(SensorListActivity.class,""));
        sensor_presence_btn.setOnClickListener(view -> launch_Activity(SensorPresenceAbsenceActivity.class,String.valueOf(SensorPresenceAbsenceActivity.SENSOR_PRESENCE)));
        sensor_absence_btn.setOnClickListener(view -> launch_Activity(SensorPresenceAbsenceActivity.class,String.valueOf(SensorPresenceAbsenceActivity.SENSOR_ABSENCE)));
        accelerometer_btn.setOnClickListener(view -> launch_Activity(AccelerometerActivity.class,""));
        direction_btn.setOnClickListener(view -> launch_Activity(DirectionActivity.class,""));
        shake_proximity_btn.setOnClickListener(view -> launch_Activity(ShakeProximityActivity.class,""));
        geolocalization_btn.setOnClickListener(view -> launch_Activity(GeolocalizationActivity.class,""));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }


    public void launch_Activity(Class<?> class_to_launch, String param){
        Intent i = new Intent(this,class_to_launch);
        i.putExtra("param_intent",param);
        startActivity(i);
    }
}