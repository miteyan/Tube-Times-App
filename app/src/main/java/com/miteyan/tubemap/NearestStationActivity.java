package com.miteyan.tubemap;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NearestStationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LatLng location = new LatLng(51,-1);
    private StationCardAdapter adapter;
    List<Station> listStations = Collections.EMPTY_LIST;
    Button button;
    private void setList(List<Station> list){
        this.listStations=list;
        System.out.println("List set: " + list.size());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_stations);

        new Thread() {
            @Override
            public void run() {
                try {
                    NearestStations ns = new NearestStations(location);
                    List<Station> stationsList = ns.getStations();
                    setList(stationsList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void onClickGetStations(View view) throws IOException {

        //Initialise recycle view
        button = (Button) findViewById(R.id.getStationsButton);
        recyclerView = (RecyclerView) findViewById(R.id.stationRecyclerView);
        try {
            adapter = new StationCardAdapter(this, loadStations(listStations));
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static List<stationCardInfo> loadStations(List<Station> nearest) throws IOException {
        int numStations = nearest.size();
        List<stationCardInfo> stations = Collections.emptyList();
        for (int i = 0; i < numStations; i++) {
            stationCardInfo info = new stationCardInfo(nearest.get(i).getName(),
                    nearest.get(i).getTubeLines(),
                    nearest.get(i).getDistance());
            stations.add(info);
        }
        return stations;
    }


}