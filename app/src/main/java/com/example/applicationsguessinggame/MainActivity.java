package com.example.applicationsguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button noob;
    Button amateur;
    Button legend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noob = (Button) findViewById(R.id.noob_lvl);
        noob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, noobActivity.class));
            }
        });

        amateur = (Button) findViewById(R.id.amateur_lvl);
        amateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AmateurActivity.class));
            }
        });

        legend = (Button) findViewById(R.id.legend_lvl);
        legend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LegendActivity.class));
            }
        });
    }




}