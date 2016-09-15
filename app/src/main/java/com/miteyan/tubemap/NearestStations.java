package com.miteyan.tubemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by miteyan on 08/09/2016.
 */
public class NearestStations {
    static String link ="http://transportapi.com/v3/uk/tube/stations/near.json?app_id=03bf8009&app_key=d9307fd91b0247c607e098d5effedc97&lat=" +
            "51.548584" +
            "&lon=" +
            "-0.448603" +
            "&page=1&rpp=8";
    private final String NUM_STATIONS = "6";

    public NearestStations(LatLng location) throws IOException {//initialise API link to get the stations then parse
        link ="http://transportapi.com/v3/uk/tube/stations/near.json?app_id=03bf8009&app_key=d9307fd91b0247c607e098d5effedc97&lat=" +
                location.getLat() + "&lon=" + location.getLng() + "&page=1&rpp="+NUM_STATIONS;
    }
    public NearestStations(LatLng location, boolean nearest) throws IOException {//initialise API link to get the nearest station then parse
        link ="http://transportapi.com/v3/uk/tube/stations/near.json?app_id=03bf8009&app_key=d9307fd91b0247c607e098d5effedc97&lat=" +
                location.getLat() + "&lon=" + location.getLng() + "&page=1&rpp=1";
    }


    public static List<String> save(String URL) throws IOException {//saves web page generated into a string array
        BufferedReader input = null;
        String all="";
        try{
            java.net.URL url = new URL(URL);
            input = new BufferedReader(new InputStreamReader(url.openStream()));
            all=input.readLine();
        } finally {
            if (input != null) {
                input.close();
            }
        }
        List<String> strings = Arrays.asList(all.split("\\s*,\\s*"));

        return strings;
    }

    public static List<Station> crawl(List<String> lines) {//parse webpage string array into the Station object
        int size = lines.size();

        List<Station> stations = new ArrayList<Station>();
        String line = "";
        int stationNum =0;
        int index = 0;

        for (int i = 0 ; i < size ; i++ ) {
            line = lines.get(i);
            if (line.contains("atcocode")) {
                String stationID =regex(line, "atcocode");
                stations.add(new Station(stationID));
            }
            //if line contains line id - add a new tube to the array list of tubes.
            else if (line.contains("name")) {
                stations.get(index).setName(regex(line, "name"));
            }
            StringBoolPair pair = containsTube(line);
            if ( pair.getBool()){
                stations.get(stationNum).addTubeLine(pair.getStr());
            }
            if (line.contains("distance")) {
                stations.get(index).setDistance(line.replaceAll("\\D+", ""));
                stationNum++;
                index++;
            }
        }
        return stations;
    }

    private static StringBoolPair containsTube(String line) {

        StringBoolPair pair= new StringBoolPair(false, "");

        Lines[] tubeLines = Lines.getLines();
        for (int i = 0 ; i<tubeLines.length; i++) {
            if (line.contains(tubeLines[i].getStationID())){
                pair.setBool(true);
                pair.setStr(tubeLines[i].getStationID());
            }
        }
        return pair;
    }

    public static List<Station> getStations() throws IOException {
//        print(save(link));
        System.out.println("Getting stations");
        return crawl(save(link));
    }

    public static String[] getStationsString() throws IOException {
//        print(save(link));
        System.out.println("Getting stations");

        List<Station> stations = crawl(save(link));
        int size= stations.size();
        String[] stationsName = new String[size];
        for (int i = 0; i<size; i++) {
            stationsName[i] = stations.get(i).getName();
            System.out.println(stationsName[i]);
        }
        return stationsName;
    }

    public static List<ListViewItemStations> getStationsList() throws IOException {
//        print(save(link));
        System.out.println("Getting stations");
        List<Station> stationsList =  crawl(save(link));

        int size= stationsList.size();
        ArrayList<ListViewItemStations> stationsListItems = new ArrayList<>(size);

        for (int i = 0; i<size; i++) {
            Station current = stationsList.get(i);

            ListViewItemStations newItem = new ListViewItemStations(current.getStationID(),current.getName(),current.getTubes(),current.getDistance());
            stationsListItems.add(i,newItem);
        }
        return stationsListItems;
    }

    public static String regex(String line, String arg) {
        if (line.contains(arg)) {
            Pattern p = Pattern.compile("\"(.*?)\":\"(.*?)\"");//pattern to extract second x in the form of ' "x":"x" '
            Matcher m = p.matcher(line);
            m.find();
            return m.group(2);
        }else{
            return "error";
        }
    }

}
