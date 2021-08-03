package com.example.betterathleat;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    //general
    private String Name;
    private Integer Age;
    private Double Weight;
    private Double Height;
    private String Help;
    private String Purpose;

   ///if he has lifting experience and want program
    private Double TrainingTime;
    private String [] LiftRecord= new String[6];
    private Integer MostUsedRepRange;
    private String TrainingStyle;
    private Integer NumberOfAccomplishedPrograms;
    ///if he does not have lifting experience and want program
    private Integer WorkOutPerWeek;
    private Integer TrainingSeriousness;


    ///if he want menu
    private String FoodIdeology;
    private String [] LikedFoods;

    private Boolean Finish;


    public User( String Name,
        Double Weight,
        Double Height,
        Integer Age,
        String Help,
        String Purpose,
        Double TrainingTime,
        String TrainingStyle,
        Integer WorkOutPerWeek,
        Integer TrainingSeriousness,
        String [] LiftRecord,
        Integer MostUsedRepRange,
        Integer NumberOfAccomplishedPrograms,
        String FoodIdeology,
        String [] LikedFoods,

        Boolean Finish
                 ){
    SetUser(Name, Weight, Height, Age, Help,Purpose, TrainingTime, TrainingStyle, WorkOutPerWeek, TrainingSeriousness, LiftRecord, MostUsedRepRange, NumberOfAccomplishedPrograms, FoodIdeology, LikedFoods, Finish);


}

