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

    private void setListStation(List<Station> list){
        this.listStations=list;
        System.out.println("List set: " + list.size());
    }
    private void setList(List<ListViewItem> list){
        this.listStationsItems=list;
        System.out.println("List set: " + list.size());
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
                    List<ListViewItem> stationsList = ns.getStationsList();
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

    public String getStationID(int i){
        String id =listStationsItems.get(i).getStationID();
        System.out.println(id);
        return id;
    }

    private void register() {
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            private int index;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(StationsActivity.this,i+"",Toast.LENGTH_LONG ).show();

                ListViewItem a = (ListViewItem) adapterView.getItemAtPosition(i);
                System.out.println(a.getStationID()+ "LLLLLLLLLLLLLLL");
                new Thread() {
                    @Override
                    public void run() {
                        int i = index;
//                        String stationID = StationID(i);
                        String stationID = getStationID(i);
                        TubeTimes times = new TubeTimes("jubilee", stationID);
                        try {
                            List<Tube> list =times.getTimes();
                            String s = "String";
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }.start();

                Intent intent = new Intent(StationsActivity.this,TubeActivity.class);
//                intent.putExtra("String",s);
                startActivity(intent);
            }


        });
    }


}
