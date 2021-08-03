package com.example.betterathleat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreateYourOwnMenu extends AppCompatActivity {

    /////General variables/////
    private Gson gson=new Gson();
    private ScrollView OwnMenuScrollView;
    private LinearLayout OwnMenuScrollViewLinearLayout;
    private Button MainMenu;
    private Button Options;
    private CreateMenu TheMenu;
    private User user;
    private ArrayList<Foods> LikedFoodsArray;
    private LinearLayout OwnMenuOpScrollViewLinearLayout;
    private TableLayout OwnTableMenu;

    /////Build Own Menu////
    private ArrayList<ArrayList<Foods>> OwnMeals;
    private Spinner MealsSpinner=null;
    private  ArrayList<String> SpinnerArray1;
    private  ArrayList<String> AddFoodSpinnerArrayString;
    private Spinner AddFoodSpinner =null;
    private  ArrayList<String> SpinnerArrayForMealOptions;
    private Spinner MealsSpinnerForMealOptions=null;
    private int NumberOfMeals;
    private LinearLayout HorizontalScrollLinearLayout;
    private String Get_FillAddFoodAmountForMealText;
    private EditText FillAddFoodAmountForMealText;
    private Button AddFoodForMealButton;
    private int CurrentMeal;
    private ArrayAdapter<String> SpinnerArray1Adapter;
    private ArrayAdapter<String> SpinnerArray1AdapterOptions;
    private ArrayAdapter<String> DeleteFoodSpinnerArrayAdapter;
    private ArrayAdapter<String> ChangeFoodSpinnerArrayAdapter;


    private TableRow GeneralInformationRow;
    private Button AddMeal=null;
    private TextView SelectMeal=null;
    private ArrayList<TableLayout> UsedTables=new ArrayList<TableLayout>();
    private TableRow.LayoutParams params1;


    private Button DeleteFoodForMealButton;
    private  ArrayList<String> DeleteFoodSpinnerArrayString= new ArrayList<String>();
    private Spinner DeleteFoodSpinner =null;

    private Button ChangeFoodForMealButton;
    private  ArrayList<String> ChangeFoodSpinnerArrayString;
    private Spinner ChangeFoodSpinner =null;
    private String Get_FillChangeFoodAmountForMealText;
    private EditText FillChangeFoodAmountForMealText;


    private ArrayList<View> OptionMenuArrayViews=new ArrayList<View>();
    private ArrayList<Object> InfoFoodNameTempArray= new ArrayList<Object>();

    private Double TotalCalories;
    private Double TotalProtins;
    private Double TotalCarbs;
    private Double TotalFats;
    final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own_menu);

        OwnMenuScrollView=(ScrollView) findViewById(R.id.OwnMenuScrollView);
        OwnMenuScrollViewLinearLayout=(LinearLayout) findViewById(R.id.OwnMenuScrollViewLinearLayout);
        MainMenu=(Button)findViewById(R.id.Main_Menu);
        user= get_shared_info_Gson_User( MainActivity.PASSUSER);
        OwnMeals= new ArrayList<ArrayList<Foods>>();
        OwnMenuOpScrollViewLinearLayout=(LinearLayout) findViewById(R.id.OwnMenuOpScrollViewLinearLayout);
        NumberOfMeals=0;

        if(!get_shared_info_Gson_Array_Of_Arrays_Foods(MainActivity.OWNMEALS).isEmpty()){

            OwnMeals=get_shared_info_Gson_Array_Of_Arrays_Foods(MainActivity.OWNMEALS);
            BuildTableMenu();

        }
        else{
            StartTheBuild();


        }

        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain_Menu();


            }
        });








    }

    private void BuildTableMenu(){
        InitializeOptionMenuBasicViews();
        OwnMenuScrollViewLinearLayout.removeAllViews();

        HorizontalScrollView HorizontalScrollViewInsideLayoutInsideScrollView=new HorizontalScrollView(this);
        OwnMenuScrollViewLinearLayout.addView(HorizontalScrollViewInsideLayoutInsideScrollView);


        HorizontalScrollLinearLayout= new LinearLayout(this);
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);
        HorizontalScrollLinearLayout.setLayoutParams(params);
        HorizontalScrollLinearLayout.setBackgroundColor(0xff0000ff);
        HorizontalScrollLinearLayout.setOrientation(LinearLayout.VERTICAL);
        HorizontalScrollViewInsideLayoutInsideScrollView.addView(HorizontalScrollLinearLayout);
        UpdateTable();

    }




    private void StartTheBuild(){
        InitializeOptionMenuBasicViews();

        OwnMenuScrollViewLinearLayout.removeAllViews();

        HorizontalScrollView HorizontalScrollViewInsideLayoutInsideScrollView=new HorizontalScrollView(this);
        OwnMenuScrollViewLinearLayout.addView(HorizontalScrollViewInsideLayoutInsideScrollView);


        HorizontalScrollLinearLayout= new LinearLayout(this);
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);
        HorizontalScrollLinearLayout.setLayoutParams(params);
        HorizontalScrollLinearLayout.setBackgroundColor(0xff0000ff);
        HorizontalScrollLinearLayout.setOrientation(LinearLayout.VERTICAL);
        HorizontalScrollViewInsideLayoutInsideScrollView.addView(HorizontalScrollLinearLayout);

        TableLayout TableInsideHorizontalScrollView=new TableLayout(this);
        TableInsideHorizontalScrollView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
        HorizontalScrollLinearLayout.addView(TableInsideHorizontalScrollView);
        UsedTables.add(TableInsideHorizontalScrollView);

        TableRow Meal1= new TableRow(this);
        NumberOfMeals=1;
        TableInsideHorizontalScrollView.addView(Meal1);
        TableInsideHorizontalScrollView.setTag(NumberOfMeals);
        TableRow.LayoutParams paramsMeal1 =new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        paramsMeal1.setMargins(2,2,2,2);
        TextView TitleMeal1=new TextView(this);
        TitleMeal1.setText("Meal 1: ");
        SpinnerArray1.add("Meal"+ " " +String.valueOf(NumberOfMeals) +":" );
        MealsSpinner.setAdapter(SpinnerArray1Adapter);
        TitleMeal1.setBackgroundColor(-1);
        TitleMeal1.setGravity(Gravity.CENTER);
        TitleMeal1.setLayoutParams(paramsMeal1);
        Meal1.addView(TitleMeal1);

        ArrayList<Foods> Meal=new ArrayList<Foods>();
        OwnMeals.add(Meal);


        GeneralInformationRow= new TableRow(this);
        TableInsideHorizontalScrollView.addView(GeneralInformationRow);
        LoadInformationToGeneralInformationRow();




    }




