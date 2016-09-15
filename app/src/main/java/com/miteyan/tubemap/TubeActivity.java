package com.miteyan.tubemap;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class TubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube);

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
        }
        //List view for tubes created
        ListView listViewTubes = (ListView) findViewById(R.id.listViewTubes);
        listViewTubes.setDivider(new ColorDrawable(this.getResources().getColor(R.color.colorAccent)));   //0xAARRGGBB
        listViewTubes.setDividerHeight(2);
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
