package com.miteyan.tubemap;

import java.util.ArrayList;

/**
 * Created by miteyan on 11/09/2016.
 */
public class ListViewItem{
    public ListViewItem(String stationName, String tubeLines, String distance) {
        this.stationName = stationName;
        this.tubeLines = tubeLines;
        this.distance = distance;
    }
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTubeLines() {
        return tubeLines;
    }

    public void setTubeLines(String tubeLines) {
        this.tubeLines = tubeLines;
    }

    public String getDistance() {
        return distance;
    }



    public void setDistance(String distance) {
        this.distance = distance;
    }

    private String stationName;
    private String tubeLines;
    private String distance;
}

