package com.miteyan.tubemap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import android.widget.TextView;

/**
 * Created by miteyan on 12/09/2016.
 */
public class CustomListAdapterStations extends BaseAdapter {

    List<ListViewItemStations> items;
    LayoutInflater inflater;

    public CustomListAdapterStations(Activity context, List<ListViewItemStations> items) {
        this.items=items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        ListViewItemStations current = items.get(i);

        if (view==null){
            vi = inflater.inflate(R.layout.station_card,null);
        }

        TextView stationName = (TextView) vi.findViewById(R.id.stationName);
        stationName.setText(current.getStationName());

        TextView stationTubes = (TextView) vi.findViewById(R.id.stationLines);
        stationTubes.setText(current.getTubeLines());

        TextView stationDistance = (TextView) vi.findViewById(R.id.stationDistance);
        stationDistance.setText("Distance: "+current.getDistance()+"m");

        return vi;
    }
}