private void  AddNewMealTable(){

    NumberOfMeals=OwnMeals.size()+1;
    TableLayout TableInsideHorizontalScrollView=new TableLayout(this);
    TableInsideHorizontalScrollView.setTag(NumberOfMeals);
    TableLayout.LayoutParams TableInsideHorizontalScrollViewParams = new TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT
    );
    TableInsideHorizontalScrollViewParams.setMargins(0, 0, 0, 20);
    TableInsideHorizontalScrollView.setLayoutParams(TableInsideHorizontalScrollViewParams);

    //TableInsideHorizontalScrollView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
    HorizontalScrollLinearLayout.addView(TableInsideHorizontalScrollView);
    ArrayList<Foods> Meal=new ArrayList<Foods>();
    OwnMeals.add(Meal);
    UsedTables.add(TableInsideHorizontalScrollView);

    TableRow Meal1= new TableRow(this);

    TableInsideHorizontalScrollView.addView(Meal1);
    TableRow.LayoutParams paramsMeal1 =new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
    paramsMeal1.setMargins(2,2,2,20);
    TextView TitleMeal1=new TextView(this);
    TitleMeal1.setText("Meal"+ " " +String.valueOf(NumberOfMeals) +":" );
    SpinnerArray1.add("Meal"+ " " +String.valueOf(NumberOfMeals) +":" );
    MealsSpinner.setAdapter(SpinnerArray1Adapter);
    TitleMeal1.setBackgroundColor(-1);
    TitleMeal1.setGravity(Gravity.CENTER);
    TitleMeal1.setLayoutParams(paramsMeal1);
    Meal1.addView(TitleMeal1);
    GeneralInformationRow= new TableRow(this);
    TableInsideHorizontalScrollView.addView(GeneralInformationRow);
    LoadInformationToGeneralInformationRow();

}



