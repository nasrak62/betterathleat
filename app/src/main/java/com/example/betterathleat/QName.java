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
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static java.lang.Integer.parseInt;

public class QName extends AppCompatActivity {
    private Button Name_button;
    private Button Next;
    private Button Main_Menu;
    private Button Back;
    private EditText Name;
    private String Get_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_name);
        Name_button= (Button) findViewById(R.id.Name_button);
        Name= (EditText) findViewById(R.id.Fill_Name);
        Next= (Button) findViewById(R.id.Next1);
        Next.setEnabled(false);
        Name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name= (EditText) findViewById(R.id.Fill_Name);
                Get_name=Name.getText().toString();
                updateData(Get_name,MainActivity.NAME);
                ResetProjectKeyElements();
                if (!(Get_name.equals("")))
                {
                    Next.setEnabled(true);
                    Toast toast = Toast.makeText(getApplicationContext(),"Info Updated " , Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        Name.addTextChangedListener(new TextWatcher() {
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
                openNextQ();


            }
        });
        Back= (Button) findViewById(R.id.Back1);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain_Menu();


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
    public void openPrevQ(){
        Intent intentPrevQ= new Intent(this, QName.class);
        startActivity(intentPrevQ);
        finish();
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();
    }
    public void openNextQ(){
        Intent intentNextQ= new Intent(this, QAge.class);
        startActivity(intentNextQ);
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
            user.SetName(get_shared_info_string(MainActivity.NAME));
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