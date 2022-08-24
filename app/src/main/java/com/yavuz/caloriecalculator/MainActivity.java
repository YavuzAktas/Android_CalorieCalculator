package com.yavuz.caloriecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup gender;
    Button button, button1;
    EditText ageText, heightText, weightText;
    TextView resultText;
    double BMH;
    int age, height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gender = findViewById(R.id.gender);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        ageText = findViewById(R.id.ageText);
        heightText = findViewById(R.id.heightText);
        weightText = findViewById(R.id.weightText);
        resultText = findViewById(R.id.resultText);
    }

    public void calculate(View view) {

        if (ageText.getText().toString().matches("") || heightText.getText().toString().matches("") || weightText.getText().toString().matches("")) {
            Message.massage(getApplicationContext(), "Please enter your age, height and weight correctly!");
        }

        else{

            age = Integer.parseInt(ageText.getText().toString());
            height = Integer.parseInt(heightText.getText().toString());
            weight = Integer.parseInt(weightText.getText().toString());
            int checkedId = gender.getCheckedRadioButtonId();


            if (checkedId == -1) {
                Message.massage(getApplicationContext(), "Please select your gender!");
            }

            else {

                if (checkedId == R.id.female) {
                    BMH = (10 * weight) + (6.25 * height) + (5 * age) + 5;
                }
                else if (checkedId == R.id.male) {
                    BMH = (10 * weight) + (6.25 * height) + (5 * age) + 161;
                }
            }

        }

        resultText.setText("Your daily calorie needs : " + String.format("%.2f", BMH));

    }


    public void reset(View view){

        gender.clearCheck();
        ageText.setText("");
        heightText.setText("");
        weightText.setText("");
        resultText.setText("");
        age = 0;
        height = 0;
        weight = 0;
        BMH = 0;

    }
}