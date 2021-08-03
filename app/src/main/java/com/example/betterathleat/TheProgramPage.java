package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class TheProgramPage extends AppCompatActivity {
    private User user;
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
    private int NumOfDiffrentWorkoutInPersonalizedProgram;
    private Button Main_Menu;
    private String test;
    private  ScrollView SHowWorkOutDetail;
    private TextView WorkOutMuscles;
    private TextView WorkOutName;
    private LinearLayout TitleTopOfScrollView;
    private LinearLayout ProgramScrollViewLinearLayout;
    private Gson gson=new Gson();
    private Program MyProgram;
    private EditText EditNewReps;
    private EditText EditNewWeight;
    private Button ClearNewWorkOutExercise;
    private Button SaveNewWorkOutExercise;
    private Button GoBAckTOWorkOut;
    private Button Advice;
    private int mInterval = 1000*60; // 1 min by default, can be changed later
    private Handler mHandler;
    private static TheProgramPage instance;
    private Exercise TempExercise;
    private ArrayList<Integer> TempRepsNumberPerSet= new ArrayList<Integer>();
    private ArrayList<Double> TempWeightPerSet= new ArrayList<Double>();
    ArrayList<Exercise> ExercisesForWorkOutA = new ArrayList<Exercise>();
    ArrayList<Exercise> ExercisesForWorkOutB = new ArrayList<Exercise>();
    ArrayList<Exercise> ExercisesForWorkOutC = new ArrayList<Exercise>();
    ArrayList<Exercise> ExercisesForWorkOutD = new ArrayList<Exercise>();
    ArrayList<Exercise> GeneralExercise = new ArrayList<Exercise>();
    private Button Options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_program_page);
        user= get_shared_info_Gson_User( MainActivity.PASSUSER);
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
        NumOfDiffrentWorkoutInPersonalizedProgram=user.NumOfDiffrentWorkoutInPersonalizedProgram(user.PersonalizedProgramName());


        Toast toast = Toast.makeText(getApplicationContext(),user.PersonalizedProgramName() , Toast.LENGTH_SHORT);
        toast.show();


        MondayActivity.setText("Rest");
        TuesdayActivity.setText("Rest");
        WednesdayActivity.setText("Rest");
        ThursdayActivity.setText("Rest");
        FridayActivity.setText("Rest");
        SaturdayActivity.setText("Rest");
        SundayActivity.setText("Rest");
        ButtonMondayActivity.setText("Rest");
        ButtonTuesdayActivity.setText("Rest");
        ButtonWednesdayActivity.setText("Rest");
        ButtonThursdayActivity.setText("Rest");
        ButtonFridayActivity.setText("Rest");
        ButtonSaturdayActivity.setText("Rest");
        ButtonSundayActivity.setText("Rest");
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
        MyProgram = new Program(user.PersonalizedProgramName(), user.GetAge(), user.GetWeight(), user.GetHeight(), user.GetPurpose(), user.GetTrainingTime(), user.GetLiftRecord(), user.GetMostUsedRepRange(), user.GetNumberOfAccomplishedPrograms(), user.GetTrainingSeriousness(),NumOfDiffrentWorkoutInPersonalizedProgram, this);
        if( !(get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES)).isEmpty()){


            GeneralExercise=get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES);
        }





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

        switch(NumOfDiffrentWorkoutInPersonalizedProgram){
            case  1:
                MakeItOneDayOneWorkOut();
                mHandler = new Handler();
                startRepeatingTask();
                break;

            case  2:
                switch(user.GetWorkOutPerWeek()){
                    case  2:
                        MakeItTwoDayTwoWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                    case  3:
                        MakeItThreeDayTwoWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                    case  4:
                        MakeItFourDayTwoWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                    case  5:
                        MakeItFiveDayTwoWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                }
                break;
            case  3:
                switch(user.GetWorkOutPerWeek()){
                    case  3:
                        MakeItThreeDayThreeWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                    case  4:
                        MakeItFourDayThreeWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                    case  5:
                        MakeItFiveDayThreeWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                    case  6:
                        MakeItSixDayThreeWorkOut();
                        mHandler = new Handler();
                        startRepeatingTask();
                        break;
                }
                break;
            case  4:
                MakeItSevenDayFourWorkOut();
                mHandler = new Handler();
                startRepeatingTask();
                break;


        }







    }










    private void MakeItOneDayOneWorkOut() {
        int WorkOutDay;
        if( (get_shared_info_string(MainActivity.TRAINING1DAYPERWEEK1WORKOUT)).equals("0")){
            WorkOutDay = ((int) (Math.random() * 7))+1;
            updateData(String.valueOf(WorkOutDay),(MainActivity.TRAINING1DAYPERWEEK1WORKOUT));

        }
        else{
            WorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING1DAYPERWEEK1WORKOUT));
        }
        switch (WorkOutDay) {
            case 1:
                ResetDaysView();
                TrainOnMonday("A");
                break;
            case 2:
                ResetDaysView();
                TrainOnTuesday("A");
                break;
            case 3:
                ResetDaysView();
                TrainOnWednesday("A");
                break;
            case 4:
                ResetDaysView();
                TrainOnThursday("A");
                break;
            case 5:
                ResetDaysView();
                TrainOnFriday("A");
                break;
            case 6:
                ResetDaysView();
                TrainOnSaturday("A");
                break;
            case 7:
                ResetDaysView();
                TrainOnSunday("A");
                break;

        }
    }



    private void MakeItTwoDayTwoWorkOut() {
        int FirstWorkOutDay;
        int SecondWorkOutDay;
        if( (get_shared_info_string(MainActivity.TRAINING2DAYPERWEEK2WORKOUT1)).equals("0")){
            FirstWorkOutDay = ((int) (Math.random() * 7))+1;
            updateData(String.valueOf(FirstWorkOutDay),(MainActivity.TRAINING2DAYPERWEEK2WORKOUT1));

        }
        else{
            FirstWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING2DAYPERWEEK2WORKOUT1));
        }


        if( (get_shared_info_string(MainActivity.TRAINING2DAYPERWEEK2WORKOUT2)).equals("0")){
            do{
                SecondWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine2(FirstWorkOutDay,SecondWorkOutDay));

            updateData(String.valueOf(SecondWorkOutDay),(MainActivity.TRAINING2DAYPERWEEK2WORKOUT2));

        }
        else{
            SecondWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING2DAYPERWEEK2WORKOUT2));
        }


        switch (FirstWorkOutDay) {
            case 1:
                ResetDaysView();
                TrainOnMonday("A");
                break;
            case 2:
                ResetDaysView();
                TrainOnTuesday("A");
                break;
            case 3:
                ResetDaysView();
                TrainOnWednesday("A");
                break;
            case 4:
                ResetDaysView();
                TrainOnThursday("A");
                break;
            case 5:
                ResetDaysView();
                TrainOnFriday("A");
                break;
            case 6:
                ResetDaysView();
                TrainOnSaturday("A");
                break;
            case 7:
                ResetDaysView();
                TrainOnSunday("A");
                break;

        }
        switch (SecondWorkOutDay) {
            case 1:
                TrainOnMonday("B");
                break;
            case 2:
                TrainOnTuesday("B");
                break;
            case 3:
                TrainOnWednesday("B");
                break;
            case 4:
                TrainOnThursday("B");
                break;
            case 5:
                TrainOnFriday("B");
                break;
            case 6:
                TrainOnSaturday("B");
                break;
            case 7:
                TrainOnSunday("B");
                break;

        }
    }



    private void MakeItThreeDayTwoWorkOut() {
        Calendar today = Calendar.getInstance();
        ArrayList<Integer> SelectedDays = new ArrayList<Integer>();
        int FirstWorkOutDay;
        int SecondWorkOutDay;
        int ThirdWorkOutDay;
        if( (get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK2WORKOUT1)).equals("0")){
            FirstWorkOutDay = ((int) (Math.random() * 7))+1;
            updateData(String.valueOf(FirstWorkOutDay),(MainActivity.TRAINING3DAYPERWEEK2WORKOUT1));

        }
        else{
            FirstWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK2WORKOUT1));
        }


        if( (get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK2WORKOUT2)).equals("0")){
            do{
                SecondWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine2(FirstWorkOutDay,SecondWorkOutDay));

            updateData(String.valueOf(SecondWorkOutDay),(MainActivity.TRAINING3DAYPERWEEK2WORKOUT2));

        }
        else{
            SecondWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK2WORKOUT2));
        }



        if( (get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK2WORKOUT3)).equals("0")){
            do{
                ThirdWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine3(FirstWorkOutDay,SecondWorkOutDay, ThirdWorkOutDay));

            updateData(String.valueOf(ThirdWorkOutDay),(MainActivity.TRAINING3DAYPERWEEK2WORKOUT3));

        }
        else{
            ThirdWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK2WORKOUT3));
        }
        SelectedDays.add(FirstWorkOutDay);
        SelectedDays.add(SecondWorkOutDay);
        SelectedDays.add(ThirdWorkOutDay);


        switch (Collections.min(SelectedDays)) {
            case 1:
                ResetDaysView();
                TrainOnMonday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                ResetDaysView();
                TrainOnTuesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                ResetDaysView();
                TrainOnWednesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                ResetDaysView();
                TrainOnThursday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                ResetDaysView();
                TrainOnFriday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                ResetDaysView();
                TrainOnSaturday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                ResetDaysView();
                TrainOnSunday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }
        switch (Collections.min(SelectedDays)) {
            case 1:
                TrainOnMonday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                TrainOnTuesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                TrainOnWednesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                TrainOnThursday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                TrainOnFriday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                TrainOnSaturday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                TrainOnSunday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }
        if(today.get(Calendar.WEEK_OF_MONTH)%2!=0) {
            switch (Collections.min(SelectedDays)) {
                case 1:
                    TrainOnMonday("A");
                    SelectedDays.remove((Collections.min(SelectedDays)));
                    break;
                case 2:
                    TrainOnTuesday("A");
                    SelectedDays.remove((Collections.min(SelectedDays)));
                    break;
                case 3:
                    TrainOnWednesday("A");
                    SelectedDays.remove((Collections.min(SelectedDays)));
                    break;
                case 4:
                    TrainOnThursday("A");
                    SelectedDays.remove((Collections.min(SelectedDays)));
                    break;
                case 5:
                    TrainOnFriday("A");
                    SelectedDays.remove((Collections.min(SelectedDays)));
                    break;
                case 6:
                    TrainOnSaturday("A");
                    SelectedDays.remove((Collections.min(SelectedDays)));
                    break;
                case 7:
                    TrainOnSunday("A");
                    SelectedDays.remove((Collections.min(SelectedDays)));
                    break;

            }
        }
            else{
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
            }


    }
























    private void MakeItFourDayTwoWorkOut() {
        ArrayList<Integer> SelectedDays = new ArrayList<Integer>();
        int FirstWorkOutDay;
        int SecondWorkOutDay;
        int ThirdWorkOutDay;
        int ForthWorkOutDay;
        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT1)).equals("0")){
            FirstWorkOutDay = ((int) (Math.random() * 7))+1;
            updateData(String.valueOf(FirstWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK2WORKOUT1));

        }
        else{
            FirstWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT1));
        }


        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT2)).equals("0")){
            do{
                SecondWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine2(FirstWorkOutDay,SecondWorkOutDay));

            updateData(String.valueOf(SecondWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK2WORKOUT2));

        }
        else{
            SecondWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT2));
        }



        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT3)).equals("0")){
            do{
                ThirdWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine3(FirstWorkOutDay,SecondWorkOutDay, ThirdWorkOutDay));

            updateData(String.valueOf(ThirdWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK2WORKOUT3));

        }
        else{
            ThirdWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT3));
        }

        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT4)).equals("0")){
            do{
                ForthWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(ForthWorkOutDay== FirstWorkOutDay || ForthWorkOutDay==SecondWorkOutDay || ForthWorkOutDay==ThirdWorkOutDay|| ( !DaysAreFine3(FirstWorkOutDay,SecondWorkOutDay, ForthWorkOutDay) && !DaysAreFine3(ForthWorkOutDay,SecondWorkOutDay, ThirdWorkOutDay))) ;

            updateData(String.valueOf(ForthWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK2WORKOUT4));

        }
        else{
            ForthWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK2WORKOUT4));
        }


        SelectedDays.add(FirstWorkOutDay);
        SelectedDays.add(SecondWorkOutDay);
        SelectedDays.add(ThirdWorkOutDay);
        SelectedDays.add(ForthWorkOutDay);

        switch (Collections.min(SelectedDays)) {
            case 1:
                ResetDaysView();
                TrainOnMonday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                ResetDaysView();
                TrainOnTuesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                ResetDaysView();
                TrainOnWednesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                ResetDaysView();
                TrainOnThursday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                ResetDaysView();
                TrainOnFriday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                ResetDaysView();
                TrainOnSaturday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                ResetDaysView();
                TrainOnSunday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }
        switch (Collections.min(SelectedDays)) {
            case 1:
                TrainOnMonday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                TrainOnTuesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                TrainOnWednesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                TrainOnThursday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                TrainOnFriday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                TrainOnSaturday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                TrainOnSunday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }
        switch (Collections.min(SelectedDays)) {
            case 1:
                TrainOnMonday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                TrainOnTuesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                TrainOnWednesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                TrainOnThursday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                TrainOnFriday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                TrainOnSaturday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                TrainOnSunday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }
        switch (Collections.min(SelectedDays)) {
            case 1:
                TrainOnMonday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                TrainOnTuesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                TrainOnWednesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                TrainOnThursday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                TrainOnFriday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                TrainOnSaturday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                TrainOnSunday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }



    }







    public void MakeItFiveDayTwoWorkOut(){
        Calendar today = Calendar.getInstance();
        switch((today.get(Calendar.WEEK_OF_MONTH))){
            case 1:
            case 3:
                MondayActivity.setText("WorkOut A");
                TuesdayActivity.setText("WorkOut B");
                WednesdayActivity.setText("Rest");
                ThursdayActivity.setText("WorkOut A");
                FridayActivity.setText("WorkOut B");
                SaturdayActivity.setText("Rest");
                SundayActivity.setText("WorkOut A");
                ButtonMondayActivity.setText("Work\nOut A");
                ButtonTuesdayActivity.setText("Work\nOut B");
                ButtonWednesdayActivity.setText("Rest");
                ButtonThursdayActivity.setText("Work\nOut A");
                ButtonFridayActivity.setText("Work\nOut B");
                ButtonSaturdayActivity.setText("Rest");
                ButtonSundayActivity.setText("Work\nOut A");

                break;
            case 2:
            case 4:
                MondayActivity.setText("WorkOut B");
                TuesdayActivity.setText("Rest");
                WednesdayActivity.setText("WorkOut A");
                ThursdayActivity.setText("WorkOut B");
                FridayActivity.setText("Rest");
                SaturdayActivity.setText("WorkOut A");
                SundayActivity.setText("WorkOut B");
                ButtonMondayActivity.setText("Work\nOut B");
                ButtonTuesdayActivity.setText("Rest");
                ButtonWednesdayActivity.setText("Work\nOut A");
                ButtonThursdayActivity.setText("Work\nOut B");
                ButtonFridayActivity.setText("Rest");
                ButtonSaturdayActivity.setText("Work\nOut A");
                ButtonSundayActivity.setText("Work\nOut B");

                break;

        }





    }


    public void MakeItThreeDayThreeWorkOut(){
        Calendar today = Calendar.getInstance();
        ArrayList<Integer> SelectedDays = new ArrayList<Integer>();
        int FirstWorkOutDay;
        int SecondWorkOutDay;
        int ThirdWorkOutDay;
        if( (get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK3WORKOUT1)).equals("0")){
            FirstWorkOutDay = ((int) (Math.random() * 7))+1;
            updateData(String.valueOf(FirstWorkOutDay),(MainActivity.TRAINING3DAYPERWEEK3WORKOUT1));

        }
        else{
            FirstWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK3WORKOUT1));
        }


        if( (get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK3WORKOUT2)).equals("0")){
            do{
                SecondWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine2(FirstWorkOutDay,SecondWorkOutDay));

            updateData(String.valueOf(SecondWorkOutDay),(MainActivity.TRAINING3DAYPERWEEK3WORKOUT2));

        }
        else{
            SecondWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK3WORKOUT2));
        }



        if( (get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK3WORKOUT3)).equals("0")){
            do{
                ThirdWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine3(FirstWorkOutDay,SecondWorkOutDay, ThirdWorkOutDay));

            updateData(String.valueOf(ThirdWorkOutDay),(MainActivity.TRAINING3DAYPERWEEK3WORKOUT3));

        }
        else{
            ThirdWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING3DAYPERWEEK3WORKOUT3));
        }
        SelectedDays.add(FirstWorkOutDay);
        SelectedDays.add(SecondWorkOutDay);
        SelectedDays.add(ThirdWorkOutDay);


        switch (Collections.min(SelectedDays)) {
            case 1:
                ResetDaysView();
                TrainOnMonday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                ResetDaysView();
                TrainOnTuesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                ResetDaysView();
                TrainOnWednesday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                ResetDaysView();
                TrainOnThursday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                ResetDaysView();
                TrainOnFriday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                ResetDaysView();
                TrainOnSaturday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                ResetDaysView();
                TrainOnSunday("A");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }
        switch (Collections.min(SelectedDays)) {
            case 1:
                TrainOnMonday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                TrainOnTuesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                TrainOnWednesday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                TrainOnThursday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                TrainOnFriday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                TrainOnSaturday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                TrainOnSunday("B");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }
        switch (Collections.min(SelectedDays)) {
            case 1:
                TrainOnMonday("C");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 2:
                TrainOnTuesday("C");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 3:
                TrainOnWednesday("C");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 4:
                TrainOnThursday("C");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 5:
                TrainOnFriday("C");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 6:
                TrainOnSaturday("C");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;
            case 7:
                TrainOnSunday("C");
                SelectedDays.remove((Collections.min(SelectedDays)));
                break;

        }


    }










    public void MakeItFourDayThreeWorkOut(){
        Calendar today = Calendar.getInstance();
        ArrayList<Integer> SelectedDays = new ArrayList<Integer>();
        int FirstWorkOutDay;
        int SecondWorkOutDay;
        int ThirdWorkOutDay;
        int ForthWorkOutDay;
        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT1)).equals("0")){
            FirstWorkOutDay = ((int) (Math.random() * 7))+1;
            updateData(String.valueOf(FirstWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK3WORKOUT1));

        }
        else{
            FirstWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT1));
        }


        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT2)).equals("0")){
            do{
                SecondWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine2(FirstWorkOutDay,SecondWorkOutDay));

            updateData(String.valueOf(SecondWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK3WORKOUT2));

        }
        else{
            SecondWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT2));
        }



        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT3)).equals("0")){
            do{
                ThirdWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine3(FirstWorkOutDay,SecondWorkOutDay, ThirdWorkOutDay));

            updateData(String.valueOf(ThirdWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK3WORKOUT3));

        }
        else{
            ThirdWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT3));
        }

        if( (get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT4)).equals("0")){
            do{
                ForthWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(ForthWorkOutDay== FirstWorkOutDay || ForthWorkOutDay==SecondWorkOutDay || ForthWorkOutDay==ThirdWorkOutDay);

            updateData(String.valueOf(ForthWorkOutDay),(MainActivity.TRAINING4DAYPERWEEK3WORKOUT4));

        }
        else{
            ForthWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING4DAYPERWEEK3WORKOUT4));
        }


        SelectedDays.add(FirstWorkOutDay);
        SelectedDays.add(SecondWorkOutDay);
        SelectedDays.add(ThirdWorkOutDay);
        SelectedDays.add(ForthWorkOutDay);


        switch((today.get(Calendar.WEEK_OF_MONTH))){
            case 1:
            case 4:
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        ResetDaysView();
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        ResetDaysView();
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        ResetDaysView();
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        ResetDaysView();
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        ResetDaysView();
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        ResetDaysView();
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        ResetDaysView();
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }

                break;
            case 2:
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        ResetDaysView();
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        ResetDaysView();
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        ResetDaysView();
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        ResetDaysView();
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        ResetDaysView();
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        ResetDaysView();
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        ResetDaysView();
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }

                break;
            case 3:
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        ResetDaysView();
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        ResetDaysView();
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        ResetDaysView();
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        ResetDaysView();
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        ResetDaysView();
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        ResetDaysView();
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        ResetDaysView();
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }

                break;


        }






    }










    public void MakeItFiveDayThreeWorkOut(){
        Calendar today = Calendar.getInstance();
        ArrayList<Integer> SelectedDays = new ArrayList<Integer>();
        int FirstWorkOutDay;
        int SecondWorkOutDay;
        int ThirdWorkOutDay;
        int ForthWorkOutDay;
        int FifthWorkOutDay;
        if( (get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT1)).equals("0")){
            FirstWorkOutDay = ((int) (Math.random() * 7))+1;
            updateData(String.valueOf(FirstWorkOutDay),(MainActivity.TRAINING5DAYPERWEEK3WORKOUT1));

        }
        else{
            FirstWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT1));
        }


        if( (get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT2)).equals("0")){
            do{
                SecondWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine2(FirstWorkOutDay,SecondWorkOutDay));

            updateData(String.valueOf(SecondWorkOutDay),(MainActivity.TRAINING5DAYPERWEEK3WORKOUT2));

        }
        else{
            SecondWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT2));
        }



        if( (get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT3)).equals("0")){
            do{
                ThirdWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(!DaysAreFine3(FirstWorkOutDay,SecondWorkOutDay, ThirdWorkOutDay));

            updateData(String.valueOf(ThirdWorkOutDay),(MainActivity.TRAINING5DAYPERWEEK3WORKOUT3));

        }
        else{
            ThirdWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT3));
        }

        if( (get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT4)).equals("0")){
            do{
                ForthWorkOutDay = ((int) (Math.random() * 7))+1;
            }while(ForthWorkOutDay== FirstWorkOutDay || ForthWorkOutDay==SecondWorkOutDay || ForthWorkOutDay==ThirdWorkOutDay);

            updateData(String.valueOf(ForthWorkOutDay),(MainActivity.TRAINING5DAYPERWEEK3WORKOUT4));

        }
        else{
            ForthWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT4));
        }



        if( (get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT5)).equals("0")){
            do{
                FifthWorkOutDay = ((int) (Math.random() * 7))+1;
            }while( FifthWorkOutDay== FirstWorkOutDay ||  FifthWorkOutDay==SecondWorkOutDay ||  FifthWorkOutDay==ThirdWorkOutDay ||  FifthWorkOutDay==ForthWorkOutDay);

            updateData(String.valueOf( FifthWorkOutDay),(MainActivity.TRAINING5DAYPERWEEK3WORKOUT5));

        }
        else{
            FifthWorkOutDay=StringToInt(get_shared_info_string(MainActivity.TRAINING5DAYPERWEEK3WORKOUT5));
        }


        SelectedDays.add(FirstWorkOutDay);
        SelectedDays.add(SecondWorkOutDay);
        SelectedDays.add(ThirdWorkOutDay);
        SelectedDays.add(ForthWorkOutDay);
        SelectedDays.add(FifthWorkOutDay);


        switch((today.get(Calendar.WEEK_OF_MONTH))){
            case 1:
            case 4:
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        ResetDaysView();
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        ResetDaysView();
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        ResetDaysView();
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        ResetDaysView();
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        ResetDaysView();
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        ResetDaysView();
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        ResetDaysView();
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }

                break;
            case 3:
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        ResetDaysView();
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        ResetDaysView();
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        ResetDaysView();
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        ResetDaysView();
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        ResetDaysView();
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        ResetDaysView();
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        ResetDaysView();
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }

                break;
            case 2:
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        ResetDaysView();
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        ResetDaysView();
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        ResetDaysView();
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        ResetDaysView();
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        ResetDaysView();
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        ResetDaysView();
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        ResetDaysView();
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("B");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("C");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }
                switch (Collections.min(SelectedDays)) {
                    case 1:
                        TrainOnMonday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 2:
                        TrainOnTuesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 3:
                        TrainOnWednesday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 4:
                        TrainOnThursday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 5:
                        TrainOnFriday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 6:
                        TrainOnSaturday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;
                    case 7:
                        TrainOnSunday("A");
                        SelectedDays.remove((Collections.min(SelectedDays)));
                        break;

                }

                break;


        }



    }










    public void MakeItSixDayThreeWorkOut(){
        int Random = ((int) (Math.random() * 2))+1;
        switch(Random){
            case 1:
                MondayActivity.setText("WorkOut A");
                TuesdayActivity.setText("WorkOut B");
                WednesdayActivity.setText("WorkOut C");
                ThursdayActivity.setText("Rest");
                FridayActivity.setText("WorkOut A");
                SaturdayActivity.setText("WorkOut B");
                SundayActivity.setText("WorkOut C");
                ButtonMondayActivity.setText("Work\nOut A");
                ButtonTuesdayActivity.setText("Work\nOut B");
                ButtonWednesdayActivity.setText("Work\nOut C");
                ButtonThursdayActivity.setText("Rest");
                ButtonFridayActivity.setText("Work\nOut A");
                ButtonSaturdayActivity.setText("Work\nOut B");
                ButtonSundayActivity.setText("Work\nOut C");

                break;
            case 2:
                MondayActivity.setText("WorkOut A");
                TuesdayActivity.setText("WorkOut B");
                WednesdayActivity.setText("WorkOut C");
                ThursdayActivity.setText("WorkOut A");
                FridayActivity.setText("WorkOut B");
                SaturdayActivity.setText("WorkOut C");
                SundayActivity.setText("Rest");
                ButtonMondayActivity.setText("Work\nOut A");
                ButtonTuesdayActivity.setText("Work\nOut B");
                ButtonWednesdayActivity.setText("Work\nOut C");
                ButtonThursdayActivity.setText("Work\nOut A");
                ButtonFridayActivity.setText("Work\nOut B");
                ButtonSaturdayActivity.setText("Work\nOut C");
                ButtonSundayActivity.setText("Rest");

                break;

        }

    }











    public void MakeItSevenDayFourWorkOut(){
        Calendar today = Calendar.getInstance();
        switch((today.get(Calendar.WEEK_OF_MONTH))){
            case 1:
                MondayActivity.setText("WorkOut A");
                TuesdayActivity.setText("WorkOut B");
                WednesdayActivity.setText("WorkOut C");
                ThursdayActivity.setText("WorkOut D");
                FridayActivity.setText("WorkOut A");
                SaturdayActivity.setText("WorkOut B");
                SundayActivity.setText("WorkOut C");
                ButtonMondayActivity.setText("Work\nOut A");
                ButtonTuesdayActivity.setText("Work\nOut B");
                ButtonWednesdayActivity.setText("Work\nOut C");
                ButtonThursdayActivity.setText("Work\nOut D");
                ButtonFridayActivity.setText("Work\nOut A");
                ButtonSaturdayActivity.setText("Work\nOut B");
                ButtonSundayActivity.setText("Work\nOut C");

                break;
            case 2:
                MondayActivity.setText("WorkOut D");
                TuesdayActivity.setText("WorkOut A");
                WednesdayActivity.setText("WorkOut B");
                ThursdayActivity.setText("WorkOut C");
                FridayActivity.setText("WorkOut D");
                SaturdayActivity.setText("WorkOut A");
                SundayActivity.setText("WorkOut B");
                ButtonMondayActivity.setText("Work\nOut D");
                ButtonTuesdayActivity.setText("Work\nOut A");
                ButtonWednesdayActivity.setText("Work\nOut B");
                ButtonThursdayActivity.setText("Work\nOut C");
                ButtonFridayActivity.setText("Work\nOut D");
                ButtonSaturdayActivity.setText("Work\nOut A");
                ButtonSundayActivity.setText("Work\nOut B");

                break;
            case 3:
                MondayActivity.setText("WorkOut C");
                TuesdayActivity.setText("WorkOut D");
                WednesdayActivity.setText("WorkOut A");
                ThursdayActivity.setText("WorkOut B");
                FridayActivity.setText("WorkOut C");
                SaturdayActivity.setText("WorkOut D");
                SundayActivity.setText("WorkOut A");
                ButtonMondayActivity.setText("Work\nOut C");
                ButtonTuesdayActivity.setText("Work\nOut D");
                ButtonWednesdayActivity.setText("Work\nOut A");
                ButtonThursdayActivity.setText("Work\nOut B");
                ButtonFridayActivity.setText("Work\nOut C");
                ButtonSaturdayActivity.setText("Work\nOut D");
                ButtonSundayActivity.setText("Work\nOut A");

                break;
            case 4:
                MondayActivity.setText("WorkOut B");
                TuesdayActivity.setText("WorkOut C");
                WednesdayActivity.setText("WorkOut D");
                ThursdayActivity.setText("WorkOut A");
                FridayActivity.setText("WorkOut B");
                SaturdayActivity.setText("WorkOut C");
                SundayActivity.setText("WorkOut D");
                ButtonMondayActivity.setText("Work\nOut B");
                ButtonTuesdayActivity.setText("Work\nOut C");
                ButtonWednesdayActivity.setText("Work\nOut D");
                ButtonThursdayActivity.setText("Work\nOut A");
                ButtonFridayActivity.setText("Work\nOut B");
                ButtonSaturdayActivity.setText("Work\nOut C");
                ButtonSundayActivity.setText("Work\nOut D");

                break;


        }

    }

