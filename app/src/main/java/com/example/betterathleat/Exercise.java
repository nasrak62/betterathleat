package com.example.betterathleat;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.logging.Level;

import static androidx.core.content.ContextCompat.getSystemService;

public class Exercise {
    private String ExerciseName;
    private String ExerciseType;
    private String MuscleName;
    private int SetNum;
    private ArrayList<Integer> CurrentRepsNumberPerSet = new ArrayList<Integer>();
    private ArrayList<Integer> PrevRepsNumberPerSet = new ArrayList<Integer>();
    private ArrayList<Double> CurrentWeightPerSet = new ArrayList<Double>();
    private ArrayList<Double> PrevWeightPerSet = new ArrayList<Double>();
    private int MaxRepsNumberForProgram;
    private int MinRepsNumberForProgram;
    private Double CurrentExerciseWeight;
    private Double CurrentExerciseGoalWeight;
    private Double FinalExerciseGoalWeight;
    private Double HighestRecord;
    private ArrayList<Double> HighestRecordArray = new ArrayList<Double>();
    private Double PersonWeight;
    private String ExerciseLvl;
    private String Purpose;
    private static ArrayList<Exercise> GeneralExercises = new ArrayList<Exercise>();







    public Exercise(String ExerciseName,String ExerciseType,String MuscleName,int SetNum,Double CurrentExerciseWeight,Double PersonWeight,String Purpose){
        SetExercise( ExerciseName,ExerciseType,MuscleName,SetNum,  CurrentExerciseWeight, PersonWeight, Purpose);
        if(!ExerciseInGeneralExercises()){
            GeneralExercises.add(this);
        }

    }

    private void SetExercise(String ExerciseName,String ExerciseType,String MuscleName,int SetNum,Double CurrentExerciseWeight,Double PersonWeight,String Purpose){
        SetHighestRecord();
        SetExerciseName(ExerciseName);
        SetExerciseType(ExerciseType);
        SetMuscleName(MuscleName);
        SetPurpose(Purpose);
        SetPersonWeight(PersonWeight);
        SetSetNum(SetNum);
        SetMaxRepsNumberForProgram();
        SetMinRepsNumberForProgram();
        SetCurrentExerciseWeight(CurrentExerciseWeight);
        SetStartingRepsNumberPerSet();
        SetCurrentExerciseGoalWeight();
        SetFinalExerciseGoalWeight();
        SetStringExerciseLvl();
        SetStartingCurrentWeightPerSet();






    }
    public void SetExerciseName(String ExerciseName){
        this.ExerciseName=ExerciseName;

    }

    public void SetExerciseType(String ExerciseType){
        this.ExerciseType=ExerciseType;

    }
    public void SetMuscleName(String MuscleName){
        this.MuscleName=MuscleName;

    }
    public void SetPurpose(String Purpose){
        this.Purpose=Purpose;

    }

    public void SetPersonWeight(Double PersonWeight){
        this.PersonWeight=PersonWeight;

    }
    public void SetSetNum(int SetNum){
        this.SetNum=SetNum;

    }

    public void SetMaxRepsNumberForProgram() {

        if (this.ExerciseType.equals("Big")) {
            switch ( this.Purpose) {
                case "Shreded":
                case "Lean":
                case "Recomposition":
                    this.MaxRepsNumberForProgram = 12;
                    break;


                case "Lean Mass":
                case "Dirty Mass":
                    this.MaxRepsNumberForProgram = 12;
                    break;

                case "Power":
                    this.MaxRepsNumberForProgram = 8;
                    break;
            }

        } else if (this.ExerciseType.equals("Small")) {
            switch ( this.Purpose) {
                case "Shreded":
                case "Lean":
                case "Recomposition":
                    this.MaxRepsNumberForProgram = 15;
                    break;


                case "Lean Mass":
                case "Dirty Mass":
                    this.MaxRepsNumberForProgram = 12;
                    break;

                case "Power":
                    this.MaxRepsNumberForProgram = 8;
                    break;
            }


        }
    }
    public void SetMinRepsNumberForProgram() {

            if (this.ExerciseType.equals("Big")) {
                switch ( this.Purpose) {
                    case "Shreded":
                    case "Lean":
                    case "Recomposition":
                        this.MinRepsNumberForProgram = 3;
                        break;


                    case "Lean Mass":
                    case "Dirty Mass":
                        this.MinRepsNumberForProgram = 6;
                        break;

                    case "Power":
                        this.MinRepsNumberForProgram = 3;
                        break;
                }

            } else if (this.ExerciseType.equals("Small")) {
                switch ( this.Purpose) {
                    case "Shreded":
                    case "Lean":
                    case "Recomposition":
                        this.MinRepsNumberForProgram = 8;
                        break;


                    case "Lean Mass":
                    case "Dirty Mass":
                        this.MinRepsNumberForProgram = 6;
                        break;

                    case "Power":
                        this.MinRepsNumberForProgram = 1;
                        break;
                }


            }
        }
    public void SetCurrentExerciseWeight(Double CurrentExerciseWeight){
        if(CurrentExerciseWeight== null){
            this.CurrentExerciseWeight=0.0;
        }
        this.CurrentExerciseWeight=CurrentExerciseWeight;

    }
    public void SetStartingRepsNumberPerSet(){
        int AddReps=this.MinRepsNumberForProgram;
        if(this.SetNum>0){
            if(this.ExerciseType.equals("Big")){
                for( int i=0;i<this.SetNum;i++){
                    CurrentRepsNumberPerSet.add(AddReps);
                    AddReps= (int) (AddReps+Math.round(AddReps*0.2));
                    if (AddReps>=this.MaxRepsNumberForProgram){
                        AddReps=this.MaxRepsNumberForProgram;
                    }
                }
            }
            else if(this.ExerciseType.equals("Small")){
                for( int i=0;i<this.SetNum;i++){
                    CurrentRepsNumberPerSet.add(AddReps);

                }

            }
        }


    }
    public void SetCurrentExerciseGoalWeight(){
        this.CurrentExerciseGoalWeight=CurrentExerciseWeight+1;

    }
    public void SetStartingCurrentWeightPerSet(){
        for(int x=0;x<CurrentRepsNumberPerSet.size();x++)
        this.CurrentWeightPerSet.add(this.CurrentExerciseWeight);

    }

