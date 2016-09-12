package com.miteyan.tubemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StationsActivity extends AppCompatActivity {

    LatLng location = new LatLng(51.5333, 0.1322);
    List<Station> listStations = Collections.EMPTY_LIST;
    Button button;
    private String[] listStationsName;

    private void setList(List<Station> list){
        this.listStations=list;
        System.out.println("List set: " + list.size());
    }
    private void setList(String[] list){
        this.listStationsName=list;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations2);

        new Thread() {
            @Override
            public void run() {
                try {
                    NearestStations ns = new NearestStations(location);
                    String[] stationsList = ns.getStationsString();
                    setList(stationsList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        ListView listView = (ListView) findViewById(R.id.listView);

        button = (Button)findViewById(R.id.BUTTON);
    }

    public void populate(View view) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.listitem,listStationsName);

        ListView listView = (ListView) findViewById(R.id.listView);
        assert listView != null;
        listView.setAdapter(arrayAdapter);
    }

}
