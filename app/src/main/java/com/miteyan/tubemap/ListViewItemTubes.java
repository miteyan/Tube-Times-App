package com.miteyan.tubemap;

import java.util.List;

/**
 * Created by miteyan on 11/09/2016.
 */
public class ListViewItemTubes{
    private String arrivalTime;
    private String tubeLine;
    private String destination;
    private String time;
    private String platform;
    private String currentLocation;

    public ListViewItemTubes(String arrivalTime, String tubeLine, String destination, String time, String platform, String currentLocation) {
        this.arrivalTime = arrivalTime;
        this.tubeLine = tubeLine;
        this.destination = destination;
        this.time = time;
        this.platform = platform;
        this.currentLocation = currentLocation;
    }


    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTubeLine() {
        return tubeLine;
    }

    public void setTubeLine(String tubeLine) {
        this.tubeLine = tubeLine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }



}