    public void SetPrevCurrentWeightPerSet(){
        if( !this.CurrentWeightPerSet.isEmpty()){
            this.PrevWeightPerSet.clear();
            for(Double X: this.CurrentWeightPerSet){
                this.PrevWeightPerSet.add(X);
            }
            this.CurrentWeightPerSet.clear();
        }



    }
    public void SetPrevCurrentRepsPerSet(){
        if( !this.CurrentRepsNumberPerSet.isEmpty()){
            this.PrevRepsNumberPerSet.clear();
            for(int X: this.CurrentRepsNumberPerSet){
                this.PrevRepsNumberPerSet.add(X);
            }

            this.CurrentRepsNumberPerSet.clear();
        }



    }
    public void SetFinalExerciseGoalWeight(){
        if((this.CurrentExerciseWeight/this.PersonWeight)<=0.7){
            this.FinalExerciseGoalWeight=this.CurrentExerciseWeight*1.4;
        }
        else if((this.CurrentExerciseWeight/this.PersonWeight)>0.7){
            this.FinalExerciseGoalWeight=this.CurrentExerciseWeight*1.4;
        }
        else{
            this.FinalExerciseGoalWeight=this.CurrentExerciseWeight*1.4;
        }

    }
    public void SetStringExerciseLvl(){
        Double WhereAbout=this.CurrentExerciseWeight/this.PersonWeight;
        if(this.ExerciseType.equals("Big")&& WhereAbout<2.1){
            if(WhereAbout<0.5){

                this.ExerciseLvl="Level 1";
            }
            else if(WhereAbout>=0.5 && WhereAbout<1){
                this.ExerciseLvl="Level 2";
            }
            else if(WhereAbout>=1 && WhereAbout<1.4){
                this.ExerciseLvl="Level 3";
            }
            else if(WhereAbout>=1.4 && WhereAbout<1.6){
                this.ExerciseLvl="Level 4";
            }
            else if(WhereAbout>=1.6 && WhereAbout<1.8){
                this.ExerciseLvl="Level 5";
            }
            else if(WhereAbout>=1.8 && WhereAbout<2){
                this.ExerciseLvl="Level 6";
            }

        }
        else if(this.ExerciseType.equals("Big")&& WhereAbout>=2.1){
            if(WhereAbout<1){

                this.ExerciseLvl="Level 1";
            }
            else if(WhereAbout>=1 && WhereAbout<1.5){
                this.ExerciseLvl="Level 2";
            }
            else if(WhereAbout>=1.5 && WhereAbout<2){
                this.ExerciseLvl="Level 3";
            }
            else if(WhereAbout>=2 && WhereAbout<2.5){
                this.ExerciseLvl="Level 4";
            }
            else if(WhereAbout>=2.5 && WhereAbout<3){
                this.ExerciseLvl="Level 5";
            }
            else if(WhereAbout>=3){
                this.ExerciseLvl="Level 6";
            }

        }
        else if(this.ExerciseType.equals("Small")){
            if(WhereAbout<0.1){

                this.ExerciseLvl="Level 1";
            }
            else if(WhereAbout>=0.25 && WhereAbout<0.4){
                this.ExerciseLvl="Level 2";
            }
            else if(WhereAbout>=0.4 && WhereAbout<0.5){
                this.ExerciseLvl="Level 3";
            }
            else if(WhereAbout>=0.5 && WhereAbout<0.7){
                this.ExerciseLvl="Level 4";
            }
            else if(WhereAbout>=0.7 && WhereAbout<1.2){
                this.ExerciseLvl="Level 5";
            }
            else if(WhereAbout>=1.2 ){
                this.ExerciseLvl="Level 6";
            }

        }

    }
    private void SetHighestRecord(){
        this.HighestRecord=0.0;

    }
    public void AddRecordToArray(){
        if(this.HighestRecordArray!=null){
            this.HighestRecordArray.add(this.HighestRecord);
        }

    }
    public String GetExerciseName(){
        return (this.ExerciseName);

    }
    public String GetExerciseType(){
        return (this.ExerciseType);

    }
    public String GetMuscleName(){
        return (this.MuscleName);

    }

