package com.hai811i.basicapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Trip {
    String from;
    String to;
    String time;
}

public class TrainActivity extends AppCompatActivity {

    private ListView trainList;
    private Button searchButton;
    private EditText departure;
    private EditText arrival;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        String[] cities = {"Lyon", "Toulouse", "Montpellier", "Marseille", "Béziers"};
        Trip[] tripArray = new Trip[80];
        Random rand = new Random();

        for(int i = 0; i < 80; i++) {
            tripArray[i] = new Trip();
            tripArray[i].from = cities[rand.nextInt(cities.length)];
            tripArray[i].to = cities[rand.nextInt(cities.length)];
            tripArray[i].time = Integer.toString(rand.nextInt(24)) + ":" + Integer.toString(rand.nextInt(60));
            System.out.println(tripArray[i].time.toString() + " : " + tripArray[i].from.toString() + " | " + tripArray[i].to.toString());

        }

        trainList = findViewById(R.id.train_list);
        searchButton = findViewById(R.id.search);
        departure = findViewById(R.id.departure);
        arrival = findViewById(R.id.arrival);
        list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        trainList.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.removeAll(list);
                for (Trip trip : tripArray) {
                    if (trip.from.equals(departure.getText().toString()) && trip.to.equals(arrival.getText().toString())) {
                        list.add(trip.time + " : " + trip.from + " - " + trip.to);
                    }
                }
                if(list.size() == 0) {
                    list.add("No result");
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
}
