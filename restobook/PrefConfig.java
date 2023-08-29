package com.example.restobook;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefConfig {
    private static final String LIST_KEY = "list_key";
    public static void writeListInPref(Context context, ArrayList<Order> orderArrayList) {

        Gson gson = new Gson();
        String jsonString = gson.toJson(orderArrayList);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY,jsonString);
        editor.apply();
    }

    public static ArrayList<Order> readListFromPref(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_KEY,"");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Order>>() {} .getType();
        ArrayList<Order> orderArrayList = gson.fromJson(jsonString,type);
        return orderArrayList;
    }
}
