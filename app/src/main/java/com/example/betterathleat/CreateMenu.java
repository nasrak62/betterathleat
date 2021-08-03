package com.example.betterathleat;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateMenu implements Serializable {

    private Double WeightInPounds;
    private Double HeightInFoots;
    private Double WeightInKgs;
    private Double HeightInInches;
    private Double HeightInCms;
    private int Age;
    private ArrayList<Foods> FoodsForMenu=new  ArrayList<Foods>();
    private ArrayList<Foods> ProtinSourceForMenu=new  ArrayList<Foods>();
    private ArrayList<Foods> CarbsSourceForMenu=new  ArrayList<Foods>();
    private ArrayList<Foods> FatsSourceForMenu=new  ArrayList<Foods>();
    private ArrayList<Foods> FruitsSourceForMenu=new  ArrayList<Foods>();
    private ArrayList<Foods> VegetablesSourceForMenu=new  ArrayList<Foods>();
    private ArrayList<Foods> MilkSourceForMenu=new  ArrayList<Foods>();

    private Double Harris_BenedictMale;
    private Double Harris_Benedict2Male;
    private Double MiffinStJeorMale;
    private Double Harris_BenedictFemale;
    private Double Harris_Benedict2Female;
    private Double MiffinStJeorFemale;
    private Double TotalCalories;
    private Double TotalProtins;
    private Double TotalCarbs;
    private Double TotalFats;
    private int NumberOfMeals;
    private String Purpose;
    private String FoodIdeology;
    private ArrayList<ArrayList<Foods>> Meals= new ArrayList<ArrayList<Foods>>();
    private ArrayList<Double> CalorisPerMeal=new  ArrayList<Double>();
    private ArrayList<Double> ProtinPerMeal=new  ArrayList<Double>();
    private ArrayList<Double> CarbsPerMeal=new  ArrayList<Double>();
    private ArrayList<Double> FatsPerMeal=new  ArrayList<Double>();
    private Double UsedCaloriesForMeal;
    private Double UsedProtinsForMeal;
    private Double UsedCarbsForMeal;
    private Double UsedFatsForMeal;
    private Foods ProtinForMeal;
    private Foods FatsForMeal;
    private Foods CarbsForMeal;
    private Foods FruitsForMeal;
    private Foods VegetablesForMeal;
    private Foods MilkForMeal;
    private Foods GotChosen;
    private boolean NoCarbs;
    private Double TotalCaloriesForMeal;
    private Double TotalProtinsForMeal;
    private Double TotalCarbsForMeal;
    private Double TotalFatsForMeal;
    private ArrayList<Foods> MealInDesinMeal =new  ArrayList<Foods>();
    private ArrayList<Foods> CarbsOptionsMealForDesignMeal=new  ArrayList<Foods>();
    private ArrayList<String> SideDishOptionsMealForDesignMeal=new  ArrayList<String>();
    private int RandomFoodForMealDesign;
    private int NumberOfTriesForMealDesign;
    private ArrayList<Foods> VegetarianCarbsOptionsMealForMealDesign=new  ArrayList<Foods>();;
    private ArrayList<Foods> VeganCarbsOptionsMealForMealDesign=new  ArrayList<Foods>();;
    private boolean BreakFromSwitch;
    private Foods FutureFoodTest=null;
    private Foods FiveFutureFoodTest=null;


    public CreateMenu( Double WeightPounds, Double HeightFoots, int Age, ArrayList<Foods> FoodsForMenu, int NumberOfMeals,String Purpose, String FoodIdeology){
        SetVariables(WeightPounds, HeightFoots,  Age,  FoodsForMenu, NumberOfMeals,Purpose,FoodIdeology);
        CalculateBMR();
        CalculateCalories();
        CalculateMacros();
        SelectFoodsForMenu();



    }


    private void SetVariables(Double WeightPounds, Double HeightFoots, int Age, ArrayList<Foods> FoodsForMenu , int NumberOfMeals,String Purpose,String FoodIdeology){
        SetWeight(WeightPounds);
        SetHeight(HeightFoots);
        SetAge(Age);
        SetFoodsForMenu(FoodsForMenu);
        SetNumberOfMeals(NumberOfMeals);
        SetPurpose(Purpose);
        SetFoodIdeology(FoodIdeology);


    }



    private void CalculateBMR(){
        Harris_BenedictMale=66.473+(13.7516* this.WeightInKgs)+ (3.0033*this.HeightInCms)-(6.755*this.Age);
        Harris_Benedict2Male=88.362+(13.397* this.WeightInKgs)+ (4.799*this.HeightInCms)-(5.677*this.Age);
        MiffinStJeorMale=(10* this.WeightInKgs)+ (6.25*this.HeightInCms)-(5*this.Age)+5;
        Harris_BenedictFemale=655.0955+(9.5634* this.WeightInKgs)+ (1.8496*this.HeightInCms)-(4.6756*this.Age);
        Harris_Benedict2Female=447.593+(9.247* this.WeightInKgs)+ (3.098*this.HeightInCms)-(4.33*this.Age);
        MiffinStJeorFemale=(10* this.WeightInKgs)+ (6.25*this.HeightInCms)-(5*this.Age)-161;



        Harris_BenedictMale*=1.2;
        Harris_Benedict2Male*=1.2;
        MiffinStJeorMale*=1.2;
        Harris_BenedictFemale*=1.2;
        Harris_Benedict2Female*=1.2;
        MiffinStJeorFemale*=1.2;
    }





    private void CalculateCalories(){
        TotalCalories=15*WeightInPounds;
        Double MinDistance=1000.0;
        Double DistanceHarris_BenedictMale=Math.abs(15*WeightInPounds-Harris_BenedictMale);
        Double DistanceHarris_Benedict2Male=Math.abs(15*WeightInPounds-Harris_Benedict2Male);
        Double DistanceMiffinStJeorMale=Math.abs(15*WeightInPounds-MiffinStJeorMale);
        Double DistanceHarris_BenedictFemale=Math.abs(15*WeightInPounds-Harris_BenedictFemale);
        Double DistanceHarris_Benedict2Female=Math.abs(15*WeightInPounds-Harris_Benedict2Female);
        Double DistanceMiffinStJeorFemale=Math.abs(15*WeightInPounds-MiffinStJeorFemale);

        if (MinDistance > DistanceHarris_BenedictMale && DistanceHarris_BenedictMale<=100) {
            MinDistance =DistanceHarris_BenedictMale;
            TotalCalories=Harris_BenedictMale;
        }
        if (MinDistance >DistanceHarris_Benedict2Male && DistanceHarris_Benedict2Male<=100) {
            MinDistance =DistanceHarris_Benedict2Male;
            TotalCalories=Harris_Benedict2Male;
        }
        if (MinDistance > DistanceMiffinStJeorMale && DistanceMiffinStJeorMale<=100) {
            MinDistance =DistanceMiffinStJeorMale;
            TotalCalories=MiffinStJeorMale;
        }
        if (MinDistance > DistanceHarris_BenedictFemale && DistanceHarris_BenedictFemale<=100) {
            MinDistance =DistanceHarris_BenedictFemale;
            TotalCalories=Harris_BenedictFemale;
        }
        if (MinDistance > DistanceHarris_Benedict2Female && DistanceHarris_Benedict2Female<=100) {
            MinDistance =DistanceHarris_Benedict2Female;
            TotalCalories=Harris_Benedict2Female;
        }
        if (MinDistance > DistanceMiffinStJeorFemale && DistanceMiffinStJeorFemale<=100) {
            MinDistance =DistanceMiffinStJeorFemale;
            TotalCalories=MiffinStJeorFemale;
        }

        switch ( this.Purpose) {
            case "Shreded":
                TotalCalories=TotalCalories*(1-0.375);
                break;

            case "Lean":
                TotalCalories=TotalCalories*(1-0.25);
                break;

            case "Lean Mass":
                TotalCalories=TotalCalories*0.9;
                break;

            case "Dirty Mass":
                TotalCalories=TotalCalories*(1-0.175);
                break;

            case "Power":
                TotalCalories=TotalCalories*(1-0.1);
                break;
        }

        if(TotalCalories<800){
            TotalCalories=800.0;
        }

    }

    private void CalculateMacros(){


        switch (this.FoodIdeology){
            case "1" :              /////NoProblem
            case "4" :              /////Vegetarian
            case "5" :              /////Vegan


                switch ( this.Purpose) {
                    case "Shreded":
                    case "Lean":
                    case "Recomposition":
                        TotalProtins=Math.max(WeightInPounds*0.8,TotalCalories*0.25/4);
                        TotalFats=Math.min(WeightInPounds*0.25,TotalCalories*0.2/9);
                        TotalCarbs=(TotalCalories-TotalProtins*4-TotalFats*9)/4;
                        break;

                    case "Lean Mass":
                    case "Dirty Mass":
                    case "Power":
                        TotalProtins=Math.min(WeightInPounds*0.8,TotalCalories*0.25/4);
                        TotalFats=Math.max(WeightInPounds*0.3,TotalCalories*0.2/9);
                        TotalCarbs=(TotalCalories-TotalProtins*4-TotalFats*9)/4;
                        break;
                }


                break;
            case "2" :              /////NoCarbs
                switch ( this.Purpose) {
                    case "Shreded":
                    case "Lean":
                    case "Recomposition":
                        TotalProtins=TotalCalories*0.45/4;
                        TotalFats=TotalCalories*0.45/9;
                        TotalCarbs=TotalCalories*0.1/4;
                        break;

                    case "Lean Mass":
                    case "Dirty Mass":
                    case "Power":
                        TotalProtins=TotalCalories*0.5/4;
                        TotalFats=TotalCalories*0.45/9;
                        TotalCarbs=TotalCalories*0.05/4;
                        break;
                }



                break;
            case "3" :              /////NoFats
                switch ( this.Purpose) {
                    case "Shreded":
                    case "Lean":
                    case "Recomposition":
                        TotalProtins=TotalCalories*0.45/4;
                        TotalFats=TotalCalories*0.1/9;
                        TotalCarbs=TotalCalories*0.45/4;
                        break;

                    case "Lean Mass":
                    case "Dirty Mass":
                    case "Power":
                        TotalProtins=TotalCalories*0.4/4;
                        TotalFats=TotalCalories*0.1/9;
                        TotalCarbs=TotalCalories*0.6/4;
                        break;
                }



                break;

        }

    }






    private void SelectFoodsForMenu(){
        PrepareCaloriesPerMealArrays();

        Meals.clear();
        for(int i=0; i<this.NumberOfMeals;i++){
            TotalCaloriesForMeal=CalorisPerMeal.get(i);
            TotalProtinsForMeal=ProtinPerMeal.get(i);
            TotalCarbsForMeal=CarbsPerMeal.get(i);
            TotalFatsForMeal=FatsPerMeal.get(i);

            Meals.add(DesinMeal());

        }

    }







    private ArrayList<Foods> DesinMeal() {
        UsedCaloriesForMeal =0.0;
        UsedProtinsForMeal =0.0;
        UsedCarbsForMeal =0.0;
        UsedFatsForMeal =0.0;
        ProtinForMeal=null;
        FatsForMeal=null;
        CarbsForMeal=null;
        FruitsForMeal=null;
        VegetablesForMeal=null;
        MilkForMeal=null;
        GotChosen=null;

        MealInDesinMeal=new  ArrayList<Foods>();
        CarbsOptionsMealForDesignMeal=new  ArrayList<Foods>();
        SideDishOptionsMealForDesignMeal=new  ArrayList<String>();
        if(!FruitsSourceForMenu.isEmpty() ){
            SideDishOptionsMealForDesignMeal.add("Fruits");
        }
        if(!VegetablesSourceForMenu.isEmpty() ){
            SideDishOptionsMealForDesignMeal.add("Vegetables");
        }
        if(!MilkSourceForMenu.isEmpty() ){
            SideDishOptionsMealForDesignMeal.add("Milk");
        }

        SortMacrosIfThereAreSomeDeficiencies();

        switch (this.FoodIdeology){
            case "1" :              /////NoProblem
            case "2" :              /////NoCarbs
            case "3" :              /////NoFats
                BuildMealForMeatEaters();
            break;

            case "4" :              /////Vegetarian
                BuildMealFoVegetarianEaters();
            break;

            case "5" :              /////Vegan
                BuildMealFoVeganEaters();
                break;

        }







        if(ProtinForMeal!=null){


            MealInDesinMeal.add(new Foods( ProtinForMeal.GetAmount(), ProtinForMeal.GetPer100grmCalories(),ProtinForMeal.GetPer100grmProtins(),ProtinForMeal.GetPer100grmFats(),ProtinForMeal.GetPer100grmCarbs(),ProtinForMeal.GetName(),ProtinForMeal.GetType()));
                String TempName1=ProtinForMeal.GetName();
        }

        if(FatsForMeal!=null){
            MealInDesinMeal.add(new Foods( FatsForMeal.GetAmount(), FatsForMeal.GetPer100grmCalories(),FatsForMeal.GetPer100grmProtins(),FatsForMeal.GetPer100grmFats(),FatsForMeal.GetPer100grmCarbs(),FatsForMeal.GetName(),FatsForMeal.GetType()));

        }


        for (Foods X: CarbsOptionsMealForDesignMeal){
            MealInDesinMeal.add(new Foods(X.GetAmount(), X.GetPer100grmCalories(),X.GetPer100grmProtins(),X.GetPer100grmFats(),X.GetPer100grmCarbs(),X.GetName(),X.GetType()));

        }


        return (MealInDesinMeal);


    }








    private void SortMacrosIfThereAreSomeDeficiencies(){
        if(CarbsSourceForMenu.isEmpty() && MilkSourceForMenu.isEmpty() && FruitsSourceForMenu.isEmpty() &&VegetablesSourceForMenu.isEmpty() ){
            NoCarbs=true;
        }
        else{

            NoCarbs=false;
        }

        if(ProtinSourceForMenu.isEmpty()    &&   FatsSourceForMenu.isEmpty() &&  !NoCarbs  ){
            TotalCarbsForMeal += TotalProtinsForMeal;
            TotalCarbsForMeal += TotalFatsForMeal;
        }
        else if(ProtinSourceForMenu.isEmpty()    &&   !FatsSourceForMenu.isEmpty() &&  !NoCarbs  ){

            switch (this.FoodIdeology){
                case "1" :              /////NoProblem
                case "4" :              /////Vegetarian
                case "5" :              /////Vegan
                case "3" :              /////NoFats

                    TotalCarbsForMeal += TotalProtinsForMeal;



                    break;
                case "2" :              /////NoCarbs

                    TotalFatsForMeal += TotalProtinsForMeal;


                    break;




            }
        }
        else if(ProtinSourceForMenu.isEmpty()    &&   !FatsSourceForMenu.isEmpty() &&  NoCarbs  ){

            TotalFatsForMeal += TotalProtinsForMeal;
            TotalFatsForMeal += TotalCarbsForMeal;
        }
        else if(!ProtinSourceForMenu.isEmpty()    &&   FatsSourceForMenu.isEmpty() &&  NoCarbs  ){

            TotalProtinsForMeal += TotalFatsForMeal;
            TotalProtinsForMeal += TotalCarbsForMeal;
        }
        else if(!ProtinSourceForMenu.isEmpty()    &&   !FatsSourceForMenu.isEmpty() &&  NoCarbs  ){
            TotalProtinsForMeal += TotalCarbsForMeal;


        }
        else if(!ProtinSourceForMenu.isEmpty()    &&   FatsSourceForMenu.isEmpty() &&  !NoCarbs  ){
            TotalProtinsForMeal += TotalFatsForMeal;


        }

    }





    private void BuildMealForMeatEaters(){

        RandomFoodForMealDesign=0;
        NumberOfTriesForMealDesign=0;


        BuildMealForMeatEaters_Protin();
        UpdateUsedMacros();
        BuildMealForMeatEaters_SideDish();
        UpdateUsedMacros();
        BuildMealForMeatEaters_Carbs();
        UpdateUsedMacros();
        BuildMeal_Fats();
        UpdateUsedMacros();














    }



    private void BuildMealFoVegetarianEaters(){

        RandomFoodForMealDesign=0;
        NumberOfTriesForMealDesign=0;



       VegetarianCarbsOptionsMealForMealDesign=new  ArrayList<Foods>();
        for (Foods X : ProtinSourceForMenu){
            if(X.GetType().equals("Egg")|| (X.GetType().equals("MeatSub"))){

                VegetarianCarbsOptionsMealForMealDesign.add(X);
            }
        }
        for (Foods X : CarbsSourceForMenu){
            VegetarianCarbsOptionsMealForMealDesign.add(X);
        }

        BuildMealFoVegetarianEaters_SideDish();
        UpdateUsedMacros();
        BuildMealFoVegetarianEaters_Protin();
        UpdateUsedMacros();
        BuildMealFoVegetarianEaters_Carbs();
        UpdateUsedMacros();
        BuildMeal_Fats();
        UpdateUsedMacros();


    }



    private void BuildMealFoVeganEaters(){
        RandomFoodForMealDesign=0;
        NumberOfTriesForMealDesign=0;

        VeganCarbsOptionsMealForMealDesign=new  ArrayList<Foods>();
        for (Foods X : ProtinSourceForMenu){
            if((X.GetType().equals("MeatSub"))){

                VeganCarbsOptionsMealForMealDesign.add(X);
            }
        }
        for (Foods X : CarbsSourceForMenu){
            VeganCarbsOptionsMealForMealDesign.add(X);
        }


        BuildMealFoVeganEaters_SideDish();
        UpdateUsedMacros();
        BuildMealForVeganEaters_Protin();
        UpdateUsedMacros();
        BuildMealForVeganEaters_Carbs();
        UpdateUsedMacros();
        BuildMeal_Fats();
        UpdateUsedMacros();


    }



    //////Setters/////
    private void SetWeight( Double WeightPounds){
        this.WeightInPounds=WeightPounds;
        this.WeightInKgs=WeightPounds/2.2;


    }
    private void SetHeight( Double HeightFoots){
        this.HeightInFoots=HeightFoots;
        this.HeightInInches=HeightFoots*12;
        this.HeightInCms=HeightFoots*30.48;




    }
    private void SetAge( int Age){
        this.Age=Age;




    }
    public void SetFoodsForMenu(ArrayList<Foods> FoodsForMenu){
        if (!FoodsForMenu.isEmpty()) {
            for(Foods X: FoodsForMenu){
                this.FoodsForMenu.add(X);


            }

            ProtinSourceForMenu.clear();
            CarbsSourceForMenu.clear();
            FatsSourceForMenu.clear();
            FruitsSourceForMenu.clear();
            VegetablesSourceForMenu.clear();
            MilkSourceForMenu.clear();
            for(Foods X:  this.FoodsForMenu){
                if(X.GetType().equals("MeatSub") || X.GetType().equals("Egg") ||X.GetType().equals("Fish") || X.GetType().equals("Meat")  ){
                    ProtinSourceForMenu.add(X);
                }
                else if(X.GetType().equals("Carbs")){
                    CarbsSourceForMenu.add(X);
                }
                else if(X.GetType().equals("Fats")){
                    FatsSourceForMenu.add(X);
                }
                else if(X.GetType().equals("Fruit")){
                    FruitsSourceForMenu.add(X);
                }
                else if(X.GetType().equals("Vegetable")){
                    VegetablesSourceForMenu.add(X);
                }
                else if(X.GetType().equals("Milk")){
                    MilkSourceForMenu.add(X);
                }



            }





        }


    }


    private void SetNumberOfMeals(int NumberOfMeals){
        this.NumberOfMeals=NumberOfMeals;

    }
    private void SetPurpose(String Purpose){
        this.Purpose=Purpose;

    }

    private void SetFoodIdeology(String FoodIdeology) {
        this.FoodIdeology=FoodIdeology;
    }




    /////Getters/////

    public ArrayList<ArrayList<Foods>> GetMeals(){
        return Meals;

    }


    public ArrayList<Double> GetCaloriesPerMeal(){

        return CalorisPerMeal;
    }
    public ArrayList<Double> GetProtinsPerMeal(){

        return ProtinPerMeal;
    }
    public ArrayList<Double> GetCarbsPerMeal(){

        return CarbsPerMeal;
    }
    public ArrayList<Double> GetFatsPerMeal(){

        return FatsPerMeal;
    }

    public Double GetTotalCalories(){

        return TotalCalories;
    }
    public Double GetTotalProtins(){

        return TotalProtins;
    }
    public Double GetTotalCarbs(){

        return TotalCarbs;
    }
    public Double GetTotalFats(){

        return TotalFats;
    }


    /////Make Life Easy/////

private int CalculateNextAmountOfFoodADD(Double Distance, Foods X){
    return ((int)(X.GetAmount()*(1+(Distance)/X.GetNetProtins())));


}

    private void  BuildMealForMeatEaters_Protin(){
        //////////////////////// Protins////////////////////

        if(!ProtinSourceForMenu.isEmpty()){

                UpdateUsedMacros();
                RandomFoodForMealDesign =((int) (Math.random() * ProtinSourceForMenu.size()));
                ProtinForMeal= ProtinSourceForMenu.get(RandomFoodForMealDesign);
                ProtinForMeal.ChangeAmount(1);
                NumberOfTriesForMealDesign=0;

                while(!AtLeastFiveGramsProtin(ProtinForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalProtinsForMeal-UsedProtinsForMeal) &&  NumberOfTriesForMealDesign< ProtinSourceForMenu.size()* ProtinSourceForMenu.size()) {
                    RandomFoodForMealDesign =((int) (Math.random() * ProtinSourceForMenu.size()));
                    ProtinForMeal= ProtinSourceForMenu.get(RandomFoodForMealDesign);
                    ProtinForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign++;

                }

                if(NumberOfTriesForMealDesign< ProtinSourceForMenu.size()* ProtinSourceForMenu.size()) {

                    GetFoodRightAMountPerCaloriesAndDesiredProtin(ProtinForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalProtinsForMeal-UsedProtinsForMeal);

                }
                else {
                    ProtinForMeal=null;
                }
            }



    }



    private void BuildMealForMeatEaters_SideDish(){
//////////////////////// Side Dish////////////////////
        UpdateUsedMacros();
        //////Enough Carbs For Side Dish Only////////////

        if(TotalCarbsForMeal <=130/4 && TotalCarbsForMeal > UsedCarbsForMeal+4 && TotalCaloriesForMeal > UsedCaloriesForMeal+10){
            RandomFoodForMealDesign =((int) (Math.random() * SideDishOptionsMealForDesignMeal.size()));
            String StringSideDishOptionsMealForDesignMeal=SideDishOptionsMealForDesignMeal.get(RandomFoodForMealDesign);
            switch (StringSideDishOptionsMealForDesignMeal){
                case "Fruits":




                    RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                    FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                    FruitsForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                        RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                        FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                        FruitsForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealForMeatEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                    CarbsOptionsMealForDesignMeal.add(FruitsForMeal);
                    GotChosen=FruitsForMeal;


                    break;

                case "Vegetables":


                    RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                    VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                    VegetablesForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                        RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                        VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                        VegetablesForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealForMeatEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                    CarbsOptionsMealForDesignMeal.add(VegetablesForMeal);
                    GotChosen=VegetablesForMeal;



                    break;
                case "Milk":
                    RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                    MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                    MilkForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                        RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                        MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                        MilkForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealForMeatEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }
                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                    CarbsOptionsMealForDesignMeal.add(MilkForMeal);
                    GotChosen=MilkForMeal;



                    break;
            }

        }


        //////Enough Carbs For Extra Side Dish////////////


        if(TotalCarbsForMeal - UsedCarbsForMeal >300/4 && TotalCarbsForMeal > UsedCarbsForMeal+4 && TotalCaloriesForMeal > UsedCaloriesForMeal +300+10){

            RandomFoodForMealDesign =((int) (Math.random() * SideDishOptionsMealForDesignMeal.size()));
            String StringSideDishOptionsMealForDesignMeal=SideDishOptionsMealForDesignMeal.get(RandomFoodForMealDesign);
            switch (StringSideDishOptionsMealForDesignMeal){
                case "Fruits":

                    RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                    FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                    FruitsForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;

                    while(!AtLeastFiveGramsCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                        RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                        FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                        FruitsForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealForMeatEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                    CarbsOptionsMealForDesignMeal.add(FruitsForMeal);
                    GotChosen=FruitsForMeal;


                    break;



                case "Vegetables":


                    RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                    VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                    VegetablesForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                        RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                        VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                        VegetablesForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealForMeatEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                    CarbsOptionsMealForDesignMeal.add(VegetablesForMeal);
                    GotChosen=VegetablesForMeal;



                    break;
                case "Milk":
                    RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                    MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                    MilkForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                        RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                        MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                        MilkForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealForMeatEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }
                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                    CarbsOptionsMealForDesignMeal.add(MilkForMeal);
                    GotChosen=MilkForMeal;



                    break;
            }

        }


    }



    private void BuildMealForMeatEaters_Carbs(){
        //////////////////Carbs/////////////////////////
        if(!CarbsSourceForMenu.isEmpty()){


                UpdateUsedMacros();
                RandomFoodForMealDesign =((int) (Math.random() * CarbsSourceForMenu.size()));
                CarbsForMeal= CarbsSourceForMenu.get(RandomFoodForMealDesign);
                CarbsForMeal.ChangeAmount(1);
                NumberOfTriesForMealDesign =0;

                while(!AtLeastFiveGramsCarbs(CarbsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal) && NumberOfTriesForMealDesign< CarbsSourceForMenu.size()* CarbsSourceForMenu.size()) {
                    RandomFoodForMealDesign =((int) (Math.random() * CarbsSourceForMenu.size()));
                    CarbsForMeal= CarbsSourceForMenu.get(RandomFoodForMealDesign);
                    CarbsForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign++;

                }


                if(NumberOfTriesForMealDesign< CarbsSourceForMenu.size()* CarbsSourceForMenu.size()) {

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(CarbsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                    CarbsOptionsMealForDesignMeal.add(CarbsForMeal);

                }
                else {
                    CarbsForMeal=null;
                }



        }

    }

    private void BuildMealFoVegetarianEaters_SideDish(){

        //////////////////////// Side Dish////////////////////
            //////Enough Carbs For Side Dish Only////////////

            if(TotalCarbsForMeal <=130/4 && TotalCarbsForMeal > UsedCarbsForMeal && TotalCaloriesForMeal > UsedCaloriesForMeal){
                RandomFoodForMealDesign =((int) (Math.random() * SideDishOptionsMealForDesignMeal.size()));
                String StringSideDishOptionsMealForDesignMeal=SideDishOptionsMealForDesignMeal.get(RandomFoodForMealDesign);
                switch (StringSideDishOptionsMealForDesignMeal){
                    case "Fruits":




                        RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                        FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                        FruitsForMeal.ChangeAmount(1);
                        NumberOfTriesForMealDesign =0;
                        BreakFromSwitch=false;
                        while(!AtLeastFiveGramsCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                            RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                            FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                            FruitsForMeal.ChangeAmount(1);
                            if (NumberOfTriesForMealDesign>1000){
                                BuildMealFoVegetarianEaters_SideDish();
                                BreakFromSwitch=true;
                                break;
                            }
                            NumberOfTriesForMealDesign++;

                        }

                        if (BreakFromSwitch){
                            break;
                        }

                        GetFoodRightAMountPerCaloriesAndDesiredCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                        CarbsOptionsMealForDesignMeal.add(FruitsForMeal);
                        GotChosen=FruitsForMeal;


                        break;

                    case "Vegetables":


                        RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                        VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                        VegetablesForMeal.ChangeAmount(1);
                        NumberOfTriesForMealDesign =0;
                        BreakFromSwitch=false;
                        while(!AtLeastFiveGramsCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                            RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                            VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                            VegetablesForMeal.ChangeAmount(1);
                            if (NumberOfTriesForMealDesign>1000){
                                BuildMealFoVegetarianEaters_SideDish();
                                BreakFromSwitch=true;
                                break;
                            }
                            NumberOfTriesForMealDesign++;

                        }

                        if (BreakFromSwitch){
                            break;
                        }

                        GetFoodRightAMountPerCaloriesAndDesiredCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                        CarbsOptionsMealForDesignMeal.add(VegetablesForMeal);
                        GotChosen=VegetablesForMeal;



                        break;
                    case "Milk":
                        RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                        MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                        MilkForMeal.ChangeAmount(1);
                        NumberOfTriesForMealDesign =0;
                        BreakFromSwitch=false;
                      while(!AtLeastFiveGramsCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                        RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                        MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                        MilkForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealFoVegetarianEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                          NumberOfTriesForMealDesign++;

                    }

                      if (BreakFromSwitch){
                          break;
                      }
                        GetFoodRightAMountPerCaloriesAndDesiredCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                        CarbsOptionsMealForDesignMeal.add(MilkForMeal);
                        GotChosen=MilkForMeal;



                        break;
                }

            }


            //////Enough Carbs For Extra Side Dish////////////


            if(TotalCarbsForMeal - UsedCarbsForMeal >300/4 && TotalCarbsForMeal > UsedCarbsForMeal && TotalCaloriesForMeal > UsedCaloriesForMeal+300){

                RandomFoodForMealDesign =((int) (Math.random() * SideDishOptionsMealForDesignMeal.size()));
                String StringSideDishOptionsMealForDesignMeal=SideDishOptionsMealForDesignMeal.get(RandomFoodForMealDesign);
                switch (StringSideDishOptionsMealForDesignMeal){
                    case "Fruits":

                        RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                        FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                        FruitsForMeal.ChangeAmount(1);
                        NumberOfTriesForMealDesign =0;
                        BreakFromSwitch=false;

                        while(!AtLeastFiveGramsCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                            RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                            FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                            FruitsForMeal.ChangeAmount(1);
                            if (NumberOfTriesForMealDesign>1000){
                                BuildMealFoVegetarianEaters_SideDish();
                                BreakFromSwitch=true;
                                break;
                            }
                            NumberOfTriesForMealDesign++;

                        }

                        if (BreakFromSwitch){
                            break;
                        }

                        GetFoodRightAMountPerCaloriesAndDesiredCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                        CarbsOptionsMealForDesignMeal.add(FruitsForMeal);
                        GotChosen=FruitsForMeal;


                        break;



                    case "Vegetables":


                        RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                        VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                        VegetablesForMeal.ChangeAmount(1);
                        NumberOfTriesForMealDesign =0;
                        BreakFromSwitch=false;
                        while(!AtLeastFiveGramsCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                            RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                            VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                            VegetablesForMeal.ChangeAmount(1);
                            if (NumberOfTriesForMealDesign>1000){
                                BuildMealFoVegetarianEaters_SideDish();
                                BreakFromSwitch=true;
                                break;
                            }
                            NumberOfTriesForMealDesign++;

                        }

                        if (BreakFromSwitch){
                            break;
                        }

                        GetFoodRightAMountPerCaloriesAndDesiredCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                        CarbsOptionsMealForDesignMeal.add(VegetablesForMeal);
                        GotChosen=VegetablesForMeal;



                        break;
                    case "Milk":
                        RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                        MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                        MilkForMeal.ChangeAmount(1);
                        NumberOfTriesForMealDesign =0;
                        BreakFromSwitch=false;
                        while(!AtLeastFiveGramsCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                            RandomFoodForMealDesign =((int) (Math.random() * MilkSourceForMenu.size()));
                            MilkForMeal= MilkSourceForMenu.get(RandomFoodForMealDesign);
                            MilkForMeal.ChangeAmount(1);
                            if (NumberOfTriesForMealDesign>1000){
                                BuildMealFoVegetarianEaters_SideDish();
                                BreakFromSwitch=true;
                                break;
                            }
                            NumberOfTriesForMealDesign++;

                        }

                        if (BreakFromSwitch){
                            break;
                        }
                        GetFoodRightAMountPerCaloriesAndDesiredCarbs(MilkForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                        CarbsOptionsMealForDesignMeal.add(MilkForMeal);
                        GotChosen=MilkForMeal;



                        break;
                }

            }

    }


    private void BuildMealFoVegetarianEaters_Protin(){

        //////////////////////// Protins////////////////////

        if(!VegetarianCarbsOptionsMealForMealDesign.isEmpty()){

                UpdateUsedMacros();
                ProtinForMeal= VegetarianCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                RandomFoodForMealDesign =((int) (Math.random() * VegetarianCarbsOptionsMealForMealDesign.size()));
                ProtinForMeal.ChangeAmount(1);
                NumberOfTriesForMealDesign =0;


                int TryToGetGoodProtinSource=0;


                while(!AtLeastFiveGramsProtin(ProtinForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalProtinsForMeal-UsedProtinsForMeal) && (ProtinForMeal.GetPer100grmProtins()<10-TryToGetGoodProtinSource/(VegetarianCarbsOptionsMealForMealDesign.size()* VegetarianCarbsOptionsMealForMealDesign.size()* VegetarianCarbsOptionsMealForMealDesign.size()))&&  NumberOfTriesForMealDesign<VegetarianCarbsOptionsMealForMealDesign.size()* VegetarianCarbsOptionsMealForMealDesign.size()) {
                    ProtinForMeal= VegetarianCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                    RandomFoodForMealDesign =((int) (Math.random() * VegetarianCarbsOptionsMealForMealDesign.size()));
                    ProtinForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign++;
                    TryToGetGoodProtinSource++;

                }
            if(NumberOfTriesForMealDesign<VegetarianCarbsOptionsMealForMealDesign.size()* VegetarianCarbsOptionsMealForMealDesign.size()) {

                GetFoodRightAMountPerCaloriesAndDesiredProtin(ProtinForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalProtinsForMeal-UsedProtinsForMeal);

            }
            else {
                ProtinForMeal=null;
            }



        }







    }


    private void BuildMealFoVegetarianEaters_Carbs(){
        //////////////////Carbs/////////////////////////
        if(!VegetarianCarbsOptionsMealForMealDesign.isEmpty()){

                UpdateUsedMacros();
                RandomFoodForMealDesign =((int) (Math.random() * VegetarianCarbsOptionsMealForMealDesign.size()));
                CarbsForMeal= VegetarianCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                CarbsForMeal.ChangeAmount(1);
                NumberOfTriesForMealDesign=0;


                while(!AtLeastFiveGramsCarbs(CarbsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)&&  NumberOfTriesForMealDesign< VegetarianCarbsOptionsMealForMealDesign.size()* VegetarianCarbsOptionsMealForMealDesign.size()) {
                    RandomFoodForMealDesign =((int) (Math.random() * VegetarianCarbsOptionsMealForMealDesign.size()));
                    CarbsForMeal= VegetarianCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                    CarbsForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign++;

                }

            if(NumberOfTriesForMealDesign< VegetarianCarbsOptionsMealForMealDesign.size()* VegetarianCarbsOptionsMealForMealDesign.size()) {

                GetFoodRightAMountPerCaloriesAndDesiredCarbs(CarbsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                CarbsOptionsMealForDesignMeal.add(CarbsForMeal);

            }
            else {
                CarbsForMeal=null;
            }



        }

    }

    private void BuildMealForVeganEaters_Protin(){

        //////////////////////// Protins////////////////////

        if(! VeganCarbsOptionsMealForMealDesign.isEmpty()){
                UpdateUsedMacros();
                ProtinForMeal=  VeganCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                RandomFoodForMealDesign =((int) (Math.random() *  VeganCarbsOptionsMealForMealDesign.size()));
                ProtinForMeal.ChangeAmount(1);
                NumberOfTriesForMealDesign =0;


                int TryToGetGoodProtinSource=0;


                while(!AtLeastFiveGramsProtin(ProtinForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalProtinsForMeal-UsedProtinsForMeal) && (ProtinForMeal.GetPer100grmProtins()<10-TryToGetGoodProtinSource/( VeganCarbsOptionsMealForMealDesign.size()*  VeganCarbsOptionsMealForMealDesign.size()*  VeganCarbsOptionsMealForMealDesign.size()))&&  NumberOfTriesForMealDesign< VeganCarbsOptionsMealForMealDesign.size()*  VeganCarbsOptionsMealForMealDesign.size()) {
                    ProtinForMeal=  VeganCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                    RandomFoodForMealDesign =((int) (Math.random() *  VeganCarbsOptionsMealForMealDesign.size()));
                    ProtinForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign++;
                    TryToGetGoodProtinSource++;

                }
            if(NumberOfTriesForMealDesign<VeganCarbsOptionsMealForMealDesign.size()*  VeganCarbsOptionsMealForMealDesign.size()) {

                GetFoodRightAMountPerCaloriesAndDesiredProtin(ProtinForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalProtinsForMeal-UsedProtinsForMeal);

            }
            else {
                ProtinForMeal=null;
            }



        }

    }


    private void BuildMealForVeganEaters_Carbs(){
        //////////////////Carbs/////////////////////////
        if(! VeganCarbsOptionsMealForMealDesign.isEmpty()){

                UpdateUsedMacros();
                RandomFoodForMealDesign =((int) (Math.random() *  VeganCarbsOptionsMealForMealDesign.size()));
                CarbsForMeal=  VeganCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                CarbsForMeal.ChangeAmount(1);
                NumberOfTriesForMealDesign=0;

                while(!AtLeastFiveGramsCarbs(CarbsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)&&  NumberOfTriesForMealDesign<VeganCarbsOptionsMealForMealDesign.size()*  VeganCarbsOptionsMealForMealDesign.size()) {
                    RandomFoodForMealDesign =((int) (Math.random() *  VeganCarbsOptionsMealForMealDesign.size()));
                    CarbsForMeal=  VeganCarbsOptionsMealForMealDesign.get(RandomFoodForMealDesign);
                    CarbsForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign++;


                }
            if(NumberOfTriesForMealDesign<VeganCarbsOptionsMealForMealDesign.size()*  VeganCarbsOptionsMealForMealDesign.size()) {

                GetFoodRightAMountPerCaloriesAndDesiredCarbs(CarbsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                CarbsOptionsMealForDesignMeal.add(CarbsForMeal);

            }
            else {
                CarbsForMeal=null;
            }





        }






    }

    private void BuildMeal_Fats(){

        //////////////Fats///////////////////
        if(!FatsSourceForMenu.isEmpty()){
            UpdateUsedMacros();
            RandomFoodForMealDesign =((int) (Math.random() * FatsSourceForMenu.size()));
            FatsForMeal= FatsSourceForMenu.get(RandomFoodForMealDesign);
            FatsForMeal.ChangeAmount(1);
            NumberOfTriesForMealDesign=0;

            while(!AtLeastFiveGramsFats(FatsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal) &&  NumberOfTriesForMealDesign< FatsSourceForMenu.size()* FatsSourceForMenu.size()) {
                RandomFoodForMealDesign =((int) (Math.random() * FatsSourceForMenu.size()));
                FatsForMeal= FatsSourceForMenu.get(RandomFoodForMealDesign);
                FatsForMeal.ChangeAmount(1);
                NumberOfTriesForMealDesign++;


            }
            if(NumberOfTriesForMealDesign< FatsSourceForMenu.size()* FatsSourceForMenu.size()) {

                GetFoodRightAMountPerCaloriesAndDesiredFats(FatsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);

            }
            else {
                FatsForMeal=null;
            }






        }

    }





    private void BuildMealFoVeganEaters_SideDish(){
        //////////////////////// Side Dish////////////////////

        //////Enough Carbs For Side Dish Only////////////

        if(TotalCarbsForMeal <=130/4 && TotalCarbsForMeal > UsedCarbsForMeal && TotalCaloriesForMeal > UsedCaloriesForMeal){
            RandomFoodForMealDesign =((int) (Math.random() * SideDishOptionsMealForDesignMeal.size()));
            String StringSideDishOptionsMealForDesignMeal=SideDishOptionsMealForDesignMeal.get(RandomFoodForMealDesign);
            switch (StringSideDishOptionsMealForDesignMeal){
                case "Fruits":




                    RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                    FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                    FruitsForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                        RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                        FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                        FruitsForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealFoVeganEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                    CarbsOptionsMealForDesignMeal.add(FruitsForMeal);
                    GotChosen=FruitsForMeal;


                    break;

                case "Vegetables":


                    RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                    VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                    VegetablesForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal)) {
                        RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                        VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                        VegetablesForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealFoVeganEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal,TotalCarbsForMeal-UsedCarbsForMeal);
                    CarbsOptionsMealForDesignMeal.add(VegetablesForMeal);
                    GotChosen=VegetablesForMeal;



                    break;
                case "Milk":
                    BuildMealFoVeganEaters_SideDish();
                    break;
            }

        }


        //////Enough Carbs For Extra Side Dish////////////


        if(TotalCarbsForMeal - UsedCarbsForMeal >300/4 && TotalCarbsForMeal > UsedCarbsForMeal && TotalCaloriesForMeal > UsedCaloriesForMeal+300){

            RandomFoodForMealDesign =((int) (Math.random() * SideDishOptionsMealForDesignMeal.size()));
            String StringSideDishOptionsMealForDesignMeal=SideDishOptionsMealForDesignMeal.get(RandomFoodForMealDesign);
            switch (StringSideDishOptionsMealForDesignMeal){
                case "Fruits":

                    RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                    FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                    FruitsForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;

                    while(!AtLeastFiveGramsCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                        RandomFoodForMealDesign =((int) (Math.random() * FruitsSourceForMenu.size()));
                        FruitsForMeal= FruitsSourceForMenu.get(RandomFoodForMealDesign);
                        FruitsForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealFoVeganEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(FruitsForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                    CarbsOptionsMealForDesignMeal.add(FruitsForMeal);
                    GotChosen=FruitsForMeal;


                    break;



                case "Vegetables":


                    RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                    VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                    VegetablesForMeal.ChangeAmount(1);
                    NumberOfTriesForMealDesign =0;
                    BreakFromSwitch=false;
                    while(!AtLeastFiveGramsCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4)) {
                        RandomFoodForMealDesign =((int) (Math.random() * VegetablesSourceForMenu.size()));
                        VegetablesForMeal= VegetablesSourceForMenu.get(RandomFoodForMealDesign);
                        VegetablesForMeal.ChangeAmount(1);
                        if (NumberOfTriesForMealDesign>1000){
                            BuildMealFoVeganEaters_SideDish();
                            BreakFromSwitch=true;
                            break;
                        }
                        NumberOfTriesForMealDesign++;

                    }

                    if (BreakFromSwitch){
                        break;
                    }

                    GetFoodRightAMountPerCaloriesAndDesiredCarbs(VegetablesForMeal,TotalCaloriesForMeal-UsedCaloriesForMeal-300,TotalCarbsForMeal-UsedCarbsForMeal-300/4);
                    CarbsOptionsMealForDesignMeal.add(VegetablesForMeal);
                    GotChosen=VegetablesForMeal;



                    break;
                case "Milk":
                    BuildMealFoVeganEaters_SideDish();
                    break;
            }

        }




    }







    private void PrepareCaloriesPerMealArrays(){
        CalorisPerMeal.clear();
        ProtinPerMeal.clear();
        CarbsPerMeal.clear();
        FatsPerMeal.clear();

        switch(this.NumberOfMeals){
            case 1:
                CalorisPerMeal.add(TotalCalories);
                ProtinPerMeal.add(TotalProtins);
                CarbsPerMeal.add(TotalCarbs);
                FatsPerMeal.add(TotalFats);
                break;
            case 2:
                CalorisPerMeal.add(TotalCalories*0.5);
                ProtinPerMeal.add(TotalProtins*0.5);
                CarbsPerMeal.add(TotalCarbs*0.5);
                FatsPerMeal.add(TotalFats*0.5);
                CalorisPerMeal.add(TotalCalories*0.5);
                ProtinPerMeal.add(TotalProtins*0.5);
                CarbsPerMeal.add(TotalCarbs*0.5);
                FatsPerMeal.add(TotalFats*0.5);

                break;
            case 3:
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);
                CalorisPerMeal.add(TotalCalories*0.4);
                ProtinPerMeal.add(TotalProtins*0.4);
                CarbsPerMeal.add(TotalCarbs*0.4);
                FatsPerMeal.add(TotalFats*0.4);
                CalorisPerMeal.add(TotalCalories*0.4);
                ProtinPerMeal.add(TotalProtins*0.4);
                CarbsPerMeal.add(TotalCarbs*0.4);
                FatsPerMeal.add(TotalFats*0.4);

                break;
            case 4:
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);
                CalorisPerMeal.add(TotalCalories*0.3);
                ProtinPerMeal.add(TotalProtins*0.3);
                CarbsPerMeal.add(TotalCarbs*0.3);
                FatsPerMeal.add(TotalFats*0.3);
                CalorisPerMeal.add(TotalCalories*0.3);
                ProtinPerMeal.add(TotalProtins*0.3);
                CarbsPerMeal.add(TotalCarbs*0.3);
                FatsPerMeal.add(TotalFats*0.3);
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);

                break;
            case 5:
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);
                CalorisPerMeal.add(TotalCalories*0.2);
                ProtinPerMeal.add(TotalProtins*0.2);
                CarbsPerMeal.add(TotalCarbs*0.2);
                FatsPerMeal.add(TotalFats*0.2);
                break;


        }



    }

