package com.hai811i.basicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Identified extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identified);
        Intent intent = getIntent();
        TextView name = findViewById(R.id.name_identified);
        TextView firstname = findViewById(R.id.firstname_identified);
        TextView tel = findViewById(R.id.tel_identified);
        TextView domain = findViewById(R.id.domain_identified);
        name.setText(intent.getStringExtra("name"));
        firstname.setText(intent.getStringExtra("firstname"));
        tel.setText(intent.getStringExtra("tel"));
        domain.setText(intent.getStringExtra("domain"));

        Button okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                okFunc(view);
            }

            private void okFunc(View view) {
                Intent i = new Intent(Identified.this, Call.class);
                TextView tel = findViewById(R.id.tel_identified);
                i.putExtra("number", tel.getText().toString());
                startActivity(i);
            }
        });
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                backFunc(view);
            }

            private void backFunc(View view) {
                finish();
            }
        });
    }
}
