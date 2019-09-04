package com.hensongeodata.technologies.hensonlocationlibrary.controller;

import android.content.Context;
import android.content.Intent;

public class BroadcastCall {

      public static String LOCSCAST_SERVICE = "com.hensongeodata.broadcast.location.service";

      public static void publishLocationService(Context context, String latitude, String longitude, String accuracy, String altitude, String provider){
            Intent intent = new Intent(LOCSCAST_SERVICE);
            intent.setAction(LOCSCAST_SERVICE);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            intent.putExtra("accuracy", accuracy);
            intent.putExtra("altitude", altitude);
            intent.putExtra("provider", provider);
            context.sendBroadcast(intent);
      }

}