//////////Scroll View /////////////////////////


    public void ShowWorkOut(View view) {
        Button b = (Button)view;
        switch(b.getText().toString()){
            case "Work\nOut A":
                ShowWorkOutA();
                break;
            case "Work\nOut B":
                ShowWorkOutB();
                break;
            case "Work\nOut C":
                ShowWorkOutC();
                break;
            case "Work\nOut D":
                ShowWorkOutD();
                break;

        }


    }



    private void ShowWorkOutA(){
        ProgramScrollViewLinearLayout.removeAllViews();
        ProgramScrollViewLinearLayout.addView(TitleTopOfScrollView);

        ArrayList<String> SelectedExercises = new ArrayList<String>();


        ExercisesForWorkOutA = new ArrayList<Exercise>();
        if( (get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutA)).isEmpty()){


            MyProgram.MakeNewProgram();
            ExercisesForWorkOutA=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutA);
        }
        else{
            ExercisesForWorkOutA=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutA);

        }
        String WorkOutMusclesString= "";
        WorkOutName.setText("WorkOut A");
        SelectedExercises=MyProgram.GetExercisesStringForWorkout("A");

        for(String x:SelectedExercises){
            WorkOutMusclesString=WorkOutMusclesString+x;

            if((SelectedExercises.get(SelectedExercises.size()-1))!=x)
                WorkOutMusclesString=WorkOutMusclesString+", ";
        }

        WorkOutMuscles.setText(WorkOutMusclesString);
        for (Exercise X :ExercisesForWorkOutA){
            TextView Text=new TextView(this);
            Text.setText(X.GetExerciseName());
            ProgramScrollViewLinearLayout.addView(Text);
            Button cb = new Button(getApplicationContext());
            cb.setText("Expand "+X.GetExerciseName());
            cb.setTag(X);
            TempExercise=X;
            ProgramScrollViewLinearLayout.addView(cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    Exercise x=(Exercise) b.getTag();
                    ShowExerciseParametersForWorkOutA(x);
                }
            });


        }


    }


    private void ShowWorkOutB(){
        ProgramScrollViewLinearLayout.removeAllViews();
        ProgramScrollViewLinearLayout.addView(TitleTopOfScrollView);

        ArrayList<String> SelectedExercises = new ArrayList<String>();


        ExercisesForWorkOutB = new ArrayList<Exercise>();
        if( (get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutB)).isEmpty()){


            MyProgram.MakeNewProgram();
            ExercisesForWorkOutB=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutB);
        }
        else{
            ExercisesForWorkOutB=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutB);
        }
        String WorkOutMusclesString= "";
        WorkOutName.setText("WorkOut B");
        SelectedExercises=MyProgram.GetExercisesStringForWorkout("B");

        for(String x:SelectedExercises){
            WorkOutMusclesString=WorkOutMusclesString+x;

            if((SelectedExercises.get(SelectedExercises.size()-1))!=x)
                WorkOutMusclesString=WorkOutMusclesString+", ";
        }

        WorkOutMuscles.setText(WorkOutMusclesString);
        for (Exercise X :ExercisesForWorkOutB){
            TextView Text=new TextView(this);
            Text.setText(X.GetExerciseName());
            ProgramScrollViewLinearLayout.addView(Text);
            Button cb = new Button(getApplicationContext());
            cb.setText("Expand "+X.GetExerciseName());
            cb.setTag(X);
            TempExercise=X;
            ProgramScrollViewLinearLayout.addView(cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    Exercise x=(Exercise) b.getTag();
                    ShowExerciseParametersForWorkOutB(x);
                }
            });


        }


    }



    private void ShowWorkOutC(){
        ProgramScrollViewLinearLayout.removeAllViews();
        ProgramScrollViewLinearLayout.addView(TitleTopOfScrollView);

        ArrayList<String> SelectedExercises = new ArrayList<String>();


        ExercisesForWorkOutC = new ArrayList<Exercise>();
        if( (get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutC)).isEmpty()){


            MyProgram.MakeNewProgram();
            ExercisesForWorkOutC=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutC);
        }
        else{
            ExercisesForWorkOutC=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutC);
        }
        String WorkOutMusclesString= "";
        WorkOutName.setText("WorkOut C");
        SelectedExercises=MyProgram.GetExercisesStringForWorkout("C");

        for(String x:SelectedExercises){
            WorkOutMusclesString=WorkOutMusclesString+x;

            if((SelectedExercises.get(SelectedExercises.size()-1))!=x)
                WorkOutMusclesString=WorkOutMusclesString+", ";
        }

        WorkOutMuscles.setText(WorkOutMusclesString);
        for (Exercise X :ExercisesForWorkOutC){
            TextView Text=new TextView(this);
            Text.setText(X.GetExerciseName());
            ProgramScrollViewLinearLayout.addView(Text);
            Button cb = new Button(getApplicationContext());
            cb.setText("Expand "+X.GetExerciseName());
            cb.setTag(X);
            TempExercise=X;
            ProgramScrollViewLinearLayout.addView(cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    Exercise x=(Exercise) b.getTag();
                    ShowExerciseParametersForWorkOutC(x);
                }
            });


        }


    }



    private void ShowWorkOutD(){
        ProgramScrollViewLinearLayout.removeAllViews();
        ProgramScrollViewLinearLayout.addView(TitleTopOfScrollView);

        ArrayList<String> SelectedExercises = new ArrayList<String>();


        ExercisesForWorkOutD = new ArrayList<Exercise>();
        if( (get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutD)).isEmpty()){


            MyProgram.MakeNewProgram();
            ExercisesForWorkOutD=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutD);
        }
        else{
            ExercisesForWorkOutD=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutD);
        }
        String WorkOutMusclesString= "";
        WorkOutName.setText("WorkOut D");
        SelectedExercises=MyProgram.GetExercisesStringForWorkout("D");

        for(String x:SelectedExercises){
            WorkOutMusclesString=WorkOutMusclesString+x;

            if((SelectedExercises.get(SelectedExercises.size()-1))!=x)
                WorkOutMusclesString=WorkOutMusclesString+", ";
        }

        WorkOutMuscles.setText(WorkOutMusclesString);
        for (Exercise X :ExercisesForWorkOutD){
            TextView Text=new TextView(this);
            Text.setText(X.GetExerciseName());
            ProgramScrollViewLinearLayout.addView(Text);
            Button cb = new Button(getApplicationContext());
            cb.setText("Expand "+X.GetExerciseName());
            cb.setTag(X);
            TempExercise=X;
            ProgramScrollViewLinearLayout.addView(cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button b = (Button)view;
                    Exercise x=(Exercise) b.getTag();
                    ShowExerciseParametersForWorkOutD(x);
                }
            });


        }


    }






    private void ShowExerciseParametersForWorkOutA(Exercise X){
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
            Toast toast = Toast.makeText(getApplicationContext(),"not empty", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"empty", Toast.LENGTH_SHORT);
            toast.show();
        }



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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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

                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutA,MainActivity.ExercisesForWorkOutA);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);

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
                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutA,MainActivity.ExercisesForWorkOutA);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);

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

                ShowWorkOutA();

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


    private void ShowExerciseParametersForWorkOutB(Exercise X){
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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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

                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutB,MainActivity.ExercisesForWorkOutB);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);

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

                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutB,MainActivity.ExercisesForWorkOutB);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);

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

                ShowWorkOutB();

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




    private void ShowExerciseParametersForWorkOutC(Exercise X){
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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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


                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutC,MainActivity.ExercisesForWorkOutC);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);

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

                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutC,MainActivity.ExercisesForWorkOutC);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);

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

                ShowWorkOutC();

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






    private void ShowExerciseParametersForWorkOutD(Exercise X){
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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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
                TextView ShowSavedExerciseText=new TextView(TheProgramPage.this);
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


                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutD,MainActivity.ExercisesForWorkOutD);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);
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

                Gson_Update_Data_Exercies_Array(ExercisesForWorkOutD,MainActivity.ExercisesForWorkOutD);
                Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);

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

                ShowWorkOutD();

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