public String WhereAmI(){
        if (this.Name=="0")
    {
        return ("1");
    }
        else if(this.Age==0){
            return ("2");
        }
        else if(this.Weight==0){
            return ("3");
        }
        else if(this.Height==0){
            return ("4");
        }

        else if(this.Help=="0"){
            return ("5");
        }
        else if(this.Purpose=="0"){
            return ("6");
        }
        else if((this.TrainingTime==0 )&&(this.Help!="3")){
            return ("7");
        }
        else if((this.LiftRecord[0]=="0")&&(this.LiftRecord[1]=="0")&&(this.LiftRecord[2]=="0")&&(this.LiftRecord[3]=="0")&&(this.LiftRecord[4]=="0")&&(this.LiftRecord[5]=="0")&&(this.Help!="3"))
        {
            return ("8");
        }
        else if((this.MostUsedRepRange==0 )&&(this.Help!="3")){
            return ("9");
        }
        else if((this.TrainingStyle=="0") &&(this.Help!="3")){
            return ("10");
        }
        else if((this.NumberOfAccomplishedPrograms==0) &&(this.Help!="3")){
            return ("11");
        }
        else if((this.WorkOutPerWeek==0)&&(this.Help!="3")){
            return ("12");
        }
        else if((this.TrainingSeriousness==0)&&(this.Help!="3")){
            return ("13");
        }

        else if((this.FoodIdeology=="0")&&(this.Help!="2")){
            return ("14");
        }
        else if((this.LikedFoods[0]=="0")&&(this.LikedFoods[1]=="0")&&(this.LikedFoods[2]=="0")&&(this.LikedFoods[3]=="0")&&(this.LikedFoods[4]=="0")&&(this.LikedFoods[5]=="0")&&(this.Help!="2"))
        {
            return ("15");
        }
        else if(this.Finish==true){
            return ("16");
        }
        else{
            return ("0");
        }

}
    private void SetUser(String Name,
                         Double Weight,
                         Double Height,
                         Integer Age,
                         String Help,
                         String Purpose,
                         Double TrainingTime,
                         String TrainingStyle,
                         Integer WorkOutPerWeek,
                         Integer TrainingSeriousness,
                         String [] LiftRecord,
                         Integer MostUsedRepRange,
                         Integer NumberOfAccomplishedPrograms,
                         String FoodIdeology,
                         String [] LikedFoods,

                         Boolean Finish)
    {

        SetName(Name);
        SetWeight(Weight);
        SetHeight(Height);
        SetAge(Age);
        SetHelp(Help);
        SetPurpose(Purpose);
        SetTrainingTime(TrainingTime);
        SetTrainingStyle(TrainingStyle);
        SetWorkOutPerWeek(WorkOutPerWeek);
        SetTrainingSeriousness(TrainingSeriousness);
        SetLiftRecord(LiftRecord);
        SetMostUsedRepRange(MostUsedRepRange);
        SetNumberOfAccomplishedPrograms(NumberOfAccomplishedPrograms);
        SetFoodIdeology(FoodIdeology);
        SetLikedFoods(LikedFoods);
        SetFinish(Finish);

    }
    public void SetName(String Name){
        this.Name=Name;
    }
    public String GetName(){
        return this.Name;
    }

    public void SetWeight(Double Weight){
        this.Weight=Weight;
    }

    public Double GetWeight(){
        return this.Weight;
    }


    public void SetHeight(Double Height){
        this.Height=Height;
    }

    public Double GetHeight(){
        return this.Height;
    }

    public void SetAge( Integer Age){
        this.Age=Age;
    }

    public Integer GetAge(){
        return this.Age;
    }

    public void SetPurpose(String Purpose)
    {
        this.Purpose=Purpose;
    }

    public String GetPurpose(){
        return this.Purpose;
    }

    public void SetHelp( String Help){

        this.Help=Help;
    }

    public String GetHelp(){
        return this.Help;
    }

    public void SetTrainingTime( Double TrainingTime){
        this.TrainingTime=TrainingTime;
    }

    public Double GetTrainingTime(){
        return this.TrainingTime;
    }

    public void SetTrainingStyle(String TrainingStyle){
        this.TrainingStyle=TrainingStyle;
    }

    public String GetTrainingStyle(){
        return this.TrainingStyle;
    }

    public void SetWorkOutPerWeek(Integer WorkOutPerWeek){
        this.WorkOutPerWeek=WorkOutPerWeek;
    }

    public Integer GetWorkOutPerWeek(){
        return this.WorkOutPerWeek;
    }

    public void SetTrainingSeriousness(Integer TrainingSeriousness){
        this.TrainingSeriousness=TrainingSeriousness;
    }

    public Integer GetTrainingSeriousness(){
        return this.TrainingSeriousness;
    }

    public void SetLiftRecord(String [] LiftRecord){
        this.LiftRecord=LiftRecord;
    }

    public String [] GetLiftRecord(){
        return this.LiftRecord;
    }

    public void SetMostUsedRepRange(Integer MostUsedRepRange){
        this.MostUsedRepRange=MostUsedRepRange;
    }

    public Integer GetMostUsedRepRange(){
        return this.MostUsedRepRange;
    }

    public void SetNumberOfAccomplishedPrograms(Integer NumberOfAccomplishedPrograms){
        this.NumberOfAccomplishedPrograms=NumberOfAccomplishedPrograms;
    }

    public Integer GetNumberOfAccomplishedPrograms(){
        return this.NumberOfAccomplishedPrograms;
    }

    public void SetFoodIdeology(String FoodIdeology){
        this.FoodIdeology=FoodIdeology;
    }

    public String GetFoodIdeology(){
        return this.FoodIdeology;
    }

    public void SetLikedFoods( String [] LikedFoods){
        this.LikedFoods=LikedFoods;
    }

    public String [] GetLikedFoods(){
        return this.LikedFoods;
    }


    public void SetFinish(Boolean Finish){
        this.Finish=Finish;
    }

    public Boolean GetFinish(){
        return this.Finish;
    }





    public String PersonalizedProgramName(){

      if((this.WorkOutPerWeek==1)&&(this.TrainingStyle.equals("FullBody"))){
          return("FullBody1");
      }

      else if((this.WorkOutPerWeek==2)&&(this.TrainingStyle.equals("FullBody"))){

          return("FullBody2");

      }
      else if((this.WorkOutPerWeek==3)&&(this.TrainingStyle.equals("FullBody"))){

          return("FullBody3");

      }
      else if((this.WorkOutPerWeek==4)&&(this.TrainingStyle.equals("FullBody"))){

          return("FullBody4");

      }
      else if((this.WorkOutPerWeek==5)&&(this.TrainingStyle.equals("FullBody"))){


          return("FullBody5ToBroSplit5");
      }
      else if((this.WorkOutPerWeek==6)&&(this.TrainingStyle.equals("FullBody"))){
          return("FullBody6ToBroSplit6");


      }
      else if((this.WorkOutPerWeek==7)&&(this.TrainingStyle.equals("FullBody"))){
          return("FullBody7ToBroSplit7");


      }
      else if((this.WorkOutPerWeek==1)&&(this.TrainingStyle.equals("BroSplit"))){

          return("BroSplit1");

      }
      else if((this.WorkOutPerWeek==2)&&(this.TrainingStyle.equals("BroSplit"))){


          return("BroSplit2");
      }
      else if((this.WorkOutPerWeek==3)&&(this.TrainingStyle.equals("BroSplit"))){


          return("BroSplit3");
      }
      else if((this.WorkOutPerWeek==4)&&(this.TrainingStyle.equals("BroSplit"))){

          return("BroSplit4");

      }
      else if((this.WorkOutPerWeek==5)&&(this.TrainingStyle.equals("BroSplit"))){

          return("BroSplit5");

      }
      else if((this.WorkOutPerWeek==6)&&(this.TrainingStyle.equals("BroSplit"))){

          return("BroSplit6");

      }
      else if((this.WorkOutPerWeek==7)&&(this.TrainingStyle.equals("BroSplit"))){

          return("BroSplit7");

      }

      else if((this.WorkOutPerWeek==1)&&(this.TrainingStyle.equals("PushPull"))){

          return("PushPull1ToBroSplit1");

      }
      else if((this.WorkOutPerWeek==2)&&(this.TrainingStyle.equals("PushPull"))){


          return("PushPull2ToBroSplit2");
      }
      else if((this.WorkOutPerWeek==3)&&(this.TrainingStyle.equals("PushPull"))){

          return("PushPull3");

      }
      else if((this.WorkOutPerWeek==4)&&(this.TrainingStyle.equals("PushPull"))){

          return("PushPull4");

      }
      else if((this.WorkOutPerWeek==5)&&(this.TrainingStyle.equals("PushPull"))){


          return("PushPull5");
      }
      else if((this.WorkOutPerWeek==6)&&(this.TrainingStyle.equals("PushPull"))){

          return("PushPull6");

      }
      else if((this.WorkOutPerWeek==7)&&(this.TrainingStyle.equals("PushPull"))){

          return("PushPull7ToBroSplit7");

      }
      else if((this.WorkOutPerWeek==1)&&(this.TrainingStyle.equals("UpperLower"))){

          return("UpperLower1ToBroSplit1");

      }
      else if((this.WorkOutPerWeek==2)&&(this.TrainingStyle.equals("UpperLower"))){

          return("UpperLower2");

      }
      else if((this.WorkOutPerWeek==3)&&(this.TrainingStyle.equals("UpperLower"))){


          return("UpperLower3");
      }
      else if((this.WorkOutPerWeek==4)&&(this.TrainingStyle.equals("UpperLower"))){

          return("UpperLower4");

      }
      else if((this.WorkOutPerWeek==5)&&(this.TrainingStyle.equals("UpperLower"))){


          return("UpperLower5");
      }
      else if((this.WorkOutPerWeek==6)&&(this.TrainingStyle.equals("UpperLower"))){


          return("UpperLower6ToBroSplit6");
      }
      else if((this.WorkOutPerWeek==7)&&(this.TrainingStyle.equals("UpperLower"))){


          return("UpperLower7ToBroSplit7");
      }

      else{
          return("error");
      }


    }


    public int NumOfDiffrentWorkoutInPersonalizedProgram(String PersonalizedProgramName){
        switch(PersonalizedProgramName)
        {
            case "FullBody1":
                return (1);
            case "FullBody2":
                return (2);
            case "FullBody3":
                return (2);
            case "FullBody4":
                return (2);
            case "FullBody5ToBroSplit5":
                return (3);
            case "FullBody6ToBroSplit6":
                return (3);
            case "FullBody7ToBroSplit7":
                return (4);

            case  "BroSplit1":
                return (1);
            case  "BroSplit2":
                return (2);
            case  "BroSplit3":
                return (2);
            case  "BroSplit4":
                return (2);
            case  "BroSplit5":
                return (3);
            case  "BroSplit6":
                return (3);
            case  "BroSplit7":
                return (4);
            case  "PushPull1ToBroSplit1":
                return (1);
            case  "PushPull2ToBroSplit2":
                return (2);
            case  "PushPull3":
                return (3);

            case  "PushPull4":
                return (3);

            case  "PushPull5":
                return (3);

            case  "PushPull6":
                return (3);

            case  "PushPull7ToBroSplit7":
                return (4);

            case   "UpperLower1ToBroSplit1":
                return (1);

            case   "UpperLower2":
                return (2);

            case   "UpperLower3":
                return (2);

            case   "UpperLower4":
                return (2);

            case   "UpperLower5":
                return (2);

            case   "UpperLower6ToBroSplit6":
                return (3);

            case   "UpperLower7ToBroSplit7":
                return (4);

            default: return(0);
        }

    }














}
