package com.miteyan.tubemap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miteyan on 08/09/2016.
 */

public class Station {

    public Station(String stationID) {
        this.stationID = stationID;
        System.out.println();
    }


    List<String> linesAtStation= new ArrayList<String>();
    String name;

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    String stationID;
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
        System.out.println("Distance set: " +distance);

    }

    String distance;

    public List<String> getTubes() {
        return linesAtStation;
    }

    public String getTubeLines() {
        int numLines = linesAtStation.size();
        String ret = "";
        for (int i = 0 ; i<numLines; i++) {
            ret= ret+linesAtStation.get(i) + " ";
        }
        return ret;
    }

    public void setTubeLines(List<String> tubes) {
        this.linesAtStation = tubes;
    }
    public void addTubeLine(String line) {
        linesAtStation.add(line);
        System.out.println(line+ " added");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Station name: " + name);
    }

    @Override
    public String toString() {
        return "Station{" +
                "linesAtStation=" + print(linesAtStation) +
                ", name='" + name + '\'' +
                ", stationID='" + stationID + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
    public static String print(List<String> x) {
        String s = "";
        for (int i = 0; i <x.size() ; i++) {
            s+=(x.get(i))+" ";
        }
        return s;
    }

}