private void GetFoodRightAMountPerCaloriesAndDesiredProtin(Foods TestedFood, Double TargetCaloriesTest, Double TargetMacroTest){
        if(TestedFood.GetAmount()<5){
            TestedFood.ChangeAmount(5);
        }
        FutureFoodTest=null;
        FiveFutureFoodTest=null;

        do{
            FutureFoodTest=new Foods( TestedFood.GetAmount()+1, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());
            FiveFutureFoodTest=new Foods( TestedFood.GetAmount()+5, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());

            if(FiveFutureFoodTest.GetNetCalories()< TargetCaloriesTest && FiveFutureFoodTest.GetNetProtins()< TargetMacroTest){


                TestedFood.ChangeAmount(Math.max(TestedFood.GetAmount()+1,Math.min((int) (TestedFood.GetAmount()*((TargetMacroTest)/TestedFood.GetNetProtins())),(int) (TestedFood.GetAmount()*((TargetCaloriesTest)/TestedFood.GetNetCalories()))) ));
            }
            else if(FutureFoodTest.GetNetCalories()< TargetCaloriesTest && FutureFoodTest.GetNetProtins()< TargetMacroTest){

                TestedFood.ChangeAmount(TestedFood.GetAmount()+1);

            }
            else if(TestedFood.GetNetCalories()> TargetCaloriesTest || TestedFood.GetNetProtins()> TargetMacroTest){

                TestedFood.ChangeAmount(TestedFood.GetAmount()-1);

            }
            else if(TestedFood.GetNetCalories()<= TargetCaloriesTest && TestedFood.GetNetProtins()<= TargetMacroTest && (FutureFoodTest.GetNetCalories()> TargetCaloriesTest || FutureFoodTest.GetNetProtins()> TargetMacroTest)){

                break;
            }



        }while (true);



}



    private void GetFoodRightAMountPerCaloriesAndDesiredCarbs(Foods TestedFood, Double TargetCaloriesTest, Double TargetMacroTest){
        if(TestedFood.GetAmount()<5){
            TestedFood.ChangeAmount(5);
        }
        FutureFoodTest=null;
        FiveFutureFoodTest=null;
        do{
            FutureFoodTest=new Foods( TestedFood.GetAmount()+1, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());
            FiveFutureFoodTest=new Foods( TestedFood.GetAmount()+5, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());

            if(FiveFutureFoodTest.GetNetCalories()< TargetCaloriesTest && FiveFutureFoodTest.GetNetCarbs()< TargetMacroTest){
                TestedFood.ChangeAmount(Math.max(TestedFood.GetAmount()+1,Math.min((int) (TestedFood.GetAmount()*((TargetMacroTest)/TestedFood.GetNetCarbs())),(int) (TestedFood.GetAmount()*((TargetCaloriesTest)/TestedFood.GetNetCalories()))) ));
            }
            else if(FutureFoodTest.GetNetCalories()< TargetCaloriesTest && FutureFoodTest.GetNetCarbs()< TargetMacroTest){
                TestedFood.ChangeAmount(TestedFood.GetAmount()+1);

            }
            else if(TestedFood.GetNetCalories()> TargetCaloriesTest || TestedFood.GetNetCarbs()> TargetMacroTest){
                TestedFood.ChangeAmount(TestedFood.GetAmount()-1);

            }
            else if(TestedFood.GetNetCalories()<= TargetCaloriesTest && TestedFood.GetNetCarbs()<= TargetMacroTest && (FutureFoodTest.GetNetCalories()> TargetCaloriesTest || FutureFoodTest.GetNetCarbs()> TargetMacroTest)){
                break;
            }
        }while (true);



    }




    private void GetFoodRightAMountPerCaloriesAndDesiredFats(Foods TestedFood, Double TargetCaloriesTest, Double TargetMacroTest){
        if(TestedFood.GetAmount()<5){
            TestedFood.ChangeAmount(5);
        }
        FutureFoodTest=null;
        FiveFutureFoodTest=null;
        do{
            FutureFoodTest=new Foods( TestedFood.GetAmount()+1, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());
            FiveFutureFoodTest=new Foods( TestedFood.GetAmount()+5, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());

            if(FiveFutureFoodTest.GetNetCalories()< TargetCaloriesTest && FiveFutureFoodTest.GetNetFats()< TargetMacroTest){
                TestedFood.ChangeAmount(Math.max(TestedFood.GetAmount()+1,Math.min((int) (TestedFood.GetAmount()*((TargetMacroTest)/TestedFood.GetNetFats())),(int) (TestedFood.GetAmount()*((TargetCaloriesTest)/TestedFood.GetNetCalories()))) ));
            }
            else if(FutureFoodTest.GetNetCalories()< TargetCaloriesTest && FutureFoodTest.GetNetFats()< TargetMacroTest){
                TestedFood.ChangeAmount(TestedFood.GetAmount()+1);

            }
            else if(TestedFood.GetNetCalories()> TargetCaloriesTest || TestedFood.GetNetFats()> TargetMacroTest){
                TestedFood.ChangeAmount(TestedFood.GetAmount()-1);

            }
            else if(TestedFood.GetNetCalories()<= TargetCaloriesTest && TestedFood.GetNetFats()<= TargetMacroTest && (FutureFoodTest.GetNetCalories()> TargetCaloriesTest || FutureFoodTest.GetNetFats()> TargetMacroTest)){
                break;
            }
        }while (true);



    }


    private void GetFoodRightAMountPerCaloriesAndDesiredGeneral(Foods TestedFood, Double TargetCaloriesTest){

        if(TestedFood.GetAmount()<5){
            TestedFood.ChangeAmount(5);
        }
        FutureFoodTest=null;
        FiveFutureFoodTest=null;

        do{
            FutureFoodTest=new Foods( TestedFood.GetAmount()+1, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());
            FiveFutureFoodTest=new Foods( TestedFood.GetAmount()+5, TestedFood.GetPer100grmCalories(),TestedFood.GetPer100grmProtins(),TestedFood.GetPer100grmFats(),TestedFood.GetPer100grmCarbs(),TestedFood.GetName(),TestedFood.GetType());

            if(FiveFutureFoodTest.GetNetCalories()< TargetCaloriesTest ){


                TestedFood.ChangeAmount(Math.max(TestedFood.GetAmount()+1,(int) (TestedFood.GetAmount()*((TargetCaloriesTest)/TestedFood.GetNetCalories()))) );
            }
            else if(FutureFoodTest.GetNetCalories()< TargetCaloriesTest ){

                TestedFood.ChangeAmount(TestedFood.GetAmount()+1);

            }
            else if(TestedFood.GetNetCalories()> TargetCaloriesTest ){

                TestedFood.ChangeAmount(TestedFood.GetAmount()-1);

            }
            else if(TestedFood.GetNetCalories()<= TargetCaloriesTest && (FutureFoodTest.GetNetCalories()> TargetCaloriesTest )){

                break;
            }



        }while (true);





    }