private void RefreshOpMenu(){
    OwnMenuOpScrollViewLinearLayout.removeAllViews();
    for(View X: OptionMenuArrayViews){
        OwnMenuOpScrollViewLinearLayout.addView(X);
    }


}

private void InitializeOptionMenuBasicViews(){
    AddMeal= new Button(this);
    AddMeal.setText("Add New Meal");
    OwnMenuOpScrollViewLinearLayout.addView(AddMeal);
    AddMeal.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            AddNewMealTable();


        }
    });
    OptionMenuArrayViews.add(AddMeal);



    SelectMeal= new TextView(this);
    SelectMeal.setText("Select Meal:");
    OwnMenuOpScrollViewLinearLayout.addView(SelectMeal);
    OptionMenuArrayViews.add(SelectMeal);


    SpinnerArray1 = new ArrayList<String>();
    SpinnerArray1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArray1);
    MealsSpinner = new Spinner(this);
    MealsSpinner.setAdapter(SpinnerArray1Adapter);
    OwnMenuOpScrollViewLinearLayout.addView(MealsSpinner);
    OptionMenuArrayViews.add(MealsSpinner);



    TextView OptionForMealText=new TextView(this);
    OptionForMealText.setText("Select Option For Meal ");
    OwnMenuOpScrollViewLinearLayout.addView(OptionForMealText);
    OptionMenuArrayViews.add(OptionForMealText);


    SpinnerArrayForMealOptions = new ArrayList<String>();
    SpinnerArrayForMealOptions.add("Choose Option");
    SpinnerArrayForMealOptions.add("Add Food For Meal");
    SpinnerArrayForMealOptions.add("Delete Food From Meal");
    SpinnerArrayForMealOptions.add("Delete Meal");
   // SpinnerArrayForMealOptions.add("Change Selected Food");
    SpinnerArrayForMealOptions.add("Change Food Amount");



    SpinnerArray1AdapterOptions = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpinnerArrayForMealOptions);
    MealsSpinnerForMealOptions = new Spinner(this);
    MealsSpinnerForMealOptions.setAdapter(SpinnerArray1AdapterOptions);
    OwnMenuOpScrollViewLinearLayout.addView(MealsSpinnerForMealOptions);
    OptionMenuArrayViews.add(MealsSpinnerForMealOptions);


    MealsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            RefreshOpMenu();
            MealsSpinnerForMealOptions.setSelection(0);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }

    });


    MealsSpinnerForMealOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            switch (MealsSpinnerForMealOptions.getSelectedItem().toString()){
                case "Add Food For Meal":
                    UpgradeOpViewAddFood();
                    break;

                case "Delete Food From Meal":
                    UpgradeOpViewDeleteFood();
                    break;

                case "Delete Meal":
                    DeleteMealView();
                    break;

              /*  case "Change Selected Food":
                    ChangeSelectedFood();
                    break;*/

                case "Change Food Amount":
                    ChangeAmountForSelectedFood();
                    break;
                case "Choose Option":
                    RefreshOpMenu();
                    break;


            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }

    });

}





