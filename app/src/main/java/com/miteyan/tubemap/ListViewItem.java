package com.miteyan.tubemap;

import java.util.List;

/**
 * Created by miteyan on 11/09/2016.
 */
public class ListViewItem{
    private String stationName;
    private String stationID;
    private List<String> tubeLinesList;
    private String distance;

//    public ListViewItem(String stationID,String stationName, String tubeLines, String distance) {
//        this.stationID=stationID;
//        this.stationName = stationName;
//        this.tubeLines = tubeLines;
//        this.distance = distance;
//    }
    public ListViewItem(String stationID, String stationName, List<String> tubeLines, String distance) {
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

//    public void setDistance(String distance) {
//        this.distance = distance;
//    }
//    public void setStationID(String stationID) {
//        this.stationID = stationID;
//    }
//    public void setTubeLines(String tubeLines) {
//        this.tubeLines = tubeLines;
//    }
//    public String getTubeLines() {
//        return tubeLines;
//    }
//    public void setStationName(String stationName) {
//    this.stationName = stationName;
//}

}

