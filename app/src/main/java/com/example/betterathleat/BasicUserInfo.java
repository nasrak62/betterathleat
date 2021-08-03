package com.example.betterathleat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class BasicUserInfo extends AppCompatActivity {
    private EditText Name;
    private String Get_name;
    private ImageButton Finish_Button;
    private String Finish;
    private EditText Age;
    private String Get_age;
    private RadioGroup radioGroupWeight;
    private RadioButton radioButtonWeight;
    private EditText Fill_Weight;
    private String Get_weight;
    private Double weight;
    private RadioGroup radioGroupHeight;
    private RadioButton radioButtonHeight;
    private EditText Fill_Height;
    private String Get_Height;
    private Double Height;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_user_info);
        Name= (EditText) findViewById(R.id.Fill_Name);
        Age= (EditText) findViewById(R.id.FillAge);
        radioGroupWeight= (RadioGroup) findViewById(R.id.radioGroupWeight);
        Fill_Weight= (EditText) findViewById(R.id.Fill_Weight);
        radioGroupHeight= (RadioGroup) findViewById(R.id.radioGroupHeight);
        Fill_Height= (EditText) findViewById(R.id.Fill_Height);
        this.context = getApplicationContext();


        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Name= (EditText) findViewById(R.id.Fill_Name);
                Get_name=Name.getText().toString();
                FunctionsToSaveInMemory.updateData(Get_name,MainActivity.NAME,context.getApplicationContext());




            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Age= (EditText) findViewById(R.id.FillAge);
                Get_age=Age.getText().toString();
                FunctionsToSaveInMemory.updateData(Get_age,MainActivity.AGE,context.getApplicationContext());




            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Fill_Weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Get_weight=Fill_Weight.getText().toString();

                int radioId = radioGroupWeight.getCheckedRadioButtonId();
                radioButtonWeight = findViewById(radioId);
                if ((radioId == R.id.Kg)&&(!Get_weight.equals(""))) {
                    weight=Double.parseDouble( Get_weight);
                    weight = 2.2 * weight;
                    Get_weight = Double.toString((double) weight);
                }
                FunctionsToSaveInMemory.updateData(Get_weight,MainActivity.WEIGHT,context.getApplicationContext());


            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Fill_Height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Get_Height=Fill_Height.getText().toString();

                int radioId = radioGroupHeight.getCheckedRadioButtonId();
                radioButtonHeight = findViewById(radioId);
                if ((radioId == R.id.Cm)&&(!Get_Height.equals(""))) {
                    Height=Double.parseDouble( Get_Height);
                    Height =  Height/30.48;
                    Get_Height = Double.toString((double) Height);
                    FunctionsToSaveInMemory.updateData(Get_Height,MainActivity.HEIGHT,context.getApplicationContext());

                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Finish_Button= (ImageButton) findViewById(R.id.Finish_button);

        Finish_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((Get_name.equals("")))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Use Valid Name. " , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if ((Get_age.equals("")) || (parseInt(Get_age)<9) || (parseInt(Get_age)>120))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Use Valid Age. " , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if ((radioGroupWeight.getCheckedRadioButtonId()==-1)|| (Get_weight.equals("")))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Use Valid Weight And Choose Weight Scale. " , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if ((radioGroupHeight.getCheckedRadioButtonId() ==-1) || (Get_Height.equals("")))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Use Valid Height And Choose Height Scale. " , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    FunctionsToSaveInMemory.updateData("1",MainActivity.FINISHED,context.getApplicationContext());
                    ResetProjectKeyElements();
                    openMain_Menu();

                }




            }
        });

    }

    public void ResetProjectKeyElements(){
        FunctionsToSaveInMemory.UpdateUserIfPossible(MainActivity.NAME,this.context);
        FunctionsToSaveInMemory.UpdateUserIfPossible(MainActivity.AGE,this.context);
        FunctionsToSaveInMemory.UpdateUserIfPossible(MainActivity.WEIGHT,this.context);
        FunctionsToSaveInMemory.UpdateUserIfPossible(MainActivity.HEIGHT,this.context);
        FunctionsToSaveInMemory.UpdateUserIfPossible(MainActivity.FINISHED,this.context);
    }
    public void openMain_Menu(){
        Intent intentMain_Menu= new Intent(this,MainActivity.class);
        startActivity(intentMain_Menu);
        finish();
    }
}