/*private void  ChangeSelectedFood(){
    RefreshOpMenu();

    TextView ChangeFoodForMealText=new TextView(this);
    ChangeFoodForMealText.setText("Select Food");
    OwnMenuOpScrollViewLinearLayout.addView(ChangeFoodForMealText);

    ChangeFoodSpinnerArrayString = new ArrayList<String>();
    for(Foods x:OwnMeals.get(GetCurrentMealIndex())){
        ChangeFoodSpinnerArrayString.add(x.GetName());
    }

    ChangeFoodSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ChangeFoodSpinnerArrayString);
    ChangeFoodSpinner = new Spinner(this);
    ChangeFoodSpinner.setAdapter(ChangeFoodSpinnerArrayAdapter);
    OwnMenuOpScrollViewLinearLayout.addView(ChangeFoodSpinner);

    ChangeFoodForMealButton= new Button(this);
    ChangeFoodForMealButton.setText("Change Amount ");
    ChangeFoodForMealButton.setTag(GetCurrentMealIndex());
    OwnMenuOpScrollViewLinearLayout.addView(ChangeFoodForMealButton);
    ChangeFoodForMealButton.setEnabled(false);
    if( !OwnMeals.get(GetCurrentMealIndex()).isEmpty()  ){
        ChangeFoodForMealButton.setEnabled(true);
    }

    ChangeFoodForMealButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChangeFoodForChosenMeal();

        }
    });

}*/


/*private void ChangeFoodForChosenMeal(){
    for(Foods x: OwnMeals.get(GetCurrentMealIndex())){
        if(x.GetName().equals(ChangeFoodSpinner.getSelectedItem().toString()) ){
            x.ChangeAmount(StringToInt(Get_FillChangeFoodAmountForMealText));
            UpdateTable();
            RefreshOpMenu();
            MealsSpinnerForMealOptions.setSelection(0);
            break;
        }
    }


}*/



private void DeleteMealView(){
    RefreshOpMenu();
    Button DeleteMealButton= new Button(this);
    DeleteMealButton.setText("Delete Meal");
    OwnMenuOpScrollViewLinearLayout.addView(DeleteMealButton);
    DeleteMealButton.setEnabled(false);
    if(OwnMeals.size()>1){
        DeleteMealButton.setEnabled(true);

    }
    else{
        Toast toast = Toast.makeText(getApplicationContext(),"You Need At Least Two Meals To Use Delete" , Toast.LENGTH_SHORT);
        toast.show();

    }
    DeleteMealButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DeleteMeal();
        }
    });


}



private void DeleteMeal(){

    if(OwnMeals.size()>1){
        UsedTables.remove(GetCurrentMealIndex());
        OwnMeals.remove(GetCurrentMealIndex());
        Gson_Update_Data_Array_Of_Arrays_Foods(OwnMeals,MainActivity.OWNMEALS);
        NumberOfMeals=OwnMeals.size();
        SpinnerArray1.remove(GetCurrentMealIndex());
        MealsSpinner.setSelection(0);
        MealsSpinnerForMealOptions.setSelection(0);
        UpdateTable();
        RefreshOpMenu();

    }
    else{
        Toast toast = Toast.makeText(getApplicationContext(),"You Need At Least Two Meals To Use Delete" , Toast.LENGTH_SHORT);
        toast.show();

    }



}



