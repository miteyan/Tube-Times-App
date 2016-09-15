package com.miteyan.tubemap;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StationsActivity extends AppCompatActivity {

    private List<ListViewItemStations> listStationsItems = Collections.EMPTY_LIST;
    private Button button;
    private long STATIONAPIDELAY=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        new Thread() {
            @Override
            public void run() {
                try {
                    //pass info from location activity
                    Bundle bundle = getIntent().getExtras();
                    LatLng location = new LatLng(51.531172, -0.129047);
                    NearestStations ns = new NearestStations(location);
                    List<ListViewItemStations> stationsList = ns.getStationsList();
                    setList(stationsList);
                    //return list view items
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setDivider(new ColorDrawable(this.getResources().getColor(R.color.colorAccent)));   //0xAARRGGBB
        listView.setDividerHeight(2);
        button = (Button)findViewById(R.id.BUTTON);
        register();
        populate(button);
    }

    //generate and insert stations into list view
    public void populate(View view) {
        button.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ListView listView = (ListView) findViewById(R.id.listView);
                //Custom list adapter
                CustomListAdapterStations customListAdapterStations = new CustomListAdapterStations(StationsActivity.this, listStationsItems);
                listView.setAdapter(customListAdapterStations);
            }
        }, STATIONAPIDELAY);
        //DELAY ADDED TO ALLOW API RESPONSE

    }

    private void register() {
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //list item clicked on
                final ListViewItemStations current = (ListViewItemStations) adapterView.getItemAtPosition(i);
                Toast.makeText(StationsActivity.this,current.getStationName()+"",Toast.LENGTH_LONG ).show();

                new Thread() {
                    @Override
                    public void run() {
//                        String stationID = StationID(i);

                        List<String> tubes = current.getTubeLineList();
//                                getTubeLineList(i);
//                        print(tubes);
                        String stationID = current.getStationID();
//                                getStationID(i);
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

    private void setList(List<ListViewItemStations> list){
        this.listStationsItems=list;
        System.out.println("List set: " + list.size());
    }

}
