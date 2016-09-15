package com.miteyan.tubemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NearestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest);

        //get tubes from previous intent
        ArrayList<Tube> tubes = getIntent().getParcelableArrayListExtra("Tubes");
        int size= tubes.size();

        //Convert tubes object to listtubes objects and insert
        ArrayList<ListViewItemTubes> listStationsItemsTubes = new ArrayList<>(size);
        Tube currentTube;
        ListViewItemTubes currListTube;
        for (int i =0 ; i <size; i++) {
            currentTube = tubes.get(i);
            currListTube = new ListViewItemTubes(currentTube.getArrivalTime(),
                    currentTube.getLine(),
                    currentTube.getDestination(),
                    currentTube.getTimeTo(),
                    currentTube.getPlatform(),
                    currentTube.getCurrLocation());
            listStationsItemsTubes.add(i,currListTube);
            System.out.println("LIST ITEM CREATED");
        }
        //List view for tubes created
        ListView listViewTubes = (ListView) findViewById(R.id.listViewNearestStation);
        CustomListAdapterTubes customListAdapter = new CustomListAdapterTubes(NearestActivity.this, listStationsItemsTubes);
        assert listViewTubes != null;
        listViewTubes.setAdapter(customListAdapter);
    }
}