private void  ChangeAmountForSelectedFood(){

    RefreshOpMenu();

    TextView ChangeFoodForMealText=new TextView(this);
    ChangeFoodForMealText.setText("Select Food");
    OwnMenuOpScrollViewLinearLayout.addView(ChangeFoodForMealText);

    ChangeFoodSpinnerArrayString = new ArrayList<String>();
    for(Foods x:OwnMeals.get(GetCurrentMealIndex())){
        ChangeFoodSpinnerArrayString.add(x.GetName());
    }

    ChangeFoodSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ChangeFoodSpinnerArrayString);
    ChangeFoodSpinner = new Spinner(this);
    ChangeFoodSpinner.setAdapter(ChangeFoodSpinnerArrayAdapter);
    OwnMenuOpScrollViewLinearLayout.addView(ChangeFoodSpinner);



    FillChangeFoodAmountForMealText= new EditText(this);
    FillChangeFoodAmountForMealText.setInputType(InputType.TYPE_CLASS_NUMBER);
    OwnMenuOpScrollViewLinearLayout.addView(FillChangeFoodAmountForMealText);
    Get_FillChangeFoodAmountForMealText=FillChangeFoodAmountForMealText.getText().toString();

    FillChangeFoodAmountForMealText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Get_FillChangeFoodAmountForMealText=FillChangeFoodAmountForMealText.getText().toString();
            if( Get_FillChangeFoodAmountForMealText.equals("") || Get_FillChangeFoodAmountForMealText.equals(null) || StringToInt(Get_FillChangeFoodAmountForMealText)<=0 || ChangeFoodSpinner.getSelectedItem().toString().equals("") || ChangeFoodSpinner.getSelectedItem().toString().equals(null) ){

                ChangeFoodForMealButton.setEnabled(false);
            }

            else{
                ChangeFoodForMealButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
    ChangeFoodForMealButton= new Button(this);
    ChangeFoodForMealButton.setText("Change Amount ");
    ChangeFoodForMealButton.setTag(GetCurrentMealIndex());
    OwnMenuOpScrollViewLinearLayout.addView(ChangeFoodForMealButton);
    ChangeFoodForMealButton.setEnabled(false);
    if( !OwnMeals.get(GetCurrentMealIndex()).isEmpty() && !( Get_FillChangeFoodAmountForMealText.equals("") || Get_FillChangeFoodAmountForMealText.equals(null) || StringToInt(Get_FillChangeFoodAmountForMealText)<=0 || ChangeFoodSpinner.getSelectedItem().toString().equals("") || ChangeFoodSpinner.getSelectedItem().toString().equals(null) ) ){
        ChangeFoodForMealButton.setEnabled(true);
    }

    ChangeFoodForMealButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ChangeFoodAmountForChosenMeal();

        }
    });




}

private void ChangeFoodAmountForChosenMeal(){
    for(Foods x: OwnMeals.get(GetCurrentMealIndex())){
        if(x.GetName().equals(ChangeFoodSpinner.getSelectedItem().toString()) ){
           x.ChangeAmount(StringToInt(Get_FillChangeFoodAmountForMealText));
            Gson_Update_Data_Array_Of_Arrays_Foods(OwnMeals,MainActivity.OWNMEALS);
            UpdateTable();
            RefreshOpMenu();
            MealsSpinnerForMealOptions.setSelection(0);
            break;
        }
    }


}





private void UpgradeOpViewAddFood(){
    RefreshOpMenu();



    TextView AddFoodForMealText=new TextView(this);
    AddFoodForMealText.setText("Select Food For Meal ");
    OwnMenuOpScrollViewLinearLayout.addView(AddFoodForMealText);

    AddFoodSpinnerArrayString = new ArrayList<String>();
    for(String x:user.GetLikedFoods()){
        AddFoodSpinnerArrayString.add(x);
    }

    ArrayAdapter<String> AddFoodSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, AddFoodSpinnerArrayString);
    AddFoodSpinner = new Spinner(this);
    AddFoodSpinner.setAdapter(AddFoodSpinnerArrayAdapter);
    OwnMenuOpScrollViewLinearLayout.addView(AddFoodSpinner);


    TextView AddFoodAmountForMealText=new TextView(this);
    AddFoodAmountForMealText.setText("Select Food Amount ");
    OwnMenuOpScrollViewLinearLayout.addView(AddFoodAmountForMealText);


    FillAddFoodAmountForMealText= new EditText(this);
    FillAddFoodAmountForMealText.setInputType(InputType.TYPE_CLASS_NUMBER);
    OwnMenuOpScrollViewLinearLayout.addView(FillAddFoodAmountForMealText);
    Get_FillAddFoodAmountForMealText=FillAddFoodAmountForMealText.getText().toString();

    AddFoodForMealButton= new Button(this);
    AddFoodForMealButton.setText("Add New Food For Meal ");
    AddFoodForMealButton.setTag(GetCurrentMealIndex());
    OwnMenuOpScrollViewLinearLayout.addView(AddFoodForMealButton);
    AddFoodForMealButton.setEnabled(false);

    FillAddFoodAmountForMealText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Get_FillAddFoodAmountForMealText=FillAddFoodAmountForMealText.getText().toString();
            if( Get_FillAddFoodAmountForMealText.equals("") || Get_FillAddFoodAmountForMealText.equals(null) || StringToInt(Get_FillAddFoodAmountForMealText)<=0 || AddFoodSpinner.getSelectedItem().toString().equals("") || AddFoodSpinner.getSelectedItem().toString().equals(null) ){

                AddFoodForMealButton.setEnabled(false);
            }

            else{
                AddFoodForMealButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });


    AddFoodForMealButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddFoodForChosenMeal();

        }
    });



}


