package com.example.betterathleat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.List;

public class Program implements Serializable {
    private String ProgramName;
    private int NumberOfWorkOuts;



    private Integer Age;
    private Double Weight;
    private Double Height;
    private String Purpose;
    private Double TrainingTime;
    private String [] LiftRecord= new String[6];
    private Integer MostUsedRepRange;
    private Integer NumberOfAccomplishedPrograms;
    private Integer TrainingSeriousness;
    private ArrayList<Exercise> GeneralExercises= new ArrayList<Exercise>();
    private ArrayList<ArrayList<Integer> > aList = new ArrayList<ArrayList<Integer> >(NumberOfWorkOuts);
    private int ChestSetNum;
    private int BackSetNum;
    private int LegsSetNum;
    private int ShouldersSetNum;
    private int BicepsSetNum;
    private int TricepsSetNum;
    private String [] BigMuscles= {"Chest","Legs","Back"};
    private String [] SmallMuscles= {"Shoulders","Biceps","Triceps"};
    private ArrayList<Exercise> ExercisesInWorkOutA= new ArrayList<Exercise>();
    private ArrayList<Exercise> ExercisesInWorkOutB= new ArrayList<Exercise>();
    private ArrayList<Exercise> ExercisesInWorkOutC= new ArrayList<Exercise>();
    private ArrayList<Exercise> ExercisesInWorkOutD= new ArrayList<Exercise>();
    private Gson ExercisesInWorkOutGson = new Gson();
    Context context;
    private ArrayList<Exercise> BigChestExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> BigLegsExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> BigBackExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> BigShouldersExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> SmallChestExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> SmallLegsExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> SmallBackExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> SmallShouldersExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> SmallBicepsExercises= new ArrayList<Exercise>();
    private ArrayList<Exercise> SmallTricepsExercises= new ArrayList<Exercise>();
    ArrayList<String> WorkOutAComponents=new ArrayList<String>();
    ArrayList<String> WorkOutBComponents=new ArrayList<String>();
    ArrayList<String> WorkOutCComponents=new ArrayList<String>();
    ArrayList<String> WorkOutDComponents=new ArrayList<String>();











