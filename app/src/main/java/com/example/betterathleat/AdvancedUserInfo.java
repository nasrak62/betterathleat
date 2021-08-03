package com.example.betterathleat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

public class AdvancedUserInfo extends AppCompatActivity {
    private Button Main_Menu;
    private Button Menu_Question0;
    private Button Menu_Question1;
    private Button Menu_Question2;
    private Button Menu_Question3;
    private Button Menu_QuestionNew4;
    private Button Menu_Question4;
    private Button Menu_Question5;
    private Button Menu_Question6;
    private Button Menu_Question7;
    private Button Menu_Question8;
    private Button Menu_Question9;
    private Button Menu_Question10;
    private Button Menu_Question11;
    private Button Menu_Question12;
    private Button Menu_Question13;
    private LinearLayout Menu_Question0LinearLayout;
    private LinearLayout Menu_Question1LinearLayout;
    private LinearLayout Menu_Question2LinearLayout;
    private LinearLayout Menu_Question3LinearLayout;
    private LinearLayout Menu_QuestionNew4LinearLayout;
    private LinearLayout Menu_Question4LinearLayout;
    private LinearLayout Menu_Question5LinearLayout;
    private LinearLayout Menu_Question6LinearLayout;
    private LinearLayout Menu_Question7LinearLayout;
    private LinearLayout Menu_Question8LinearLayout;
    private LinearLayout Menu_Question9LinearLayout;
    private LinearLayout Menu_Question10LinearLayout;
    private LinearLayout Menu_Question11LinearLayout;
    private LinearLayout Menu_Question12LinearLayout;
    private LinearLayout Menu_Question13LinearLayout;

    private String Help;
    private String TrainingTime;
    private KenBurnsView kenBurnsView;
    private boolean moving = true;
    private int kenBurnscount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_menu);





        Menu_Question0=(Button) findViewById(R.id.Menu_Question0);
        Menu_Question1=(Button) findViewById(R.id.Menu_Question1);
        Menu_Question2=(Button) findViewById(R.id.Menu_Question2);
        Menu_Question3=(Button) findViewById(R.id.Menu_Question3);
        Menu_QuestionNew4=(Button) findViewById(R.id.Menu_QuestionNew4);
        Menu_Question4=(Button) findViewById(R.id.Menu_Question4);
        Menu_Question5=(Button) findViewById(R.id.Menu_Question5);
        Menu_Question6=(Button) findViewById(R.id.Menu_Question6);
        Menu_Question7=(Button) findViewById(R.id.Menu_Question7);
        Menu_Question8=(Button) findViewById(R.id.Menu_Question8);
        Menu_Question9=(Button) findViewById(R.id.Menu_Question9);
        Menu_Question10=(Button) findViewById(R.id.Menu_Question10);
        Menu_Question11=(Button) findViewById(R.id.Menu_Question11);
        Menu_Question12=(Button) findViewById(R.id.Menu_Question12);
        Menu_Question13=(Button) findViewById(R.id.Menu_Question13);
        Menu_Question0LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question0LinearLayout);
        Menu_Question1LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question1LinearLayout);
        Menu_Question2LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question2LinearLayout);
        Menu_Question3LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question3LinearLayout);
        Menu_QuestionNew4LinearLayout=(LinearLayout) findViewById(R.id.Menu_QuestionNew4LinearLayout);
        Menu_Question4LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question4LinearLayout);
        Menu_Question5LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question5LinearLayout);
        Menu_Question6LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question6LinearLayout);
        Menu_Question7LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question7LinearLayout);
        Menu_Question8LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question8LinearLayout);
        Menu_Question9LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question9LinearLayout);
        Menu_Question10LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question10LinearLayout);
        Menu_Question11LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question11LinearLayout);
        Menu_Question12LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question12LinearLayout);
        Menu_Question13LinearLayout=(LinearLayout) findViewById(R.id.Menu_Question13LinearLayout);
        Help=get_shared_info_string( MainActivity.HELP);
        TrainingTime=get_shared_info_string( MainActivity.TRAININGTIME);

        kenBurnsView = findViewById(R.id.kView);
        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(5000, interpolator);
        kenBurnsView.setTransitionGenerator(generator);
        kenBurnsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (moving) {
                    kenBurnsView.pause();
                    moving = false;
                }
                else if (!moving && kenBurnscount<=2) {
                    kenBurnsView.resume();
                    moving = true;
                    kenBurnscount++;
                }

            }
        });


        Menu_Question0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question0();






            }
        });
        Menu_Question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question1();






            }
        });
        Menu_Question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question2();






            }
        });
        Menu_Question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question3();






            }
        });
        Menu_QuestionNew4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_QuestionNew4();






            }
        });
        Menu_Question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question4();






            }
        });
        Menu_Question5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question5();






            }
        });
        Menu_Question6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question6();






            }
        });
        Menu_Question7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question7();






            }
        });
        Menu_Question8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question8();






            }
        });
        Menu_Question9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question9();






            }
        });
        Menu_Question10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question10();






            }
        });
        Menu_Question11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question11();






            }
        });
        Menu_Question12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question12();






            }
        });
        Menu_Question13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu_Question13();






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





    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();

    }

    public void openMenu_Question13(){
        Intent intentMain_Menu= new Intent(this, QLikedFoods.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question12(){
        Intent intentMain_Menu= new Intent(this, QFoodIdeology.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question11(){
        Intent intentMain_Menu= new Intent(this, QMotivation_Seriousness.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question10(){
        Intent intentMain_Menu= new Intent(this, QPastProgramNum.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question9(){

        Intent intentMain_Menu= new Intent(this, QWorkOutsPerWeek.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question8(){
        Intent intentMain_Menu= new Intent(this, QTrainingStyle.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question7(){
        Intent intentMain_Menu= new Intent(this, QRepRange.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question6(){
        Intent intentMain_Menu= new Intent(this, QLiftRecord.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question5(){
        Intent intentMain_Menu= new Intent(this, QExp.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_QuestionNew4(){
        Intent intentMain_Menu= new Intent(this,Help.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question4(){
        Intent intentMain_Menu= new Intent(this, QGoal.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question3(){
        Intent intentMain_Menu= new Intent(this, QHeight.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question2(){
        Intent intentMain_Menu= new Intent(this, QWeight.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question1(){
        Intent intentMain_Menu= new Intent(this, QAge.class);
        startActivity(intentMain_Menu);
        finish();

    }
    public void openMenu_Question0(){
        Intent intentMain_Menu= new Intent(this, QName.class);
        startActivity(intentMain_Menu);
        finish();

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


}