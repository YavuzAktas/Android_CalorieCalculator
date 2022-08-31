package com.yavuz.caloriecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Calculated extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;

    double userBMR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);

        Intent reciveIntent = this.getIntent();
        userBMR = reciveIntent.getDoubleExtra("BMRValue", 0.0);

        textView.setText("" + userBMR);
        textView2.setText(""+ String.format("%.2f", userBMR*1.149));
        textView4.setText("" + String.format("%.2f", userBMR*1.220));
        textView5.setText("" + String.format("%.2f", userBMR*1.292));
        textView6.setText("" + String.format("%.2f", userBMR*1.437));
        textView7.setText("" + String.format("%.2f", userBMR*1.583));

    }

}