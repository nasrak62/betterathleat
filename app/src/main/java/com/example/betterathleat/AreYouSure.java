package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AreYouSure extends AppCompatActivity {
    private Button Yes;
    private Button No;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_are_you_sure);



        Yes= (Button) findViewById(R.id.Yes);
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteOldData();
                openQ1();


            }
        });
        No= (Button) findViewById(R.id.No);
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain_Menu();


            }
        });
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openQ1(){
        Intent intentQ1= new Intent(this, QName.class);
        startActivity(intentQ1);
        finish();
    }
    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public void DeleteOldData(){
        updateData("0",MainActivity.NAME);
        updateData("0",MainActivity.WEIGHT);
        updateData("0",MainActivity.HEIGHT);
        updateData("0",MainActivity.AGE);
        updateData("0",MainActivity.HELP);
        updateData("0",MainActivity.PURPOSE);
        updateData("0",MainActivity.TRAININGTIME);
        updateData("0",MainActivity.TRAININGSTYLE);
        updateData("0",MainActivity.WORKOUTNUM);
        updateData("0",MainActivity.SERIOUSNESS);
        updateData("0",MainActivity.LIFTREC);
        updateData("0",MainActivity.RANGES);
        updateData("0",MainActivity.PROGRAMS);
        updateData("0",MainActivity.FOODIDEOLOGY);
        updateData("0",MainActivity.LIKEDFOODS);
        updateData("0",MainActivity.FINISHED);
    }
}