    public Program(String ProgramName, Integer Age, Double Weight, Double Height, String Purpose, Double TrainingTime, String [] LiftRecord, Integer MostUsedRepRange, Integer NumberOfAccomplishedPrograms, Integer TrainingSeriousness, int NumberOfWorkOuts, Context context){
        this.context=context;
        SetProgramVariables( ProgramName, Age, Weight, Height, Purpose, TrainingTime, LiftRecord, MostUsedRepRange, NumberOfAccomplishedPrograms, TrainingSeriousness,NumberOfWorkOuts);
        LoadRawExercises();

        if(get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutA).isEmpty() &&get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutB).isEmpty() &&get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutC).isEmpty() &&get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutD).isEmpty()){
            ChooseSetNumberPerWorkOut();
            ChooseExercisesForProgram();
            GetProgram();
        }
        else{
            ExercisesInWorkOutA=get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutA);
            ExercisesInWorkOutB=get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutB);
            ExercisesInWorkOutC=get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutC);
            ExercisesInWorkOutD=get_shared_info_Gson_Exercise_Array( MainActivity.ExercisesForWorkOutD);
            WorkOutAComponents.clear();
            WorkOutBComponents.clear();
            WorkOutCComponents.clear();
            WorkOutDComponents.clear();
            for(Exercise x:ExercisesInWorkOutA){
               if( !WorkOutAComponents.contains(x.GetMuscleName())){
                   WorkOutAComponents.add(x.GetMuscleName());
               }

            }
            for(Exercise x:ExercisesInWorkOutB){
                if( !WorkOutBComponents.contains(x.GetMuscleName())){
                    WorkOutBComponents.add(x.GetMuscleName());
                }

            }
            for(Exercise x:ExercisesInWorkOutC){
                if( !WorkOutCComponents.contains(x.GetMuscleName())){
                    WorkOutCComponents.add(x.GetMuscleName());
                }

            }
            for(Exercise x:ExercisesInWorkOutD){
                if( !WorkOutDComponents.contains(x.GetMuscleName())){
                    WorkOutDComponents.add(x.GetMuscleName());
                }

            }
        }




    }
    private void SetProgramVariables(String ProgramName,Integer Age,Double Weight,Double Height,String Purpose,Double TrainingTime,String [] LiftRecord,Integer MostUsedRepRange,Integer NumberOfAccomplishedPrograms,Integer TrainingSeriousness,int NumberOfWorkOuts){
        SetProgramName(ProgramName);
        SetAge( Age);
        SetWeight( Weight);
        SetHeight( Height);
        SetPurpose(Purpose);
        SetTrainingTime(TrainingTime);
        SetLiftRecord( LiftRecord);
        SetMostUsedRepRange(MostUsedRepRange);
        SetNumberOfAccomplishedPrograms(NumberOfAccomplishedPrograms);
        SetTrainingSeriousness(TrainingSeriousness);
        SetTrainingSeriousness(NumberOfWorkOuts);





    }
    private void SetProgramName(String ProgramName){
        this.ProgramName=ProgramName;
    }


    private void SetAge(Integer Age){
        this.Age=Age;
    }

    private void SetWeight(Double Weight){
        this.Weight=Weight;
    }

    private void SetHeight(Double Height){
        this.Height=Height;
    }

    private void SetPurpose(String Purpose){
        this.Purpose=Purpose;
    }

    private void SetTrainingTime(Double TrainingTime){
        this.TrainingTime=TrainingTime;
    }

    private void SetLiftRecord(String [] LiftRecord){
        this.LiftRecord=LiftRecord;
    }

    private void SetMostUsedRepRange(Integer MostUsedRepRange){
        this.MostUsedRepRange=MostUsedRepRange;
    }

    private void SetNumberOfAccomplishedPrograms(Integer NumberOfAccomplishedPrograms){
        this.NumberOfAccomplishedPrograms=NumberOfAccomplishedPrograms;
    }

    private void SetTrainingSeriousness(Integer TrainingSeriousness){
        this.TrainingSeriousness=TrainingSeriousness;
    }
    private void SetNumberOfWorkOuts(Integer NumberOfWorkOuts){
        this.NumberOfWorkOuts=NumberOfWorkOuts;
    }






    private void LoadRawExercises(){
        if(get_shared_info_Gson_Exercise_Array( MainActivity.GENERALEXERCISES).isEmpty() ){

            ////Chest////
            Exercise Dips= new Exercise("Dips","Big","Chest",0,StringToDouble(this.LiftRecord[5]),this.Weight,this.Purpose);
            GeneralExercises.add(Dips);
            Exercise BarbellBenchPress= new Exercise("Barbell Bench Press","Big","Chest",0,StringToDouble(this.LiftRecord[0]),this.Weight,this.Purpose);
            GeneralExercises.add(BarbellBenchPress);
            Exercise DumbbellBenchPress= new Exercise("Dumbbell Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellBenchPress);
            Exercise AlternateDumbbellBenchPress= new Exercise("Alternate Dumbbell Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateDumbbellBenchPress);

            Exercise BarbellInclineBenchPress= new Exercise("Barbell Incline Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(BarbellInclineBenchPress);
            Exercise DumbbellInclineBenchPress= new Exercise("Dumbbell Incline Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellInclineBenchPress);
            Exercise AlternateDumbbellInclineBenchPress= new Exercise("Alternate Dumbbell Incline Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateDumbbellInclineBenchPress);

            Exercise BarbellDeclineBenchPress= new Exercise("Barbell Decline Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(BarbellDeclineBenchPress);
            Exercise DumbbellDeclineBenchPress= new Exercise("Dumbbell Decline Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellDeclineBenchPress);
            Exercise AlternateDumbbellDeclineBenchPress= new Exercise("Alternate Dumbbell Decline Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateDumbbellDeclineBenchPress);

            Exercise CloseGripBarbellBenchPress= new Exercise("Close Grip Barbell Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripBarbellBenchPress);
            Exercise CloseGripDumbbellBenchPress= new Exercise("Close Grip Dumbbell Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripDumbbellBenchPress);
            Exercise AlternateCloseGripDumbbellBenchPress= new Exercise("Alternate Close Grip Dumbbell Bench Press","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateCloseGripDumbbellBenchPress);

            Exercise DumbbellFly= new Exercise("Dumbbell Fly","Small","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellFly);
            Exercise InclineDumbbellFly= new Exercise("Incline Dumbbell Fly","Small","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(InclineDumbbellFly);
            Exercise FloorDumbbellFly= new Exercise("Floor Dumbbell Fly","Big","Chest",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(FloorDumbbellFly);







            ////Legs////
            Exercise Squat= new Exercise("Squat","Big","Legs",0,StringToDouble(this.LiftRecord[1]),this.Weight,this.Purpose);
            GeneralExercises.add(Squat);

            Exercise LegPress= new Exercise("Leg Press","Big","Legs",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(LegPress);

            Exercise DeadLift= new Exercise("DeadLift","Big","Legs",0,StringToDouble(this.LiftRecord[3]),this.Weight,this.Purpose);
            GeneralExercises.add(DeadLift);
            Exercise SumoDeadLift= new Exercise("Sumo DeadLift","Big","Legs",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(SumoDeadLift);

            Exercise CalfRaisesMachine= new Exercise("Calf Raises Machine","Small","Legs",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CalfRaisesMachine);
            Exercise CalfRaises= new Exercise("Calf Raises","Small","Legs",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CalfRaises);
            Exercise SeatedCalfRaisesMachine= new Exercise("Seated Calf Raises Machine","Small","Legs",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(SeatedCalfRaisesMachine);

            Exercise Lunge= new Exercise("Lunge","Small","Legs",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(Lunge);
            Exercise OneLeggedCableKickback= new Exercise("One-Legged Cable Kickback","Small","Legs",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(OneLeggedCableKickback);






            ////Back////
            Exercise PullUps= new Exercise("Pull Ups","Big","Back",0,StringToDouble(this.LiftRecord[4]),this.Weight,this.Purpose);
            GeneralExercises.add(PullUps);
            Exercise ChinUps= new Exercise("Chin Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(ChinUps);
            Exercise HammerPullUps= new Exercise("Hammer Pull Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(HammerPullUps);

            Exercise CloseGripPullUps= new Exercise("Close Grip Pull Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripPullUps);
            Exercise CloseGripChinUps= new Exercise("Close Grip Chin Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripChinUps);
            Exercise CloseGripHammerPullUps= new Exercise("Close Grip Hammer Pull Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripHammerPullUps);

            Exercise WideGripPullUps= new Exercise("Wide Grip Pull Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(WideGripPullUps);
            Exercise WideGripChinUps= new Exercise("Wide Grip Chin Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(WideGripChinUps);
            Exercise WideGripHammerPullUps= new Exercise("Wide Grip Hammer Pull Ups","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(WideGripHammerPullUps);

            Exercise LatPulldown= new Exercise("Lat Pulldown","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(LatPulldown);
            Exercise ReverseLatPulldown= new Exercise("Reverse Lat Pulldown","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(ReverseLatPulldown);
            Exercise HammerLatPulldown= new Exercise("Hammer Lat Pulldow","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(HammerLatPulldown);

            Exercise CloseGripLatPulldown= new Exercise("Close Grip Lat Pulldown","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripLatPulldown);
            Exercise CloseGripReverseLatPulldown= new Exercise("Close Grip Reverse Lat Pulldown","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripReverseLatPulldown);
            Exercise CloseGripHammerLatPulldown= new Exercise("Close Grip Hammer Lat Pulldow","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CloseGripHammerLatPulldown);

            Exercise WideGripLatPulldown= new Exercise("Wide Grip Lat Pulldown","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(WideGripLatPulldown);
            Exercise WideGripReverseLatPulldown= new Exercise("Wide Grip Reverse Lat Pulldown","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(WideGripReverseLatPulldown);
            Exercise WideGripHammerLatPulldown= new Exercise("Wide Grip Hammer Lat Pulldow","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(WideGripHammerLatPulldown);

            Exercise TRowMachine= new Exercise("T Row Machine","Big","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(TRowMachine);
            Exercise CabelRow= new Exercise("Cabel Row","Small","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(CabelRow);
            Exercise DummbellRow= new Exercise("Dummbell Row","Small","Back",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DummbellRow);







            ////Biceps////
            Exercise BarbellCurl= new Exercise("Barbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(BarbellCurl);
            Exercise DumbbellCurl= new Exercise("Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellCurl);
            Exercise AlternateDumbbellCurl= new Exercise("Alternate Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateDumbbellCurl);

            Exercise InclineDumbbellCurl= new Exercise("Incline Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(InclineDumbbellCurl);
            Exercise InclineAlternateDumbbellCurl= new Exercise("Incline Alternate Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(InclineAlternateDumbbellCurl);

            Exercise HammerDumbbellCurl= new Exercise("Hammer Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(HammerDumbbellCurl);
            Exercise AlternateHammerDumbbellCurl= new Exercise("Alternate Hammer Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateHammerDumbbellCurl);

            Exercise InclineHammerDumbbellCurl= new Exercise("Incline Hammer Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(InclineHammerDumbbellCurl);
            Exercise InclineAlternateHammerDumbbellCurl= new Exercise("Incline Alternate Hammer Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(InclineAlternateHammerDumbbellCurl);

            Exercise DiagonalHammerDumbbellCurl= new Exercise("Diagonal Hammer Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DiagonalHammerDumbbellCurl);
            Exercise DiagonalAlternateHammerDumbbellCurl= new Exercise("Diagonal Alternate Hammer Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DiagonalAlternateHammerDumbbellCurl);

            Exercise LionDumbbellCurl= new Exercise("Lion Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(LionDumbbellCurl);

            Exercise ConcentrationDumbbellCurl= new Exercise("Concentration Dumbbell Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(ConcentrationDumbbellCurl);

            Exercise BarbellPreacherCurl= new Exercise("Barbell Preacher Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(BarbellPreacherCurl);
            Exercise DumbbellPreacherCurl= new Exercise("Dumbbell Preacher Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellPreacherCurl);
            Exercise AlternateDumbbellPreacherCurl= new Exercise("Alternate Dumbbell Preacher Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateDumbbellPreacherCurl);

            Exercise DumbbellSpiderCurl= new Exercise("Dumbbell Spider Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellSpiderCurl);
            Exercise AlternateDumbbellSpiderCurl= new Exercise("Alternate Dumbbell Spider Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(AlternateDumbbellSpiderCurl);

            Exercise RopeCurl= new Exercise("Rope Curl","Small","Biceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(RopeCurl);







            ////Triceps////
            Exercise BarbellSkullCrushers= new Exercise("Barbell Skull Crushers","Small","Triceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(BarbellSkullCrushers);
            Exercise DumbbellSkullCrushers= new Exercise("Dumbbell Skull Crushers","Small","Triceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellSkullCrushers);

            Exercise TricepsRopePushDowns= new Exercise("Triceps Rope Push Downs","Small","Triceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(TricepsRopePushDowns);

            Exercise DumbbellTricepsExtension= new Exercise("Dumbbell Triceps Extension","Small","Triceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(DumbbellTricepsExtension);

            Exercise  ReverseGripTricepsPushdown= new Exercise("Reverse Grip Triceps Pushdown","Small","Triceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(ReverseGripTricepsPushdown);
            Exercise  TricepsPushdown= new Exercise("Triceps Pushdown","Small","Triceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(TricepsPushdown);

            Exercise  TricepsKickback= new Exercise("Triceps Kickback","Small","Triceps",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(TricepsKickback);






            ////Shoulders////
            Exercise StandingBarbellShoulderPress= new Exercise("Standing Barbell Shoulder Press","Big","Shoulders",0,StringToDouble(this.LiftRecord[2]),this.Weight,this.Purpose);
            GeneralExercises.add(StandingBarbellShoulderPress);
            Exercise StandingDumbbellShoulderPress= new Exercise("Standing Dumbbell Shoulder Press","Big","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(StandingDumbbellShoulderPress);
            Exercise StandingShoulderPressMachine= new Exercise("Standing Shoulder Press Machine","Big","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(StandingShoulderPressMachine);
            Exercise SeatedBarbellShoulderPress= new Exercise("Seated Barbell Shoulder Press","Big","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(SeatedBarbellShoulderPress);
            Exercise SeatedDumbbellShoulderPress= new Exercise("Seated Dumbbell Shoulder Press","Big","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(SeatedDumbbellShoulderPress);
            Exercise SeatedShoulderPressMachine= new Exercise("Seated Shoulder Press Machine","Big","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(SeatedShoulderPressMachine);


            Exercise SideLateralRaises= new Exercise("Side Lateral Raises","Small","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(SideLateralRaises);
            Exercise FacePulls= new Exercise("Face Pulls","Small","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(FacePulls);
            Exercise SeatedBentOverRearDeltRaise= new Exercise("Seated Bent-Over Rear Delt Raise","Small","Shoulders",0,0.0,this.Weight,this.Purpose);
            GeneralExercises.add(SeatedBentOverRearDeltRaise);
            Gson_Update_Data_Exercies_Array(GeneralExercises,MainActivity.GENERALEXERCISES);

        }
        else {
            GeneralExercises = get_shared_info_Gson_Exercise_Array(MainActivity.GENERALEXERCISES);
        }



        for(Exercise X:GeneralExercises){
            switch(X.GetMuscleName()){
                case "Chest":
                    switch(X.GetExerciseType()){
                        case "Big":
                            BigChestExercises.add(X);
                            break;
                        case "Small":
                            SmallChestExercises.add(X);
                            break;

                    }
                    break;

                case "Legs":
                    switch(X.GetExerciseType()){
                        case "Big":
                            BigLegsExercises.add(X);
                            break;
                        case "Small":
                            SmallLegsExercises.add(X);
                            break;

                    }
                    break;
                case "Back":
                    switch(X.GetExerciseType()){
                        case "Big":
                            BigBackExercises.add(X);
                            break;
                        case "Small":
                            SmallBackExercises.add(X);
                            break;

                    }
                    break;
                case "Shoulders":
                    switch(X.GetExerciseType()){
                        case "Big":
                            BigShouldersExercises.add(X);
                            break;
                        case "Small":
                            SmallShouldersExercises.add(X);
                            break;

                    }
                    break;
                case "Triceps":
                    SmallTricepsExercises.add(X);
                    break;
                case "Biceps":
                    SmallBicepsExercises.add(X);
                    break;


            }

        }



    }

    private void ChooseExercisesForProgram(){
        BuildStringForProgram();
        switch(this.ProgramName){
            case "FullBody1":
            case "BroSplit1":
            case "PushPull1ToBroSplit1":
            case "UpperLower1ToBroSplit1":
                BuildWorkOut("A",WorkOutAComponents);
                break;

            case "FullBody2":
            case "FullBody3":
            case "FullBody4":
            case "BroSplit2":
            case "BroSplit3":
            case "BroSplit4":
            case "PushPull2ToBroSplit2":
            case "UpperLower2":
            case "UpperLower3":
            case "UpperLower4":
            case "UpperLower5":
                BuildWorkOut("A",WorkOutAComponents);
                BuildWorkOut("B",WorkOutBComponents);
                break;

            case "BroSplit5":
            case "BroSplit6":
            case "FullBody5ToBroSplit5":
            case "FullBody6ToBroSplit6":
            case "UpperLower6ToBroSplit6":
            case "PushPull3":
            case "PushPull4":
            case "PushPull5":
            case "PushPull6":
                BuildWorkOut("A",WorkOutAComponents);
                BuildWorkOut("B",WorkOutBComponents);
                BuildWorkOut("C",WorkOutCComponents);


                break;
            case "BroSplit7":
            case "FullBody7ToBroSplit7":
            case "PushPull7ToBroSplit7":
            case "UpperLower7ToBroSplit7":
                BuildWorkOut("A",WorkOutAComponents);
                BuildWorkOut("B",WorkOutBComponents);
                BuildWorkOut("C",WorkOutCComponents);
                BuildWorkOut("D",WorkOutDComponents);

                break;
        }


    }





    private void  ChooseSetNumberPerWorkOut(){
        switch(this.ProgramName){
            case "FullBody1":
            case "FullBody2":
            case "FullBody3":
            case "FullBody4":
            case "BroSplit1":
            case "PushPull1ToBroSplit1":
            case "UpperLower1ToBroSplit1":
                 ChestSetNum=3;
                 BackSetNum=3;
                 LegsSetNum=3;
                 ShouldersSetNum=3;
                 BicepsSetNum=3;
                 TricepsSetNum=3;

                break;
            case "BroSplit2":
            case "BroSplit3":
            case "BroSplit4":
            case "PushPull2ToBroSplit2":
                ChestSetNum=6;
                BackSetNum=6;
                LegsSetNum=6;
                ShouldersSetNum=6;
                BicepsSetNum=6;
                TricepsSetNum=6;
                break;
            case "BroSplit5":
            case "BroSplit6":
            case "FullBody5ToBroSplit5":
            case "FullBody6ToBroSplit6":
            case "UpperLower6ToBroSplit6":
                ChestSetNum=9;
                BackSetNum=9;
                LegsSetNum=9;
                ShouldersSetNum=9;
                BicepsSetNum=9;
                TricepsSetNum=9;

                break;
            case "BroSplit7":
            case "FullBody7ToBroSplit7":
            case "PushPull7ToBroSplit7":
            case "UpperLower7ToBroSplit7":
                ChestSetNum=16;
                BackSetNum=16;
                LegsSetNum=16;
                ShouldersSetNum=6;
                BicepsSetNum=6;
                TricepsSetNum=6;

                break;
            case "UpperLower2":
            case "UpperLower3":
            case "UpperLower4":
            case "UpperLower5":
                ChestSetNum=3;
                BackSetNum=3;
                LegsSetNum=16;
                ShouldersSetNum=3;
                BicepsSetNum=3;
                TricepsSetNum=3;

                break;
            case "PushPull3":
            case "PushPull4":
            case "PushPull5":
            case "PushPull6":
                ChestSetNum=6;
                BackSetNum=9;
                LegsSetNum=16;
                ShouldersSetNum=6;
                BicepsSetNum=9;
                TricepsSetNum=6;

                break;



        }


    }

    private void BuildWorkOut(String WorkoutName, ArrayList<String> MusclesToUse){
        ArrayList<Exercise> NewWorkOut=new ArrayList<Exercise>();
        int RandomExercise;
        int NumberOfChestExercises;
        int NumberOfChestExercisesleft;
        int NumberOfLegsExercises;
        int NumberOfLegsExercisesleft;
        int NumberOfBackExercises;
        int NumberOfBackExercisesleft;
        int NumberOfShouldersExercises;
        int NumberOfShouldersExercisesleft;
        int NumberOfTricepsExercises;
        int NumberOfTricepsExercisesleft;
        int NumberOfBicepsExercises;
        int NumberOfBicepsExercisesleft;




        if(ChestSetNum%4==0){
            NumberOfChestExercises=ChestSetNum/4;
        }
        else if(ChestSetNum%3==0){
            NumberOfChestExercises=ChestSetNum/3;
        }
        else{
            NumberOfChestExercises=0;
        }
        NumberOfChestExercisesleft=NumberOfChestExercises;

        if(LegsSetNum%4==0){
            NumberOfLegsExercises=LegsSetNum/4;
        }
        else if(LegsSetNum%3==0){
            NumberOfLegsExercises=LegsSetNum/3;
        }
        else{
            NumberOfLegsExercises=0;
        }
        NumberOfLegsExercisesleft=NumberOfLegsExercises;

        if(BackSetNum%4==0){
            NumberOfBackExercises=BackSetNum/4;
        }
        else if(BackSetNum%3==0){
            NumberOfBackExercises=BackSetNum/3;
        }
        else{
            NumberOfBackExercises=0;
        }
        NumberOfBackExercisesleft=NumberOfBackExercises;

        if(ShouldersSetNum%4==0){
            NumberOfShouldersExercises=ShouldersSetNum/4;
        }
        else if(ShouldersSetNum%3==0){
            NumberOfShouldersExercises=ShouldersSetNum/3;
        }
        else{
            NumberOfShouldersExercises=0;
        }
        NumberOfShouldersExercisesleft=NumberOfShouldersExercises;

        if(TricepsSetNum%4==0){
            NumberOfTricepsExercises=TricepsSetNum/4;
        }
        else if(TricepsSetNum%3==0){
            NumberOfTricepsExercises=TricepsSetNum/3;
        }
        else{
            NumberOfTricepsExercises=0;
        }
        NumberOfTricepsExercisesleft=NumberOfTricepsExercises;

        if(BicepsSetNum%4==0){
            NumberOfBicepsExercises=BicepsSetNum/4;
        }
        else if(BicepsSetNum%3==0){
            NumberOfBicepsExercises=BicepsSetNum/3;
        }
        else{
            NumberOfBicepsExercises=0;
        }
        NumberOfBicepsExercisesleft=NumberOfBicepsExercises;


        for(String x:MusclesToUse){
            switch(x){
                case "Chest":
                    while(NumberOfChestExercisesleft>0){
                        if(NumberOfChestExercises-NumberOfChestExercisesleft<2 && NumberOfChestExercisesleft>0){
                            do{
                                RandomExercise = ((int) (Math.random() * BigChestExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,BigChestExercises.get(RandomExercise)));
                            NewWorkOut.add(BigChestExercises.get(RandomExercise));
                            NumberOfChestExercisesleft-=1;
                        }
                        else if( NumberOfChestExercises-NumberOfChestExercisesleft>=2 && NumberOfChestExercisesleft>0){

                            do{
                                RandomExercise = ((int) (Math.random() * SmallChestExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,SmallChestExercises.get(RandomExercise)));
                            NewWorkOut.add(SmallChestExercises.get(RandomExercise));
                            NumberOfChestExercisesleft-=1;
                        }
                    }


                    break;
                case "Legs":
                    while(NumberOfLegsExercisesleft>0){
                        if(NumberOfLegsExercises-NumberOfLegsExercisesleft<2 && NumberOfLegsExercisesleft>0){
                            do{
                                RandomExercise = ((int) (Math.random() * BigLegsExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,BigLegsExercises.get(RandomExercise)));
                            NewWorkOut.add(BigLegsExercises.get(RandomExercise));
                            NumberOfLegsExercisesleft-=1;
                        }
                        else if( NumberOfLegsExercises-NumberOfLegsExercisesleft>=2 && NumberOfLegsExercisesleft>0){

                            do{
                                RandomExercise = ((int) (Math.random() * SmallLegsExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,SmallLegsExercises.get(RandomExercise)));
                            NewWorkOut.add(SmallLegsExercises.get(RandomExercise));
                            NumberOfLegsExercisesleft-=1;
                        }
                    }

                    break;

                case "Back":
                    while(NumberOfBackExercisesleft>0){
                        if(NumberOfBackExercises-NumberOfBackExercisesleft<2 && NumberOfBackExercisesleft>0){
                            do{
                                RandomExercise = ((int) (Math.random() * BigBackExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,BigBackExercises.get(RandomExercise)));
                            NewWorkOut.add(BigBackExercises.get(RandomExercise));
                            NumberOfBackExercisesleft-=1;
                        }
                        else if( NumberOfBackExercises-NumberOfBackExercisesleft>=2 && NumberOfBackExercisesleft>0){

                            do{
                                RandomExercise = ((int) (Math.random() * SmallBackExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,SmallBackExercises.get(RandomExercise)));
                            NewWorkOut.add(SmallBackExercises.get(RandomExercise));
                            NumberOfBackExercisesleft-=1;
                        }
                    }

                    break;

                case "Shoulders":
                    while(NumberOfShouldersExercisesleft>0){
                        if(NumberOfShouldersExercises-NumberOfShouldersExercisesleft<2 && NumberOfShouldersExercisesleft>0){
                            do{
                                RandomExercise = ((int) (Math.random() * BigShouldersExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,BigShouldersExercises.get(RandomExercise)));
                            NewWorkOut.add(BigShouldersExercises.get(RandomExercise));
                            NumberOfShouldersExercisesleft-=1;
                        }
                        else if( NumberOfShouldersExercises-NumberOfShouldersExercisesleft>=2 && NumberOfShouldersExercisesleft>0){

                            do{
                                RandomExercise = ((int) (Math.random() * SmallShouldersExercises.size()));
                            }while(ExreciseInArray(NewWorkOut ,SmallShouldersExercises.get(RandomExercise)));
                            NewWorkOut.add(SmallShouldersExercises.get(RandomExercise));
                            NumberOfShouldersExercisesleft-=1;
                        }
                    }

                    break;
                case "Triceps":
                    while(NumberOfTricepsExercisesleft>0){
                        do{
                            RandomExercise = ((int) (Math.random() * SmallTricepsExercises.size()));
                        }while(ExreciseInArray(NewWorkOut ,SmallTricepsExercises.get(RandomExercise)));
                        NewWorkOut.add(SmallTricepsExercises.get(RandomExercise));
                        NumberOfTricepsExercisesleft-=1;
                    }
                    break;
                case "Biceps":
                    while(NumberOfBicepsExercisesleft>0){
                        do{
                            RandomExercise = ((int) (Math.random() * SmallBicepsExercises.size()));
                        }while(ExreciseInArray(NewWorkOut ,SmallBicepsExercises.get(RandomExercise)));
                        NewWorkOut.add(SmallBicepsExercises.get(RandomExercise));
                        NumberOfBicepsExercisesleft-=1;
                    }

                    break;
            }

        }
        switch(WorkoutName){
            case "A":
                ExercisesInWorkOutA.clear();
                for(Exercise T:NewWorkOut){
                    ExercisesInWorkOutA.add(T);
                }
                break;
            case "B":
                ExercisesInWorkOutB.clear();
                for(Exercise T:NewWorkOut){
                    ExercisesInWorkOutB.add(T);
                }
                break;
            case "C":
                ExercisesInWorkOutC.clear();
                for(Exercise T:NewWorkOut){
                    ExercisesInWorkOutC.add(T);
                }
                break;
            case "D":
                ExercisesInWorkOutD.clear();
                for(Exercise T:NewWorkOut){
                    ExercisesInWorkOutD.add(T);
                }
                break;
        }



    }
    public void GetProgram(){
        Gson_Update_Data_Exercies_Array(ExercisesInWorkOutA,MainActivity.ExercisesForWorkOutA);
        Gson_Update_Data_Exercies_Array(ExercisesInWorkOutB,MainActivity.ExercisesForWorkOutB);
        Gson_Update_Data_Exercies_Array(ExercisesInWorkOutC,MainActivity.ExercisesForWorkOutC);
        Gson_Update_Data_Exercies_Array(ExercisesInWorkOutD,MainActivity.ExercisesForWorkOutD);
    }
    public void MakeNewProgram(){
        ChooseSetNumberPerWorkOut();
        ChooseExercisesForProgram();
        GetProgram();
    }
    public ArrayList<String> GetExercisesStringForWorkout(String WorkOut){
        switch(WorkOut){
            case "A":
                return(WorkOutAComponents);
            case "B":
                return(WorkOutBComponents);
            case "C":
                return(WorkOutCComponents);
            case "D":
                return(WorkOutDComponents);
        }
        return(WorkOutAComponents);
    }


///////////////////////make life easy/////////////
    public Double StringToDouble(String Key){

        return(Double.parseDouble(Key));

    }
    public void Gson_Update_Data_Exercies_Array(ArrayList<Exercise> Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = ExercisesInWorkOutGson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }



    public Boolean ExreciseInArray(ArrayList<Exercise> Array,Exercise T ){
        for(Exercise x : Array){
            if(x==T){
                return  (true);
            }
        }
        return  (false);
    }


    public ArrayList<Exercise> get_shared_info_Gson_Exercise_Array( final String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        ArrayList<Exercise> ExercisesForWorkOut= new ArrayList<Exercise>();
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

            return(ExercisesForWorkOut);
        }
        Type type = new TypeToken<List<Exercise>>(){}.getType();
        Gson gson=new Gson();
        ExercisesForWorkOut = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(ExercisesForWorkOut);
    }



    private void BuildStringForProgram(){
        int RandomExercise;
        switch(this.ProgramName){
            case "FullBody1":
            case "BroSplit1":
            case "PushPull1ToBroSplit1":
            case "UpperLower1ToBroSplit1":
                WorkOutAComponents.clear();
                RandomExercise = ((int) (Math.random() * 4));
                switch(RandomExercise){
                    case 0:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        break;
                    case 1:
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        break;
                    case 2:
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        break;
                    case 3:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        break;

                }
                break;

            case "FullBody2":
            case "FullBody3":
            case "FullBody4":

                WorkOutAComponents.clear();
                WorkOutBComponents.clear();
                RandomExercise = ((int) (Math.random() * 4));
                switch(RandomExercise){
                    case 0:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Chest");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Biceps");
                        WorkOutBComponents.add("Triceps");

                        break;
                    case 1:
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Chest");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Biceps");
                        WorkOutBComponents.add("Triceps");
                        break;
                    case 2:
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Chest");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Biceps");
                        WorkOutBComponents.add("Triceps");
                        break;
                    case 3:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Chest");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Biceps");
                        WorkOutBComponents.add("Triceps");
                        break;

                }
                break;

            case "BroSplit2":
            case "BroSplit3":
            case "BroSplit4":
            case "PushPull2ToBroSplit2":
                WorkOutAComponents.clear();
                WorkOutBComponents.clear();
                RandomExercise = ((int) (Math.random() * 7));
                switch(RandomExercise){
                    case 0:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Biceps");

                        break;
                    case 1:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Legs");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Triceps");

                        break;
                    case 2:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Biceps");

                        break;
                    case 3:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Triceps");

                        break;
                    case 4:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Triceps");

                        break;
                    case 5:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Legs");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Biceps");

                        break;
                    case 6:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Legs");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Biceps");
                        WorkOutBComponents.add("Triceps");

                        break;

                }
                break;
            case "BroSplit5":
            case "BroSplit6":
            case "FullBody5ToBroSplit5":
            case "FullBody6ToBroSplit6":
            case "UpperLower6ToBroSplit6":
                WorkOutAComponents.clear();
                WorkOutBComponents.clear();
                WorkOutCComponents.clear();
                RandomExercise = ((int) (Math.random() * 6));
                switch(RandomExercise){
                    case 0:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Legs");
                        WorkOutCComponents.add("Back");
                        WorkOutCComponents.add("Triceps");




                        break;
                    case 1:
                        WorkOutBComponents.add("Chest");
                        WorkOutBComponents.add("Biceps");
                        WorkOutCComponents.add("Shoulders");
                        WorkOutCComponents.add("Legs");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Triceps");

                        break;
                    case 2:
                        WorkOutCComponents.add("Chest");
                        WorkOutCComponents.add("Biceps");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Legs");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Triceps");

                        break;
                    case 3:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Shoulders");
                        WorkOutBComponents.add("Legs");
                        WorkOutCComponents.add("Back");
                        WorkOutCComponents.add("Biceps");




                        break;
                    case 4:
                        WorkOutBComponents.add("Chest");
                        WorkOutBComponents.add("Triceps");
                        WorkOutCComponents.add("Shoulders");
                        WorkOutCComponents.add("Legs");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Biceps");


                        break;
                    case 5:
                        WorkOutCComponents.add("Chest");
                        WorkOutCComponents.add("Triceps");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Legs");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Biceps");

                        break;


                }
                break;
            case "BroSplit7":
            case "FullBody7ToBroSplit7":
            case "PushPull7ToBroSplit7":
            case "UpperLower7ToBroSplit7":
                WorkOutAComponents.clear();
                WorkOutBComponents.clear();
                WorkOutCComponents.clear();
                WorkOutDComponents.clear();
                RandomExercise = ((int) (Math.random() * 5));
                switch(RandomExercise){
                    case 0:
                        WorkOutAComponents.add("Chest");
                        WorkOutBComponents.add("Legs");
                        WorkOutCComponents.add("Back");
                        WorkOutDComponents.add("Shoulders");
                        WorkOutDComponents.add("Triceps");
                        WorkOutDComponents.add("Biceps");




                        break;
                    case 1:
                        WorkOutAComponents.add("Chest");
                        WorkOutBComponents.add("Legs");
                        WorkOutCComponents.add("Back");
                        WorkOutDComponents.add("Shoulders");
                        WorkOutDComponents.add("Biceps");
                        WorkOutDComponents.add("Triceps");

                        break;
                    case 2:
                        WorkOutAComponents.add("Chest");
                        WorkOutBComponents.add("Legs");
                        WorkOutCComponents.add("Back");
                        WorkOutDComponents.add("Biceps");
                        WorkOutDComponents.add("Triceps");
                        WorkOutDComponents.add("Shoulders");

                        break;
                    case 3:
                        WorkOutAComponents.add("Chest");
                        WorkOutBComponents.add("Legs");
                        WorkOutCComponents.add("Back");
                        WorkOutDComponents.add("Biceps");
                        WorkOutDComponents.add("Shoulders");
                        WorkOutDComponents.add("Triceps");




                        break;
                    case 4:
                        WorkOutAComponents.add("Chest");
                        WorkOutBComponents.add("Legs");
                        WorkOutCComponents.add("Back");
                        WorkOutDComponents.add("Triceps");
                        WorkOutDComponents.add("Shoulders");
                        WorkOutDComponents.add("Biceps");


                        break;



                }
                break;
            case "UpperLower2":
            case "UpperLower3":
            case "UpperLower4":
            case "UpperLower5":
                WorkOutAComponents.clear();
                WorkOutBComponents.clear();
                RandomExercise = ((int) (Math.random() * 6));
                switch(RandomExercise){
                    case 0:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Triceps");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Legs");

                        break;
                    case 1:
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Triceps");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Legs");

                        break;
                    case 2:
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Triceps");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Legs");

                        break;
                    case 3:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Triceps");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Biceps");
                        WorkOutBComponents.add("Legs");
                        break;
                    case 4:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutBComponents.add("Legs");

                        break;
                    case 5:
                        WorkOutAComponents.add("Back");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Biceps");
                        WorkOutAComponents.add("Triceps");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutBComponents.add("Legs");

                        break;

                }
                break;
            case "PushPull3":
            case "PushPull4":
            case "PushPull5":
            case "PushPull6":
                WorkOutAComponents.clear();
                WorkOutBComponents.clear();
                WorkOutCComponents.clear();
                RandomExercise = ((int) (Math.random() * 2));
                switch(RandomExercise){
                    case 0:
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Biceps");
                        WorkOutCComponents.add("Legs");




                        break;
                    case 1:
                        WorkOutAComponents.add("Shoulders");
                        WorkOutAComponents.add("Chest");
                        WorkOutAComponents.add("Triceps");
                        WorkOutBComponents.add("Back");
                        WorkOutBComponents.add("Biceps");
                        WorkOutCComponents.add("Legs");

                }
                break;



        }


    }


private Boolean CheckIFStringInArrayString(String x,String[] y){
        for(String T:y){
            if(T.equals(x)){
                return(true);
            }
        }
    return (false);
}



public ArrayList<Exercise> GetGeneralExercises(){
        return(this.GeneralExercises);
}



}


