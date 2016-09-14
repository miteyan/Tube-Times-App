package com.miteyan.tubemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TubeActivity extends AppCompatActivity {
    private TextView tubes;
    private List<ListViewItem> listStationsItems = Collections.EMPTY_LIST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube);

        ArrayList<Tube> t = getIntent().getParcelableArrayListExtra("Tubes");
        //sort on time to
        Toast.makeText(this,t.get(1).getStationName(),Toast.LENGTH_LONG).show();
        String s = "";
        for (int i =0 ; i<t.size(); i++) {
            s+=t.get(i).toString();
        }

        tubes = (TextView) findViewById(R.id.TUBES);
        tubes.setText(s);
    }
}
