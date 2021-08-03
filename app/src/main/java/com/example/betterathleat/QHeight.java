package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class QHeight extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText Fill_Height;
    private String Get_Height;
    private Button SubmitHeight;
    private Double Height;
    private Button Next;
    private Button Main_Menu;
    private Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_height);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        Fill_Height= (EditText) findViewById(R.id.Fill_Height);
        Next= (Button) findViewById(R.id.Next1);
        Next.setEnabled(false);
        SubmitHeight=(Button) findViewById(R.id.Height_button);
        SubmitHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Get_Height=Fill_Height.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if ((radioId == R.id.Cm)&&(!Get_Height.equals(""))) {
                    Height=Double.parseDouble( Get_Height);
                    Height =  Height/30.48;
                    Get_Height = Double.toString((double) Height);
                    updateData(Get_Height,MainActivity.HEIGHT);
                    ResetProjectKeyElements();
                }
                if ((radioGroup.getCheckedRadioButtonId()!=-1)&&(!Get_Height.equals("")))
                {
                    Next.setEnabled(true);
                    Toast toast = Toast.makeText(getApplicationContext(),"Info Updated " , Toast.LENGTH_SHORT);
                    toast.show();
                }
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

        Fill_Height.addTextChangedListener(new TextWatcher() {
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



    }






    public void checkButton(View v){

        int radioId= radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
    }

    public void openNextActivity(){
        Intent intentopenNextActivity= new Intent(this,Help.class);
        startActivity(intentopenNextActivity);
        finish();
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();
    }
    public void openPrevActivity(){
        Intent intentopenPrevActivity= new Intent(this, QWeight.class);
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
            user.SetHeight(StringToDouble(get_shared_info_string(MainActivity.HEIGHT)));
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


    public Double StringToDouble(String Key){

        return(Double.parseDouble(Key));

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