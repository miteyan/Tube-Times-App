package com.miteyan.tubemap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HomeActivity extends AppCompatActivity {

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 60 * 1000, 15, locationListenerGPS);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // instantiate the location manager
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // get the last know location from your location manager.
                Location loc= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                // now get the lat/lon from the location and do something with it.
                Intent intent = new Intent(HomeActivity.this, StationsActivity.class);
                intent.putExtra("lat",loc.getLatitude());
                intent.putExtra("long", loc.getLongitude());
                startActivity(intent);
            }
        });
    }
//    private final LocationListener locationListenerGPS = new LocationListener() {
//        @Override
//        public void onLocationChanged(final Location location) {
//            double latitudeGPS = location.getLatitude();
//            double longitudeGPS = location.getLongitude();
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
////                    longitudeValueGPS.setText(longitudeGPS + "");
////                    latitudeValueGPS.setText(latitudeGPS + "");
//                    Intent intent = new Intent(HomeActivity.this, StationsActivity.class);
//                    intent.putExtra("lat",latitudeGPS);
//                    intent.putExtra("long", longitudeGPS);
////            intent.putExtra("long",longitudeGPS);
//                    if (longitudeGPS!=0 && latitudeGPS!=0)
//                    {startActivity(intent);}
//
//                }
//            });
//        }
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {}
//        @Override
//        public void onProviderEnabled(String s) {
//
//        }
//        @Override
//        public void onProviderDisabled(String s) {}};
//

    public void nearest(View view) {

        new Thread() {
            @Override
            public void run() {
                try {

                    Location locationL = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    double latitudeGPS = locationL.getLatitude();
                    double longitudeGPS = locationL.getLongitude();
                    LatLng location = new LatLng(latitudeGPS,longitudeGPS);
                    NearestStations nearestStation = new NearestStations(location,true);
                    Station nearest  =nearestStation.getStations().get(0);
                    TubeTimes t = new TubeTimes(nearest.getTubes(),nearest.getStationID());
                    ArrayList<Tube> tubes = (t.getTubeTimes());
                    //sort list of times
                    Collections.sort(tubes, new Comparator<Tube>() {
                        @Override
                        public int compare(Tube tube, Tube t1) {
                            return tube.getDate().compareTo(t1.getDate());
                        }
                    });

                    Intent intent = new Intent(HomeActivity.this,NearestActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("Tubes", (ArrayList<? extends Parcelable>) tubes);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void tube(View view) {
        Intent intent = new Intent(this, TubeMap.class);
        startActivity(intent);
    }
}