private boolean AtLeastFiveGramsProtin(Foods TestedFood, Double TargetCaloriesTest, Double TargetMacroTest){
                TestedFood.ChangeAmount(5);
                if(TestedFood.GetNetCalories()>TargetCaloriesTest || TestedFood.GetNetProtins()>TargetMacroTest){
                    return false;
                }
                return true;


}

    private boolean AtLeastFiveGramsCarbs(Foods TestedFood, Double TargetCaloriesTest, Double TargetMacroTest){
        TestedFood.ChangeAmount(5);
        if(TestedFood.GetNetCalories()>TargetCaloriesTest || TestedFood.GetNetCarbs()>TargetMacroTest){
            return false;
        }
        return true;


    }

    private boolean AtLeastFiveGramsFats(Foods TestedFood, Double TargetCaloriesTest, Double TargetMacroTest){
        TestedFood.ChangeAmount(5);
        if(TestedFood.GetNetCalories()>TargetCaloriesTest || TestedFood.GetNetFats()>TargetMacroTest){
            return false;
        }
        return true;


    }
    private boolean AtLeastFiveGramsGeneral(Foods TestedFood, Double TargetCaloriesTest){
        TestedFood.ChangeAmount(5);
        if(TestedFood.GetNetCalories()>TargetCaloriesTest ){
            return false;
        }



        return true;


    }


    private void UpdateUsedMacros(){
        UsedCaloriesForMeal =0.0;
        UsedProtinsForMeal =0.0;
        UsedCarbsForMeal =0.0;
        UsedFatsForMeal =0.0;



        if(ProtinForMeal!=null){


            UsedCaloriesForMeal +=ProtinForMeal.GetNetCalories();
            UsedProtinsForMeal +=ProtinForMeal.GetNetProtins();
            UsedCarbsForMeal +=ProtinForMeal.GetNetCarbs();
            UsedFatsForMeal +=ProtinForMeal.GetNetFats();
        }

        if(FatsForMeal!=null){
            UsedCaloriesForMeal +=FatsForMeal.GetNetCalories();
            UsedProtinsForMeal +=FatsForMeal.GetNetProtins();
            UsedCarbsForMeal +=FatsForMeal.GetNetCarbs();
            UsedFatsForMeal +=FatsForMeal.GetNetFats();
        }


        for (Foods X: CarbsOptionsMealForDesignMeal){
            UsedCaloriesForMeal +=X.GetNetCalories();
            UsedProtinsForMeal +=X.GetNetProtins();
            UsedCarbsForMeal +=X.GetNetCarbs();
            UsedFatsForMeal +=X.GetNetFats();
        }

    }



    public Foods GetEquivalentFood(Foods Y,Double TargetedCalories,Double TargetedProtin,Double TargetedCarbs,Double TargetedFats){
        Foods NewFood=null;
        ArrayList<Foods> TempArray=new  ArrayList<Foods>() ;
        Double NetDominantMacro=0.0;
        switch (Y.GetType()){
            case "Meat":
            case "Egg":
            case "MeatSub":
            case "Fish":
               TempArray= ProtinSourceForMenu;
                NetDominantMacro=TargetedProtin;
                break;


            case "Carbs":
                TempArray= CarbsSourceForMenu;
                NetDominantMacro=TargetedCarbs;
                break;
            case "Fats":
                TempArray= FatsSourceForMenu;
                NetDominantMacro=TargetedFats;
                break;
            case "Fruit":
                TempArray=FruitsSourceForMenu;
                NetDominantMacro=TargetedCarbs;
                break;
            case "Vegetable":
                TempArray=VegetablesSourceForMenu;
                NetDominantMacro=TargetedCarbs;
                break;
            case "Milk":
                TempArray=MilkSourceForMenu;
                NetDominantMacro=TargetedCarbs;
                break;
        }


        if(!TempArray.isEmpty()){

            RandomFoodForMealDesign =((int) (Math.random() * TempArray.size()));
            NewFood= TempArray.get(RandomFoodForMealDesign);
            NewFood.ChangeAmount(1);
            NumberOfTriesForMealDesign=0;

            while(!AtLeastFiveGramsGeneral(NewFood,TargetedCalories) &&  NumberOfTriesForMealDesign< TempArray.size()* TempArray.size()) {
                RandomFoodForMealDesign =((int) (Math.random() * TempArray.size()));
                NewFood= TempArray.get(RandomFoodForMealDesign);
                NewFood.ChangeAmount(1);
                NumberOfTriesForMealDesign++;

            }

            if(NumberOfTriesForMealDesign< TempArray.size()* TempArray.size()) {

                GetFoodRightAMountPerCaloriesAndDesiredGeneral(NewFood,TargetedCalories);

            }
            else {
                NewFood=null;
            }


        }



        if(NewFood==null){
            return Y;
        }
        return NewFood;


    }



    public Foods GetDifferentTypeOfFood(Foods Y,Double TargetedCalories,Double TargetedProtin,Double TargetedCarbs,Double TargetedFats){
        Foods NewFood=null;
        ArrayList<Foods> TempArray=new  ArrayList<Foods>() ;
        ArrayList< ArrayList<Foods>> TempArrayOfArrays=new  ArrayList< ArrayList<Foods>>() ;
        TempArrayOfArrays.add(ProtinSourceForMenu);
        TempArrayOfArrays.add(CarbsSourceForMenu);
        TempArrayOfArrays.add(FatsSourceForMenu);
        TempArrayOfArrays.add(FruitsSourceForMenu);
        TempArrayOfArrays.add(VegetablesSourceForMenu);
        TempArrayOfArrays.add(MilkSourceForMenu);
        Double NetDominantMacro=0.0;
        switch (Y.GetType()){
            case "Meat":
            case "Egg":
            case "MeatSub":
            case "Fish":
                TempArrayOfArrays.add(CarbsSourceForMenu);
                TempArrayOfArrays.add(FatsSourceForMenu);
                TempArrayOfArrays.add(FruitsSourceForMenu);
                TempArrayOfArrays.add(VegetablesSourceForMenu);
                TempArrayOfArrays.add(MilkSourceForMenu);
                RandomFoodForMealDesign =((int) (Math.random() *  TempArrayOfArrays.size()));
                TempArray= TempArrayOfArrays.get(RandomFoodForMealDesign);

                break;


            case "Carbs":
                TempArrayOfArrays.add(ProtinSourceForMenu);
                TempArrayOfArrays.add(FatsSourceForMenu);
                TempArrayOfArrays.add(FruitsSourceForMenu);
                TempArrayOfArrays.add(VegetablesSourceForMenu);
                TempArrayOfArrays.add(MilkSourceForMenu);
                RandomFoodForMealDesign =((int) (Math.random() *  TempArrayOfArrays.size()));
                TempArray= TempArrayOfArrays.get(RandomFoodForMealDesign);

                break;
            case "Fats":
                TempArrayOfArrays.add(ProtinSourceForMenu);
                TempArrayOfArrays.add(CarbsSourceForMenu);
                TempArrayOfArrays.add(FruitsSourceForMenu);
                TempArrayOfArrays.add(VegetablesSourceForMenu);
                TempArrayOfArrays.add(MilkSourceForMenu);
                RandomFoodForMealDesign =((int) (Math.random() *  TempArrayOfArrays.size()));
                TempArray= TempArrayOfArrays.get(RandomFoodForMealDesign);

                break;
            case "Fruit":
                TempArrayOfArrays.add(ProtinSourceForMenu);
                TempArrayOfArrays.add(CarbsSourceForMenu);
                TempArrayOfArrays.add(FatsSourceForMenu);
                TempArrayOfArrays.add(VegetablesSourceForMenu);
                TempArrayOfArrays.add(MilkSourceForMenu);
                RandomFoodForMealDesign =((int) (Math.random() *  TempArrayOfArrays.size()));
                TempArray= TempArrayOfArrays.get(RandomFoodForMealDesign);

                break;
            case "Vegetable":
                TempArrayOfArrays.add(ProtinSourceForMenu);
                TempArrayOfArrays.add(CarbsSourceForMenu);
                TempArrayOfArrays.add(FatsSourceForMenu);
                TempArrayOfArrays.add(FruitsSourceForMenu);
                TempArrayOfArrays.add(MilkSourceForMenu);
                RandomFoodForMealDesign =((int) (Math.random() *  TempArrayOfArrays.size()));
                TempArray= TempArrayOfArrays.get(RandomFoodForMealDesign);

                break;
            case "Milk":
                TempArrayOfArrays.add(ProtinSourceForMenu);
                TempArrayOfArrays.add(CarbsSourceForMenu);
                TempArrayOfArrays.add(FatsSourceForMenu);
                TempArrayOfArrays.add(FruitsSourceForMenu);
                TempArrayOfArrays.add(VegetablesSourceForMenu);
                RandomFoodForMealDesign =((int) (Math.random() *  TempArrayOfArrays.size()));
                TempArray= TempArrayOfArrays.get(RandomFoodForMealDesign);

                break;
        }


        if(!TempArray.isEmpty()){

            RandomFoodForMealDesign =((int) (Math.random() * TempArray.size()));
            NewFood= TempArray.get(RandomFoodForMealDesign);
            NewFood.ChangeAmount(1);
            NumberOfTriesForMealDesign=0;

            while(!AtLeastFiveGramsGeneral(NewFood,TargetedCalories) &&  NumberOfTriesForMealDesign< TempArray.size()* TempArray.size()) {
                RandomFoodForMealDesign =((int) (Math.random() * TempArray.size()));
                NewFood= TempArray.get(RandomFoodForMealDesign);
                NewFood.ChangeAmount(1);
                NumberOfTriesForMealDesign++;

            }

            if(NumberOfTriesForMealDesign< TempArray.size()* TempArray.size()) {

                GetFoodRightAMountPerCaloriesAndDesiredGeneral(NewFood,TargetedCalories);

            }
            else {
                NewFood=null;
            }


        }



        if(NewFood==null){
            return Y;
        }
        return NewFood;


    }






}
