package Tubes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TubeTimes {
    private static String link = "https://api.tfl.gov.uk/Line/%20piccadilly/Arrivals?stopPointId=9400ZZLUHGD&app_id=318edad9&app_key=f62235cee14d8cd591f491fcd0cf5ac0";

    public TubeTimes(String tubeLine, String stationID) {
        link = "https://api.tfl.gov.uk/Line/%20" +
                tubeLine +
                "/Arrivals?stopPointId=" +
                stationID +
                "&app_id=318edad9&app_key=f62235cee14d8cd591f491fcd0cf5ac0";
    }

    public TubeTimes(List<String> allTubeLines, String stationID) throws IOException, ParseException {
        for (int i = 0; i < allTubeLines.size(); i++) {
            System.out.println("Getting: "+ allTubeLines.get(i));
            link = "https://api.tfl.gov.uk/Line/%20" +
                    allTubeLines.get(i) +
                    "/Arrivals?stopPointId=" +
                    stationID +
                    "&app_id=318edad9&app_key=f62235cee14d8cd591f491fcd0cf5ac0";
            getTimes();
        }
    }

    public static List<Tube> getTrains(List<String> lines) throws ParseException {
        int size = lines.size();


        List<Tube> tubes = new ArrayList<Tube>();

        String line = "";
        int index = 0;
        for (int i = 0 ; i < size ; i++ ) {
            line = lines.get(i);

            //set station name
            if (line.contains("stationName")) {
                String stationName = regex(line, "stationName");
                //add new tube if arriving
                tubes.add(new Tube(stationName));
            }

            int numTubes = tubes.size();
            //if line contains line id - add a new tube to the array list of tubes.
            if (line.contains("lineId")) {
                tubes.get(index).setLine(regex(line, "lineId"));
            }

            //set platform
            if (line.contains("platformName")) {
                tubes.get(index).setPlatform(regex(line, "platformName"));
            }
            //set curr location
            if (line.contains("currentLocation")) {
                tubes.get(index).setCurrLocation(regex(line, "currentLocation"));
            }
            //set destination
            if (line.contains("towards")) {
                tubes.get(index).setDestination(regex(line, "towards"));
            }
            //set arrival time
            if (line.contains("expectedArrival")) {
                tubes.get(index).setArrivalTime(regex(line, "expectedArrival"));
                index++;
            }
        }
        return tubes;
    }
    //Use regular experessions to extract information from the API lines.
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

    public List<Tube> getTimes() throws IOException, ParseException {
        return getTrains(save(link));
    }

    public static void main(String args[]) throws IOException, ParseException {
        List<String> list =(save(link));
        print(list);
        List<Tube> tubes = getTrains(list);

        for (int i =0 ; i<tubes.size(); i++) {
            System.out.println(tubes.get(i).toString());
        }


    }

    public static void print(List<String> x) {
        for (int i = 0; i <x.size() ; i++) {
            System.out.println(x.get(i));
        }
    }
    /*/
    Saves APIs web page as a string
     */
    public static List<String> save(String URL) throws IOException {
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

}
