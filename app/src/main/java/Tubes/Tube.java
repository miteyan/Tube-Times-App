package Tubes;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by miteyan on 08/09/2016.
 */
public class Tube {

    public Tube (String line) {
        this.stationName = stationName;
        System.out.println();
        System.out.println("New Tube added: "+line);
    }

    private String line;
    private String platform;
    private String destination;
    private String currLocation;
    private String arrivalTime;
    private String stationName;
    private Date date;
    private String timeTo;

    public String getLine() {
        return line;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
        System.out.println("Station name set: " +stationName);
    }

    public void setLine(String line) {
        this.line = line;
        System.out.println("Line: "+line);
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
        System.out.println("Platform set: " +platform);

    }

    public String getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(String currLocation) {
        this.currLocation = currLocation;
        System.out.println("Current location set: " +currLocation);

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
        System.out.println("Destination set: " +destination);

    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) throws ParseException {
        System.out.println(arrivalTime);
        String eTime = arrivalTime.substring(arrivalTime.indexOf("T")+1,arrivalTime.indexOf("."));
        System.out.println(eTime);
        DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        date =  sdf.parse(eTime);
        String time= "";
        if (date.getMinutes()<10){
            time =(date.getHours())+":0" + date.getMinutes()+":"+date.getSeconds();
        }else{
            time =(date.getHours())+":" + date.getMinutes()+":"+date.getSeconds();
        }
        this.arrivalTime = time;
        System.out.println("Arrival set: " +this.arrivalTime);
        getTimeTo();
    }

    public String getTimeTo(){
        Date now = new Date();
        date.setMonth(now.getMonth());
        date.setYear(now.getYear());
        date.setDate(now.getDate());
        long diffMs = date.getTime()-now.getTime();
        long diffSec = diffMs / 1000;
        long min = diffSec / 60;
        long sec = diffSec % 60;
        timeTo = min+ ":"+sec;
        String timeLeft="Arriving in "+min+" minutes and "+sec+" seconds.";
        System.out.println(timeLeft);
        return timeLeft;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "line='" + line + '\'' +
                ", platform='" + platform + '\'' +
                ", destination='" + destination + '\'' +
                ", currLocation='" + currLocation + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", stationName='" + stationName + '\'' +
                ", date=" + date +
                ", ='" + timeTo + '\'' +
                '}';
    }


}
