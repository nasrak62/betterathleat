package com.example.betterathleat;

import java.util.ArrayList;

public class Foods {
    private int Amount;
    private Double CaloriesPer100gram;
    private Double NetCalories;
    private Double ProtinsPer100gram;
    private Double NetProtins;
    private Double CarbsPer100gram;
    private Double NetCarbs;
    private Double FatsPer100gram;
    private Double NetFats;
    private static ArrayList<Foods> FoodsArray = new ArrayList<Foods>();
    private String FoodType;
    private String FoodName;


    public  Foods(int Amount,Double CaloriesPer100gram,Double ProtinsPer100gram,Double FatsPer100gram,Double CarbsPer100gram,String FoodName,String FoodType){
        SetCaloriesPerAmount( Amount,CaloriesPer100gram,ProtinsPer100gram,FatsPer100gram,CarbsPer100gram);
        SetName(FoodName);
        SetType(FoodType);
        if(!FoodInArray()){
            FoodsArray.add(this);
        }


    }

   private void SetAmount(int Amount){
        this.Amount=Amount;
    }
    private void SetCalories(Double CaloriesPer100gram){
        this.CaloriesPer100gram=CaloriesPer100gram;
        this.NetCalories=(CaloriesPer100gram*Amount)/100;
    }
    private void SetProtins(Double ProtinsPer100gram){
        this.ProtinsPer100gram=ProtinsPer100gram;
        this.NetProtins=(ProtinsPer100gram*Amount)/100;
    }
    private void SetFats(Double FatsPer100gram){
        this.FatsPer100gram=FatsPer100gram;
        this.NetFats=(FatsPer100gram*Amount)/100;
    }
    private void SetCarbs(Double CarbsPer100gram){
        this.CarbsPer100gram=CarbsPer100gram;
        this.NetCarbs=(CarbsPer100gram*Amount)/100;
    }
    private void SetName(String FoodName) {
        this.FoodName = FoodName;
    }
    private void SetType(String FoodType){
        this.FoodType = FoodType;

    }
    private void SetCaloriesPerAmount(int Amount,Double CaloriesPer100gram,Double ProtinsPer100gram,Double FatsPer100gram,Double CarbsPer100gram){
        SetAmount(Amount);
        SetCalories(CaloriesPer100gram);
        SetProtins(ProtinsPer100gram);
        SetFats(FatsPer100gram);
        SetCarbs(CarbsPer100gram);

    }


    public int GetAmount(){
        return this.Amount;
    }
    public Double GetNetCalories(){
        return this.NetCalories;
    }
    public Double GetNetProtins(){
        return this.NetProtins;
    }
    public Double GetNetFats(){
        return this.NetFats;
    }
    public Double GetNetCarbs(){
        return this.NetCarbs;
    }


    public Double GetPer100grmCalories(){
        return this.CaloriesPer100gram;
    }
    public Double GetPer100grmProtins(){
        return this.ProtinsPer100gram;
    }
    public Double GetPer100grmFats(){
        return this.FatsPer100gram;
    }
    public Double GetPer100grmCarbs(){
        return this.CarbsPer100gram;
    }


    public String GetName(){
        return this.FoodName;
    }
    public String GetType(){
        return this.FoodType;
    }

    public String GetFoodsInArray(){
        String Temp="";
        for(Foods x: FoodsArray) {
            Temp+= x.GetName();
        }
        return(Temp);
    }

    public ArrayList<Foods> GetFoodsArray(){
        return FoodsArray;

    }
    public Boolean FoodInArray(){
        for(Foods x : FoodsArray){
            if(x.GetName().equals(this.GetName())){
                return  (true);
            }
        }
        return  (false);
    }

    public void RemoveFoodFromFoodArray(Foods Y){
        if(FoodsArray.contains(Y)){
            FoodsArray.remove(FoodsArray.indexOf(Y));

        }


    }

   public Boolean ExternalFoodInArray(String FoodName){
        for(Foods x : FoodsArray){
            if(x.GetName().equals(FoodName)){
                return  (true);
            }
        }
        return  (false);
    }



   public void ChangeAmount(int NewAmount){
        SetCaloriesPerAmount(NewAmount,this.CaloriesPer100gram,this.ProtinsPer100gram,this.FatsPer100gram,this.CarbsPer100gram);

    }
    public ArrayList<Foods> GetMeatAndFishInFoodsArray(){
        ArrayList<Foods> MeatAndFishArray = new ArrayList<Foods>();
        for(Foods x : FoodsArray) {
            if ((x.GetType().equals("Meat"))||(x.GetType().equals("Fish"))) {
                MeatAndFishArray.add(x);
            }
        }

        return MeatAndFishArray;
    }

    public ArrayList<Foods> GetCarbsInFoodsArray(){
        ArrayList<Foods> CarbsArray = new ArrayList<Foods>();
        for(Foods x : FoodsArray) {
            if (x.GetType().equals("Carbs")) {
                CarbsArray.add(x);
            }
        }

        return CarbsArray;
    }

    public ArrayList<Foods> GetFatsInFoodsArray(){
        ArrayList<Foods> FatsArray = new ArrayList<Foods>();
        for(Foods x : FoodsArray) {
            if (x.GetType().equals("Fats")) {
                FatsArray.add(x);
            }
        }

        return FatsArray;
    }

    public ArrayList<Foods> GetEggsInFoodsArray(){
        ArrayList<Foods> EggsArray = new ArrayList<Foods>();
        for(Foods x : FoodsArray) {
            if (x.GetType().equals("Egg")) {
                EggsArray.add(x);
            }
        }

        return EggsArray;
    }

    public ArrayList<Foods> GetMilkInFoodsArray(){
        ArrayList<Foods> MilkArray = new ArrayList<Foods>();
        for(Foods x : FoodsArray) {
            if (x.GetType().equals("Milk")) {
                MilkArray.add(x);
            }
        }

        return MilkArray;
    }

    public ArrayList<Foods> GetVegetablesInFoodsArray(){
        ArrayList<Foods> VegetablesArray = new ArrayList<Foods>();
        for(Foods x : FoodsArray) {
            if (x.GetType().equals("Vegetable")) {
                VegetablesArray.add(x);
            }
        }

        return VegetablesArray;
    }

    public ArrayList<Foods> GetFruitsInFoodsArray(){
        ArrayList<Foods> FruitsArray = new ArrayList<Foods>();
        for(Foods x : FoodsArray) {
            if (x.GetType().equals("Fruit")) {
                FruitsArray.add(x);
            }
        }

        return FruitsArray;
    }


}
