package com.miteyan.tubemap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    TextView longitudeValueBest, latitudeValueBest;
    TextView longitudeValueGPS, latitudeValueGPS;
    TextView longitudeValueNetwork, latitudeValueNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //REGISTER TEXT VIEWS
        longitudeValueBest = (TextView) findViewById(R.id.longitudeValueBest);
        latitudeValueBest = (TextView) findViewById(R.id.latitudeValueBest);
        longitudeValueGPS = (TextView) findViewById(R.id.longitudeValueGPS);
        latitudeValueGPS = (TextView) findViewById(R.id.latitudeValueGPS);
        longitudeValueNetwork = (TextView) findViewById(R.id.longitudeValueNetwork);
        latitudeValueNetwork = (TextView) findViewById(R.id.latitudeValueNetwork);
        //REGISTER LOCATION MANAGER
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    //Show dialog to enable location
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
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
        if (!isLocationEnabled()) {
            showAlert();
            Toast.makeText(this,"Location off",Toast.LENGTH_SHORT).show();
        }
        return isLocationEnabled();
    }


    public void toggleGPSUpdates(View view) {

        if (!LocationEnabled()) {
            return;
        }
        Button button = (Button) view;

        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerGPS);
            button.setText(R.string.resume);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 60 * 1000, 15, locationListenerGPS);
        }
    }

    public void toggleNetworkUpdates(View view) {
        if (!LocationEnabled()) {
            return;}
        Button button = (Button) view;
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerNetwork);
            button.setText(R.string.resume);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5 * 60 * 1000, 15, locationListenerNetwork);
            button.setText(R.string.pause);
        }
    }

    public void toggleBestUpdates(View view) {
        if (!LocationEnabled()) {
            return;
        }
        Button button = (Button) view;
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerBest);
            button.setText(R.string.resume);
        } else {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(false);
            criteria.setSpeedRequired(false);
            criteria.setPowerRequirement(Criteria.ACCURACY_LOW);
            String provider = locationManager.getBestProvider(criteria, true);
            if (provider != null) {
                locationManager.requestLocationUpdates(provider, 5 * 60 * 1000, 15, locationListenerBest);
                button.setText(R.string.pause);
            }
        }
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            latitudeGPS = location.getLatitude();
            longitudeGPS = location.getLongitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueGPS.setText(longitudeGPS + "");
                    latitudeValueGPS.setText(latitudeGPS + "");
                    Toast.makeText(MainActivity.this, location.getProvider(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    private final LocationListener locationListenerNetwork = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitudeNetwork = location.getLatitude();
            longitudeNetwork = location.getLongitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueNetwork.setText(longitudeNetwork + "");
                    latitudeValueNetwork.setText(latitudeGPS + "");
                    Toast.makeText(MainActivity.this, "Network Provider update", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    private final LocationListener locationListenerBest = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitudeBest = location.getLatitude();
            longitudeBest = location.getLongitude();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    longitudeValueBest.setText(longitudeGPS + "");
                    latitudeValueBest.setText(latitudeGPS + "");
                    Toast.makeText(MainActivity.this, "Best Provider update", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };
}