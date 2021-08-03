package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class CreateYourOwnProgram extends AppCompatActivity {
    /////General variables/////
    private Gson gson=new Gson();
    private Button Options;
    private int Rotation;
    private int iForRotations=0;


    private Button ButtonSubmitRotationsToMemory;
    private ScrollView SHowWorkOutDetail;
    private TextView WorkOutMuscles;
    private TextView WorkOutName;
    private LinearLayout TitleTopOfScrollView;
    private LinearLayout ProgramScrollViewLinearLayout;
    private Button Main_Menu;



    private int mInterval = 1000*60; // 1 min by default, can be changed later
    private Handler mHandler;
    private static CreateYourOwnProgram instance;


    /////Days variables/////
    private Spinner SpinnerWhatWorkoutMonday;
    private Spinner SpinnerWhatWorkoutTuesday;
    private Spinner SpinnerWhatWorkoutWednesday;
    private Spinner SpinnerWhatWorkoutThursday;
    private Spinner SpinnerWhatWorkoutFriday;
    private Spinner SpinnerWhatWorkoutSaturday;
    private Spinner SpinnerWhatWorkoutSunday;
    private LinearLayout MondayLinearLayout;
    private LinearLayout TuesdayLinearLayout;
    private LinearLayout WednesdayLinearLayout;
    private LinearLayout ThursdayLinearLayout;
    private LinearLayout FridayLinearLayout;
    private LinearLayout SaturdayLinearLayout;
    private LinearLayout SundayLinearLayout;
    private ArrayList<String> RememberSunday = new ArrayList<String>();
    private ArrayList<String> RememberMonday = new ArrayList<String>();
    private ArrayList<String> RememberTuesday = new ArrayList<String>();
    private ArrayList<String> RememberWednesday = new ArrayList<String>();
    private ArrayList<String> RememberThursday = new ArrayList<String>();
    private ArrayList<String> RememberFriday = new ArrayList<String>();
    private ArrayList<String> RememberSaturday = new ArrayList<String>();
    private TextView MondayActivity;
    private TextView TuesdayActivity;
    private TextView WednesdayActivity;
    private TextView ThursdayActivity;
    private TextView FridayActivity;
    private TextView SaturdayActivity;
    private TextView SundayActivity;
    private Button ButtonMondayActivity;
    private Button ButtonTuesdayActivity;
    private Button ButtonWednesdayActivity;
    private Button ButtonThursdayActivity;
    private Button ButtonFridayActivity;
    private Button ButtonSaturdayActivity;
    private Button ButtonSundayActivity;
    private Button ButtonSubmitDaysToMemory;


    /////Exercises variables/////
    private  ArrayList<Exercise> GeneralExercises = new ArrayList<Exercise>();
    private ArrayList<String> WhatWorkOutsWasChosen = new ArrayList<String>();
    private ArrayList<Exercise> OwnExercisesForWorkOutA = new ArrayList<Exercise>();
    private ArrayList<Exercise> OwnExercisesForWorkOutB = new ArrayList<Exercise>();
    private ArrayList<Exercise> OwnExercisesForWorkOutC = new ArrayList<Exercise>();
    private ArrayList<Exercise> OwnExercisesForWorkOutD = new ArrayList<Exercise>();
    private ArrayList<Exercise> OwnExercisesForWorkOutE = new ArrayList<Exercise>();
    private ArrayList<Exercise> OwnExercisesForWorkOutF = new ArrayList<Exercise>();
    private ArrayList<Exercise> OwnExercisesForWorkOutG = new ArrayList<Exercise>();
    //private Spinner SpinnerGeneralExercises;
    private AutoCompleteTextView SpinnerGeneralExercises;
    private Button AddNewExercise;
    private Button Done;
    private Button RemoveLastAddedNewExercise;
    private String TrackWhichWorkOut;
    private EditText EditNewReps;
    private EditText EditNewWeight;
    private Button ClearNewWorkOutExercise;
    private Button SaveNewWorkOutExercise;
    private Button GoBAckTOWorkOut;
    private Button Advice;
    private Boolean CanGoOn=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own_program);
        instance = this;

        //////The Days View////
        MondayActivity= (TextView) findViewById(R.id.MondayActivity);
        TuesdayActivity= (TextView) findViewById(R.id.TuesdayActivity);
        WednesdayActivity= (TextView) findViewById(R.id.WednesdayActivity);
        ThursdayActivity= (TextView) findViewById(R.id.ThursdayActivity);
        FridayActivity= (TextView) findViewById(R.id.FridayActivity);
        SaturdayActivity= (TextView) findViewById(R.id.SaturdayActivity);
        SundayActivity= (TextView) findViewById(R.id.SundayActivity);
        ButtonMondayActivity= (Button) findViewById(R.id.ButtonMondayActivity);
        ButtonTuesdayActivity= (Button) findViewById(R.id.ButtonTuesdayActivity);
        ButtonWednesdayActivity= (Button) findViewById(R.id.ButtonWednesdayActivity);
        ButtonThursdayActivity= (Button) findViewById(R.id.ButtonThursdayActivity);
        ButtonFridayActivity= (Button) findViewById(R.id.ButtonFridayActivity);
        ButtonSaturdayActivity= (Button) findViewById(R.id.ButtonSaturdayActivity);
        ButtonSundayActivity= (Button) findViewById(R.id.ButtonSundayActivity);
        ButtonMondayActivity.setTextSize(10f);
        ButtonTuesdayActivity.setTextSize(10f);
        ButtonWednesdayActivity.setTextSize(10f);
        ButtonThursdayActivity.setTextSize(10f);
        ButtonFridayActivity.setTextSize(10f);
        ButtonSaturdayActivity.setTextSize(10f);
        ButtonSundayActivity.setTextSize(10f);
        Main_Menu= (Button) findViewById(R.id.Main_Menu);
        Options= (Button) findViewById(R.id.ProgramOptions);
        SHowWorkOutDetail =(ScrollView) findViewById(R.id.ProgramScrollView);
        WorkOutMuscles= (TextView) findViewById(R.id.WorkOutMuscles);
        WorkOutName= (TextView) findViewById(R.id.WorkOutName);
        TitleTopOfScrollView=(LinearLayout) findViewById(R.id.LinearLayout2layer);
        ProgramScrollViewLinearLayout=(LinearLayout) findViewById(R.id.ProgramScrollViewLinearLayout);





        Main_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain_Menu();


            }
        });

        Options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptions_Menu();


            }
        });


        if( (get_shared_info_Gson_String_Array(MainActivity.OwnDaysFirst)).isEmpty() && (get_shared_info_Gson_String_Array(MainActivity.OwnDaysSecond)).isEmpty() && (get_shared_info_Gson_String_Array(MainActivity.OwnDaysThird)).isEmpty() && (get_shared_info_Gson_String_Array(MainActivity.OwnDaysFourth)).isEmpty() && (get_shared_info_Gson_String_Array(MainActivity.OwnDaysFifth)).isEmpty() && (get_shared_info_Gson_String_Array(MainActivity.OwnDaysSixth)).isEmpty() && (get_shared_info_Gson_String_Array(MainActivity.OwnDaysSeventh)).isEmpty()){
            HowManyRotations();



        }
        else{
            Rotation= StringToInt(get_shared_info_string(MainActivity.ROTATIONS));
            UseKnownDays();


        }










    }


    public void AskUserForDays(){
        ProgramScrollViewLinearLayout.removeAllViews();
        MondayLinearLayout= new LinearLayout(this);
        MondayLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        MondayLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TuesdayLinearLayout= new LinearLayout(this);
        TuesdayLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TuesdayLinearLayout.setOrientation(LinearLayout.HORIZONTAL);


        WednesdayLinearLayout= new LinearLayout(this);
        WednesdayLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        WednesdayLinearLayout.setOrientation(LinearLayout.HORIZONTAL);


        ThursdayLinearLayout= new LinearLayout(this);
        ThursdayLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ThursdayLinearLayout.setOrientation(LinearLayout.HORIZONTAL);


        FridayLinearLayout= new LinearLayout(this);
        FridayLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        FridayLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        SaturdayLinearLayout= new LinearLayout(this);
        SaturdayLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        SaturdayLinearLayout.setOrientation(LinearLayout.HORIZONTAL);


        SundayLinearLayout= new LinearLayout(this);
        SundayLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        SundayLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        ArrayList<String> SpinnerArray1 = new ArrayList<String>();
        SpinnerArray1.add("Rest");
        SpinnerArray1.add("Train");
        ArrayAdapter<String> SpinnerArray1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArray1);
        ArrayList<String> SpinnerArray2 = new ArrayList<String>();
        SpinnerArray2.add("WorkOut A");
        SpinnerArray2.add("WorkOut B");
        SpinnerArray2.add("WorkOut C");
        SpinnerArray2.add("WorkOut D");
        SpinnerArray2.add("WorkOut E");
        SpinnerArray2.add("WorkOut F");
        SpinnerArray2.add("WorkOut G");
        ArrayAdapter<String> SpinnerArray2Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArray2);



       if(iForRotations<=Rotation){
           TextView WorkingOnRotation= new TextView(this);
           WorkingOnRotation.setText("Rotation "+ String.valueOf(iForRotations)+ " : ");
           ProgramScrollViewLinearLayout.addView(WorkingOnRotation);

            ////Monday////


            ProgramScrollViewLinearLayout.addView(MondayLinearLayout);
            TextView WhatToDoMonday= new TextView(this);
            WhatToDoMonday.setText("Monday: ");
            MondayLinearLayout.addView(WhatToDoMonday);
            Spinner SpinnerWhatToDoMonday = new Spinner(this);
            SpinnerWhatToDoMonday.setAdapter(SpinnerArray1Adapter);
            MondayLinearLayout.addView(SpinnerWhatToDoMonday);
            SpinnerWhatWorkoutMonday = new Spinner(this);
            SpinnerWhatWorkoutMonday.setAdapter(SpinnerArray2Adapter);
            SpinnerWhatToDoMonday.setTag("Monday");
            SpinnerWhatToDoMonday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    switch (Chosen){
                        case "Train":
                            MondayLinearLayout.addView(SpinnerWhatWorkoutMonday);
                            break;
                        case "Rest":
                            if(MondayLinearLayout.findViewWithTag("Monday")!= null){
                                MondayLinearLayout.removeView(SpinnerWhatWorkoutMonday);
                            }
                            if(iForRotations== RememberMonday.size()){
                                RememberMonday.add(iForRotations,"Rest");
                            }
                            RememberMonday.set(iForRotations,"Rest");
                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            SpinnerWhatWorkoutMonday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    if(iForRotations== RememberMonday.size()){
                        RememberMonday.set(iForRotations,Chosen);
                    }
                    RememberMonday.set(iForRotations,Chosen);




                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


////Tuesday////
            ProgramScrollViewLinearLayout.addView(TuesdayLinearLayout);
            TextView WhatToDoTuesday= new TextView(this);
            WhatToDoTuesday.setText("Tuesday: ");
            TuesdayLinearLayout.addView(WhatToDoTuesday);
            Spinner SpinnerWhatToDoTuesday = new Spinner(this);
            SpinnerWhatToDoTuesday.setAdapter(SpinnerArray1Adapter);
            TuesdayLinearLayout.addView(SpinnerWhatToDoTuesday);
            SpinnerWhatWorkoutTuesday = new Spinner(this);
            SpinnerWhatWorkoutTuesday.setAdapter(SpinnerArray2Adapter);
            SpinnerWhatToDoTuesday.setTag("Tuesday");
            SpinnerWhatToDoTuesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    switch (Chosen){
                        case "Train":
                            TuesdayLinearLayout.addView(SpinnerWhatWorkoutTuesday);
                            break;
                        case "Rest":
                            if(TuesdayLinearLayout.findViewWithTag("Tuesday")!= null){
                                TuesdayLinearLayout.removeView(SpinnerWhatWorkoutTuesday);
                            }
                            if(iForRotations== RememberTuesday.size()){
                                RememberTuesday.add(iForRotations,"Rest");
                            }
                            RememberTuesday.set(iForRotations,"Rest");
                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            SpinnerWhatWorkoutTuesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    if(iForRotations== RememberTuesday.size()){
                        RememberTuesday.add(iForRotations,Chosen);
                    }
                    RememberTuesday.set(iForRotations,Chosen);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });



            ////Wednesday////
            ProgramScrollViewLinearLayout.addView(WednesdayLinearLayout);
            TextView WhatToDoWednesday= new TextView(this);
            WhatToDoWednesday.setText("Wednesday: ");
            WednesdayLinearLayout.addView(WhatToDoWednesday);
            Spinner SpinnerWhatToDoWednesday = new Spinner(this);
            SpinnerWhatToDoWednesday.setAdapter(SpinnerArray1Adapter);
            WednesdayLinearLayout.addView(SpinnerWhatToDoWednesday);
            SpinnerWhatWorkoutWednesday = new Spinner(this);
            SpinnerWhatWorkoutWednesday.setAdapter(SpinnerArray2Adapter);
            SpinnerWhatToDoWednesday.setTag("Wednesday");
            SpinnerWhatToDoWednesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    switch (Chosen){
                        case "Train":
                            WednesdayLinearLayout.addView(SpinnerWhatWorkoutWednesday);
                            break;
                        case "Rest":
                            if(WednesdayLinearLayout.findViewWithTag("Wednesday")!= null){
                                WednesdayLinearLayout.removeView(SpinnerWhatWorkoutWednesday);
                            }
                            if(iForRotations== RememberWednesday.size()){
                                RememberWednesday.add(iForRotations,"Rest");
                            }
                            RememberWednesday.set(iForRotations,"Rest");
                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            SpinnerWhatWorkoutWednesday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    if(iForRotations== RememberWednesday.size()){
                        RememberWednesday.add(iForRotations,Chosen);
                    }
                    RememberWednesday.set(iForRotations,Chosen);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });


            ////Thursday////
            ProgramScrollViewLinearLayout.addView(ThursdayLinearLayout);
            TextView WhatToDoThursday= new TextView(this);
            WhatToDoThursday.setText("Thursday: ");
            ThursdayLinearLayout.addView(WhatToDoThursday);
            Spinner SpinnerWhatToDoThursday = new Spinner(this);
            SpinnerWhatToDoThursday.setAdapter(SpinnerArray1Adapter);
            ThursdayLinearLayout.addView(SpinnerWhatToDoThursday);
            SpinnerWhatWorkoutThursday = new Spinner(this);
            SpinnerWhatWorkoutThursday.setAdapter(SpinnerArray2Adapter);
            SpinnerWhatToDoThursday.setTag("Thursday");
            SpinnerWhatToDoThursday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    switch (Chosen){
                        case "Train":
                            ThursdayLinearLayout.addView(SpinnerWhatWorkoutThursday);
                            break;
                        case "Rest":
                            if(ThursdayLinearLayout.findViewWithTag("Thursday")!= null){
                                ThursdayLinearLayout.removeView(SpinnerWhatWorkoutThursday);
                            }
                            if(iForRotations==  RememberThursday.size()){
                                RememberThursday.add(iForRotations,"Rest");
                            }
                            RememberThursday.set(iForRotations,"Rest");
                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            SpinnerWhatWorkoutThursday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    if(iForRotations==  RememberThursday.size()){
                        RememberThursday.add(iForRotations,Chosen);
                    }
                    RememberThursday.set(iForRotations,Chosen);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });



            ////Friday////
            ProgramScrollViewLinearLayout.addView(FridayLinearLayout);
            TextView WhatToDoFriday= new TextView(this);
            WhatToDoFriday.setText("Friday: ");
            FridayLinearLayout.addView(WhatToDoFriday);
            Spinner SpinnerWhatToDoFriday = new Spinner(this);
            SpinnerWhatToDoFriday.setAdapter(SpinnerArray1Adapter);
            FridayLinearLayout.addView(SpinnerWhatToDoFriday);
            SpinnerWhatWorkoutFriday = new Spinner(this);
            SpinnerWhatWorkoutFriday.setAdapter(SpinnerArray2Adapter);
            SpinnerWhatToDoFriday.setTag("Friday");
            SpinnerWhatToDoFriday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    switch (Chosen){
                        case "Train":
                            FridayLinearLayout.addView(SpinnerWhatWorkoutFriday);
                            break;
                        case "Rest":
                            if(FridayLinearLayout.findViewWithTag("Friday")!= null){
                                FridayLinearLayout.removeView(SpinnerWhatWorkoutFriday);
                            }
                            if(iForRotations== RememberFriday.size()){
                                RememberFriday.add(iForRotations,"Rest");
                            }
                            RememberFriday.set(iForRotations,"Rest");
                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            SpinnerWhatWorkoutFriday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    if(iForRotations==  RememberFriday.size()){
                        RememberFriday.add(iForRotations,Chosen);
                    }
                    RememberFriday.set(iForRotations,Chosen);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });



            ////Saturday////
            ProgramScrollViewLinearLayout.addView(SaturdayLinearLayout);
            TextView WhatToDoSaturday= new TextView(this);
            WhatToDoSaturday.setText("Saturday: ");
            SaturdayLinearLayout.addView(WhatToDoSaturday);
            Spinner SpinnerWhatToDoSaturday = new Spinner(this);
            SpinnerWhatToDoSaturday.setAdapter(SpinnerArray1Adapter);
            SaturdayLinearLayout.addView(SpinnerWhatToDoSaturday);
            SpinnerWhatWorkoutSaturday = new Spinner(this);
            SpinnerWhatWorkoutSaturday.setAdapter(SpinnerArray2Adapter);
            SpinnerWhatToDoSaturday.setTag("Saturday");
            SpinnerWhatToDoSaturday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    switch (Chosen){
                        case "Train":
                            SaturdayLinearLayout.addView(SpinnerWhatWorkoutSaturday);
                            break;
                        case "Rest":
                            if(SaturdayLinearLayout.findViewWithTag("Saturday")!= null){
                                SaturdayLinearLayout.removeView(SpinnerWhatWorkoutSaturday);
                            }
                            if(iForRotations== RememberSaturday.size()){
                                RememberSaturday.add(iForRotations,"Rest");
                            }
                            RememberSaturday.set(iForRotations,"Rest");
                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            SpinnerWhatWorkoutSaturday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    if(iForRotations== RememberSaturday.size()){
                        RememberSaturday.add(iForRotations,Chosen);
                    }
                    RememberSaturday.set(iForRotations,Chosen);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });



            ////Sunday////
            ProgramScrollViewLinearLayout.addView(SundayLinearLayout);
            TextView WhatToDoSunday= new TextView(this);
            WhatToDoSunday.setText("Sunday: ");
            SundayLinearLayout.addView(WhatToDoSunday);
            Spinner SpinnerWhatToDoSunday = new Spinner(this);
            SpinnerWhatToDoSunday.setAdapter(SpinnerArray1Adapter);
            SundayLinearLayout.addView(SpinnerWhatToDoSunday);
            SpinnerWhatWorkoutSunday = new Spinner(this);
            SpinnerWhatWorkoutSunday.setAdapter(SpinnerArray2Adapter);
            SpinnerWhatToDoSunday.setTag("Sunday");
            SpinnerWhatToDoSunday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    switch (Chosen){
                        case "Train":
                            SundayLinearLayout.addView(SpinnerWhatWorkoutSunday);
                            break;
                        case "Rest":
                            if(SundayLinearLayout.findViewWithTag("Sunday")!= null){
                                SundayLinearLayout.removeView(SpinnerWhatWorkoutSunday);
                            }
                            if(iForRotations== RememberSunday.size()){
                                RememberSunday.add(iForRotations,"Rest");
                            }
                            RememberSunday.set(iForRotations,"Rest");
                            break;

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            SpinnerWhatWorkoutSunday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String Chosen=parentView.getSelectedItem().toString();
                    if(iForRotations== RememberSunday.size()){
                        RememberSunday.add(iForRotations,Chosen);
                    }
                    RememberSunday.set(iForRotations,Chosen);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });




            ButtonSubmitDaysToMemory= new Button(this);
            ButtonSubmitDaysToMemory.setText("Save My Choices");
            ProgramScrollViewLinearLayout.addView(ButtonSubmitDaysToMemory);
            ButtonSubmitDaysToMemory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson_Update_Data_String_Array(RememberMonday,MainActivity.OwnDaysFirst);
                    Gson_Update_Data_String_Array(RememberTuesday,MainActivity.OwnDaysSecond);
                    Gson_Update_Data_String_Array(RememberWednesday,MainActivity.OwnDaysThird);
                    Gson_Update_Data_String_Array(RememberThursday,MainActivity.OwnDaysFourth);
                    Gson_Update_Data_String_Array(RememberFriday,MainActivity.OwnDaysFifth);
                    Gson_Update_Data_String_Array(RememberSaturday,MainActivity.OwnDaysSixth);
                    Gson_Update_Data_String_Array(RememberSunday,MainActivity.OwnDaysSeventh);
                    iForRotations++;
                    if(iForRotations>Rotation){
                        UseKnownDays();
                    }
                    else if(iForRotations<=Rotation){
                        AskUserForDays();
                    }

                }
            });





        }









    }



    private void UseKnownDays(){
        Calendar today = Calendar.getInstance();
        mHandler = new Handler();
        startRepeatingTask();

        switch(Rotation){
            case 0:
                String RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(0);
                switch (RemeberedChocieDays){
                    case "Rest":
                        SetRestDay("Monday");
                        break;
                    case "WorkOut A":
                        TrainOnMonday("A");
                        break;
                    case "WorkOut B":
                        TrainOnMonday("B");
                        break;
                    case "WorkOut C":
                        TrainOnMonday("C");
                        break;
                    case "WorkOut D":
                        TrainOnMonday("D");
                        break;
                    case "WorkOut E":
                        TrainOnMonday("E");
                        break;
                    case "WorkOut F":
                        TrainOnMonday("F");
                        break;
                    case "WorkOut G":
                        TrainOnMonday("G");
                        break;

                }


                RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(0);
                switch (RemeberedChocieDays){
                    case "Rest":
                        SetRestDay("Tuesday");
                        break;
                    case "WorkOut A":
                        TrainOnTuesday("A");
                        break;
                    case "WorkOut B":
                        TrainOnTuesday("B");
                        break;
                    case "WorkOut C":
                        TrainOnTuesday("C");
                        break;
                    case "WorkOut D":
                        TrainOnTuesday("D");
                        break;
                    case "WorkOut E":
                        TrainOnTuesday("E");
                        break;
                    case "WorkOut F":
                        TrainOnTuesday("F");
                        break;
                    case "WorkOut G":
                        TrainOnTuesday("G");
                        break;

                }


                RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(0);
                switch (RemeberedChocieDays){
                    case "Rest":
                        SetRestDay("Wednesday");
                        break;
                    case "WorkOut A":
                        TrainOnWednesday("A");
                        break;
                    case "WorkOut B":
                        TrainOnWednesday("B");
                        break;
                    case "WorkOut C":
                        TrainOnWednesday("C");
                        break;
                    case "WorkOut D":
                        TrainOnWednesday("D");
                        break;
                    case "WorkOut E":
                        TrainOnWednesday("E");
                        break;
                    case "WorkOut F":
                        TrainOnWednesday("F");
                        break;
                    case "WorkOut G":
                        TrainOnWednesday("G");
                        break;

                }


                RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(0);
                switch (RemeberedChocieDays){
                    case "Rest":
                        SetRestDay("Thursday");
                        break;
                    case "WorkOut A":
                        TrainOnThursday("A");
                        break;
                    case "WorkOut B":
                        TrainOnThursday("B");
                        break;
                    case "WorkOut C":
                        TrainOnThursday("C");
                        break;
                    case "WorkOut D":
                        TrainOnThursday("D");
                        break;
                    case "WorkOut E":
                        TrainOnThursday("E");
                        break;
                    case "WorkOut F":
                        TrainOnThursday("F");
                        break;
                    case "WorkOut G":
                        TrainOnThursday("G");
                        break;

                }


                RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(0);
                switch (RemeberedChocieDays){
                    case "Rest":
                        SetRestDay("Friday");
                        break;
                    case "WorkOut A":
                        TrainOnFriday("A");
                        break;
                    case "WorkOut B":
                        TrainOnFriday("B");
                        break;
                    case "WorkOut C":
                        TrainOnFriday("C");
                        break;
                    case "WorkOut D":
                        TrainOnFriday("D");
                        break;
                    case "WorkOut E":
                        TrainOnFriday("E");
                        break;
                    case "WorkOut F":
                        TrainOnFriday("F");
                        break;
                    case "WorkOut G":
                        TrainOnFriday("G");
                        break;

                }



                RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(0);
                switch (RemeberedChocieDays){
                    case "Rest":
                        SetRestDay("Saturday");
                        break;
                    case "WorkOut A":
                        TrainOnSaturday("A");
                        break;
                    case "WorkOut B":
                        TrainOnSaturday("B");
                        break;
                    case "WorkOut C":
                        TrainOnSaturday("C");
                        break;
                    case "WorkOut D":
                        TrainOnSaturday("D");
                        break;
                    case "WorkOut E":
                        TrainOnSaturday("E");
                        break;
                    case "WorkOut F":
                        TrainOnSaturday("F");
                        break;
                    case "WorkOut G":
                        TrainOnSaturday("G");
                        break;

                }




                RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(0);
                switch (RemeberedChocieDays){
                    case "Rest":
                        SetRestDay("Sunday");
                        break;
                    case "WorkOut A":
                        TrainOnSunday("A");
                        break;
                    case "WorkOut B":
                        TrainOnSunday("B");
                        break;
                    case "WorkOut C":
                        TrainOnSunday("C");
                        break;
                    case "WorkOut D":
                        TrainOnSunday("D");
                        break;
                    case "WorkOut E":
                        TrainOnSunday("E");
                        break;
                    case "WorkOut F":
                        TrainOnSunday("F");
                        break;
                    case "WorkOut G":
                        TrainOnSunday("G");
                        break;

                }

                break;
            case 1:
                switch(today.get(Calendar.WEEK_OF_MONTH)){
                    case 1:
                    case 3:

                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }


                        break;

                    case 2:
                    case 4:

                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }



                        break;


                }






                break;
            case 2:

                switch(today.get(Calendar.WEEK_OF_MONTH)){
                    case 1:
                    case 4:

                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }



                        break;

                    case 2:


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }




                        break;
                    case 3:



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }












                        break;




                }





                break;
            case 3:
                switch(today.get(Calendar.WEEK_OF_MONTH)){
                    case 1:

                         RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(0);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }



                        break;

                    case 2:

                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(1);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }





                        break;
                    case 3:


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(2);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }






                        break;

                    case 4:
                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFirst).get(3);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Monday");
                                break;
                            case "WorkOut A":
                                TrainOnMonday("A");
                                break;
                            case "WorkOut B":
                                TrainOnMonday("B");
                                break;
                            case "WorkOut C":
                                TrainOnMonday("C");
                                break;
                            case "WorkOut D":
                                TrainOnMonday("D");
                                break;
                            case "WorkOut E":
                                TrainOnMonday("E");
                                break;
                            case "WorkOut F":
                                TrainOnMonday("F");
                                break;
                            case "WorkOut G":
                                TrainOnMonday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSecond).get(3);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Tuesday");
                                break;
                            case "WorkOut A":
                                TrainOnTuesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnTuesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnTuesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnTuesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnTuesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnTuesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnTuesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysThird).get(3);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Wednesday");
                                break;
                            case "WorkOut A":
                                TrainOnWednesday("A");
                                break;
                            case "WorkOut B":
                                TrainOnWednesday("B");
                                break;
                            case "WorkOut C":
                                TrainOnWednesday("C");
                                break;
                            case "WorkOut D":
                                TrainOnWednesday("D");
                                break;
                            case "WorkOut E":
                                TrainOnWednesday("E");
                                break;
                            case "WorkOut F":
                                TrainOnWednesday("F");
                                break;
                            case "WorkOut G":
                                TrainOnWednesday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFourth).get(3);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Thursday");
                                break;
                            case "WorkOut A":
                                TrainOnThursday("A");
                                break;
                            case "WorkOut B":
                                TrainOnThursday("B");
                                break;
                            case "WorkOut C":
                                TrainOnThursday("C");
                                break;
                            case "WorkOut D":
                                TrainOnThursday("D");
                                break;
                            case "WorkOut E":
                                TrainOnThursday("E");
                                break;
                            case "WorkOut F":
                                TrainOnThursday("F");
                                break;
                            case "WorkOut G":
                                TrainOnThursday("G");
                                break;

                        }


                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysFifth).get(3);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Friday");
                                break;
                            case "WorkOut A":
                                TrainOnFriday("A");
                                break;
                            case "WorkOut B":
                                TrainOnFriday("B");
                                break;
                            case "WorkOut C":
                                TrainOnFriday("C");
                                break;
                            case "WorkOut D":
                                TrainOnFriday("D");
                                break;
                            case "WorkOut E":
                                TrainOnFriday("E");
                                break;
                            case "WorkOut F":
                                TrainOnFriday("F");
                                break;
                            case "WorkOut G":
                                TrainOnFriday("G");
                                break;

                        }



                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSixth).get(3);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Saturday");
                                break;
                            case "WorkOut A":
                                TrainOnSaturday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSaturday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSaturday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSaturday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSaturday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSaturday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSaturday("G");
                                break;

                        }




                        RemeberedChocieDays =get_shared_info_Gson_String_Array( MainActivity.OwnDaysSeventh).get(3);
                        switch (RemeberedChocieDays){
                            case "Rest":
                                SetRestDay("Sunday");
                                break;
                            case "WorkOut A":
                                TrainOnSunday("A");
                                break;
                            case "WorkOut B":
                                TrainOnSunday("B");
                                break;
                            case "WorkOut C":
                                TrainOnSunday("C");
                                break;
                            case "WorkOut D":
                                TrainOnSunday("D");
                                break;
                            case "WorkOut E":
                                TrainOnSunday("E");
                                break;
                            case "WorkOut F":
                                TrainOnSunday("F");
                                break;
                            case "WorkOut G":
                                TrainOnSunday("G");
                                break;

                        }
                        break;


                }
                break;

        }


        GeneralExercises=get_shared_info_Gson_Exercise_Array(MainActivity.OWNGENERALEXERCISES);
        if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutA).isEmpty() && get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutB).isEmpty() && get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutC).isEmpty() && get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutD).isEmpty() && get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutE).isEmpty() && get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutF).isEmpty() && get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutG).isEmpty() ){
            WhatWorkOutsWasUsedInTheProgramAndSetTheWorkOuts();

        }
        else{
            OwnExercisesForWorkOutA =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutA);
            OwnExercisesForWorkOutB =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutB);
            OwnExercisesForWorkOutC =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutC);
            OwnExercisesForWorkOutD =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutD);
            OwnExercisesForWorkOutE =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutE);
            OwnExercisesForWorkOutF =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutF);
            OwnExercisesForWorkOutG =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutG);
        }







    }

    private void HowManyRotations(){
        ProgramScrollViewLinearLayout.removeAllViews();
        TextView RotationNumber= new TextView(this);
        RotationNumber.setText("How Many Rotations: ");
        ProgramScrollViewLinearLayout.addView(RotationNumber);
        ArrayList<String> SpinnerArrayRotations = new ArrayList<String>();
        SpinnerArrayRotations.add("0");
        SpinnerArrayRotations.add("1");
        SpinnerArrayRotations.add("2");
        SpinnerArrayRotations.add("3");
        ArrayAdapter<String> SpinnerArrayRotationsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArrayRotations);
        Spinner SpinnerRotations = new Spinner(this);
        SpinnerRotations.setAdapter(SpinnerArrayRotationsAdapter);
        ProgramScrollViewLinearLayout.addView(SpinnerRotations);
        TextView RotationExplanation= new TextView(this);
        RotationExplanation.setText("No Rotation: \n Week One: \n WorkOut A -> WorkOut B -> WorkOut A \n Week Two: \n WorkOut A -> WorkOut B -> WorkOut A \n " +
                "With Rotation: \n Week One: \n WorkOut A -> WorkOut B -> WorkOut A \n Week Two: \n WorkOut B -> WorkOut A -> WorkOut B \n");
        ProgramScrollViewLinearLayout.addView(RotationExplanation);


        SpinnerRotations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String Chosen=parentView.getSelectedItem().toString();
                switch (Chosen){
                    case "0":
                        Rotation=0;
                        break;
                    case "1":
                        Rotation=1;
                        break;
                    case "2":
                        Rotation=2;
                        break;
                    case "3":
                        Rotation=3;
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        ButtonSubmitRotationsToMemory= new Button(this);
        ButtonSubmitRotationsToMemory.setText("Save My Choices");
        ProgramScrollViewLinearLayout.addView(ButtonSubmitRotationsToMemory);
        ButtonSubmitRotationsToMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(String.valueOf(Rotation),MainActivity.ROTATIONS);
                AskUserForDays();


            }
        });


    }







    public void WhatWorkOutsWasUsedInTheProgramAndSetTheWorkOuts(){
        for(String X: RememberMonday ){
            if(!WhatWorkOutsWasChosen.contains(X)&& !X.equals("Rest")){
                WhatWorkOutsWasChosen.add(X);
            }
        }
        for(String X: RememberTuesday ){
            if(!WhatWorkOutsWasChosen.contains(X)&& !X.equals("Rest")){
                WhatWorkOutsWasChosen.add(X);
            }
        }
        for(String X: RememberWednesday ){
            if(!WhatWorkOutsWasChosen.contains(X)&& !X.equals("Rest")){
                WhatWorkOutsWasChosen.add(X);
            }
        }
        for(String X: RememberThursday ){
            if(!WhatWorkOutsWasChosen.contains(X)&& !X.equals("Rest")){
                WhatWorkOutsWasChosen.add(X);
            }
        }
        for(String X: RememberFriday ){
            if(!WhatWorkOutsWasChosen.contains(X)&& !X.equals("Rest")){
                WhatWorkOutsWasChosen.add(X);
            }
        }
        for(String X: RememberSaturday ){
            if(!WhatWorkOutsWasChosen.contains(X)&& !X.equals("Rest")){
                WhatWorkOutsWasChosen.add(X);
            }
        }
        for(String X: RememberSunday ){
            if(!WhatWorkOutsWasChosen.contains(X) && !X.equals("Rest")){
                WhatWorkOutsWasChosen.add(X);
            }
        }

        ChooseExercisesForWorkOut();








    }



    public void ChooseExercisesForWorkOut(){
        Toast toast = Toast.makeText(getApplicationContext(),"Went To Build Workout", Toast.LENGTH_SHORT);
        toast.show();


        if(!WhatWorkOutsWasChosen.isEmpty() ){
           String X=WhatWorkOutsWasChosen.get(0);
            switch (X){
                case "WorkOut A":
                    if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutA).isEmpty()){
                        BuildWorkOutArray("A");
                        WhatWorkOutsWasChosen.remove(0);
                    }
                    else{
                        ProgramScrollViewLinearLayout.removeAllViews();
                    }


                    break;
                case "WorkOut B":
                    if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutB).isEmpty()){
                        BuildWorkOutArray("B");
                        WhatWorkOutsWasChosen.remove(0);
                    }

                    else{
                        ProgramScrollViewLinearLayout.removeAllViews();
                    }


                    break;
                case "WorkOut C":
                    if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutC).isEmpty()){
                        BuildWorkOutArray("C");
                        WhatWorkOutsWasChosen.remove(0);
                    }

                    else{
                        ProgramScrollViewLinearLayout.removeAllViews();
                    }


                    break;
                case "WorkOut D":
                    if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutD).isEmpty()){
                        BuildWorkOutArray("D");
                        WhatWorkOutsWasChosen.remove(0);
                    }
                    else{
                        ProgramScrollViewLinearLayout.removeAllViews();
                    }


                    break;
                case "WorkOut E":
                    if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutE).isEmpty()){
                        BuildWorkOutArray("E");
                        WhatWorkOutsWasChosen.remove(0);
                    }
                    else{
                        ProgramScrollViewLinearLayout.removeAllViews();
                    }


                    break;
                case "WorkOut F":
                    if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutF).isEmpty()){
                        BuildWorkOutArray("F");
                        WhatWorkOutsWasChosen.remove(0);
                    }
                    else{
                        ProgramScrollViewLinearLayout.removeAllViews();
                    }


                    break;
                case "WorkOut G":
                    if(get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutG).isEmpty()){
                        BuildWorkOutArray("G");
                        WhatWorkOutsWasChosen.remove(0);
                    }
                    else{
                        ProgramScrollViewLinearLayout.removeAllViews();
                    }



                    break;


            }



        }
        else{
            ProgramScrollViewLinearLayout.removeAllViews();
        }







    }






    public void BuildWorkOutArray(String Token){
        ProgramScrollViewLinearLayout.removeAllViews();
        TextView AddExercisesForWorkOut= new TextView(this);
        AddExercisesForWorkOut.setText("Add Exercises For WorkOut " + Token +":");
        ProgramScrollViewLinearLayout.addView(AddExercisesForWorkOut);



        ArrayList<String> AutoCompleteTextArrayGeneralMuscles = new ArrayList<String>();
        for(Exercise x: GeneralExercises) {
            if(!AutoCompleteTextArrayGeneralMuscles.contains(x.GetMuscleName())){
                AutoCompleteTextArrayGeneralMuscles.add(x.GetMuscleName());
            }
        }



        ArrayList<String> SpinnerArrayGeneralExercises = new ArrayList<String>();
        for(String MuscleName: AutoCompleteTextArrayGeneralMuscles){
            SpinnerArrayGeneralExercises.add(MuscleName+" :\n");
            for(Exercise x: GeneralExercises) {
                if(!SpinnerArrayGeneralExercises.contains(x.GetExerciseName()) && x.GetMuscleName().equals(MuscleName)  ){
                    SpinnerArrayGeneralExercises.add(x.GetExerciseName());
                }
            }

        }



        ArrayAdapter<String> SpinnerArrayGeneralExercisesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SpinnerArrayGeneralExercises);
        SpinnerGeneralExercises= new AutoCompleteTextView(this);
        SpinnerGeneralExercises.setAdapter(SpinnerArrayGeneralExercisesAdapter);
        SpinnerGeneralExercises.setThreshold(0);
        SpinnerGeneralExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpinnerGeneralExercises.showDropDown();
            }
        });



        ProgramScrollViewLinearLayout.addView(SpinnerGeneralExercises);





        AddNewExercise= new Button(this);
        AddNewExercise.setText("Add Selected Exercise");
        ProgramScrollViewLinearLayout.addView(AddNewExercise);
        AddNewExercise.setTag("Add New Exercise For WorkOut "+ Token);


        RemoveLastAddedNewExercise= new Button(this);
        RemoveLastAddedNewExercise.setText("Remove Last Added Exercise");
        ProgramScrollViewLinearLayout.addView(RemoveLastAddedNewExercise);
        RemoveLastAddedNewExercise.setTag("Remove Last Added Exercise "+ Token);

        Done= new Button(this);
        Done.setText("Done");
        ProgramScrollViewLinearLayout.addView(Done);
        Done.setEnabled(false);
        RemoveLastAddedNewExercise.setEnabled(false);

        switch (Token){
            case "A":
                if(OwnExercisesForWorkOutA.isEmpty()){
                    Done.setEnabled(false);
                    RemoveLastAddedNewExercise.setEnabled(false);
                }
                else{
                    Done.setEnabled(true);
                    RemoveLastAddedNewExercise.setEnabled(true);
                }

                break;

            case "B":
                if(OwnExercisesForWorkOutB.isEmpty()){
                    Done.setEnabled(false);
                    RemoveLastAddedNewExercise.setEnabled(false);
                }
                else{
                    Done.setEnabled(true);
                    RemoveLastAddedNewExercise.setEnabled(true);
                }

                break;

            case "C":
                if(OwnExercisesForWorkOutC.isEmpty()){
                    Done.setEnabled(false);
                    RemoveLastAddedNewExercise.setEnabled(false);
                }
                else{
                    Done.setEnabled(true);
                    RemoveLastAddedNewExercise.setEnabled(true);
                }

                break;

            case "D":
                if(OwnExercisesForWorkOutD.isEmpty()){
                    Done.setEnabled(false);
                    RemoveLastAddedNewExercise.setEnabled(false);
                }
                else{
                    Done.setEnabled(true);
                    RemoveLastAddedNewExercise.setEnabled(true);
                }

                break;


            case "E":
                if(OwnExercisesForWorkOutE.isEmpty()){
                    Done.setEnabled(false);
                    RemoveLastAddedNewExercise.setEnabled(false);
                }
                else{
                    Done.setEnabled(true);
                    RemoveLastAddedNewExercise.setEnabled(true);
                }
                break;

            case "F":
                if(OwnExercisesForWorkOutF.isEmpty()){
                    Done.setEnabled(false);
                    RemoveLastAddedNewExercise.setEnabled(false);
                }
                else{
                    Done.setEnabled(true);
                    RemoveLastAddedNewExercise.setEnabled(true);
                }
                break;

            case "G":
                if(OwnExercisesForWorkOutG.isEmpty()){
                    Done.setEnabled(false);
                    RemoveLastAddedNewExercise.setEnabled(false);
                }
                else{
                    Done.setEnabled(true);
                    RemoveLastAddedNewExercise.setEnabled(true);
                }
                break;

        }




        AddNewExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String Temp= (String) b.getTag();
                for(Exercise x: GeneralExercises) {


                    if(SpinnerGeneralExercises.getText().toString().equals(x.GetExerciseName())){

                        switch (Temp){
                            case "Add New Exercise For WorkOut A":
                                if(!ExerciseInWorkOut( x, OwnExercisesForWorkOutA)){
                                    OwnExercisesForWorkOutA.add(x);
                                    Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutA,MainActivity.OwnExercisesForWorkOutA);
                                    Done.setEnabled(true);
                                    RemoveLastAddedNewExercise.setEnabled(true);

                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Chosen", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                break;

                            case "Add New Exercise For WorkOut B":
                                if(!ExerciseInWorkOut( x, OwnExercisesForWorkOutB)){
                                    OwnExercisesForWorkOutB.add(x);
                                    Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutB,MainActivity.OwnExercisesForWorkOutB);
                                    Done.setEnabled(true);
                                    RemoveLastAddedNewExercise.setEnabled(true);
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Chosen", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                break;

                            case "Add New Exercise For WorkOut C":
                                if(!ExerciseInWorkOut( x, OwnExercisesForWorkOutC)){
                                    OwnExercisesForWorkOutC.add(x);
                                    Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutC,MainActivity.OwnExercisesForWorkOutC);
                                    Done.setEnabled(true);
                                    RemoveLastAddedNewExercise.setEnabled(true);
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Chosen", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                break;

                            case "Add New Exercise For WorkOut D":
                                if(!ExerciseInWorkOut( x, OwnExercisesForWorkOutD)){
                                    OwnExercisesForWorkOutD.add(x);
                                    Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutD,MainActivity.OwnExercisesForWorkOutD);
                                    Done.setEnabled(true);
                                    RemoveLastAddedNewExercise.setEnabled(true);
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Chosen", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                break;


                            case "Add New Exercise For WorkOut E":
                                if(!ExerciseInWorkOut( x, OwnExercisesForWorkOutE)){
                                    OwnExercisesForWorkOutE.add(x);
                                    Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutE,MainActivity.OwnExercisesForWorkOutE);
                                    Done.setEnabled(true);
                                    RemoveLastAddedNewExercise.setEnabled(true);
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Chosen", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                break;

                            case "Add New Exercise For WorkOut F":
                                if(!ExerciseInWorkOut( x, OwnExercisesForWorkOutF)){
                                    OwnExercisesForWorkOutF.add(x);
                                    Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutF,MainActivity.OwnExercisesForWorkOutF);
                                    Done.setEnabled(true);
                                    RemoveLastAddedNewExercise.setEnabled(true);
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Chosen", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                break;

                            case "Add New Exercise For WorkOut G":
                                if(!ExerciseInWorkOut( x, OwnExercisesForWorkOutG)){
                                    OwnExercisesForWorkOutG.add(x);
                                    Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutG,MainActivity.OwnExercisesForWorkOutG);
                                    Done.setEnabled(true);
                                    RemoveLastAddedNewExercise.setEnabled(true);
                                }
                                else{
                                    Toast toast = Toast.makeText(getApplicationContext(),"Exercise Was Chosen", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                break;




                        }




                    }

                }

            }
        });



        ////clear and done

        RemoveLastAddedNewExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String Temp= (String) b.getTag();
                switch (Temp){
                    case "Remove Last Added Exercise A":
                        OwnExercisesForWorkOutA.remove(OwnExercisesForWorkOutA.size()-1);
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutA,MainActivity.OwnExercisesForWorkOutA);
                        if(OwnExercisesForWorkOutA.isEmpty()){
                            Done.setEnabled(false);
                            RemoveLastAddedNewExercise.setEnabled(false);
                        }



                        break;
                    case "Remove Last Added Exercise B":
                        OwnExercisesForWorkOutB.remove(OwnExercisesForWorkOutB.size()-1);
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutB,MainActivity.OwnExercisesForWorkOutB);
                        if(OwnExercisesForWorkOutB.isEmpty()){
                            Done.setEnabled(false);
                            RemoveLastAddedNewExercise.setEnabled(false);
                        }




                        break;
                    case "Remove Last Added Exercise C":
                        OwnExercisesForWorkOutC.remove(OwnExercisesForWorkOutC.size()-1);
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutC,MainActivity.OwnExercisesForWorkOutC);
                        if(OwnExercisesForWorkOutC.isEmpty()){
                            Done.setEnabled(false);
                            RemoveLastAddedNewExercise.setEnabled(false);
                        }




                        break;
                    case "Remove Last Added Exercise D":
                        OwnExercisesForWorkOutD.remove(OwnExercisesForWorkOutD.size()-1);
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutD,MainActivity.OwnExercisesForWorkOutD);
                        if(OwnExercisesForWorkOutD.isEmpty()){
                            Done.setEnabled(false);
                            RemoveLastAddedNewExercise.setEnabled(false);
                        }



                        break;
                    case "Remove Last Added Exercise E":
                        OwnExercisesForWorkOutE.remove(OwnExercisesForWorkOutE.size()-1);
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutE,MainActivity.OwnExercisesForWorkOutE);
                        if(OwnExercisesForWorkOutE.isEmpty()){
                            Done.setEnabled(false);
                            RemoveLastAddedNewExercise.setEnabled(false);
                        }



                        break;
                    case "Remove Last Added Exercise F":
                        OwnExercisesForWorkOutF.remove(OwnExercisesForWorkOutF.size()-1);
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutF,MainActivity.OwnExercisesForWorkOutF);
                        if(OwnExercisesForWorkOutF.isEmpty()){
                            Done.setEnabled(false);
                            RemoveLastAddedNewExercise.setEnabled(false);
                        }



                        break;
                    case "Remove Last Added Exercise G":
                        OwnExercisesForWorkOutG.remove(OwnExercisesForWorkOutG.size()-1);
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutG,MainActivity.OwnExercisesForWorkOutG);
                        if(OwnExercisesForWorkOutG.isEmpty()){
                            Done.setEnabled(false);
                            RemoveLastAddedNewExercise.setEnabled(false);
                        }



                        break;
                }



            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OwnExercisesForWorkOutA =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutA);
                OwnExercisesForWorkOutB =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutB);
                OwnExercisesForWorkOutC =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutC);
                OwnExercisesForWorkOutD =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutD);
                OwnExercisesForWorkOutE =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutE);
                OwnExercisesForWorkOutF =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutF);
                OwnExercisesForWorkOutG =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutG);
                ChooseExercisesForWorkOut();

            }
        });









    }





    public void AddExerciseForWorkOutMethod(){

    }
















