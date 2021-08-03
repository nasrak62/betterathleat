package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class QRepRange extends AppCompatActivity {
    private Button Next;
    private Button Main_Menu;
    private Button Back;
    private Button SubmitRepRange;
    private String UsedRep;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_rep_range);


        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        SubmitRepRange=(Button) findViewById(R.id.Rep_button);
        Next= (Button) findViewById(R.id.Next1);
        Next.setEnabled(false);
        SubmitRepRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                switch (radioId){
                    case R.id.OneToThree:
                        UsedRep="3";
                        break;
                    case R.id.FourToSix:
                        UsedRep="5";
                        break;
                    case R.id.SevenToNine:
                        UsedRep="8";
                        break;
                    case R.id.TentoTwelve:
                        UsedRep="10";
                        break;
                    case R.id.MoreThenTwelve:
                        UsedRep="15";
                        break;

                }
                updateData(UsedRep,MainActivity.RANGES);
                ResetProjectKeyElements();
                if (radioGroup.getCheckedRadioButtonId()!=-1)
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
    }
    public void openNextActivity(){
        Intent intentopenNextActivity= new Intent(this, QTrainingStyle.class);
        startActivity(intentopenNextActivity);
        finish();
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openPrevActivity(){
        Intent intentopenPrevActivity= new Intent(this, QLiftRecord.class);
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
            user.SetMostUsedRepRange(StringToInt(get_shared_info_string(MainActivity.RANGES)));
            Gson_Update_Data_User(user,MainActivity.PASSUSER);
        }




    }




    public int StringToInt(String Key){
        return(Integer.parseInt(Key));

    }





    public String get_shared_info_string( final String Key){
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE);
        if((sharedPreferences.getString(Key,"0" ).equals("")))
        {
            updateData("0",Key);
        }
        return(sharedPreferences.getString(Key,"0" ));
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