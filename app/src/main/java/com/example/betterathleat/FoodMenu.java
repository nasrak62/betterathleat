package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FoodMenu extends AppCompatActivity {
    /////General variables/////
    private Gson gson=new Gson();
    private ScrollView MenuScrollView;
    private LinearLayout MenuScrollViewLinearLayout;
    private Button MainMenu;
    private Button Options;
    private CreateMenu TheMenu;
    private User user;
    private ArrayList<Foods> LikedFoodsArray;
    private TableLayout MenuTableLayout;
    private boolean isSpeakButtonLongPressed = false;

    /////Get Info From User/////

    private Spinner NumberOfMealsSpinner;
    private int NumberOfMeals;


    /////Build Menu////
    private ArrayList<ArrayList<Foods>> Meals;
    private ArrayList<Foods> SepcificMeal1= new ArrayList<Foods>();
    private ArrayList<Foods> SepcificMeal2= new ArrayList<Foods>();
    private ArrayList<Foods> SepcificMeal3= new ArrayList<Foods>();
    private ArrayList<Foods> SepcificMeal4= new ArrayList<Foods>();
    private ArrayList<Foods> SepcificMeal5= new ArrayList<Foods>();
    private ArrayList<Button> ChangeFoodButtonArray;
    private Double ActualCaloriesForMeal;
    private Double ActualProtinsForMeal;
    private Double ActualCarbsForMeal;
    private Double ActualFatsForMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        MenuScrollView=(ScrollView) findViewById(R.id.MenuScrollView);
        MenuScrollViewLinearLayout=(LinearLayout) findViewById(R.id.MenuScrollViewLinearLayout);
        MainMenu=(Button)findViewById(R.id.Main_Menu);
        Options=(Button)findViewById(R.id.MenuOptions);
        user= get_shared_info_Gson_User( MainActivity.PASSUSER);

       //ResetMenuStuff();
        if(get_shared_info_string( MainActivity.NUMBEROFMEALS).equals("") || get_shared_info_string( MainActivity.NUMBEROFMEALS).equals("0") ){

            ////Get Info From User////
            TextView ChooseNumberOfMeals= new TextView(this);
            ChooseNumberOfMeals.setText("How Many Meals Will You Eat In A Day?");
            MenuScrollViewLinearLayout.addView(ChooseNumberOfMeals);

            ArrayList<String> SpinnerArray1 = new ArrayList<String>();
            SpinnerArray1.add("1");
            SpinnerArray1.add("2");
            SpinnerArray1.add("3");
            SpinnerArray1.add("4");
            SpinnerArray1.add("5");


            ArrayAdapter<String> SpinnerArray1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArray1);
            NumberOfMealsSpinner = new Spinner(this);
            NumberOfMealsSpinner.setAdapter(SpinnerArray1Adapter);
            MenuScrollViewLinearLayout.addView(NumberOfMealsSpinner);



            Button ButtonGetNumberOfMeals=new Button(this);
            ButtonGetNumberOfMeals.setText("Save My Choice");
            MenuScrollViewLinearLayout.addView(ButtonGetNumberOfMeals);
            ButtonGetNumberOfMeals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MenuScrollViewLinearLayout.removeAllViews();
                    NumberOfMeals=StringToInt(NumberOfMealsSpinner.getSelectedItem().toString());
                    updateData(NumberOfMealsSpinner.getSelectedItem().toString(),MainActivity.NUMBEROFMEALS);
                    BuildMenu();

                }
            });


        }
        else{
            MenuScrollViewLinearLayout.removeAllViews();
            BuildMenu();
        }



        MainMenu.setOnClickListener(new View.OnClickListener() {
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












    }


    /////Build Menu////

    private void BuildMenu(){
        MenuScrollViewLinearLayout.removeAllViews();

        if(get_shared_info_Gson_Array_Of_Arrays_Foods( MainActivity.THEMEALS).isEmpty()){
            LikedFoodsArray=new ArrayList<Foods>();
            Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
            for(String X: user.GetLikedFoods()){
                for(Foods Y: ChickenBreast.GetFoodsArray()){
                    if(Y.GetName().equals(X)){
                        LikedFoodsArray.add(Y);
                    }

                }

            }

            if(!LikedFoodsArray.isEmpty()){
                 ArrayList<Foods> ProtinSourceForMenu=new  ArrayList<Foods>();
                 ArrayList<Foods> CarbsSourceForMenu=new  ArrayList<Foods>();
                 ArrayList<Foods> FatsSourceForMenu=new  ArrayList<Foods>();
                 ArrayList<Foods> FruitsSourceForMenu=new  ArrayList<Foods>();
                 ArrayList<Foods> VegetablesSourceForMenu=new  ArrayList<Foods>();
                 ArrayList<Foods> MilkSourceForMenu=new  ArrayList<Foods>();
                for(Foods X:  LikedFoodsArray) {
                    if (X.GetType().equals("MeatSub") || X.GetType().equals("Egg") || X.GetType().equals("Fish") || X.GetType().equals("Meat")) {
                        ProtinSourceForMenu.add(X);
                    } else if (X.GetType().equals("Carbs")) {
                        CarbsSourceForMenu.add(X);
                    } else if (X.GetType().equals("Fats")) {
                        FatsSourceForMenu.add(X);
                    } else if (X.GetType().equals("Fruit")) {
                        FruitsSourceForMenu.add(X);
                    } else if (X.GetType().equals("Vegetable")) {
                        VegetablesSourceForMenu.add(X);
                    } else if (X.GetType().equals("Milk")) {
                        MilkSourceForMenu.add(X);
                    }
                }

                TheMenu=new CreateMenu(user.GetWeight(),user.GetHeight(),user.GetAge(),LikedFoodsArray,StringToInt(get_shared_info_string( MainActivity.NUMBEROFMEALS)),user.GetPurpose(),user.GetFoodIdeology());
                Gson_Update_Data_Menu(TheMenu,MainActivity.THEMENU);

            }


            else{

                TheMenu=new CreateMenu(user.GetWeight(),user.GetHeight(),user.GetAge(),ChickenBreast.GetFoodsArray(),StringToInt(get_shared_info_string( MainActivity.NUMBEROFMEALS)),user.GetPurpose(),user.GetFoodIdeology());
                Gson_Update_Data_Menu(TheMenu,MainActivity.THEMENU);


            }

            Meals= TheMenu.GetMeals();
            Gson_Update_Data_Array_Of_Arrays_Foods(Meals,MainActivity.THEMEALS);


        }

        else{
            TheMenu=get_shared_info_Gson_Menu(MainActivity.THEMENU);
            Meals=get_shared_info_Gson_Array_Of_Arrays_Foods(MainActivity.THEMEALS);


        }

        ArrayList<String> MealTimes=new ArrayList<String>();

        if(Meals!=null && TheMenu!=null ){



            switch (Meals.size()){
                case 1:
                    MealTimes.add("16:00");
                    break;
                case 2:
                    MealTimes.add("14:00");
                    MealTimes.add("20:00");
                    break;
                case 3:
                    MealTimes.add("08:00");
                    MealTimes.add("14:00");
                    MealTimes.add("20:00");


                    break;
                case 4:
                    MealTimes.add("08:00");
                    MealTimes.add("12:00");
                    MealTimes.add("16:00");
                    MealTimes.add("20:00");
                    break;
                case 5:
                    MealTimes.add("08:00");
                    MealTimes.add("12:00");
                    MealTimes.add("15:00");
                    MealTimes.add("18:00");
                    MealTimes.add("20:00");


                    break;



            }

            for (int i=0;i<=Meals.size()-1;i++){
                TextView MealShow=new TextView(this);
                MealShow.setText("Meal Number " +String.valueOf(i+1)+ ": " + "Recommended Eating Time: " +MealTimes.get(i));
                MealShow.setGravity(1);
                MealShow.setBackgroundResource(R.drawable.login_background);
                LinearLayout.LayoutParams MealShowParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                MealShowParams.setMargins(0, 0, 0, 5);
                MealShow.setLayoutParams(MealShowParams);


                Button MealShowButton=new Button(this);
                MealShowButton.setText("Show Meal"+String.valueOf(i+1)+ ": ");
                MealShowButton.setTag(Meals.get(i));
                MealShowButton.setBackgroundResource(R.drawable.show_meal_button);
                LinearLayout.LayoutParams MealShowButtonParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                MealShowButtonParams.setMargins(0, 0, 0, 5);
                MealShowButton.setLayoutParams(MealShowButtonParams);


                MenuScrollViewLinearLayout.addView(MealShow);
                MenuScrollViewLinearLayout.addView(MealShowButton);
                MealShowButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button b=(Button) view;
                        ArrayList<Foods> X=(ArrayList<Foods>) b.getTag();
                        switch (b.getText().toString()){
                            case "Show Meal"+"1"+ ": ":
                                ShowMealContent(X,1);
                                break;

                            case "Show Meal"+"2"+ ": ":
                                ShowMealContent(X,2);
                                break;

                            case "Show Meal"+"3"+ ": ":
                                ShowMealContent(X,3);
                                break;


                            case "Show Meal"+"4"+ ": ":
                                ShowMealContent(X,4);
                                break;


                            case "Show Meal"+"5"+ ": ":
                                ShowMealContent(X,5);
                                break;

                        }

                    }
                });


            }


        }







    }




    private void ShowMealContent( ArrayList<Foods> X , int MealNumber){
        MenuScrollViewLinearLayout.removeAllViews();

        HorizontalScrollView HorizontalScrollViewInsideLayoutInsideScrollView=new HorizontalScrollView(this);
        MenuScrollViewLinearLayout.addView(HorizontalScrollViewInsideLayoutInsideScrollView);


        LinearLayout HorizontalScrollLinearLayout= new LinearLayout(this);
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);
        HorizontalScrollLinearLayout.setLayoutParams(params);
        HorizontalScrollLinearLayout.setBackgroundColor(0xff0000ff);
        HorizontalScrollLinearLayout.setOrientation(LinearLayout.VERTICAL);
        HorizontalScrollViewInsideLayoutInsideScrollView.addView(HorizontalScrollLinearLayout);

        TableLayout TableInsideHorizontalScrollView=new TableLayout(this);
        TableInsideHorizontalScrollView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
        HorizontalScrollLinearLayout.addView(TableInsideHorizontalScrollView);


        TableRow GeneralInformationRow= new TableRow(this);
        TableRow.LayoutParams params1 =new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        params1.setMargins(2,2,2,2);



        TableInsideHorizontalScrollView.addView(GeneralInformationRow);

        TextView GeneralInfoFoodName=new TextView(this);
        GeneralInfoFoodName.setText("Food Name ");
        GeneralInfoFoodName.setBackgroundColor(-1);
        GeneralInfoFoodName.setGravity(Gravity.CENTER);
        GeneralInfoFoodName.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodName);

        TextView GeneralInfoFoodType=new TextView(this);
        GeneralInfoFoodType.setText("Food Type ");
        GeneralInfoFoodType.setBackgroundColor(-1);
        GeneralInfoFoodType.setGravity(Gravity.CENTER);
        GeneralInfoFoodType.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodType);

        TextView GeneralInfoFoodDominantMacro=new TextView(this);
        GeneralInfoFoodDominantMacro.setText("Food Dominant Macro ");
        GeneralInfoFoodDominantMacro.setBackgroundColor(-1);
        GeneralInfoFoodDominantMacro.setGravity(Gravity.CENTER);
        GeneralInfoFoodDominantMacro.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodDominantMacro);

        TextView GeneralInfoFoodAmount=new TextView(this);
        GeneralInfoFoodAmount.setText("Food Amount ");
        GeneralInfoFoodAmount.setBackgroundColor(-1);
        GeneralInfoFoodAmount.setGravity(Gravity.CENTER);
        GeneralInfoFoodAmount.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodAmount);

        TextView GeneralInfoFoodCaloriesPerAmount=new TextView(this);
        GeneralInfoFoodCaloriesPerAmount.setText("Food Calories For Selected Amount ");
        GeneralInfoFoodCaloriesPerAmount.setBackgroundColor(-1);
        GeneralInfoFoodCaloriesPerAmount.setGravity(Gravity.CENTER);
        GeneralInfoFoodCaloriesPerAmount.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodCaloriesPerAmount);

        TextView GeneralInfoFoodProtinsPerAmount=new TextView(this);
        GeneralInfoFoodProtinsPerAmount.setText("Food Protins For Selected Amount ");
        GeneralInfoFoodProtinsPerAmount.setBackgroundColor(-1);
        GeneralInfoFoodProtinsPerAmount.setGravity(Gravity.CENTER);
        GeneralInfoFoodProtinsPerAmount.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodProtinsPerAmount);

        TextView GeneralInfoFoodCarbsPerAmount=new TextView(this);
        GeneralInfoFoodCarbsPerAmount.setText("Food Carbs For Selected Amount ");
        GeneralInfoFoodCarbsPerAmount.setBackgroundColor(-1);
        GeneralInfoFoodCarbsPerAmount.setGravity(Gravity.CENTER);
        GeneralInfoFoodCarbsPerAmount.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodCarbsPerAmount);

        TextView GeneralInfoFoodFatsPerAmount=new TextView(this);
        GeneralInfoFoodFatsPerAmount.setText("Food Fats For Selected Amount ");
        GeneralInfoFoodFatsPerAmount.setBackgroundColor(-1);
        GeneralInfoFoodFatsPerAmount.setGravity(Gravity.CENTER);
        GeneralInfoFoodFatsPerAmount.setLayoutParams(params1);
        GeneralInformationRow.addView(GeneralInfoFoodFatsPerAmount);


        TableRow ButtonRow= new TableRow(this);
        ButtonRow.setBackgroundColor(-1);
        ActualCaloriesForMeal=0.0;
        ActualProtinsForMeal=0.0;
        ActualCarbsForMeal=0.0;
        ActualFatsForMeal=0.0;
        ChangeFoodButtonArray= new ArrayList<Button>();

        for(Foods Y: X ){
            ActualCaloriesForMeal+=Y.GetNetCalories();
            ActualProtinsForMeal+=Y.GetNetProtins();
            ActualCarbsForMeal+=Y.GetNetCarbs();
            ActualFatsForMeal+=Y.GetNetFats();

            TableRow InformationRow= new TableRow(this);
            TableInsideHorizontalScrollView.addView(InformationRow);




            TextView InfoFoodName=new TextView(this);
            InfoFoodName.setText(Y.GetName()+ " ");
            InfoFoodName.setBackgroundColor(-1);
            InfoFoodName.setGravity(Gravity.CENTER);
            InfoFoodName.setLayoutParams(params1);
            InformationRow.addView(InfoFoodName);

            TextView InfoFoodType=new TextView(this);
            InfoFoodType.setText(Y.GetType()+ " ");
            InfoFoodType.setBackgroundColor(-1);
            InfoFoodType.setGravity(Gravity.CENTER);
            InfoFoodType.setLayoutParams(params1);
            InformationRow.addView(InfoFoodType);

            TextView InfoFoodDominantMacro=new TextView(this);
            InfoFoodDominantMacro.setText(GetDominentMacro(Y)+ " ");
            InfoFoodDominantMacro.setBackgroundColor(-1);
            InfoFoodDominantMacro.setGravity(Gravity.CENTER);
            InfoFoodDominantMacro.setLayoutParams(params1);
            InformationRow.addView(InfoFoodDominantMacro);



            TextView InfoFoodAmount=new TextView(this);
            InfoFoodAmount.setText(String.valueOf(Math.round(Y.GetAmount() * 100d) / 100d)+ " ");
            InfoFoodAmount.setBackgroundColor(-1);
            InfoFoodAmount.setGravity(Gravity.CENTER);
            InfoFoodAmount.setLayoutParams(params1);
            InformationRow.addView(InfoFoodAmount);

            TextView InfoFoodCaloriesPerAmount=new TextView(this);
            InfoFoodCaloriesPerAmount.setText(String.valueOf(Math.round(Y.GetNetCalories() * 100d) / 100d)+ " ");
            InfoFoodCaloriesPerAmount.setBackgroundColor(-1);
            InfoFoodCaloriesPerAmount.setGravity(Gravity.CENTER);
            InfoFoodCaloriesPerAmount.setLayoutParams(params1);
            InformationRow.addView(InfoFoodCaloriesPerAmount);

            TextView InfoFoodProtinsPerAmount=new TextView(this);
            InfoFoodProtinsPerAmount.setText(String.valueOf(Math.round(Y.GetNetProtins() * 100d) / 100d)+ " ");
            InfoFoodProtinsPerAmount.setBackgroundColor(-1);
            InfoFoodProtinsPerAmount.setGravity(Gravity.CENTER);
            InfoFoodProtinsPerAmount.setLayoutParams(params1);
            InformationRow.addView(InfoFoodProtinsPerAmount);

            TextView InfoFoodCarbsPerAmount=new TextView(this);
            InfoFoodCarbsPerAmount.setText(String.valueOf(Math.round(Y.GetNetCarbs() * 100d) / 100d)+ " ");
            InfoFoodCarbsPerAmount.setBackgroundColor(-1);
            InfoFoodCarbsPerAmount.setGravity(Gravity.CENTER);
            InfoFoodCarbsPerAmount.setLayoutParams(params1);
            InformationRow.addView(InfoFoodCarbsPerAmount);

            TextView InfoFoodFatsPerAmount=new TextView(this);
            InfoFoodFatsPerAmount.setText(String.valueOf(Math.round(Y.GetNetFats() * 100d) / 100d)+ " ");
            InfoFoodFatsPerAmount.setBackgroundColor(-1);
            InfoFoodFatsPerAmount.setGravity(Gravity.CENTER);
            InfoFoodFatsPerAmount.setLayoutParams(params1);
            InformationRow.addView(InfoFoodFatsPerAmount);
            ArrayList<Object> TempArray= new ArrayList<Object>();
            TempArray.add(Y);
            TempArray.add(X);
            TempArray.add(MealNumber);
            Button ChangeFood= new Button(this);
            ChangeFood.setText("Change "+Y.GetName());
            ChangeFood.setTextSize(12);
            LinearLayout.LayoutParams ChangeFoodParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            ChangeFoodParams.setMargins(0, 0, 0, 5);
            ChangeFood.setLayoutParams(ChangeFoodParams);
            ChangeFood.setTag(TempArray);
            ChangeFood.setBackgroundResource(R.drawable.change_food_button);

            ChangeFoodButtonArray.add(ChangeFood);
            //ButtonRow.addView(ChangeFood);
            ChangeFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button ChangeFood= (Button) v;
                    ArrayList<Object> TempArray =(ArrayList<Object>) ChangeFood.getTag();

                    FindAnotherFood((Foods)TempArray.get(0),(ArrayList<Foods>)TempArray.get(1) ,(int) TempArray.get(2));
                    Gson_Update_Data_Array_Of_Arrays_Foods(Meals,MainActivity.THEMEALS);
                    ShowMealContent(  (ArrayList<Foods>)TempArray.get(1) ,(int) TempArray.get(2));

                }
            });
            InfoFoodName.setTag(TempArray);
            InfoFoodName.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(getApplicationContext(), "Touched", Toast.LENGTH_SHORT).show();
                    TextView InfoFoodName= (TextView) v;
                    ArrayList<Object> TempArray =(ArrayList<Object>) InfoFoodName.getTag();

                    FindAnotherFood((Foods)TempArray.get(0),(ArrayList<Foods>)TempArray.get(1) ,(int) TempArray.get(2));
                    Gson_Update_Data_Array_Of_Arrays_Foods(Meals,MainActivity.THEMEALS);
                    ShowMealContent(  (ArrayList<Foods>)TempArray.get(1) ,(int) TempArray.get(2));
                    return true;
                }

            });


        }

        TableInsideHorizontalScrollView.addView(ButtonRow);
        TableRow TargetMealInfo= new TableRow(this);
        TableInsideHorizontalScrollView.addView(TargetMealInfo);


        TextView TotalCaloriesForMeal=new TextView(this);
        TotalCaloriesForMeal.setText("Target Calories For The Meal: "+String.valueOf(Math.round(TheMenu.GetCaloriesPerMeal().get(MealNumber-1) * 100d) / 100d));
        TotalCaloriesForMeal.setBackgroundColor(-1);
        TotalCaloriesForMeal.setGravity(Gravity.CENTER);
        TotalCaloriesForMeal.setLayoutParams(params1);
        TargetMealInfo.addView(TotalCaloriesForMeal);

        TextView TotalProtinsForMeal=new TextView(this);
        TotalProtinsForMeal.setText("Target Protins For The Meal: "+String.valueOf(Math.round(TheMenu.GetProtinsPerMeal().get(MealNumber-1) * 100d) / 100d));
        TotalProtinsForMeal.setBackgroundColor(-1);
        TotalProtinsForMeal.setGravity(Gravity.CENTER);
        TotalProtinsForMeal.setLayoutParams(params1);
        TargetMealInfo.addView(TotalProtinsForMeal);

        TextView TotalCarbsForMeal=new TextView(this);
        TotalCarbsForMeal.setText("Target Carbs For The Meal: "+String.valueOf(Math.round(TheMenu.GetCarbsPerMeal().get(MealNumber-1)* 100d) / 100d));
        TotalCarbsForMeal.setBackgroundColor(-1);
        TotalCarbsForMeal.setGravity(Gravity.CENTER);
        TotalCarbsForMeal.setLayoutParams(params1);
        TargetMealInfo.addView(TotalCarbsForMeal);

        TextView TotalFatsForMeal=new TextView(this);
        TotalFatsForMeal.setText("Target Fats For The Meal: "+String.valueOf(Math.round(TheMenu.GetFatsPerMeal().get(MealNumber-1) * 100d) / 100d));
        TotalFatsForMeal.setBackgroundColor(-1);
        TotalFatsForMeal.setGravity(Gravity.CENTER);
        TotalFatsForMeal.setLayoutParams(params1);
        TargetMealInfo.addView(TotalFatsForMeal);









        TableRow ActualMealInfo= new TableRow(this);
        TableInsideHorizontalScrollView.addView(ActualMealInfo);


        TextView ActualTotalCaloriesForMeal=new TextView(this);
        ActualTotalCaloriesForMeal.setText("Actual Calories For The Meal: "+String.valueOf(Math.round(ActualCaloriesForMeal * 100d) / 100d));
        ActualTotalCaloriesForMeal.setBackgroundColor(-1);
        ActualTotalCaloriesForMeal.setGravity(Gravity.CENTER);
        ActualTotalCaloriesForMeal.setLayoutParams(params1);
        ActualMealInfo.addView(ActualTotalCaloriesForMeal);

        TextView ActualTotalProtinsForMeal=new TextView(this);
        ActualTotalProtinsForMeal.setText("Actual Protins For The Meal: "+String.valueOf(Math.round(ActualProtinsForMeal * 100d) / 100d));
        ActualTotalProtinsForMeal.setBackgroundColor(-1);
        ActualTotalProtinsForMeal.setGravity(Gravity.CENTER);
        ActualTotalProtinsForMeal.setLayoutParams(params1);
        ActualMealInfo.addView(ActualTotalProtinsForMeal);

        TextView ActualTotalCarbsForMeal=new TextView(this);
        ActualTotalCarbsForMeal.setText("Actual Carbs For The Meal: "+String.valueOf(Math.round(ActualCarbsForMeal * 100d) / 100d));
        ActualTotalCarbsForMeal.setBackgroundColor(-1);
        ActualTotalCarbsForMeal.setGravity(Gravity.CENTER);
        ActualTotalCarbsForMeal.setLayoutParams(params1);
        ActualMealInfo.addView(ActualTotalCarbsForMeal);

        TextView ActualTotalFatsForMeal=new TextView(this);
        ActualTotalFatsForMeal.setText("Actual Fats For The Meal: "+String.valueOf(Math.round(ActualFatsForMeal * 100d) / 100d));
        ActualTotalFatsForMeal.setBackgroundColor(-1);
        ActualTotalFatsForMeal.setGravity(Gravity.CENTER);
        ActualTotalFatsForMeal.setLayoutParams(params1);
        ActualMealInfo.addView(ActualTotalFatsForMeal);






        TableRow TotalPlanInfo= new TableRow(this);
        TableInsideHorizontalScrollView.addView(TotalPlanInfo);




        TextView TotalCaloriesForPlan=new TextView(this);
        TotalCaloriesForPlan.setText("Total Calories For The Plan: "+String.valueOf(Math.round(TheMenu.GetTotalCalories() * 100d) / 100d));
        TotalCaloriesForPlan.setBackgroundColor(-1);
        TotalCaloriesForPlan.setGravity(Gravity.CENTER);
        TotalCaloriesForPlan.setLayoutParams(params1);
        TotalPlanInfo.addView(TotalCaloriesForPlan);

        TextView TotalProtinsForPlan=new TextView(this);
        TotalProtinsForPlan.setText("Total Protins For The Plan: "+String.valueOf(Math.round(TheMenu.GetTotalProtins() * 100d) / 100d));
        TotalProtinsForPlan.setBackgroundColor(-1);
        TotalProtinsForPlan.setGravity(Gravity.CENTER);
        TotalProtinsForPlan.setLayoutParams(params1);
        TotalPlanInfo.addView(TotalProtinsForPlan);

        TextView TotalCarbsForPlan=new TextView(this);
        TotalCarbsForPlan.setText("Total Carbs For The Plan: "+String.valueOf(Math.round(TheMenu.GetTotalCarbs() * 100d) / 100d));
        TotalCarbsForPlan.setBackgroundColor(-1);
        TotalCarbsForPlan.setGravity(Gravity.CENTER);
        TotalCarbsForPlan.setLayoutParams(params1);
        TotalPlanInfo.addView(TotalCarbsForPlan);

        TextView TotalFatsForPlan=new TextView(this);
        TotalFatsForPlan.setText("Total Fats For The Plan: "+String.valueOf(Math.round(TheMenu.GetTotalFats() * 100d) / 100d));
        TotalFatsForPlan.setBackgroundColor(-1);
        TotalFatsForPlan.setGravity(Gravity.CENTER);
        TotalFatsForPlan.setLayoutParams(params1);
        TotalPlanInfo.addView(TotalFatsForPlan);

        for(Button button: ChangeFoodButtonArray){
            MenuScrollViewLinearLayout.addView(button);
        }
        Button BackToMealsOverView=new Button(this);
        BackToMealsOverView.setText("Back");
        BackToMealsOverView.setBackgroundResource(R.drawable.bright_color_text_for_dark_background);
        LinearLayout.LayoutParams BackToMealsOverViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        BackToMealsOverViewParams.setMargins(0, 0, 0, 5);
        BackToMealsOverView.setLayoutParams(BackToMealsOverViewParams);
        MenuScrollViewLinearLayout.addView(BackToMealsOverView);
        BackToMealsOverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuildMenu();

            }
        });






    }































