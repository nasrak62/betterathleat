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

public class ProgramOptions extends AppCompatActivity {
    private Button GenerateAllButton;
    private Button GenerateDaysButton;
    private Button GenerateExercisesButton;
    private Button Explanation;
    private Button GoBackToProgram;
    private Gson gson=new Gson();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_options);
        GenerateAllButton=(Button) findViewById(R.id.GenerateAllButton);
        GenerateDaysButton=(Button) findViewById(R.id.GenerateDaysButton);
        GenerateExercisesButton=(Button) findViewById(R.id.GenerateExercisesButton);
        GoBackToProgram=(Button) findViewById(R.id.GoBackToProgramPage);
        Explanation=(Button) findViewById(R.id.Explanation_button);




        GenerateAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetDaysForProgram();
                ResetExerciseRelevantInfo();
                ResetExercisesForProgram();
            }
        });

        GenerateDaysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetDaysForProgram();
                ResetExerciseRelevantInfo();

            }
        });

        GenerateExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResetExerciseRelevantInfo();
                ResetExercisesForProgram();

            }
        });

        GoBackToProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoBackToProgramPage();

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

    public void GoBackToProgramPage(){
        Intent intentGoBackToProgramPage= new Intent(this,TheProgramPage.class);
        startActivity(intentGoBackToProgramPage);
        finish();
    }

    public void ResetDaysForProgram(){
        updateData("0",MainActivity.TRAINING1DAYPERWEEK1WORKOUT);

        updateData("0",MainActivity.TRAINING2DAYPERWEEK2WORKOUT1);
        updateData("0",MainActivity.TRAINING2DAYPERWEEK2WORKOUT2);

        updateData("0",MainActivity.TRAINING3DAYPERWEEK2WORKOUT1);
        updateData("0",MainActivity.TRAINING3DAYPERWEEK2WORKOUT2);
        updateData("0",MainActivity.TRAINING3DAYPERWEEK2WORKOUT3);

        updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT1);
        updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT2);
        updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT3);
        updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT4);


        updateData("0",MainActivity.TRAINING3DAYPERWEEK3WORKOUT1);
        updateData("0",MainActivity.TRAINING3DAYPERWEEK3WORKOUT2);
        updateData("0",MainActivity.TRAINING3DAYPERWEEK3WORKOUT3);

        updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT1);
        updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT2);
        updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT3);
        updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT4);


        updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT1);
        updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT2);
        updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT3);
        updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT4);
        updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT5);


    }


    public void ResetExercisesForProgram(){

        Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutA);
        Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutB);
        Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutC);
        Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutD);



    }

    public void ResetExerciseRelevantInfo(){
        ArrayList<Exercise> ExercisesForWorkOutA=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutA);
        ArrayList<Exercise> ExercisesForWorkOutB=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutB);
        ArrayList<Exercise> ExercisesForWorkOutC=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutC);
        ArrayList<Exercise>ExercisesForWorkOutD=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutD);
        ArrayList<Exercise> GeneralExercise=get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES);
        if(!GeneralExercise.isEmpty()){
            for (Exercise X :GeneralExercise){
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }

            Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);
        }
        if(!ExercisesForWorkOutA.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutA) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutA,MainActivity.ExercisesForWorkOutA);
        }

        if(!ExercisesForWorkOutB.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutB) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutB,MainActivity.ExercisesForWorkOutB);
        }

        if(!ExercisesForWorkOutC.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutC) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutC,MainActivity.ExercisesForWorkOutC);
        }

        if(!ExercisesForWorkOutD.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutD) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutD,MainActivity.ExercisesForWorkOutD);
        }

    }



    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public void Gson_Update_Data_Exercies_Array(ArrayList<Exercise> Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public ArrayList<Exercise> get_shared_info_Gson_Exercise_Array( final String Key){
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




}