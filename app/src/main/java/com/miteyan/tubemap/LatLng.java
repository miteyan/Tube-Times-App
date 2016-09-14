package com.miteyan.tubemap;

/**
 * Created by miteyan on 08/09/2016.
 */
public class LatLng {
    private double lat;
    private double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
    public double getLng() {
        return lng;
    }
    public double getLat() {
        return lat;
    }

}