/*
    public void AddNewExerciseForWorkOutMethod(){
        ProgramScrollViewLinearLayout.removeAllViews();


        TextView ExerciseName= new TextView(this);
        ExerciseName.setText("Exercise Name");
        ProgramScrollViewLinearLayout.addView(ExerciseName);
        EditText FillExerciseName= new EditText(this);
        FillExerciseName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        ProgramScrollViewLinearLayout.addView(FillExerciseName);

        TextView ExerciseType= new TextView(this);
        ExerciseType.setText("Exercise Type");
        ProgramScrollViewLinearLayout.addView(ExerciseType);
        EditText FillExerciseType= new EditText(this);
        FillExerciseType.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        ProgramScrollViewLinearLayout.addView(FillExerciseType);


        TextView MuscleName= new TextView(this);
        MuscleName.setText("Muscle Name");
        ProgramScrollViewLinearLayout.addView(MuscleName);
        EditText FillMuscleName= new EditText(this);
        FillMuscleName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        ProgramScrollViewLinearLayout.addView(FillMuscleName);



        TextView MuscleName= new TextView(this);
        MuscleName.setText("Muscle Name");
        ProgramScrollViewLinearLayout.addView(MuscleName);
        EditText FillMuscleName= new EditText(this);
        FillMuscleName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        ProgramScrollViewLinearLayout.addView(FillMuscleName);





        ArrayList<String> SpinnerArray1 = new ArrayList<String>();
        SpinnerArray1.add("Rest");
        SpinnerArray1.add("Train");
        ArrayAdapter<String> SpinnerArray1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArray1);
        Spinner SpinnerWhatToDoWednesday = new Spinner(this);
        SpinnerWhatToDoWednesday.setAdapter(SpinnerArray1Adapter);
        WednesdayLinearLayout.addView(SpinnerWhatToDoWednesday);
    }




*/

