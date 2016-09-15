package com.miteyan.tubemap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import android.widget.TextView;

/**
 * Created by miteyan on 12/09/2016.
 */
public class CustomListAdapterTubes extends BaseAdapter {

    List<ListViewItemTubes> items;
    LayoutInflater inflater;

    public CustomListAdapterTubes(Activity context, List<ListViewItemTubes> items) {
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
        ListViewItemTubes current = items.get(i);

        if (view==null){
            vi = inflater.inflate(R.layout.tube_card,null);
        }

        TextView arrivalTime = (TextView) vi.findViewById(R.id.tArrivalTime);
        arrivalTime.setText(current.getArrivalTime());

        TextView currentL = (TextView) vi.findViewById(R.id.tCurrLocation);
        currentL.setText(current.getCurrentLocation());

        TextView destination = (TextView) vi.findViewById(R.id.tDestination);
        destination.setText(current.getDestination());

        TextView timeTo = (TextView) vi.findViewById(R.id.tTimeTo);
        timeTo.setText(current.getTime());

        TextView platform = (TextView) vi.findViewById(R.id.tPlatform);
        platform.setText(current.getPlatform());

        String tube = current.getTubeLine();

        TextView line = (TextView) vi.findViewById(R.id.tTube);
        if (tube.equals("metropolitan")) {
            line.setTextColor(Color.rgb(94,13,58));
        }


        line.setText(tube);
        System.out.println("LINE" +line.getText());

        return vi;

    }
}
