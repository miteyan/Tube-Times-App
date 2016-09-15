package com.miteyan.tubemap;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TubeMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        WebView webView = (WebView) findViewById(R.id.webView);
        assert webView != null;
        webView.loadDataWithBaseURL("file:///android_asset/", "<img src='tubemap.jpg' />", "text/html", "utf-8", null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread() {
                    @Override
                    public void run() {
                        try {

                            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                            ;
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

                            Intent intent = new Intent(TubeMap.this,NearestActivity.class);
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
        });
    }

}
