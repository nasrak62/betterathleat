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

public class OwnProgramOptionsMenu extends AppCompatActivity {

    private Button CreateNewProgramButton;
    private Button Explanation;
    private Button GoBackToProgram;
    private Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_program_options_menu);
        CreateNewProgramButton=(Button) findViewById(R.id.CreateNewProgramButton);
        GoBackToProgram=(Button) findViewById(R.id.GoBackToProgramPage);
        Explanation=(Button) findViewById(R.id.Explanation_button);




        CreateNewProgramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetDaysForProgram();
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
        Intent intentGoBackToProgramPage= new Intent(this,CreateYourOwnProgram.class);
        startActivity(intentGoBackToProgramPage);
        finish();
    }

    public void ResetDaysForProgram(){

        Gson_Empty_UpdateData("0",MainActivity.OwnDaysFirst);
        Gson_Empty_UpdateData("0",MainActivity.OwnDaysSecond);
        Gson_Empty_UpdateData("0",MainActivity.OwnDaysThird);
        Gson_Empty_UpdateData("0",MainActivity.OwnDaysFourth);
        Gson_Empty_UpdateData("0",MainActivity.OwnDaysFifth);
        Gson_Empty_UpdateData("0",MainActivity.OwnDaysSixth);
        Gson_Empty_UpdateData("0",MainActivity.OwnDaysSeventh);




    }


    public void ResetExercisesForProgram(){

        Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutA);
        Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutB);
        Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutC);
        Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutD);
        Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutE);
        Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutF);
        Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutG);



    }

    public void ResetExerciseRelevantInfo(){
        ArrayList<Exercise> OwnExercisesForWorkOutA =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutA);
        ArrayList<Exercise> OwnExercisesForWorkOutB =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutB);
        ArrayList<Exercise> OwnExercisesForWorkOutC =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutC);
        ArrayList<Exercise> OwnExercisesForWorkOutD =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutD);
        ArrayList<Exercise> OwnExercisesForWorkOutE =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutE);
        ArrayList<Exercise> OwnExercisesForWorkOutF =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutF);
        ArrayList<Exercise> OwnExercisesForWorkOutG =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutG);
        ArrayList<Exercise> GeneralExercises=get_shared_info_Gson_Exercise_Array(MainActivity.OWNGENERALEXERCISES);
        if(!OwnExercisesForWorkOutA.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutA){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutA,MainActivity.OwnExercisesForWorkOutA);
        }

        if(!OwnExercisesForWorkOutB.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutB){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutB,MainActivity.OwnExercisesForWorkOutB);
        }


        if(!OwnExercisesForWorkOutC.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutC){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutC,MainActivity.OwnExercisesForWorkOutC);
        }


        if(!OwnExercisesForWorkOutD.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutD){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutD,MainActivity.OwnExercisesForWorkOutD);
        }


        if(!OwnExercisesForWorkOutE.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutE){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutE,MainActivity.OwnExercisesForWorkOutE);
        }

        if(!OwnExercisesForWorkOutF.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutF){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutF,MainActivity.OwnExercisesForWorkOutF);
        }


        if(!OwnExercisesForWorkOutG.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutG){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutG,MainActivity.OwnExercisesForWorkOutG);
        }


        if(!GeneralExercises.isEmpty()){

            Gson_Update_Data_Exercies_Array(GeneralExercises,MainActivity.OWNGENERALEXERCISES);
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




}