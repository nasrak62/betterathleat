package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreateNewFood extends AppCompatActivity {
    private EditText FillNewFoodName;
    private EditText FillCaloriesPer100Grams;
    private EditText FillProtinsPer100Grams;
    private EditText FillCarbsPer100Grams;
    private EditText FillFatsPer100Grams;
    private Button  AddNewFoodButton;
    private Button  DeleteFoodButton;
    private Button  Main_Menu;
    private Spinner FoodTypeSpinner;
    private String Get_FillNewFoodName;
    private String Get_FillCaloriesPer100Grams;
    private String Get_FillProtinsPer100Grams;
    private String Get_FillCarbsPer100Grams;
    private String Get_FillFatsPer100Grams;
    private User user;
    private ArrayList<Foods> AdditionalFoodsArray;
    private Gson gson=new Gson();
    private CreateMenu TheMenu;
    private ArrayList<Foods> LikedFoodsArray=new ArrayList<Foods>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_food);




        Main_Menu= (Button) findViewById(R.id.Main_Menu);
        DeleteFoodButton= (Button) findViewById(R.id.DeleteFoodButton);
        AddNewFoodButton= (Button) findViewById(R.id.AddNewFoodButton);
        FillNewFoodName= (EditText) findViewById(R.id.FillNewFoodName);
        FillProtinsPer100Grams= (EditText) findViewById(R.id.FillProtinsPer100Grams);
        FillCaloriesPer100Grams= (EditText) findViewById(R.id.FillCaloriesPer100Grams);
        FillCarbsPer100Grams= (EditText) findViewById(R.id.FillCarbsPer100Grams);
        FillFatsPer100Grams= (EditText) findViewById(R.id.FillFatsPer100Grams);
        ArrayList<String> SpinnerArrayGeneralFood = new ArrayList<String>();
        SpinnerArrayGeneralFood.add("Meat");
        SpinnerArrayGeneralFood.add("Egg");
        SpinnerArrayGeneralFood.add("MeatSub");
        SpinnerArrayGeneralFood.add("Fish");
        SpinnerArrayGeneralFood.add("Carbs");
        SpinnerArrayGeneralFood.add("Fats");
        SpinnerArrayGeneralFood.add("Fruit");
        SpinnerArrayGeneralFood.add("Vegetable");
        SpinnerArrayGeneralFood.add("Milk");
        ArrayAdapter<String> SpinnerArrayGeneralFoodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArrayGeneralFood);
        FoodTypeSpinner = (Spinner) findViewById(R.id.FoodTypeSpinner);
        FoodTypeSpinner.setAdapter(SpinnerArrayGeneralFoodAdapter);
        AddNewFoodButton.setEnabled(false);
        user=get_shared_info_Gson_User(MainActivity.PASSUSER);




        if(!get_shared_info_Gson_Foods_Array(MainActivity.ADDITIONALFOODS).isEmpty() ){

            AdditionalFoodsArray=get_shared_info_Gson_Foods_Array(MainActivity.ADDITIONALFOODS);
        }
        else{
            AdditionalFoodsArray=new ArrayList<Foods>();
        }


        AddNewFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Get_FillNewFoodName = FillNewFoodName.getText().toString();
                Get_FillCaloriesPer100Grams = FillCaloriesPer100Grams.getText().toString();
                Get_FillProtinsPer100Grams = FillProtinsPer100Grams.getText().toString();
                Get_FillCarbsPer100Grams = FillCarbsPer100Grams.getText().toString();
                Get_FillFatsPer100Grams = FillFatsPer100Grams.getText().toString();
                Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
                if(ChickenBreast.ExternalFoodInArray(Get_FillNewFoodName)){
                    Toast toast = Toast.makeText(getApplicationContext(),"Food Exists" , Toast.LENGTH_SHORT);
                    toast.show();
                }

                if(!ChickenBreast.ExternalFoodInArray(Get_FillNewFoodName) && StringToDouble(Get_FillCaloriesPer100Grams)>=0 && StringToDouble(Get_FillProtinsPer100Grams)>=0 && StringToDouble(Get_FillCarbsPer100Grams)>=0&& StringToDouble(Get_FillFatsPer100Grams)>=0 && !(FoodTypeSpinner.getSelectedItem().toString().equals("")) ){
                    Foods NewFood= new Foods(100, StringToDouble(Get_FillCaloriesPer100Grams),StringToDouble(Get_FillProtinsPer100Grams),StringToDouble(Get_FillFatsPer100Grams),StringToDouble(Get_FillCarbsPer100Grams),Get_FillNewFoodName,FoodTypeSpinner.getSelectedItem().toString());
                    Toast toast = Toast.makeText(getApplicationContext(),Get_FillNewFoodName+" Was Added To The DataBase" , Toast.LENGTH_SHORT);
                    toast.show();
                    PassDataTOmain(NewFood);
                    AdditionalFoodsArray.add(NewFood);
                    Gson_Update_Data_Foods_Array(AdditionalFoodsArray,MainActivity.ADDITIONALFOODS);
                    UpdateCreateMenu();

                }




            }
        });

        FillNewFoodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(FillProtinsPer100Grams.getText().toString().equals("")|| FillNewFoodName.getText().toString().equals("")|| FillCaloriesPer100Grams.getText().toString().equals("")|| FillCarbsPer100Grams.getText().toString().equals("")|| FillFatsPer100Grams.getText().toString().equals("")){
                    AddNewFoodButton.setEnabled(false);
                }
                else{
                    AddNewFoodButton.setEnabled(true);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        FillProtinsPer100Grams.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(FillProtinsPer100Grams.getText().toString().equals("")|| FillNewFoodName.getText().toString().equals("")|| FillCaloriesPer100Grams.getText().toString().equals("")|| FillCarbsPer100Grams.getText().toString().equals("")|| FillFatsPer100Grams.getText().toString().equals("")){
                    AddNewFoodButton.setEnabled(false);
                }
                else{
                    AddNewFoodButton.setEnabled(true);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        FillCaloriesPer100Grams.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(FillProtinsPer100Grams.getText().toString().equals("")|| FillNewFoodName.getText().toString().equals("")|| FillCaloriesPer100Grams.getText().toString().equals("")|| FillCarbsPer100Grams.getText().toString().equals("")|| FillFatsPer100Grams.getText().toString().equals("")){
                    AddNewFoodButton.setEnabled(false);
                }
                else{
                    AddNewFoodButton.setEnabled(true);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        FillCarbsPer100Grams.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(FillProtinsPer100Grams.getText().toString().equals("")|| FillNewFoodName.getText().toString().equals("")|| FillCaloriesPer100Grams.getText().toString().equals("")|| FillCarbsPer100Grams.getText().toString().equals("")|| FillFatsPer100Grams.getText().toString().equals("")){
                    AddNewFoodButton.setEnabled(false);
                }
                else{
                    AddNewFoodButton.setEnabled(true);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        FillFatsPer100Grams.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(FillProtinsPer100Grams.getText().toString().equals("")|| FillNewFoodName.getText().toString().equals("")|| FillCaloriesPer100Grams.getText().toString().equals("")|| FillCarbsPer100Grams.getText().toString().equals("")|| FillFatsPer100Grams.getText().toString().equals("")){
                    AddNewFoodButton.setEnabled(false);
                }
                else{
                    AddNewFoodButton.setEnabled(true);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        Main_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain_Menu();


            }
        });

        DeleteFoodButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDeleteFood();


                }
            });













    }

    public Double StringToDouble(String Key){

        return(Double.parseDouble(Key));

    }


    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();
    }

    public void openDeleteFood(){
        Intent intentDeleteFood= new Intent(this,DeleteFood.class);
        startActivity(intentDeleteFood);
        finish();
    }

    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }

    public void PassDataTOmain(Foods Y){
        ArrayList<Foods> PersonlizedFoodsArray=new ArrayList<Foods>();
        PersonlizedFoodsArray.add(Y);
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        for(String x: user.GetLikedFoods()){
            for(Foods z: ChickenBreast.GetFoodsArray()){
                if(z.GetName().equals(x)){
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

    private void UpdateCreateMenu(){
        if( get_shared_info_Gson_Menu(MainActivity.THEMENU)!=null   ){
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
            Gson_Update_Data_Menu(TheMenu,MainActivity.THEMENU);
        }


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