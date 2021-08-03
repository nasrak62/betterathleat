package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class QLiftRecord extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText Fill_BenchPress;
    private EditText Fill_Squat;
    private EditText Fill_ShoulderPress;
    private EditText Fill_Deadlift;
    private EditText Fill_PullUps;
    private EditText Fill_Dips;
    private String Get_BenchPress;
    private String Get_Squat;
    private String Get_ShoulderPress;
    private String Get_Deadlift;
    private String Get_PullUps;
    private String Get_Dips;
    private String Get_Values;
    private Button SubmitPR;
    private Double weight;
    private Button Next;
    private Button Main_Menu;
    private Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_lift_record);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        Fill_BenchPress= (EditText) findViewById(R.id.Fill_BenchPress);
        Fill_Squat= (EditText) findViewById(R.id.Fill_Squat);
        Fill_ShoulderPress= (EditText) findViewById(R.id.Fill_ShoulderPress);
        Fill_Deadlift= (EditText) findViewById(R.id.Fill_Deadlift);
        Fill_PullUps= (EditText) findViewById(R.id.Fill_PullUps);
        Fill_Dips= (EditText) findViewById(R.id.Fill_Dips);
        Next= (Button) findViewById(R.id.Next1);
        Next.setEnabled(false);
        SubmitPR=(Button) findViewById(R.id.PR_button);

        SubmitPR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Get_BenchPress=Fill_BenchPress.getText().toString();
                Get_Squat=Fill_Squat.getText().toString();
                Get_ShoulderPress=Fill_ShoulderPress.getText().toString();
                Get_Deadlift=Fill_Deadlift.getText().toString();
                Get_PullUps=Fill_PullUps.getText().toString();
                Get_Dips=Fill_Dips.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if ((radioId == R.id.Kg)&&(!Get_BenchPress.equals(""))&&(!Get_Squat.equals(""))&&(!Get_ShoulderPress.equals(""))&&(!Get_Deadlift.equals(""))&&(!Get_PullUps.equals(""))&&(!Get_Dips.equals("")))
                {

                    weight=Double.parseDouble( Get_BenchPress);
                    weight = 2.2 * weight;
                    Get_BenchPress = Double.toString((double) weight);
                    weight=Double.parseDouble( Get_Squat);
                    weight = 2.2 * weight;
                    Get_Squat = Double.toString((double) weight);
                    weight=Double.parseDouble( Get_ShoulderPress);
                    weight = 2.2 * weight;
                    Get_ShoulderPress = Double.toString((double) weight);
                    weight=Double.parseDouble( Get_Deadlift);
                    weight = 2.2 * weight;
                    Get_Deadlift = Double.toString((double) weight);
                    weight=Double.parseDouble( Get_PullUps);
                    weight = 2.2 * weight;
                    Get_PullUps = Double.toString((double) weight);
                    weight=Double.parseDouble( Get_Dips);
                    weight = 2.2 * weight;
                    Get_Dips = Double.toString((double) weight);

                }
                Get_Values=Get_BenchPress+","+Get_Squat+","+Get_ShoulderPress+","+Get_Deadlift+","+Get_PullUps+","+Get_Dips;
                updateData(Get_Values,MainActivity.LIFTREC);
                ResetProjectKeyElements();
                if ((radioGroup.getCheckedRadioButtonId()!=-1)&&(!Get_BenchPress.equals(""))&&(!Get_Squat.equals(""))&&(!Get_ShoulderPress.equals(""))&&(!Get_Deadlift.equals(""))&&(!Get_PullUps.equals(""))&&(!Get_Dips.equals("")))
                {
                    Next.setEnabled(true);
                    Toast toast = Toast.makeText(getApplicationContext(),"Info Updated " , Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        Fill_BenchPress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Next.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Fill_Squat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Next.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Fill_ShoulderPress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Next.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Fill_Deadlift.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Next.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Fill_PullUps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Next.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Fill_Dips.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Next.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) {

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


    public void checkButton(View v){

        int radioId= radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
    }

    public void openNextActivity(){
        Intent intentopenNextActivity= new Intent(this, QRepRange.class);
        startActivity(intentopenNextActivity);
        finish();
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();
    }
    public void openPrevActivity(){
        Intent intentopenPrevActivity= new Intent(this, QExp.class);
        startActivity(intentopenPrevActivity);
        finish();
    }
    public void updateData(String Value,String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public void ResetProjectKeyElements(){
        UpdateUserIfPossible();


    }
    private void UpdateUserIfPossible(){
        if(!get_shared_info_Gson_User( MainActivity.PASSUSER).GetName().equals("0")){
            User user=get_shared_info_Gson_User( MainActivity.PASSUSER);
            user.SetLiftRecord(convertStringToArray(get_shared_info_string(MainActivity.LIFTREC)));
            Gson_Update_Data_User(user,MainActivity.PASSUSER);
        }




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
}