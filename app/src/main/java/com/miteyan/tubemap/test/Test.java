//package com.miteyan.tubemap.test;
//
//import com.miteyan.tubemap.*;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.List;
//
///**
// * Created by miteyan on 08/09/2016.
// */
//public class Test {
//
//    public Test(LatLng myLocation) {
////        this.Location=myLocation;
//    }
//
//    static LatLng Location = new LatLng(51.548123, -0.447846);
//
//    public static void main(String[] args) throws IOException, ParseException {
//        //get nearest stations to your locations
//        NearestStations nearestStations = new NearestStations(Location);
//
//        List<Station> stations = nearestStations.getStations();
//
//        for (int i = 0  ; i <stations.size(); i++) {
//            System.out.println(stations.get(i).toString());
//        }
//        Station nearestStation = stations.get(0);
//
//        List<String> tubes = nearestStation.linesAtStation;
//        System.out.println("Nearest station: " + nearestStation.getName());
//        System.out.println(nearestStation.toString());
////        nearest station in sorted station array
//        String stationID = nearestStation.getStationID();
////        get times in those stations
//        TubeTimes times = new TubeTimes(tubes, stationID);
//
//
//
//    }
//
//
//    public static void print(List<String> x) {
//        for (int i = 0; i <x.size() ; i++) {
//            System.out.println(x.get(i));
//        }
//    }
//
//}
