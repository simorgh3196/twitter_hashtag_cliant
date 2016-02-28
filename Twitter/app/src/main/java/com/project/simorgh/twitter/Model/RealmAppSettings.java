package com.project.simorgh.twitter.Model;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class RealmAppSettings {

    public static void updatePreference(Context context, String keyStr, List<String> values) {

        JSONArray array = new JSONArray();
        for (int i = 0, length = values.size(); i < length; i++) {
            try {
                array.put(i, values.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SharedPreferences.Editor editor
                = context.getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).edit();

        editor.putString(keyStr, array.toString());  //key名を"list"としてシリアライズ化したデータを保存
        editor.commit();
    }

    public List<String> getFromPreference(Context context, String keyStr) {

        Bundle bundle = new Bundle();  //保存用のバンドル
        Map<String, ?> prefKV
                = context.getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).getAll();
        Set<String> keys = prefKV.keySet();
        for(String key : keys){
            Object value = prefKV.get(key);
            if(value instanceof String){
                bundle.putString(key, (String) value);
            }else if(value instanceof Integer){
                // …略
            }
        }

        String stringList = bundle.getString(keyStr);  //key名が"list"のものを取り出す
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(stringList);
            for(int i = 0, length = array.length(); i < length; i++){
                list.add(array.optString(i));
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return list;
    }
}