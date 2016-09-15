package com.miteyan.tubemap;

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
    public String getTime() {
        return time;
    }
    public String getTubeLine() {
        return tubeLine;
    }
    public String getDestination() {
        return destination;
    }
    public String getPlatform() {
        return platform;
    }
    public String getCurrentLocation() {
        return currentLocation;
    }
}
