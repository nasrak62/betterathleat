package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DeleteFood extends AppCompatActivity {
    private Button AddNewFoodButton;
    private Button  DeleteFoodButton;
    private Button  Main_Menu;
    private AutoCompleteTextView AutoCompleteAdditionalFood;
    private ArrayList<String> AutoCompleteArrayAdditionalFood;
    private User user;
    private ArrayList<Foods> AdditionalFoodsArray;
    private Gson gson=new Gson();
    private ArrayList<ArrayList<Foods>> Meals;
    private CreateMenu TheMenu;
    private Double ActualCaloriesForMeal;
    private Double ActualProtinsForMeal;
    private Double ActualCarbsForMeal;
    private Double ActualFatsForMeal;
    private ArrayAdapter<String> AutoCompleteArrayAdditionalFoodAdapter;
    private ArrayList<Foods> LikedFoodsArray=new ArrayList<Foods>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_food);
        Main_Menu= (Button) findViewById(R.id.Main_Menu);
        DeleteFoodButton= (Button) findViewById(R.id.DeleteFoodButton);
        AddNewFoodButton= (Button) findViewById(R.id.AddNewFoodButton);
        DeleteFoodButton.setEnabled(false);
        AutoCompleteAdditionalFood=(AutoCompleteTextView) findViewById(R.id.DeleteFoodAutoComplete);


        user=get_shared_info_Gson_User(MainActivity.PASSUSER);

        if(!get_shared_info_Gson_Foods_Array(MainActivity.ADDITIONALFOODS).isEmpty() ){

            AdditionalFoodsArray=get_shared_info_Gson_Foods_Array(MainActivity.ADDITIONALFOODS);
        }
        else{
            AdditionalFoodsArray=new ArrayList<Foods>();
            Toast toast = Toast.makeText(getApplicationContext(),"You Can't Delete Foods Without Adding Theme First" , Toast.LENGTH_SHORT);
            toast.show();
        }



        AutoCompleteArrayAdditionalFood = new ArrayList<String>();

        if(!AdditionalFoodsArray.isEmpty()){

            for(Foods x: AdditionalFoodsArray) {
                if(!AutoCompleteArrayAdditionalFood.contains(x.GetName())){
                    AutoCompleteArrayAdditionalFood.add(x.GetName());
                }
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"You Can't Delete Foods Without Adding Theme First" , Toast.LENGTH_SHORT);
            toast.show();
            DeleteFoodButton.setEnabled(false);
        }






        AutoCompleteArrayAdditionalFoodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, AutoCompleteArrayAdditionalFood);
        AutoCompleteAdditionalFood.setAdapter(AutoCompleteArrayAdditionalFoodAdapter);
        AutoCompleteAdditionalFood.setThreshold(0);
        AutoCompleteAdditionalFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AutoCompleteAdditionalFood.showDropDown();
                if(AutoCompleteAdditionalFood.getText().toString().equals(null) || AutoCompleteAdditionalFood.getText().toString().equals("") || AdditionalFoodsArray.isEmpty()){
                    DeleteFoodButton.setEnabled(false);
                }
                else {
                    DeleteFoodButton.setEnabled(true);
                }
            }
        });










        DeleteFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
                if(!AdditionalFoodsArray.isEmpty()){
                    for(Foods x: AdditionalFoodsArray){
                        if(AutoCompleteAdditionalFood.getText().toString().equals(x.GetName())){
                            AdditionalFoodsArray.remove(AdditionalFoodsArray.indexOf(x));
                            AutoCompleteArrayAdditionalFoodAdapter.remove(x.GetName());
                            PassDataTOmain(x);
                            DeleteFromOtherPlacesInTheProgram(x);
                            ChickenBreast.RemoveFoodFromFoodArray(x);
                            Gson_Update_Data_Foods_Array(AdditionalFoodsArray,MainActivity.ADDITIONALFOODS);
                            Gson_Update_Data_User(user,MainActivity.PASSUSER);
                            Toast toast = Toast.makeText(getApplicationContext(),x.GetName()+ " Was Deleted" , Toast.LENGTH_SHORT);
                            toast.show();

                            AutoCompleteAdditionalFood.setText("");
                            AutoCompleteAdditionalFood.showDropDown();
                            if(AdditionalFoodsArray.isEmpty()){

                                DeleteFoodButton.setEnabled(false);
                                toast = Toast.makeText(getApplicationContext()," No More Foods To Delete" , Toast.LENGTH_SHORT);
                                toast.show();
                            }


                                    break;

                        }

                    }

                }



            }
        });


        AddNewFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewFood();


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

    public void openAddNewFood(){
        Intent intentAddNewFood= new Intent(this,CreateNewFood.class);
        startActivity(intentAddNewFood);
        finish();
    }

    public void Gson_Update_Data_Foods_Array(ArrayList<Foods> Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public ArrayList<Foods> get_shared_info_Gson_Foods_Array( final String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        ArrayList<Foods> FoodsForWorkOut= new ArrayList<Foods>();
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

            return(FoodsForWorkOut);
        }
        Type type = new TypeToken<ArrayList<Foods>>(){}.getType();
        FoodsForWorkOut = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(FoodsForWorkOut);
    }

    public void PassDataTOmain(Foods Y){
        ArrayList<Foods> PersonlizedFoodsArray=new ArrayList<Foods>();
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        for(String x: user.GetLikedFoods()){
            for(Foods z: ChickenBreast.GetFoodsArray()){
                if(z.GetName().equals(x) &&  !z.GetName().equals(Y.GetName())){
                    PersonlizedFoodsArray.add(z);
                }
            }

        }

        String Temp="";
        for(Foods x: PersonlizedFoodsArray) {
            Temp+= x.GetName();
            if(PersonlizedFoodsArray.indexOf(x)!=PersonlizedFoodsArray.size()-1){
                Temp+= ",";
            }
        }
        updateData(Temp,MainActivity.LIKEDFOODS);
        user.SetLikedFoods( convertStringToArray(get_shared_info_string(MainActivity.LIKEDFOODS)));
        Gson_Update_Data_User(user,MainActivity.PASSUSER);
        user=get_shared_info_Gson_User(MainActivity.PASSUSER);

    }
    public void Gson_Update_Data_User(User Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public String get_shared_info_string( final String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        if((sharedPreferences.getString(Key,"0" ).equals("")))
        {
            updateData("0",Key);
        }
        return(sharedPreferences.getString(Key,"0" ));
    }

    private static String[] convertStringToArray(String str){

        if(str.equals("0")){
            String[] arr1={"0", "0", "0", "0", "0", "0"};
            return(arr1) ;
        }
        else {
            String[] arr = str.split(",");
            return arr;
        }


    }
    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

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

    private void DeleteFromOtherPlacesInTheProgram(Foods FoodToDelete){
        if(!get_shared_info_Gson_Array_Of_Arrays_Foods( MainActivity.THEMEALS).isEmpty() && get_shared_info_Gson_Menu(MainActivity.THEMENU)!=null   ){
            Meals=get_shared_info_Gson_Array_Of_Arrays_Foods( MainActivity.THEMEALS);
            TheMenu=get_shared_info_Gson_Menu(MainActivity.THEMENU);
            Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
            for(String X: user.GetLikedFoods()){
                for(Foods Y: ChickenBreast.GetFoodsArray()){
                    if(Y.GetName().equals(X)){
                        LikedFoodsArray.add(Y);
                    }

                }

            }
            TheMenu.SetFoodsForMenu(LikedFoodsArray);


            int index=-1;
            int MealNumber=1;
            for(ArrayList<Foods> x:Meals){
                ActualCaloriesForMeal=0.0;
                ActualProtinsForMeal=0.0;
                ActualCarbsForMeal=0.0;
                ActualFatsForMeal=0.0;
                for(Foods Y :x){

                    ActualCaloriesForMeal+=Y.GetNetCalories();
                    ActualProtinsForMeal+=Y.GetNetProtins();
                    ActualCarbsForMeal+=Y.GetNetCarbs();
                    ActualFatsForMeal+=Y.GetNetFats();


                }

                for(Foods Y :x){

                    if(Y.GetName().equals(FoodToDelete.GetName())){
                        index=x.indexOf(Y);
                        FindAnotherFood(Y,MealNumber,index);
                    }




                }
                MealNumber++;
            }


            Gson_Update_Data_Menu(TheMenu,MainActivity.THEMENU);
            Gson_Update_Data_Array_Of_Arrays_Foods(Meals,MainActivity.THEMEALS);
            Meals=get_shared_info_Gson_Array_Of_Arrays_Foods( MainActivity.THEMEALS);
            TheMenu=get_shared_info_Gson_Menu(MainActivity.THEMENU);
        }
        if(!get_shared_info_Gson_Array_Of_Arrays_Foods(MainActivity.OWNMEALS).isEmpty()){

            ArrayList<ArrayList<Foods>> OwnMeals=get_shared_info_Gson_Array_Of_Arrays_Foods(MainActivity.OWNMEALS);
            for(ArrayList<Foods> x:OwnMeals){
                for(Foods z:x){

                    if(z.GetName().equals(FoodToDelete.GetName())){
                        x.remove(x.indexOf(z));

                    }
                }

            }

            Gson_Update_Data_Array_Of_Arrays_Foods(OwnMeals,MainActivity.OWNMEALS);

        }

    }

    public int StringToInt(String Key){
        return(Integer.parseInt(Key));

    }


    public void Gson_Update_Data_Array_Of_Arrays_Foods(ArrayList<ArrayList<Foods>> Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public ArrayList<ArrayList<Foods>> get_shared_info_Gson_Array_Of_Arrays_Foods( final String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        ArrayList<ArrayList<Foods>> Meals= new ArrayList<ArrayList<Foods>>();
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals(""))) {

            return(Meals);
        }
        Type type = new TypeToken<ArrayList<ArrayList<Foods>>>(){}.getType();
        Meals = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(Meals);
    }

    private void FindAnotherFood(Foods Y, int MealNumber,int index){
        Foods Z;

        int NumOfTries=0;
        do{
            Double  d=TheMenu.GetCaloriesPerMeal().get(MealNumber-1);
            d=TheMenu.GetCaloriesPerMeal().get(MealNumber-1)+Y.GetNetCalories();
            d=TheMenu.GetCaloriesPerMeal().get(MealNumber-1)+Y.GetNetCalories()-ActualCaloriesForMeal;
            Z= TheMenu.GetEquivalentFood(Y,TheMenu.GetCaloriesPerMeal().get(MealNumber-1)+Y.GetNetCalories()-ActualCaloriesForMeal,TheMenu.GetProtinsPerMeal().get(MealNumber-1)+Y.GetNetProtins()-ActualProtinsForMeal,TheMenu.GetCarbsPerMeal().get(MealNumber-1)+Y.GetNetCarbs()-ActualCarbsForMeal,TheMenu.GetFatsPerMeal().get(MealNumber-1)+Y.GetNetFats()-ActualFatsForMeal);
            NumOfTries++;
        }while (Y.GetName().equals(Z.GetName()) && NumOfTries<100 );

        if(Y.GetName().equals(Z.GetName()) && NumOfTries>=100 ){
            Z=TheMenu.GetDifferentTypeOfFood(Y,TheMenu.GetCaloriesPerMeal().get(MealNumber-1)+Y.GetNetCalories()-ActualCaloriesForMeal,TheMenu.GetProtinsPerMeal().get(MealNumber-1)+Y.GetNetProtins()-ActualProtinsForMeal,TheMenu.GetCarbsPerMeal().get(MealNumber-1)+Y.GetNetCarbs()-ActualCarbsForMeal,TheMenu.GetFatsPerMeal().get(MealNumber-1)+Y.GetNetFats()-ActualFatsForMeal);

        }
        if(index==-1){
            for (int i=0; i<=Meals.get(MealNumber-1).size()-1;i++ ){
                if( Meals.get(MealNumber-1).get(i).GetName().equals(Y.GetName()))
                    index=i;
            }
        }

        Meals.get(MealNumber-1).set(index,Z);




    }


    public CreateMenu get_shared_info_Gson_Menu( final String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        CreateMenu TempCreateMenu;
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {
            ArrayList<Foods> TempArrayList=new ArrayList<Foods>();
            Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
            TempArrayList.add(ChickenBreast);
            TempCreateMenu=new CreateMenu(1.0,1.0, 1,TempArrayList ,1 , "Recomposition","1");
            updateData("0", Key);
            return(TempCreateMenu);
        }
        Type type = new TypeToken<CreateMenu>(){}.getType();
        TempCreateMenu = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(TempCreateMenu);
    }

    public void Gson_Update_Data_Menu(CreateMenu Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }



}