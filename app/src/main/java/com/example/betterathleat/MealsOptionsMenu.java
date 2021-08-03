package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MealsOptionsMenu extends AppCompatActivity {
    private Button GenerateNewMenuButton;
    private Button Explanation;
    private Button GoBackToMenuPage;
    private Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_options_menu);

        GenerateNewMenuButton=(Button) findViewById(R.id.GenerateNewMenuButton);
        GoBackToMenuPage=(Button) findViewById(R.id.GoBackToMenuPage);
        Explanation=(Button) findViewById(R.id.Explanation_button);




        GenerateNewMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetMenuStuff();
                GoBackToMenuPage();
            }
        });



        GoBackToMenuPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoBackToMenuPage();

            }
        });

        Explanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExplanationPage();
            }
        });










    }

    public void ExplanationPage(){
        Intent intentExplanationPage= new Intent(this, AdvancedUserInfo.class);
        startActivity(intentExplanationPage);
        finish();
    }

    public void GoBackToMenuPage(){
        Intent intentGoBackToMenuPage= new Intent(this,FoodMenu.class);
        startActivity(intentGoBackToMenuPage);
        finish();
    }


    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public void Gson_Update_Data_Exercies_Array(ArrayList<Exercise> Value, String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public ArrayList<Exercise> get_shared_info_Gson_Exercise_Array(final String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        ArrayList<Exercise> ExercisesForWorkOut= new ArrayList<Exercise>();
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

            return(ExercisesForWorkOut);
        }
        Type type = new TypeToken<ArrayList<Exercise>>(){}.getType();
        ExercisesForWorkOut = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(ExercisesForWorkOut);
    }


    public void Gson_Empty_UpdateData(String Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

    }
    public void ResetMenuStuff(){


        updateData("0",MainActivity.NUMBEROFMEALS);
        Gson_Empty_UpdateData("0",MainActivity.THEMENU);
        Gson_Empty_UpdateData("0",MainActivity.THEMEALS);



    }
}