//////////Scroll View  The WorkOuts Themselves/////////////////////////


    public void ShowWorkOut(View view) {
        Button b = (Button)view;
        switch(b.getText().toString()){
            case "Work\nOut A":
                TrackWhichWorkOut="A";
                ShowWorkOut();
                break;
            case "Work\nOut B":
                TrackWhichWorkOut="B";
                ShowWorkOut();
                break;
            case "Work\nOut C":
                TrackWhichWorkOut="C";
                ShowWorkOut();
                break;
            case "Work\nOut D":
                TrackWhichWorkOut="D";
                ShowWorkOut();
                break;
            case "Work\nOut E":
                TrackWhichWorkOut="E";
                ShowWorkOut();
                break;
            case "Work\nOut F":
                TrackWhichWorkOut="F";
                ShowWorkOut();
                break;
            case "Work\nOut G":
                TrackWhichWorkOut="G";
                ShowWorkOut();
                break;

        }


    }



    private void ShowWorkOut(){
        ProgramScrollViewLinearLayout.removeAllViews();
        ProgramScrollViewLinearLayout.addView(TitleTopOfScrollView);

        ArrayList<String> SelectedExercises = new ArrayList<String>();
        ArrayList<Exercise> SelectedOwnWorkOut= new ArrayList<Exercise>();
            switch (TrackWhichWorkOut){
                case "A":
                   SelectedOwnWorkOut=OwnExercisesForWorkOutA;

                    break;

                case "B":
                    SelectedOwnWorkOut =OwnExercisesForWorkOutB;
                    break;

                case "C":
                    SelectedOwnWorkOut=OwnExercisesForWorkOutC;

                    break;

                case "D":
                    SelectedOwnWorkOut=OwnExercisesForWorkOutD;

                    break;


                case "E":
                    SelectedOwnWorkOut=OwnExercisesForWorkOutE;
                    break;

                case "F":
                    SelectedOwnWorkOut=OwnExercisesForWorkOutF;
                    break;

                case "G":
                    SelectedOwnWorkOut=OwnExercisesForWorkOutG;
                    break;

            }


        String WorkOutMusclesString= "";
        WorkOutName.setText("WorkOut "+TrackWhichWorkOut);
        for(Exercise X: SelectedOwnWorkOut){
            if(!SelectedExercises.contains(X.GetMuscleName())){
                SelectedExercises.add(X.GetMuscleName());
            }

        }


        for(String x:SelectedExercises){
            WorkOutMusclesString=WorkOutMusclesString+x;

            if((SelectedExercises.get(SelectedExercises.size()-1))!=x)
                WorkOutMusclesString=WorkOutMusclesString+", ";
        }

        WorkOutMuscles.setText(WorkOutMusclesString);
        for (Exercise X : SelectedOwnWorkOut){
            TextView Text=new TextView(this);
            Text.setText(X.GetExerciseName());
            ProgramScrollViewLinearLayout.addView(Text);
            Button cb = new Button(getApplicationContext());
            cb.setText("Expand "+X.GetExerciseName());
            cb.setTag(X);
            ProgramScrollViewLinearLayout.addView(cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    Exercise x=(Exercise) b.getTag();
                    ShowExerciseParametersForWorkOut(x);
                }
            });


        }


    }

    private void ShowExerciseParametersForWorkOut(Exercise X){
        LinearLayout SeeExerciseInfo= new LinearLayout(this);
        SeeExerciseInfo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        SeeExerciseInfo.setOrientation(LinearLayout.VERTICAL);


        ProgramScrollViewLinearLayout.removeAllViews();
        TextView ExerciseName=new TextView(this);
        ExerciseName.setText("Exercise Name: "+X.GetExerciseName());
        ProgramScrollViewLinearLayout.addView(ExerciseName);
        TextView MuscleName=new TextView(this);
        MuscleName.setText("Primary Muscle Name: "+X.GetMuscleName());
        ProgramScrollViewLinearLayout.addView(MuscleName);
        TextView CurrentExerciseGoalWeight=new TextView(this);
        CurrentExerciseGoalWeight.setText("Exercise Next Goal: "+"In Pound "+String.valueOf(((int) ((X.GetCurrentExerciseGoalWeight())*2 + 0.5))/2.0)+" In Kg "+String.valueOf(((int) ((X.GetCurrentExerciseGoalWeight()/2.2)*2 + 0.5))/2.0));
        CurrentExerciseGoalWeight.setTag("ShowCurrentExerciseGoalWeight");
        ProgramScrollViewLinearLayout.addView(CurrentExerciseGoalWeight);
        TextView FinalExerciseGoalWeight=new TextView(this);
        FinalExerciseGoalWeight.setText("Exercise Final Goal: "+"In Pound "+String.valueOf(((int) ((X.GetFinalExerciseGoalWeight())*2 + 0.5))/2.0)+" In Kg "+String.valueOf(((int) ((X.GetFinalExerciseGoalWeight()/2.2)*2 + 0.5))/2.0));
        FinalExerciseGoalWeight.setTag("ShowFinalExerciseGoalWeight");
        ProgramScrollViewLinearLayout.addView(FinalExerciseGoalWeight);
        TextView ExerciseLvl=new TextView(this);
        ExerciseLvl.setText("Exercise Level: "+X.GetExerciseLvl());
        ExerciseLvl.setTag("ShowExerciseLvl");
        ProgramScrollViewLinearLayout.addView(ExerciseLvl);
        TextView ShowPrev=new TextView(this);
        ShowPrev.setText("Last Time:");



        if(!X.GetPrevRepsNumberPerSet().isEmpty()){
            ProgramScrollViewLinearLayout.addView(ShowPrev);
            ArrayList<Integer> CurrentRepsNumberPerSet = X.GetPrevRepsNumberPerSet();
            ArrayList<Double> CurrentWeightPerSet = X.GetPrevWeightPerSet();
            for(int i=0;i<=CurrentRepsNumberPerSet.size()-1;i++){
                TextView text1=new TextView(this);
                text1.setText("Set: "+String.valueOf(i+1)+ " Reps: "+String.valueOf(CurrentRepsNumberPerSet.get(i))+ " Weight: "+String.valueOf(CurrentWeightPerSet.get(i)));
                SeeExerciseInfo.addView(text1);
            }
            ProgramScrollViewLinearLayout.addView(SeeExerciseInfo);
        }

        TextView ShowNow=new TextView(this);
        ShowNow.setText("Present WorkOut:");
        ProgramScrollViewLinearLayout.addView(ShowNow);
        SaveNewWorkOutExercise =new Button(this);
        SaveNewWorkOutExercise.setText("Save");
        ClearNewWorkOutExercise =new Button(this);
        ClearNewWorkOutExercise.setText("Clear");
        GoBAckTOWorkOut =new Button(this);
        GoBAckTOWorkOut.setText("Go Back To WorkOut");

        SaveNewWorkOutExercise.setEnabled(false);
        ClearNewWorkOutExercise.setEnabled(false);

        LinearLayout ShowPresentWorkOut= new LinearLayout(this);
        ShowPresentWorkOut.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ShowPresentWorkOut.setOrientation(LinearLayout.VERTICAL);

        LinearLayout NewWeightLayOut= new LinearLayout(this);
        NewWeightLayOut.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        NewWeightLayOut.setOrientation(LinearLayout.HORIZONTAL);

        TextView NewWeightText=new TextView(this);
        NewWeightText.setText("Weight: ");
        NewWeightLayOut.addView(NewWeightText);
        EditNewWeight= new EditText(this);
        EditNewWeight.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        NewWeightLayOut.addView(EditNewWeight);

        LinearLayout NewRepsLayOut= new LinearLayout(this);
        NewRepsLayOut.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        NewRepsLayOut.setOrientation(LinearLayout.HORIZONTAL);

        TextView NewRepsText=new TextView(this);
        NewRepsText.setText("Reps: ");
        NewRepsLayOut.addView(NewRepsText);
        EditNewReps= new EditText(this);
        EditNewReps.setInputType(InputType.TYPE_CLASS_NUMBER);
        NewRepsLayOut.addView(EditNewReps);
        ShowPresentWorkOut.addView(NewWeightLayOut);
        ShowPresentWorkOut.addView(NewRepsLayOut);
        ProgramScrollViewLinearLayout.addView(ShowPresentWorkOut);
        if(!EditNewReps.getText().toString().equals("")&& parseInt(EditNewReps.getText().toString())>0 && !EditNewWeight.getText().toString().equals("")&& parseDouble(EditNewWeight.getText().toString())>0){
            SaveNewWorkOutExercise.setEnabled(true);

        }
        EditNewReps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SaveNewWorkOutExercise.setEnabled(false);



            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!EditNewReps.getText().toString().equals("")&& parseInt(EditNewReps.getText().toString())>0 && !EditNewWeight.getText().toString().equals("")&& parseDouble(EditNewWeight.getText().toString())>0){
                    SaveNewWorkOutExercise.setEnabled(true);

                }

            }
        });
        EditNewWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SaveNewWorkOutExercise.setEnabled(false);




            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!EditNewReps.getText().toString().equals("")&& parseInt(EditNewReps.getText().toString())>0 && !EditNewWeight.getText().toString().equals("")&& parseDouble(EditNewWeight.getText().toString())>0){
                    SaveNewWorkOutExercise.setEnabled(true);

                }
            }
        });

        LinearLayout SaveAndClearAndAdvice= new LinearLayout(this);
        SaveAndClearAndAdvice.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        SaveAndClearAndAdvice.setOrientation(LinearLayout.HORIZONTAL);
        Advice =new Button(this);
        Advice.setText("Improve My WorkOut?");
        SaveAndClearAndAdvice.addView(SaveNewWorkOutExercise);
        SaveAndClearAndAdvice.addView(ClearNewWorkOutExercise);
        SaveAndClearAndAdvice.addView(Advice);
        ProgramScrollViewLinearLayout.addView(SaveAndClearAndAdvice);
        ProgramScrollViewLinearLayout.addView(GoBAckTOWorkOut);
        SaveNewWorkOutExercise.setTag(X);
        ClearNewWorkOutExercise.setTag(X);
        Advice.setTag(X);

        if(!X.GetCurrentWeightPerSet().isEmpty()){
            for(int i=0;i<=X.GetCurrentWeightPerSet().size()-1;i++){
                TextView ShowSavedExerciseText=new TextView(CreateYourOwnProgram.this);
                ShowSavedExerciseText.setTag(String.valueOf(i));
                ShowSavedExerciseText.setText("Set Number: " +String.valueOf(i+1)+" Reps:" +" "+String.valueOf(X.GetCurrentRepsNumberPerSet().get(i))+" Weight:" +" "+String.valueOf(X.GetCurrentWeightPerSet().get(i)));
                ProgramScrollViewLinearLayout.addView(ShowSavedExerciseText);
                ClearNewWorkOutExercise.setEnabled(true);
            }
        }
        if(X.GetCurrentWeightPerSet().isEmpty()){
            ClearNewWorkOutExercise.setEnabled(false);
        }



        SaveNewWorkOutExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button B= (Button) view;
                Exercise X= (Exercise)B.getTag();
                Double PrevHighestScore=X.GetHighestRecord();
                X.UpdateCurrentWeightPerSet(parseDouble(EditNewWeight.getText().toString()));
                X.UpdateCurrentRepsNumberPerSet(parseInt(EditNewReps.getText().toString()));
                TextView ShowSavedExerciseText=new TextView(CreateYourOwnProgram.this);
                ShowSavedExerciseText.setTag(String.valueOf(X.GetCurrentWeightPerSet().size()-1));
                ShowSavedExerciseText.setText("Set Number: " +String.valueOf(X.GetCurrentWeightPerSet().size())+" Reps:" +" "+EditNewReps.getText().toString()+" Weight:" +" "+EditNewWeight.getText().toString());
                ProgramScrollViewLinearLayout.addView(ShowSavedExerciseText);

                if(X.GetCurrentWeightPerSet().isEmpty()){
                    ClearNewWorkOutExercise.setEnabled(false);

                }
                else{
                    ClearNewWorkOutExercise.setEnabled(true);

                }

                if(X.GetHighestRecord()>PrevHighestScore){
                    Toast toast = Toast.makeText(getApplicationContext(),"New Highest Record" , Toast.LENGTH_SHORT);
                    toast.show();
                }
                switch (TrackWhichWorkOut){
                    case "A":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutA,MainActivity.OwnExercisesForWorkOutA);

                        break;

                    case "B":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutB,MainActivity.OwnExercisesForWorkOutB);
                        break;

                    case "C":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutC,MainActivity.OwnExercisesForWorkOutC);

                        break;

                    case "D":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutD,MainActivity.OwnExercisesForWorkOutD);

                        break;


                    case "E":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutE,MainActivity.OwnExercisesForWorkOutE);
                        break;

                    case "F":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutF,MainActivity.OwnExercisesForWorkOutF);
                        break;

                    case "G":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutG,MainActivity.OwnExercisesForWorkOutG);
                        break;

                }

                Gson_Update_Data_Exercies_Array(GeneralExercises,MainActivity.OWNGENERALEXERCISES);

                TextView CurrentExerciseGoalWeight = (TextView) ProgramScrollViewLinearLayout.findViewWithTag("ShowCurrentExerciseGoalWeight");
                CurrentExerciseGoalWeight.setText("Exercise Next Goal: "+"In Pound "+String.valueOf(((int) ((X.GetCurrentExerciseGoalWeight())*2 + 0.5))/2.0)+" In Kg "+String.valueOf(((int) ((X.GetCurrentExerciseGoalWeight()/2.2)*2 + 0.5))/2.0));
                TextView FinalExerciseGoalWeight = (TextView) ProgramScrollViewLinearLayout.findViewWithTag("ShowFinalExerciseGoalWeight");
                FinalExerciseGoalWeight.setText("Exercise Final Goal: "+"In Pound "+String.valueOf(((int) ((X.GetFinalExerciseGoalWeight())*2 + 0.5))/2.0)+" In Kg "+String.valueOf(((int) ((X.GetFinalExerciseGoalWeight()/2.2)*2 + 0.5))/2.0));
                TextView ExerciseLvl = (TextView) ProgramScrollViewLinearLayout.findViewWithTag("ShowExerciseLvl");
                ExerciseLvl.setText("Exercise Level: "+X.GetExerciseLvl());

            }
        });



        ClearNewWorkOutExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button B= (Button) view;
                Exercise X= (Exercise)B.getTag();
                Double PrevHighestScore=X.GetHighestRecord();
                TextView text = (TextView) ProgramScrollViewLinearLayout.findViewWithTag(String.valueOf(X.GetCurrentWeightPerSet().size()-1));
                ProgramScrollViewLinearLayout.removeView(text);
                X.RemoveLastIndexCurrentWeightPerSet();
                X.RemoveLastIndexCurrentRepsNumberPerSet();
                if(X.GetCurrentWeightPerSet().isEmpty()){
                    ClearNewWorkOutExercise.setEnabled(false);
                }
                else{
                    ClearNewWorkOutExercise.setEnabled(true);
                }

                if(X.GetHighestRecord()>PrevHighestScore){
                    Toast toast = Toast.makeText(getApplicationContext(),"New Highest Record" , Toast.LENGTH_SHORT);
                    toast.show();
                }


                switch (TrackWhichWorkOut){
                    case "A":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutA,MainActivity.OwnExercisesForWorkOutA);

                        break;

                    case "B":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutB,MainActivity.OwnExercisesForWorkOutB);
                        break;

                    case "C":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutC,MainActivity.OwnExercisesForWorkOutC);

                        break;

                    case "D":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutD,MainActivity.OwnExercisesForWorkOutD);

                        break;


                    case "E":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutE,MainActivity.OwnExercisesForWorkOutE);
                        break;

                    case "F":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutF,MainActivity.OwnExercisesForWorkOutF);
                        break;

                    case "G":
                        Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutG,MainActivity.OwnExercisesForWorkOutG);
                        break;

                }
                Gson_Update_Data_Exercies_Array(GeneralExercises,MainActivity.OWNGENERALEXERCISES);

                TextView CurrentExerciseGoalWeight = (TextView) ProgramScrollViewLinearLayout.findViewWithTag("ShowCurrentExerciseGoalWeight");
                CurrentExerciseGoalWeight.setText("Exercise Next Goal: "+"In Pound "+String.valueOf(((int) ((X.GetCurrentExerciseGoalWeight())*2 + 0.5))/2.0)+" In Kg "+String.valueOf(((int) ((X.GetCurrentExerciseGoalWeight()/2.2)*2 + 0.5))/2.0));
                TextView FinalExerciseGoalWeight = (TextView) ProgramScrollViewLinearLayout.findViewWithTag("ShowFinalExerciseGoalWeight");
                FinalExerciseGoalWeight.setText("Exercise Final Goal: "+"In Pound "+String.valueOf(((int) ((X.GetFinalExerciseGoalWeight())*2 + 0.5))/2.0)+" In Kg "+String.valueOf(((int) ((X.GetFinalExerciseGoalWeight()/2.2)*2 + 0.5))/2.0));
                TextView ExerciseLvl = (TextView) ProgramScrollViewLinearLayout.findViewWithTag("ShowExerciseLvl");
                ExerciseLvl.setText("Exercise Level: "+X.GetExerciseLvl());
            }
        });


        GoBAckTOWorkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowWorkOut();

            }
        });


        Advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button B= (Button) view;
                Exercise X= (Exercise)B.getTag();
                WhatIsTheNextLevel(X);

            }
        });


    }












    private void WhatIsTheNextLevel(Exercise X){
        boolean WasToasted=false;

        if (X.GetPrevWeightPerSet().isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),"You Don't Have A Finished WorkOut Yet, Try Again Later." , Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            if(X.GetExerciseType().equals("Big")){
                for(int i=0; i<= X.GetPrevRepsNumberPerSet().size()-2; i++){
                    if(X.GetPrevWeightPerSet().get(i)<=X.GetPrevWeightPerSet().get(i+1)&& WasToasted==false){
                        Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Pound Or 0.5 Kg For The "+String.valueOf(i+1)+ " Set" , Toast.LENGTH_SHORT);
                        toast.show();
                        i= X.GetPrevRepsNumberPerSet().size()-1;
                        WasToasted=true;
                    }

                }
                for(int i=0; i<= X.GetPrevRepsNumberPerSet().size()-2; i++){
                    if(X.GetPrevRepsNumberPerSet().get(i+1)<X.GetMaxRepsNumberForProgram() && X.GetPrevRepsNumberPerSet().get(i)<X.GetMaxRepsNumberForProgram()){
                        if(X.GetPrevRepsNumberPerSet().get(i)>=X.GetPrevRepsNumberPerSet().get(i+1)&& WasToasted==false){
                            Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Rep For The "+String.valueOf(i+2)+ " Set" , Toast.LENGTH_SHORT);
                            toast.show();
                            i= X.GetPrevRepsNumberPerSet().size()-1;
                            WasToasted=true;
                        }
                    }


                }

                for(int i=0; i<= X.GetPrevRepsNumberPerSet().size()-2; i++){
                    if(X.GetPrevWeightPerSet().get(i)*0.9<=X.GetPrevWeightPerSet().get(i+1)&& WasToasted==false){
                        Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Pound Or 0.5 Kg For The "+String.valueOf(i+1)+ " Set" , Toast.LENGTH_SHORT);
                        toast.show();
                        i= X.GetPrevRepsNumberPerSet().size()-1;
                        WasToasted=true;
                    }
                    else if(X.GetPrevWeightPerSet().get(i)*0.9>X.GetPrevWeightPerSet().get(i+1)&& WasToasted==false){
                        Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Pound Or 0.5 Kg For The "+String.valueOf(i+2)+ " Set" , Toast.LENGTH_SHORT);
                        toast.show();
                        i= X.GetPrevRepsNumberPerSet().size()-1;
                        WasToasted=true;
                    }

                }



                for(int i=0; i<= X.GetPrevRepsNumberPerSet().size()-2; i++){
                    if(X.GetPrevRepsNumberPerSet().get(i+1)<X.GetMaxRepsNumberForProgram() && X.GetPrevRepsNumberPerSet().get(i)<X.GetMaxRepsNumberForProgram()&& WasToasted==false){
                        if(X.GetPrevRepsNumberPerSet().get(i)>=X.GetPrevRepsNumberPerSet().get(i+1)){
                            Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Rep For The "+String.valueOf(i+2)+ " Set", Toast.LENGTH_SHORT);
                            toast.show();
                            i= X.GetPrevRepsNumberPerSet().size()-1;
                            WasToasted=true;
                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Rep For The "+String.valueOf(i+1)+ " Set", Toast.LENGTH_SHORT);
                            toast.show();
                            i= X.GetPrevRepsNumberPerSet().size()-1;
                            WasToasted=true;
                        }
                    }


                }
                if(WasToasted==false){
                    Toast toast = Toast.makeText(getApplicationContext(),"Lower All Reps In all Of The Sets And Add 1 Pound Or 0.5 Kg For Your Weakest Set", Toast.LENGTH_SHORT);
                    toast.show();
                    WasToasted=true;
                }


            }
            else if(X.GetExerciseType().equals("Small")){
                for(int i=0; i<= X.GetPrevRepsNumberPerSet().size()-2; i++){
                    if(X.GetPrevRepsNumberPerSet().get(i)<X.GetPrevRepsNumberPerSet().get(i+1) && WasToasted==false){
                        Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Rep For The "+String.valueOf(i+1)+ " Set" , Toast.LENGTH_SHORT);
                        toast.show();
                        i= X.GetPrevRepsNumberPerSet().size()-1;
                        WasToasted=true;
                    }

                }
                for(int i=0; i<= X.GetPrevRepsNumberPerSet().size()-2; i++){
                    if(X.GetPrevWeightPerSet().get(i)<X.GetPrevWeightPerSet().get(i+1)&& WasToasted==false){
                        Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Pound Or 0.5 Kg For The "+String.valueOf(i+1)+ " Set" , Toast.LENGTH_SHORT);
                        toast.show();
                        i= X.GetPrevRepsNumberPerSet().size()-1;
                        WasToasted=true;
                    }


                }

                for(int i=0; i<= X.GetPrevRepsNumberPerSet().size()-2; i++){
                    if(X.GetPrevRepsNumberPerSet().get(i+1)<X.GetMaxRepsNumberForProgram() && X.GetPrevRepsNumberPerSet().get(i)<X.GetMaxRepsNumberForProgram()&& WasToasted==false){
                        if(X.GetPrevRepsNumberPerSet().get(i)>=X.GetPrevRepsNumberPerSet().get(i+1)){
                            Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Rep For The "+String.valueOf(i+2)+ " Set", Toast.LENGTH_SHORT);
                            toast.show();
                            i= X.GetPrevRepsNumberPerSet().size()-1;
                            WasToasted=true;
                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(),"Add 1 Rep For The "+String.valueOf(i+1)+ " Set", Toast.LENGTH_SHORT);
                            toast.show();
                            i= X.GetPrevRepsNumberPerSet().size()-1;
                            WasToasted=true;
                        }
                    }


                }
                if(WasToasted==false){
                    Toast toast = Toast.makeText(getApplicationContext(),"Lower All Reps In all Of The Sets And Add 1 Pound Or 0.5 Kg For Your First Set", Toast.LENGTH_SHORT);
                    toast.show();
                    WasToasted=true;
                }



            }
        }



    }





























    //////Make Life Easy//////
    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }

    public String get_shared_info_string( final String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        if((sharedPreferences.getString(Key,"0" ).equals("")))
        {
            updateData("0",Key);
        }
        return(sharedPreferences.getString(Key,"0" ));
    }


    public int StringToInt(String Key){
        return(Integer.parseInt(Key));

    }

    public void Gson_Update_Data_String_Array(ArrayList<String> Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public ArrayList<String> get_shared_info_Gson_String_Array( final String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        ArrayList<String> RememberDays= new ArrayList<String>();
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

            return(RememberDays);
        }
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        RememberDays = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(RememberDays);
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








    private void TrainOnMonday(String WorkoutToken)
    {

        MondayActivity.setText("WorkOut "+WorkoutToken);
        ButtonMondayActivity.setText("Work\nOut "+WorkoutToken);

    }
    private void TrainOnTuesday(String WorkoutToken) {
        TuesdayActivity.setText("WorkOut "+WorkoutToken);
        ButtonTuesdayActivity.setText("Work\nOut "+WorkoutToken);


    }
    private void TrainOnWednesday(String WorkoutToken) {
        WednesdayActivity.setText("WorkOut "+WorkoutToken);
        ButtonWednesdayActivity.setText("Work\nOut "+WorkoutToken);

    }
    private void TrainOnThursday(String WorkoutToken) {
        ThursdayActivity.setText("WorkOut "+WorkoutToken);
        ButtonThursdayActivity.setText("Work\nOut "+WorkoutToken);
    }
    private void TrainOnFriday(String WorkoutToken) {
        FridayActivity.setText("WorkOut "+WorkoutToken);
        ButtonFridayActivity.setText("Work\nOut "+WorkoutToken);


    }
    private void TrainOnSaturday(String WorkoutToken) {
        SaturdayActivity.setText("WorkOut "+WorkoutToken);
        ButtonSaturdayActivity.setText("Work\nOut "+WorkoutToken);


    }
    private void TrainOnSunday(String WorkoutToken) {
        SundayActivity.setText("WorkOut "+WorkoutToken);
        ButtonSundayActivity.setText("Work\nOut "+WorkoutToken);

    }
    private void SetRestDay(String Name){
        switch (Name){
            case "Monday":
                MondayActivity.setText("Rest");
                ButtonMondayActivity.setText("Rest");
                break;
            case "Tuesday":
                TuesdayActivity.setText("Rest");
                ButtonTuesdayActivity.setText("Rest");
                break;
            case "Wednesday":
                WednesdayActivity.setText("Rest");
                ButtonWednesdayActivity.setText("Rest");
                break;
            case "Thursday":
                ThursdayActivity.setText("Rest");
                ButtonThursdayActivity.setText("Rest");
                break;
            case "Friday":
                FridayActivity.setText("Rest");
                ButtonFridayActivity.setText("Rest");
                break;
            case "Saturday":
                SaturdayActivity.setText("Rest");
                ButtonSaturdayActivity.setText("Rest");
                break;
            case "Sunday":
                SundayActivity.setText("Rest");
                ButtonSundayActivity.setText("Rest");
                break;
        }
    }

    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();

    }

    public void openOptions_Menu(){
        Intent intentOptions_Menu= new Intent(this,OwnProgramOptionsMenu.class);
        startActivity(intentOptions_Menu);
        finish();

    }



    public Boolean ExerciseInWorkOut( Exercise X, ArrayList<Exercise> Y){
        for(Exercise Z : Y){
            if(Z.GetExerciseName()==X.GetExerciseName()){
                return  (true);
            }
        }
        return  (false);
    }
    public void Gson_Empty_UpdateData(String Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

    }

