package com.example.betterathleat;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FunctionsToSaveInMemory {

    public static User get_shared_info_Gson_User(final String Key, Context context){
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        User Tempuser=new User("0",1.0,1.0,1,"0","0",1.0,"0",1,1, new String[]{"1", "1", "1", "1", "1"},1,1,"0",new String[]{"1", "1", "1", "1", "1"},false);
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

           updateData("0", Key,context);
            return(Tempuser);
        }
        Type type = new TypeToken<User>(){}.getType();
        Tempuser = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(Tempuser);
    }

    public static void Gson_Update_Data_User(User Value,String Key,Context context){
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }
    public static void updateData(String Value,String Key,Context context){
        Gson gson=new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.SHARED_PREFS, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key,Value);
        editor.apply();

    }
    public static String get_shared_info_string( final String Key,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.SHARED_PREFS, context.MODE_PRIVATE);
        if((sharedPreferences.getString(Key,"0" ).equals("")))
        {
            updateData("0",Key,context);
        }
        return(sharedPreferences.getString(Key,"0" ));
    }
    public static int StringToInt(String Key){
        return(Integer.parseInt(Key));

    }

    public static Double StringToDouble(String Key){

        return(Double.parseDouble(Key));

    }
    public static Boolean StringToBoolean(String Key){
        if (Key.equals("1"))
        {

            return(true);
        }
        else
        {

            return(false);
        }


    }
    public static String[] convertStringToArray(String str){

        if(str.equals("0")){
            String[] arr1={"0", "0", "0", "0", "0", "0"};
            return(arr1) ;
        }
        else {
            String[] arr = str.split(",");
            return arr;
        }


    }

    public static void Gson_Empty_UpdateData(String Value,String Key,Context context){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(Key, Value);
        prefsEditor.commit();

    }
    public static void Gson_Update_Data_Exercies_Array(ArrayList<Exercise> Value,String Key,Context context){
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public static ArrayList<Exercise> get_shared_info_Gson_Exercise_Array( final String Key,Context context){
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<Exercise> ExercisesForWorkOut= new ArrayList<Exercise>();
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

            return(ExercisesForWorkOut);
        }
        Type type = new TypeToken<ArrayList<Exercise>>(){}.getType();
        ExercisesForWorkOut = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(ExercisesForWorkOut);
    }




    public static void Gson_Update_Data_String_Array(ArrayList<String> Value,String Key,Context context){
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        String json = gson.toJson(Value);
        prefsEditor.putString(Key, json);
        prefsEditor.commit();
    }

    public static ArrayList<String> get_shared_info_Gson_String_Array(final String Key,Context context){
        Gson gson=new Gson();
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        ArrayList<String> RememberDays= new ArrayList<String>();
        if((appSharedPrefs.getString(Key, "0").equals("0")||appSharedPrefs.getString(Key, "0").equals("")))
        {

            return(RememberDays);
        }
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        RememberDays = gson.fromJson(appSharedPrefs.getString(Key, "0"), type);
        return(RememberDays);
    }




    public static void UpdateFoodsArray(ArrayList<Foods> PersonalizedFoodsArray, Context context){
        String Temp="";
        for(Foods x: PersonalizedFoodsArray) {
            Temp+= x.GetName();
            if(PersonalizedFoodsArray.indexOf(x)!= PersonalizedFoodsArray.size()-1){
                Temp+= ",";
            }
        }
        updateData(Temp,MainActivity.LIKEDFOODS,context);
    }

    public static void UpdateUserIfPossible(String UserInfo,Context context) {
        if (!get_shared_info_Gson_User(MainActivity.PASSUSER, context).GetName().equals("0")) {
            User user = get_shared_info_Gson_User(MainActivity.PASSUSER, context);
            user.SetName(get_shared_info_string(MainActivity.NAME,context.getApplicationContext()));
            user.SetAge(FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.AGE,context.getApplicationContext())));
            user.SetWeight(FunctionsToSaveInMemory.StringToDouble(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WEIGHT,context.getApplicationContext())));
            user.SetHeight(StringToDouble(get_shared_info_string(MainActivity.HEIGHT,context.getApplicationContext())));
            user.SetHelp(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.HELP,context.getApplicationContext()));
            user.SetPurpose(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PURPOSE,context.getApplicationContext()));
            user.SetTrainingTime(FunctionsToSaveInMemory.StringToDouble(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGTIME,context.getApplicationContext())));
            user.SetTrainingStyle(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.TRAININGSTYLE,context.getApplicationContext()));
            user.SetWorkOutPerWeek(FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.WORKOUTNUM,context.getApplicationContext())));
            user.SetTrainingSeriousness(FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.SERIOUSNESS,context.getApplicationContext())));
            user.SetLiftRecord(FunctionsToSaveInMemory.convertStringToArray(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIFTREC,context.getApplicationContext())));
            user.SetMostUsedRepRange(FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.RANGES,context.getApplicationContext())));
            user.SetNumberOfAccomplishedPrograms(FunctionsToSaveInMemory.StringToInt(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.PROGRAMS,context.getApplicationContext())));
            user.SetFoodIdeology(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FOODIDEOLOGY,context.getApplicationContext()));
            user.SetLikedFoods( FunctionsToSaveInMemory.convertStringToArray(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.LIKEDFOODS,context.getApplicationContext())));
            user.SetFinish(FunctionsToSaveInMemory.StringToBoolean(FunctionsToSaveInMemory.get_shared_info_string(MainActivity.FINISHED,context.getApplicationContext())));
            Gson_Update_Data_User(user, MainActivity.PASSUSER, context);
        }

    }
}
