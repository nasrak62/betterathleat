package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QWeight extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText Fill_Weight;
    private String Get_weight;
    private Button SubmitWeight;
    private Double weight;
    private Button Next;
    private Button Main_Menu;
    private Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_weight);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        Fill_Weight= (EditText) findViewById(R.id.Fill_Weight);

        SubmitWeight=(Button) findViewById(R.id.Weight_button);
        Next= (Button) findViewById(R.id.Next1);
        Next.setEnabled(false);
        SubmitWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Get_weight=Fill_Weight.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if ((radioId == R.id.Kg)&&(!Get_weight.equals(""))) {
                    weight=Double.parseDouble( Get_weight);
                    weight = 2.2 * weight;
                    Get_weight = Double.toString((double) weight);
                }
                updateData(Get_weight,MainActivity.WEIGHT);
                ResetProjectKeyElements();
                if ((radioGroup.getCheckedRadioButtonId()!=-1)&&(!Get_weight.equals("")))
                {
                    Next.setEnabled(true);
                    Toast toast = Toast.makeText(getApplicationContext(),"Info Updated " , Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

        });
        Fill_Weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Next.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextActivity();


            }
        });
        Back= (Button) findViewById(R.id.Back1);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrevActivity();


            }
        });
        Main_Menu= (Button) findViewById(R.id.Main_Menu);
        Main_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain_Menu();


            }
        });

    }


   public void checkButton(View v){

        int radioId= radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
    }

    public void openNextActivity(){
        Intent intentopenNextActivity= new Intent(this, QHeight.class);
        startActivity(intentopenNextActivity);
        finish();
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();
    }
    public void openPrevActivity(){
        Intent intentopenPrevActivity= new Intent(this, QAge.class);
        startActivity(intentopenPrevActivity);
        finish();
    }
    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public void ResetProjectKeyElements(){
        UpdateUserIfPossible();
        ResetMenuStuff();
        UpdateExerciseWeight();

    }



    public void ResetMenuStuff(){


        updateData("0",MainActivity.NUMBEROFMEALS);
        Gson_Empty_UpdateData("0",MainActivity.THEMENU);
        Gson_Empty_UpdateData("0",MainActivity.THEMEALS);



    }
    public void Gson_Empty_UpdateData(String Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

    }

    private void UpdateUserIfPossible(){
        if(!get_shared_info_Gson_User( MainActivity.PASSUSER).GetName().equals("0")){
            User user=get_shared_info_Gson_User( MainActivity.PASSUSER);
            user.SetWeight(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
            Gson_Update_Data_User(user,MainActivity.PASSUSER);
        }




    }

    public String get_shared_info_string( final String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        if((sharedPreferences.getString(Key,"0" ).equals("")))
        {
            updateData("0",Key);
        }
        return(sharedPreferences.getString(Key,"0" ));
    }

    public User get_shared_info_Gson_User( final String Key){
        Gson gson=new Gson();
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
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }



    public void UpdateExerciseWeight(){
        ArrayList<Exercise> ExercisesForWorkOutA=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutA);
        ArrayList<Exercise> ExercisesForWorkOutB=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutB);
        ArrayList<Exercise> ExercisesForWorkOutC=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutC);
        ArrayList<Exercise> ExercisesForWorkOutD=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutD);
        ArrayList<Exercise> GeneralExercise=get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES);
        if(!GeneralExercise.isEmpty()){
            for (Exercise X :GeneralExercise){
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));

            }

            Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);
        }
        if(!ExercisesForWorkOutA.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutA) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutA,MainActivity.ExercisesForWorkOutA);
        }

        if(!ExercisesForWorkOutB.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutB) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutB,MainActivity.ExercisesForWorkOutB);
        }

        if(!ExercisesForWorkOutC.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutC) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutC,MainActivity.ExercisesForWorkOutC);
        }

        if(!ExercisesForWorkOutD.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutD) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
            }
            Gson_Update_Data_Exercies_Array(ExercisesForWorkOutD,MainActivity.ExercisesForWorkOutD);
        }




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
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
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
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
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
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
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
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
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
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
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
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
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
                X.WeightUpdate(StringToDouble(get_shared_info_string(MainActivity.WEIGHT)));
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

    public Double StringToDouble(String Key){

        return(Double.parseDouble(Key));

    }

    public void Gson_Update_Data_Exercies_Array(ArrayList<Exercise> Value,String Key){
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public ArrayList<Exercise> get_shared_info_Gson_Exercise_Array( final String Key){
        Gson gson=new Gson();
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
}