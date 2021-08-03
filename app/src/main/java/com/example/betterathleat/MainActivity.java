package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button AdvancedUserInfo;
    private Button ShowProgram;
    private Button ShowMenu;
    private Button CreateOwnProgram;
    private Button AddNewExercise;
    private Button CreateOwnMenu;
    private Button AddNewFood;

    private Gson gson=new Gson();


    ///////User Strings/////////
    private User user;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String NAME="Name";
    public static final String AGE="Age";
    public static final String WEIGHT="Weight";
    public static final String HEIGHT="Height";
    public static final String HELP="What Can I Do For You";
    public static final String PURPOSE="Purpose";

    public static final String TRAININGTIME="TrainingTime";
    public static final String LIFTREC="LiftRecord";
    public static final String RANGES="MostUsedRepRange";
    public static final String TRAININGSTYLE="TrainingStyle";
    public static final String PROGRAMS="NumberOfAccomplishedPrograms";

    public static final String WORKOUTNUM="WorkOutPerWeek";
    public static final String SERIOUSNESS="TrainigSeriousness";

    public static final String FOODIDEOLOGY="FoodIdeology";
    public static final String LIKEDFOODS="LikedFoods";
    public static final String FINISHED="USER CREATED";
    public static final String PASSUSER="PassUser";
    ///////Program Strings/////////
    ///////Remember WorkOuts Days///////
    public static final String TRAINING1DAYPERWEEK1WORKOUT="TRAINING 1 DAY PER WEEK 1 WorkOut";

    public static final String TRAINING2DAYPERWEEK2WORKOUT1="TRAINING 2 DAY PER WEEK 2 WorkOut1";
    public static final String TRAINING2DAYPERWEEK2WORKOUT2="TRAINING 2 DAY PER WEEK 2 WorkOut2";

    public static final String TRAINING3DAYPERWEEK2WORKOUT1="TRAINING 3 DAY PER WEEK 2 WorkOut1";
    public static final String TRAINING3DAYPERWEEK2WORKOUT2="TRAINING 3 DAY PER WEEK 2 WorkOut2";
    public static final String TRAINING3DAYPERWEEK2WORKOUT3="TRAINING 3 DAY PER WEEK 2 WorkOut3";

    public static final String TRAINING4DAYPERWEEK2WORKOUT1="TRAINING 4 DAY PER WEEK 2 WorkOut1";
    public static final String TRAINING4DAYPERWEEK2WORKOUT2="TRAINING 4 DAY PER WEEK 2 WorkOut2";
    public static final String TRAINING4DAYPERWEEK2WORKOUT3="TRAINING 4 DAY PER WEEK 2 WorkOut3";
    public static final String TRAINING4DAYPERWEEK2WORKOUT4="TRAINING 4 DAY PER WEEK 2 WorkOut4";


    public static final String TRAINING3DAYPERWEEK3WORKOUT1="TRAINING 3 DAY PER WEEK 3 WorkOut1";
    public static final String TRAINING3DAYPERWEEK3WORKOUT2="TRAINING 3 DAY PER WEEK 3 WorkOut2";
    public static final String TRAINING3DAYPERWEEK3WORKOUT3="TRAINING 3 DAY PER WEEK 3 WorkOut3";

    public static final String TRAINING4DAYPERWEEK3WORKOUT1="TRAINING 4 DAY PER WEEK 3 WorkOut1";
    public static final String TRAINING4DAYPERWEEK3WORKOUT2="TRAINING 4 DAY PER WEEK 3 WorkOut2";
    public static final String TRAINING4DAYPERWEEK3WORKOUT3="TRAINING 4 DAY PER WEEK 3 WorkOut3";
    public static final String TRAINING4DAYPERWEEK3WORKOUT4="TRAINING 4 DAY PER WEEK 3 WorkOut4";


    public static final String TRAINING5DAYPERWEEK3WORKOUT1="TRAINING 5 DAY PER WEEK 3 WorkOut1";
    public static final String TRAINING5DAYPERWEEK3WORKOUT2="TRAINING 5 DAY PER WEEK 3 WorkOut2";
    public static final String TRAINING5DAYPERWEEK3WORKOUT3="TRAINING 5 DAY PER WEEK 3 WorkOut3";
    public static final String TRAINING5DAYPERWEEK3WORKOUT4="TRAINING 5 DAY PER WEEK 3 WorkOut4";
    public static final String TRAINING5DAYPERWEEK3WORKOUT5="TRAINING 5 DAY PER WEEK 3 WorkOut5";
    ///////Remember WorkOut Exercises///////
    public static final String ExercisesForWorkOutA="Exercises For WorkOut A";
    public static final String ExercisesForWorkOutB="Exercises For WorkOut B";
    public static final String ExercisesForWorkOutC="Exercises For WorkOut C";
    public static final String ExercisesForWorkOutD="Exercises For WorkOut D";
    public static final String GENERALEXERCISES="ALL Exercises";

    public static final String PASSMYPROGRAM="PassProgram";


    //////Use Default Or Own Program/////
    public static final String USEDEFAULTPROGRAM="Use Default Program";

    //////Own Program/////
    public static final String OwnExercisesForWorkOutA="Own Exercises For WorkOut A";
    public static final String OwnExercisesForWorkOutB="Own Exercises For WorkOut B";
    public static final String OwnExercisesForWorkOutC="Own Exercises For WorkOut C";
    public static final String OwnExercisesForWorkOutD="Own Exercises For WorkOut D";
    public static final String OwnExercisesForWorkOutE="Own Exercises For WorkOut E";
    public static final String OwnExercisesForWorkOutF="Own Exercises For WorkOut F";
    public static final String OwnExercisesForWorkOutG="Own Exercises For WorkOut G";
    public static final String OWNGENERALEXERCISES="Own ALL Exercises";
    public static final String OwnDaysFirst="First Day Status";
    public static final String OwnDaysSecond="Second Day Status";
    public static final String OwnDaysThird="Third Day Status";
    public static final String OwnDaysFourth="Fourth Day Status";
    public static final String OwnDaysFifth="Fifth Day Status";
    public static final String OwnDaysSixth="Sixth Day Status";
    public static final String OwnDaysSeventh="Seventh Day Status";
    public static final String ROTATIONS="Rotations";

    /////Menu//////
    public static final String NUMBEROFMEALS="NumberOfMeals";
    public static final String THEMEALS="The Meals";
    public static final String THEMENU="The Menu";

    /////Create And Delete Foods//////
    public static final String ADDITIONALFOODS="Additional Foods";

    /////Own Menu//////
    public static final String OWNMEALS="Own Meals";

    private TextView test;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdvancedUserInfo =(Button) findViewById(R.id.AdvancedUserInfo);

        ShowProgram=(Button) findViewById(R.id.MyWorkOutProgram);
        ShowMenu=(Button) findViewById(R.id.MyEatingMenu);
        CreateOwnProgram=(Button) findViewById(R.id.CreateOwnProgram);
        AddNewExercise=(Button) findViewById(R.id.AddNewExercise);
        CreateOwnMenu=(Button) findViewById(R.id.CreateYourOwnMenu);
        AddNewFood=(Button) findViewById(R.id.AddNewFood);

        this.context = getApplicationContext();



        test= (TextView) findViewById(R.id.Test) ;
        CreateFoodArray();
        InitiateUser();

                user=new User(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.NAME,context.getApplicationContext()),
                        FunctionsToSaveInMemory.StringToDouble(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WEIGHT,context.getApplicationContext())),
                        FunctionsToSaveInMemory.StringToDouble(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.HEIGHT,context.getApplicationContext())),
                        FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.AGE,context.getApplicationContext())),
                        FunctionsToSaveInMemory.get_shared_info_string(MainActivity.HELP,context.getApplicationContext()),
                        FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PURPOSE,context.getApplicationContext()),
                        FunctionsToSaveInMemory.StringToDouble(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGTIME,context.getApplicationContext())),
                        FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGSTYLE,context.getApplicationContext()),
                        FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM,context.getApplicationContext())),
                        FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.SERIOUSNESS,context.getApplicationContext())),
                        FunctionsToSaveInMemory.convertStringToArray(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext())),
                        FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.RANGES,context.getApplicationContext())),
                        FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PROGRAMS,context.getApplicationContext())),
                        FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FOODIDEOLOGY,context.getApplicationContext()),
                        FunctionsToSaveInMemory.convertStringToArray(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIKEDFOODS,context.getApplicationContext())),
                        FunctionsToSaveInMemory.StringToBoolean(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FINISHED,context.getApplicationContext())));

        FunctionsToSaveInMemory.Gson_Update_Data_User(user,PASSUSER,context.getApplicationContext());



        if (user.GetFinish()==false)
        {
            GoToBasicUserInfo();
        }

        CreateExercisesArray();
        AdvancedUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestionMenu();


            }
        });

       /* ResumeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (user.WhereAmI()){
                    case"1":
                        Intent intentName= new Intent(MainActivity.this, QName.class);
                        startActivity(intentName);
                        finish();
                        break;
                    case"2":
                        Intent intentAge= new Intent(MainActivity.this, QAge.class);
                        startActivity(intentAge);
                        finish();
                        break;
                    case"3":
                        Intent intentWeight= new Intent(MainActivity.this, QWeight.class);
                        startActivity(intentWeight);
                        finish();
                        break;
                    case"4":
                        Intent intentHeight= new Intent(MainActivity.this, QHeight.class);
                        startActivity(intentHeight);
                        finish();
                        break;
                    case"5":
                        Intent intentHelp= new Intent(MainActivity.this,Help.class);
                        startActivity(intentHelp);
                        finish();
                        break;
                    case"6":
                        Intent intentGoal= new Intent(MainActivity.this, QGoal.class);
                        startActivity(intentGoal);
                        finish();
                        break;
                    case"7":
                        Intent intentTrainingExp= new Intent(MainActivity.this, QExp.class);
                        startActivity(intentTrainingExp);
                        finish();
                        break;
                    case"8":
                        Intent intentLiftRec= new Intent(MainActivity.this, QLiftRecord.class);
                        startActivity(intentLiftRec);
                        finish();
                        break;
                    case"9":
                        Intent intentRepRange= new Intent(MainActivity.this, QRepRange.class);
                        startActivity(intentRepRange);
                        finish();
                        break;
                    case"10":
                        Intent intentTrainingStyle= new Intent(MainActivity.this, QTrainingStyle.class);
                        startActivity(intentTrainingStyle);
                        finish();
                        break;
                    case"11":
                        Intent intentFat9= new Intent(MainActivity.this, QPastProgramNum.class);
                        startActivity(intentFat9);
                        finish();
                        break;
                    case"12":
                        Intent intentFat11= new Intent(MainActivity.this, QWorkOutsPerWeek.class);
                        startActivity(intentFat11);
                        finish();
                        break;
                    case"13":
                        Intent intentFat12= new Intent(MainActivity.this, QMotivation_Seriousness.class);
                        startActivity(intentFat12);
                        finish();
                        break;
                    case"14":
                        Intent intentFat13= new Intent(MainActivity.this, QFoodIdeology.class);
                        startActivity(intentFat13);
                        finish();
                        break;
                    case"15":
                        Intent intentFat14= new Intent(MainActivity.this, QLikedFoods.class);
                        startActivity(intentFat14);
                        finish();
                        break;
                    case"16":
                        Intent intentFat15= new Intent(MainActivity.this, QFinish.class);
                        startActivity(intentFat15);
                        finish();
                        break;

                }
            }
        });*/


        ShowProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGTIME,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGTIME ,context.getApplicationContext()).equals("") ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGSTYLE,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGSTYLE ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.SERIOUSNESS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.SERIOUSNESS ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext() ).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.RANGES,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.RANGES ,context.getApplicationContext()).equals(""))||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.PROGRAMS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PROGRAMS ,context.getApplicationContext()).equals(""))){
                    Toast toast = Toast.makeText(getApplicationContext(),"You Didn't Answer All Of The Relevant Questions " , Toast.LENGTH_SHORT);
                    toast.show();

                }
                else{
                    openShowProgram();
                }


            }
        });
        ShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((FunctionsToSaveInMemory.get_shared_info_string( MainActivity.FOODIDEOLOGY,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FOODIDEOLOGY ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.LIKEDFOODS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIKEDFOODS ,context.getApplicationContext()).equals(""))){
                    Toast toast = Toast.makeText(getApplicationContext(),"You Didn't Answer All Of The Relevant Questions " , Toast.LENGTH_SHORT);
                    toast.show();

                }
                else{
                    openShowMenu();
                }



            }
        });


        CreateOwnProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGTIME,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGTIME,context.getApplicationContext() ).equals("") ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGSTYLE,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGSTYLE ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.SERIOUSNESS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.SERIOUSNESS,context.getApplicationContext() ).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.RANGES,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.RANGES ,context.getApplicationContext()).equals(""))||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.PROGRAMS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PROGRAMS ,context.getApplicationContext()).equals(""))){

                    Toast toast = Toast.makeText(getApplicationContext(),"You Didn't Answer All Of The Relevant Questions " , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    openCreateOwnProgram();
                }


            }
        });


        AddNewExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGTIME,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGTIME,context.getApplicationContext() ).equals("") ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGSTYLE,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGSTYLE ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM,context.getApplicationContext() ).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.SERIOUSNESS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.SERIOUSNESS ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC ,context.getApplicationContext()).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.RANGES,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.RANGES ,context.getApplicationContext()).equals(""))||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.PROGRAMS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PROGRAMS ,context.getApplicationContext()).equals(""))){

                    Toast toast = Toast.makeText(getApplicationContext(),"You Didn't Answer All Of The Relevant Questions " , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    openAddNewExercise();
                }


            }
        });

        CreateOwnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((FunctionsToSaveInMemory.get_shared_info_string( MainActivity.FOODIDEOLOGY,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FOODIDEOLOGY,context.getApplicationContext() ).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.LIKEDFOODS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIKEDFOODS ,context.getApplicationContext()).equals(""))){

                    Toast toast = Toast.makeText(getApplicationContext(),"You Didn't Answer All Of The Relevant Questions " , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    openCreateOwnMenu();
                }



            }
        });


        AddNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((FunctionsToSaveInMemory.get_shared_info_string( MainActivity.FOODIDEOLOGY,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FOODIDEOLOGY,context.getApplicationContext() ).equals("")) ||
                        (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.LIKEDFOODS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIKEDFOODS,context.getApplicationContext() ).equals(""))){
                    Toast toast = Toast.makeText(getApplicationContext(),"You Didn't Answer All Of The Relevant Questions " , Toast.LENGTH_SHORT);
                    toast.show();

                }
                else{
                    openAddNewFood();
                }


            }
        });






    }







    public void openQ1(){
        Intent intentQ1= new Intent(this, QName.class);
        startActivity(intentQ1);

    }
    public void openQuestionMenu(){
        Intent intentQuestionMenu= new Intent(this,AdvancedUserInfo.class);
        startActivity(intentQuestionMenu);

    }
    public void openAreYouSure(){
        Intent intentQAreYouSure= new Intent(this,AreYouSure.class);
        startActivity(intentQAreYouSure);

    }
    public void openShowProgram(){
        Intent intentShowProgram= new Intent(this,TheProgramPage.class);
        startActivity(intentShowProgram);

    }


    public void openCreateOwnProgram(){
        Intent intentCreateOwnProgram= new Intent(this,CreateYourOwnProgram.class);
        startActivity(intentCreateOwnProgram);

    }


    public void openAddNewExercise(){
        Intent intentAddNewExercise= new Intent(this,CreateNewExercise.class);
        startActivity(intentAddNewExercise);

    }

    public void openCreateOwnMenu(){
        Intent intentCreateOwnMenu= new Intent(this,CreateYourOwnMenu.class);
        startActivity(intentCreateOwnMenu);

    }

    public void openAddNewFood(){
        Intent intentAddNewFood= new Intent(this,CreateNewFood.class);
        startActivity(intentAddNewFood);

    }

    


    public void openShowMenu(){
        Intent intentShowMenu= new Intent(this, FoodMenu.class);
        startActivity(intentShowMenu);

    }

    public void GoToBasicUserInfo(){
        Intent intentGoToBasicUserInfo= new Intent(this, BasicUserInfo.class);
        startActivity(intentGoToBasicUserInfo);
        finish();

    }











    public void DeleteOldData(){
        FunctionsToSaveInMemory.updateData("0",MainActivity.NAME,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.WEIGHT,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.HEIGHT,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.AGE,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.HELP,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.PURPOSE,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAININGTIME,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAININGSTYLE,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.WORKOUTNUM,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.SERIOUSNESS,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.LIFTREC,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.RANGES,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.PROGRAMS,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.FOODIDEOLOGY,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.LIKEDFOODS,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.FINISHED,this.context);
        ResetDaysForProgram();
        ResetExercisesForProgram();
        ResetExerciseRelevantInfo();
        ResetMenuStuff();






    }

    public void ResetDaysForProgram(){
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING1DAYPERWEEK1WORKOUT,this.context);

        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING2DAYPERWEEK2WORKOUT1,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING2DAYPERWEEK2WORKOUT2,this.context);

        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING3DAYPERWEEK2WORKOUT1,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING3DAYPERWEEK2WORKOUT2,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING3DAYPERWEEK2WORKOUT3,this.context);

        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT1,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT2,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT3,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK2WORKOUT4,this.context);


        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING3DAYPERWEEK3WORKOUT1,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING3DAYPERWEEK3WORKOUT2,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING3DAYPERWEEK3WORKOUT3,this.context);

        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT1,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT2,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT3,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING4DAYPERWEEK3WORKOUT4,this.context);


        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT1,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT2,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT3,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT4,this.context);
        FunctionsToSaveInMemory.updateData("0",MainActivity.TRAINING5DAYPERWEEK3WORKOUT5,this.context);




        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnDaysFirst,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnDaysSecond,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnDaysThird,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnDaysFourth,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnDaysFifth,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnDaysSixth,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnDaysSeventh,this.context);


    }


    public void ResetExercisesForProgram(){

        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutA,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutB,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutC,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.ExercisesForWorkOutD,this.context);


        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutA,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutB,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutC,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutD,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutE,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutF,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.OwnExercisesForWorkOutG,this.context);


    }

    public void ResetExerciseRelevantInfo(){
        ArrayList<Exercise> ExercisesForWorkOutA=FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutA,this.context);
        ArrayList<Exercise> ExercisesForWorkOutB=FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutB,this.context);
        ArrayList<Exercise> ExercisesForWorkOutC=FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutC,this.context);
        ArrayList<Exercise> ExercisesForWorkOutD=FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array(MainActivity.ExercisesForWorkOutD,this.context);
        ArrayList<Exercise> GeneralExercise=FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES,this.context);
        if(!GeneralExercise.isEmpty()){
            for (Exercise X :GeneralExercise){
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }

            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(GeneralExercise,MainActivity.GENERALEXERCISES,this.context);
        }
        if(!ExercisesForWorkOutA.isEmpty()) {
            for (Exercise X : ExercisesForWorkOutA) {
                X.ResetPrevCurrentRepsPerSet();
                X.ResetPrevCurrentWeightPerSet();

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(ExercisesForWorkOutA,MainActivity.ExercisesForWorkOutA,this.context);
        }

            if(!ExercisesForWorkOutB.isEmpty()) {
                for (Exercise X : ExercisesForWorkOutB) {
                    X.ResetPrevCurrentRepsPerSet();
                    X.ResetPrevCurrentWeightPerSet();

                }
                FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(ExercisesForWorkOutB,MainActivity.ExercisesForWorkOutB,this.context);
            }

                if(!ExercisesForWorkOutC.isEmpty()) {
                    for (Exercise X : ExercisesForWorkOutC) {
                        X.ResetPrevCurrentRepsPerSet();
                        X.ResetPrevCurrentWeightPerSet();

                    }
                    FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(ExercisesForWorkOutC,MainActivity.ExercisesForWorkOutC,this.context);
                }

                    if(!ExercisesForWorkOutD.isEmpty()) {
                        for (Exercise X : ExercisesForWorkOutD) {
                            X.ResetPrevCurrentRepsPerSet();
                            X.ResetPrevCurrentWeightPerSet();

                        }
                        FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(ExercisesForWorkOutD,MainActivity.ExercisesForWorkOutD,this.context);
                    }




        ArrayList<Exercise> OwnExercisesForWorkOutA =FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutA,this.context);
        ArrayList<Exercise> OwnExercisesForWorkOutB =FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutB,this.context);
        ArrayList<Exercise> OwnExercisesForWorkOutC =FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutC,this.context);
        ArrayList<Exercise> OwnExercisesForWorkOutD =FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutD,this.context);
        ArrayList<Exercise> OwnExercisesForWorkOutE =FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutE,this.context);
        ArrayList<Exercise> OwnExercisesForWorkOutF =FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutF,this.context);
        ArrayList<Exercise> OwnExercisesForWorkOutG =FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array( MainActivity.OwnExercisesForWorkOutG,this.context);
        ArrayList<Exercise> GeneralExercises=FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array(MainActivity.OWNGENERALEXERCISES,this.context);
        if(!OwnExercisesForWorkOutA.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutA){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutA,MainActivity.OwnExercisesForWorkOutA,this.context);
        }

        if(!OwnExercisesForWorkOutB.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutB){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutB,MainActivity.OwnExercisesForWorkOutB,this.context);
        }


        if(!OwnExercisesForWorkOutC.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutC){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutC,MainActivity.OwnExercisesForWorkOutC,this.context);
        }


        if(!OwnExercisesForWorkOutD.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutD){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutD,MainActivity.OwnExercisesForWorkOutD,this.context);
        }


        if(!OwnExercisesForWorkOutE.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutE){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutE,MainActivity.OwnExercisesForWorkOutE,this.context);
        }

        if(!OwnExercisesForWorkOutF.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutF){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutF,MainActivity.OwnExercisesForWorkOutF,this.context);
        }


        if(!OwnExercisesForWorkOutG.isEmpty()){
            for (Exercise X :OwnExercisesForWorkOutG){
                X.SetPrevCurrentWeightPerSet();
                X.SetPrevCurrentRepsPerSet();
                if(X.GetHighestRecord()>0 && X.GetHighestRecord()!=null){
                    X.AddRecordToArray();
                }

            }
            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(OwnExercisesForWorkOutG,MainActivity.OwnExercisesForWorkOutG,this.context);
        }


        if(!GeneralExercises.isEmpty()){

            FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(GeneralExercises,MainActivity.OWNGENERALEXERCISES,this.context);
        }



    }

    public void ResetMenuStuff(){


        FunctionsToSaveInMemory.updateData("0",MainActivity.NUMBEROFMEALS,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.THEMENU,this.context);
        FunctionsToSaveInMemory.Gson_Empty_UpdateData("0",MainActivity.THEMEALS,this.context);



    }













    public void CreateFoodArray(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        Foods Pasta= new Foods(100,131.0,5.0,1.1,25.0,"Cooked Pasta","Carbs");
        Foods WhiteRice= new Foods(100,130.0,2.7,0.3,28.0,"Cooked White Rice","Carbs");
        Foods WholePasta= new Foods(100,118.0,5.4,1.7,26.0,"Whole Wheat Cooked Pasta","Carbs");
        Foods BrownRice= new Foods(100,133.0,3.2,0.7,28.3,"Cooked Brown Rice","Carbs");
        Foods Potato= new Foods(100,77.0,2.0,0.2,17.0,"Potato","Carbs");
        Foods SweetPotato= new Foods(100,86.0,1.6,0.1,20.0,"Sweet Potato","Carbs");
        Foods Salmon= new Foods(100,208.0,20.0,3.8,0.0,"Salmon","Fish");
        Foods Tuna= new Foods(100,132.0,28.0,1.3,0.0,"Tuna","Fish");
        Foods Beef= new Foods(100,250.0,26.0,15.0,0.0,"Beef","Meat");
        Foods Mushroom= new Foods(100,22.0,3.1,0.3,3.3,"Mushroom","Vegetable");
        Foods ChickenThigh= new Foods(100,177.0,24.0,8.0,0.0,"Chicken Thigh","Meat");
        Foods CheddarCheese= new Foods(100,402.0,25.0,33.0,1.3,"Cheddar Cheese","Milk");
        Foods WhiteBread= new Foods(100,265.0,9.0,3.2,49.0,"White Bread","Carbs");
        Foods BrownBread= new Foods(100,313.0,13.0,4.3,56.0,"Brown Bread","Carbs");
        Foods Pita= new Foods(100,275.0,9.0,1.2,56.0,"Pita","Carbs");
        Foods Hummus= new Foods(100,166.0,8.0,10.0,14.0,"Hummus","Fats");
        Foods Tofu= new Foods(100,76.0,8.0,4.8,1.9,"Tofu","MeatSub");
        Foods Falafel= new Foods(100,333.0,13.0,18.0,32.0,"Falafel","Carbs");
        Foods Spinach= new Foods(100,24.0,2.9,0.8,1.3,"Spinach","Vegetable");
        Foods Lettuce= new Foods(100,15.0,1.4,0.2,2.9,"Lettuce","Vegetable");
        Foods Carrot= new Foods(100,41.0,0.9,0.2,10.0,"Carrot","Vegetable");
        Foods Apple= new Foods(100,52.0,0.3,0.2,14.0,"Apple","Fruit");
        Foods Orange= new Foods(100,47.0,0.9,0.1,12.0,"Orange","Fruit");
        Foods KiwiFruit= new Foods(100,61.0,1.1,0.5,15.0,"Kiwi Fruit","Fruit");
        Foods Grape= new Foods(100,67.0,0.6,0.4,17.0,"Grape","Fruit");
        Foods Cherry= new Foods(100,50.0,1.0,0.3,12.0,"Cherry","Fruit");
        Foods Peach= new Foods(100,39.0,0.9,0.3,10.0,"Peach","Fruit");
        Foods Apricot= new Foods(100,48.0,1.4,0.4,11.0,"Apricot","Fruit");
        Foods Muskmelon= new Foods(100,34.0,0.8,0.2,8.0,"Muskmelon","Fruit");
        Foods Avocado= new Foods(100,160.0,2.0,15.0,9.0,"Avocado","Fats");
        Foods Blueberry= new Foods(100,57.0,0.7,0.3,14.0,"Blueberry","Fruit");
        Foods Blackberry= new Foods(100,43.0,1.4,0.5,10.0,"Blackberry","Fruit");
        Foods Onion= new Foods(100,40.0,1.1,0.1,9.0,"Onion","Vegetable");
        Foods Banana= new Foods(100,89.0,1.1,0.3,23.0,"Banana","Fruit");
        Foods Watermelon= new Foods(100,30.0,0.6,0.2,8.0,"Watermelon","Fruit");
        Foods Pumpkin= new Foods(100,26.0,1.0,0.1,7.0,"Pumpkin","Vegetable");
        Foods Strawberry= new Foods(100,33.0,0.7,0.3,8.0,"Strawberry","Fruit");
        Foods Cabbage= new Foods(100,25.0,1.3,0.1,6.0,"Cabbage","Vegetable");
        Foods Cauliflower= new Foods(100,25.0,1.9,0.3,5.0,"Cauliflower","Vegetable");
        Foods Asparagus= new Foods(100,20.0,2.2,0.1,3.9,"Asparagus","Vegetable");
        Foods Beetroot= new Foods(100,43.0,1.6,0.2,10.0,"Beetroot","Vegetable");
        Foods Tomato= new Foods(100,18.0,0.9,0.2,3.9,"Tomato","Vegetable");
        Foods Cucumber= new Foods(100,10.0,0.7,0.1,1.5,"Cucumber","Vegetable");
        Foods Watercress= new Foods(100,11.0,2.3,0.1,1.3,"Watercress","Vegetable");
        Foods Parsley= new Foods(100,36.0,3.0,0.8,6.0,"Parsley","Vegetable");
        Foods Eggplant= new Foods(100,25.0,1.0,0.2,6.0,"Eggplant","Vegetable");
        Foods Courgette= new Foods(100,17.0,1.2,0.3,3.1,"Courgette","Vegetable");
        Foods Chickpea= new Foods(100,364.0,19.0,6.0,61.0,"Chickpea","Carbs");
        Foods Tahini= new Foods(100,595.0,17.0,54.0,21.0,"Tahini","Fats");
        Foods Tortilla= new Foods(100,237.0,7.0,1.0,50.0,"Tortilla","Carbs");
        Foods Egg= new Foods(100,143.0,13.0,10.0,0.7,"Egg","Egg");
        Foods PeanutButter= new Foods(100,588.0,25.0,50.0,20.0,"Peanut Butter","Fats");
        Foods BeefClod= new Foods(100,182.0,26.0,8.0,0.0,"Beef Clod","Meat");
        Foods Popcorn= new Foods(100,375.0,11.0,4.3,74.0,"Popcorn","Carbs");
        Foods CottageCheese= new Foods(100,98.0,11.0,4.3,3.4,"Cottage Cheese","Milk");
        Foods Oatmeal= new Foods(100,68.0,2.4,1.4,12.0,"Oatmeal","Carbs");




       // test.setText(get_shared_info_string(WEIGHT));




    }




    public void CreateExercisesArray(){
       if(FunctionsToSaveInMemory.get_shared_info_Gson_Exercise_Array(MainActivity.OWNGENERALEXERCISES,this.context).isEmpty()){
           ArrayList<Exercise> GeneralExercises = new ArrayList<Exercise>();
           ////Chest////
           Exercise Dips= new Exercise("Dips","Big","Chest",0,FunctionsToSaveInMemory.StringToDouble(user.GetLiftRecord()[5]),user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(Dips);
           Exercise BarbellBenchPress= new Exercise("Barbell Bench Press","Big","Chest",0,FunctionsToSaveInMemory.StringToDouble(user.GetLiftRecord()[0]),user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(BarbellBenchPress);
           Exercise DumbbellBenchPress= new Exercise("Dumbbell Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellBenchPress);
           Exercise AlternateDumbbellBenchPress= new Exercise("Alternate Dumbbell Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateDumbbellBenchPress);

           Exercise BarbellInclineBenchPress= new Exercise("Barbell Incline Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(BarbellInclineBenchPress);
           Exercise DumbbellInclineBenchPress= new Exercise("Dumbbell Incline Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellInclineBenchPress);
           Exercise AlternateDumbbellInclineBenchPress= new Exercise("Alternate Dumbbell Incline Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateDumbbellInclineBenchPress);

           Exercise BarbellDeclineBenchPress= new Exercise("Barbell Decline Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(BarbellDeclineBenchPress);
           Exercise DumbbellDeclineBenchPress= new Exercise("Dumbbell Decline Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellDeclineBenchPress);
           Exercise AlternateDumbbellDeclineBenchPress= new Exercise("Alternate Dumbbell Decline Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateDumbbellDeclineBenchPress);

           Exercise CloseGripBarbellBenchPress= new Exercise("Close Grip Barbell Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripBarbellBenchPress);
           Exercise CloseGripDumbbellBenchPress= new Exercise("Close Grip Dumbbell Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripDumbbellBenchPress);
           Exercise AlternateCloseGripDumbbellBenchPress= new Exercise("Alternate Close Grip Dumbbell Bench Press","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateCloseGripDumbbellBenchPress);

           Exercise DumbbellFly= new Exercise("Dumbbell Fly","Small","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellFly);
           Exercise InclineDumbbellFly= new Exercise("Incline Dumbbell Fly","Small","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(InclineDumbbellFly);
           Exercise FloorDumbbellFly= new Exercise("Floor Dumbbell Fly","Big","Chest",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(FloorDumbbellFly);







           ////Legs////
           Exercise Squat= new Exercise("Squat","Big","Legs",0,FunctionsToSaveInMemory.StringToDouble(user.GetLiftRecord()[1]),user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(Squat);

           Exercise LegPress= new Exercise("Leg Press","Big","Legs",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(LegPress);

           Exercise DeadLift= new Exercise("DeadLift","Big","Legs",0,FunctionsToSaveInMemory.StringToDouble(user.GetLiftRecord()[3]),user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DeadLift);
           Exercise SumoDeadLift= new Exercise("Sumo DeadLift","Big","Legs",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(SumoDeadLift);

           Exercise CalfRaisesMachine= new Exercise("Calf Raises Machine","Small","Legs",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CalfRaisesMachine);
           Exercise CalfRaises= new Exercise("Calf Raises","Small","Legs",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CalfRaises);
           Exercise SeatedCalfRaisesMachine= new Exercise("Seated Calf Raises Machine","Small","Legs",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(SeatedCalfRaisesMachine);

           Exercise Lunge= new Exercise("Lunge","Small","Legs",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(Lunge);
           Exercise OneLeggedCableKickback= new Exercise("One-Legged Cable Kickback","Small","Legs",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(OneLeggedCableKickback);






           ////Back////
           Exercise PullUps= new Exercise("Pull Ups","Big","Back",0,FunctionsToSaveInMemory.StringToDouble(user.GetLiftRecord()[4]),user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(PullUps);
           Exercise ChinUps= new Exercise("Chin Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(ChinUps);
           Exercise HammerPullUps= new Exercise("Hammer Pull Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(HammerPullUps);

           Exercise CloseGripPullUps= new Exercise("Close Grip Pull Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripPullUps);
           Exercise CloseGripChinUps= new Exercise("Close Grip Chin Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripChinUps);
           Exercise CloseGripHammerPullUps= new Exercise("Close Grip Hammer Pull Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripHammerPullUps);

           Exercise WideGripPullUps= new Exercise("Wide Grip Pull Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(WideGripPullUps);
           Exercise WideGripChinUps= new Exercise("Wide Grip Chin Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(WideGripChinUps);
           Exercise WideGripHammerPullUps= new Exercise("Wide Grip Hammer Pull Ups","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(WideGripHammerPullUps);

           Exercise LatPulldown= new Exercise("Lat Pulldown","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(LatPulldown);
           Exercise ReverseLatPulldown= new Exercise("Reverse Lat Pulldown","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(ReverseLatPulldown);
           Exercise HammerLatPulldown= new Exercise("Hammer Lat Pulldow","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(HammerLatPulldown);

           Exercise CloseGripLatPulldown= new Exercise("Close Grip Lat Pulldown","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripLatPulldown);
           Exercise CloseGripReverseLatPulldown= new Exercise("Close Grip Reverse Lat Pulldown","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripReverseLatPulldown);
           Exercise CloseGripHammerLatPulldown= new Exercise("Close Grip Hammer Lat Pulldow","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CloseGripHammerLatPulldown);

           Exercise WideGripLatPulldown= new Exercise("Wide Grip Lat Pulldown","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(WideGripLatPulldown);
           Exercise WideGripReverseLatPulldown= new Exercise("Wide Grip Reverse Lat Pulldown","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(WideGripReverseLatPulldown);
           Exercise WideGripHammerLatPulldown= new Exercise("Wide Grip Hammer Lat Pulldow","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(WideGripHammerLatPulldown);

           Exercise TRowMachine= new Exercise("T Row Machine","Big","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(TRowMachine);
           Exercise CabelRow= new Exercise("Cabel Row","Small","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(CabelRow);
           Exercise DummbellRow= new Exercise("Dummbell Row","Small","Back",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DummbellRow);







           ////Biceps////
           Exercise BarbellCurl= new Exercise("Barbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(BarbellCurl);
           Exercise DumbbellCurl= new Exercise("Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellCurl);
           Exercise AlternateDumbbellCurl= new Exercise("Alternate Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateDumbbellCurl);

           Exercise InclineDumbbellCurl= new Exercise("Incline Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(InclineDumbbellCurl);
           Exercise InclineAlternateDumbbellCurl= new Exercise("Incline Alternate Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(InclineAlternateDumbbellCurl);

           Exercise HammerDumbbellCurl= new Exercise("Hammer Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(HammerDumbbellCurl);
           Exercise AlternateHammerDumbbellCurl= new Exercise("Alternate Hammer Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateHammerDumbbellCurl);

           Exercise InclineHammerDumbbellCurl= new Exercise("Incline Hammer Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(InclineHammerDumbbellCurl);
           Exercise InclineAlternateHammerDumbbellCurl= new Exercise("Incline Alternate Hammer Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(InclineAlternateHammerDumbbellCurl);

           Exercise DiagonalHammerDumbbellCurl= new Exercise("Diagonal Hammer Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DiagonalHammerDumbbellCurl);
           Exercise DiagonalAlternateHammerDumbbellCurl= new Exercise("Diagonal Alternate Hammer Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DiagonalAlternateHammerDumbbellCurl);

           Exercise LionDumbbellCurl= new Exercise("Lion Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(LionDumbbellCurl);

           Exercise ConcentrationDumbbellCurl= new Exercise("Concentration Dumbbell Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(ConcentrationDumbbellCurl);

           Exercise BarbellPreacherCurl= new Exercise("Barbell Preacher Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(BarbellPreacherCurl);
           Exercise DumbbellPreacherCurl= new Exercise("Dumbbell Preacher Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellPreacherCurl);
           Exercise AlternateDumbbellPreacherCurl= new Exercise("Alternate Dumbbell Preacher Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateDumbbellPreacherCurl);

           Exercise DumbbellSpiderCurl= new Exercise("Dumbbell Spider Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellSpiderCurl);
           Exercise AlternateDumbbellSpiderCurl= new Exercise("Alternate Dumbbell Spider Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(AlternateDumbbellSpiderCurl);

           Exercise RopeCurl= new Exercise("Rope Curl","Small","Biceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(RopeCurl);







           ////Triceps////
           Exercise BarbellSkullCrushers= new Exercise("Barbell Skull Crushers","Small","Triceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(BarbellSkullCrushers);
           Exercise DumbbellSkullCrushers= new Exercise("Dumbbell Skull Crushers","Small","Triceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellSkullCrushers);

           Exercise TricepsRopePushDowns= new Exercise("Triceps Rope Push Downs","Small","Triceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(TricepsRopePushDowns);

           Exercise DumbbellTricepsExtension= new Exercise("Dumbbell Triceps Extension","Small","Triceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(DumbbellTricepsExtension);

           Exercise  ReverseGripTricepsPushdown= new Exercise("Reverse Grip Triceps Pushdown","Small","Triceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(ReverseGripTricepsPushdown);
           Exercise  TricepsPushdown= new Exercise("Triceps Pushdown","Small","Triceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(TricepsPushdown);

           Exercise  TricepsKickback= new Exercise("Triceps Kickback","Small","Triceps",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(TricepsKickback);






           ////Shoulders////
           Exercise StandingBarbellShoulderPress= new Exercise("Standing Barbell Shoulder Press","Big","Shoulders",0,FunctionsToSaveInMemory.StringToDouble(user.GetLiftRecord()[2]),user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(StandingBarbellShoulderPress);
           Exercise StandingDumbbellShoulderPress= new Exercise("Standing Dumbbell Shoulder Press","Big","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(StandingDumbbellShoulderPress);
           Exercise StandingShoulderPressMachine= new Exercise("Standing Shoulder Press Machine","Big","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(StandingShoulderPressMachine);
           Exercise SeatedBarbellShoulderPress= new Exercise("Seated Barbell Shoulder Press","Big","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(SeatedBarbellShoulderPress);
           Exercise SeatedDumbbellShoulderPress= new Exercise("Seated Dumbbell Shoulder Press","Big","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(SeatedDumbbellShoulderPress);
           Exercise SeatedShoulderPressMachine= new Exercise("Seated Shoulder Press Machine","Big","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(SeatedShoulderPressMachine);


           Exercise SideLateralRaises= new Exercise("Side Lateral Raises","Small","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(SideLateralRaises);
           Exercise FacePulls= new Exercise("Face Pulls","Small","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(FacePulls);
           Exercise SeatedBentOverRearDeltRaise= new Exercise("Seated Bent-Over Rear Delt Raise","Small","Shoulders",0,0.0,user.GetWeight(),user.GetPurpose());
           GeneralExercises.add(SeatedBentOverRearDeltRaise);
           FunctionsToSaveInMemory.Gson_Update_Data_Exercies_Array(GeneralExercises,MainActivity.OWNGENERALEXERCISES,this.context);


       }





    }

private void InitiateUser(){
    if(FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGTIME,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGTIME ,context.getApplicationContext()).equals("") ||
            (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.TRAININGSTYLE,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGSTYLE ,context.getApplicationContext()).equals("")) ||
            (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM ,context.getApplicationContext()).equals("")) ||
            (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.SERIOUSNESS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.SERIOUSNESS ,context.getApplicationContext()).equals("")) ||
            (FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext() ).equals("")) ||
            (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.RANGES,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.RANGES ,context.getApplicationContext()).equals(""))||
            (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.PROGRAMS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PROGRAMS ,context.getApplicationContext()).equals("")) ||
            (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.FOODIDEOLOGY,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FOODIDEOLOGY ,context.getApplicationContext()).equals("")) ||
            (FunctionsToSaveInMemory.get_shared_info_string( MainActivity.LIKEDFOODS,context.getApplicationContext()).equals("0") || FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIKEDFOODS ,context.getApplicationContext()).equals(""))){

        FunctionsToSaveInMemory.updateData("1",MainActivity.HELP,this.context);
        FunctionsToSaveInMemory.updateData("Recomposition",MainActivity.PURPOSE,this.context);
        FunctionsToSaveInMemory.updateData("1",MainActivity.TRAININGTIME,this.context);
        FunctionsToSaveInMemory.updateData("8",MainActivity.RANGES,this.context);
        String Get_Values="1.0"+","+"1.0"+","+"1.0"+","+"1.0"+","+"1.0"+","+"1.0";
        FunctionsToSaveInMemory.updateData(Get_Values,MainActivity.LIFTREC,this.context);
        FunctionsToSaveInMemory.updateData("6",MainActivity.SERIOUSNESS,this.context);
        FunctionsToSaveInMemory.updateData("3",MainActivity.WORKOUTNUM,this.context);
        FunctionsToSaveInMemory.updateData("BroSplit",MainActivity.TRAININGSTYLE,this.context);
        FunctionsToSaveInMemory.updateData("1",MainActivity.PROGRAMS,this.context);
        FunctionsToSaveInMemory.updateData("1",MainActivity.FOODIDEOLOGY,this.context);
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        FunctionsToSaveInMemory.UpdateFoodsArray(ChickenBreast.GetFoodsArray(),this.context);

    }

}

}