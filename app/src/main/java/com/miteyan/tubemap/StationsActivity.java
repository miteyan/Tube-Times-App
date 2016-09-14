package com.miteyan.tubemap;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    List<ListViewItem> listStationsItems = Collections.EMPTY_LIST;
    Button button;

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

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ListView listView = (ListView) findViewById(R.id.listView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.listitem,listStationsName);
//        assert listView != null;
//        listView.setAdapter(arrayAdapter);
                //Custom
                CustomListAdapter customListAdapter = new CustomListAdapter(StationsActivity.this, listStationsItems);
                listView.setAdapter(customListAdapter);
            }
        }, 500);
        //DELAY ADDED TO ALLOW RESPONSE

    }

    public String getStationID(int i){
        String id =listStationsItems.get(i).getStationID();
        System.out.println(id);
        return id;
    }
    public List<String> getTubeLineList(int i){
        List<String> lines =listStationsItems.get(i).getTubeLineList();
        return lines;
    }

    private void register() {
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Toast.makeText(StationsActivity.this,i+"",Toast.LENGTH_LONG ).show();

                ListViewItem a = (ListViewItem) adapterView.getItemAtPosition(i);
                new Thread() {
                    @Override
                    public void run() {
//                        String stationID = StationID(i);
                        List<String> tubes = getTubeLineList(i);
                        print(tubes);
                        String stationID = getStationID(i);
                        TubeTimes times = null;
                        try {
                            times = new TubeTimes(tubes, stationID);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<Tube> list = times.getTubeTimes();
                            Log.v("Tag",""+list.size());

                            Intent intent = new Intent(StationsActivity.this,TubeActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("Tubes", (ArrayList<? extends Parcelable>) list);
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
    public static void print(List<String> a) {
        for (int i =0 ; i<a.size(); i++) {
            System.out.println(a.get(i));
        }
    }
    private void setList(List<ListViewItem> list){
        this.listStationsItems=list;
        System.out.println("List set: " + list.size());
    }

}
