package com.miteyan.tubemap;

import java.util.List;

/**
 * Created by miteyan on 11/09/2016.
 */
public class ListViewItemStations {
    private String stationName;
    private String stationID;
    private List<String> tubeLinesList;
    private String distance;

    public ListViewItemStations(String stationID, String stationName, List<String> tubeLines, String distance) {
        this.stationID=stationID;
        this.stationName = stationName;
        this.tubeLinesList = tubeLines;
        this.distance = distance;
    }

    public String getStationID() {
        return stationID;
    }

    public String getStationName() {
        return stationName;
    }
    public List<String> getTubeLineList() {
        return tubeLinesList;
    }

    public String getTubeLines() {
        String ret = " ";
        for (int i =0 ; i<tubeLinesList.size();i++){
            ret = ret + tubeLinesList.get(i)+ " ";
        }
        if (ret==""){
            return "NONE";
        }
        return ret;
    }


    public String getDistance() {
        return distance;
    }
}

