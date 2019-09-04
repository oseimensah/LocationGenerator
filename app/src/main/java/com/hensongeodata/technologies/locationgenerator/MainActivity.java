package com.hensongeodata.technologies.locationgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.hensongeodata.technologies.hensonlocationlibrary.controller.BroadcastCall;
import com.hensongeodata.technologies.hensonlocationlibrary.service.LocationService;
import com.hensongeodata.technologies.locationgenerator.model.LatLong;
import com.karumi.dexter.BuildConfig;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

      private static String TAG = MainActivity.class.getSimpleName();

      TextView latlonlocation;
      private IntentFilter intentFilter;
      private LocationReceiver locationBroadcastReceiver;
      private LatLong latLon;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            latlonlocation = findViewById(R.id.lonlat);
            intentFilter = new IntentFilter(BroadcastCall.LOCSCAST_SERVICE);
            locationBroadcastReceiver = new LocationReceiver();
            latLon = new LatLong();

            checkPermission();

            registerReceiver(locationBroadcastReceiver, intentFilter);
            startService(new Intent(getApplicationContext(), LocationService.class));


      }

      public void checkPermission(){

            // Requesting ACCESS_FINE_LOCATION using Dexter library
            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                          @Override
                          public void onPermissionGranted(PermissionGrantedResponse response) {
                                Log.v(TAG, "onPermissionGranted: Permission is successfully granted");
                          }

                          @Override
                          public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                      // open device settings when the permission is
                                      // denied permanently
                                      openSettings();
                                }
                          }

                          @Override
                          public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                          }
                    }).check();

      }

      private boolean checkPermissions() {
            int permissionState = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            return permissionState == PackageManager.PERMISSION_GRANTED;
      }

      private void openSettings() {

            checkPermissions();
            Intent intent = new Intent();
            intent.setAction(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package",
                    BuildConfig.APPLICATION_ID, null);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
      }

      class LocationReceiver extends BroadcastReceiver{

            @Override
            public void onReceive(Context context, Intent intent) {

                  if (intent.getAction().equalsIgnoreCase(BroadcastCall.LOCSCAST_SERVICE)){
                        String latitude = intent.getStringExtra("latitude");
                        String longitude = intent.getStringExtra("longitude");
                        String altitude = intent.getStringExtra("accuracy");
                        String accuracy = intent.getStringExtra("altitude");
                        String provider = intent.getStringExtra("provider");

                        latlonlocation.setText(latitude + " , " + longitude + "\n\nAltitude is: " + altitude + "; and Accuracy is: " + accuracy + "\n\nProvided by: " + provider );
                  }

            }
      }
}
