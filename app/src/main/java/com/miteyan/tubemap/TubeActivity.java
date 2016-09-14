package com.miteyan.tubemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class TubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube);

        ArrayList<Tube> tubes = getIntent().getParcelableArrayListExtra("Tubes");
        int size= tubes.size();
        //Convert tubes object to listtubes objects
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
        }
        ListView listViewTubes = (ListView) findViewById(R.id.listViewTubes);
        CustomListAdapterTubes customListAdapter = new CustomListAdapterTubes(TubeActivity.this, listStationsItemsTubes);
        listViewTubes.setAdapter(customListAdapter);

        //sort on time to
//        String s = "";
//        for (int i =0 ; i<t.size(); i++) {
//            s+=t.get(i).toString();
//        }
//        tubes = (TextView) findViewById(R.id.TUBES);
//        tubes.setText(s);

    }
}
