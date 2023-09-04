package com.hai811i.basicapp;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AgendaActivity extends AppCompatActivity {
    Button add;
    CalendarView cal;
    ListView list_events;
    Map<Integer, ArrayList> events = new HashMap<>();
    EditText add_events_text_view;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        add_events_text_view = findViewById(R.id.add_events_text_view);
        cal = findViewById(R.id.calendar);
        list_events = findViewById(R.id.list_events);

        adapter = new ArrayAdapter<>(AgendaActivity.this, android.R.layout.simple_spinner_item);
        list_events.setAdapter(adapter);


        ArrayList<String> tmpEvents = new ArrayList<>();
        tmpEvents.add("Rendu TP1 Dev Mobile");
        tmpEvents.add("Reunion TER");
        events.put(0, tmpEvents);

        tmpEvents = new ArrayList<>();
        tmpEvents.add("Devenir riche");
        tmpEvents.add("Mon anniv");
        events.put(1, tmpEvents);

        tmpEvents = new ArrayList<>();
        tmpEvents.add("RDV avec B. Gates");
        tmpEvents.add("J'ai plus d'idées");
        events.put(2, tmpEvents);

        cal.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            ArrayList<String> displayEvents = new ArrayList<>();
            displayEvents = events.get(dayOfMonth % 3);

            adapter = new ArrayAdapter<>(AgendaActivity.this, android.R.layout.simple_spinner_item, displayEvents);

            list_events.setAdapter(adapter);
        });


        add = findViewById(R.id.add_btn);

        add.setOnClickListener(v ->{
            String event_msg = add_events_text_view.getText().toString();
            if(event_msg.length() > 1){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String selectedDate = sdf.format(new Date(cal.getDate()));
                int day = Integer.parseInt(selectedDate.split("/")[0]) % 3;
                ArrayList<String> foo = new ArrayList<>();
                foo = events.get(day);
                foo.add(event_msg);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    events.replace(day,foo);
                }
                else {
                    events.remove(day);
                    events.put(day,foo);
                }
                add_events_text_view.setText("");
                Toast.makeText(AgendaActivity.this,"Event added succesfully !",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