    public int GetSetNum(){
        return (this.SetNum);

    }

    public ArrayList<Integer> GetCurrentRepsNumberPerSet(){
        return (this.CurrentRepsNumberPerSet);

    }
    public ArrayList<Double> GetCurrentWeightPerSet(){
        return (this.CurrentWeightPerSet);


    }
    public void UpdateCurrentRepsNumberPerSet(int x){
        this.CurrentRepsNumberPerSet.add(x);
    }

    public void UpdateCurrentWeightPerSet(Double x){
        this.CurrentWeightPerSet.add(x);
        CheckForNewCurrentWight();
    }


    public void RemoveLastIndexCurrentWeightPerSet(){
        if(!this.CurrentWeightPerSet.isEmpty()){
            if(this.CurrentWeightPerSet.get( this.CurrentWeightPerSet.size()-1)==this.HighestRecord){
                if(!this.HighestRecordArray.isEmpty())
                {
                    if(!this.HighestRecordArray.contains(this.HighestRecord)){
                        this.HighestRecord=this.HighestRecordArray.get( this.CurrentWeightPerSet.size()-1);
                    }
                }
                else{
                    this.HighestRecord=0.0;
                }

            }
            this.CurrentWeightPerSet.remove( this.CurrentWeightPerSet.size()-1);
            CheckForNewCurrentWight();
        }

    }
    public void RemoveLastIndexCurrentRepsNumberPerSet(){
        this.CurrentRepsNumberPerSet.remove(this.CurrentRepsNumberPerSet.size()-1);
    }


    public ArrayList<Integer> GetPrevRepsNumberPerSet(){
        return (this.PrevRepsNumberPerSet);

    }
    public ArrayList<Double> GetPrevWeightPerSet(){
        return (this.PrevWeightPerSet);


    }
    public int GetMaxRepsNumberForProgram(){
        return (this.MaxRepsNumberForProgram);

    }
    public int GetMinRepsNumberForProgram(){
        return (this.MinRepsNumberForProgram);

    }
    public Double GetCurrentExerciseWeight(){
        return (this.CurrentExerciseWeight);

    }
    public Double GetCurrentExerciseGoalWeight(){
        return (this.CurrentExerciseGoalWeight);

    }
    public Double GetFinalExerciseGoalWeight(){
        return (this.FinalExerciseGoalWeight);

    }
    public Double GetPersonWeight(){
        return (this.PersonWeight);

    }
    public String GetExerciseLvl(){
        return (this.ExerciseLvl);

    }

    public Double GetHighestRecord(){
        return this.HighestRecord;
    }

    private void UpdateHighestRecord(){
        if(this.HighestRecord < this.CurrentExerciseWeight){
            this.HighestRecord=this.CurrentExerciseWeight;
        }

    }

    public void RefreshSetStartingRepsNumberPerSet(int NewSetNum){
        SetSetNum(NewSetNum);
        SetStartingRepsNumberPerSet();

    }


    private void UpdateCurrentExerciseWeight(Double Weight){
        this.CurrentExerciseWeight=Weight;
    }

    private void CheckForNewCurrentWight(){
        if(!this.CurrentWeightPerSet.isEmpty()){
            UpdateCurrentExerciseWeight(MaxWorkoutWeight());
            SetCurrentExerciseGoalWeight();
            SetFinalExerciseGoalWeight();
            SetStringExerciseLvl();
            UpdateHighestRecord();

        }
        else{
            UpdateCurrentExerciseWeight(0.0);
            SetCurrentExerciseGoalWeight();
            SetFinalExerciseGoalWeight();
            SetStringExerciseLvl();
            UpdateHighestRecord();
        }

    }
    private Double MaxWorkoutWeight(){
        Double x=0.0;
        for(Double Y: this.CurrentWeightPerSet){
            if(Y>x){
                x=Y;
            }

        }
        return (x);
    }

    public void ResetPrevCurrentWeightPerSet(){
        this.PrevWeightPerSet.clear();

    }
    public void ResetPrevCurrentRepsPerSet(){
        this.PrevRepsNumberPerSet.clear();
    }




    public String GetGeneralExercisesNames(){
        String Temp="";
        for(Exercise x: GeneralExercises) {
            Temp+= x.GetExerciseName();
        }
        return(Temp);
    }

    public ArrayList<Exercise> GetGeneralExercises(){
        return GeneralExercises;

    }

    public Boolean ExerciseInGeneralExercises(){
        for(Exercise x : GeneralExercises){
            if(x.GetExerciseName()==this.GetExerciseName()){
                return  (true);
            }
        }
        return  (false);
    }
    public void WeightUpdate(Double NewWeight){

        SetPersonWeight(NewWeight);
        SetFinalExerciseGoalWeight();
        SetStringExerciseLvl();

    }



}