private void UpgradeOpViewDeleteFood(){
    RefreshOpMenu();



    TextView DeleteFoodForMealText=new TextView(this);
    DeleteFoodForMealText.setText("Select Food");
    OwnMenuOpScrollViewLinearLayout.addView(DeleteFoodForMealText);

    DeleteFoodSpinnerArrayString = new ArrayList<String>();
    for(Foods x:OwnMeals.get(GetCurrentMealIndex())){
        DeleteFoodSpinnerArrayString.add(x.GetName());
    }

    DeleteFoodSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, DeleteFoodSpinnerArrayString);
    DeleteFoodSpinner = new Spinner(this);
    DeleteFoodSpinner.setAdapter(DeleteFoodSpinnerArrayAdapter);
    OwnMenuOpScrollViewLinearLayout.addView(DeleteFoodSpinner);

    DeleteFoodForMealButton= new Button(this);
    DeleteFoodForMealButton.setText("Delete New Food For Meal ");
    DeleteFoodForMealButton.setTag(GetCurrentMealIndex());
    OwnMenuOpScrollViewLinearLayout.addView(DeleteFoodForMealButton);
    DeleteFoodForMealButton.setEnabled(false);
    if( !OwnMeals.get(GetCurrentMealIndex()).isEmpty()){
        DeleteFoodForMealButton.setEnabled(true);
    }

    DeleteFoodForMealButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DeleteFoodForChosenMeal();

        }
    });




}

private void DeleteFoodForChosenMeal(){
    if(OwnMeals.get(GetCurrentMealIndex()).size()>=1){
        for(Foods x: OwnMeals.get(GetCurrentMealIndex())){
            int Index;
            if(x.GetName().equals(DeleteFoodSpinner.getSelectedItem().toString()) ){
                Index= OwnMeals.get(GetCurrentMealIndex()).indexOf(x);
                OwnMeals.get(GetCurrentMealIndex()).remove(Index);
                Gson_Update_Data_Array_Of_Arrays_Foods(OwnMeals,MainActivity.OWNMEALS);

                DeleteFoodSpinnerArrayString.clear();
                for(Foods t:OwnMeals.get(GetCurrentMealIndex())){
                    DeleteFoodSpinnerArrayString.add(t.GetName());
                }
                DeleteFoodSpinner.setAdapter(DeleteFoodSpinnerArrayAdapter);
                UpdateTable();
                RefreshOpMenu();
                MealsSpinnerForMealOptions.setSelection(0);
                break;
            }
        }
    }
}


    private void LongDeleteFoodForChosenMeal(Foods FoodToDelete,int index){
        if(OwnMeals.get(index).size()>=1){
            for(Foods x: OwnMeals.get(index)){
                int FoodIndex;
                if(x.GetName().equals(FoodToDelete.GetName()) ){
                    FoodIndex= OwnMeals.get(index).indexOf(x);
                    OwnMeals.get(index).remove(FoodIndex);
                    Gson_Update_Data_Array_Of_Arrays_Foods(OwnMeals,MainActivity.OWNMEALS);

                    if(!DeleteFoodSpinnerArrayString.isEmpty()){
                        DeleteFoodSpinnerArrayString.clear();
                    }

                    for(Foods t:OwnMeals.get(index)){
                        DeleteFoodSpinnerArrayString.add(t.GetName());
                    }
                    if(DeleteFoodSpinner==null){
                        DeleteFoodSpinner=new Spinner(this);
                    }
                    DeleteFoodSpinner.setAdapter(DeleteFoodSpinnerArrayAdapter);
                    UpdateTable();
                    RefreshOpMenu();
                    MealsSpinnerForMealOptions.setSelection(0);
                    break;
                }
            }
        }
    }