public void  ResetOwnProgramStuff(){
    Gson_Empty_UpdateData("0",MainActivity.OwnDaysFirst);
    Gson_Empty_UpdateData("0",MainActivity.OwnDaysSecond);
    Gson_Empty_UpdateData("0",MainActivity.OwnDaysThird);
    Gson_Empty_UpdateData("0",MainActivity.OwnDaysFourth);
    Gson_Empty_UpdateData("0",MainActivity.OwnDaysFifth);
    Gson_Empty_UpdateData("0",MainActivity.OwnDaysSixth);
    Gson_Empty_UpdateData("0",MainActivity.OwnDaysSeventh);
    Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutA);
    Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutB);
    Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutC);
    Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutD);
    Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutE);
    Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutF);
    Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutG);



}
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                UpdateOnceADay(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    public static CreateYourOwnProgram getInstance(){
        return instance;
    }
    private void startRepeatingTask() {
        mStatusChecker.run();
    }



    public void UpdateOnceADay(){
        OwnExercisesForWorkOutA =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutA);
        OwnExercisesForWorkOutB =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutB);
        OwnExercisesForWorkOutC =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutC);
        OwnExercisesForWorkOutD =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutD);
        OwnExercisesForWorkOutE =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutE);
        OwnExercisesForWorkOutF =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutF);
        OwnExercisesForWorkOutG =get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutG);
        GeneralExercises=get_shared_info_Gson_Exercise_Array(MainActivity.OWNGENERALEXERCISES);
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


}