package com.miteyan.tubemap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;
    TextView longitudeValueGPS, latitudeValueGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //REGISTER TEXT VIEWS
//        longitudeValueGPS = (TextView) findViewById(R.id.longitudeValueGPS);
//        latitudeValueGPS = (TextView) findViewById(R.id.latitudeValueGPS);
        //REGISTER LOCATION MANAGER
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    //Show dialog to enable location
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Location is disabled")
                .setPositiveButton(("Turn Location On"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent locationSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(locationSettings);
                    }
                })
                .setNegativeButton(("Cancel"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        dialog.show();
    }

    public boolean LocationEnabled() {//show dialog if disabled location
        if (!isLocationEnabled()) {showAlert();}
        return isLocationEnabled();
    }


    public void toggleGPSUpdates(View view) {
        if (!LocationEnabled()) {return;}
        Button button = (Button) view;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 60 * 1000, 15, locationListenerGPS);

    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            latitudeGPS = location.getLatitude();
            longitudeGPS = location.getLongitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    longitudeValueGPS.setText(longitudeGPS + "");
//                    latitudeValueGPS.setText(latitudeGPS + "");
                    Intent intent = new Intent(HomeActivity.this, StationsActivity.class);
                    intent.putExtra("lat",latitudeGPS);
                    intent.putExtra("long", longitudeGPS);
//            intent.putExtra("long",longitudeGPS);
                    if (longitudeGPS!=0 && latitudeGPS!=0)
                    {startActivity(intent);}

                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}
        @Override
        public void onProviderEnabled(String s) {}
        @Override
        public void onProviderDisabled(String s) {}};

}