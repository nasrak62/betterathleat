package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class QLikedFoods extends AppCompatActivity {
    private String FoodIdeology;
    private int ButtonIdInt=1000;
    private LinearLayout LinearLayoutOverAll;
    private Button Next;
    private Button Main_Menu;
    private Button Back;
    private Button SubmitLikedFoods_button;
    private ArrayList<CheckBox> CheckBoxArray = new ArrayList<CheckBox>();
    private CheckBox MeatAndFishCheckBox ;
    private CheckBox CarbsCheckBox ;
    private CheckBox MilkCheckBox ;
    private CheckBox FatsCheckBox;
    private CheckBox EggsCheckBox;
    private CheckBox VegetablesCheckBox ;
    private CheckBox FruitsCheckBox ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_liked_foods);
        LinearLayoutOverAll= (LinearLayout)findViewById(R.id.LinearLayoutScrollViewForMainFoodsTypes);
        Next= (Button) findViewById(R.id.Next1);
        Next.setEnabled(false);
        FoodIdeology=get_shared_info_string( MainActivity.FOODIDEOLOGY);


        MeatAndFishCheckBox = new CheckBox(getApplicationContext());
        CarbsCheckBox = new CheckBox(getApplicationContext());
        MilkCheckBox = new CheckBox(getApplicationContext());
        FatsCheckBox = new CheckBox(getApplicationContext());
        EggsCheckBox = new CheckBox(getApplicationContext());
        VegetablesCheckBox = new CheckBox(getApplicationContext());
        FruitsCheckBox = new CheckBox(getApplicationContext());




        DependOnSwitch();
        SubmitLikedFoods_button= (Button) findViewById(R.id.SubmitLikedFoods_button);
        SubmitLikedFoods_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuildPersonlizedFoodsArray();


            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNextActivity();


            }
        });
        Back= (Button) findViewById(R.id.Back1);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrevActivity();


            }
        });
        Main_Menu= (Button) findViewById(R.id.Main_Menu);
        Main_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain_Menu();


            }
        });




















    }

    private void DependOnSwitch() {
        switch(FoodIdeology){
            case  "1":
                MakeNormalMenu();
                break;
            case  "2":
                MakeNocarbsMenu();
                break;
            case  "3":
                MakeNoFatsMenu();
                break;
            case  "4":
                MakeVegetarianMenu();
                break;
            case  "5":
                MakeVeganMenu();
                break;

        }


    }

    public void MakeNormalMenu(){
        LinearLayoutOverAll.removeAllViews();
        AddMeatAndFishCB();
        AddCarbsCB();
        AddFatsCB();
        AddMilkCB();
        AddEggsCB();
        AddVegetablesCB();
        AddFruitsCB();
    }
    public void AddMeatAndFishCB(){

        MeatAndFishCheckBox.setText("Meat And Fish");
        LinearLayoutOverAll.addView(MeatAndFishCheckBox);
        MeatAndFishCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MeatAndFishCheckBox.isChecked()){
                    CreateMeatAndFishScrollView();
                }
                if(!MeatAndFishCheckBox.isChecked()){
                    DependOnSwitch();
                }

            }
        });
    }
    public void AddCarbsCB(){

        CarbsCheckBox.setText("Carbs");
        LinearLayoutOverAll.addView(CarbsCheckBox);
        CarbsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CarbsCheckBox.isChecked()){
                    CreateCarbsScrollView();
                }
                if(!CarbsCheckBox.isChecked()){
                    DependOnSwitch();
                }

            }
        });
    }
    public void AddFatsCB(){

        FatsCheckBox.setText("Fats");
        LinearLayoutOverAll.addView(FatsCheckBox);
        FatsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FatsCheckBox.isChecked()){
                    CreateFatsScrollView();
                }
                if(!FatsCheckBox.isChecked()){
                    DependOnSwitch();
                }

            }
        });
    }
    public void AddMilkCB(){

        MilkCheckBox.setText("Milk");
        LinearLayoutOverAll.addView(MilkCheckBox);
        MilkCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MilkCheckBox.isChecked()){
                    CreateMilkScrollView();
                }
                if(!MilkCheckBox.isChecked()){
                    DependOnSwitch();
                }

            }
        });
    }
    public void AddEggsCB(){

        EggsCheckBox.setText("Eggs");
        LinearLayoutOverAll.addView(EggsCheckBox);
        EggsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EggsCheckBox.isChecked()){
                    CreateEggsScrollView();
                }
                if(!EggsCheckBox.isChecked()){
                    DependOnSwitch();
                }

            }
        });
    }
    public void AddVegetablesCB(){

        VegetablesCheckBox.setText("Vegetables");
        LinearLayoutOverAll.addView(VegetablesCheckBox);
        VegetablesCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VegetablesCheckBox.isChecked()){
                    CreateVegetablesScrollView();
                }
                if(!VegetablesCheckBox.isChecked()){
                    DependOnSwitch();
                }

            }
        });
    }
    public void AddFruitsCB(){

        FruitsCheckBox.setText("Fruits");
        LinearLayoutOverAll.addView(FruitsCheckBox);
        FruitsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FruitsCheckBox.isChecked()){
                    CreateFruitsScrollView();
                }
                if(!FruitsCheckBox.isChecked()){
                    DependOnSwitch();
                }

            }
        });
    }
    public void MakeNocarbsMenu(){
        LinearLayoutOverAll.removeAllViews();
        AddMeatAndFishCB();
        AddFatsCB();
        AddMilkCB();
        AddEggsCB();
        AddVegetablesCB();
        AddFruitsCB();

    }
    public void MakeNoFatsMenu(){
        LinearLayoutOverAll.removeAllViews();
        AddMeatAndFishCB();
        AddCarbsCB();
        AddMilkCB();
        AddEggsCB();
        AddVegetablesCB();
        AddFruitsCB();

    }
    public void MakeVegetarianMenu(){
        LinearLayoutOverAll.removeAllViews();
        AddCarbsCB();
        AddFatsCB();
        AddMilkCB();
        AddEggsCB();
        AddVegetablesCB();
        AddFruitsCB();

    }
    public void MakeVeganMenu(){
        LinearLayoutOverAll.removeAllViews();
        AddCarbsCB();
        AddFatsCB();
        AddVegetablesCB();
        AddFruitsCB();

    }


    @SuppressLint("ResourceAsColor")
    public void CreateMeatAndFishScrollView(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        LinearLayoutOverAll.removeAllViews();
        LinearLayoutOverAll.addView(MeatAndFishCheckBox);
        if(!MeatAndFishCheckBox.isChecked()){
            MeatAndFishCheckBox.setChecked(true);
        }


        ArrayList<Foods> MeatAndFishArray;
        MeatAndFishArray = ChickenBreast.GetMeatAndFishInFoodsArray();

        for(Foods x : MeatAndFishArray) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(x.GetName());
            cb.setTag(x);
            CheckBoxArray.add(cb);
            LinearLayoutOverAll.addView(cb);
        }

        CheckBox cbForAll = new CheckBox(getApplicationContext());
        cbForAll.setText("Check \\ UnCheck All");
        LinearLayoutOverAll.addView(cbForAll);
        cbForAll.setTag(MeatAndFishArray);
        cbForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =(CheckBox)v;
                ArrayList<Foods> MeatAndFishArray=(ArrayList<Foods>)cb.getTag();
                if(cb.isChecked()){
                    for(Foods x : MeatAndFishArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(true);
                    }
                }
                if(!cb.isChecked()){
                    for(Foods x : MeatAndFishArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(false);
                    }
                }

            }
        });



    }
    public void CreateCarbsScrollView(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        LinearLayoutOverAll.removeAllViews();
        LinearLayoutOverAll.addView(CarbsCheckBox);
        if(!CarbsCheckBox.isChecked()){
            CarbsCheckBox.setChecked(true);
        }
        ArrayList<Foods> CarbsArray ;
        CarbsArray = ChickenBreast.GetCarbsInFoodsArray();

        for(Foods x : CarbsArray) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(x.GetName());
            cb.setTag(x);
            CheckBoxArray.add(cb);
            LinearLayoutOverAll.addView(cb);
        }

        CheckBox cbForAll = new CheckBox(getApplicationContext());
        cbForAll.setText("Check \\ UnCheck All");
        LinearLayoutOverAll.addView(cbForAll);
        cbForAll.setTag( CarbsArray);
        cbForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =(CheckBox)v;
                ArrayList<Foods>  CarbsArray=(ArrayList<Foods>)cb.getTag();
                if(cb.isChecked()){
                    for(Foods x :  CarbsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(true);
                    }
                }
                if(!cb.isChecked()){
                    for(Foods x :  CarbsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(false);
                    }
                }

            }
        });


    }
    public void CreateFatsScrollView(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        LinearLayoutOverAll.removeAllViews();
        LinearLayoutOverAll.addView(FatsCheckBox);
        if(!FatsCheckBox.isChecked()){
            FatsCheckBox.setChecked(true);
        }
        ArrayList<Foods> FatsArray ;
        FatsArray = ChickenBreast.GetFatsInFoodsArray();

        for(Foods x : FatsArray) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(x.GetName());
            cb.setTag(x);
            CheckBoxArray.add(cb);
            LinearLayoutOverAll.addView(cb);
        }
        CheckBox cbForAll = new CheckBox(getApplicationContext());
        cbForAll.setText("Check \\ UnCheck All");
        LinearLayoutOverAll.addView(cbForAll);
        cbForAll.setTag(FatsArray);
        cbForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =(CheckBox)v;
                ArrayList<Foods> FatsArray=(ArrayList<Foods>)cb.getTag();
                if(cb.isChecked()){
                    for(Foods x : FatsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(true);
                    }
                }
                if(!cb.isChecked()){
                    for(Foods x : FatsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(false);
                    }
                }

            }
        });

    }
    public void CreateMilkScrollView(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        LinearLayoutOverAll.removeAllViews();
        LinearLayoutOverAll.addView(MilkCheckBox);
        if(!MilkCheckBox.isChecked()){
            MilkCheckBox.setChecked(true);
        }
        ArrayList<Foods> MilkArray ;
        MilkArray = ChickenBreast.GetMilkInFoodsArray();

        for(Foods x : MilkArray) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(x.GetName());
            cb.setTag(x);
            CheckBoxArray.add(cb);
            LinearLayoutOverAll.addView(cb);
        }
        CheckBox cbForAll = new CheckBox(getApplicationContext());
        cbForAll.setText("Check \\ UnCheck All");
        LinearLayoutOverAll.addView(cbForAll);
        cbForAll.setTag(MilkArray);
        cbForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =(CheckBox)v;
                ArrayList<Foods> MilkArray=(ArrayList<Foods>)cb.getTag();
                if(cb.isChecked()){
                    for(Foods x : MilkArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(true);
                    }
                }
                if(!cb.isChecked()){
                    for(Foods x : MilkArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(false);
                    }
                }

            }
        });

    }
    public void CreateVegetablesScrollView(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        LinearLayoutOverAll.removeAllViews();
        LinearLayoutOverAll.addView(VegetablesCheckBox);
        if(!VegetablesCheckBox.isChecked()){
            VegetablesCheckBox.setChecked(true);
        }
        ArrayList<Foods> VegetablesArray ;
        VegetablesArray = ChickenBreast.GetVegetablesInFoodsArray();

        for(Foods x : VegetablesArray) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(x.GetName());
            cb.setTag(x);
            CheckBoxArray.add(cb);
            LinearLayoutOverAll.addView(cb);
        }
        CheckBox cbForAll = new CheckBox(getApplicationContext());
        cbForAll.setText("Check \\ UnCheck All");
        LinearLayoutOverAll.addView(cbForAll);
        cbForAll.setTag(VegetablesArray);
        cbForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =(CheckBox)v;
                ArrayList<Foods> VegetablesArray=(ArrayList<Foods>)cb.getTag();
                if(cb.isChecked()){
                    for(Foods x : VegetablesArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(true);
                    }
                }
                if(!cb.isChecked()){
                    for(Foods x : VegetablesArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(false);
                    }
                }

            }
        });

    }
    public void CreateFruitsScrollView(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        LinearLayoutOverAll.removeAllViews();
        LinearLayoutOverAll.addView(FruitsCheckBox);
        if(!FruitsCheckBox.isChecked()){
            FruitsCheckBox.setChecked(true);
        }
        ArrayList<Foods> FruitsArray ;
        FruitsArray = ChickenBreast.GetFruitsInFoodsArray();

        for(Foods x : FruitsArray) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(x.GetName());
            cb.setTag(x);
            CheckBoxArray.add(cb);
            LinearLayoutOverAll.addView(cb);
        }
        CheckBox cbForAll = new CheckBox(getApplicationContext());
        cbForAll.setText("Check \\ UnCheck All");
        LinearLayoutOverAll.addView(cbForAll);
        cbForAll.setTag(FruitsArray);
        cbForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =(CheckBox)v;
                ArrayList<Foods> FruitsArray=(ArrayList<Foods>)cb.getTag();
                if(cb.isChecked()){
                    for(Foods x : FruitsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(true);
                    }
                }
                if(!cb.isChecked()){
                    for(Foods x : FruitsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(false);
                    }
                }

            }
        });

    }
    public void CreateEggsScrollView(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        LinearLayoutOverAll.removeAllViews();
        LinearLayoutOverAll.addView(EggsCheckBox);
        if(!EggsCheckBox.isChecked()){
            EggsCheckBox.setChecked(true);
        }
        ArrayList<Foods> EggsArray ;
        EggsArray = ChickenBreast.GetEggsInFoodsArray();

        for(Foods x : EggsArray) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(x.GetName());
            cb.setTag(x);
            CheckBoxArray.add(cb);
            LinearLayoutOverAll.addView(cb);
        }
        CheckBox cbForAll = new CheckBox(getApplicationContext());
        cbForAll.setText("Check \\ UnCheck All");
        LinearLayoutOverAll.addView(cbForAll);
        cbForAll.setTag(EggsArray);
        cbForAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb =(CheckBox)v;
                ArrayList<Foods> EggsArray=(ArrayList<Foods>)cb.getTag();
                if(cb.isChecked()){
                    for(Foods x : EggsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(true);
                    }
                }
                if(!cb.isChecked()){
                    for(Foods x : EggsArray) {
                        CheckBox cbTemp=(CheckBox) LinearLayoutOverAll.findViewWithTag(x);
                        cbTemp.setChecked(false);
                    }
                }

            }
        });

    }
    public String get_shared_info_string( final String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        if((sharedPreferences.getString(Key,"0" ).equals("")))
        {
            updateData("0",Key);
        }
        return(sharedPreferences.getString(Key,"0" ));
    }
    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public void openNextActivity(){
        Intent intentopenNextActivity= new Intent(this, QFinish.class);
        startActivity(intentopenNextActivity);
        finish();
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openPrevActivity(){
        Intent intentopenPrevActivity= new Intent(this, QFoodIdeology.class);
        startActivity(intentopenPrevActivity);
        finish();

    }
    public void BuildPersonlizedFoodsArray(){
        Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
        ArrayList<Foods> PersonlizedFoodsArray = new ArrayList<Foods>();


        for(CheckBox cb : CheckBoxArray) {

            if( cb.isChecked()){
                PersonlizedFoodsArray.add((Foods) cb.getTag());


            }


        }
        if(PersonlizedFoodsArray.isEmpty()){
            Next.setEnabled(false);
            Toast toast = Toast.makeText(getApplicationContext(), "You Need To Select Some Food", Toast.LENGTH_SHORT);

            toast.show();
        }
        else if(!PersonlizedFoodsArray.isEmpty()){
            Next.setEnabled(true);
            Toast toast = Toast.makeText(getApplicationContext(),"Info Updated " , Toast.LENGTH_SHORT);
            toast.show();
            PassDataTOmain(PersonlizedFoodsArray);
        }

    }
    public void PassDataTOmain(ArrayList<Foods> PersonlizedFoodsArray){
        String Temp="";
        for(Foods x: PersonlizedFoodsArray) {
            Temp+= x.GetName();
            if(PersonlizedFoodsArray.indexOf(x)!=PersonlizedFoodsArray.size()-1){
                Temp+= ",";
            }
        }
        updateData(Temp,MainActivity.LIKEDFOODS);
        ResetProjectKeyElements();



    }



    public void ResetProjectKeyElements(){
        UpdateUserIfPossible();
        ResetMenuStuff();


    }

    public void ResetMenuStuff(){


        updateData("0",MainActivity.NUMBEROFMEALS);
        Gson_Empty_UpdateData("0",MainActivity.THEMENU);
        Gson_Empty_UpdateData("0",MainActivity.THEMEALS);



    }
    public void Gson_Empty_UpdateData(String Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

    }
    private void UpdateUserIfPossible(){
        if(!get_shared_info_Gson_User( MainActivity.PASSUSER).GetName().equals("0")){
            User user=get_shared_info_Gson_User( MainActivity.PASSUSER);
            user.SetLikedFoods(convertStringToArray(get_shared_info_string(MainActivity.LIKEDFOODS)));
            Gson_Update_Data_User(user,MainActivity.PASSUSER);
        }




    }



    public User get_shared_info_Gson_User( final String Key){
        Gson gson=new Gson();
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
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
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
}