package com.hensongeodata.technologies.hensonlocationlibrary.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {

      private static SharedPrefManager mInstance;
      private static Context mCtx;

      private static final String SHARED_PREF_NAME = "locationservice";
      private static final String KEY_LATITUDE = "keylatitude";
      private static final String KEY_LONGITUDE = "keylongitude";

      public SharedPrefManager(Context context) {
            mCtx = context;
      }

      public static synchronized SharedPrefManager getInstance(Context context){
            if (mInstance == null){
                  mInstance = new SharedPrefManager(context);
            }
            return mInstance;
      }

      public boolean latLon(String lati, String longi){
            SharedPreferences preferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_LATITUDE, lati);
            editor.putString(KEY_LONGITUDE, longi);
            editor.apply();

            return true;
      }

      public List<String> getLatLon(){
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            List<String> latlon = new ArrayList<>();
            latlon.add(sharedPreferences.getString(KEY_LATITUDE, null));
            latlon.add(sharedPreferences.getString(KEY_LONGITUDE, null));

            return latlon;
      }

}
