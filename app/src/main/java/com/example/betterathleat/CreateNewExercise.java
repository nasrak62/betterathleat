package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreateNewExercise extends AppCompatActivity {
    private Gson gson=new Gson();
    private Button Main_Menu;
    private Button AddNewExerciseButton;
    private EditText FillNewMuscleName;
    private EditText FillNewExerciseName;
    private String Get_FillNewExerciseName;
    private String Get_FillNewMuscleName;
    private Spinner SpinnerNewExerciseType;
    private ArrayList<Exercise> GeneralExercises;
    private ArrayList<Exercise> OwnGeneralExercises;
    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_exercise);

        Main_Menu= (Button) findViewById(R.id.Main_Menu);
        AddNewExerciseButton= (Button) findViewById(R.id.AddNewExerciseButton);
        FillNewMuscleName= (EditText) findViewById(R.id.FillNewMuscleName);
        FillNewExerciseName= (EditText) findViewById(R.id.FillNewExerciseName);
        ArrayList<String> SpinnerArrayGeneralExercises = new ArrayList<String>();
        SpinnerArrayGeneralExercises.add("Big");
        SpinnerArrayGeneralExercises.add("Small");
        ArrayAdapter<String> SpinnerArrayGeneralExercisesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArrayGeneralExercises);
        SpinnerNewExerciseType = (Spinner) findViewById(R.id.SpinnerNewExerciseType);

        SpinnerNewExerciseType.setAdapter(SpinnerArrayGeneralExercisesAdapter);
        GeneralExercises=get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES);
        OwnGeneralExercises=get_shared_info_Gson_Exercise_Array(MainActivity.OWNGENERALEXERCISES);
        user=get_shared_info_Gson_User(MainActivity.PASSUSER);
        AddNewExerciseButton.setEnabled(false);



        AddNewExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Get_FillNewMuscleName=FillNewMuscleName.getText().toString();
                Get_FillNewExerciseName=FillNewExerciseName.getText().toString();



                if (!(Get_FillNewMuscleName.equals(""))  && !(Get_FillNewExerciseName.equals("")) && !(SpinnerNewExerciseType.getSelectedItem().toString().equals(""))  )
                {

                    Exercise X= new Exercise(Get_FillNewExerciseName,SpinnerNewExerciseType.getSelectedItem().toString(),Get_FillNewMuscleName,3,0.0,user.GetWeight(),user.GetPurpose());
                    if(!GeneralExercises.isEmpty()){
                        if(X.GetExerciseType().equals("Chest") || X.GetExerciseType().equals("Triceps") || X.GetExerciseType().equals("Biceps") || X.GetExerciseType().equals("Shoulders") || X.GetExerciseType().equals("Legs") || X.GetExerciseType().equals("Back") ){


                            if(!ExerciseInGeneralExercises(X.GetExerciseName(), GeneralExercises)){
                                GeneralExercises.add(X);
                                Gson_Update_Data_Exercies_Array(GeneralExercises,MainActivity.GENERALEXERCISES);

                            }
                            else{
                                Toast toast = Toast.makeText(getApplicationContext(),"Exercise Exist" , Toast.LENGTH_SHORT);
                                toast.show();
                            }



                        }


                    }

                    if(!OwnGeneralExercises.isEmpty()){
                        if(!ExerciseInGeneralExercises(X.GetExerciseName(), OwnGeneralExercises)){
                            OwnGeneralExercises.add(X);
                            Gson_Update_Data_Exercies_Array(OwnGeneralExercises,MainActivity.OWNGENERALEXERCISES);

                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(),"Exercise Exist" , Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }

                        if(OwnGeneralExercises.contains(X) || GeneralExercises.contains(X)){

                            Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Add" , Toast.LENGTH_SHORT);
                            toast.show();
                            FillNewMuscleName.setText("");
                            FillNewExerciseName.setText("");

                        }

                }

            }
        });

        FillNewMuscleName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(FillNewExerciseName.getText().toString().equals("")|| FillNewMuscleName.getText().toString().equals("")){
                    AddNewExerciseButton.setEnabled(false);
                }
                else{
                    AddNewExerciseButton.setEnabled(true);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        FillNewExerciseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(FillNewExerciseName.getText().toString().equals("") || FillNewMuscleName.getText().toString().equals("")){
                    AddNewExerciseButton.setEnabled(false);
                }
                else{
                    AddNewExerciseButton.setEnabled(true);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        Main_Menu.setOnClickListener(new View.OnClickListener() {
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

    public User get_shared_info_Gson_User( final String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        User Tempuser=new User("0",1.0,1.0,1,"0","0",1.0,"0",1,1, new String[]{"1", "1", "1", "1", "1"},1,1,"0",new String[]{"1", "1", "1", "1", "1"},false);
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

            updateData("0", Key);
            return(Tempuser);
        }
        Type type = new TypeToken<User>(){}.getType();
        Tempuser = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(Tempuser);
    }

    public void Gson_Update_Data_User(User Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public void Gson_Empty_UpdateData(String Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

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
    public Boolean ExerciseInGeneralExercises(String  ExerciseName, ArrayList<Exercise> Y){
        for(Exercise x : Y){
            if(x.GetExerciseName().equals(ExerciseName)){
                return  (true);
            }
        }
        return  (false);
    }

}