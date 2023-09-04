package com.hai811i.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button validationButton = findViewById(R.id.validate);
        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onValidate(view);
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



    public void onValidate(View view){
        EditText tel = findViewById(R.id.tel);
        if(tel.getText().toString().length() <= 1) {
            Toast.makeText(MainActivity.this,"Please provide a phone number",Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.validation_title);
            alertDialogBuilder.setMessage(R.string.validation_msg);
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent i = new Intent(MainActivity.this, Identified.class);
                    EditText name = findViewById(R.id.name);
                    EditText firstname = findViewById(R.id.firstname);
                    EditText tel = findViewById(R.id.tel);
                    EditText domain = findViewById(R.id.domain);
                    i.putExtra("name", name.getText().toString());
                    i.putExtra("firstname", firstname.getText().toString());
                    i.putExtra("tel", tel.getText().toString());
                    i.putExtra("domain", domain.getText().toString());
                    startActivity(i);
                }
            });

            alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this,R.string.no,Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }
}