private void AddFoodForChosenMeal(){
    Foods ChickenBreast= new Foods(100,165.0,31.0,3.6,0.0,"Chicken Breast","Meat");
    Foods NewFood=null;
        for(Foods x: ChickenBreast.GetFoodsArray()){
            if(x.GetName().equals(AddFoodSpinner.getSelectedItem().toString())){
                NewFood=new Foods(StringToInt(Get_FillAddFoodAmountForMealText),x.GetPer100grmCalories(),x.GetPer100grmProtins(),x.GetPer100grmFats(),x.GetPer100grmCarbs(),x.GetName(),x.GetType());

            }

        }
    if(NewFood !=null){
        OwnMeals.get(GetCurrentMealIndex()).add(NewFood);
        Gson_Update_Data_Array_Of_Arrays_Foods(OwnMeals,MainActivity.OWNMEALS);
        UpdateTable();
        RefreshOpMenu();
        MealsSpinnerForMealOptions.setSelection(0);
    }




}

private void UpdateTable(){
    int index=0;

    HorizontalScrollLinearLayout.removeAllViews();
    SpinnerArray1.clear();
    TotalCalories=0.0;
    TotalProtins=0.0;
    TotalCarbs=0.0;
    TotalFats=0.0;


    for(ArrayList<Foods> Meal:OwnMeals){

        SpinnerArray1.add("Meal "+String.valueOf(index+1));
        MealsSpinner.setAdapter(SpinnerArray1Adapter);
        //TableLayout TableInsideHorizontalScrollView=UsedTables.get(index);
       // TableInsideHorizontalScrollView.removeAllViews();
        TableLayout TableInsideHorizontalScrollView=new TableLayout(this);
        HorizontalScrollLinearLayout.addView(TableInsideHorizontalScrollView);



        TableRow Meal1= new TableRow(this);

        TableInsideHorizontalScrollView.addView(Meal1);
        TableRow.LayoutParams paramsMeal1 =new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        paramsMeal1.setMargins(0,0,0,0);
        TableRow.LayoutParams paramsMeal2 =new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);
        paramsMeal2.setMargins(0,50,0,0);
        TextView TitleMeal1=new TextView(this);
        TitleMeal1.setText("Meal"+ " " +String.valueOf(index+1) +":" );
        TitleMeal1.setBackgroundColor(-1);
        TitleMeal1.setGravity(Gravity.CENTER);
        if(index==0){
            TitleMeal1.setLayoutParams(paramsMeal1);
        }
        else{
            TitleMeal1.setLayoutParams(paramsMeal2);
        }
        Meal1.addView(TitleMeal1);





        GeneralInformationRow= new TableRow(this);
        TableInsideHorizontalScrollView.addView(GeneralInformationRow);
        LoadInformationToGeneralInformationRow();





        int FoodIndex=0;
        for (Foods Y: OwnMeals.get(index) ){



            TableRow InformationRow= new TableRow(this);
            InformationRow.setTag(FoodIndex);
            TableInsideHorizontalScrollView.addView(InformationRow);




            TextView InfoFoodName=new TextView(this);
            InfoFoodName.setText(Y.GetName()+ " ");
            InfoFoodName.setBackgroundColor(-1);
            InfoFoodName.setGravity(Gravity.CENTER);
            InfoFoodName.setLayoutParams(params1);
            ArrayList<Object> TempArray= new ArrayList<Object>();
            TempArray.add(Y);
            TempArray.add(OwnMeals.get(index));
            TempArray.add(index);
            InfoFoodName.setTag(TempArray);
            registerForContextMenu(InfoFoodName);
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

            FoodIndex++;
            TotalCalories+=Y.GetNetCalories();
            TotalProtins+=Y.GetNetProtins();
            TotalCarbs+=Y.GetNetCarbs();
            TotalFats+=Y.GetNetFats();

        }

        index++;

    }





    TableLayout TableInsideHorizontalScrollViewOverAll=new TableLayout(this);
    HorizontalScrollLinearLayout.addView(TableInsideHorizontalScrollViewOverAll);
    TableRow TotalPlanInfo= new TableRow(this);
    TableInsideHorizontalScrollViewOverAll.addView(TotalPlanInfo);




    TextView TotalCaloriesForPlan=new TextView(this);
    TotalCaloriesForPlan.setText("Total Calories For The Plan: "+String.valueOf(Math.round(TotalCalories * 100d) / 100d));
    TotalCaloriesForPlan.setBackgroundColor(-1);
    TotalCaloriesForPlan.setGravity(Gravity.CENTER);
    TotalCaloriesForPlan.setLayoutParams(params1);
    TotalPlanInfo.addView(TotalCaloriesForPlan);

    TextView TotalProtinsForPlan=new TextView(this);
    TotalProtinsForPlan.setText("Total Protins For The Plan: "+String.valueOf(Math.round(TotalProtins * 100d) / 100d));
    TotalProtinsForPlan.setBackgroundColor(-1);
    TotalProtinsForPlan.setGravity(Gravity.CENTER);
    TotalProtinsForPlan.setLayoutParams(params1);
    TotalPlanInfo.addView(TotalProtinsForPlan);

    TextView TotalCarbsForPlan=new TextView(this);
    TotalCarbsForPlan.setText("Total Carbs For The Plan: "+String.valueOf(Math.round(TotalCarbs* 100d) / 100d));
    TotalCarbsForPlan.setBackgroundColor(-1);
    TotalCarbsForPlan.setGravity(Gravity.CENTER);
    TotalCarbsForPlan.setLayoutParams(params1);
    TotalPlanInfo.addView(TotalCarbsForPlan);

    TextView TotalFatsForPlan=new TextView(this);
    TotalFatsForPlan.setText("Total Fats For The Plan: "+String.valueOf(Math.round(TotalFats * 100d) / 100d));
    TotalFatsForPlan.setBackgroundColor(-1);
    TotalFatsForPlan.setGravity(Gravity.CENTER);
    TotalFatsForPlan.setLayoutParams(params1);
    TotalPlanInfo.addView(TotalFatsForPlan);



}




private void LoadInformationToGeneralInformationRow(){
    params1 =new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
    params1.setMargins(2,2,2,2);
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



}


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.food_name_options,menu);
        TextView InfoFoodName= (TextView) v;
        InfoFoodNameTempArray=(ArrayList<Object>) InfoFoodName.getTag();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.DeleteFoodFragment:
                LongDeleteFoodForChosenMeal((Foods)InfoFoodNameTempArray.get(0),(int) InfoFoodNameTempArray.get(2));
                return true;
            case R.id.option2:

                return true;
            default:
                return super.onContextItemSelected(item);

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
    private int GetCurrentMealIndex(){
        int index=0;
        for (String x:SpinnerArray1){
            if(x.equals(MealsSpinner.getSelectedItem().toString())){
                break;
            }
            index++;
        }
        return (index);
    }

    private int GetCurrentFoodIndex(){
        int index=0;
        for (String x: AddFoodSpinnerArrayString){
            if(x.equals(AddFoodSpinner.getSelectedItem().toString())){
                break;
            }
            index++;
        }
        return (index);
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



}