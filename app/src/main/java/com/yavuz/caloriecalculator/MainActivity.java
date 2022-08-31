package com.yavuz.caloriecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    double BMR;
    double calculatedBMR;
    int age, height, weight;
    SharedPreferences sharedPreferences;

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
        sharedPreferences = getApplicationContext().getSharedPreferences("com.yavuz.caloriecalculator", Context.MODE_PRIVATE);

        int BMRValue = sharedPreferences.getInt("BMHValue", 0);
        if(BMRValue != 0){
            resultText.setText("Your daily calorie needs : " + BMRValue);
        }
        else{
            resultText.setText("Your daily calorie needs : ");
        }
    }

    public void calculate(View view) {

        if (ageText.getText().toString().matches("") || heightText.getText().toString().matches("") || weightText.getText().toString().matches("")) {
            Message.massage(getApplicationContext(), "Please enter your age, height and weight correctly!");
        } else{
            calculatedBMR = userBMR();
        }

        resultText.setText("Your daily calorie needs : " +  String.format("%.2f", calculatedBMR));
        sharedPreferences.edit().putFloat("BMRValue", (float) calculatedBMR).apply();
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
        BMR = 0;
        int BMRValue = sharedPreferences.getInt("BMRValue", 0);

        if(BMRValue != 0){

            sharedPreferences.edit().remove("BMRValue").apply();
            resultText.setText("Your daily calorie needs : ");
        }
    }

    public void analyze(View view){

        double bmrAnalyze = userBMR();

        Intent intent = new Intent(this, Calculated.class);
        intent.putExtra("BMRValue", bmrAnalyze);
        startActivity(intent);

    }

    public double userBMR(){

        age = Integer.parseInt(ageText.getText().toString());
        height = Integer.parseInt(heightText.getText().toString());
        weight = Integer.parseInt(weightText.getText().toString());
        int checkedId = gender.getCheckedRadioButtonId();


        if (checkedId == -1) {
            Message.massage(getApplicationContext(), "Please select your gender!");
        }

        else {

            if (checkedId == R.id.female) {
                BMR = (10 * weight) + (6.25 * height) + (5 * age) + 5;
            }
            else if (checkedId == R.id.male) {
                BMR = (10 * weight) + (6.25 * height) + (5 * age) + 161;
            }
        }

        return BMR;
    }

}