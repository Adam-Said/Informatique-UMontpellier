package com.hai811i.basicapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button callButton = findViewById(R.id.call);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                callFunc(view);
            }

            private void callFunc(View view) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button trainButton = findViewById(R.id.train);
        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                trainFunc(view);
            }

            private void trainFunc(View view) {
                Intent i = new Intent(HomeActivity.this, TrainActivity.class);
                startActivity(i);
            }
        });

        Button agendaButton = findViewById(R.id.agenda);
        agendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                agendaFunc(view);
            }

            private void agendaFunc(View view) {
                Intent i = new Intent(HomeActivity.this, AgendaActivity.class);
                startActivity(i);
            }
        });

    }
}
