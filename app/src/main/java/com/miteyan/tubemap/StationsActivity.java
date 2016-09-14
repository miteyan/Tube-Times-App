package com.miteyan.tubemap;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StationsActivity extends AppCompatActivity {



    List<Station> listStations = Collections.EMPTY_LIST;
    List<ListViewItem> listStationsItems = Collections.EMPTY_LIST;
    Button button;
    private String[] listStationsName;

    private void setListStation(List<Station> list){
        this.listStations=list;
        System.out.println("List set: " + list.size());
    }
    private void setList(List<ListViewItem> list){
        this.listStationsItems=list;
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
                    //pass info from location activity
                    Bundle bundle = getIntent().getExtras();
                    final double Lat = bundle.getDouble("lat");
                    final double Long = bundle.getDouble("long");
                    LatLng location = new LatLng(Lat, Long);

//                    LatLng location = new LatLng(51.531172, -0.129047);

                    NearestStations ns = new NearestStations(location);
                    List<ListViewItem> stationsList = ns.getStationsListItems();
                    setList(stationsList);
                    //return list view items
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        ListView listView = (ListView) findViewById(R.id.listView);

        button = (Button)findViewById(R.id.BUTTON);
        register();
    }

    public void populate(View view) {

        button.setVisibility(View.INVISIBLE);
        ListView listView = (ListView) findViewById(R.id.listView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.listitem,listStationsName);
//        assert listView != null;
//        listView.setAdapter(arrayAdapter);

        //Custom
        CustomListAdapter customListAdapter = new CustomListAdapter(this, listStationsItems);
        listView.setAdapter(customListAdapter);

    }


    private void register() {
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(StationsActivity.this,i+"",Toast.LENGTH_LONG ).show();
                listStationsItems.
                Intent intent = new Intent(StationsActivity.this,TubeActivity.class);
                startActivity(intent);
            }
        });
    }


}