///////Make Life Easy//////////////////




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




    public void Gson_Empty_UpdateData(String Value,String Key){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

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


    private String GetDominentMacro(Foods Y){
        if(Y.GetNetProtins()>=Y.GetNetCarbs() && Y.GetNetProtins()>=Y.GetNetFats() ){
            return ("Protin");
        }
        if(Y.GetNetCarbs()>=Y.GetNetProtins() && Y.GetNetCarbs()>=Y.GetNetFats() ){
            return ("Carbs");
        }
        else{
            return ("Fats");
        }




    }



    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();

    }

    public void openOptions_Menu(){
        Intent intentOptions_Menu= new Intent(this,MealsOptionsMenu.class);
        startActivity(intentOptions_Menu);
        finish();

    }


    public void ResetMenuStuff(){


        updateData("0",MainActivity.NUMBEROFMEALS);
        Gson_Empty_UpdateData("0",MainActivity.THEMENU);
        Gson_Empty_UpdateData("0",MainActivity.THEMEALS);



    }
    private void FindAnotherFood(Foods Y, ArrayList<Foods> X, int MealNumber){
        int index=X.indexOf(Y);
        Y= TheMenu.GetEquivalentFood(Y,TheMenu.GetCaloriesPerMeal().get(MealNumber-1)+Y.GetNetCalories()-ActualCaloriesForMeal,TheMenu.GetProtinsPerMeal().get(MealNumber-1)+Y.GetNetProtins()-ActualProtinsForMeal,TheMenu.GetCarbsPerMeal().get(MealNumber-1)+Y.GetNetCarbs()-ActualCarbsForMeal,TheMenu.GetFatsPerMeal().get(MealNumber-1)+Y.GetNetFats()-ActualFatsForMeal);
        Meals.get(MealNumber-1).set(index,Y);


    }

}