//////////functions for making life easy////////

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


    public int StringToInt(String Key){
        return(Integer.parseInt(Key));

    }
    public void ResetDaysView(){
        MondayActivity.setText("Rest");
        TuesdayActivity.setText("Rest");
        WednesdayActivity.setText("Rest");
        ThursdayActivity.setText("Rest");
        FridayActivity.setText("Rest");
        SaturdayActivity.setText("Rest");
        SundayActivity.setText("Rest");
        ButtonMondayActivity.setText("Rest");
        ButtonTuesdayActivity.setText("Rest");
        ButtonWednesdayActivity.setText("Rest");
        ButtonThursdayActivity.setText("Rest");
        ButtonFridayActivity.setText("Rest");
        ButtonSaturdayActivity.setText("Rest");
        ButtonSundayActivity.setText("Rest");

    }
    public Boolean DaysAreFine2(int a, int b){
        if(Math.abs(a-b)<=1){
            return (false);
        }
        else if(Math.abs(a-b)==6){
            return (false);
        }
        else if(a==b){
            return (false);
        }
        else{
            return (true);
        }
    }
    public Boolean DaysAreFine3(int a, int b,int c){
        if ((DaysAreFine2(a,b))&&(DaysAreFine2(a,c))&&(DaysAreFine2(c,b))){
            return (true);

        }
        else{
            return (false);
        }
    }

    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openOptions_Menu(){
        Intent intentOptions_Menu= new Intent(this,ProgramOptions.class);
        startActivity(intentOptions_Menu);
        finish();


    }
   public void UpdateOnceADay(){
       ExercisesForWorkOutA=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutA);
       ExercisesForWorkOutB=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutB);
       ExercisesForWorkOutC=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutC);
       ExercisesForWorkOutD=get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutD);
       GeneralExercise=get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES);
       if(!ExercisesForWorkOutA.isEmpty()){
           for (Exercise X :ExercisesForWorkOutA){
               X.SetPrevCurrentWeightPerSet();
               X.SetPrevCurrentRepsPerSet();
               if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                   X.AddRecordToArray();
               }

           }
           Gson_Update_Data_Exercies_Array(ExercisesForWorkOutA,MainActivity.ExercisesForWorkOutA);
       }
       if(!ExercisesForWorkOutB.isEmpty()){
           for (Exercise X :ExercisesForWorkOutB){
               X.SetPrevCurrentWeightPerSet();
               X.SetPrevCurrentRepsPerSet();
               X.AddRecordToArray();
           }
           Gson_Update_Data_Exercies_Array(ExercisesForWorkOutB,MainActivity.ExercisesForWorkOutB);
       }
       if(!ExercisesForWorkOutC.isEmpty()){
           for (Exercise X :ExercisesForWorkOutC){
               X.SetPrevCurrentWeightPerSet();
               X.SetPrevCurrentRepsPerSet();
               X.AddRecordToArray();
           }
           Gson_Update_Data_Exercies_Array(ExercisesForWorkOutC,MainActivity.ExercisesForWorkOutC);
       }
       if(!ExercisesForWorkOutD.isEmpty()){
           for (Exercise X :ExercisesForWorkOutD){
               X.SetPrevCurrentWeightPerSet();
               X.SetPrevCurrentRepsPerSet();
               X.AddRecordToArray();

           }
           Gson_Update_Data_Exercies_Array(ExercisesForWorkOutD,MainActivity.ExercisesForWorkOutD);
       }

       if(!GeneralExercise.isEmpty()){

           Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);
       }



   }


    public void ResetExerciseRelevantInfo(){
        GeneralExercise=get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES);
        if(!GeneralExercise.isEmpty()){
            for (Exercise X :GeneralExercise){
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }
            Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES);
        }

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
    public static TheProgramPage getInstance(){
        return instance;
    }
    private void startRepeatingTask() {
        mStatusChecker.run();
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




    public void Gson_Empty_UpdateData(String Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

    }














}