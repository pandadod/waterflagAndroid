package com.example.waterflag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pseudobox = findViewById(R.id.eTpseudo);
                String pseudo = pseudobox.getText().toString();
                SharedPreferences sharePref = MainActivity.this.getSharedPreferences("com.example.waterflag", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharePref.edit();
                editor.putString("PSEUDO", pseudo);
                editor.apply();
                Intent intent = new Intent(MainActivity.this, Salle1.class);
                startActivity(intent);
            }
        });

    }
}
